package vn.aoi.onii.task;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Player;
import vn.aoi.onii.Main;
import java.util.Random;

public class ThunderTask extends BukkitRunnable {

    private int t=30;
    private final Player p;
    private final Random r=new Random();

    public ThunderTask(Player p){this.p=p;}

    public void run(){
        if(--t<=0){cancel();return;}
        var l=p.getLocation().clone().add(r.nextInt(10)-5,0,r.nextInt(10)-5);
        p.getWorld().strikeLightning(l);
    }

    public void start(){
        runTaskTimer(Main.get(),0,20);
    }
}
