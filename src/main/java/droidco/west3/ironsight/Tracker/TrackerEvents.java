package droidco.west3.ironsight.Tracker;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Globals.Utils.BanditUtils;
import droidco.west3.ironsight.Globals.Utils.GameContentLoader;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Location.Location;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.checkerframework.checker.units.qual.A;

public class TrackerEvents implements Listener {


    @EventHandler
    public void trackerMenuSelect(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Bandit b = Bandit.getPlayer(p);
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Tracker")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case DARK_OAK_HANGING_SIGN -> {
                    p.openInventory(TrackerUIs.openTownsUi(p));
                    break;
                }

                case PLAYER_HEAD -> {
                    p.openInventory(TrackerUIs.openPlayersUi(p));
                    break;
                }

                case BOOK -> {
                    // p.openInventory(TrackerUIs.openContractsUi(p));
                    break;
                }

                case BELL -> {
                    p.openInventory(TrackerUIs.openMerchantsUi(p));
                    break;
                }

                case BOOKSHELF -> {
                    p.openInventory(TrackerUIs.openNPCsUi(p));
                    break;
                }

                case COMPASS -> {
                    p.openInventory(TrackerUIs.openLocationsUi(p));
                    break;
                }


            }

        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Towns")) {
            switch (e.getCurrentItem().getType()) {
                case WHITE_BANNER -> {
                    b.setTrackingLocation(Location.getLocation("Santa Fe"));
                    break;
                }
                case YELLOW_BANNER -> {
                    b.setTrackingLocation(Location.getLocation("New Orleans"));
                    break;
                }
                case BLUE_BANNER -> {
                    b.setTrackingLocation(Location.getLocation("Republic of Texas"));
                    break;
                }
            }

        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Players")) {
            switch (e.getCurrentItem().getType()) {
                case ZOMBIE_HEAD -> {
                    b.setIsTrackingLocation(false);
                    Player target = BanditUtils.getNearest(p, 14000.0);
                    if (target == null) {
                        p.sendMessage("No wanted player found nearby");
                    } else b.setTargetedPlayer(target);
                    b.setIsTrackingPlayer(true);
                    break;
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Contracts")) {

        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Merchants")) {
            switch (e.getCurrentItem().getType()) {
                case FISHING_ROD -> {
                    //something
                    break;
                }
                case PAPER -> {

                }
                case STONE_AXE -> {

                }
                case IRON_AXE -> {

                }
                case LEATHER_CHESTPLATE -> {

                }
                case NETHERITE_CHESTPLATE -> {

                }
                case COOKED_BEEF -> {

                }
                case STONE -> {

                }
                case SADDLE -> {

                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "NPC")) {
            switch (e.getCurrentItem().getType()) {
                case RAIL -> {

                }
                case BIRCH_BOAT -> {

                }
                case GOLD_INGOT -> {

                }
                case DIAMOND -> {

                }
                case FILLED_MAP -> {

                }
                case PIGLIN_HEAD -> {

                }

            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Locations")) {
            switch (e.getCurrentItem().getType()) {
                case COBBLESTONE -> {
                    p.openInventory(TrackerUIs.openMinesUi(p));
                    break;
                }
                case WATER_BUCKET -> {
                    p.openInventory(TrackerUIs.openRiversUi(p));
                    break;
                }
                case OAK_SAPLING -> {
                    p.openInventory(TrackerUIs.openForestReservesUi(p));
                    break;
                }
                case SKELETON_SKULL -> {
                    p.openInventory(TrackerUIs.openBanditCampsUi(p));
                    break;
                }
                case OAK_DOOR -> {
                    p.openInventory(TrackerUIs.openScavTownsUi(p));
                    break;
                }
                case COAL -> {
                    p.openInventory(TrackerUIs.openOilFieldsUi(p));
                    break;
                }
                case SPRUCE_SAPLING -> {
                    p.openInventory(TrackerUIs.openDrugFieldsUi(p));
                    break;
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Mines")) {
            switch (e.getCurrentItem().getType()) {
                case FLINT -> {
                    b.setTrackingLocation(Location.getLocation("Black Spur Mines"));
                    ;
                    break;
                }
                case TERRACOTTA -> {
                    b.setTrackingLocation(Location.getLocation("Barron's Canyon"));
                    break;
                }
                case STONE -> {
                    b.setTrackingLocation(Location.getLocation("Half Dome Mines"));
                    break;
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Rivers")) {
            switch (e.getCurrentItem().getType()) {
                case MUSIC_DISC_CAT -> {
                    b.setTrackingLocation(Location.getLocation("Pearl Mines"));
                }
                case MUSIC_DISC_CHIRP -> {
                    b.setTrackingLocation(Location.getLocation("Three Forks Delta"));
                }
                case MUSIC_DISC_BLOCKS -> {
                    b.setTrackingLocation(Location.getLocation("Lower Guadalupe River"));
                }
                case MUSIC_DISC_FAR -> {
                    b.setTrackingLocation(Location.getLocation("Slough Creek"));
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Forest Reserves")) {
            switch (e.getCurrentItem().getType()) {
                case OAK_SAPLING -> {
                    b.setTrackingLocation(Location.getLocation("Grizzly Ridge"));
                }
                case BIRCH_SAPLING -> {
                    b.setTrackingLocation(Location.getLocation("Marston Glacier"));
                }
                case ACACIA_SAPLING -> {
                    b.setTrackingLocation(Location.getLocation("Hawk Ridge Forest"));
                }
                case QUARTZ -> {
                    b.setTrackingLocation(Location.getLocation("Sentinel Rock"));
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Bandit Camps")) {
            switch (e.getCurrentItem().getType()) {
                case REDSTONE -> {
                    b.setTrackingLocation(Location.getLocation("Red Ash Camp"));
                }
                case ITEM_FRAME -> {
                    b.setTrackingLocation(Location.getLocation("Storm Point"));
                }

            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Scav Towns")) {
            switch (e.getCurrentItem().getType()) {
                case ACACIA_DOOR -> {
                    b.setTrackingLocation(Location.getLocation("Florence Peak"));
                }
                case SPRUCE_DOOR -> {
                    b.setTrackingLocation(Location.getLocation("Washington Column"));
                }
                case BIRCH_DOOR -> {
                    b.setTrackingLocation(Location.getLocation("Sierra Gorge"));
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Oil Fields")) {
            switch (e.getCurrentItem().getType()) {
                case BUCKET -> {
                    b.setTrackingLocation(Location.getLocation("North Moraine Oil Field"));

                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Drug Fields")) {
            switch (e.getCurrentItem().getType()) {
                case KELP -> {
                    b.setTrackingLocation(Location.getLocation("Smokeleaf Drug Field"));
                }
            }
        }
    }

    @EventHandler
    public void trackerMovement(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Bandit b = Bandit.getPlayer(p);
        p.setCompassTarget(Location.getLocation(b.getTrackingLocation().getLocName()).getCenterLocation(p));

    }

    @EventHandler
    public void trackerRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.getInventory().getItemInMainHand().getType().compareTo(Material.COMPASS) == 0) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                p.openInventory(TrackerUIs.openTrackerUI(p));
            }

        }
    }


}
