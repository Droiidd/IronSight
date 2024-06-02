package droidco.west3.ironsight.Tracker;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Globals.Utils.BanditUtils;
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.Items.ItemIcon;
import droidco.west3.ironsight.NPC.NPC;
import droidco.west3.ironsight.NPC.NPCType;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TrackerEvents implements Listener {
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
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_RED + "Tracker")) {
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
                case IRON_PICKAXE -> {
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
                    //p.openInventory(TrackerUI.openScavTownsUi(p));
                    break;
                }
                case COAL -> {
                    p.openInventory(TrackerUI.openOilFieldsUi(p));
                    break;
                }
                case KELP -> {
                    p.openInventory(TrackerUI.openDrugFieldsUi(p));
                    break;
                }


            }

        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Towns")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case WHITE_BANNER -> {
                    b.setIsTrackingNPC(false);
                    b.setTrackingLocation(FrontierLocation.getLocation("Santa Fe").getCenterLocation(p));
                    b.setTrackingFrontierLocation(FrontierLocation.getLocation("Santa Fe"));

                    p.closeInventory();
                    break;

                }
                case YELLOW_BANNER -> {
                    b.setIsTrackingNPC(false);
                    b.setTrackingLocation(FrontierLocation.getLocation("New Orleans").getCenterLocation(p));
                    b.setTrackingFrontierLocation(FrontierLocation.getLocation("New Orleans"));
                    p.closeInventory();
                    break;
                }
                case BLUE_BANNER -> {
                    b.setIsTrackingNPC(false);
                    b.setTrackingLocation(FrontierLocation.getLocation("Republic of Texas").getCenterLocation(p));
                    b.setTrackingFrontierLocation(FrontierLocation.getLocation("Republic of Texas"));

                    p.closeInventory();
                    break;
                }

            }

        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Players")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case ZOMBIE_HEAD -> {
                    b.setIsTrackingLocation(false);
                    b.setIsTrackingNPC(false);
                    Player target = BanditUtils.getNearest(p, 14000.0);
                    if (target == null) {
                        p.sendMessage("No wanted player found nearby");
                    } else b.setTargetedPlayer(target);
                    b.setIsTrackingPlayer(true);
                    p.closeInventory();
                    break;
                }
            }

        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Merchants")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case FISHING_ROD -> {
                    trackNPC(p, b, NPCType.FISHERMAN);
                }
                case PAPER -> {
                    trackNPC(p, b, NPCType.PHARMACIST);
                }
                case STONE_AXE -> {
                    trackNPC(p, b, NPCType.ARMS_DEALER);
                }
                case IRON_AXE -> {
//                    NPC npc = NPC.getNPC("Illegal Arms Dealer");
//                    b.setIsTrackingNPC(true);
//                    b.setTrackedNPC("Illegal Arms Dealer");
//                    b.setTrackingLocation(new Location(p.getWorld(), npc.getX(), npc.getY(), npc.getZ()));
//                    p.closeInventory();
                }
                case LEATHER_CHESTPLATE -> {
                    trackNPC(p, b, NPCType.ARMORER);
                }
                case NETHERITE_CHESTPLATE -> {
//                    NPC npc = NPC.getNPC("Illegal Armorer");
//                    b.setIsTrackingNPC(true);
//                    b.setTrackedNPC("Illegal Armorer");
//                    b.setTrackingLocation(new Location(p.getWorld(), npc.getX(), npc.getY(), npc.getZ()));
//
//                    p.closeInventory();
                }
                case COOKED_BEEF -> {
                    trackNPC(p, b, NPCType.SHOPKEEPER);
                }
                case STONE -> {
                    trackNPC(p, b, NPCType.GEOLOGIST);
                }
                case SADDLE -> {
                    trackNPC(p, b, NPCType.STABLE_MANAGER);
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "NPCs")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case RAIL -> {
//                    NPC npc = NPC.getNPC("Conductor");
//                    b.setIsTrackingNPC(true);
//                    b.setTrackedNPC("Conductor");
//                    b.setTrackingLocation(new Location(p.getWorld(), npc.getX(), npc.getY(), npc.getZ()));
//                    p.closeInventory();
                }
                case BIRCH_BOAT -> {
//                    NPC npc = NPC.getNPC("Ferry Captain");
//                    b.setIsTrackingNPC(true);
//                    b.setTrackedNPC("Ferry Captain");
//                    b.setTrackingLocation(new Location(p.getWorld(), npc.getX(), npc.getY(), npc.getZ()));
//                    p.closeInventory();
                }
                case GOLD_INGOT -> {
                    trackNPC(p, b, NPCType.BANKER);
                }
                case DIAMOND -> {
                    trackNPC(p, b, NPCType.VAULT_KEEPER);
                }
                case BOOK -> {
                    trackNPC(p, b, NPCType.CONTRACTOR);
                }
                case PIGLIN_HEAD -> {
//                    NPC npc = NPC.getNPC("Chief of Police");
//                    b.setIsTrackingNPC(true);
//                    b.setTrackedNPC("Chief of Police");
//                    b.setTrackingLocation(new Location(p.getWorld(), npc.getX(), npc.getY(), npc.getZ()));
//                    p.closeInventory();
                }

            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Locations")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case IRON_PICKAXE -> {
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
                    //p.openInventory(TrackerUI.openScavTownsUi(p));
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
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Mines")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case IRON_ORE -> {
                    b.setIsTrackingNPC(false);
                    b.setTrackingLocation(FrontierLocation.getLocation("Black Spur Mines").getCenterLocation(p));
                    b.setTrackingFrontierLocation(FrontierLocation.getLocation("Black Spur Mines"));
                    p.closeInventory();
                    break;
                }
                case TERRACOTTA -> {
                    b.setIsTrackingNPC(false);
                    b.setTrackingLocation(FrontierLocation.getLocation("Barron's Canyon").getCenterLocation(p));
                    b.setTrackingFrontierLocation(FrontierLocation.getLocation("Barron's Canyon"));
                    p.closeInventory();
                    break;
                }
                case STONE -> {
                    b.setIsTrackingNPC(false);
                    b.setTrackingLocation(FrontierLocation.getLocation("Half Dome Mines").getCenterLocation(p));
                    b.setTrackingFrontierLocation(FrontierLocation.getLocation("Half Dome Mines"));
                    p.closeInventory();
                    break;
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE + "Rivers")) {
            e.setCancelled(true);
            Material targetType = e.getCurrentItem().getType();
            if (targetType.equals(ItemIcon.getIcon("pearl_river_tracker").getItem().getType())) {
                b.setIsTrackingNPC(false);
                b.setTrackingLocation(FrontierLocation.getLocation("Pearl River").getCenterLocation(p));
                b.setTrackingFrontierLocation(FrontierLocation.getLocation("Pearl River"));
                p.closeInventory();
            } else if (targetType.equals(ItemIcon.getIcon("slough_creek_river_tracker").getItem().getType())) {
                b.setIsTrackingNPC(false);
                b.setTrackingLocation(FrontierLocation.getLocation("Slough Creek River").getCenterLocation(p));
                b.setTrackingFrontierLocation(FrontierLocation.getLocation("Slough Creek River"));
                p.closeInventory();
            } else if (targetType.equals(ItemIcon.getIcon("three_forks_delta_tracker").getItem().getType())) {
                b.setIsTrackingNPC(false);
                b.setTrackingLocation(FrontierLocation.getLocation("Three Forks Delta").getCenterLocation(p));
                b.setTrackingFrontierLocation(FrontierLocation.getLocation("Three Forks Delta"));
                p.closeInventory();
            } else if (targetType.equals(ItemIcon.getIcon("lower_guadalupe_tracker").getItem().getType())) {
                b.setIsTrackingNPC(false);
                b.setTrackingLocation(FrontierLocation.getLocation("Lower Guadalupe River").getCenterLocation(p));
                b.setTrackingFrontierLocation(FrontierLocation.getLocation("Lower Guadalupe River"));
                p.closeInventory();
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Forest Reserves")) {
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                case OAK_SAPLING -> {
                    b.setIsTrackingNPC(false);
                    b.setTrackingLocation(FrontierLocation.getLocation("Grizzly Ridge").getCenterLocation(p));
                    b.setTrackingFrontierLocation(FrontierLocation.getLocation("Grizzly Ridge"));

                    p.closeInventory();
                }
                case BIRCH_SAPLING -> {
                    b.setIsTrackingNPC(false);
                    b.setTrackingLocation(FrontierLocation.getLocation("Marston Glacier").getCenterLocation(p));
                    b.setTrackingFrontierLocation(FrontierLocation.getLocation("Marston Glacier"));
                    p.closeInventory();
                }
                case ACACIA_SAPLING -> {
                    b.setIsTrackingNPC(false);
                    b.setTrackingLocation(FrontierLocation.getLocation("Hawk Ridge Forest").getCenterLocation(p));
                    b.setTrackingFrontierLocation(FrontierLocation.getLocation("Hawk Ridge Forest"));
                    p.closeInventory();
                }
                case QUARTZ -> {
                    b.setIsTrackingNPC(false);
                    b.setTrackingLocation(FrontierLocation.getLocation("Sentinel Rock").getCenterLocation(p));
                    b.setTrackingFrontierLocation(FrontierLocation.getLocation("Sentinel Rock"));
                    p.closeInventory();
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Bandit Camps")) {
            e.setCancelled(true);
            Material targetType = e.getCurrentItem().getType();
//            if(targetType.equals(ItemIcon.getIcon("red_ash_camp_tracker").getItem().getType())){
//                b.setTrackingLocation(FrontierLocation.getLocation("Red Ash Camp"));
//                b.setTrackingFrontierLocation(FrontierLocation.getLocation("Red Ash Camp"));
//                p.closeInventory();
//            }
            //else
            if (targetType.equals(ItemIcon.getIcon("storm_point_tracker").getItem().getType())) {
                b.setIsTrackingNPC(false);
                b.setTrackingLocation(FrontierLocation.getLocation("Storm Point").getCenterLocation(p));
                b.setTrackingFrontierLocation(FrontierLocation.getLocation("Storm Point"));
                p.closeInventory();
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Scav Towns")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case ACACIA_DOOR -> {
                    b.setIsTrackingNPC(false);
                    b.setTrackingLocation(FrontierLocation.getLocation("Florence Peak").getCenterLocation(p));
                    b.setTrackingFrontierLocation(FrontierLocation.getLocation("Florence Peak"));
                    p.closeInventory();
                }
                case SPRUCE_DOOR -> {
                    b.setIsTrackingNPC(false);
                    b.setTrackingLocation(FrontierLocation.getLocation("Washington Column").getCenterLocation(p));
                    b.setTrackingFrontierLocation(FrontierLocation.getLocation("Washington Column"));
                    p.closeInventory();
                }
                case BIRCH_DOOR -> {
                    b.setIsTrackingNPC(false);
                    b.setTrackingLocation(FrontierLocation.getLocation("Sierra Gorge").getCenterLocation(p));
                    b.setTrackingFrontierLocation(FrontierLocation.getLocation("Sierra Gorge"));
                    p.closeInventory();
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BLACK + "Oil Fields")) {
            e.setCancelled(true);
            Material targetType = e.getCurrentItem().getType();
            if (targetType.equals(ItemIcon.getIcon("north_moraine_oil_field_tracker").getItem().getType())) {
                b.setIsTrackingNPC(false);
                b.setTrackingLocation(FrontierLocation.getLocation("North Moraine Oil Field").getCenterLocation(p));
                b.setTrackingFrontierLocation(FrontierLocation.getLocation("North Moraine Oil Field"));
                p.closeInventory();
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Drug Fields")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case KELP -> {
                    b.setIsTrackingNPC(false);
                    b.setTrackingLocation(FrontierLocation.getLocation("Smokeleaf Field").getCenterLocation(p));
                    b.setTrackingFrontierLocation(FrontierLocation.getLocation("Smokeleaf Field"));
                    p.closeInventory();
                }
            }
        }
    }

    public void trackNPC(Player p, Bandit b, NPCType type) {
        List<NPC> npcs = NPC.getNPCsByType(type);
        List<Double> distances = new ArrayList<>();
        HashMap<Double, NPC> npcDistanceMap = new HashMap<>();
        for (NPC npc : npcs) {
            double distance = GlobalUtils.getDistanceBetweenPoints(p, new Location(p.getWorld(), npc.getX(), npc.getY(), npc.getZ()));
            npcDistanceMap.put(distance, npc);
            distances.add(distance);
        }
        double least = Collections.min(distances);

        NPC npc = npcDistanceMap.get(least);
        b.setIsTrackingNPC(true);
        b.setTrackedNPC(type.toString());

        b.setTrackingLocation(new Location(p.getWorld(), npc.getX(), npc.getY(), npc.getZ()));
        p.closeInventory();
    }

}
