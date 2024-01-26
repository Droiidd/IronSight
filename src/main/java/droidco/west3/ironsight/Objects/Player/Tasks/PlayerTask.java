package droidco.west3.ironsight.Objects.Player.Tasks;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Objects.Location.Location;
import droidco.west3.ironsight.Objects.Player.IronPlayer;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerTask extends BukkitRunnable {
    private final ArrayList<PlayerTask> tasks = new ArrayList<>();
    private final IronSight plugin;
    private final IronPlayer iPlayer;
    private int tick;
    private final Player p;
    private boolean wildernessFlag;

    public PlayerTask(IronSight plugin, IronPlayer iPlayer, Player p){

        this.plugin = plugin;
        this.iPlayer = iPlayer;
        this.p = p;
        this.wildernessFlag = false;
        tick = 0;
        tasks.add(this);
        this.runTaskTimer(plugin, 0, 10);
    }
    @Override
    public void run() {
        HashMap<String, Location> locations =  Location.getLocations();

        locations.forEach((s, location) -> {
            if(location.isPlayerInside(p)){
                location.displayTitle(p);
                iPlayer.setCurrentLocation(location.getLocName());
                wildernessFlag = true;
            }
        });
        if(!wildernessFlag){

        }


        if(tick % 2 == 0){
            //it has been one second.
            if(iPlayer.isBleeding()){
                p.damage(1.5);
                for(int i =0;i<9;i++){
                    p.spawnParticle(Particle.BLOCK_DUST, p.getLocation().add(0.5,0.5,0.5),1,1,0.1,0.1,0.1, Material.REDSTONE_BLOCK.createBlockData());
                }
            }

        }
        tick++;
        if(!p.isOnline()){
            this.cancel();
            tasks.remove(this);
        }
    }
}
