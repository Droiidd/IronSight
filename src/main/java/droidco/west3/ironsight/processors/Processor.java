package droidco.west3.ironsight.processors;

import droidco.west3.ironsight.frontierlocation.FrontierLocation;
import droidco.west3.ironsight.globals.utils.GlobalUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
public class Processor {

  private static HashMap<String, Processor> processorsById = new HashMap<>();
  private static HashMap<UUID, LivingEntity> entities = new HashMap<>();
  private static List<ProcessorCoordinate> coordList = new ArrayList<>();
  private static HashMap<String, List<ProcessorCoordinate>> coordMap = new HashMap<>();
  private final ArrayList<Processor> utilsList = new ArrayList<>();
  private UUID npcId;
  private boolean processing;
  private Location defaultLocation;
  private Location previousLocation;
  private ProcessorCoordinate defaultPosition;
  private FrontierLocation location;
  private ProcessorType type;
  private String processorCode;
  private ItemStack unprocDrug;
  private ItemStack procDrug;
  private String displayName;

  public Processor(
      String processorCode,
      ProcessorType type,
      FrontierLocation location,
      ItemStack unprocDrug,
      ItemStack procDrug) {
    this.processorCode = processorCode;
    this.location = location;
    this.type = type;
    this.defaultPosition = null;
    this.defaultLocation = null;
    this.unprocDrug = unprocDrug;
    this.procDrug = procDrug;
    this.displayName = String.valueOf(ChatColor.RED) + processorCode;
    processorsById.put(processorCode, this);
    utilsList.add(this);
  }

  public static HashMap<String, Processor> getProcessors() {
    return processorsById;
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
    coordMap.put(type.toString(), coords);
  }

  public static Processor getProcessor(String procName) {
    return processorsById.getOrDefault(ChatColor.stripColor(procName), null);
  }

  public static List<ProcessorCoordinate> getCoordList(ProcessorType type) {
    return coordMap.get(type.toString());
  }

  public static void setCoordList(List<ProcessorCoordinate> coordList) {
    Processor.coordList = coordList;
  }

  public static HashMap<UUID, LivingEntity> getEntities() {
    return entities;
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
    entities.put(npcId, vil);
  }

  public void randomizeProcLocation(Player p) {
    List<ProcessorCoordinate> tmpList = getCoordList(this.type);
    int choice = GlobalUtils.getRandomNumber(tmpList.size());
    ProcessorCoordinate coord = coord = tmpList.get(choice);

    if (this.defaultPosition == null) {
      // PROCESSOR IS BEING INITIALIZED, REMOVE NEW LOCATION FROM POOL AFTER SELECTING
      this.defaultLocation =
          new Location(p.getPlayer().getWorld(), coord.getX(), coord.getY(), coord.getZ());
      this.defaultPosition = coord;
      createVillager(String.valueOf(ChatColor.RED) + this.processorCode, this.defaultLocation);
      // removing from pool
      tmpList.remove(choice);
      Processor.updateCoordMap(this.type, tmpList);
    } else {
      // PROCESSOR ALREADY HAS A LOCATION, RANDOMIZE, THEN RETURN IT TO THE POOL
      // Saving to return to pool later
      ProcessorCoordinate tmpCoord = this.defaultPosition;

      this.defaultLocation =
          new Location(p.getPlayer().getWorld(), coord.getX(), coord.getY(), coord.getZ());
      this.defaultPosition = coord;
      createVillager(String.valueOf(ChatColor.RED) + this.processorCode, this.defaultLocation);
      // removing from pool
      tmpList.remove(choice);
      // returning previous location to pool
      tmpList.add(tmpCoord);
      Processor.updateCoordMap(this.type, tmpList);
    }
  }
}
