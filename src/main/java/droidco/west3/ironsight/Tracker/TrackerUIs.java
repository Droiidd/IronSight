package droidco.west3.ironsight.Tracker;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Items.ItemIcon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TrackerUIs {

    public static Inventory openTrackerUI(Player p) {
        Inventory TrackerUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Tracker");
        Bandit iPlayer = Bandit.getPlayer(p);
        TrackerUi.setItem(10, ItemIcon.getIcon("Towns").getItem());
        TrackerUi.setItem(11, ItemIcon.getIcon("Players").getItem());
        TrackerUi.setItem(12, ItemIcon.getIcon("Contracts").getItem());
        TrackerUi.setItem(14, ItemIcon.getIcon("Merchants").getItem());
        TrackerUi.setItem(15, ItemIcon.getIcon("NPCs").getItem());
        TrackerUi.setItem(16, ItemIcon.getIcon("Locations").getItem());
        return TrackerUi;
    }

    public static Inventory openTownsUi(Player p) {
        Inventory townsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Towns");
        Bandit iPlayer = Bandit.getPlayer(p);
        townsUi.setItem(10, ItemIcon.getIcon("Santa Fe").getItem());
        townsUi.setItem(11, ItemIcon.getIcon("New Orleans").getItem());
        townsUi.setItem(12, ItemIcon.getIcon("Republic of Texas").getItem());

        return townsUi;
    }

    public static Inventory openPlayersUi(Player p) {
        Inventory playersUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Players");
        Bandit iPlayer = Bandit.getPlayer(p);
        playersUi.setItem(10, ItemIcon.getIcon("Nearest Wanted Player").getItem());
        return playersUi;
    }

    /*    public static Inventory openContractsUi(Player p) {
            Inventory contractsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_GRAY + "Contractor Title Select:");
            Bandit iPlayer = Bandit.getPlayer(p);
            contractsUi.setItem(10, ItemIcon.getIcon("Cowboy").getItem());
            return contractsUi;
        } */
    public static Inventory openMerchantsUi(Player p) {
        Inventory merchantsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Merchants");
        Bandit iPlayer = Bandit.getPlayer(p);
        merchantsUi.setItem(10, ItemIcon.getIcon("Fisherman").getItem());
        merchantsUi.setItem(11, ItemIcon.getIcon("Pharmacist").getItem());
        merchantsUi.setItem(12, ItemIcon.getIcon("Arms Dealer").getItem());
        merchantsUi.setItem(14, ItemIcon.getIcon("Illegal Arms Dealer").getItem());
        merchantsUi.setItem(15, ItemIcon.getIcon("Armorer").getItem());
        merchantsUi.setItem(16, ItemIcon.getIcon("Illegal Armorer").getItem());
        merchantsUi.setItem(14, ItemIcon.getIcon("General Store").getItem());
        merchantsUi.setItem(15, ItemIcon.getIcon("Geologist").getItem());
        merchantsUi.setItem(16, ItemIcon.getIcon("Stable Manager").getItem());
        return merchantsUi;
    }
    public static Inventory openNPCsUi(Player p) {
        Inventory NPCsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "NPCs");
        Bandit iPlayer = Bandit.getPlayer(p);
        NPCsUi.setItem(10, ItemIcon.getIcon("Conductor").getItem());
        NPCsUi.setItem(11, ItemIcon.getIcon("Ferry Captain").getItem());
        NPCsUi.setItem(12, ItemIcon.getIcon("Bank Teller").getItem());
        NPCsUi.setItem(14, ItemIcon.getIcon("Item Vault Manager").getItem());
        NPCsUi.setItem(15, ItemIcon.getIcon("Contractor").getItem());
        NPCsUi.setItem(16, ItemIcon.getIcon("Chief of Police").getItem());
        NPCsUi.setItem(14, ItemIcon.getIcon("General Store").getItem());
        return NPCsUi;
    }

    public static Inventory openLocationsUi(Player p) {
        Inventory locationsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Locations");
        Bandit iPlayer = Bandit.getPlayer(p);
        locationsUi.setItem(10, ItemIcon.getIcon("Mines").getItem());
        locationsUi.setItem(11, ItemIcon.getIcon("Rivers").getItem());
        locationsUi.setItem(12, ItemIcon.getIcon("Forest Reserves").getItem());
        locationsUi.setItem(14, ItemIcon.getIcon("Bandit Camps").getItem());
        locationsUi.setItem(15, ItemIcon.getIcon("Scav Towns").getItem());
        locationsUi.setItem(16, ItemIcon.getIcon("Oil Fields").getItem());
        locationsUi.setItem(14, ItemIcon.getIcon("Drug Fields").getItem());
        return locationsUi;
    }

    public static Inventory openMinesUi(Player p) {
        Inventory minesUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Mines");
        Bandit iPlayer = Bandit.getPlayer(p);
        minesUi.setItem(10, ItemIcon.getIcon("Black Spur Mines").getItem());
        minesUi.setItem(11, ItemIcon.getIcon("Barron's Canyon").getItem());
        minesUi.setItem(12, ItemIcon.getIcon("Half Dome Mines").getItem());
        return minesUi;
    }

    public static Inventory openRiversUi(Player p) {
        Inventory riversUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Rivers");
        Bandit iPlayer = Bandit.getPlayer(p);
        riversUi.setItem(10, ItemIcon.getIcon("Pearl River").getItem());
        riversUi.setItem(11, ItemIcon.getIcon("Three Forks Delta").getItem());
        riversUi.setItem(12, ItemIcon.getIcon("Lower Guadalupe River").getItem());
        riversUi.setItem(12, ItemIcon.getIcon("Slough Creek").getItem());
        return riversUi;
    }
    public static Inventory openForestReservesUi(Player p) {
        Inventory forestsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Forest Reserves");
        Bandit iPlayer = Bandit.getPlayer(p);
        forestsUi.setItem(10, ItemIcon.getIcon("Grizzly Ridge").getItem());
        forestsUi.setItem(11, ItemIcon.getIcon("Marston Glacier").getItem());
        forestsUi.setItem(12, ItemIcon.getIcon("Hawk Ridge Forest").getItem());
        forestsUi.setItem(12, ItemIcon.getIcon("Sentinel Rock").getItem());
        return forestsUi;
    }

    public static Inventory openBanditCampsUi(Player p) {
        Inventory banditCampsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Forest Reserves");
        Bandit iPlayer = Bandit.getPlayer(p);
        banditCampsUi.setItem(10, ItemIcon.getIcon("Red Ash Camp").getItem());
        banditCampsUi.setItem(11, ItemIcon.getIcon("Storm Point Rebel Base").getItem());
        return banditCampsUi;
    }

    public static Inventory openScavTownsUi(Player p) {
        Inventory scavTownsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Forest Reserves");
        Bandit iPlayer = Bandit.getPlayer(p);
        scavTownsUi.setItem(10, ItemIcon.getIcon("Florence Peak").getItem());
        scavTownsUi.setItem(11, ItemIcon.getIcon("Washington Column").getItem());
        scavTownsUi.setItem(12, ItemIcon.getIcon("Sierra Gorge").getItem());
        return scavTownsUi;
    }
    public static Inventory openOilFieldsUi(Player p) {
        Inventory oilFieldsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Oil Fields");
        Bandit iPlayer = Bandit.getPlayer(p);
        oilFieldsUi.setItem(10, ItemIcon.getIcon("North Moraine Oil Field").getItem());
        return oilFieldsUi;
    }
    public static Inventory openDrugFieldsUi(Player p) {
        Inventory drugFieldsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Drug Fields");
        Bandit iPlayer = Bandit.getPlayer(p);
        drugFieldsUi.setItem(10, ItemIcon.getIcon("Smokeleaf Drug Field").getItem());
        return drugFieldsUi;
    }






}

