package droidco.west3.ironsight.Tracker;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Items.ItemIcon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TrackerUI {

    public static Inventory openTrackerUI(Player p) {
        Inventory trackerUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Tracker");
        Bandit b = Bandit.getPlayer(p);
        trackerUi.setItem(10, ItemIcon.getIcon("town_tracker").getItem());
        trackerUi.setItem(11, ItemIcon.getIcon("santa_fe_tracker").getItem());
        trackerUi.setItem(12, ItemIcon.getIcon("contract_tracker").getItem());
        trackerUi.setItem(14, ItemIcon.getIcon("merchant_tracker").getItem());
        trackerUi.setItem(15, ItemIcon.getIcon("npc_tracker").getItem());
        trackerUi.setItem(16, ItemIcon.getIcon("location_tracker").getItem());
        return trackerUi;
    }

    public static Inventory openTownsUi(Player p) {
        Inventory townsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Towns");
        Bandit iPlayer = Bandit.getPlayer(p);
        townsUi.setItem(10, ItemIcon.getIcon("santa_fe_tracker").getItem());
        townsUi.setItem(11, ItemIcon.getIcon("new_orleans_tracker").getItem());
        townsUi.setItem(12, ItemIcon.getIcon("republic_of_texas_tracker").getItem());
        return townsUi;
    }

    public static Inventory openPlayersUi(Player p) {
        Inventory playersUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Players");
        Bandit iPlayer = Bandit.getPlayer(p);
        playersUi.setItem(10, ItemIcon.getIcon("wanted_tracker").getItem());
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
        merchantsUi.setItem(10, ItemIcon.getIcon("fisherman_tracker").getItem());
        merchantsUi.setItem(11, ItemIcon.getIcon("pharmacist_tracker").getItem());
        merchantsUi.setItem(12, ItemIcon.getIcon("arms_dealer_tracker").getItem());
        merchantsUi.setItem(14, ItemIcon.getIcon("illegal_arms_dealer_tracker").getItem());
        merchantsUi.setItem(15, ItemIcon.getIcon("armorer_tracker").getItem());
        merchantsUi.setItem(16, ItemIcon.getIcon("illegal_armorer_tracker").getItem());
        merchantsUi.setItem(14, ItemIcon.getIcon("general_store_tracker").getItem());
        merchantsUi.setItem(15, ItemIcon.getIcon("geologist_tracker").getItem());
        merchantsUi.setItem(16, ItemIcon.getIcon("stable_manager_tracker").getItem());
        return merchantsUi;
    }
    public static Inventory openNPCsUi(Player p) {
        Inventory NPCsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "NPCs");
        Bandit iPlayer = Bandit.getPlayer(p);
        NPCsUi.setItem(10, ItemIcon.getIcon("conductor_tracker").getItem());
        NPCsUi.setItem(11, ItemIcon.getIcon("ferry_captain_tracker").getItem());
        NPCsUi.setItem(12, ItemIcon.getIcon("bank_teller_tracker").getItem());
        NPCsUi.setItem(14, ItemIcon.getIcon("vault_manager_tracker").getItem());
        NPCsUi.setItem(15, ItemIcon.getIcon("contractor_tracker").getItem());
        NPCsUi.setItem(16, ItemIcon.getIcon("chief_of_police_tracker").getItem());
        NPCsUi.setItem(14, ItemIcon.getIcon("general_store_tracker").getItem());
        return NPCsUi;
    }

    public static Inventory openLocationsUi(Player p) {
        Inventory locationsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Locations");
        Bandit iPlayer = Bandit.getPlayer(p);
        locationsUi.setItem(10, ItemIcon.getIcon("mine_tracker").getItem());
        locationsUi.setItem(11, ItemIcon.getIcon("river_tracker").getItem());
        locationsUi.setItem(12, ItemIcon.getIcon("forest_reserves_tracker").getItem());
        locationsUi.setItem(14, ItemIcon.getIcon("bandit_camp_tracker").getItem());
        locationsUi.setItem(15, ItemIcon.getIcon("scav_town_tracker").getItem());
        locationsUi.setItem(16, ItemIcon.getIcon("oil_field_tracker").getItem());
        locationsUi.setItem(14, ItemIcon.getIcon("drug_field_tracker").getItem());
        return locationsUi;
    }

    public static Inventory openMinesUi(Player p) {
        Inventory minesUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Mines");
        Bandit iPlayer = Bandit.getPlayer(p);
        minesUi.setItem(10, ItemIcon.getIcon("black_spur_mines_tracker").getItem());
        minesUi.setItem(11, ItemIcon.getIcon("barrons_canyon_tracker").getItem());
        minesUi.setItem(12, ItemIcon.getIcon("half_dome_tracker").getItem());
        return minesUi;
    }

    public static Inventory openRiversUi(Player p) {
        Inventory riversUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Rivers");
        Bandit iPlayer = Bandit.getPlayer(p);
        riversUi.setItem(10, ItemIcon.getIcon("pearl_river_tracker").getItem());
        riversUi.setItem(11, ItemIcon.getIcon("three_forks_delta_tracker").getItem());
        riversUi.setItem(12, ItemIcon.getIcon("lower_guadalupe_river_tracker").getItem());
        riversUi.setItem(12, ItemIcon.getIcon("slough_creek_river_tracker").getItem());
        return riversUi;
    }
    public static Inventory openForestReservesUi(Player p) {
        Inventory forestsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Forest Reserves");
        Bandit iPlayer = Bandit.getPlayer(p);
        forestsUi.setItem(10, ItemIcon.getIcon("grizzly_ridge_tracker").getItem());
        forestsUi.setItem(11, ItemIcon.getIcon("marston_glacier_tracker").getItem());
        forestsUi.setItem(12, ItemIcon.getIcon("hawk_ridge_forest_tracker").getItem());
        forestsUi.setItem(12, ItemIcon.getIcon("sentinel_rock_tracker").getItem());
        return forestsUi;
    }

    public static Inventory openBanditCampsUi(Player p) {
        Inventory banditCampsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Forest Reserves");
        Bandit iPlayer = Bandit.getPlayer(p);
        banditCampsUi.setItem(10, ItemIcon.getIcon("red_ash_tracker").getItem());
        banditCampsUi.setItem(11, ItemIcon.getIcon("storm_point_tracker").getItem());
        return banditCampsUi;
    }

    public static Inventory openScavTownsUi(Player p) {
        Inventory scavTownsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Forest Reserves");
        Bandit iPlayer = Bandit.getPlayer(p);
        scavTownsUi.setItem(10, ItemIcon.getIcon("florence_peak_tracker").getItem());
        scavTownsUi.setItem(11, ItemIcon.getIcon("washington_column_tracker").getItem());
        scavTownsUi.setItem(12, ItemIcon.getIcon("sierra_gorge_tracker").getItem());
        return scavTownsUi;
    }
    public static Inventory openOilFieldsUi(Player p) {
        Inventory oilFieldsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Oil Fields");
        Bandit iPlayer = Bandit.getPlayer(p);
        oilFieldsUi.setItem(10, ItemIcon.getIcon("north_moraine_oil_field_tracker").getItem());
        return oilFieldsUi;
    }
    public static Inventory openDrugFieldsUi(Player p) {
        Inventory drugFieldsUi = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Drug Fields");
        Bandit iPlayer = Bandit.getPlayer(p);
        drugFieldsUi.setItem(10, ItemIcon.getIcon("smokeleaf_field_tracker").getItem());
        return drugFieldsUi;
    }






}

