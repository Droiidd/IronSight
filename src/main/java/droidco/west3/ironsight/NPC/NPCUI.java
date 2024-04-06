package droidco.west3.ironsight.NPC;

import org.bukkit.entity.Item;
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
        shopkeeperUI.setItem(19,CustomItem.getCustomItem("Bandage").getItemForSale());
        shopkeeperUI.setItem(20,CustomItem.getCustomItem("Splint").getItemForSale());
        shopkeeperUI.setItem(21,CustomItem.getCustomItem("Tracker").getItemForSale());
        shopkeeperUI.setItem(10, CustomItem.getCustomItem("Smoked Salmon").getItemForSale());
        shopkeeperUI.setItem(10, CustomItem.getCustomItem("Smoked Salmon").getItemForSale());
        shopkeeperUI.setItem(11, CustomItem.getCustomItem("Charred Potato").getItemForSale());
        shopkeeperUI.setItem(12, CustomItem.getCustomItem("Brown Stew").getItemForSale());
        shopkeeperUI.setItem(13, CustomItem.getCustomItem("Cooked Fox").getItemForSale());
        shopkeeperUI.setItem(14, CustomItem.getCustomItem("Rabbit Stew").getItemForSale());
        shopkeeperUI.setItem(15, CustomItem.getCustomItem("Cooked Rabbit").getItemForSale());

        return shopkeeperUI;

    }

    public static Inventory armsDealerUI(Player p) {
        Inventory shop = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Arms Dealer");

        return shop;

    }

    public static Inventory officerArmsUI(Player p) {
        Inventory officerArmsUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Officer Arms Dealer");


        return officerArmsUI;

    }

    public static Inventory illegalArmsUI(Player p) {
        Inventory illegalArmsUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Black Market Arms");
        Bandit iPlayer = Bandit.getPlayer(p);

        return illegalArmsUI;

    }

    public static Inventory geologistUI(Player p) {
        Inventory shop = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Geologist");
        shop.setItem(10,CustomItem.getCustomItem("Broken Pick").getItemForSale());
        shop.setItem(11,CustomItem.getCustomItem("Old Miner's Pick").getItemForSale());
        shop.setItem(12,CustomItem.getCustomItem("Explorer's Pick").getItemForSale());
        shop.setItem(16,ItemIcon.getIcon("open_geode").getItem());

        return shop;

    }

    public static Inventory fishermanUI(Player p) {
        Inventory shop = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Fisherman");
        shop.setItem(10,CustomItem.getCustomItem("Wooden Fishing Rod").getItemForSale());
        shop.setItem(11,CustomItem.getCustomItem("Steel Lined Rod").getItemForSale());
        shop.setItem(12,CustomItem.getCustomItem("Expedition Rod").getItemForSale());
        shop.setItem(1,CustomItem.getCustomItem("Sea Slug").getItemForSale());
        shop.setItem(2,CustomItem.getCustomItem("Hermit Crab").getItemForSale());

        return shop;

    }

    public static Inventory pharmacistUI(Player p) {
        Inventory pharmacistUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Pharmacist");

        return pharmacistUI;

    }

    public static Inventory armorerUI(Player p) {
        Inventory shop = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Armorer");

        shop.setItem(1,CustomItem.getCustomItem("Farm Hand Hat").getItemForSale());
        shop.setItem(2,CustomItem.getCustomItem("Farm Hand Shirt").getItemForSale());
        shop.setItem(3,CustomItem.getCustomItem("Farm Hand Chaps").getItemForSale());
        shop.setItem(4,CustomItem.getCustomItem("Farm Hand Boots").getItemForSale());

        shop.setItem(10,CustomItem.getCustomItem("Huntsmen Hat").getItemForSale());
        shop.setItem(11,CustomItem.getCustomItem("Huntsmen Jacket").getItemForSale());
        shop.setItem(12,CustomItem.getCustomItem("Huntsmen Trousers").getItemForSale());
        shop.setItem(13,CustomItem.getCustomItem("Huntsmen Boots").getItemForSale());

        return shop;

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

    public static Inventory openBankerUI(Player p) {
        Inventory bankerUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Bank Teller");
        bankerUI.setItem(11, ItemIcon.getIcon("bank_deposit").getItem());
        bankerUI.setItem(15, ItemIcon.getIcon("bank_withdraw").getItem());
        return bankerUI;

    }
    public static Inventory vaultKeeperUI(Player p) {
        Inventory vaultKeeperUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Vault Keeper");
        Bandit iPlayer = Bandit.getPlayer(p);

        return vaultKeeperUI;

    }


    public static Inventory chiefUI(Player p) {
        Inventory chiefUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Chief of Police");
        Bandit iPlayer = Bandit.getPlayer(p);

        return chiefUI;

    }

}
