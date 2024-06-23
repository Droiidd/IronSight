package droidco.west3.ironsight.frontierlocation;

import droidco.west3.ironsight.items.looting.ItemTable;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FrontierLocation {
    private String locName;
    private double x1, x2, z1, z2;
    private double spawnX, spawnY, spawnZ;
    private String welcomeMessage;
    private final BossBar locTitle;
    private static final BossBar wildernessTitle = Bukkit.createBossBar("Wilderness", BarColor.GREEN, BarStyle.SOLID);
    private LocationType type;
    private ItemTable itemTable;
    private static HashMap<String, FrontierLocation> locations = new HashMap<>();
private static List<FrontierLocation> locationList = new ArrayList<>();
    private boolean newArrival;
    private boolean mobsSpawned;
    private boolean procsSpawned;
    private List<Player> playersInside = new ArrayList<>();

    public FrontierLocation(String locName, String welcomeMessage, LocationType type, double x1, double x2, double z1, double z2) {
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
    public FrontierLocation(String locName, String welcomeMessage, LocationType type, double x1, double x2, double z1, double z2, double spawnX, double spawnY, double spawnZ) {
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
    public boolean isPlayerInside(Player p)
    {
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
        //PLAYER IS WITHIN THE ZONE
        if ((playerX > minX && playerX < maxX) && (playerZ > minZ && playerZ < maxZ)) {
            if(!playersInside.contains(p)){
                playersInside.add(p);
            }
            return true;
        }
        if(playersInside.contains(p)){
            playersInside.remove(p);
        }
        if(!p.isOnline()){
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
        return Bukkit.createBossBar(title,
                color,
                BarStyle.SOLID);
    }

    public org.bukkit.Location getSpawnLocation(Player p) {
        return new org.bukkit.Location(p.getWorld(), spawnX, spawnY, spawnZ);
    }

    public void addTitle(Player p) {
        this.locTitle.setProgress(1);
        this.locTitle.addPlayer(p);
        this.locTitle.setVisible(true);
    }

    public void removeTitle(Player p) {
        this.locTitle.removePlayer(p);
        this.locTitle.setVisible(false);
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

    public BarColor getTitleColor(LocationType type) {
        switch (type) {
            case EVENT, MINE -> {
                return BarColor.YELLOW;
            }
            case TOWN -> {
                return BarColor.PINK;
            }
            case ILLEGAL, OIL_FIELD,PRISON -> {
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

    // >>>=== GETTERS & SETTERS ===<<<

    public static List<FrontierLocation> getLocationList() {
        return locationList;
    }

    public static void setLocationList(List<FrontierLocation> locationList) {
        FrontierLocation.locationList = locationList;
    }

    public boolean isProcsSpawned() {
        return procsSpawned;
    }

    public void setProcsSpawned(boolean procsSpawned) {
        this.procsSpawned = procsSpawned;
    }

    public double getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(double spawnX) {
        this.spawnX = spawnX;
    }

    public double getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(double spawnY) {
        this.spawnY = spawnY;
    }

    public double getSpawnZ() {
        return spawnZ;
    }

    public void setSpawnZ(double spawnZ) {
        this.spawnZ = spawnZ;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getZ1() {
        return z1;
    }

    public void setZ1(double z1) {
        this.z1 = z1;
    }

    public double getZ2() {
        return z2;
    }

    public void setZ2(double z2) {
        this.z2 = z2;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public BossBar getLocTitle() {
        return locTitle;
    }

    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public org.bukkit.Location getCenterLocation(Player p) {
        double x3 = (this.x1 + this.x2) / 2;
        double z3 = (this.z1 + this.z2) / 2;
        return new org.bukkit.Location(p.getWorld(), x3, 100.0, z3);
    }
    public void setItemTable(ItemTable table) {
        this.itemTable = table;
    }
    public ItemTable getItemTable(){
        return this.itemTable;
    }

    public List<Player> getPlayersInside() {
        return playersInside;
    }

    public void setPlayersInside(List<Player> playersInside) {
        this.playersInside = playersInside;
    }

    public boolean isMobsSpawned() {
        return mobsSpawned;
    }

    public void setMobsSpawned(boolean mobsSpawned) {
        this.mobsSpawned = mobsSpawned;
    }
}


