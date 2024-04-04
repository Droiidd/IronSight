package droidco.west3.ironsight.Globals.Utils;

import droidco.west3.ironsight.Bandit.Bandit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

public class BanditUtils {
    public static void displayBasicStats(Bandit b, Player p) {
        p.sendMessage("Iron Sight Player Stats:");
        p.sendMessage("============");
        p.sendMessage("Bleeding: " + b.isBleeding());
        p.sendMessage("Broken Legs: " + b.isBrokenLegs());
        p.sendMessage("In Prison: " + b.isJailed());
        p.sendMessage("EXPERIENCE INFO: ");
        p.sendMessage("Contractor Lvl " + b.getContractorLvl());
        p.sendMessage("Contractor XP: " + b.getContractorXp());
        p.sendMessage("Wanted Kills: " + b.getWantedKills());

    }

    public static void releasePrisoner(Player p, Bandit b) {
        b.setBounty(0);
        b.setJailed(false);
        b.setEscaping(false);
        b.setJailedFlag(false);
        p.sendTitle(ChatColor.GRAY + "You are released from" + ChatColor.RED + " jail!", "Good luck...", 1, 2, 1);
        b.setRespawning(true);
    }

    public static String getPlayerRoleTitle() {
        return ChatColor.RED + "Bandit ";
    }

    public static void loadScoreBoard(Player p, Bandit b, int secCombatBlock, int minWanted, int secWanted) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard sb = manager.getNewScoreboard();

        //Set titles to blank strings if they do not have one selected.
        String playerTitle = b.getRoleTitle() == null ? "" : b.getRoleTitle();
        String contractTitle = BanditUtils.getContractorTitle(b) == null ? "" : BanditUtils.getContractorTitle(b);
        //Do the same for teams

        //Get banking info
        String wallet = ChatColor.GREEN + "Wallet: " + ChatColor.RESET + b.getWallet() + ChatColor.GOLD + "g";
        String bank = ChatColor.GREEN + "Bank: " + ChatColor.RESET + b.getBank() + ChatColor.GOLD + "g";
        String bounty = ChatColor.RED + "Bounty: " + ChatColor.RESET + b.getBounty();

        //Wanted timer
        List<Integer> singleDigits = new ArrayList<>();
        for (int x = 0; x < 10; x++) {
            singleDigits.add(x);
        }
        String secWantedStr = singleDigits.contains(secWanted) ? "0" + String.valueOf(secWanted) : String.valueOf(secWanted);
        String wanted = ChatColor.DARK_RED + "< Wanted > " + ChatColor.RESET
                + minWanted + ":" + secWantedStr;
        //Combat blocked timer
        String secCmbtStr = singleDigits.contains(secCombatBlock) ? "0" + String.valueOf(secCombatBlock) : String.valueOf(secCombatBlock);
        String combatBlock = ChatColor.DARK_PURPLE + "Combat Blocked " + ChatColor.RESET + "0:" + secCmbtStr;

        String seperator = "---------------";

        Objective objective = sb.registerNewObjective(ChatColor.DARK_RED + String.valueOf(ChatColor.BOLD) + "IronSight", ChatColor.GRAY + String.valueOf(ChatColor.BOLD) + "Iron Sight");
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
        if (b.isCombatBlocked()) {
            combatDis.setScore(6);
        }
        if (b.isWanted()) {
            wantedDis.setScore(7);
        }
        p.setScoreboard(sb);
    }

    public static Player getNearest(Player p, Double range) {
        double distance = Double.POSITIVE_INFINITY;
        Player target = null;

        for (Entity e : p.getNearbyEntities(4096, 4096, 4096)) {
            if (!(e instanceof Player)) {
                continue;
            } else {
                target = (Player) e;
                double distanceTo = p.getLocation().distance(target.getLocation());
                if (distanceTo > distance) {
                    continue;
                } else {
                    Bandit b = Bandit.getPlayer(target);
                    if (b.isWanted()) {
                        return target;
                    }
                }
                distance = distanceTo;

            }
        }

        return null;

    }
    public static String getContractorTitle(Bandit b) {
        switch(b.getContractorTitle()){
            case 1:
                return String.valueOf(ChatColor.GRAY)+"Miner";
            case 2:
                return String.valueOf(ChatColor.GRAY)+"Cowboy";
            case 3:
                return String.valueOf(ChatColor.DARK_GREEN)+"Tracker";
            case 4:
                return String.valueOf(ChatColor.GREEN)+"Medic";
            case 5:
                return String.valueOf(ChatColor.DARK_RED)+ "Raider";
            case 6:
                return String.valueOf(ChatColor.YELLOW)+ "Explorer";
        }
        return "";
    }
}

