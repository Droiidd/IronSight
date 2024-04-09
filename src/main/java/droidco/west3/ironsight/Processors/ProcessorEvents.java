package droidco.west3.ironsight.Processors;

import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Items.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public class ProcessorEvents implements Listener {
    private static final String smokeLeafProcName1 = ChatColor.RED    + "Smokeleaf Processor 1";
    private static final String smokeLeafProcName2 = ChatColor.RED   + "Smokeleaf Processor 2";
    private static final String smokeLeafProcName3 = ChatColor.RED  + "Smokeleaf Processor 3";
    private IronSight plugin;
    public ProcessorEvents(IronSight plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void illegalSalesmenHandler(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        // >>>===--- SMOKE LEAF PROCESSOR 1 ---===<<<
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Smoke Leaf Processor 1")) {
            Processor processor = Processor.getProcessor("smokeleaf1");
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case ENDER_PEARL:
                    //Play grind stone sounds or brewing machine sounds
                    //SET UP QUEUE FOR PROCESSING A SINGLE DRUG
                    if (!p.getInventory().containsAtLeast(CustomItem.getCustomItem("Unprocessed Smokeleaf").getItemStack(), 8)) {
                        p.closeInventory();
                        p.sendMessage(ChatColor.GRAY + "You have no " + ChatColor.RED + "smokeleaf!");

                    } else {
                        //PLAYER HAS DRUGS
                        if (processor.isProcessing()) {
                            //THERE IS A PLAYER PROCESSING ALREADY
                            p.sendMessage(ChatColor.RED + "Another player is using this processor!");
                            p.closeInventory();
                            return;
                        } else {
                            //IT'S UNUSED!
                            //

                            int chance = GlobalUtils.getRandomNumber(101);
                            if (chance < 8) {
                                Bukkit.broadcastMessage(ChatColor.GRAY + "Smokeleaf Processor 1" + ChatColor.AQUA + " has changed locations!");
                                //SPAWN LOC 1
                                List<Entity> entities = p.getNearbyEntities(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
                                for (int i = 0; i < entities.size(); i++) {
                                    if (entities.get(i) instanceof Villager) {
                                        if (entities.get(i).getCustomName().equalsIgnoreCase(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Smokeleaf Processor 1")) {
                                            entities.get(i).remove();
                                        }
                                    }
                                }
                                p.closeInventory();
                                processor.randomizeDefaultLocation(processor.getDefaultLocation());
                                LoadProcessor.createVillager(smokeLeafProcName1, processor.getDefaultLocation());
                            } else {
                                p.playSound(p.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 1, 1);
                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_WORK_CLERIC, 1, 1);
                                new ProcessorTask("smokeleaf1", processor, plugin, p, 60, CustomItem.getCustomItem("Unprocessed Smokeleaf").getItemStack(),
                                        90.0, 8, processor.getDefaultLocation());
                                p.closeInventory();
                            }

                        }
                    }
                    break;
                case BARRIER:
                    p.closeInventory();
                    break;
            }
        }
        // >>>===--- SMOKE LEAF PROCESSOR 2 ---===<<<
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Smoke Leaf Processor 2")) {
            Processor processor = Processor.getProcessor("smokeleaf2");
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case ENDER_PEARL:
                    //Play grind stone sounds or brewing machine sounds
                    //SET UP QUEUE FOR PROCESSING A SINGLE DRUG
                    if (!p.getInventory().containsAtLeast(CustomItem.getCustomItem("Unprocessed Smokeleaf").getItemStack(), 8)) {
                        p.closeInventory();
                        p.sendMessage(ChatColor.GRAY + "You have no smoke " + ChatColor.RED + "leaf!");

                    } else {
                        //PLAYER HAS DRUGS
                        if (processor.isProcessing()) {
                            //THERE IS A PLAYER PROCESSING ALREADY
                            p.sendMessage(ChatColor.RED + "Another player is using this processor!");
                            p.closeInventory();
                            return;
                        } else {
                            //IT'S UNUSED!
                            //

                            int chance = GlobalUtils.getRandomNumber(101);
                            if (chance < 8) {
                                //SPAWN LOC 1
                                Bukkit.broadcastMessage(ChatColor.GRAY + "Smokeleaf Processor 2" + ChatColor.AQUA + " has changed locations!");
                                List<Entity> entities = p.getNearbyEntities(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
                                for (int i = 0; i < entities.size(); i++) {
                                    if (entities.get(i) instanceof Villager) {
                                        if (entities.get(i).getCustomName().equalsIgnoreCase(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Smokeleaf Processor 2")) {
                                            entities.get(i).remove();
                                        }
                                    }
                                }
                                p.closeInventory();
                                processor.randomizeDefaultLocation(processor.getDefaultLocation());
                                LoadProcessor.createVillager(smokeLeafProcName2, processor.getDefaultLocation());
                            } else {
                                p.playSound(p.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 1, 1);
                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_WORK_CLERIC, 1, 1);
                                new ProcessorTask("smokeleaf2", processor, plugin, p, 60, CustomItem.getCustomItem("Unprocessed Smokeleaf").getItemStack(),
                                        90.0, 8, processor.getDefaultLocation());
                                p.closeInventory();
                            }

                        }
                    }
                    break;
                case BARRIER:
                    p.closeInventory();
                    break;
            }
        }
        // >>>===--- SMOKE LEAF PROCESSOR 3 ---===<<<
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Smoke Leaf Processor 3")) {
            Processor processor = Processor.getProcessor("smokeleaf3");
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case BARRIER:
                    p.closeInventory();
                    break;
                case ENDER_PEARL:
                    //Play grind stone sounds or brewing machine sounds
                    //SET UP QUEUE FOR PROCESSING A SINGLE DRUG
                    if (!p.getInventory().containsAtLeast(CustomItem.getCustomItem("Unprocessed Smokeleaf").getItemStack(), 8)) {
                        p.closeInventory();
                        p.sendMessage(ChatColor.GRAY + "You have no smoke " + ChatColor.RED + "leaf!");

                    } else {
                        //PLAYER HAS DRUGS
                        if (processor.isProcessing()) {
                            //THERE IS A PLAYER PROCESSING ALREADY
                            p.sendMessage(ChatColor.RED + "Another player is using this processor!");
                            p.closeInventory();
                            return;
                        } else {
                            //IT'S UNUSED!

                            int chance = GlobalUtils.getRandomNumber(101);
                            if (chance < 8) {
                                //SPAWN LOC 1
                                Bukkit.broadcastMessage(ChatColor.GRAY + "Smokeleaf Processor 3" + ChatColor.AQUA + " has changed locations!");
                                List<Entity> entities = p.getNearbyEntities(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
                                for (int i = 0; i < entities.size(); i++) {
                                    if (entities.get(i) instanceof Villager) {
                                        if (entities.get(i).getCustomName().equalsIgnoreCase(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Smokeleaf Processor 3")) {
                                            entities.get(i).remove();
                                        }
                                    }
                                }
                                p.closeInventory();
                                processor.randomizeDefaultLocation(processor.getDefaultLocation());
                                LoadProcessor.createVillager(smokeLeafProcName3, processor.getDefaultLocation());
                            } else {
                                p.playSound(p.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 1, 1);
                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_WORK_CLERIC, 1, 1);
                                new ProcessorTask("smokeleaf3", processor, plugin, p, 60, CustomItem.getCustomItem("Unprocessed Smokeleaf").getItemStack(),
                                        90.0, 8, processor.getDefaultLocation());
                                p.closeInventory();
                            }

                        }
                    }
                    break;
            }
        }
    }
}
