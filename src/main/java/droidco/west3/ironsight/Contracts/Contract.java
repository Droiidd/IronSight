package droidco.west3.ironsight.Contracts;

import droidco.west3.ironsight.Contracts.Utils.BountyTargetType;
import droidco.west3.ironsight.Contracts.Utils.ContractType;
import droidco.west3.ironsight.Contracts.Utils.ContractUtils;
import droidco.west3.ironsight.Contracts.Utils.Difficulty;
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
    private List<ItemStack> requestedItems;
    private static HashMap<String, Contract> contracts = new HashMap<>();

    public Contract(String contractName, int rewardXp, ContractType type, List<Location> contractLocs, boolean isActive, Difficulty difficulty, int rarity) {
        //THESE ARE UNIVERSAL FOR THE CONTRACT
        this.contractName = contractName;
        this.rewardXp = rewardXp;
        this.contractType = type;
        this.contractLocs = contractLocs;
        this.isActive = isActive;
        this.difficulty = difficulty;
        this.rarity = rarity;

        this.listingName = ChatColor.WHITE+ contractName +" - "+ ContractUtils.getDifficultyScale(difficulty);

        contracts.put(this.contractName,this);
        //This will load EXTRA data SPECIFIC to the COMPLETION TYPE
        generateContracts();
    }
    public void generateContracts()
    {
        /*
        Contract generation is really scuffed for now so until we change it this is how it works
        First the contract is instanciated when the plugin loads. This gives the contract it's type, title,
        list of possible locations, the most basic info.
        New contracts can then be generated from this type.
         */
        this.location = getRandomLocation();
        this.reward = ContractUtils.getDifficultyReward(difficulty);
        //Check if it's a bulk order delivery
        int bulkOdds = GlobalUtils.getRandomNumber(101);
        if(bulkOdds < 35){
            this.bulkOrder = true;
            this.bulkMultiplier = GlobalUtils.getRandomRange(3,5);
        }else {
        this.bulkOrder = false;
        }
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
        }
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

    public void generateNewDelivery(){
        /*
        In order:
        -Figure out what item list is associated with the job.
        -Randomly choose an item, and give it a regular amount * bulkMultiplier
        -Create the description
         */
        //Choose which item is requested from the jobs list of requestable items

        //Determine amount of requested items.
        ItemStack requestedItem = null;
//        switch(contractType){
//            case Miner -> {
//                int minerLow = 25;
//                int minerHigh = 45;
//                if (bulkOrder) {
//                    requestedItem.setAmount(GlobalUtils.getRandomRange(minerLow, minerHigh) * bulkMultiplier);
//                } else {
//                    requestedItem.setAmount(GlobalUtils.getRandomRange(minerLow, minerHigh));
//                }
//            }
//            case Fisher -> {
//                int fisherLow = 25;
//                int fisherHigh = 45;
//                if (bulkOrder) {
//                    requestedItem.setAmount(GlobalUtils.getRandomRange(fisherLow, fisherHigh) * bulkMultiplier);
//                } else {
//                    requestedItem.setAmount(GlobalUtils.getRandomRange(fisherLow, fisherHigh));
//                }
//            }
            //Add new contracts here
        //}

        //Set up the description.
//        switch(contractType){
//            case Miner -> {
//                if(bulkOrder){
//                    //Add bulk items
//                    this.description = createDescription("I'm looking for", requestedItem.getAmount()+" "+requestedItem.getItemMeta().getDisplayName()+"'s",
//                            "Raw ore is fine.","");
//                }else{
//                    this.description = createDescription("I'm looking for", requestedItem.getAmount()+" "+requestedItem.getItemMeta().getDisplayName()+"'s",
//                            "I'll pay well","for good gems.");
//                }
//            }
//            case Fisher -> {
//                if(bulkOrder){
//                    //Add bulk items
//                    this.description = createDescription("I'm requesting a","bulk order of",
//                            requestedItem.getAmount()+" "+requestedItem.getItemMeta().getDisplayName(),
//                            "");
//                }else{
//                    this.description = createDescription("Looking for a couple of", requestedItem.getAmount()+" "+requestedItem.getItemMeta().getDisplayName(),
//                            "Just need food for home.","");
//                }
//            }
//            //ADD MORE CONTRACTS HERE
//        }





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
