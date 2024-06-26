package droidco.west3.ironsight.contracts;

import droidco.west3.ironsight.bandit.Bandit;
import droidco.west3.ironsight.contracts.oilfield.OilFieldCrate;
import droidco.west3.ironsight.contracts.utils.*;
import droidco.west3.ironsight.frontierlocation.FrontierLocation;
import droidco.west3.ironsight.globals.utils.GlobalUtils;
import droidco.west3.ironsight.items.CustomItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Getter
@Setter
public class Contract {
  private String contractName;
  private int rewardXp;
  private double reward;
  private ItemStack requestedItem;
  private List<OilFieldCrate> crates;
  private int reinforcementCount;
  private ContractType contractType;
  private List<FrontierLocation> contractLocs;
  private FrontierLocation location;
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
    // THESE ARE UNIVERSAL FOR THE CONTRACT
    this.contractType = type;
    this.contractLocs = contractLocs;
    this.rarity = rarity;
    // This will load EXTRA data SPECIFIC to the COMPLETION TYPE
  }

  public Contract(
      ContractType type,
      List<FrontierLocation> contractLocs,
      int rarity,
      DeliveryType deliveryType) {
    // THESE ARE UNIVERSAL FOR THE CONTRACT
    this.contractType = type;
    this.contractLocs = contractLocs;
    this.rarity = rarity;
    this.deliveryType = deliveryType;

    // This will load EXTRA data SPECIFIC to the COMPLETION TYPE
  }

  public Contract(
      CustomItem requestedItem,
      int requestedAmount,
      String listingName,
      ContractType type,
      DeliveryType deliveryType,
      FrontierLocation location,
      Difficulty difficulty) {
    this.requestedItem = requestedItem.getItemStack();
    this.requestedAmount = requestedAmount;
    this.listingName = listingName;
    this.contractType = type;
    this.deliveryType = deliveryType;
    this.location = location;
    this.difficulty = difficulty;
    setRewardXp();
    this.reward = (requestedItem.getSalePrice() * requestedAmount);
  }

  public static void assignPlayerContracts(Player p, Bandit b) {
    List<Contract> mainPool = b.getContracts();
    List<Contract> contractPool = new ArrayList<>();
    contractPool.addAll(mainPool);
    refreshContracts(contractPool);

    // GET CONTRACTS
    System.out.println("INITIALIZING ROOKIES");
    List<Contract> rookieContracts = initializeContracts(Difficulty.ROOKIE, contractPool);
    Contract rookie = ContractUtils.getSingleContract(rookieContracts);
    contractPool.remove(rookie);
    b.setRookieContract(rookie);

    System.out.println("INITIALIZING APPRENTICE");
    List<Contract> apprenticeContracts = initializeContracts(Difficulty.APPRENTICE, contractPool);
    Contract apprentice = ContractUtils.getSingleContract(apprenticeContracts);
    contractPool.remove(apprentice);
    b.setApprenticeContract(apprentice);

    System.out.println("INITIALIZING EXPERIENCED");
    List<Contract> experiencedContracts = initializeContracts(Difficulty.EXPERIENCED, contractPool);
    Contract experienced = ContractUtils.getSingleContract(experiencedContracts);
    contractPool.remove(experienced);
    b.setExperiencedContract(experienced);

    // int masterOdds = GlobalUtils.getRandomNumber(101);
    // if(masterOdds<50){
    //    b.setExperiencedContract(ContractUtils.getSingleContract(masterContracts));
    // }else{

    // }

  }

  public static List<Contract> initializeContracts(
      Difficulty targetDiff, List<Contract> contractPool) {
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
    // System.out.println("REFRESHING");
    for (Contract contract : contractPool) {
      contract.generateContract();
    }
  }

  public static List<Contract> getContractByDiff(
      Difficulty difficulty, List<Contract> contractPool) {
    List<Contract> targeted = new ArrayList<>();
    for (Contract contract : contractPool) {
      if (contract.getDifficulty().equals(difficulty)) {
        System.out.println("Difficult matched.");
        targeted.add(contract);
      }
    }
    return targeted;
  }

  public void setRewardXp() {
    switch (difficulty) {
      case ROOKIE -> {
        // this.rewardXp = 25;
        this.rewardXp = 17;
      }
      case APPRENTICE -> {
        // this.rewardXp = 45;
        this.rewardXp = 38;
      }
      case EXPERIENCED -> {
        this.rewardXp = 60;
      }
      case MASTER -> {
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

  public void addCompletionStep(
      String stepKey,
      int stepNumber,
      List<String> taskDesc,
      ItemStack requestedGoods,
      String locationDesc) {
    CompletionStep step =
        new CompletionStep(stepKey, stepNumber, taskDesc, requestedGoods, locationDesc);
    steps.add(step);
  }

  public void generateContract() {
    /*
    First the contract is instanciated when the plugin loads. This gives the contract it's type, title,
    list of possible locations, the most basic info.
    New contracts can then be generated from this type.
     */
    this.location = getRandomLocation();
    /*
       After all the default random contract variables are set up,
       it's time to separate the contracts to load them by contract type
       Some contracts may have different completion requirements than others, this handles
       that.
    */
    switch (this.contractType) {
      case DELIVERY -> {
        generateNewDelivery();
      }
      case BOUNTY -> {
        // generateNewHunter();
      }
      case OIL_FIELD -> {
        generateNewOilField(20);
      }
    }
  }

  public void generateNewOilField(int reinforcementCount) {
    this.crates = OilFieldCrate.getCratesByLocation(location);
    this.reinforcementCount = reinforcementCount;
    this.steps = new ArrayList<>();
    int odds = GlobalUtils.getRandomNumber(101);
    if (odds <= 20) {
      this.difficulty = Difficulty.MASTER;
      this.reward = 3150.0;
    } else {
      this.difficulty = Difficulty.EXPERIENCED;
      this.reward = 2500.0;
    }
    this.listingName =
        ChatColor.WHITE + contractName + " - " + ContractUtils.getDifficultyScale(difficulty);
    List<String> desc = new ArrayList<>();
    desc.add("Arrive at oilfield. Find");
    desc.add("and unlock the main crate.");
    addCompletionStep("steptest", 1, desc, null, "Ride to " + location.getLocName());
    List<String> desc2 = new ArrayList<>();
    desc2.add("Guard off all enemies.");
    desc2.add("Survive until crate unlocks.");
    addCompletionStep("steptest2", 2, desc2, null, "Hold down " + location.getLocName());

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
          amount = GlobalUtils.getRandomRange(30, 63);
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
      amount *= bulkMultiplier;
      bulkOrder = true;
    }
    this.requestedAmount = amount;
    //      CHOOSE THE ITEM
    this.location = getRandomLocation();
    switch (deliveryType) {
      case FISHER -> {
        if (rareRequest) {
          if (location.getLocName().equalsIgnoreCase("Three Forks Delta")) {
            requestedItem = CustomItem.getCustomItem("Alligator").getItemStack();
          } else if (location.getLocName().equalsIgnoreCase("Pearl River")) {
            int fishOdds = GlobalUtils.getRandomNumber(101);
            if (fishOdds < 40) {
              requestedItem = CustomItem.getCustomItem("Pearl River Trout").getItemStack();
            } else {
              requestedItem = CustomItem.getCustomItem("Arctic Salmon").getItemStack();
            }
          } else if (location.getLocName().equalsIgnoreCase("Lower Guadalupe River")) {
            requestedItem = CustomItem.getCustomItem("Sunken Catfish").getItemStack();
          } else if (location.getLocName().equalsIgnoreCase("Slough Creek River")) {
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
        if (location.getLocName().equalsIgnoreCase("Red Ash Camp")) {
          requestedItem = CustomItem.getCustomItem("Spice").getItemStack();
        } else if (location.getLocName().equalsIgnoreCase("Storm Point")) {
          requestedItem = CustomItem.getCustomItem("Processed Smokeleaf").getItemStack();
        }
      }
    }

    // requestedItem.setAmount(amount);

    //      SELECT DIFFICULTY BASED OFF REQUEST AMOUNT
    if (rareRequest) {

      if (amount < 10) {
        // MEDIUM
        difficulty = Difficulty.APPRENTICE;
      } else {
        // HARD
        difficulty = Difficulty.EXPERIENCED;
      }

    } else {
      if (amount < 29) {
        // EASY
        if (deliveryType.equals(DeliveryType.DRUG_RUNNER)) {
          difficulty = Difficulty.APPRENTICE;
        } else {
          difficulty = Difficulty.ROOKIE;
        }

      } else if (amount < 44 && amount >= 29) {
        // MEDIUM
        if (deliveryType.equals(DeliveryType.DRUG_RUNNER)) {
          difficulty = Difficulty.EXPERIENCED;
        } else {
          difficulty = Difficulty.APPRENTICE;
          ;
        }
      } else {
        // HARD?
        if (deliveryType.equals(DeliveryType.DRUG_RUNNER)) {
          difficulty = Difficulty.MASTER;
        } else {
          difficulty = Difficulty.EXPERIENCED;
          ;
        }
      }
    }
    //      COMPLETION STEPS
    this.steps = new ArrayList<>();
    switch (deliveryType) {
      case FISHER -> {
        List<String> desc = new ArrayList<>();
        desc.add("Arrive at " + ChatColor.GREEN + location.getLocName());
        desc.add("Fish until you have requested amount");
        addCompletionStep(
            "steptest",
            1,
            desc,
            requestedItem,
            "Ride to " + ChatColor.GREEN + location.getLocName());
      }
      case MINER -> {
        List<String> desc = new ArrayList<>();
        desc.add("Arrive at " + ChatColor.GREEN + location.getLocName());
        desc.add("Mine ores until you have requested amount");
        addCompletionStep(
            "steptest",
            1,
            desc,
            requestedItem,
            "Ride to " + ChatColor.GREEN + location.getLocName());
      }
      case DRUG_RUNNER -> {
        List<String> desc = new ArrayList<>();
        desc.add(
            "Arrive at "
                + ChatColor.GREEN
                + FrontierLocation.getLocation("Smokeleaf Field").getLocName());
        desc.add("Harvest unprocessed drugs from field");
        addCompletionStep(
            "steptest",
            1,
            desc,
            requestedItem,
            "Ride to "
                + ChatColor.GREEN
                + FrontierLocation.getLocation("Smokeleaf Field").getLocName());
        desc = new ArrayList<>();
        desc.add("Arrive at " + ChatColor.GREEN + location.getLocName());
        desc.add("Process the drugs at a processor inside");
        addCompletionStep(
            "steptest",
            2,
            desc,
            requestedItem,
            "Ride to " + ChatColor.GREEN + location.getLocName());
      }
    }
    List<String> desc = new ArrayList<>();
    desc.add("Return to any " + ChatColor.YELLOW + "Contractor");
    desc.add("for reward.");
    addCompletionStep("steptest", 3, desc, null, "Ride to any town");
    System.out.println(requestedItem.getItemMeta().getDisplayName());

    this.reward =
        amount
            * CustomItem.getCustomItem(
                    ChatColor.stripColor(requestedItem.getItemMeta().getDisplayName()))
                .getSalePrice();
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
    // Check if player gets a PLAYER or NPC contract
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
        // Make a list of online players OTHER THAN the person taking the contract
        List<Player> playerPool = new ArrayList<>();
        for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
          // Avoid contractee, add others
          if (!p2.getUniqueId().toString().equalsIgnoreCase(p.getUniqueId().toString())) {
            playerPool.add(p2);
          }
        }
        // Check if we could find any other online players
        if (playerPool.size() > 0) {
          // FOUND PLAYERS!
          int playerIndex = GlobalUtils.getRandomNumber(playerPool.size());
          target = playerPool.get(playerIndex);
        } else {
          // NO players
          // Try again
          generateNewBountyHunter(p);
        }
        String targetName = target.getDisplayName();
        p.sendMessage(targetName);
      }
      case NPC -> {}
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
    if (difficulty == Difficulty.MASTER) {
      multiplier = 1.5;
    }
    int val = (int) Math.round(reinforcementCount * multiplier);
    return val;
  }

  public void startContract(Player p) {
    switch (this.contractType) {
      case OIL_FIELD -> {
        p.getInventory().addItem(CustomItem.getCustomItem("Crate Key").getItemStack());
      }
    }
  }
}
