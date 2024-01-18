package droidco.west3.ironsight.Player;

import droidco.west3.ironsight.IronSightCore;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IronPlayer
{
    private String pId;
    private double wallet;
    private double bank;
    private boolean isBleeding;
    private boolean brokenLegs;
    private boolean isWanted;
    private boolean isJailed;
    private boolean isCombatBlocked;
    //private Sheriff sheriffType;
    //private Team team;
    private int bounty;
    private int pceContractXp;
    private int cmbtContractXp;
    private int pceContractLvl;
    private int cmbtContractLvl;
    private Player onlinePlayer;
    private static List<IronPlayer> playerList = new ArrayList<>();
    private final IronSightCore plugin;

    private int wantedKills;
    private static HashMap<String, IronPlayer> ironPlayers = new HashMap<>();
    //private List<ironHorse> horses;

    public IronPlayer(String pId, IronSightCore plugin)
    {
        this.pId = pId;
        this.wallet = 0.0;
        this.bank = 1000.0;
        this.isBleeding = false;
        this.isJailed = false;
        this.isWanted = false;
        this.isCombatBlocked = false;
        this.brokenLegs = false;

        this.bounty = 0;
        this.wantedKills = 0;
        this.pceContractXp = 0;
        this.pceContractLvl = 0;
        this.cmbtContractLvl =0;
        this.cmbtContractXp = 0;

        playerList.add(this);
        ironPlayers.put(pId,this);
        this.plugin = plugin;
        this.onlinePlayer = null;
    }
    public void setOnlinePlayer(Player p)
    {
        if(pId.equalsIgnoreCase(p.getUniqueId().toString())){
            onlinePlayer = p;
        }
    }
    public void loadPlayer(String pId, double wallet, double bank, boolean isBleeding, boolean isJailed,
                           boolean isWanted, boolean isCombatBlocked, boolean brokenLegs, int bounty, int
                                   wantedKills, int pceContractLvl, int pceContractXp, int cmbtContractLvl, int cmbtContractXp)
    {
        this.pId = pId;
        this.wallet = wallet;
        this.bank = bank;
        this.isBleeding = isBleeding;
        this.isJailed = isJailed;
        this.isWanted = isWanted;
        this.isCombatBlocked = isCombatBlocked;
        this.brokenLegs = brokenLegs;

        this.bounty = bounty;
        this.wantedKills = wantedKills;
        this.pceContractLvl = pceContractLvl;
        this.pceContractXp = pceContractXp;
        this.cmbtContractLvl = cmbtContractLvl;
        this.cmbtContractXp = cmbtContractXp;

    }
    public static List<IronPlayer> getPlayerList()
    {
        return playerList;
    }
    public static IronPlayer getPlayer(Player p){
        if(p.getUniqueId().toString().equalsIgnoreCase(this.onlinePlayer.getUniqueId().toString())){
            return this;
        }
        else{
            return null;
        }
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

    public boolean isJailed() {
        return isJailed;
    }

    public void setJailed(boolean jailed) {
        isJailed = jailed;
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

    }
