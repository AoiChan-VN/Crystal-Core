package vn.aoi.onii.commands.modules;

import cloud.commandframework.annotations.CommandMethod;
import org.bukkit.entity.Player;
import vn.aoi.onii.commands.framework.AbstractCommand;
import vn.aoi.onii.config.MessageManager;

public class InfoCommand extends AbstractCommand {

    public InfoCommand(vn.aoi.onii.Main plugin) {
        super(plugin);
    }

    @Override
    public void register(cloud.commandframework.annotations.AnnotationParser<?> parser) {
        parser.parse(this);
    }

    @CommandMethod("aoi info")
    public void info(Player player) {

        var data = plugin.getPlayerManager().get(player);

        player.sendMessage(MessageManager.get("info.header"));
        player.sendMessage(MessageManager.get("info.realm")
                .replace("{realm}", data.getRealm()));
        player.sendMessage(MessageManager.get("info.level")
                .replace("{level}", String.valueOf(data.getLevel())));
        player.sendMessage(MessageManager.get("info.exp")
                .replace("{exp}", String.valueOf(data.getExp())));
    }
}
