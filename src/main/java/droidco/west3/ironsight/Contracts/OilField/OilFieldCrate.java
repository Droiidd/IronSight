package droidco.west3.ironsight.Contracts.OilField;

import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OilFieldCrate {
    private int crateX;
    private int crateZ;
    private int crateY;
    private FrontierLocation frontierLocation;
    private int crateNumber;
    private String crateKey;
    private boolean isUnlocked;
    private static HashMap<String,OilFieldCrate> crates = new HashMap<>();
    public OilFieldCrate(int crateNumber, FrontierLocation frontierLocation, int crateX, int crateY, int crateZ){
        this.crateNumber = crateNumber;
        this.frontierLocation = frontierLocation;
        this.crateX = crateX;
        this.crateY = crateY;
        this.crateZ = crateZ;
        this.crateKey = frontierLocation.getLocName()+crateNumber;
        crates.put(crateKey,this);
        this.isUnlocked = false;
    }

    public static List<OilFieldCrate> getCratesByLocation(FrontierLocation targetLoc){
        List<OilFieldCrate> crateList = new ArrayList<>();
        for (Map.Entry<String,OilFieldCrate> crate : crates.entrySet()) {
            String key = crate.getKey();
            OilFieldCrate val = crate.getValue();
            if(val.frontierLocation.getLocName().equalsIgnoreCase(targetLoc.getLocName())){
                crateList.add(val);
            }
        }
        return crateList;
    }
    public static OilFieldCrate getRandomCrate(FrontierLocation targetLoc){
        List<OilFieldCrate> crates = getCratesByLocation(targetLoc);
        int ran = GlobalUtils.getRandomNumber(crates.size());
        return crates.get(ran);
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
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

    public FrontierLocation getLocation() {
        return frontierLocation;
    }

    public void setLocation(FrontierLocation frontierLocation) {
        this.frontierLocation = frontierLocation;
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
