package droidco.west3.ironsight.bandit.events;

import droidco.west3.ironsight.bandit.Bandit;
import droidco.west3.ironsight.npc.NPC;
import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class VaultEvents implements Listener {
  @EventHandler
  public void vaultClick(InventoryClickEvent e) {
    Player p = (Player) e.getWhoClicked();
    Bandit b = Bandit.getPlayer(p);
    if (e.getCurrentItem() != null) {
      if (e.getView()
          .getTitle()
          .equalsIgnoreCase(p.getDisplayName() + ": " + ChatColor.DARK_AQUA + "Vault")) {
        if (e.getCurrentItem().getType().equals(Material.GRAY_STAINED_GLASS_PANE)) {
          e.setCancelled(true);
        }
      }
    }
  }

  @EventHandler
  public void vaultItemSave(InventoryCloseEvent e) {
    Player p = (Player) e.getPlayer();
    Bandit b = Bandit.getPlayer(p);
    if (e.getView()
        .getTitle()
        .equalsIgnoreCase(p.getDisplayName() + ": " + ChatColor.DARK_AQUA + "Vault")) {
      p.sendMessage(
          NPC.getNPC("Vault Keeper").getDisplayName()
              + ": "
              + ChatColor.GRAY
              + "We've saved your items to the vault. Thank you!");
      ArrayList<ItemStack> temp = new ArrayList<>();
      ItemStack[] vaultItems = e.getInventory().getContents();
      for (int i = 0; i < b.getVaultSize(); i++) {
        temp.add(vaultItems[i]);
      }
      b.setItemVault(temp);

      //// p.closeInventory();
    }
  }

  @EventHandler
  public void vaultUpgradeMenu(InventoryClickEvent e) {
    Player p = (Player) e.getWhoClicked();
    Bandit b = Bandit.getPlayer(p);
    if (e.getCurrentItem() != null) {
      if (e.getView()
          .getTitle()
          .equalsIgnoreCase(p.getDisplayName() + ": " + ChatColor.DARK_AQUA + "Upgrade Vault")) {
        if (e.getCurrentItem() != null) {
          e.setCancelled(true);
          switch (e.getCurrentItem().getType()) {
            case EMERALD_BLOCK -> {
              if (b.getWallet() >= 50000 && b.getVaultLevel() < 9) {
                b.setVaultSize(b.getVaultSize() + 6);
                b.setVaultLevel(b.getVaultLevel() + 1);
                p.sendMessage(
                    NPC.getNPC("Vault Keeper").getDisplayName()
                        + ": "
                        + ChatColor.GRAY
                        + "Thanks for purchasing a vault upgrade! You now have "
                        + b.getVaultSize()
                        + " slots!");
              } else if (b.getWallet() < 50000 && b.getVaultLevel() < 9) {
                p.closeInventory();
                p.sendMessage(
                    NPC.getNPC("Vault Keeper").getDisplayName()
                        + ": "
                        + ChatColor.RED
                        + "You don't have enough funds!");
              } else if (b.getVaultLevel() == 9) {
                p.closeInventory();
                p.sendMessage(
                    NPC.getNPC("Vault Keeper").getDisplayName()
                        + ": "
                        + ChatColor.RED
                        + "There are no more upgrades available!");
              }
            }
            case REDSTONE_BLOCK -> {
              p.closeInventory();
            }
          }
        }
      }
    }
  }

  @EventHandler
  public void openVaultAccountMenu(InventoryClickEvent e) {
    Player p = (Player) e.getWhoClicked();
    Bandit b = Bandit.getPlayer(p);
    if (e.getCurrentItem() != null) {
      if (e.getView()
          .getTitle()
          .equalsIgnoreCase(p.getDisplayName() + ": " + ChatColor.DARK_AQUA + "Open an Account")) {
        if (e.getCurrentItem() != null) {
          e.setCancelled(true);
          switch (e.getCurrentItem().getType()) {
            case EMERALD_BLOCK -> {
              if (b.getWallet() >= 25000 && b.getVaultLevel() == 0) {
                b.setVaultSize(6);
                b.setVaultLevel(1);
                b.updateWallet(-1 * 25000);
                p.closeInventory();
                p.sendMessage(
                    NPC.getNPC("Vault Keeper").getDisplayName()
                        + ": "
                        + ChatColor.GRAY
                        + "Thanks for opening an account with us! You can store up to 6 items in your vault.");
              } else if (b.getWallet() < 25000 && b.getVaultLevel() == 0) {
                p.closeInventory();
                p.sendMessage(
                    NPC.getNPC("Vault Keeper").getDisplayName()
                        + ": "
                        + ChatColor.RED
                        + "Sorry! You don't have enough funds.");
              } else if (b.getVaultLevel() != 0) {
                p.closeInventory();
                p.sendMessage(
                    NPC.getNPC("Vault Keeper").getDisplayName()
                        + ": "
                        + ChatColor.RED
                        + "We can only offer you one vault.");
              }
            }

            case REDSTONE_BLOCK -> {
              p.closeInventory();
            }
          }
        }
      }
    }
  }
}
