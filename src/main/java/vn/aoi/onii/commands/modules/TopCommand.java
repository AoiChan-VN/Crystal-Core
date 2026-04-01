package vn.aoi.onii.commands.modules;

import cloud.commandframework.annotations.*;
import org.bukkit.command.CommandSender;
import vn.aoi.onii.commands.framework.AbstractCommand;
import vn.aoi.onii.commands.framework.annotations.CommandCooldown;
import vn.aoi.onii.config.MessageManager;
import vn.aoi.onii.utils.PaginationUtil;

import java.util.ArrayList;
import java.util.List;

public class TopCommand extends AbstractCommand {

    public TopCommand(vn.aoi.onii.Main plugin) {
        super(plugin);
    }

    @Override
    public void register(cloud.commandframework.annotations.AnnotationParser<?> parser) {
        parser.parse(this);
    }

    @CommandMethod("aoi bxh [page]")
    @CommandCooldown(seconds = 5)
    public void top(CommandSender sender, @Argument(value = "page", defaultValue = "1") int page) {

        sender.sendMessage(MessageManager.get("top.header"));

        List<String> list = new ArrayList<>();

        plugin.getPlayerManager().getTop10().forEach((uuid, data) -> {
            list.add("§e" + data.getRealm() + " §7- Lv." + data.getLevel());
        });

        PaginationUtil<String> paginator = new PaginationUtil<>();
        List<String> pageData = paginator.paginate(list, page, 5);

        if (pageData.isEmpty()) {
            sender.sendMessage("§cKhông có dữ liệu!");
            return;
        }

        pageData.forEach(sender::sendMessage);
    }
}
