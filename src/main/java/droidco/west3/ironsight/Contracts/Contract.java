package droidco.west3.ironsight.Contracts;

import droidco.west3.ironsight.Contracts.OilField.OilFieldCrate;
import droidco.west3.ironsight.Contracts.Utils.*;
import droidco.west3.ironsight.Location.Location;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Contract
{
    private String contractName;
    private int rewardXp;
    private double reward;

    private List<OilFieldCrate> crates;
    private int reinforcementCount;
    private ContractType contractType;
    private List<Location> contractLocs;
    private Location location;
    private boolean isActive;
    private Difficulty difficulty;
    private int rarity;
    private int bulkMultiplier;
    private boolean bulkOrder;
    private String listingName;
    private List<String> description;
    public List<CompletionStep> steps = new ArrayList<>();
    private List<ItemStack> requestedItems;
    private static HashMap<String, Contract> contracts = new HashMap<>();

    public Contract(String contractName, ContractType type, List<Location> contractLocs, boolean isActive, int rarity) {
        //THESE ARE UNIVERSAL FOR THE CONTRACT
        this.contractName = contractName;
        this.contractType = type;
        this.contractLocs = contractLocs;
        this.isActive = isActive;
        this.rarity = rarity;

        this.listingName = ChatColor.WHITE+ contractName +" - "+ ContractUtils.getDifficultyScale(difficulty);

        contracts.put(this.contractName,this);
        //This will load EXTRA data SPECIFIC to the COMPLETION TYPE
        generateContracts();
    }
    public void setRewardXp()
    {
        switch(difficulty){
            case Rookie -> {
                this.rewardXp = 25;
            }
            case Apprentice -> {
                this.rewardXp = 45;
            }
            case Experienced -> {
                this.rewardXp = 60;
            }
            case Master -> {
                this.rewardXp = 100;
            }
        }
    }

    public void addCompletionStep(String stepKey, int stepNumber, List<String> taskDesc, ItemStack requestedGoods,String locationDesc){
        CompletionStep step = new CompletionStep(stepKey,stepNumber,taskDesc,requestedGoods,locationDesc);
        steps.add(step);
    }
    public void generateContracts()
    {
        /*
        First the contract is instanciated when the plugin loads. This gives the contract it's type, title,
        list of possible locations, the most basic info.
        New contracts can then be generated from this type.
         */
        this.location = getRandomLocation();
        /*
            After all the default random contract variables are set up,
            it's time to separate the contracts to load them by completion type
            Some contracts may have different completion requirements than others, this handles
            that.
         */
        switch(this.contractType){
            case Delivery -> {
                generateNewDelivery();
            }
            case Bounty -> {
                //generateNewHunter();
            }
            case OilField -> {
                generateNewOilField(20);
            }
        }
    }

    public void generateNewOilField(int reinforcementCount){
        this.crates = OilFieldCrate.getCratesByLocation(location);
        this.reinforcementCount = reinforcementCount;
        this.location = getRandomLocation();

        List<String> desc = new ArrayList<>();
        desc.add("Arrive at oilfield. Find");
        desc.add("and unlock the main crate.");
        addCompletionStep("steptest",1,desc,null,"Ride to "+location.getLocName());
        List<String> desc2 = new ArrayList<>();
        desc2.add("Guard off all enemies.");
        desc2.add("Survive until crate unlocks.");
        addCompletionStep("steptest2",1,desc,null,"Ride to "+location.getLocName());

    }

    public void generateNewDelivery(){

    }
    public void generateNewBountyHunter(Player p){
        //Check if player gets a PLAYER or NPC contract
        int odds = GlobalUtils.getRandomNumber(101);
        BountyTargetType targetType = null;
        if(odds<50){
            targetType = BountyTargetType.PLAYER;
        }else{
            targetType = BountyTargetType.NPC;
        }
        switch(targetType){
            case PLAYER -> {
                Player target = null;
                //Make a list of online players OTHER THAN the person taking the contract
                List<Player> playerPool = new ArrayList<>();
                for(Player p2 : Bukkit.getServer().getOnlinePlayers()){
                    //Avoid contractee, add others
                    if(!p2.getUniqueId().toString().equalsIgnoreCase(p.getUniqueId().toString())){
                        playerPool.add(p2);
                    }
                }
                //Check if we could find any other online players
                if(playerPool.size() > 0){
                    //FOUND PLAYERS!
                    int playerIndex = GlobalUtils.getRandomNumber(playerPool.size());
                    target = playerPool.get(playerIndex);
                }else{
                    //NO players
                    //Try again
                    generateNewBountyHunter(p);
                }
                String targetName = target.getDisplayName();
                p.sendMessage(targetName);
            }
            case NPC -> {

            }
        }
    }
    public Location getRandomLocation(){
        Random r = new Random(System.currentTimeMillis());
        int odds = r.nextInt(contractLocs.size());
        return contractLocs.get(odds);
    }
    public List<String> createDescription(String line1, String line2, String line3, String line4)
    {
        List<String> tmpDesc = new ArrayList<>();
        tmpDesc.add(line1);
        tmpDesc.add(line2);
        tmpDesc.add(line3);
        tmpDesc.add(line4);
        return tmpDesc;
    }
    public int getReinforcementMultiplier(){
        double multiplier = 1.0;
        if(difficulty == Difficulty.Master){
            multiplier = 1.5;
        }
        int val = (int) Math.round(reinforcementCount*multiplier);
        return val;
    }

    public List<CompletionStep> getSteps() {
        return steps;
    }

    public static HashMap<String, Contract> getContracts()
    {
        return contracts;
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

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
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
