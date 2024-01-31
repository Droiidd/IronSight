package droidco.west3.ironsight.Utils;

import droidco.west3.ironsight.Objects.Player.IronPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class PlayerUtils
{
    public static void displayBasicStats(IronPlayer iP, Player p)
    {
        p.sendMessage("Iron Sight Player Stats:");
        p.sendMessage("============");
        p.sendMessage("Bleeding: "+iP.isBleeding());
        p.sendMessage("Broken Legs: "+iP.isBrokenLegs());
        p.sendMessage("Bounty: "+iP.getBounty());
        p.sendMessage("Bank: "+iP.getBank());
        p.sendMessage("Wallet: "+iP.getWallet());
        p.sendMessage("EXPERIENCE INFO: ");
        p.sendMessage("Combat Level: "+iP.getCmbtContractLvl());
        p.sendMessage("Peacemaker Level: "+iP.getPceContractLvl());
        p.sendMessage("Combat XP: "+iP.getCmbtContractXp());
        p.sendMessage("Peacemaker XP: "+iP.getPceContractXp());
        p.sendMessage("Wanted Kills: "+iP.getWantedKills());

    }
    public static String getPlayerRoleTitle()
    {
        return ChatColor.RED+"Bandit ";
    }
    public static void loadScoreBoard(Player p, IronPlayer iPlayer, int secCombatBlock, int minWanted, int secWanted)
    {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard sb = manager.getNewScoreboard();

        //Set titles to blank strings if they do not have one selected.
        String playerTitle = iPlayer.getRoleTitle() == null ? "" : iPlayer.getRoleTitle();
        String contractTitle = iPlayer.getContractorTitle() == null ? "" : iPlayer.getContractorTitle();
        //Do the same for teams

        //Get banking info
        String wallet = ChatColor.GREEN+"Wallet: "+ChatColor.RESET+iPlayer.getWallet()+"g";
        String bank = ChatColor.GREEN+"Bank: "+ChatColor.RESET+iPlayer.getBank()+"g";
        String bounty= ChatColor.RED+"Bounty: "+ChatColor.RESET+iPlayer.getBounty();

        //Wanted timer
        String wanted = ChatColor.DARK_RED+"< Wanted > " +ChatColor.RESET
                + minWanted +":"+secWanted;
        //Combat blocked timer
        String combatBlock = ChatColor.DARK_PURPLE + "Combat Blocked "+ChatColor.RESET+"0:"+secCombatBlock;

        String seperator = "---------------";

        Objective objective = sb.registerNewObjective("IronSight", ChatColor.GRAY + String.valueOf(ChatColor.BOLD)+"Iron Sight");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score wantedDis = objective.getScore(wanted);
        Score bankDis = objective.getScore(bank);
        Score walletDis = objective.getScore(wallet);
        Score combatDis = objective.getScore(combatBlock);
        Score seperatorDis = objective.getScore(seperator);
        Score bountyDis = objective.getScore(bounty);

        walletDis.setScore(2);
        bankDis.setScore(3);
        bountyDis.setScore(4);
        seperatorDis.setScore(5);
        if(iPlayer.isCombatBlocked()){
            combatDis.setScore(6);
        }
        p.setScoreboard(sb);
    }

}

