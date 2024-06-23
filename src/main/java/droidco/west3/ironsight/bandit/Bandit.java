package droidco.west3.ironsight.bandit;

import droidco.west3.ironsight.contracts.Contract;
import droidco.west3.ironsight.contracts.utils.ContractType;
import droidco.west3.ironsight.contracts.utils.DeliveryType;
import droidco.west3.ironsight.frontierlocation.FrontierLocation;
import droidco.west3.ironsight.globals.utils.BanditUtils;
import droidco.west3.ironsight.horse.FrontierHorse;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter @Setter
public class Bandit
{
    private String pId;
    private double wallet;
    private double bank;
    private final int maxHorseLimit = 3;
    private boolean bleeding;
    private boolean brokenLegs;
    private boolean wanted;
    private boolean jailed;
    private boolean jailedFlag;
    private boolean combatBlocked;
    private boolean combatBlockFlag;
    private boolean doingContract;
    private boolean respawning;
    private boolean escaping;
    //private Sheriff sheriffType;
    //private Team team;
    private int bounty;

    private Player targetedPlayer;
    private int contractorLvl;
    private int contractorXp;
    private int contractorTitle;
    private long jailStartTime;
    private String roleTitle;
    private Player onlinePlayer;
    private Location trackedLocation;
    @Setter
    private boolean isTrackingLocation;
    private boolean trackingPlayer;
    private boolean summoningHorse;
    private FrontierHorse horseBeingSummoned;
    private boolean depositing;
    private boolean withdrawing;
    private FrontierLocation currentLocation;
    private Contract rookieContract;
    private Contract apprenticeContract;
    private Contract experiencedContract;
    private Contract activeContract;
    private boolean jailRespawn;
    private static List<Bandit> playerList = new ArrayList<>();
    private List<Contract> contracts = new ArrayList<>();
    private List<FrontierHorse> horses = new ArrayList<>();

    private int vaultSize = 0;
    private int vaultLevel = 0;
    private List<ItemStack> itemVault = new ArrayList<>(vaultSize);

    //private final IronSight plugin;

    private int wantedKills;

    private FrontierLocation trackingFrontierLocation;

    private boolean trackingNPC;

    private String trackedNPC;

    private static HashMap<String, Bandit> bandits = new HashMap<>();
    private static List<Contract> activeContracts = new ArrayList<>();
    //private List<ironHorse> horses;

    public Bandit(String pId)
    {
        this.pId = pId;
        this.wallet = 0.0;
        this.bank = 1000.0;
        this.bleeding = false;
        this.jailed = false;
        this.wanted = false;
        this.combatBlocked = false;
        this.brokenLegs = false;
        this.jailedFlag = false;
        this.respawning = false;
        this.roleTitle = BanditUtils.getPlayerRoleTitle();
        this.summoningHorse = false;
        this.bounty = 0;
        this.wantedKills = 0;
        this.contractorLvl =0;
        this.contractorXp =0;
        this.vaultSize=0;
        this.vaultLevel=0;

        playerList.add(this);
        bandits.put(pId,this);
        //this.plugin = plugin;
        this.onlinePlayer = null;
    }

