package droidco.west3.ironsight.Globals.Utils;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Items.CustomItem;
import droidco.west3.ironsight.Items.ItemIcon;
import droidco.west3.ironsight.Items.Potions.CustomPotion;
import droidco.west3.ironsight.NPC.NPC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.*;
import org.checkerframework.checker.units.qual.A;

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
    public static void getStarterItems(Player p){
        ItemStack morphFull = CustomPotion.getCustomPotion("Morphine").getItemStack();
        morphFull.setAmount(2);
        ItemStack whisFull = CustomPotion.getCustomPotion("Whiskey").getItemStack();
        whisFull.setAmount(2);
        ItemStack medFull = CustomPotion.getCustomPotion("Medicine").getItemStack();
        medFull.setAmount(5);
        ItemStack salmon = CustomItem.getCustomItem("Smoked Salmon").getItemStack();
        salmon.setAmount(10);

        ItemStack pistol = CustomItem.getCustomItem("Pistol Ammo").getItemStack();
        pistol.setAmount(24);
        ItemStack splint = CustomItem.getCustomItem("Splint").getItemStack();
        splint.setAmount(4);
        ItemStack bandage = CustomItem.getCustomItem("Bandage").getItemStack();
        bandage.setAmount(4);

        BanditUtils.getFirearm(p,"coltnavy");
        p.getInventory().addItem(medFull);
        p.getInventory().addItem(whisFull);
        p.getInventory().addItem(morphFull);
        p.getInventory().addItem(salmon);
        p.getInventory().addItem(pistol);
        p.getInventory().addItem(bandage);
        p.getInventory().addItem(splint);
        p.getInventory().addItem(CustomItem.getCustomItem("Tracker").getItemStack());
        p.getInventory().setBoots(CustomItem.getCustomItem("Farm Hand Boots").getItemStack());
        p.getInventory().setLeggings(CustomItem.getCustomItem("Farm Hand Chaps").getItemStack());
        p.getInventory().setChestplate(CustomItem.getCustomItem("Farm Hand Shirt").getItemStack());
        p.getInventory().setHelmet(CustomItem.getCustomItem("Farm Hand Hat").getItemStack());

    }
    public static void getPrisonItems(Player p){

        ItemStack salmon = CustomItem.getCustomItem("Smoked Salmon").getItemStack();
        salmon.setAmount(10);

        ItemStack splint = CustomItem.getCustomItem("Splint").getItemStack();
        splint.setAmount(4);

        p.getInventory().addItem(CustomItem.getCustomItem("Explorer's Pick").getItemStack());
        p.getInventory().addItem(salmon);
        p.getInventory().addItem(splint);
    }

    public static void releasePrisoner(Player p, Bandit b) {
        b.setBounty(0);
        b.setJailed(false);
        b.setEscaping(false);
        b.setJailedFlag(false);
        p.sendTitle(ChatColor.GRAY + "You are released from" + ChatColor.RED + " Prison!", "Good luck...");
        b.setRespawning(true);
        getStarterItems(p);
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
    public static void getFirearm(Player p, String gunName )
    {
            String weapon = "shot give " + p.getDisplayName() + " " + gunName;

            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Bukkit.dispatchCommand(console, weapon);

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
    public static String getRandomTip()
    {
        ArrayList<String> tips = new ArrayList<>();
        tips.add(ChatColor.AQUA+"TIP: "+ChatColor.GRAY+"Don't forget to send your horse back to the stable when you arrive at your destination");
        tips.add(ChatColor.AQUA+"TIP: "+ChatColor.GRAY+"Do /c to quickly view active contracts!");

        int tip = GlobalUtils.getRandomNumber(tips.size());
        return tips.get(tip);
    }

    public static Inventory newVaultUI(Player p) {
        Bandit b = Bandit.getPlayer(p);
        Inventory newVaultUI = Bukkit.createInventory(p, 27, p.getDisplayName() + ": " + ChatColor.DARK_AQUA + "Open an Account");
        newVaultUI.setItem(11, ItemIcon.getIcon("open_account").getItem());
        newVaultUI.setItem(15, ItemIcon.getIcon("close_vault").getItem());
        return newVaultUI;
    }
    public static Inventory vaultUI(Player p) {
        Bandit b = Bandit.getPlayer(p);
        Inventory vaultUI = Bukkit.createInventory(p, 54, p.getDisplayName() + ": "+ChatColor.DARK_AQUA+"Vault");
        List<ItemStack> items = b.getItemVault();
        for (int i = b.getVaultSize(); i < vaultUI.getSize();i++) {
            vaultUI.setItem(i,ItemIcon.getIcon("empty_slot").getItem());
        }

        if (!items.isEmpty()) {
            for (int i = 0; i < items.size(); i++) {
                    vaultUI.setItem(i, items.get(i));
            }
        }

        return vaultUI;
    }

    public static Inventory vaultUpgradeUI(Player p) {
        Bandit b = Bandit.getPlayer(p);
        Inventory vaultUpgradeUI = Bukkit.createInventory(p, 27, p.getDisplayName() + ": " + ChatColor.DARK_AQUA + "Upgrade Vault");
        vaultUpgradeUI.setItem(11, ItemIcon.getIcon("upgrade_vault_confirm").getItem());
        vaultUpgradeUI.setItem(15, ItemIcon.getIcon("close_vault").getItem());
        return vaultUpgradeUI;
    }
}

