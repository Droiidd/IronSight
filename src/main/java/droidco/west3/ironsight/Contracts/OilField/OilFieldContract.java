package droidco.west3.ironsight.Contracts.OilField;

import droidco.west3.ironsight.Contracts.Utils.Difficulty;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.Location.Location;

import java.util.List;

public class OilFieldContract
{
    private List<OilFieldCrate> crates;
    private Location location;
    private String iconDescription;
    private int reinforcementCount;
    private int rewardXp;
    private Difficulty difficulty;
    private List<Location> locations;
    private int chanceForMaster;
    public OilFieldContract(List<Location> locations, int reinforcementCount, int rewardXp, int chanceForMaster){
        this.locations = locations;
        this.chanceForMaster = chanceForMaster;
        this.reinforcementCount = reinforcementCount;
        this.crates = OilFieldCrate.getCratesByLocation(location);
    }
    public void setDifficulty(){
        int random = GlobalUtils.getRandomNumber(101);
        if(random<chanceForMaster){
            this.difficulty = Difficulty.Master;
        }else{
            this.difficulty = Difficulty.Experienced;
        }
    }

    public int getReinforcementMultiplier(){
        double multiplier = 1.0;
        if(difficulty == Difficulty.Master){
            multiplier = 1.5;
        }
        int val = (int) Math.round(reinforcementCount*multiplier);
        return val;
    }
    public int getRewardXp(){
        double multiplier = 1.0;
        if(difficulty == Difficulty.Master){
            multiplier = 1.33;
        }
        int val = (int) Math.round(rewardXp*multiplier);
        return val;
    }
    public



}
