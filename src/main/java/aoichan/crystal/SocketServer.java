package aoidev.crystal;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;

/**
 * Minimal socket listener for cross-server commands (Gems sync). Simple protocol:
 * send "GIVE;<type>;<level>" -> creates a gem (no auth!). Production: add auth, encryption.
 */
public class SocketServer {

    private final Plugin plugin;
    private final int port;
    private final GemManager gemManager;
    private Thread thread;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public SocketServer(Plugin plugin, int port, GemManager gemManager) {
        this.plugin = plugin;
        this.port = port;
        this.gemManager = gemManager;
    }

    public void start() {
        running.set(true);
        thread = new Thread(this::runServer, "Gems-Socket");
        thread.setDaemon(true);
        thread.start();
        plugin.getLogger().info("Gems SocketServer started on port " + port);
    }

    private void runServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (running.get()) {
                try {
                    Socket client = serverSocket.accept();
                    handleClient(client);
                } catch (Exception e) {
                    if (running.get()) plugin.getLogger().log(Level.WARNING, "Socket accept error", e);
                }
            }
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to start socket server", e);
        }
    }

    private void handleClient(Socket client) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
                String line = in.readLine();
                if (line == null) return;
                // Very simple command parser
                if (line.startsWith("GIVE;")) {
                    String[] parts = line.split(";");
                    if (parts.length >= 3) {
                        String type = parts[1];
                        int level = Integer.parseInt(parts[2]);
                        gemManager.createAndSaveGemAsync(type, level);
                        plugin.getLogger().info("Socket created gem: " + type + " lvl " + level + " (via " + client.getInetAddress() + ")");
                    }
                }
            } catch (Exception e) {
                plugin.getLogger().log(Level.WARNING, "Socket client error", e);
            } finally {
                try { client.close(); } catch (Exception ignored) {}
            }
        });
    }

    public void shutdown() {
        running.set(false);
        try {
            if (thread != null) thread.interrupt();
        } catch (Exception ignored) {}
    }
                  }
