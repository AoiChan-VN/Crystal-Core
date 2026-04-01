package vn.aoi.onii.commands.framework;

import vn.aoi.onii.Main;

public abstract class AbstractCommand implements ICommandModule {

    protected final Main plugin;

    public AbstractCommand(Main plugin) {
        this.plugin = plugin;
    }
}