    public Bandit(String pId, double wallet, double bank, boolean isBleeding, boolean jailed,
                  boolean isWanted, boolean combatBlocked, boolean brokenLegs, int bounty, int
                              wantedKills, int contractorLvl, int contractorXp,
                  long jailStartTime, int contractorTitle, int vaultSize, int vaultLevel)
    {
        this.doingContract = false;
        this.pId = pId;
        this.wallet = wallet;
        this.bank = bank;
        this.bleeding = isBleeding;
        this.jailed = jailed;
        this.wanted = isWanted;
        this.combatBlocked = combatBlocked;
        this.brokenLegs = brokenLegs;
        this.jailedFlag = false;
        this.respawning = false;
        this.roleTitle = BanditUtils.getPlayerRoleTitle();
        this.summoningHorse = false;
        this.bounty = bounty;
        this.jailStartTime = jailStartTime;
        this.wantedKills = wantedKills;
        this.contractorXp = contractorXp;
        this.contractorLvl = contractorLvl;
        this.contractorTitle = contractorTitle;
        this.vaultSize = vaultSize;
        this.vaultLevel = vaultLevel;

        playerList.add(this);
        bandits.put(pId,this);
    }
    public void loadContracts()
    {
        List<FrontierLocation> testLocs = new ArrayList<>();
        testLocs.add(FrontierLocation.getLocation("Pearl River"));
        testLocs.add(FrontierLocation.getLocation("Three Forks Delta"));
        testLocs.add(FrontierLocation.getLocation("Lower Guadalupe River"));
        testLocs.add(FrontierLocation.getLocation("Slough Creek River"));
        Contract testC1 = new Contract( ContractType.DELIVERY, testLocs ,1, DeliveryType.FISHER);
        Contract testC2 = new Contract( ContractType.DELIVERY, testLocs ,2, DeliveryType.FISHER);
        List<FrontierLocation> miningLoc = new ArrayList<>();
        miningLoc.add(FrontierLocation.getLocation("Black Spur Mines"));
        Contract miner = new Contract(ContractType.DELIVERY, miningLoc, 1,DeliveryType.MINER);
        Contract miner2 = new Contract(ContractType.DELIVERY, miningLoc, 1,DeliveryType.MINER);

        List<FrontierLocation> smokeLocs = new ArrayList<>();
        smokeLocs.add(FrontierLocation.getLocation("Storm Point"));
        Contract smokeRunner = new Contract(ContractType.DELIVERY, smokeLocs ,2, DeliveryType.DRUG_RUNNER);

        List<FrontierLocation> spiceLoc = new ArrayList<>();
        spiceLoc.add(FrontierLocation.getLocation("Red Ash Camp"));
        Contract spiceRunner = new Contract(ContractType.DELIVERY, spiceLoc ,2, DeliveryType.DRUG_RUNNER);
//        List<FrontierLocation> test3Locs = new ArrayList<>();
//        test3Locs.add(FrontierLocation.getLocation("North Moraine Oil Field"));
//        Contract testC3 = new Contract(ContractType.OilField , test3Locs ,1);

        contracts.add(testC1);
        contracts.add(testC2);
        //contracts.add(testC3);
        contracts.add(smokeRunner);
        contracts.add(spiceRunner);
        contracts.add(miner);
        contracts.add(miner2);

    }
    public void setOnlinePlayer(Player p)
    {
        if(pId.equalsIgnoreCase(p.getUniqueId().toString())){
            onlinePlayer = p;
        }
    }

    public static Bandit getPlayer(Player p){
        if(bandits.containsKey(p.getUniqueId().toString())){
            return bandits.get(p.getUniqueId().toString());
        }
        return null;
    }
    public static Bandit getPlayerById(String uuid){
        if(bandits.containsKey(uuid)){
            return bandits.get(uuid);
        }
        return null;
    }

    public String getTitle(){
        return BanditUtils.getContractorTitle(this).equalsIgnoreCase("") ? roleTitle : BanditUtils.getContractorTitle(this)+" "+roleTitle;
    }

    public void updateBank(double deposit){
        this.bank += deposit;
    }
    public void updateWallet(double deposit){
        this.wallet += deposit;
    }
    public void updateBounty(int increase){ this.bounty += increase; }
    public void updateContractorXp(int xp) {
        this.contractorXp += xp;
    }

    public void setTrackedLocation(Location trackedLocation) {
        setTrackingPlayer(false);
        this.trackedLocation = trackedLocation;
        setTrackingLocation(true);
    }

    public void addActiveContract(Contract contract){
        activeContracts.add(contract);
    }
    public void removeActiveContract(Contract contract){
        activeContracts.remove(contract);
    }
}

