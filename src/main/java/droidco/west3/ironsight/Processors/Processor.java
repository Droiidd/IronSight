package droidco.west3.ironsight.Processors;

import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Processor {

    private static HashMap<String, Processor> processorsById = new HashMap<>();
    private static HashMap<UUID, LivingEntity> entities = new HashMap<>();
    private final ArrayList<Processor> utilsList = new ArrayList<>();
    private static List<ProcessorCoordinate> coordList = new ArrayList<>();
    private static HashMap<String, List<ProcessorCoordinate>> coordMap = new HashMap<>();
    private UUID npcId;
    private boolean isProcessing;
    private Location defaultLocation;
    private Location previousLocation;
    private ProcessorCoordinate defaultPosition;
    private FrontierLocation location;
    private ProcessorType type;
    private String processorCode;
    private ItemStack unprocDrug;
    private ItemStack procDrug;
    private String displayName;
    public Processor(String processorCode, ProcessorType type, FrontierLocation location,ItemStack unprocDrug,ItemStack procDrug) {
        this.processorCode = processorCode;
        this.location = location;
        this.type = type;
        this.defaultPosition = null;
        this.defaultLocation = null;
        this.unprocDrug = unprocDrug;
        this.procDrug = procDrug;
        this.displayName = String.valueOf(ChatColor.RED)+processorCode;
        processorsById.put(processorCode, this);
        utilsList.add(this);
    }
    public void createVillager(String villagerName, Location loc) {
        Villager vil = loc.getWorld().spawn(loc, Villager.class);
        vil.setCustomName(villagerName);
        vil.setCustomNameVisible(true);
        vil.setAI(false);
        vil.setInvulnerable(true);
        loc.getWorld().playSound(loc, Sound.BLOCK_BEACON_POWER_SELECT, 1, 0);
        GlobalUtils.displayParticles(loc, Particle.CLOUD, Particle.GLOW, 15);
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String command = "minecraft:kill @e[type=minecraft:armor_stand]";
        Bukkit.dispatchCommand(console, command);
        this.npcId = vil.getUniqueId();
        entities.put(npcId,vil);
    }

    public static HashMap<String, Processor> getProcessors(){
        return processorsById;
    }

    public void randomizeProcLocation(Player p) {
        List<ProcessorCoordinate> tmpList = getCoordList(this.type);
        int choice = GlobalUtils.getRandomNumber(tmpList.size());
        ProcessorCoordinate coord = coord = tmpList.get(choice);

        if(this.defaultPosition == null){
            // PROCESSOR IS BEING INITIALIZED, REMOVE NEW LOCATION FROM POOL AFTER SELECTING
            this.defaultLocation = new Location(p.getPlayer().getWorld(), coord.getX(),coord.getY(),coord.getZ());
            this.defaultPosition = coord;
            createVillager(String.valueOf(ChatColor.RED)+this.processorCode,this.defaultLocation);
            // removing from pool
            tmpList.remove(choice);
            Processor.updateCoordMap( this.type,tmpList);
        }else{
            // PROCESSOR ALREADY HAS A LOCATION, RANDOMIZE, THEN RETURN IT TO THE POOL
            // Saving to return to pool later
            ProcessorCoordinate tmpCoord = this.defaultPosition;

            this.defaultLocation = new Location(p.getPlayer().getWorld(), coord.getX(),coord.getY(),coord.getZ());
            this.defaultPosition = coord;
            p.sendMessage("Spawning NPC@!Q");
            p.sendMessage(coord.getX()+" "+coord.getY()+" "+coord.getZ()+". ");
            createVillager(String.valueOf(ChatColor.RED)+this.processorCode,this.defaultLocation);
            // removing from pool
            tmpList.remove(choice);
            // returning previous location to pool
            tmpList.add(tmpCoord);
            Processor.updateCoordMap( this.type,tmpList);
        }
    }

    public static HashMap<String, List<ProcessorCoordinate>> getCoordMap() {
        return coordMap;
    }

    public static void setCoordMap(HashMap<String, List<ProcessorCoordinate>> coordMap) {
        Processor.coordMap = coordMap;
    }
    public static void updateCoordMap(ProcessorType type, List<ProcessorCoordinate> coords) {
        coordMap.replace(type.toString(), coords);
    }

    public static void addProcCoords(ProcessorType type, List<ProcessorCoordinate> coords) {
        coordMap.put(type.toString(),coords);
    }
    public static Processor getProcessor(String procName) {
        return processorsById.getOrDefault(ChatColor.stripColor(procName), null);
    }

    public Location getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(Location location) {
        this.defaultLocation = location;
    }



    public static List<ProcessorCoordinate> getCoordList(ProcessorType type) {
        return coordMap.get(type.toString());
    }

    public static void setCoordList(List<ProcessorCoordinate> coordList) {
        Processor.coordList = coordList;
    }

    public boolean isProcessing() {
        return isProcessing;
    }

    public void setProcessing(boolean bool) {
        this.isProcessing = bool;
    }

    public ProcessorCoordinate getDefaultPosition() {
        return defaultPosition;
    }

    public void setDefaultPosition(ProcessorCoordinate defaultPosition) {
        this.defaultPosition = defaultPosition;
    }

    public FrontierLocation getLocation() {
        return location;
    }

    public void setLocation(FrontierLocation location) {
        this.location = location;
    }

    public ProcessorType getType() {
        return type;
    }

    public void setType(ProcessorType type) {
        this.type = type;
    }

    public String getProcessorCode() {
        return processorCode;
    }

    public void setProcessorCode(String processorCode) {
        this.processorCode = processorCode;
    }

    public ItemStack getUnprocDrug() {
        return unprocDrug;
    }

    public void setUnprocDrug(ItemStack unprocDrug) {
        this.unprocDrug = unprocDrug;
    }

    public ItemStack getProcDrug() {
        return procDrug;
    }

    public void setProcDrug(ItemStack procDrug) {
        this.procDrug = procDrug;
    }
    public static HashMap<UUID, LivingEntity>  getEntities(){
        return entities;
    }

    public UUID getNpcId() {
        return npcId;
    }

    public void setNpcId(UUID npcId) {
        this.npcId = npcId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}