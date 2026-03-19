package aoichan.bootstrap;

import aoichan.engine.Engine;
import org.bukkit.plugin.java.JavaPlugin;

public class Loader {

    public static void init(JavaPlugin plugin) {
        Engine.start(plugin);
    }
} 
