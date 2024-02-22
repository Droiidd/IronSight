package droidco.west3.ironsight.Objects.Player.Tasks;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Objects.Contracts.Utils.ContractUtils;
import droidco.west3.ironsight.Objects.Location.Location;
import droidco.west3.ironsight.Objects.Location.LocationType;
import droidco.west3.ironsight.Objects.Location.LocationUI;
import droidco.west3.ironsight.Objects.Player.Bandit;
import droidco.west3.ironsight.Utils.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class BanditTask extends BukkitRunnable {
    private final ArrayList<BanditTask> tasks = new ArrayList<>();
    private final IronSight plugin;
    private final Bandit b;
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

    public BanditTask(IronSight plugin, Bandit b, Player p){

        this.plugin = plugin;
        this.b = b;
        this.p = p;
        this.wildernessFlag = false;
        tick = 0;
        tasks.add(this);
        this.runTaskTimer(plugin, 0, 10);
        ContractUtils.initializeContracts(b);
    }
    @Override
    public void run() {
        //LESS THAN ONE-SECOND PLAYER EVENTS:

        //Titles for locations
        Location.displayLocation(p);
        //Refresh Players siderbar scoreboard
        PlayerUtils.loadScoreBoard(p, b, combatLogTimer-combatLogCounter,wantedMin,wantedSec);
        // HANDLE PLAYER RESPAWN
        if(b.isRespawning()){
            p.setWalkSpeed(0);
            p.setFlySpeed(0);
            p.setSprinting(false);
            if(p.getOpenInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY+"Choose Town:") != true){
                p.openInventory(LocationUI.openContractorTitleSelectUi(p));
            }
        }
       // HANDLE PLAYER SEND TO PRISON
        if(b.isJailedFlag()){
            b.setJailedFlag(false);
            Location prison = Location.getLocation("Prison");
            //Get the bukkit location of the respawn points from the Iron Sight Location (confusing)
            org.bukkit.Location respawn = new org.bukkit.Location(p.getWorld(),prison.getSpawnX(),prison.getSpawnY(),prison.getSpawnZ());

            p.sendTitle(ChatColor.GRAY+ "You are now in"+ChatColor.DARK_RED+" Prison!",ChatColor.GRAY+"Mine to 0 bounty to leave.");
            p.teleport(respawn);
            p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN,1,1);
        }
        //HANDLE LOCATION SPECIFIC
        if(b.getCurrentLocation() != null){
            Location currentLoc = b.getCurrentLocation();
            //TOWNS
            if(currentLoc.getType().equals(LocationType.TOWN)){
                //p.sendMessage("You cannot damage players in town!");
                p.setLastDamage(0.0);
                //NO WANTED PLAYERS IN TOWN!!!
                //Give them ten seconds to leave before killing them
                if(b.isWanted()){
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
            //ILLEGAL
            if(currentLoc.getType().equals(LocationType.ILLEGAL)){
                //Increase players bounty in illegal area
                if(tick%3==0){
                    b.updateBounty(3);
                }
            }
            //PRISON
            if(currentLoc.getType().equals(LocationType.Prison)){
                if(b.isJailed()){
                    if(tick%3==0){
                        b.updateBounty(-1);
                    }
                    if(b.getBounty() <= 0){
                        b.setJailed(false);
                        p.sendMessage(ChatColor.GRAY+ "You are released from"+ ChatColor.RED+" jail!");
                        p.damage(100);
                    }
                }
            }
        }
        //TIMED EVENTS

        //Roughly 1 second
        if(tick % 3 == 0){
            // HANDLE WANTED TIMER
            if(b.isWanted()){
                wantedSec--;
                if(wantedSec == -1){
                    wantedSec = 59;
                    wantedMin--;
                }
                if(wantedMin == -1){
                    //Timer is complete!
                    wantedMin = 2;
                    p.sendMessage(ChatColor.GRAY+"You are no longer "+ChatColor.DARK_RED+"wanted.");
                    b.setWanted(false);
                }
            }
            // HANDLE COMBAT BLOCK TIMER
            if(b.isCombatBlocked()){
                if(b.isCombatBlockFlag()){
                    combatLogCounter = 0;
                    b.setCombatBlockFlag(false);
                }
                if(combatLogCounter == combatLogTimer){
                    b.setCombatBlocked(false);
                    p.sendMessage(ChatColor.GRAY+"You are"+ChatColor.GREEN+" safe "+ChatColor.GRAY+"to log off");
                }
                combatLogCounter++;
            }
            if(b.isWanted()){

            }
            //HANDLE CONTRACT TIMER
            p.setLevel(contractTimer - contractCounter);
            if(contractTimer == contractCounter){
                ContractUtils.initializeContracts(b);
                p.sendMessage(ChatColor.GOLD+"Contracts"+ChatColor.GREEN+" reset!");
                contractCounter = 0;
            }
            contractCounter++;
            // HANDLE PLAYER BLEED EFFECT
            if(b.isBleeding()){
                p.setHealth(p.getHealth()-1.5);
                for(int i =0;i<13;i++){
                    p.spawnParticle(Particle.BLOCK_DUST, p.getLocation().add(0.5,0.5,0.5),1,1,1,1,1,new ItemStack(Material.RED_WOOL));
                }
            }
        }
        //END OF LOOP
        tick++;
        //CHECK IF PLAYER IS STILL ON
        if(!p.isOnline()){
            if(b.isCombatBlocked()){
                p.damage(100);
            }
            this.cancel();
            tasks.remove(this);
        }
    }
}
