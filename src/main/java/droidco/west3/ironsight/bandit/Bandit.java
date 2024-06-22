package droidco.west3.ironsight.bandit;

import droidco.west3.ironsight.contracts.Contract;
import droidco.west3.ironsight.contracts.Utils.ContractType;
import droidco.west3.ironsight.contracts.Utils.DeliveryType;
import droidco.west3.ironsight.frontierlocation.FrontierLocation;
import droidco.west3.ironsight.globals.Utils.BanditUtils;
import droidco.west3.ironsight.horse.FrontierHorse;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bandit {
    private static final List<Bandit> playerList = new ArrayList<>();
    private static final HashMap<String, Bandit> bandits = new HashMap<>();
    private final int maxHorseLimit = 3;
    private String pId;
    private double wallet;
    private double bank;
    private boolean isBleeding;
    private boolean brokenLegs;
    private boolean isWanted;
    private boolean isJailed;
    private boolean isJailedFlag;
    private boolean isCombatBlocked;
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
    private Location trackingLocation;
    private boolean isTrackingLocation;
    private boolean isTrackingPlayer;
    private boolean summoningHorse;
    private FrontierHorse horseBeingSummoned;
    private boolean isDepositing;
    private boolean isWithdrawing;
    private FrontierLocation currentFrontierLocation;
    private Contract rookieContract;
    private Contract apprenticeContract;
    private Contract experiencedContract;
    private Contract activeContract;
    private boolean jailRespawn;
    private List<Contract> contracts = new ArrayList<>();
    private List<FrontierHorse> horses = new ArrayList<>();
    private int vaultSize = 0;
    private int vaultLevel = 0;

    //private final IronSight plugin;
    private List<ItemStack> itemVault = new ArrayList<>(vaultSize);
    private int wantedKills;
    private FrontierLocation trackingFrontierLocation;
    private boolean isTrackingNPC;
    private String trackedNPC;
    //private List<ironHorse> horses;

    public Bandit(String pId) {
        this.pId = pId;
        this.wallet = 0.0;
        this.bank = 1000.0;
        this.isBleeding = false;
        this.isJailed = false;
        this.isWanted = false;
        this.isCombatBlocked = false;
        this.brokenLegs = false;
        this.isJailedFlag = false;
        this.respawning = false;
        this.roleTitle = BanditUtils.getPlayerRoleTitle();
        this.summoningHorse = false;
        this.bounty = 0;
        this.wantedKills = 0;
        this.contractorLvl = 0;
        this.contractorXp = 0;
        this.vaultSize = 0;
        this.vaultLevel = 0;

        playerList.add(this);
        bandits.put(pId, this);
        //this.plugin = plugin;
        this.onlinePlayer = null;
    }

    public Bandit(String pId, double wallet, double bank, boolean isBleeding, boolean isJailed,
                  boolean isWanted, boolean isCombatBlocked, boolean brokenLegs, int bounty, int
                          wantedKills, int contractorLvl, int contractorXp,
                  long jailStartTime, int contractorTitle, int vaultSize, int vaultLevel) {
        this.doingContract = false;
        this.pId = pId;
        this.wallet = wallet;
        this.bank = bank;
        this.isBleeding = isBleeding;
        this.isJailed = isJailed;
        this.isWanted = isWanted;
        this.isCombatBlocked = isCombatBlocked;
        this.brokenLegs = brokenLegs;
        this.isJailedFlag = false;
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
        bandits.put(pId, this);
    }

    public static List<Bandit> getPlayerList() {
        return playerList;
    }

    public static Bandit getPlayer(Player p) {
        if (bandits.containsKey(p.getUniqueId().toString())) {
            return bandits.get(p.getUniqueId().toString());
        }
        return null;
    }

    public List<ItemStack> getItemVault() {
        return itemVault;
    }

    public void setItemVault(List<ItemStack> itemVault) {
        this.itemVault = itemVault;
    }

    public int getVaultSize() {
        return vaultSize;
    }

    public void setVaultSize(int vaultSize) {
        this.vaultSize = vaultSize;
    }

    public void loadContracts() {
        List<FrontierLocation> testLocs = new ArrayList<>();
        testLocs.add(FrontierLocation.getLocation("Pearl River"));
        testLocs.add(FrontierLocation.getLocation("Three Forks Delta"));
        testLocs.add(FrontierLocation.getLocation("Lower Guadalupe River"));
        testLocs.add(FrontierLocation.getLocation("Slough Creek River"));
        Contract testC1 = new Contract(ContractType.Delivery, testLocs, 1, DeliveryType.FISHER);
        Contract testC2 = new Contract(ContractType.Delivery, testLocs, 2, DeliveryType.FISHER);
        List<FrontierLocation> miningLoc = new ArrayList<>();
        miningLoc.add(FrontierLocation.getLocation("Black Spur Mines"));
        Contract miner = new Contract(ContractType.Delivery, miningLoc, 1, DeliveryType.MINER);
        Contract miner2 = new Contract(ContractType.Delivery, miningLoc, 1, DeliveryType.MINER);

        List<FrontierLocation> smokeLocs = new ArrayList<>();
        smokeLocs.add(FrontierLocation.getLocation("Storm Point"));
        Contract smokeRunner = new Contract(ContractType.Delivery, smokeLocs, 2, DeliveryType.DRUG_RUNNER);

        List<FrontierLocation> spiceLoc = new ArrayList<>();
        spiceLoc.add(FrontierLocation.getLocation("Red Ash Camp"));
        Contract spiceRunner = new Contract(ContractType.Delivery, spiceLoc, 2, DeliveryType.DRUG_RUNNER);
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

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public String getTitle() {
        return BanditUtils.getContractorTitle(this).equalsIgnoreCase("") ? roleTitle : BanditUtils.getContractorTitle(this) + " " + roleTitle;
    }

    public FrontierHorse getHorseBeingSummoned() {
        return horseBeingSummoned;
    }

    public void setHorseBeingSummoned(FrontierHorse horseBeingSummoned) {
        this.horseBeingSummoned = horseBeingSummoned;
    }

    public int getContractorTitle() {
        return contractorTitle;
    }

    public void setContractorTitle(int contractorTitle) {
        this.contractorTitle = contractorTitle;
    }

    public void updateContractorXp(int xp) {
        this.contractorXp += xp;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public int getMaxHorseLimit() {
        return maxHorseLimit;
    }

    public boolean isSummoningHorse() {
        return summoningHorse;
    }

    public void setSummoningHorse(boolean summoningHorse) {
        this.summoningHorse = summoningHorse;
    }

    public long getJailStartTime() {
        return jailStartTime;
    }

    public void setJailStartTime(long jailStartTime) {
        this.jailStartTime = jailStartTime;
    }

    public boolean isRespawning() {
        return respawning;
    }

    public void setRespawning(boolean respawning) {
        this.respawning = respawning;
    }

    public Contract getActiveContract() {
        return activeContract;
    }

    public void setActiveContract(Contract activeContract) {
        this.activeContract = activeContract;
    }

    public boolean isDoingContract() {
        return doingContract;
    }

    public void setDoingContract(boolean doingContract) {
        this.doingContract = doingContract;
    }

    public boolean isEscaping() {
        return escaping;
    }

    public void setEscaping(boolean escaping) {
        this.escaping = escaping;
    }

    public boolean isDepositing() {
        return isDepositing;
    }

    public void setDepositing(boolean depositing) {
        isDepositing = depositing;
    }

    public boolean isWithdrawing() {
        return isWithdrawing;
    }

    public void setWithdrawing(boolean withdrawing) {
        isWithdrawing = withdrawing;
    }

    public List<FrontierHorse> getHorses() {
        return horses;
    }

    public void setHorses(List<FrontierHorse> horses) {
        this.horses = horses;
    }

    public Player getTargetedPlayer() {
        return targetedPlayer;
    }

    public void setTargetedPlayer(Player targetedPlayer) {
        this.targetedPlayer = targetedPlayer;
    }

    public FrontierLocation getCurrentLocation() {
        return this.currentFrontierLocation;
    }

    public void setCurrentLocation(FrontierLocation locName) {
        this.currentFrontierLocation = locName;
    }

    public void updateBank(double deposit) {
        this.bank += deposit;
    }

    public void updateWallet(double deposit) {
        this.wallet += deposit;
    }

    public void updateBounty(int increase) {
        this.bounty += increase;
    }

    public boolean isJailedFlag() {
        return isJailedFlag;
    }

    public void setJailedFlag(boolean jailedFlag) {
        isJailedFlag = jailedFlag;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public double getBank() {
        return bank;
    }

    public void setBank(double bank) {
        this.bank = bank;
    }

    public boolean isBleeding() {
        return isBleeding;
    }

    public void setBleeding(boolean bleeding) {
        isBleeding = bleeding;
    }

    public boolean isBrokenLegs() {
        return brokenLegs;
    }

    public void setBrokenLegs(boolean brokenLegs) {
        this.brokenLegs = brokenLegs;
    }

    public boolean isWanted() {
        return isWanted;
    }

    public void setWanted(boolean wanted) {
        isWanted = wanted;
    }

    public boolean isTrackingLocation() {
        return isTrackingLocation;
    }

    public boolean isTrackingPlayer() {
        return isTrackingPlayer;
    }

    public void setIsTrackingPlayer(boolean trackingPlayer) {
        isTrackingPlayer = trackingPlayer;
    }

    public void setIsTrackingLocation(boolean trackingLocation) {
        isTrackingLocation = trackingLocation;
    }

    public boolean isJailed() {
        return isJailed;
    }

    public void setJailed(boolean jailed) {
        isJailed = jailed;
    }

    public boolean isCombatBlockFlag() {
        return combatBlockFlag;
    }

    public void setCombatBlockFlag(boolean combatBlockFlag) {
        this.combatBlockFlag = combatBlockFlag;
    }

    public boolean isCombatBlocked() {
        return isCombatBlocked;
    }

    public void setCombatBlocked(boolean combatBlocked) {
        isCombatBlocked = combatBlocked;
    }

    public int getBounty() {
        return bounty;
    }

    public void setBounty(int bounty) {
        this.bounty = bounty;
    }

    public Location getTrackingLocation() {
        return trackingLocation;
    }

    public void setTrackingLocation(Location trackingLocation) {
        setIsTrackingPlayer(false);
        this.trackingLocation = trackingLocation;
        setIsTrackingLocation(true);
    }

    public int getContractorLvl() {
        return contractorLvl;
    }

    public void setContractorLvl(int contractorLvl) {
        this.contractorLvl = contractorLvl;
    }

    public int getContractorXp() {
        return contractorXp;
    }

    public void setContractorXp(int contractorXp) {
        this.contractorXp = contractorXp;
    }

    public Player getOnlinePlayer() {
        return onlinePlayer;
    }

    public void setOnlinePlayer(Player p) {
        if (pId.equalsIgnoreCase(p.getUniqueId().toString())) {
            onlinePlayer = p;
        }
    }

    public int getWantedKills() {
        return wantedKills;
    }

    public void setWantedKills(int wantedKills) {
        this.wantedKills = wantedKills;
    }

    public Contract getRookieContract() {
        return rookieContract;
    }

    public void setRookieContract(Contract rookieContract) {
        this.rookieContract = rookieContract;
    }

    public Contract getApprenticeContract() {
        return apprenticeContract;
    }

    public void setApprenticeContract(Contract apprenticeContract) {
        this.apprenticeContract = apprenticeContract;
    }

    public Contract getExperiencedContract() {
        return experiencedContract;
    }

    public void setExperiencedContract(Contract experiencedContract) {
        this.experiencedContract = experiencedContract;
    }

    public boolean isJailRespawn() {
        return jailRespawn;
    }

    public void setJailRespawn(boolean jailRespawn) {
        this.jailRespawn = jailRespawn;
    }

    public int getVaultLevel() {
        return vaultLevel;
    }

    public void setVaultLevel(int vaultLevel) {
        this.vaultLevel = vaultLevel;
    }

    public FrontierLocation getTrackingFrontierLocation() {
        return trackingFrontierLocation;
    }

    public void setTrackingFrontierLocation(FrontierLocation trackingFrontierLocation) {
        this.trackingFrontierLocation = trackingFrontierLocation;
    }

    public boolean isTrackingNPC() {
        return isTrackingNPC;
    }

    public void setIsTrackingNPC(boolean trackingNPC) {
        isTrackingNPC = trackingNPC;
    }

    public String getTrackedNPC() {
        return trackedNPC;
    }

    public void setTrackedNPC(String trackedNPC) {
        this.trackedNPC = trackedNPC;
    }
}
