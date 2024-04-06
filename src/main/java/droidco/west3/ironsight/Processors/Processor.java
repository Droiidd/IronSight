package droidco.west3.ironsight.Processors;

import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class Processor {

    private static final HashMap<String, Processor> utilsMap = new HashMap<>();
    private final Location location1;
    private final Location location2;
    private final Location location3;
    private final ArrayList<Processor> utilsList = new ArrayList<>();
    private boolean isProcessing;
    private Location defaultLocation;

    public Processor(String processorType, Location location1, Location location2, Location location3) {
        this.defaultLocation = location1;
        this.location1 = location1;
        this.location2 = location2;
        this.location3 = location3;
        utilsMap.put(processorType, this);
        utilsList.add(this);
    }

    public static Processor getProcessor(String procName) {
        return utilsMap.getOrDefault(procName, null);
    }

    public Location getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(Location location) {
        this.defaultLocation = location;
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