package droidco.west3.ironsight.contracts.oilfield;

import droidco.west3.ironsight.frontierlocation.FrontierLocation;
import droidco.west3.ironsight.globals.utils.GlobalUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OilFieldCrate {
  private static HashMap<String, OilFieldCrate> crates = new HashMap<>();
  private int crateX;
  private int crateZ;
  private int crateY;
  private FrontierLocation location;
  private int crateNumber;
  private String crateKey;
  private boolean unlocked;

  public OilFieldCrate(
      int crateNumber, FrontierLocation location, int crateX, int crateY, int crateZ) {
    this.crateNumber = crateNumber;
    this.location = location;
    this.crateX = crateX;
    this.crateY = crateY;
    this.crateZ = crateZ;
    this.crateKey = location.getLocName() + crateNumber;
    crates.put(crateKey, this);
    this.unlocked = false;
  }

  public static List<OilFieldCrate> getCratesByLocation(FrontierLocation targetLoc) {
    List<OilFieldCrate> crateList = new ArrayList<>();
    for (Map.Entry<String, OilFieldCrate> crate : crates.entrySet()) {
      String key = crate.getKey();
      OilFieldCrate val = crate.getValue();
      if (val.location.getLocName().equalsIgnoreCase(targetLoc.getLocName())) {
        crateList.add(val);
      }
    }
    return crateList;
  }

  public static OilFieldCrate getRandomCrate(FrontierLocation targetLoc) {
    List<OilFieldCrate> crates = getCratesByLocation(targetLoc);
    int ran = GlobalUtils.getRandomNumber(crates.size());
    return crates.get(ran);
  }

  public HashMap<String, OilFieldCrate> getCrates() {
    return crates;
  }
}
