package vn.aoi.onii.listener;

import org.bukkit.event.*;
import org.bukkit.event.player.PlayerQuitEvent;
import vn.aoi.onii.data.PlayerRepository;
import vn.aoi.onii.manager.PlayerManager;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        var d=PlayerManager.get(e.getPlayer().getUniqueId());
        if(d==null) return;

        PlayerRepository.save(d);
        PlayerManager.remove(d.uuid);
    }
}
