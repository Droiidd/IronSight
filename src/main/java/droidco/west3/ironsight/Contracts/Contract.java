package droidco.west3.ironsight.Contracts;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Contracts.OilField.OilFieldCrate;
import droidco.west3.ironsight.Contracts.Utils.*;
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.Items.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Contract
{
    private String contractName;
    private int rewardXp;
    private double reward;
    private ItemStack requestedItem;
    private List<OilFieldCrate> crates;
    private int reinforcementCount;
    private ContractType contractType;
    private List<FrontierLocation> contractLocs;
    private FrontierLocation frontierLocation;
    private Difficulty difficulty;
    private DeliveryType deliveryType;
    private int rarity;
    private int bulkMultiplier;
    private boolean bulkOrder;
    private int requestedAmount;
    private String listingName;
    private List<String> description;
    public List<CompletionStep> steps = new ArrayList<>();
    public List<ItemStack> requestedItemsNormal = new ArrayList<>();
    public List<ItemStack> requestedItemsRare = new ArrayList<>();
    private static HashMap<String, Contract> contracts = new HashMap<>();

    public Contract(String contractName, ContractType type, List<FrontierLocation> contractLocs, int rarity) {
        //THESE ARE UNIVERSAL FOR THE CONTRACT
        this.contractName = contractName;
        this.contractType = type;
        this.contractLocs = contractLocs;
        this.rarity = rarity;

        contracts.put(this.contractName,this);
        //This will load EXTRA data SPECIFIC to the COMPLETION TYPE
    }
    public Contract(String contractName, ContractType type, List<FrontierLocation> contractLocs, int rarity,DeliveryType deliveryType) {
        //THESE ARE UNIVERSAL FOR THE CONTRACT
        this.contractName = contractName;
        this.contractType = type;
        this.contractLocs = contractLocs;
        this.rarity = rarity;
        this.deliveryType = deliveryType;

        contracts.put(this.contractName,this);
        //This will load EXTRA data SPECIFIC to the COMPLETION TYPE
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
    public void addRequestedItemNormal(ItemStack item)
    {
        this.requestedItemsNormal.add(item);
    }
    public void addRequestedItemRare(ItemStack item)
    {
        this.requestedItemsRare.add(item);
    }
    public static void refreshContracts(){
        System.out.println("REFRESH");
        System.out.println("contracts" + contracts);
        for (Map.Entry<String,Contract> mapElement : contracts.entrySet()) {
            // Adding some bonus marks to all the students
            Contract value = mapElement.getValue();
            System.out.println(value.getContractName());
            value.generateContract();
        }
    }
    public static void assignPlayerContracts(Player p, Bandit b){
        refreshContracts();
        List<Contract> rookieContracts = new ArrayList<>();
        List<Contract> experiencedContracts = new ArrayList<>();
        List<Contract> apprenticeContracts = new ArrayList<>();
        List<Contract> masterContracts = new ArrayList<>();
        rookieContracts = initializeContracts(rookieContracts,Difficulty.Rookie);
        apprenticeContracts = initializeContracts(apprenticeContracts,Difficulty.Apprentice);
        experiencedContracts = initializeContracts(experiencedContracts,Difficulty.Experienced);
        masterContracts = initializeContracts(masterContracts,Difficulty.Master);
        b.setRookieContract(ContractUtils.getSingleContract(rookieContracts));
        b.setApprenticeContract(ContractUtils.getSingleContract(apprenticeContracts));
        int masterOdds = GlobalUtils.getRandomNumber(101);
        if(masterOdds<50){
            b.setExperiencedContract(ContractUtils.getSingleContract(masterContracts));
        }else{
            b.setExperiencedContract(ContractUtils.getSingleContract(experiencedContracts));
        }

    }
    public static List<Contract> initializeContracts(List<Contract> allContracts, Difficulty targetDiff){
        allContracts = ContractUtils.getContractByDiff(targetDiff);
        if(allContracts == null){
            allContracts = new ArrayList<>();
        }
        while(allContracts.isEmpty() || allContracts == null){
            if(allContracts == null){
                System.out.println("OUCHIE??");
            }
            refreshContracts();
            allContracts = ContractUtils.getContractByDiff(targetDiff);
        }
        return allContracts;
    }

    public static List<Contract> getContractByDiff(Difficulty difficulty){
        List<Contract> targeted = new ArrayList<>();
        contracts.forEach((key, contract) -> {
            if(contract.getDifficulty().compareTo(difficulty) == 0){
                targeted.add(contract);
            }
        });
        return targeted;
    }
    public void addCompletionStep(String stepKey, int stepNumber, List<String> taskDesc, ItemStack requestedGoods,String locationDesc){
        CompletionStep step = new CompletionStep(stepKey,stepNumber,taskDesc,requestedGoods,locationDesc);
        steps.add(step);
    }
    public void generateContract()
    {
        /*
        First the contract is instanciated when the plugin loads. This gives the contract it's type, title,
        list of possible locations, the most basic info.
        New contracts can then be generated from this type.
         */
        this.frontierLocation = getRandomLocation();
        /*
            After all the default random contract variables are set up,
            it's time to separate the contracts to load them by contract type
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
        this.crates = OilFieldCrate.getCratesByLocation(frontierLocation);
        this.reinforcementCount = reinforcementCount;
        this.steps = new ArrayList<>();
        int odds = GlobalUtils.getRandomNumber(101);
        if(odds<=20){
            this.difficulty = Difficulty.Master;
        }else {
            this.difficulty = Difficulty.Experienced;
        }
        this.listingName = ChatColor.WHITE+ contractName +" - "+ ContractUtils.getDifficultyScale(difficulty);
        List<String> desc = new ArrayList<>();
        desc.add("Arrive at oilfield. Find");
        desc.add("and unlock the main crate.");
        addCompletionStep("steptest",1,desc,null,"Ride to "+ frontierLocation.getLocName());
        List<String> desc2 = new ArrayList<>();
        desc2.add("Guard off all enemies.");
        desc2.add("Survive until crate unlocks.");
        addCompletionStep("steptest2",2,desc2,null,"Hold down "+ frontierLocation.getLocName());
    }

    public void generateNewDelivery(){
        //      HANDLE BULK AND RARE ORDERS:
        int amount = 0;
        boolean rareRequest = false;
        int rareRequestOdds = GlobalUtils.getRandomNumber(101);
        if(rareRequestOdds < 15){
            rareRequest = true;
        }
        switch(deliveryType){
            case FISHER,HUNTER,DRUG_RUNNER -> {
                if(rareRequest){
                    amount = GlobalUtils.getRandomRange(3,9);
                }else{
                    amount = GlobalUtils.getRandomRange(11,29);
                }
            }
            case MINER -> {
                if(rareRequest){
                    amount = GlobalUtils.getRandomRange(1,4);
                }else{
                    amount = GlobalUtils.getRandomRange(22,46);
                }
            }
        }
        bulkMultiplier = GlobalUtils.getRandomNumber(5);
        int bulkOdds = GlobalUtils.getRandomNumber(101);
        if(bulkOdds < 10){
            amount = amount * bulkMultiplier;
        }
        this.requestedAmount = amount;
        //      CHOOSE THE ITEM
        this.frontierLocation = getRandomLocation();
        switch (deliveryType){
            case FISHER -> {
                if(rareRequest){
                    if(frontierLocation.getLocName().equalsIgnoreCase("Three Forks Delta")){
                        requestedItem = CustomItem.getCustomItem("Alligator").getItemStack();
                    }
                    else if(frontierLocation.getLocName().equalsIgnoreCase("Pearl River")){
                        int fishOdds = GlobalUtils.getRandomNumber(101);
                        if(fishOdds < 40){
                            requestedItem = CustomItem.getCustomItem("Pearl River Trout").getItemStack();
                        }else{
                            requestedItem = CustomItem.getCustomItem("Arctic Salmon").getItemStack();
                        }
                    }
                    else if(frontierLocation.getLocName().equalsIgnoreCase("Lower Guadalupe River")){
                        requestedItem = CustomItem.getCustomItem("Sunken Catfish").getItemStack();
                    }
                    else if(frontierLocation.getLocName().equalsIgnoreCase("Slough Creek River")){
                        int fishOdds = GlobalUtils.getRandomNumber(101);
                        if(fishOdds < 40){
                            requestedItem = CustomItem.getCustomItem("Gold Stoned Herring").getItemStack();
                        }else{
                            requestedItem = CustomItem.getCustomItem("Southern Salmon").getItemStack();
                        }
                    }
                }else{
//                 List<ItemStack> fish = new ArrayList<>();
//                 fish.add(CustomItem.getCustomItem("Poor Mans Crappie").getItemStack());
//                 fish.add(CustomItem.getCustomItem("Gray Stoned Herring").getItemStack());
//                 fish.add(CustomItem.getCustomItem("Cactus Pronged Chub").getItemStack());
//                 int fishChoice = GlobalUtils.getRandomNumber(fish.size());
//                 requestedItem = fish.get(fishChoice);
                    requestedItem = CustomItem.getCustomItem("Southern Salmon").getItemStack();
                }
            }
        }

        //requestedItem.setAmount(amount);

        //      SELECT DIFFICULTY BASED OFF REQUEST AMOUNT
        if(rareRequest){
            if(amount < 16){
                //MEDIUM
                difficulty = Difficulty.Apprentice;
            }else{
                //HARD
                difficulty = Difficulty.Experienced;
            }

        }else{
            if(amount < 29){
                //EASY
                difficulty = Difficulty.Rookie;
            }else if(amount < 44){
                //MEDIUM
                difficulty = Difficulty.Apprentice;
            }else{
                //HARD?
                difficulty = Difficulty.Experienced;
            }
        }
        //      COMPLETION STEPS
        this.steps = new ArrayList<>();
        switch (deliveryType){
            case FISHER -> {
                List<String> desc = new ArrayList<>();
                desc.add("Arrive at "+frontierLocation.getLocName());
                desc.add("Fish until you have requested amount");
                addCompletionStep("steptest",1,desc,requestedItem,"Ride to "+ frontierLocation.getLocName());
            }
        }
        List<String> desc = new ArrayList<>();
        desc.add("Return to town");
        desc.add("for reward.");
        addCompletionStep("steptest",2,desc,null,"Ride to any town");
        System.out.println(requestedItem.getItemMeta().getDisplayName());
        this.reward = amount * CustomItem.getCustomItem(ChatColor.stripColor(requestedItem.getItemMeta().getDisplayName())).getSalePrice();
        this.listingName = ChatColor.WHITE+ contractName +" - "+ ContractUtils.getDifficultyScale(difficulty);
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
    public FrontierLocation getRandomLocation(){
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

    public void startContract(Player p){
        switch(this.contractType){
            case OilField -> {
                p.getInventory().addItem(CustomItem.getCustomItem("Crate Key").getItemStack());
            }
        }

    }
    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
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
    public List<FrontierLocation> getContractLoc() {
        return contractLocs;
    }
    public void setContractLoc(List<FrontierLocation> contractLoc) {
        this.contractLocs = contractLoc;
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
    public FrontierLocation getLocation(){
        return this.frontierLocation;
    }
    public double getReward(){
        return this.reward;
    }
    public String getListingName() {
        return listingName;
    }
}
