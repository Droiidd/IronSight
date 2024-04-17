package droidco.west3.ironsight.NPC;

import droidco.west3.ironsight.Items.Potions.CustomPotion;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Items.ItemIcon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import droidco.west3.ironsight.Items.CustomItem;
import org.bukkit.inventory.meta.ItemMeta;


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
        shop.setItem(10,CustomItem.getCustomItem("Colt Patterson").getItemForSale());
        shop.setItem(11,CustomItem.getCustomItem("66 Winchester").getItemForSale());
        shop.setItem(12,CustomItem.getCustomItem("Henry Model 3").getItemForSale());
        shop.setItem(13,CustomItem.getCustomItem("Sharps Rifle").getItemForSale());
        shop.setItem(14,CustomItem.getCustomItem("Winchester 1873").getItemForSale());
        shop.setItem(20,CustomItem.getCustomItem("Rifle Ammo").getItemForSale());
        shop.setItem(21,CustomItem.getCustomItem("Shotgun Ammo").getItemForSale());
        shop.setItem(22,CustomItem.getCustomItem("Pistol Ammo").getItemForSale());
        return shop;

    }

    public static Inventory officerArmsUI(Player p) {
        Inventory officerArmsUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Officer Arms Dealer");

        officerArmsUI.setItem(10,CustomItem.getCustomItem("Maynard Carbine .52").getItemForSale());
        officerArmsUI.setItem(11,CustomItem.getCustomItem("S&W Model 3").getItemForSale());
        officerArmsUI.setItem(12,CustomItem.getCustomItem("Double Barreled Shotgun").getItemForSale());
        officerArmsUI.setItem(13,CustomItem.getCustomItem("Springfield Trapdoor").getItemForSale());
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
    public static Inventory openSmokeleafProcessor(Player p, int processorNumber) {
        Inventory processor = Bukkit.createInventory(p, 18, ChatColor.RED + "Smoke Leaf Processor "+processorNumber);
        ItemStack exit = getExitButton();

        ItemStack blank = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemStack smokeLeaf = CustomItem.getCustomItem("Unprocessed Smokeleaf").getItemStack();
        smokeLeaf.setAmount(8);
        ItemMeta meta = blank.getItemMeta();
        meta.setDisplayName("");
        for (int i = 0; i < 18; i++) {
            if (i == 0) {
                processor.setItem(0, exit);
            } else if (i == 13) {
                processor.setItem(13, smokeLeaf);
            } else {
                processor.setItem(i, blank);
            }
        }
        return processor;
    }
    public static ItemStack getExitButton() {
        ItemStack exit = new ItemStack(Material.BARRIER);
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(ChatColor.WHITE + "Leave");
        exit.setItemMeta(exitMeta);
        return exit;
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

    public static Inventory openPharmacistUI(Player p) {
        Inventory shop = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Pharmacist");
        shop.setItem(2, CustomPotion.getCustomPotion("Medicine").getPotionForSale());
        shop.setItem(3, CustomPotion.getCustomPotion("Whiskey").getPotionForSale());
        shop.setItem(4, CustomPotion.getCustomPotion("Morphine").getPotionForSale());
        return shop;

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
        Inventory shop = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Stable Manager");
        shop.setItem(10,CustomItem.getCustomItem("Standard").getItemForSale());
        shop.setItem(13,CustomItem.getCustomItem("Thoroughbred").getItemForSale());
        shop.setItem(16,CustomItem.getCustomItem("Donkey").getItemForSale());

        return shop;
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
        vaultKeeperUI.setItem(11, ItemIcon.getIcon("open_vault").getItem());
        vaultKeeperUI.setItem(13, ItemIcon.getIcon("open_account_menu").getItem());
        vaultKeeperUI.setItem(15, ItemIcon.getIcon("upgrade_vault").getItem());

        return vaultKeeperUI;

    }



    public static Inventory chiefUI(Player p) {
        Inventory chiefUI = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Chief of Police");
        Bandit b = Bandit.getPlayer(p);
        if (Bandit.getPlayer(p).isOfficer()){
            chiefUI.setItem(18, ItemIcon.getIcon("resign_officer").getItem());
            ItemStack sheriff = ItemIcon.getIcon("sheriff").getItem();
            ItemStack deputy = ItemIcon.getIcon("deputy").getItem();
            ItemStack marshall = ItemIcon.getIcon("marshall").getItem();
            ItemStack icons[] = {sheriff, deputy, marshall};
            int title = 0;
            String role = b.getRoleTitle();
            if (role.equals("Sheriff")) title = 1;
            if (role.equals("Deputy")) title = 2;
            if (role.equals("Marshall")) title = 3;

            while (title > 0){
                    ItemMeta iconMeta = icons[title-1].getItemMeta();
                    String name = iconMeta.getDisplayName();
                    name = name.replaceAll("[^a-zA-Z]", "");
                    iconMeta.setDisplayName(ChatColor.GREEN + name);
                    icons[title-1].setItemMeta(iconMeta);
                    title--;
            }


            chiefUI.setItem(12, icons[0]);
            chiefUI.setItem(13, icons[1]);
            chiefUI.setItem(14, icons[2]);
        }
        else{
            chiefUI.setItem(13, ItemIcon.getIcon("join_up").getItem());
        }


        return chiefUI;

    }

}
