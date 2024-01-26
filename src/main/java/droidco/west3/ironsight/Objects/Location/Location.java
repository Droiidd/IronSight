package droidco.west3.ironsight.Objects.Location;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Location {
    private String locName;
    private double x1,x2,z1,z2;
    private String welcomeMessage;
    //private boolean isTown;
    //private boolean isLegal;
    private final BossBar locTitle;
    private LocationType type;
    private static HashMap<String,Location> locations = new HashMap<>();

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

    public Location(String locName, double x1, double x2, double z1, double z2, String welcomeMessage, LocationType type){
        this.locName = locName;
        this.x1= x1;
        this.x2 = x2;
        this.z1 = z1;
        this.z2 = z2;
        this.welcomeMessage = welcomeMessage;
        this.type = type;

        this.locTitle = getTitleBossBar(locName,getTitleColor(type));

        locations.put(locName,this);
    }
    public static HashMap<String,Location> getLocations()
    {
        return locations;
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
        return (playerX > minX && playerX < maxX) && (playerZ > minZ && playerZ < maxZ);
    }
    public BossBar getTitleBossBar(String title, BarColor color)
    {
        return Bukkit.createBossBar(title,
                color,
                BarStyle.SOLID);
    }
    public void displayTitle(Player p){
        this.locTitle.setProgress(1);
        this.locTitle.addPlayer(p);
        this.locTitle.setVisible(true);
    }
    public BarColor getTitleColor(LocationType type){
        if(type.compareTo(LocationType.EVENT) == 0){
            return BarColor.YELLOW;
        }else if(type.compareTo(LocationType.TOWN) == 0){
            return BarColor.BLUE;
        }
        else if(type.compareTo(LocationType.ILLEGAL) ==0){
            return BarColor.RED;
        }else{
            return BarColor.BLUE;
        }
    }
}
