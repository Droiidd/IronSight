package droidco.west3.ironsight.processors;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.globals.utils.GlobalUtils;
import droidco.west3.ironsight.items.CustomItem;
import droidco.west3.ironsight.items.ItemIcon;
import java.util.List;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

@AllArgsConstructor
public class ProcessorEvents implements Listener {
  private IronSight plugin;

  @EventHandler
  public void illegalSalesmenHandler(InventoryClickEvent e) {
    Player p = (Player) e.getWhoClicked();
    // >>>===--- SMOKE LEAF PROCESSOR 1 ---===<<<
    for (var processor : Processor.getProcessors().entrySet()) {
      String view = ChatColor.stripColor(e.getView().getTitle());
      if (view.equalsIgnoreCase(ChatColor.stripColor(processor.getValue().getProcessorCode()))) {
        e.setCancelled(true);
        if (e.getCurrentItem()
            .getItemMeta()
            .getDisplayName()
            .equalsIgnoreCase(
                ItemIcon.getIcon("process_smokeleaf").getItem().getItemMeta().getDisplayName())) {
          if (!p.getInventory()
              .containsAtLeast(
                  CustomItem.getCustomItem("Unprocessed Smokeleaf").getItemStack(), 18)) {
            p.closeInventory();
            p.sendMessage(ChatColor.GRAY + "You have no " + ChatColor.RED + "smokeleaf!");

          } else {
            // PLAYER HAS DRUGS
            if (processor.getValue().isProcessing()) {
              // THERE IS A PLAYER PROCESSING ALREADY
              p.sendMessage(ChatColor.RED + "This processor is already in use!");
              p.closeInventory();
              return;
            } else {
              // IT'S UNUSED!
              //

              int chance = GlobalUtils.getRandomNumber(1001);
              if (chance < 10) {
                Bukkit.broadcastMessage(
                    ChatColor.RED
                        + processor.getValue().getProcessorCode()
                        + ChatColor.GRAY
                        + " has "
                        + ChatColor.AQUA
                        + " changed locations!");
                // SPAWN LOC 1
                List<Entity> entities =
                    p.getNearbyEntities(
                        p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
                for (int i = 0; i < entities.size(); i++) {
                  if (entities.get(i) instanceof Villager) {
                    if (entities
                        .get(i)
                        .getCustomName()
                        .equalsIgnoreCase(
                            ChatColor.RED + processor.getValue().getProcessorCode())) {
                      entities.get(i).remove();
                    }
                  }
                }
                p.closeInventory();
                processor.getValue().randomizeProcLocation(p);
              } else {
                p.playSound(p.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 1, 1);
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_WORK_CLERIC, 1, 1);
                new ProcessorTask(
                    processor.getValue(),
                    plugin,
                    p,
                    90,
                    processor.getValue().getUnprocDrug(),
                    processor.getValue().getProcDrug(),
                    12,
                    processor.getValue().getDefaultLocation(),
                    3);
                p.closeInventory();
              }
            }
          }
        } else if (e.getCurrentItem()
            .getItemMeta()
            .getDisplayName()
            .equalsIgnoreCase(
                ItemIcon.getIcon("process_spice").getItem().getItemMeta().getDisplayName())) {
          if (!p.getInventory()
              .containsAtLeast(CustomItem.getCustomItem("Unprocessed Spice").getItemStack(), 24)) {
            p.closeInventory();
            p.sendMessage(ChatColor.GRAY + "You have no " + ChatColor.RED + "spice!");

          } else {
            // PLAYER HAS DRUGS
            if (processor.getValue().isProcessing()) {
              // THERE IS A PLAYER PROCESSING ALREADY
              p.sendMessage(ChatColor.RED + "This processor is already in use!");
              p.closeInventory();
              return;
            } else {
              // IT'S UNUSED!
              //

              int chance = GlobalUtils.getRandomNumber(1001);
              if (chance < 10) {
                Bukkit.broadcastMessage(
                    ChatColor.RED
                        + processor.getValue().getProcessorCode()
                        + ChatColor.GRAY
                        + " has "
                        + ChatColor.AQUA
                        + " changed locations!");
                // SPAWN LOC 1
                List<Entity> entities =
                    p.getNearbyEntities(
                        p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
                for (int i = 0; i < entities.size(); i++) {
                  if (entities.get(i) instanceof Villager) {
                    if (entities
                        .get(i)
                        .getCustomName()
                        .equalsIgnoreCase(
                            ChatColor.RED + processor.getValue().getProcessorCode())) {
                      entities.get(i).remove();
                    }
                  }
                }
                p.closeInventory();
                processor.getValue().randomizeProcLocation(p);
              } else {
                p.playSound(p.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 1, 1);
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_WORK_CLERIC, 1, 1);
                new ProcessorTask(
                    processor.getValue(),
                    plugin,
                    p,
                    90,
                    processor.getValue().getUnprocDrug(),
                    processor.getValue().getProcDrug(),
                    16,
                    processor.getValue().getDefaultLocation(),
                    3);
                p.closeInventory();
              }
            }
          }
        } else if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
          p.closeInventory();
        }
      }
    }
  }
}
