package droidco.west3.ironsight.horse;

import droidco.west3.ironsight.bandit.Bandit;
import droidco.west3.ironsight.database.PlayerConnector;
import java.util.Arrays;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class HorseEvents implements Listener {

  @EventHandler
  public void onHorseSummon(InventoryClickEvent e) {
    Player p = (Player) e.getWhoClicked();
    if (e.getView()
        .getTitle()
        .equalsIgnoreCase(
            ChatColor.DARK_RED
                + ChatColor.stripColor(p.getDisplayName())
                + ChatColor.DARK_GRAY
                + "'s horses")) {
      e.setCancelled(true);
      Bandit b = Bandit.getPlayer(p);
      List<FrontierHorse> horses = b.getHorses();
      if (e.getCurrentItem().getType().equals(Material.NAME_TAG)) {
        for (FrontierHorse horse : horses) {
          if (e.getCurrentItem()
              .getItemMeta()
              .getDisplayName()
              .equalsIgnoreCase(ChatColor.stripColor(horse.getHorseName()))) {
            p.closeInventory();
            if (b.isSummoningHorse()) {
              p.sendMessage("Already calling a horse!");
            } else {
              b.setSummoningHorse(true);
              b.setHorseBeingSummoned(horse);
            }
          }
        }
      }
    }
  }

  @EventHandler
  public void playerMountingHorse(PlayerInteractEntityEvent e) {
    Player p = e.getPlayer();
    boolean isHorseOwner = false;
    boolean isInvLoaded = false;
    if (e.getRightClicked().getType().equals(EntityType.HORSE)
        || e.getRightClicked().getType().equals(EntityType.DONKEY)) {
      FrontierHorse horse = FrontierHorse.getHorse(e.getRightClicked().getUniqueId());
      if (horse != null) {
        if (p.getUniqueId().toString().equalsIgnoreCase(horse.getOwnerId())) {
          isHorseOwner = true;
          isInvLoaded = horse.isInventoryLoaded();
        }
      }
    }
    if (!isHorseOwner) {
      if (e.getRightClicked().getType().equals(EntityType.HORSE)
          || e.getRightClicked().getType().equals(EntityType.DONKEY)) {
        e.setCancelled(true);
        p.sendMessage(ChatColor.RED + "This is not your horse!");
      }
    } else {
      if (p.isSneaking()) {
        e.setCancelled(true);
        p.openInventory(
            HorseUI.openHorseMenu(p, FrontierHorse.getHorse(e.getRightClicked().getUniqueId())));
      }
    }
  }

  @EventHandler
  public void disconnectedHorsesSummoned(PlayerQuitEvent e) {
    Player p = e.getPlayer();
    Bandit b = Bandit.getPlayer(p);
    List<FrontierHorse> horses = b.getHorses();
    for (FrontierHorse horse : horses) {
      if (horse.isSummoned()) {
        horse.setSummoned(false);
        FrontierHorse.getSummonedHorse(horse.getHorseId()).remove();
      }
    }
  }

  @EventHandler
  public void horseInventoryListener(InventoryClickEvent e) {
    Player p = (Player) e.getWhoClicked();
    Bandit b = Bandit.getPlayer(p);
    FrontierHorse targetHorse = null;
    List<FrontierHorse> horses = b.getHorses();
    if (e.getCurrentItem() != null) {
      for (FrontierHorse horse : horses) {
        String invTitle = horse.getHorseName() + "'s saddle-pack";
        if (invTitle.equalsIgnoreCase(e.getView().getTitle())) {
          targetHorse = horse;
        } else if (e.getView()
            .getTitle()
            .equalsIgnoreCase(horse.getHorseName() + "'s saddle-pack storage")) {
          if (e.getCurrentItem().getType().equals(Material.GRAY_STAINED_GLASS_PANE)) {
            e.setCancelled(true);
          }
        }
      }
      if (targetHorse != null) {
        e.setCancelled(true);
        switch (e.getCurrentItem().getType()) {
          case HAY_BLOCK -> {
            FrontierHorse.getSummonedHorse(targetHorse.getHorseId()).remove();
            targetHorse.setSummoned(false);
            p.closeInventory();
            p.sendMessage(
                ChatColor.GRAY
                    + "Sent "
                    + ChatColor.GREEN
                    + targetHorse.getHorseName()
                    + ChatColor.GRAY
                    + " back to the stable");
          }
          case BARRIER -> {
            p.closeInventory();
          }
          case CHEST -> {
            targetHorse.openHorseInventory(p);
          }
        }
      }
    }
  }

  @EventHandler
  public void saveHorseInventory(InventoryCloseEvent e) {
    Player p = (Player) e.getPlayer();
    Bandit b = Bandit.getPlayer(p);
    FrontierHorse targetHorse = null;
    List<FrontierHorse> horses = b.getHorses();
    for (FrontierHorse horse : horses) {
      String invTitle = horse.getHorseName() + "'s saddle-pack storage";

      if (invTitle.equalsIgnoreCase(e.getView().getTitle())) {
        targetHorse = horse;
      }
    }
    if (targetHorse != null) {
      targetHorse.setHorseInv(e.getInventory().getContents());
    }
  }

  @EventHandler
  public void onHorseDeath(EntityDeathEvent e) {
    if (e.getEntityType().equals(EntityType.HORSE)) {
      FrontierHorse horse = FrontierHorse.getHorse(e.getEntity().getUniqueId());
      if (horse != null) {
        List<ItemStack> items = Arrays.asList(horse.getInventory());
        e.getDrops().clear();
        for (ItemStack item : items) {
          e.getDrops().add(item);
        }
        Bandit b = Bandit.getPlayerById(horse.getOwnerId());
        b.getHorses().remove(horse);
        PlayerConnector connector = new PlayerConnector();
        connector.removeHorse(horse.getOwnerId(), horse);
      }
    }
  }
}
