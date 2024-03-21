package droidco.west3.ironsight.Bandit;

import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.Location.Location;
import droidco.west3.ironsight.Globals.Utils.BanditUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bandit
{
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
    private int pceContractXp;
    private int cmbtContractXp;
    private int pceContractLvl;
    private int cmbtContractLvl;
    private int contractorTitle;
    private long jailStartTime;
    private String roleTitle;
    private Player onlinePlayer;
    private Location currentLocation;

    private Location trackingLocation;

    private static boolean isTrackingLocation;
    private static boolean isTrackingPlayer;
    private Contract rookieContract;
    private Contract apprenticeContract;
    private Contract experiencedContract;
    private Contract activeContract;
    private static List<Bandit> playerList = new ArrayList<>();
    //private final IronSight plugin;

    private int wantedKills;
    private static HashMap<String, Bandit> bandits = new HashMap<>();
    //private List<ironHorse> horses;

    public Bandit(String pId)
    {
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

        this.bounty = 0;
        this.wantedKills = 0;
        this.pceContractXp = 0;
        this.pceContractLvl = 0;
        this.cmbtContractLvl =0;
        this.cmbtContractXp = 0;

        playerList.add(this);
        bandits.put(pId,this);
        //this.plugin = plugin;
        this.onlinePlayer = null;
    }
    public Bandit(String pId, double wallet, double bank, boolean isBleeding, boolean isJailed,
                  boolean isWanted, boolean isCombatBlocked, boolean brokenLegs, int bounty, int
                              wantedKills, int pceContractLvl, int pceContractXp, int cmbtContractLvl, int cmbtContractXp,
                  long jailStartTime)
    {
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

        this.bounty = bounty;
        this.jailStartTime = jailStartTime;
        this.wantedKills = wantedKills;
        this.pceContractLvl = pceContractLvl;
        this.pceContractXp = pceContractXp;
        this.cmbtContractLvl = cmbtContractLvl;
        this.cmbtContractXp = cmbtContractXp;

        playerList.add(this);
        bandits.put(pId,this);
    }
    public void setOnlinePlayer(Player p)
    {
        if(pId.equalsIgnoreCase(p.getUniqueId().toString())){
            onlinePlayer = p;
        }
    }
    public static List<Bandit> getPlayerList()
    {
        return playerList;
    }
    public static Bandit getPlayer(Player p){
        if(bandits.containsKey(p.getUniqueId().toString())){
            return bandits.get(p.getUniqueId().toString());
        }
        return null;
    }

    public String getRoleTitle() {
        return roleTitle;
    }
    public String getTitle(){
        return getContractorTitle().equalsIgnoreCase("") ? roleTitle : getContractorTitle()+" "+roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public String getContractorTitle() {
        switch(contractorTitle){
            case 1:
                return "Cowboy";
            case 2:
                return "Tracker";
            case 3:
                return "Raider";
            case 4:
                return "Miner";
            case 5:
                return "Medic";
            case 6:
                return "Explorer";
        }
        return "";
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

    public void setContractorTitle(int contractorTitle) {
        this.contractorTitle = contractorTitle;
    }

    public Contract getActiveContract() {
        return activeContract;
    }

    public void setActiveContract(Contract activeContract) {
        this.activeContract = activeContract;
    }

    public void setRookieContract(Contract rookieContract) {
        this.rookieContract = rookieContract;
    }

    public boolean isDoingContract() {
        return doingContract;
    }

    public boolean isEscaping() {
        return escaping;
    }

    public void setEscaping(boolean escaping) {
        this.escaping = escaping;
    }

    public void setDoingContract(boolean doingContract) {
        this.doingContract = doingContract;
    }

    public Player getTargetedPlayer() {
        return targetedPlayer;
    }

    public void setTargetedPlayer(Player targetedPlayer) {
        this.targetedPlayer = targetedPlayer;
    }

    public void setApprenticeContract(Contract apprenticeContract) {
        this.apprenticeContract = apprenticeContract;
    }
    public void setExperiencedContract(Contract experiencedContract) {
        this.experiencedContract = experiencedContract;
    }
    public void setCurrentLocation(Location locName){
        this.currentLocation = locName;
    }
    public Location getCurrentLocation()
    {
        return this.currentLocation;
    }
    public void updateBank(double deposit){
        this.bank += deposit;
    }
    public void updateWallet(double deposit){
        this.wallet += deposit;
    }
    public void updateBounty(int increase){ this.bounty += increase; }

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

    public boolean isTrackingLocation() {
        return isTrackingLocation;
    }

    public boolean isTrackingPlayer() {
        return isTrackingPlayer;
    }

    public static void setIsTrackingPlayer(boolean trackingPlayer) {
        isTrackingPlayer = trackingPlayer;
    }

    public static void setIsTrackingLocation(boolean trackingLocation) {
        isTrackingLocation = trackingLocation;
    }

    public void setWanted(boolean wanted) {
        isWanted = wanted;
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

    public int getPceContractXp() {
        return pceContractXp;
    }

    public void setPceContractXp(int pceContractXp) {
        this.pceContractXp = pceContractXp;
    }

    public Location getTrackingLocation() {
        return trackingLocation;
    }

    public void setTrackingLocation(Location trackingLocation) {
        Bandit.setIsTrackingPlayer(false);
        this.trackingLocation = trackingLocation;
        Bandit.setIsTrackingLocation(true);
    }

    public int getCmbtContractXp() {
        return cmbtContractXp;
    }

    public void setCmbtContractXp(int cmbtContractXp) {
        this.cmbtContractXp = cmbtContractXp;
    }

    public int getPceContractLvl() {
        return pceContractLvl;
    }

    public void setPceContractLvl(int pceContractLvl) {
        this.pceContractLvl = pceContractLvl;
    }

    public int getCmbtContractLvl() {
        return cmbtContractLvl;
    }

    public void setCmbtContractLvl(int cmbtContractLvl) {
        this.cmbtContractLvl = cmbtContractLvl;
    }

    public Player getOnlinePlayer() {
        return onlinePlayer;
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

    public Contract getApprenticeContract() {
        return apprenticeContract;
    }

    public Contract getExperiencedContract() {
        return experiencedContract;
    }
}
