package droidco.west3.ironsight.NPC;

import org.bukkit.inventory.Inventory;import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Items.ItemIcon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import droidco.west3.ironsight.Items.CustomItem;


public class NPCUI {

    public static Inventory shopkeeperUI(Player p) {
        Inventory shopkeeperUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Shopkeeper");
        Bandit iPlayer = Bandit.getPlayer(p);
        shopkeeperUI.setItem(10, CustomItem.getCustomItem("Smoked Salmon").getItemForSale());
        shopkeeperUI.setItem(11, CustomItem.getCustomItem("Charred Potato").getItemForSale());
        shopkeeperUI.setItem(12, CustomItem.getCustomItem("Brown Stew").getItemForSale());
        shopkeeperUI.setItem(13, CustomItem.getCustomItem("Cooked Fox").getItemForSale());
        shopkeeperUI.setItem(14, CustomItem.getCustomItem("Rabbit Stew").getItemForSale());
        shopkeeperUI.setItem(15, CustomItem.getCustomItem("Cooked Rabbit").getItemForSale());

        return shopkeeperUI;

    }

    public static Inventory armsDealerUI(Player p) {
        Inventory armsDealerUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Arms Dealer");
        Bandit iPlayer = Bandit.getPlayer(p);

        return armsDealerUI;

    }

    public static Inventory officerArmsUI(Player p) {
        Inventory officerArmsUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Officer Arms Dealer");
        Bandit iPlayer = Bandit.getPlayer(p);

        return officerArmsUI;

    }

    public static Inventory illegalArmsUI(Player p) {
        Inventory illegalArmsUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Black Market Arms");
        Bandit iPlayer = Bandit.getPlayer(p);

        return illegalArmsUI;

    }

    public static Inventory geologistUI(Player p) {
        Inventory geologistUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Geologist");
        Bandit iPlayer = Bandit.getPlayer(p);

        return geologistUI;

    }

    public static Inventory fishermanUI(Player p) {
        Inventory fishermanUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Fisherman");
        Bandit iPlayer = Bandit.getPlayer(p);

        return fishermanUI;

    }

    public static Inventory pharmacistUI(Player p) {
        Inventory pharmacistUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Pharmacist");
        Bandit iPlayer = Bandit.getPlayer(p);

        return pharmacistUI;

    }

    public static Inventory armorerUI(Player p) {
        Inventory armorerUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Armorer");
        Bandit iPlayer = Bandit.getPlayer(p);

        return armorerUI;

    }

    public static Inventory illegalArmorerUI(Player p) {
        Inventory illegalArmorerUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Black Market Armor");
        Bandit iPlayer = Bandit.getPlayer(p);

        return illegalArmorerUI;

    }

    public static Inventory stableManagerUI(Player p) {
        Inventory stableManagerUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Stable Manager");
        Bandit iPlayer = Bandit.getPlayer(p);

        return stableManagerUI;

    }

    public static Inventory conductorUI(Player p) {
        Inventory conductorUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Train Conductor");
        Bandit iPlayer = Bandit.getPlayer(p);

        return conductorUI;

    }

    public static Inventory ferryCaptainUI(Player p) {
        Inventory ferryCaptainUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Ferry Captain");
        Bandit iPlayer = Bandit.getPlayer(p);

        return ferryCaptainUI;

    }

    public static Inventory bankerUI(Player p) {
        Inventory bankerUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Bank Teller");
        Bandit iPlayer = Bandit.getPlayer(p);

        return bankerUI;

    }
    public static Inventory vaultKeeperUI(Player p) {
        Inventory vaultKeeperUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Vault Keeper");
        Bandit iPlayer = Bandit.getPlayer(p);

        return vaultKeeperUI;

    }

    public static Inventory contractorUI(Player p) {
        Inventory contractorUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Contractor");
        Bandit iPlayer = Bandit.getPlayer(p);

        return contractorUI;

    }

    public static Inventory chiefUI(Player p) {
        Inventory chiefUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Chief of Police");
        Bandit iPlayer = Bandit.getPlayer(p);

        return chiefUI;

    }

}
