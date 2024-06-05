package droidco.west3.ironsight.Contracts;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Contracts.OilField.OilFieldCrate;
import droidco.west3.ironsight.Contracts.Utils.*;
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.Items.CustomItem;
import droidco.west3.ironsight.Items.ItemIcon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Contract {
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
    private List<CompletionStep> steps = new ArrayList<>();
    private List<ItemStack> requestedItemsNormal = new ArrayList<>();
    private List<ItemStack> requestedItemsRare = new ArrayList<>();
    private ItemStack contractIcon;

    public Contract(ContractType type, List<FrontierLocation> contractLocs, int rarity) {
        //THESE ARE UNIVERSAL FOR THE CONTRACT
        this.contractType = type;
        this.contractLocs = contractLocs;
        this.rarity = rarity;
        //This will load EXTRA data SPECIFIC to the COMPLETION TYPE
    }

    public Contract(ContractType type, List<FrontierLocation> contractLocs, int rarity, DeliveryType deliveryType) {
        //THESE ARE UNIVERSAL FOR THE CONTRACT
        this.contractType = type;
        this.contractLocs = contractLocs;
        this.rarity = rarity;
        this.deliveryType = deliveryType;

        //This will load EXTRA data SPECIFIC to the COMPLETION TYPE
    }

    public void setRewardXp() {
        switch (difficulty) {
            case Rookie -> {
                //this.rewardXp = 25;
                this.rewardXp = 17;
            }
            case Apprentice -> {
                //this.rewardXp = 45;
                this.rewardXp = 38;
            }
            case Experienced -> {
                this.rewardXp = 60;
            }
            case Master -> {
                this.rewardXp = 82;
            }
        }
    }

    public void addRequestedItemNormal(ItemStack item) {
        this.requestedItemsNormal.add(item);
    }

    public void addRequestedItemRare(ItemStack item) {
        this.requestedItemsRare.add(item);
    }

    public static void assignPlayerContracts(Player p, Bandit b) {
        List<Contract> mainPool = b.getContracts();
        List<Contract> contractPool = new ArrayList<>();
        contractPool.addAll(mainPool);
        refreshContracts(contractPool);

        //GET CONTRACTS
        System.out.println("INITIALIZING ROOKIES");
        List<Contract> rookieContracts = initializeContracts(Difficulty.Rookie, contractPool);
        Contract rookie = ContractUtils.getSingleContract(rookieContracts);
        contractPool.remove(rookie);
        b.setRookieContract(rookie);

        System.out.println("INITIALIZING APPRENTICE");
        List<Contract> apprenticeContracts = initializeContracts(Difficulty.Apprentice, contractPool);
        Contract apprentice = ContractUtils.getSingleContract(apprenticeContracts);
        contractPool.remove(apprentice);
        b.setApprenticeContract(apprentice);

        System.out.println("INITIALIZING EXPERIENCED");
        List<Contract> experiencedContracts = initializeContracts(Difficulty.Experienced, contractPool);
        Contract experienced = ContractUtils.getSingleContract(experiencedContracts);
        contractPool.remove(experienced);
        b.setExperiencedContract(experienced);

        //int masterOdds = GlobalUtils.getRandomNumber(101);
        //if(masterOdds<50){
        //    b.setExperiencedContract(ContractUtils.getSingleContract(masterContracts));
        //}else{

        //}

    }

    public static List<Contract> initializeContracts(Difficulty targetDiff, List<Contract> contractPool) {
        List<Contract> allContracts = getContractByDiff(targetDiff, contractPool);
        if (allContracts == null) {
            allContracts = new ArrayList<>();
        }
        while (allContracts.isEmpty() || allContracts == null) {
            refreshContracts(contractPool);
            allContracts = getContractByDiff(targetDiff, contractPool);
        }
        return allContracts;
    }

    public static void refreshContracts(List<Contract> contractPool) {
        //System.out.println("REFRESHING");
        for (Contract contract : contractPool) {
            contract.generateContract();
        }
    }

    public static List<Contract> getContractByDiff(Difficulty difficulty, List<Contract> contractPool) {
        List<Contract> targeted = new ArrayList<>();
        for (Contract contract : contractPool) {
            if (contract.getDifficulty().equals(difficulty)) {
                System.out.println("Difficult matched.");
                targeted.add(contract);
            }
        }
        return targeted;
    }

    public void addCompletionStep(String stepKey, int stepNumber, List<String> taskDesc, ItemStack requestedGoods, String locationDesc) {
        CompletionStep step = new CompletionStep(stepKey, stepNumber, taskDesc, requestedGoods, locationDesc);
        steps.add(step);
    }

    public void generateContract() {
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
        switch (this.contractType) {
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

    public void generateNewOilField(int reinforcementCount) {
        this.crates = OilFieldCrate.getCratesByLocation(frontierLocation);
        this.reinforcementCount = reinforcementCount;
        this.steps = new ArrayList<>();
        int odds = GlobalUtils.getRandomNumber(101);
        if (odds <= 20) {
            this.difficulty = Difficulty.Master;
            this.reward = 3150.0;
        } else {
            this.difficulty = Difficulty.Experienced;
            this.reward = 2500.0;
        }
        this.listingName = ChatColor.WHITE + contractName + " - " + ContractUtils.getDifficultyScale(difficulty);
        List<String> desc = new ArrayList<>();
        desc.add("Arrive at oilfield. Find");
        desc.add("and unlock the main crate.");
        addCompletionStep("steptest", 1, desc, null, "Ride to " + frontierLocation.getLocName());
        List<String> desc2 = new ArrayList<>();
        desc2.add("Guard off all enemies.");
        desc2.add("Survive until crate unlocks.");
        addCompletionStep("steptest2", 2, desc2, null, "Hold down " + frontierLocation.getLocName());

        this.listingName = ChatColor.WHITE + "Oil Field Crate Heist";
        this.contractIcon = new ItemStack(Material.MILK_BUCKET);
        this.contractIcon.getItemMeta().setDisplayName(listingName);

        ItemMeta iconMeta = this.contractIcon.getItemMeta();
        iconMeta.setDisplayName(listingName);
        this.contractIcon.setItemMeta(iconMeta);
        setRewardXp();
    }

    public void generateNewDelivery() {
        //      HANDLE BULK AND RARE ORDERS:
        int amount = 0;
        boolean rareRequest = false;
        int rareRequestOdds = GlobalUtils.getRandomNumber(101);
        if (rareRequestOdds < 15) {
            rareRequest = true;
        }
        switch (deliveryType) {
            case FISHER, HUNTER -> {
                if (rareRequest) {
                    amount = GlobalUtils.getRandomRange(3, 9);
                } else {
                    amount = GlobalUtils.getRandomRange(11, 29);
                }
            }
            case DRUG_RUNNER -> {
                if (rareRequest) {
                    amount = GlobalUtils.getRandomRange(44, 92);
                } else {
                    amount = GlobalUtils.getRandomRange(22, 46);
                }

            }
            case MINER -> {
                if (rareRequest) {
                    amount = GlobalUtils.getRandomRange(1, 4);
                } else {
                    amount = GlobalUtils.getRandomRange(22, 46);
                }
            }
        }
        bulkMultiplier = GlobalUtils.getRandomRange(2, 5);
        int bulkOdds = GlobalUtils.getRandomNumber(101);
        if (bulkOdds < 10) {
            amount = amount * bulkMultiplier;
            bulkOrder = true;
        }
        this.requestedAmount = amount;
        //      CHOOSE THE ITEM
        this.frontierLocation = getRandomLocation();
        switch (deliveryType) {
            case FISHER -> {
                if (rareRequest) {
                    if (frontierLocation.getLocName().equalsIgnoreCase("Three Forks Delta")) {
                        requestedItem = CustomItem.getCustomItem("Alligator").getItemStack();
                    } else if (frontierLocation.getLocName().equalsIgnoreCase("Pearl River")) {
                        int fishOdds = GlobalUtils.getRandomNumber(101);
                        if (fishOdds < 40) {
                            requestedItem = CustomItem.getCustomItem("Pearl River Trout").getItemStack();
                        } else {
                            requestedItem = CustomItem.getCustomItem("Arctic Salmon").getItemStack();
                        }
                    } else if (frontierLocation.getLocName().equalsIgnoreCase("Lower Guadalupe River")) {
                        requestedItem = CustomItem.getCustomItem("Sunken Catfish").getItemStack();
                    } else if (frontierLocation.getLocName().equalsIgnoreCase("Slough Creek River")) {
                        int fishOdds = GlobalUtils.getRandomNumber(101);
                        if (fishOdds < 40) {
                            requestedItem = CustomItem.getCustomItem("Gold Stoned Herring").getItemStack();
                        } else {
                            requestedItem = CustomItem.getCustomItem("Southern Salmon").getItemStack();
                        }
                    }
                } else {
                    List<ItemStack> fish = new ArrayList<>();
                    fish.add(CustomItem.getCustomItem("Poor Mans Crappie").getItemStack());
                    fish.add(CustomItem.getCustomItem("Gray Stoned Herring").getItemStack());
                    fish.add(CustomItem.getCustomItem("Cactus Pronged Chub").getItemStack());
                    int fishChoice = GlobalUtils.getRandomNumber(fish.size());
                    requestedItem = fish.get(fishChoice);
                }

            }
            case MINER -> {
                if (rareRequest) {
                    List<ItemStack> gems = new ArrayList<>();
                    gems.add(CustomItem.getCustomItem("Amethyst Bud").getItemStack());
                    gems.add(CustomItem.getCustomItem("Mossy Jade").getItemStack());
                    gems.add(CustomItem.getCustomItem("River Diamond").getItemStack());
                    gems.add(CustomItem.getCustomItem("Void Opal").getItemStack());
                    gems.add(CustomItem.getCustomItem("Baron's Emerald").getItemStack());
                    int gemChoice = GlobalUtils.getRandomNumber(gems.size());
                    requestedItem = gems.get(gemChoice);
                } else {
                    List<ItemStack> gems = new ArrayList<>();
                    gems.add(CustomItem.getCustomItem("Gold Ore").getItemStack());
                    gems.add(CustomItem.getCustomItem("Iron Ore").getItemStack());
                    gems.add(CustomItem.getCustomItem("Copper Ore").getItemStack());
                    int gemChoice = GlobalUtils.getRandomNumber(gems.size());
                    requestedItem = gems.get(gemChoice);
                }
            }
            case DRUG_RUNNER -> {
                if (frontierLocation.getLocName().equalsIgnoreCase("Red Ash Camp")) {
                    requestedItem = CustomItem.getCustomItem("Spice").getItemStack();
                } else if (frontierLocation.getLocName().equalsIgnoreCase("Storm Point")) {
                    requestedItem = CustomItem.getCustomItem("Processed Smokeleaf").getItemStack();
                }
            }
        }

        //requestedItem.setAmount(amount);

        //      SELECT DIFFICULTY BASED OFF REQUEST AMOUNT
        if (rareRequest) {
            if (amount < 16) {
                //MEDIUM
                difficulty = Difficulty.Apprentice;
            } else {
                //HARD
                difficulty = Difficulty.Experienced;
            }

        } else {
            if (amount < 29) {
                //EASY
                if (deliveryType.equals(DeliveryType.DRUG_RUNNER)) {
                    difficulty = Difficulty.Apprentice;
                } else {
                    difficulty = Difficulty.Rookie;
                }

            } else if (amount < 44 && amount >= 29) {
                //MEDIUM
                if (deliveryType.equals(DeliveryType.DRUG_RUNNER)) {
                    difficulty = Difficulty.Experienced;
                } else {
                    difficulty = Difficulty.Apprentice;
                    ;
                }
            } else {
                //HARD?
                if (deliveryType.equals(DeliveryType.DRUG_RUNNER)) {
                    difficulty = Difficulty.Master;
                } else {
                    difficulty = Difficulty.Experienced;
                    ;
                }
            }
        }
        //      COMPLETION STEPS
        this.steps = new ArrayList<>();
        switch (deliveryType) {
            case FISHER -> {
                List<String> desc = new ArrayList<>();
                desc.add("Arrive at " + ChatColor.GREEN + frontierLocation.getLocName());
                desc.add("Fish until you have requested amount");
                addCompletionStep("steptest", 1, desc, requestedItem, "Ride to " + ChatColor.GREEN + frontierLocation.getLocName());
            }
            case MINER -> {
                List<String> desc = new ArrayList<>();
                desc.add("Arrive at " + ChatColor.GREEN + frontierLocation.getLocName());
                desc.add("Mine ores until you have requested amount");
                addCompletionStep("steptest", 1, desc, requestedItem, "Ride to " + ChatColor.GREEN + frontierLocation.getLocName());
            }
            case DRUG_RUNNER -> {
                List<String> desc = new ArrayList<>();
                desc.add("Arrive at " + ChatColor.GREEN + FrontierLocation.getLocation("Smokeleaf Field").getLocName());
                desc.add("Harvest unprocessed drugs from field");
                addCompletionStep("steptest", 1, desc, requestedItem, "Ride to " + ChatColor.GREEN + FrontierLocation.getLocation("Smokeleaf Field").getLocName());
                desc = new ArrayList<>();
                desc.add("Arrive at " + ChatColor.GREEN + frontierLocation.getLocName());
                desc.add("Process the drugs at a processor inside");
                addCompletionStep("steptest", 2, desc, requestedItem, "Ride to " + ChatColor.GREEN + frontierLocation.getLocName());
            }
        }
        List<String> desc = new ArrayList<>();
        desc.add("Return to any " + ChatColor.YELLOW + "Contractor");
        desc.add("for reward.");
        addCompletionStep("steptest", 3, desc, null, "Ride to any town");
        System.out.println(requestedItem.getItemMeta().getDisplayName());
        this.reward = amount * CustomItem.getCustomItem(ChatColor.stripColor(requestedItem.getItemMeta().getDisplayName())).getSalePrice();
        String listing = "";

        switch (deliveryType) {
            case FISHER -> {
                listing += (String.valueOf(ChatColor.WHITE) + "Fishing Guild ");
            }
            case MINER -> {
                listing += (String.valueOf(ChatColor.WHITE) + "Mining Guild ");
            }
            case DRUG_RUNNER -> {
                listing += (String.valueOf(ChatColor.RED) + "Drug Runner ");
            }
        }
        if (rareRequest) {
            listing += (String.valueOf(ChatColor.LIGHT_PURPLE) + "Rare ");
        }

        if (bulkOrder) {
            listing += (String.valueOf(ChatColor.WHITE) + String.valueOf(ChatColor.BOLD) + "Bulk ");
            ;
        }
        listing += (String.valueOf(ChatColor.WHITE) + "Order!");
        this.listingName = listing;
        this.contractIcon = new ItemStack(requestedItem.getType());
        ItemMeta iconMeta = this.contractIcon.getItemMeta();
        iconMeta.setDisplayName(listing);
        this.contractIcon.setItemMeta(iconMeta);
        setRewardXp();
    }

    public void generateNewBountyHunter(Player p) {
        //Check if player gets a PLAYER or NPC contract
        int odds = GlobalUtils.getRandomNumber(101);
        BountyTargetType targetType = null;
        if (odds < 50) {
            targetType = BountyTargetType.PLAYER;
        } else {
            targetType = BountyTargetType.NPC;
        }
        switch (targetType) {
            case PLAYER -> {
                Player target = null;
                //Make a list of online players OTHER THAN the person taking the contract
                List<Player> playerPool = new ArrayList<>();
                for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
                    //Avoid contractee, add others
                    if (!p2.getUniqueId().toString().equalsIgnoreCase(p.getUniqueId().toString())) {
                        playerPool.add(p2);
                    }
                }
                //Check if we could find any other online players
                if (playerPool.size() > 0) {
                    //FOUND PLAYERS!
                    int playerIndex = GlobalUtils.getRandomNumber(playerPool.size());
                    target = playerPool.get(playerIndex);
                } else {
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

    public FrontierLocation getRandomLocation() {
        Random r = new Random(System.currentTimeMillis());
        int odds = r.nextInt(contractLocs.size());
        return contractLocs.get(odds);
    }

    public List<String> createDescription(String line1, String line2, String line3, String line4) {
        List<String> tmpDesc = new ArrayList<>();
        tmpDesc.add(line1);
        tmpDesc.add(line2);
        tmpDesc.add(line3);
        tmpDesc.add(line4);
        return tmpDesc;
    }

    public int getReinforcementMultiplier() {
        double multiplier = 1.0;
        if (difficulty == Difficulty.Master) {
            multiplier = 1.5;
        }
        int val = (int) Math.round(reinforcementCount * multiplier);
        return val;
    }

    public void startContract(Player p) {
        switch (this.contractType) {
            case OilField -> {
                p.getInventory().addItem(CustomItem.getCustomItem("Crate Key").getItemStack());
            }
        }

    }

    public ItemStack getContractIcon() {
        return contractIcon;
    }

    public void setContractIcon(ItemStack contractIcon) {
        this.contractIcon = contractIcon;
    }

    public int getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(int requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public ItemStack getRequestedItem() {
        return requestedItem;
    }

    public void setRequestedItem(ItemStack requestedItem) {
        this.requestedItem = requestedItem;
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

    public FrontierLocation getLocation() {
        return this.frontierLocation;
    }

    public double getReward() {
        return this.reward;
    }

    public String getListingName() {
        return listingName;
    }
}
