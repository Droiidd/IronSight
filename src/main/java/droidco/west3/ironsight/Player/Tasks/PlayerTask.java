package droidco.west3.ironsight.Player.Tasks;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Player.IronPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class PlayerTask extends BukkitRunnable {
    private final ArrayList<PlayerTask> tasks = new ArrayList<>();
    private final IronSight plugin;
    private final IronPlayer iPlayer;
    private int tick;
    private final Player p;

    public PlayerTask(IronSight plugin, IronPlayer iPlayer, Player p){

        this.plugin = plugin;
        this.iPlayer = iPlayer;
        this.p = p;
        tick = 0;
        tasks.add(this);
        this.runTaskTimer(plugin, 0, 10);
    }
    @Override
    public void run() {

        if(tick % 2 == 0){
            //it has been one second.
            
        }
        tick++;
        if(!p.isOnline()){
            this.cancel();
            tasks.remove(this);
        }
    }
}
