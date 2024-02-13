package droidco.west3.ironsight.Objects.Player.Tasks;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Objects.Contracts.Utils.ContractUtils;
import droidco.west3.ironsight.Objects.Location.Location;
import droidco.west3.ironsight.Objects.Location.LocationType;
import droidco.west3.ironsight.Objects.Location.LocationUI;
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
    private int wantedMin = 2;
    private int wantedSec = 0;
    private final int contractTimer = 30;
    private int contractCounter = 0;
    private int wantedTownCounter = 0;
    private int wantedTownTimer = 10;
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

       //Respawn menu until they choose a town
        if(iPlayer.isRespawning()){
            p.setWalkSpeed(0);
            p.setFlySpeed(0);
            p.setSprinting(false);
            if(p.getOpenInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY+"Choose Town:") != true){
                p.openInventory(LocationUI.openContractorTitleSelectUi(p));
            }
        }
       //Get time remaining, and mod by 60 to get minutes

        PlayerUtils.loadScoreBoard(p, iPlayer, combatLogTimer-combatLogCounter,wantedMin,wantedSec);
        //Handle location specific things
        Location currentLoc = iPlayer.getCurrentLocation();
        if(currentLoc.getType().equals(LocationType.TOWN)){
            //p.sendMessage("You cannot damage players in town!");
            p.setLastDamage(0.0);
            //NO WANTED PLAYERS IN TOWN!!!
            //Give them ten seconds to leave before killing them
            if(iPlayer.isWanted()){
                if(tick % 3 == 0){
                    //DISPLAY HOW LONG THEY HAVE TO LEAVE BEFORE KILLING THEM
                    p.sendMessage("Wanted players not allowed in towns, leave or die.");
                    if(wantedTownCounter == 0){
                        //TIMER JUST STARTED!
                        p.sendMessage("Wanted players not allowed in towns, leave or die.");
                    }
                    p.sendMessage((wantedTownTimer-wantedTownCounter)+" seconds to leave.");
                    if(wantedTownCounter == wantedTownTimer){
                        p.damage(100);
                    }
                    wantedTownCounter++;
                }
            }

        }
        if(currentLoc.getType().equals(LocationType.ILLEGAL)){
            //Increase players bounty in illegal area
            iPlayer.updateBounty(3);
        }
        if(currentLoc.getType().equals(LocationType.Prison)){
            if(iPlayer.isJailed()){
                iPlayer.updateBounty(-1);
                if(iPlayer.getBounty() == 0){
                    iPlayer.setJailed(false);
                    p.sendMessage(ChatColor.GRAY+ "You are released from"+ ChatColor.RED+" jail!");
                    p.damage(100);

                }
            }
        }


        //Roughly 1 second
        if(tick % 3 == 0){
            //Check if player is in illegal area and increase their bounty
            Location.increaseIllegalBounty(iPlayer, 2);
            if(iPlayer.isWanted()){
                wantedSec--;
                if(wantedSec == -1){
                    wantedSec = 59;
                    wantedMin--;
                }
                if(wantedMin == -1){
                    //Timer is complete!
                    wantedMin = 2;
                    p.sendMessage(ChatColor.GRAY+"You are no longer wanted.");
                    iPlayer.setWanted(false);
                }
            }

            if(iPlayer.isJailed()){

            }

            if(iPlayer.isCombatBlocked()){
                if(iPlayer.isCombatBlockFlag()){
                    combatLogCounter = 0;
                    iPlayer.setCombatBlockFlag(false);
                }
                if(combatLogCounter == combatLogTimer){
                    iPlayer.setCombatBlocked(false);
                    p.sendMessage(ChatColor.GRAY+"You are safe to log off");
                }
                combatLogCounter++;
            }
            if(iPlayer.isWanted()){

            }
        }
        //it has been close to one second.
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
        //END OF LOOP
        tick++;
        //CHECK IF PLAYER IS STILL ON
        if(!p.isOnline()){
            if(iPlayer.isCombatBlocked()){
                p.damage(100);
            }
            this.cancel();
            tasks.remove(this);
        }
    }
}
