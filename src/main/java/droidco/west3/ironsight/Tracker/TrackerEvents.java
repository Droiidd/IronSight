package droidco.west3.ironsight.Tracker;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Globals.Utils.BanditUtils;
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class TrackerEvents implements Listener {


    @EventHandler
    public void trackerMovement(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Bandit b = Bandit.getPlayer(p);
        p.setCompassTarget(FrontierLocation.getLocation(b.getTrackingLocation().getLocName()).getCenterLocation(p));

    }

    @EventHandler
    public void trackerRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.getInventory().getItemInMainHand().getType().compareTo(Material.COMPASS) == 0) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                p.openInventory(TrackerUI.openTrackerUI(p));
            }

        }
    }
    @EventHandler
    public void trackerMenuSelect(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Bandit b = Bandit.getPlayer(p);
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Tracker")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case DARK_OAK_HANGING_SIGN -> {
                    p.openInventory(TrackerUI.openTownsUi(p));
                    break;
                }

                case PLAYER_HEAD -> {
                    p.openInventory(TrackerUI.openPlayersUi(p));
                    break;
                }

                case BOOK -> {
                    // p.openInventory(TrackerUIs.openContractsUi(p));
                    break;
                }

                case BELL -> {
                    p.openInventory(TrackerUI.openMerchantsUi(p));
                    break;
                }

                case BOOKSHELF -> {
                    p.openInventory(TrackerUI.openNPCsUi(p));
                    break;
                }

                case COMPASS -> {
                    p.openInventory(TrackerUI.openLocationsUi(p));
                    break;
                }


            }

        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Towns")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case WHITE_BANNER -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Santa Fe"));
                    p.closeInventory();
                    break;

                }
                case YELLOW_BANNER -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("New Orleans"));
                    p.closeInventory();
                    break;
                }
                case BLUE_BANNER -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Republic of Texas"));
                    p.closeInventory();
                    break;
                }

            }

        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Players")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case ZOMBIE_HEAD -> {
                    b.setIsTrackingLocation(false);
                    Player target = BanditUtils.getNearest(p, 14000.0);
                    if (target == null) {
                        p.sendMessage("No wanted player found nearby");
                    } else b.setTargetedPlayer(target);
                    b.setIsTrackingPlayer(true);
                    p.closeInventory();
                    break;
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Contracts")) {
            e.setCancelled(true);

        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Merchants")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case FISHING_ROD -> {
                    //something
                    p.closeInventory();
                }
                case PAPER -> {
                    p.closeInventory();
                }
                case STONE_AXE -> {
                    p.closeInventory();
                }
                case IRON_AXE -> {
                    p.closeInventory();
                }
                case LEATHER_CHESTPLATE -> {
                    p.closeInventory();
                }
                case NETHERITE_CHESTPLATE -> {
                    p.closeInventory();
                }
                case COOKED_BEEF -> {
                    p.closeInventory();
                }
                case STONE -> {
                    p.closeInventory();
                }
                case SADDLE -> {
                    p.closeInventory();
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "NPC")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case RAIL -> {
                    p.closeInventory();
                }
                case BIRCH_BOAT -> {
                    p.closeInventory();
                }
                case GOLD_INGOT -> {
                    p.closeInventory();
                }
                case DIAMOND -> {
                    p.closeInventory();
                }
                case FILLED_MAP -> {
                    p.closeInventory();
                }
                case PIGLIN_HEAD -> {
                    p.closeInventory();
                }

            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Locations")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case COBBLESTONE -> {
                    p.openInventory(TrackerUI.openMinesUi(p));
                    break;
                }
                case WATER_BUCKET -> {
                    p.openInventory(TrackerUI.openRiversUi(p));
                    break;
                }
                case OAK_SAPLING -> {
                    p.openInventory(TrackerUI.openForestReservesUi(p));
                    break;
                }
                case SKELETON_SKULL -> {
                    p.openInventory(TrackerUI.openBanditCampsUi(p));
                    break;
                }
                case OAK_DOOR -> {
                    p.openInventory(TrackerUI.openScavTownsUi(p));
                    break;
                }
                case COAL -> {
                    p.openInventory(TrackerUI.openOilFieldsUi(p));
                    break;
                }
                case SPRUCE_SAPLING -> {
                    p.openInventory(TrackerUI.openDrugFieldsUi(p));
                    break;
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Mines")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case FLINT -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Black Spur Mines"));
                    p.closeInventory();
                    break;
                }
                case TERRACOTTA -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Barron's Canyon"));
                    p.closeInventory();
                    break;
                }
                case STONE -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Half Dome Mines"));
                    p.closeInventory();
                    break;
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Rivers")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case MUSIC_DISC_CAT -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Pearl Mines"));
                    p.closeInventory();
                }
                case MUSIC_DISC_CHIRP -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Three Forks Delta"));
                    p.closeInventory();
                }
                case MUSIC_DISC_BLOCKS -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Lower Guadalupe River"));
                    p.closeInventory();
                }
                case MUSIC_DISC_FAR -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Slough Creek"));
                    p.closeInventory();
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Forest Reserves")) {
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                case OAK_SAPLING -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Grizzly Ridge"));
                    p.closeInventory();
                }
                case BIRCH_SAPLING -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Marston Glacier"));
                    p.closeInventory();
                }
                case ACACIA_SAPLING -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Hawk Ridge Forest"));
                    p.closeInventory();
                }
                case QUARTZ -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Sentinel Rock"));
                    p.closeInventory();
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Bandit Camps")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case REDSTONE -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Red Ash Camp"));
                    p.closeInventory();
                }
                case ITEM_FRAME -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Storm Point"));
                    p.closeInventory();
                }

            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Scav Towns")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case ACACIA_DOOR -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Florence Peak"));
                    p.closeInventory();
                }
                case SPRUCE_DOOR -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Washington Column"));
                    p.closeInventory();
                }
                case BIRCH_DOOR -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Sierra Gorge"));
                    p.closeInventory();
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Oil Fields")) {
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                case BUCKET -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("North Moraine Oil Field"));
                    p.closeInventory();
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Drug Fields")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case KELP -> {
                    b.setTrackingLocation(FrontierLocation.getLocation("Smokeleaf Drug Field"));
                    p.closeInventory();
                }
            }
        }
    }

}
