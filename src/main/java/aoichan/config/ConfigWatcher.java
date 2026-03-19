package aoichan.config;

import java.io.IOException;
import java.nio.file.*;

public class ConfigWatcher implements Runnable {

    private final Path path;
    private final Runnable reload;

    public ConfigWatcher(Path path, Runnable reload) {
        this.path = path;
        this.reload = reload;
    }

    @Override
    public void run() {
        try {
            WatchService watch = FileSystems.getDefault().newWatchService();
            path.register(watch, StandardWatchEventKinds.ENTRY_MODIFY);

            while (true) {
                WatchKey key = watch.take();

                for (WatchEvent<?> event : key.pollEvents()) {
                    reload.run();
                }

                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
 
