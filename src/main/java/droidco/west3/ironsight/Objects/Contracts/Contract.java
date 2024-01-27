package droidco.west3.ironsight.Objects.Contracts;

import droidco.west3.ironsight.Objects.Contracts.Utils.CompletionType;
import droidco.west3.ironsight.Objects.Contracts.Utils.ContractType;
import droidco.west3.ironsight.Objects.Contracts.Utils.ContractUtils;
import droidco.west3.ironsight.Objects.Contracts.Utils.Difficulty;
import droidco.west3.ironsight.Objects.Location.Location;
import droidco.west3.ironsight.Utils.GlobalUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
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
    private CompletionType completionType;
    private ContractType contractType;
    private List<Location> contractLocs;
    private Location location;
    private boolean isActive;
    private Difficulty difficulty;
    private int rarity;
    private String listingName;
    private List<String> description;
    private List<ItemStack> requestedItems;
    private static HashMap<String, Contract> contracts = new HashMap<>();

    public Contract(String contractName, int rewardXp, CompletionType completionType, ContractType type, List<Location> contractLocs, boolean isActive, Difficulty difficulty, int rarity) {
        //THESE ARE UNIVERSAL FOR THE CONTRACT
        this.contractName = contractName;
        this.rewardXp = rewardXp;
        this.completionType = completionType;
        this.contractType = type
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
        //Contract generation is really 
        switch(contractType){
            case Miner -> {
                int bulkOdds = GlobalUtils.getRandomNumber(101);
                List<ItemStack> request = new ArrayList<>();
                if(bulkOdds <= 35){
                    //Add bulk items
                    this.description = createDescription("I'm looking for", request.get(0).getAmount()+" "+request.get(0).getItemMeta().getDisplayName()+"'s",
                            "Raw ore is fine.","");
                }else{
                    //Add normal amount
                    this.description = createDescription("I'm looking for", request.get(0).getAmount()+" "+request.get(0).getItemMeta().getDisplayName()+"'s",
                            "I'll pay well","for good gems.");
                }
            }
            case Fisher -> {
                int bulkOdds = GlobalUtils.getRandomNumber(101);
                List<ItemStack> request = new ArrayList<>();
                if(bulkOdds <= 35){
                    //Add bulk items
                    this.description = createDescription("I'm requesting a","bulk order of",
                            request.get(0).getAmount()+" "+request.get(0).getItemMeta().getDisplayName(),
                            "");
                }else{
                    //Add normal amount
                    this.description = createDescription("Looking for a couple of", request.get(0).getAmount()+" "+request.get(0).getItemMeta().getDisplayName(),
                            "Just need food for home.","");
                }
            }
        }




        switch(this.completionType){
            case Delivery -> {
                //generateNewDelivery();
            }
            case Hunter -> {
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

    public void generateNewDelivery(List<ItemStack> requestedItems){
        this.location = getRandomLocation();
        this.reward = ContractUtils.getDifficultyReward(difficulty);
        this.requestedItems = requestedItems;
    }
    public void generateNewHunter(Player p){
        //Check if player gets a PLAYER or NPC contract
        int odds = GlobalUtils.getRandomNumber(101);
        if(odds<50){
            //Player-based contract
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
                generateNewHunter(p);
            }
            String targetName = target.getDisplayName();
            p.sendMessage(targetName);
        }else{
            //NPC-based contract
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

    public CompletionType getType() {
        return completionType;
    }

    public void setType(CompletionType type) {
        this.completionType = type;
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
