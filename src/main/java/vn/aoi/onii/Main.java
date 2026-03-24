package vn.aoi.onii;

import org.bukkit.plugin.java.JavaPlugin;
import vn.aoi.onii.core.Bootstrap;

public final class Main extends JavaPlugin {

    private Bootstrap bootstrap;

    @Override
    public void onEnable() {
        bootstrap = new Bootstrap(this);
        bootstrap.init();

        File file = new File(getDataFolder(), "skills.json");

        if (!file.exists()) {
        saveResource("skills.json", false);
}

SkillConfigManager skillConfigManager = new SkillConfigManager();
skillConfigManager.load(file);
    }

    @Override
    public void onDisable() {
        if (bootstrap != null) bootstrap.shutdown();
    }
}
