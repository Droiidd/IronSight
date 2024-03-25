package droidco.west3.ironsight.Contracts.OilField;

import droidco.west3.ironsight.Contracts.Utils.Difficulty;
import droidco.west3.ironsight.Location.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OilFieldCrate {
    private int crateX;
    private int crateZ;
    private int crateY;
    private Location location;
    private int crateNumber;
    private String crateKey;
    private static HashMap<String,OilFieldCrate> crates = new HashMap<>();
    public OilFieldCrate(int crateNumber, Location location, int crateX,int crateY,int crateZ){
        this.crateNumber = crateNumber;
        this.location = location;
        this.crateX = crateX;
        this.crateY = crateY;
        this.crateZ = crateZ;
        this.crateKey = location.getLocName()+crateNumber;
        crates.put(crateKey,this);
    }

    public static List<OilFieldCrate> getCratesByLocation(Location targetLoc){
        List<OilFieldCrate> crateList = new ArrayList<>();
        for (Map.Entry<String,OilFieldCrate> crate : crates.entrySet()) {
            String key = crate.getKey();
            OilFieldCrate val = crate.getValue();
            if(val.location.getLocName().equalsIgnoreCase(targetLoc.getLocName())){
                crateList.add(val);
            }
        }
        return crateList;
    }
    public int getCrateX() {
        return crateX;
    }

    public void setCrateX(int crateX) {
        this.crateX = crateX;
    }

    public int getCrateZ() {
        return crateZ;
    }

    public void setCrateZ(int crateZ) {
        this.crateZ = crateZ;
    }

    public int getCrateY() {
        return crateY;
    }

    public void setCrateY(int crateY) {
        this.crateY = crateY;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getCrateNumber() {
        return crateNumber;
    }

    public void setCrateNumber(int crateNumber) {
        this.crateNumber = crateNumber;
    }

    public String getCrateKey() {
        return crateKey;
    }
    public HashMap<String, OilFieldCrate> getCrates() {
        return crates;
    }
}
