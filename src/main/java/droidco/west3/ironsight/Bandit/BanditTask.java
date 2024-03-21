package droidco.west3.ironsight.Bandit;

import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.FrontierLocation.LocationType;
import droidco.west3.ironsight.FrontierLocation.LocationUI;
import droidco.west3.ironsight.Globals.Utils.BanditUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
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
    private ArrayList<BanditTask> tasks = new ArrayList<>();
    private final IronSight plugin;
    private final Bandit b;
    private int tick = 0;
    private final int combatLogTimer = 30;
    private int combatLogCounter = 0;
    private int wantedMin = 2;
    private int wantedSec = 0;
    private final int contractTimer = 30;
    private int contractCounter = 0;
    private int wantedTownCounter = 0;
    private int wantedTownTimer = 10;
    private boolean escapeFlag = false;
    private final Player p;
    private boolean wildernessFlag;
    private HashMap<String, FrontierLocation> locations;

    public BanditTask(IronSight plugin, Bandit b, Player p) {

        this.plugin = plugin;
        this.b = b;
        this.p = p;
        this.wildernessFlag = false;
        Contract.assignPlayerContracts(p,b);
        tasks.add(this);
        this.runTaskTimer(plugin, 0, 10);

        b.setDoingContract(false);
        //Contract.assignPlayerContracts(p,b);
    }

    @Override
    public void run() {
        //LESS THAN ONE-SECOND PLAYER EVENTS:
        if (b.isTrackingLocation() && !b.isTrackingPlayer()) {
            p.setCompassTarget(FrontierLocation.getLocation(b.getTrackingLocation().getLocName()).getCenterLocation(p));
            Double distance = FrontierLocation.getLocation(b.getTrackingLocation().getLocName()).getCenterLocation(p).distance(p.getLocation());
            int distanceMsg = distance.intValue();
            p.spigot().sendMessage(
                    ChatMessageType.ACTION_BAR,
                    new TextComponent(String.valueOf(distanceMsg) + ChatColor.GRAY + " blocks away!"));
        } else {
            if (b.getTargetedPlayer() != null) {
                if (b.getTargetedPlayer().isOnline()) {
                    p.setCompassTarget(b.getTargetedPlayer().getLocation());
                    Double distance = b.getTargetedPlayer().getLocation().distance(p.getLocation());
                    int distanceMsg = distance.intValue();
                    p.spigot().sendMessage(
                            ChatMessageType.ACTION_BAR,
                            new TextComponent(String.valueOf(distanceMsg) + ChatColor.GRAY + " blocks away!"));

                }
            }

        }

        //Titles for locations
        FrontierLocation.displayLocation(p);
        //Refresh Players siderbar scoreboard
        BanditUtils.loadScoreBoard(p, b, combatLogTimer - combatLogCounter, wantedMin, wantedSec);
        // PLAYER RESPAWN STUFF
        if (b.isRespawning()) {
            // SEND PLAYER TO PRISON
            b.setRespawning(false);
            if (b.isJailedFlag()) {
                //REPSAWN IN PRISON
                b.setJailedFlag(false);
                FrontierLocation prison = FrontierLocation.getLocation("Prison");
                //Get the bukkit location of the respawn points from the Iron Sight Location (confusing)
                org.bukkit.Location respawn = new org.bukkit.Location(p.getWorld(), prison.getSpawnX(), prison.getSpawnY(), prison.getSpawnZ());
                p.sendTitle(ChatColor.GRAY + "You are now in" + ChatColor.DARK_RED + " Prison!", ChatColor.GRAY + "Mine to 0 bounty to leave.");
                p.teleport(respawn);
                p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, 1);
            }else{
                //RESPAWNING IN A TOWN
                p.setWalkSpeed(0);
                p.setFlySpeed(0);
                p.setSprinting(false);
                if (p.getOpenInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Choose Town:") != true) {
                    p.openInventory(LocationUI.openContractorTitleSelectUi(p));
                }
            }
        }

        //  LOCATION SPECIFIC
        FrontierLocation currentLoc = b.getCurrentLocation();

        if (b.isJailed()) {
            if (!currentLoc.getType().equals(LocationType.PRISON)) {
                //Player is escaping! PUT LOGIC HERE
                b.setEscaping(true);
                if(!escapeFlag){
                    escapeFlag = true;
                    PrisonEscapeTask escapee = new PrisonEscapeTask(plugin,p);
                    p.sendTitle(ChatColor.RED+String.valueOf(ChatColor.BOLD)+"Escapee!",ChatColor.GRAY+"Return to jail or gain bounty",1,2,1);
                }
            }else{
                //They are in prison!
                escapeFlag = false;
                b.setEscaping(false);
            }
        }

        //TOWNS
        if (currentLoc.getType().equals(LocationType.TOWN)) {
            //p.sendMessage("You cannot damage players in town!");
            p.setLastDamage(0.0);
            //NO WANTED PLAYERS IN TOWN!!!
            //Give them ten seconds to leave before killing them
            if (b.isWanted()) {
                if (tick % 3 == 0) {
                    //DISPLAY HOW LONG THEY HAVE TO LEAVE BEFORE KILLING THEM
                    if (wantedTownCounter == 0) {
                        //TIMER JUST STARTED!
                        p.sendTitle(ChatColor.DARK_RED+"Wanted!",ChatColor.GRAY+"Leave town or die.",1,2,1);
                    }
                    p.spigot().sendMessage(
                            ChatMessageType.ACTION_BAR,
                            new TextComponent(ChatColor.RED + ""+(wantedTownTimer - wantedTownCounter)+ChatColor.RESET+ " seconds remaining."));
                    if (wantedTownCounter == wantedTownTimer) {
                        p.damage(100);
                    }
                    wantedTownCounter++;
                }
            }
        }
        //ILLEGAL
        if (currentLoc.getType().equals(LocationType.ILLEGAL) || currentLoc.getType().equals(LocationType.OIL_FIELD)) {
            //Increase players bounty in illegal area
            if (tick % 3 == 0) {
                b.updateBounty(2);
            }
        }

        //TIMED EVENTS

        //Roughly 1 second
        if (tick % 3 == 0) {
            //  UPDATE JAILTIME
            if(b.isJailed()){
                //REMEMBER TO CHECK IF THEY'RE INSIDE A PRISON
                b.updateBounty(-1);
                if (b.getBounty() <= 0) {
                    BanditUtils.releasePrisoner(p,b);
                }
            }

            //  HANDLE WANTED TIMER
            if (b.isWanted()) {
                wantedSec--;
                if (wantedSec == -1) {
                    wantedSec = 59;
                    wantedMin--;
                }
                if (wantedMin == -1) {
                    //Timer is complete!
                    wantedMin = 2;
                    p.sendMessage(ChatColor.GRAY + "You are no longer " + ChatColor.DARK_RED + "wanted.");
                    b.setWanted(false);
                }
            }
            //  HANDLE COMBAT BLOCK TIMER
            if (b.isCombatBlocked()) {
                if (b.isCombatBlockFlag()) {
                    combatLogCounter = 0;
                    b.setCombatBlockFlag(false);
                }
                if (combatLogCounter == combatLogTimer) {
                    b.setCombatBlocked(false);
                    p.sendMessage(ChatColor.GRAY + "You are" + ChatColor.GREEN + " safe " + ChatColor.GRAY + "to log off");
                }
                combatLogCounter++;
            }
            // HANDLE PLAYER BLEED EFFECT
            if (b.isBleeding()) {
                p.setHealth(p.getHealth() - 1.5);
                for (int i = 0; i < 13; i++) {
                    p.spawnParticle(Particle.BLOCK_DUST, p.getLocation().add(0.5, 0.5, 0.5), 1, 1, 1, 1, 1, new ItemStack(Material.RED_WOOL));
                }
            }

//            //HANDLE CONTRACT TIMER
//
//            p.setLevel(contractTimer - contractCounter);
//            if (contractTimer == contractCounter) {
//                ContractUtils.initializeContracts(b);
//                p.sendMessage(ChatColor.GOLD + "Contracts" + ChatColor.GREEN + " reset!");
//                contractCounter = 0;
//            }
//            contractCounter++;
//
        }
        //END OF LOOP
        tick++;
        //CHECK IF PLAYER IS STILL ON
        if (!p.isOnline()) {
            if (b.isCombatBlocked()) {
                p.damage(100);
            }
            this.cancel();
            tasks.remove(this);
        }
    }
}
