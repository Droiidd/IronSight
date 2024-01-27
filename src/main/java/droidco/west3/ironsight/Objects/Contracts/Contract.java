package droidco.west3.ironsight.Objects.Contracts;

import droidco.west3.ironsight.Objects.Contracts.Utils.ContractType;
import droidco.west3.ironsight.Objects.Contracts.Utils.ContractUtils;
import droidco.west3.ironsight.Objects.Contracts.Utils.Difficulty;
import droidco.west3.ironsight.Objects.Location.Location;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Contract
{
    private String contractName;
    private int rewardXp;
    private double reward;
    private ContractType type;
    private List<Location> contractLocs;
    private Location location;
    private boolean isActive;
    private Difficulty difficulty;
    private int rarity;
    private String listingName;
    private static HashMap<String, Contract> contracts = new HashMap<>();

    public Contract(String contractName, int rewardXp, ContractType type, List<Location> contractLoc, boolean isActive, Difficulty difficulty, int rarity) {
        this.contractName = contractName;
        this.rewardXp = rewardXp;
        this.reward = ContractUtils.getDifficultyReward(difficulty);
        this.type = type;
        this.contractLocs = contractLoc;
        this.isActive = isActive;
        this.difficulty = difficulty;
        this.rarity = rarity;
        this.location = getRandomLocation();
        this.listingName = ChatColor.WHITE+ contractName +" - "+ ContractUtils.getDifficultyScale(difficulty);

        contracts.put(this.contractName,this);
    }
    public static HashMap<String, Contract> getContracts()
    {
        return contracts;
    }
    public Location getRandomLocation(){
        Random r = new Random(System.currentTimeMillis());
        int odds = r.nextInt(contractLocs.size());
        return contractLocs.get(odds);
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public int getRewardXp() {
        return rewardXp;
    }

    public void setRewardXp(int rewardXp) {
        this.rewardXp = rewardXp;
    }

    public ContractType getType() {
        return type;
    }

    public void setType(ContractType type) {
        this.type = type;
    }

    public List<Location> getContractLoc() {
        return contractLocs;
    }

    public void setContractLoc(List<Location> contractLoc) {
        this.contractLocs = contractLoc;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }
    public Location getLocation(){
        return this.location;
    }
    public double getReward(){
        return this.reward;
    }

    public String getListingName() {
        return listingName;
    }
}
