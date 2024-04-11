package droidco.west3.ironsight.Processors;

import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

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
    private FrontierLocation location;
    private ProcessorType type;
    private String processorCode;
    public Processor(String processorCode, ProcessorType type, FrontierLocation location) {
        this.processorCode = processorCode;
        this.location = location;
        this.type = type;
        processorsById.put(processorCode, this);
        utilsList.add(this);
    }


    public static Processor getProcessor(String procName) {
        return processorsById.getOrDefault(procName, null);
    }

    public Location getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(Location location) {
        this.defaultLocation = location;
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
    public void initializeLocations(Player p){

    }
    public static HashMap<String, Processor> getProcessors(){
        return processorsById;
    }


    public void randomizeDefaultLocation(Location previousLocation) {
        if (previousLocation.equals(location1)) {
            int chance = GlobalUtils.getRandomNumber(101);
            if (chance % 2 == 0) {
                defaultLocation = location2;
            } else {
                defaultLocation = location3;
            }
        }
        if (previousLocation.equals(location2)) {
            int chance = GlobalUtils.getRandomNumber(101);
            if (chance % 2 == 0) {
                defaultLocation = location1;
            } else {
                defaultLocation = location3;
            }
        }
        if (previousLocation.equals(location3)) {
            int chance = GlobalUtils.getRandomNumber(101);
            if (chance % 2 == 0) {
                defaultLocation = location2;
            } else {
                defaultLocation = location1;
            }
        }
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

    public Location getLocation1() {
        return location1;
    }

    public Location getLocation2() {
        return location2;
    }

    public Location getLocation3() {
        return location3;
    }

}