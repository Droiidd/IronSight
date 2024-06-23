package droidco.west3.ironsight.frontierlocation;

import droidco.west3.ironsight.items.looting.ItemTable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

@Getter
@Setter
public class FrontierLocation {
  private static final BossBar wildernessTitle =
      Bukkit.createBossBar("Wilderness", BarColor.GREEN, BarStyle.SOLID);
  private static HashMap<String, FrontierLocation> locations = new HashMap<>();
  @Getter private static List<FrontierLocation> locationList = new ArrayList<>();
  private final BossBar locTitle;
  private String locName;
  private double x1, x2, z1, z2;
  private double spawnX, spawnY, spawnZ;
  private String welcomeMessage;
  private LocationType type;
  private ItemTable itemTable;
  private boolean newArrival;
  private boolean mobsSpawned;
  private boolean procsSpawned;
  private List<Player> playersInside = new ArrayList<>();

  public FrontierLocation(
      String locName,
      String welcomeMessage,
      LocationType type,
      double x1,
      double x2,
      double z1,
      double z2) {
    this.locName = locName;
    this.x1 = x1;
    this.x2 = x2;
    this.z1 = z1;
    this.z2 = z2;
    this.welcomeMessage = welcomeMessage;
    this.type = type;
    this.newArrival = false;
    this.mobsSpawned = false;
    this.procsSpawned = false;

    this.locTitle = getTitleBossBar(locName, getTitleColor(type));

    locations.put(locName, this);
    locationList.add(this);
  }

  public FrontierLocation(
      String locName,
      String welcomeMessage,
      LocationType type,
      double x1,
      double x2,
      double z1,
      double z2,
      double spawnX,
      double spawnY,
      double spawnZ) {
    this.locName = locName;
    this.x1 = x1;
    this.x2 = x2;
    this.z1 = z1;
    this.z2 = z2;
    this.spawnX = spawnX;
    this.spawnY = spawnY;
    this.spawnZ = spawnZ;
    this.welcomeMessage = welcomeMessage;
    this.type = type;
    this.newArrival = false;
    this.mobsSpawned = false;
    this.procsSpawned = false;

    this.locTitle = getTitleBossBar(locName, getTitleColor(type));

    locations.put(locName, this);
    locationList.add(this);
  }

  public static HashMap<String, FrontierLocation> getLocations() {
    return locations;
  }

  public static FrontierLocation getLocation(String locationName) {
    if (locations.containsKey(locationName)) {
      return locations.get(locationName);
    }
    return null;
  }

  public static void setLocationList(List<FrontierLocation> locationList) {
    FrontierLocation.locationList = locationList;
  }

  public boolean isPlayerInside(Player p) {
    double minX;
    double maxX;
    double minZ;
    double maxZ;
    double playerX = p.getLocation().getX();
    double playerZ = p.getLocation().getZ();

    if (x1 < x2) {
      minX = x1;
      maxX = x2;
    } else {
      minX = x2;
      maxX = x1;
    }
    if (z1 < z2) {
      minZ = z1;
      maxZ = z2;
    } else {
      minZ = z2;
      maxZ = z1;
    }
    // PLAYER IS WITHIN THE ZONE
    if ((playerX > minX && playerX < maxX) && (playerZ > minZ && playerZ < maxZ)) {
      if (!playersInside.contains(p)) {
        playersInside.add(p);
      }
      return true;
    }
    if (playersInside.contains(p)) {
      playersInside.remove(p);
    }
    if (!p.isOnline()) {
      playersInside.remove(p);
    }
    return false;
  }

  public boolean isNewArrival() {
    return newArrival;
  }

  public void setNewArrival(boolean newArrival) {
    this.newArrival = newArrival;
  }

  public BossBar getTitleBossBar(String title, BarColor color) {
    return Bukkit.createBossBar(title, color, BarStyle.SOLID);
  }

  public org.bukkit.Location getSpawnLocation(Player p) {
    return new org.bukkit.Location(p.getWorld(), spawnX, spawnY, spawnZ);
  }

  public void addTitle(Player p) {
    this.locTitle.setProgress(1);
    this.locTitle.addPlayer(p);
    this.locTitle.setVisible(true);
  }

  //    public static void displayWilderness(Player p) {
  //        wildernessTitle.setProgress(1);
  //        wildernessTitle.addPlayer(p);
  //        wildernessTitle.setVisible(true);
  //    }
  //
  //    public static void removeWilderness(Player p) {
  //        wildernessTitle.removePlayer(p);
  //        wildernessTitle.setVisible(false);
  //    }

  public void removeTitle(Player p) {
    this.locTitle.removePlayer(p);
    this.locTitle.setVisible(false);
  }

  public BarColor getTitleColor(LocationType type) {
    switch (type) {
      case EVENT, MINE -> {
        return BarColor.YELLOW;
      }
      case TOWN -> {
        return BarColor.PINK;
      }
      case ILLEGAL, OIL_FIELD, PRISON -> {
        return BarColor.RED;
      }
      case NATURAL, WILDERNESS -> {
        return BarColor.GREEN;
      }
      case RIVER -> {
        return BarColor.BLUE;
      }
    }
    return BarColor.GREEN;
  }

  public org.bukkit.Location getCenterLocation(Player p) {
    double x3 = (this.x1 + this.x2) / 2;
    double z3 = (this.z1 + this.z2) / 2;
    return new org.bukkit.Location(p.getWorld(), x3, 100.0, z3);
  }
}
