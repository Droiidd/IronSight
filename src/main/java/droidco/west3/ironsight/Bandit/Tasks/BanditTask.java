package droidco.west3.ironsight.Bandit.Tasks;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.FrontierMobs.FrontierMob;
import droidco.west3.ironsight.FrontierMobs.FrontierMobType;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.FrontierLocation.LocationType;
import droidco.west3.ironsight.Bandit.UI.RespawnUI;
import droidco.west3.ironsight.Globals.Utils.BanditUtils;
import droidco.west3.ironsight.NPC.NPC;
import droidco.west3.ironsight.Processors.Processor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class    BanditTask extends BukkitRunnable {
    private ArrayList<BanditTask> tasks = new ArrayList<>();
    private final IronSight plugin;
    private final Bandit b;
    private int tick = 0;
    private int seconds = 0;
    private final int combatLogTimer = 30;
    //IN SECONDS
    private int mobRespawnTime = 60;
    private int combatLogCounter = 0;
    private int horseFullCallTime = 10;
    private int wantedMin = 2;
    private int wantedSec = 0;
    private final int contractTimer = 600;
    private int contractCounter = 0;
    private int horseTimer = 0;
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
    private int mobSec = 0;
    private List<FrontierMob> miners = new ArrayList<>();
    private List<FrontierMob> animals = new ArrayList<>();
    private List<FrontierMob> raiders = new ArrayList<>();

    List<FrontierLocation> locations;
    HashMap<UUID, LivingEntity> npcEnts;

    public BanditTask(IronSight plugin, Bandit b, Player p) {

        this.plugin = plugin;
        this.b = b;
        this.p = p;
        this.wildernessFlag = false;
        b.setEscaping(false);
        b.setJailRespawn(false);
        tasks.add(this);
        this.runTaskTimer(plugin, 0, 10);

        locations = FrontierLocation.getLocationList();

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
        //this.raiders.add(raiderBrute);
        Contract.assignPlayerContracts(p,b);
    }

    @Override
    public void run() {
        if(tick % 3 == 0){
            seconds++;
            mobSec++;
            //      ===--- COMPASS TRACKER ---===
            if(p.getInventory().getItemInMainHand().getType().equals(Material.COMPASS)){
                if (b.isTrackingLocation() && !b.isTrackingPlayer()) {
                    p.setCompassTarget(b.getTrackingLocation());
                    Double distance = b.getTrackingLocation().distance(p.getLocation());
                    int distanceMsg = distance.intValue();
                    String trackingLocName = null;
                    if (b.isTrackingNPC()) {
                        p.spigot().sendMessage(
                                ChatMessageType.ACTION_BAR,
                                new TextComponent(b.getTrackedNPC() +" "+ String.valueOf(distanceMsg) + ChatColor.GRAY + " blocks away!"));
                    }
                    else {
                        p.spigot().sendMessage(
                                ChatMessageType.ACTION_BAR,
                                new TextComponent(b.getTrackingFrontierLocation().getLocName() + " " + String.valueOf(distanceMsg) + ChatColor.GRAY + " blocks away!"));
                    }
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
            //      ===--- HELPFUL TIPS ---===
            if(seconds % 300 == 0){
                p.sendMessage(BanditUtils.getRandomTip());
            }
            //      ===--- HANDLES PLAYER RESPAWN ---===

            if (b.isRespawning()) {
                
                //RESPAWNING IN A TOWN
                p.setWalkSpeed(0);
                p.setFlySpeed(0);
                p.setSprinting(false);
                if (p.getOpenInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Choose Town:") != true) {
                    p.openInventory(RespawnUI.openRespawnSelect(p));
                }

            }

            //      ===--- DISPLAYS LOCATION BOSSBAR ---===
            updatePlayerLocation(p);
            despawnEmptyTownNPCs();
            despawnEmptyCampProcessors();
            //SPAWN NPCS
            if (!p.isOnline()) {
                if (b.isCombatBlocked()) {
                    p.damage(100);
                }
                this.cancel();
                tasks.remove(this);
            }

            //      ===--- DISPLAYS SCOREBOARD / STATS ---===
            BanditUtils.loadScoreBoard(p, b, combatLogTimer - combatLogCounter, wantedMin, wantedSec);


            //      ===--- LOCATION SPECIFIC ---===
            FrontierLocation currentLoc = b.getCurrentLocation();
            b.getCurrentLocation().addTitle(p);
            for(FrontierLocation location : locations){
                if(!location.getLocName().equalsIgnoreCase(b.getCurrentLocation().getLocName())){
                    location.removeTitle(p);
                    p.resetTitle();
                }
            }

            //      ===--- PRISON ---===
            if (b.isJailed()) {
                if (!currentLoc.getType().equals(LocationType.PRISON)) {
                    //Player is escaping! PUT LOGIC HERE
                    if (!b.isEscaping()) {
                        b.setEscaping(true);
                        PrisonEscapeTask escapee = new PrisonEscapeTask(plugin, p);
                        p.sendTitle(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Escapee!", ChatColor.GRAY + "Return to jail or gain bounty");
                    }
                } else {
                    //They are in prison!
                    b.setEscaping(false);
                }
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

            //      ===--- TOWNS ---===
            if (currentLoc.getType().equals(LocationType.TOWN)) {
                spawnNPCs(p,b);
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
                spawnProcessors(p,b);
            }
            if(currentLoc.getLocName().equalsIgnoreCase("Storm Point")){
                if (!currentLoc.isNewArrival()) {
                    currentLoc.setNewArrival(true);
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
                p.damage(1.5);
                for (int i = 0; i < 13; i++) {
                    p.spawnParticle(Particle.BLOCK_DUST, p.getLocation().add(0.5, 0.5, 0.5), 1, 1, 1, 1, 1, new ItemStack(Material.RED_WOOL));
                }
            }
            //      ===--- SUMMONING HORSE ---===
            if(b.isSummoningHorse()){
                horseTimer ++;
                p.sendMessage(ChatColor.GRAY+"Horse arrives in "+(horseFullCallTime-horseTimer)+" seconds.");
                if(horseTimer == horseFullCallTime){
                    b.setSummoningHorse(false);
                    horseTimer = 0;
                    b.getHorseBeingSummoned().summonHorse(p);
                    b.setHorseBeingSummoned(null);
                    p.getLocation().getWorld().playSound(p.getLocation(), Sound.BLOCK_AZALEA_LEAVES_PLACE, 1, 0);
                    p.getLocation().getWorld().playSound(p.getLocation(), Sound.BLOCK_CAVE_VINES_PLACE, 1, 0);
                    p.getLocation().getWorld().playSound(p.getLocation(), Sound.BLOCK_BAMBOO_HIT, 1, 0);
                    p.sendMessage(ChatColor.AQUA +b.getHorseBeingSummoned().getHorseName() + ChatColor.GRAY + " has arrived!");
                    p.sendMessage(ChatColor.GRAY+"Shift + right-click to open it's inventory.");
                }
            }
            //      ===--- CONRTACT RESET TIMER ---===

            p.setLevel(contractTimer - contractCounter);
            if (contractTimer == contractCounter) {
                Contract.assignPlayerContracts(p,b);
                p.sendMessage(ChatColor.GREEN + "Contracts" + ChatColor.GRAY + " reset!");
                contractCounter = 0;
            }
            contractCounter++;
            //      ===--- MOB SPAWNING ---===
            if(mobSec == mobRespawnTime){
                //p.sendMessage("30 seconds passed.");
                switch(currentLoc.getType()){
                    case MINE -> {
                        //spawnGroupOfMobs(this.miners);
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
                mobSec = 0;
            }
        }



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
        int amount = GlobalUtils.getRandomRange(2,6);
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
    public void updatePlayerLocation(Player p) {
        Bandit b = Bandit.getPlayer(p);
        boolean wildMarker = true;
        for(FrontierLocation location : locations){
            if (location.isPlayerInside(p)) {
                //location.addTitle(p);
                b.setCurrentLocation(location);
                wildMarker = false;
            }
        }
        if(wildMarker){
            b.setCurrentLocation(FrontierLocation.getLocation("Wilderness"));
        }
    }
    public void despawnEmptyTownNPCs(){
        npcEnts = NPC.getEntities();
        for(FrontierLocation location : locations){
            if(location.getPlayersInside().isEmpty()){
                if(location.isMobsSpawned()){
                    HashMap<UUID, NPC> npcs = NPC.getNpcsById();
                    for(Map.Entry<UUID,LivingEntity> npcEnt : npcEnts.entrySet()){
                        if(location.getLocName().equalsIgnoreCase(npcs.get(npcEnt.getKey()).getFrontierLocation().getLocName())){
                            npcEnt.getValue().remove();
                            System.out.println(npcEnt.getValue().getCustomName()+ " NPC killed.");
                        }
                    }
                    location.setMobsSpawned(false);
                }
            }
        }
    }
    public void despawnEmptyCampProcessors(){
        HashMap<UUID, LivingEntity> procEnts = Processor.getEntities();
        for(FrontierLocation location : locations){
            if(location.getPlayersInside().isEmpty()){
                if(location.isMobsSpawned()){
                    HashMap<String, Processor> procs = Processor.getProcessors();
                    for(Map.Entry<UUID,LivingEntity> procEnt : procEnts.entrySet()){
                        p.sendMessage("attempting to kill Processor");
                        if(location.getLocName().equalsIgnoreCase(procs.get(ChatColor.stripColor(procEnt.getValue().getCustomName())).getLocation().getLocName())){
                            procEnt.getValue().remove();
                            System.out.println(procEnt.getValue().getCustomName()+ " NPC killed.");
                        }
                    }
                    location.setMobsSpawned(false);
                }
            }
        }
    }
    public void spawnNPCs(Player p, Bandit b){
        if (!b.getCurrentLocation().isMobsSpawned()) {
            b.getCurrentLocation().setMobsSpawned(true);
            HashMap<String, NPC> npcs = NPC.getNPCs();
            for (Map.Entry<String, NPC> entryNPC : npcs.entrySet()) {
                NPC npc = entryNPC.getValue();
                if(npc.getFrontierLocation().equals(b.getCurrentLocation())){
                    npc.spawnNPC(p);
                }
            }
        }
    }
    public void spawnProcessors(Player p, Bandit b){
        if (!b.getCurrentLocation().isMobsSpawned()) {
            p.sendMessage("Mobs not spawned");
            b.getCurrentLocation().setMobsSpawned(true);
            HashMap<String, Processor> procs = Processor.getProcessors();
            for (Map.Entry<String, Processor> proc : procs.entrySet()) {
                if(proc.getValue().getLocation().equals(b.getCurrentLocation())){
                    proc.getValue().randomizeLocAndSpawn(p);
                }
            }
        }
    }
}
