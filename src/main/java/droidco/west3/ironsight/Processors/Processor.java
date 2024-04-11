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
    private boolean isProcessing;
    private Location defaultLocation;
    private Location previousLocation;
    private ProcessorCoordinate defaultPosition;
    private FrontierLocation location;
    private ProcessorType type;
    private String processorCode;
    private ItemStack unprocDrug;
    private ItemStack procDrug;
    public Processor(String processorCode, ProcessorType type, FrontierLocation location,ItemStack unprocDrug,ItemStack procDrug) {
        this.processorCode = processorCode;
        this.location = location;
        this.type = type;
        this.previousLocation = null;
        this.unprocDrug = unprocDrug;
        this.procDrug = procDrug;
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
    }

    public static HashMap<String, Processor> getProcessors(){
        return processorsById;
    }


    public void randomizeLocAndSpawn(Player p) {
        boolean positionTaken = true;
        while(positionTaken){
            int coordChoice = GlobalUtils.getRandomNumber(coordList.size());
            ProcessorCoordinate targetCoord = coordList.get(coordChoice);
            if(!targetCoord.equals(this.defaultPosition)){
                for(var proc : processorsById.entrySet()){
                    if(!proc.getValue().getDefaultPosition().equals(targetCoord)){
                        Location newSpawn = new Location(p.getPlayer().getWorld(), targetCoord.getX(),targetCoord.getY(),targetCoord.getZ());
                        this.defaultLocation = newSpawn;
                        positionTaken = false;
                        createVillager(String.valueOf(ChatColor.RED)+this.processorCode,this.defaultLocation);
                    }
                }
            }
        }
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

    public void addCoordinate(int x,int y,int z){
        coordList.add(new ProcessorCoordinate(x,y,z));
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
}