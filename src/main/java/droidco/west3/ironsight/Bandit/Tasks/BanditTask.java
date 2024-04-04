package droidco.west3.ironsight.Bandit.Tasks;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.Contracts.Utils.ContractType;
import droidco.west3.ironsight.Contracts.Utils.DeliveryType;
import droidco.west3.ironsight.FrontierMobs.FrontierMob;
import droidco.west3.ironsight.FrontierMobs.FrontierMobType;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.FrontierLocation.LocationType;
import droidco.west3.ironsight.Bandit.UI.RespawnUI;
import droidco.west3.ironsight.Globals.Utils.BanditUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BanditTask extends BukkitRunnable {
    private ArrayList<BanditTask> tasks = new ArrayList<>();
    private final IronSight plugin;
    private final Bandit b;
    private int tick = 0;
    private int seconds = 0;
    private final int combatLogTimer = 30;
    //IN SECONDS
    private int mobRespawnTime = 60;
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
    private FrontierMob undeadMiner;
    private FrontierMob berserkerMiner;
    private FrontierMob corrodedMiner;
    private FrontierMob raider;
    private FrontierMob ranger;
    private FrontierMob wolf;
    private FrontierMob raiderBrute;
    private FrontierMob rabbit;
    private FrontierMob fox;
    private FrontierMob boar;
    private FrontierMob cow;
    private List<FrontierMob> miners = new ArrayList<>();
    private List<FrontierMob> animals = new ArrayList<>();
    private List<FrontierMob> raiders = new ArrayList<>();
    private HashMap<String, FrontierLocation> locations;

    public BanditTask(IronSight plugin, Bandit b, Player p) {

        this.plugin = plugin;
        this.b = b;
        this.p = p;
        this.wildernessFlag = false;
        tasks.add(this);
        this.runTaskTimer(plugin, 0, 10);

        b.setDoingContract(false);
        b.loadContracts();

        this.undeadMiner = new FrontierMob(FrontierMobType.UNDEAD_MINER);
        this.berserkerMiner = new FrontierMob(FrontierMobType.BERSERKER_MINER);
        this.corrodedMiner = new FrontierMob(FrontierMobType.CORRODED_MINER);
        this.miners.add(undeadMiner);
        this.miners.add(berserkerMiner);
        this.miners.add(corrodedMiner);

        this.rabbit = new FrontierMob(FrontierMobType.RABBIT);
        this.cow = new FrontierMob(FrontierMobType.WILD_COW);
        this.boar = new FrontierMob(FrontierMobType.BOAR);
        this.fox = new FrontierMob(FrontierMobType.RED_FOX);
        this.animals.add(rabbit);
        this.animals.add(boar);
        this.animals.add(fox);
        this.animals.add(cow);

        this.raider = new FrontierMob(FrontierMobType.RAIDER);
        this.raiderBrute = new FrontierMob(FrontierMobType.RAIDER_BRUTE);
        this.ranger = new FrontierMob(FrontierMobType.RANGER);
        this.wolf = new FrontierMob(FrontierMobType.WILD_DOG);
        this.raiders.add(raider);
        this.raiders.add(ranger);
        this.raiders.add(wolf);
        this.raiders.add(raiderBrute);

        Contract.assignPlayerContracts(p,b);
    }

    @Override
    public void run() {
        if(tick % 3 == 0){
            seconds++;
            p.spigot().sendMessage(
                    ChatMessageType.ACTION_BAR,
                    new TextComponent(ChatColor.GRAY+"" +seconds+ " seconds"));
            //      ===--- COMPASS TRACKER ---===
            if(p.getInventory().getItemInMainHand().getType().equals(Material.COMPASS)){
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
            }
            //      ===--- DISPLAYS LOCATION BOSSBAR ---===
            FrontierLocation.displayLocation(p);
            //      ===--- DISPLAYS SCOREBOARD / STATS ---===
            BanditUtils.loadScoreBoard(p, b, combatLogTimer - combatLogCounter, wantedMin, wantedSec);
            //      ===--- HANDLES PLAYER RESPAWN ---===

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
                } else {
                    //RESPAWNING IN A TOWN
                    p.setWalkSpeed(0);
                    p.setFlySpeed(0);
                    p.setSprinting(false);
                    if (p.getOpenInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Choose Town:") != true) {
                        p.openInventory(RespawnUI.openRespawnSelect(p));
                    }
                }
            }

            //      ===--- LOCATION SPECIFIC ---===
            FrontierLocation currentLoc = b.getCurrentLocation();
            //      ===--- PRISON ---===
            if (b.isJailed()) {
                if (!currentLoc.getType().equals(LocationType.PRISON)) {
                    //Player is escaping! PUT LOGIC HERE
                    b.setEscaping(true);
                    if (!escapeFlag) {
                        escapeFlag = true;
                        PrisonEscapeTask escapee = new PrisonEscapeTask(plugin, p);
                        p.sendTitle(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Escapee!", ChatColor.GRAY + "Return to jail or gain bounty", 1, 2, 1);
                    }
                } else {
                    //They are in prison!
                    escapeFlag = false;
                    b.setEscaping(false);
                }
            }

            //      ===--- TOWNS ---===
            if (currentLoc.getType().equals(LocationType.TOWN)) {
                p.setLastDamage(0.0);
                //NO WANTED PLAYERS IN TOWN!!!
                if (b.isWanted()) {
                    //DISPLAY HOW LONG THEY HAVE TO LEAVE BEFORE KILLING THEM
                    if (wantedTownCounter == 0) {
                        //TIMER JUST STARTED!
                        p.sendTitle(ChatColor.DARK_RED + "Wanted!", ChatColor.GRAY + "Leave town or die");
                    }
                    p.spigot().sendMessage(
                            ChatMessageType.ACTION_BAR,
                            new TextComponent(ChatColor.RED + "" + (wantedTownTimer - wantedTownCounter) + ChatColor.RESET + " seconds remaining."));
                    if (wantedTownCounter == wantedTownTimer) {
                        p.damage(100);
                    }
                    wantedTownCounter++;
                }
            }
            //      ===--- ILLEGAL / OIL FIELD ---===
            if (currentLoc.getType().equals(LocationType.ILLEGAL) || currentLoc.getType().equals(LocationType.OIL_FIELD)) {
                //Increase players bounty in illegal area
                b.updateBounty(2);
            }

            if(currentLoc.getType().equals(LocationType.PRISON)){
                if (b.isJailed()) {
                    //REMEMBER TO CHECK IF THEY'RE INSIDE A PRISON
                    b.updateBounty(-1);
                    if (b.getBounty() <= 0) {
                        BanditUtils.releasePrisoner(p, b);
                    }
                }
            }
            //      ===--- WANTED TIMER ---===
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
            //      ===--- COMBAT BLOCK TIMER ---===
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
            //      ===--- BLEED EFFECT ---===
            if (b.isBleeding()) {
                p.setHealth(p.getHealth() - 1.5);
                for (int i = 0; i < 13; i++) {
                    p.spawnParticle(Particle.BLOCK_DUST, p.getLocation().add(0.5, 0.5, 0.5), 1, 1, 1, 1, 1, new ItemStack(Material.RED_WOOL));
                }
            }
            //      ===--- MOB SPAWNING ---===
            if(seconds == mobRespawnTime){
                p.sendMessage("30 seconds passed.");
                switch(currentLoc.getType()){
                    case MINE -> {
                        spawnGroupOfMobs(this.miners);
                    }
                    case ILLEGAL,EVENT -> {
                        spawnGroupOfMobs(this.raiders);
                    }
                    case OIL_FIELD -> {
                        spawnGroupOfMobs(this.raiders);
                    }
                    case NATURAL -> {
                        if(currentLoc.getLocName().equalsIgnoreCase("Florence Plains")){
                            spawnSpecificMobs(fox);
                        }else if(currentLoc.getLocName().equalsIgnoreCase("Marston Glacier")){
                            spawnSpecificMobs(rabbit);
                        }
                        else if(currentLoc.getLocName().equalsIgnoreCase("Grizzly Ridge")){
                            spawnSpecificMobs(cow);
                        }
                        else if(currentLoc.getLocName().equalsIgnoreCase("Three Forks Marshland")){
                            spawnSpecificMobs(boar);
                        }
                    }
                }
                seconds = 0;
            }
        }


        //      ===--- MISC TIMED EVENTS ---===

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
    public void spawnGroupOfMobs(List<FrontierMob> mobList){
        int selection = GlobalUtils.getRandomNumber(mobList.size());
        int amount = GlobalUtils.getRandomNumber(4);
        for(int i=0; i < amount; i++){
            mobList.get(selection).spawnMob(p);
            selection = GlobalUtils.getRandomNumber(mobList.size());
        }
    }
    public void spawnSpecificMobs(FrontierMob mob){
        int amount = GlobalUtils.getRandomNumber(4);
        for(int i=0; i < amount; i++){
            mob.spawnMob(p);
        }
    }
}
