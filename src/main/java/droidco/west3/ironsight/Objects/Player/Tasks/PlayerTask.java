package droidco.west3.ironsight.Objects.Player.Tasks;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Objects.Contracts.Utils.ContractUtils;
import droidco.west3.ironsight.Objects.Location.Location;
import droidco.west3.ironsight.Objects.Player.IronPlayer;
import droidco.west3.ironsight.Utils.PlayerUtils;
import org.bukkit.ChatColor;
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
    private final int combatLogTimer = 30;
    private int combatLogCounter = 0;
    private final int contractTimer = 30;
    private int contractCounter = 0;
    //Seconds * ticks/second
    private final Player p;
    private boolean wildernessFlag;
    private HashMap<String, Location> locations;

    public PlayerTask(IronSight plugin, IronPlayer iPlayer, Player p){

        this.plugin = plugin;
        this.iPlayer = iPlayer;
        this.p = p;
        this.wildernessFlag = false;
        tick = 0;
        tasks.add(this);
        this.runTaskTimer(plugin, 0, 10);
        ContractUtils.initializeContracts(iPlayer);
    }
    @Override
    public void run() {
        //Titles for locations
       Location.displayLocation(p);
        PlayerUtils.loadScoreBoard(p, iPlayer, combatLogTimer-combatLogCounter,0,0);
        //Roughly 1 second
        if(tick % 3 == 0){
            //Check if player is in illegal area and increase their bounty
            Location.increaseIllegalBounty(iPlayer, 2);

            if(iPlayer.isCombatBlocked()){
                if(iPlayer.isCombatBlockFlag()){
                    combatLogCounter = 0;
                    iPlayer.setCombatBlockFlag(false);
                }
                if(combatLogCounter == combatLogTimer){
                    iPlayer.setCombatBlocked(false);
                }
                combatLogCounter++;
            }
        }
        //it has been less than one second.
        if(tick % 3 == 0){
            //Bleeding
            if(iPlayer.isBleeding()){
                //p.setHealth(p.getHealth()-1.5);
//                for(int i =0;i<13;i++){
//                    p.spawnParticle(Particle.REDSTONE, p.getLocation().add(0.5,0.5,0.5),1,1,1,1,1);
//                }
                if(contractTimer == contractCounter){
                    ContractUtils.initializeContracts(iPlayer);
                    p.sendMessage("Contracts reset!");
                    contractCounter = 0;
                }
                contractCounter++;
            }
        }
        tick++;
        if(!p.isOnline()){
            this.cancel();
            tasks.remove(this);
        }
    }
}
