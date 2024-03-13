package droidco.west3.ironsight.Location;

import droidco.west3.ironsight.Bandit.Bandit;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private String locName;
    private double x1,x2,z1,z2;
    private double spawnX,spawnY,spawnZ;
    private String welcomeMessage;
    private final BossBar locTitle;
    private static final BossBar wildernessTitle =Bukkit.createBossBar("Wilderness", BarColor.GREEN, BarStyle.SOLID);
    private LocationType type;
    private static HashMap<String,Location> locations = new HashMap<>();

    public Location(String locName, String welcomeMessage, LocationType type, double x1, double x2, double z1, double z2){
        this.locName = locName;
        this.x1= x1;
        this.x2 = x2;
        this.z1 = z1;
        this.z2 = z2;
        this.welcomeMessage = welcomeMessage;
        this.type = type;

        this.locTitle = getTitleBossBar(locName,getTitleColor(type));

        locations.put(locName,this);
    }public Location(String locName,String welcomeMessage, LocationType type, double x1, double x2, double z1, double z2,double spawnX, double spawnY, double spawnZ){
        this.locName = locName;
        this.x1= x1;
        this.x2 = x2;
        this.z1 = z1;
        this.z2 = z2;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.spawnZ = spawnZ;
        this.welcomeMessage = welcomeMessage;
        this.type = type;

        this.locTitle = getTitleBossBar(locName,getTitleColor(type));

        locations.put(locName,this);
    }

    public static HashMap<String,Location> getLocations()
    {
        return locations;
    }
    public static boolean isPlayerInWilderness(Player p)
    {
        for (Map.Entry<String,Location> mapE : locations.entrySet()) {
            if(mapE.getValue().isPlayerInside(p)){
                return false;
            }
        }
        return true;
    }
    public static Location getLocation(String locationName){
        if(locations.containsKey(locationName)){
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
        if((playerX > minX && playerX < maxX) && (playerZ > minZ && playerZ < maxZ)){
            return true;
        }
        return false;
    }


    public static void displayLocation(Player p)
    {
        Bandit b = Bandit.getPlayer(p);
        locations.forEach((s, location) -> {
            if(location.isPlayerInside(p)){
                location.addTitle(p);
                b.setCurrentLocation(location);
            }else{
                location.removeTitle(p);
            }

        });
        //p.sendMessage(""+locations.get("test").isPlayerInside(p));
        if(Location.isPlayerInWilderness(p)){
            //Display wilderness
            Location.displayWilderness(p);
            b.setCurrentLocation(getLocation("Wilderness"));
        }else{
            //Else check the towns.
            Location.removeWilderness(p);
        }
    }
    public BossBar getTitleBossBar(String title, BarColor color)
    {
        return Bukkit.createBossBar(title,
                color,
                BarStyle.SOLID);
    }
    public org.bukkit.Location getSpawnLocation(Player p){
        return new org.bukkit.Location(p.getWorld(),spawnX,spawnY,spawnZ);
    }
    public void addTitle(Player p){
        this.locTitle.setProgress(1);
        this.locTitle.addPlayer(p);
        this.locTitle.setVisible(true);
    }
    public void removeTitle(Player p){
        this.locTitle.removePlayer(p);
        this.locTitle.setVisible(false);
    }
    public static void displayWilderness(Player p){
        wildernessTitle.setProgress(1);
        wildernessTitle.addPlayer(p);
        wildernessTitle.setVisible(true);
    }
    public static void removeWilderness(Player p){
        wildernessTitle.removePlayer(p);
        wildernessTitle.setVisible(false);
    }
    public BarColor getTitleColor(LocationType type){
        if(type.compareTo(LocationType.EVENT) == 0){
            return BarColor.YELLOW;
        }else if(type.compareTo(LocationType.TOWN) == 0){
            return BarColor.PINK;
        }
        else if(type.compareTo(LocationType.ILLEGAL) ==0){
            return BarColor.RED;
        }else if(type.compareTo(LocationType.NATURAL) == 0){
            return BarColor.PURPLE;
        }else if(type.compareTo(LocationType.River) == 0){
            return BarColor.BLUE;
        }
        else{
            return BarColor.GREEN;
        }
    }

    // >>>=== GETTERS & SETTERS ===<<<

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
}
