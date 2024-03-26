package droidco.west3.ironsight.Contracts;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Contracts.OilField.OilFieldCrate;
import droidco.west3.ironsight.Contracts.Utils.*;
import droidco.west3.ironsight.Location.Location;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
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

    private List<OilFieldCrate> crates;
    private int reinforcementCount;
    private ContractType contractType;
    private List<Location> contractLocs;
    private Location location;
    private Difficulty difficulty;
    private int rarity;
    private int bulkMultiplier;
    private boolean bulkOrder;
    private String listingName;
    private List<String> description;
    public List<CompletionStep> steps = new ArrayList<>();
    private List<ItemStack> requestedItems;
    private static HashMap<String, Contract> contracts = new HashMap<>();

    public Contract(String contractName, ContractType type, List<Location> contractLocs, int rarity) {
        //THESE ARE UNIVERSAL FOR THE CONTRACT
        this.contractName = contractName;
        this.contractType = type;
        this.contractLocs = contractLocs;
        this.rarity = rarity;

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
        System.out.println("cck1");
        refreshContracts();
        System.out.println("check2");
        List<Contract> rookieContracts = new ArrayList<>();
        List<Contract> experiencedContracts = new ArrayList<>();
        List<Contract> apprenticeContracts = new ArrayList<>();
        List<Contract> masterContracts = new ArrayList<>();
        System.out.println("check3");
        rookieContracts = initializeContracts(rookieContracts,Difficulty.Rookie);
        System.out.println("check4");
        apprenticeContracts = initializeContracts(apprenticeContracts,Difficulty.Apprentice);
        System.out.println("check5");
        experiencedContracts = initializeContracts(experiencedContracts,Difficulty.Experienced);
        System.out.println("check6");
        masterContracts = initializeContracts(masterContracts,Difficulty.Master);
        System.out.println("check7");
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
        System.out.println(" ALL " + allContracts);

        if(allContracts == null){
            allContracts = new ArrayList<>();
        }
        System.out.println("init check1");
        while(allContracts.isEmpty() || allContracts == null){
            if(allContracts == null){
                System.out.println("OUCHIE??");
            }
            System.out.println("init while check");
            System.out.println("while check contracts: "+allContracts);
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
        System.out.println("generation check 1");
        this.location = getRandomLocation();
        /*
            After all the default random contract variables are set up,
            it's time to separate the contracts to load them by contract type
            Some contracts may have different completion requirements than others, this handles
            that.
         */
        System.out.println("generation check 2");
        switch(this.contractType){
            case Delivery -> {
                System.out.println("NEW DELIVERY");
                generateNewDelivery();
            }
            case Bounty -> {
                //generateNewHunter();
            }
            case OilField -> {
                System.out.println("NEW OILFIELD");
                generateNewOilField(20);
            }
        }
    }

    public void generateNewOilField(int reinforcementCount){
        this.crates = OilFieldCrate.getCratesByLocation(location);
        this.reinforcementCount = reinforcementCount;
        this.location = getRandomLocation();
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
        addCompletionStep("steptest",1,desc,null,"Ride to "+location.getLocName());
        List<String> desc2 = new ArrayList<>();
        desc2.add("Guard off all enemies.");
        desc2.add("Survive until crate unlocks.");
        addCompletionStep("steptest2",2,desc2,null,"Hold down "+location.getLocName());
    }

    public void generateNewDelivery(){
        int odds = GlobalUtils.getRandomNumber(101);
        if(odds>=50){
            this.difficulty = Difficulty.Rookie;
        }else {
            this.difficulty = Difficulty.Apprentice;
        }
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
