package droidco.west3.ironsight.Bandit.Events;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.NPC.NPC;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.Arrays;

public class VaultEvents implements Listener {
    @EventHandler

    public void vaultClick(InventoryClickEvent e) {

    }

    @EventHandler
    public void vaultItemSave(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        Bandit b = Bandit.getPlayer(p);
        if (e.getView().getTitle().equalsIgnoreCase(p.getDisplayName() + ChatColor.DARK_AQUA + " Vault")) {
            b.setItemVault(Arrays.stream(e.getInventory().getContents()).toList());
            p.closeInventory();
        }
    }

    @EventHandler
    public void vaultUpgradeMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Bandit b = Bandit.getPlayer(p);
        if (e.getView().getTitle().equalsIgnoreCase(p.getDisplayName() + ChatColor.DARK_AQUA + " Upgrade Vault")) {
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);
                switch (e.getCurrentItem().getType()) {
                    case EMERALD_BLOCK -> {
                        if (b.getWallet() >= 50000 && b.getVaultSize() < 54) {
                            b.setVaultSize(b.getVaultSize() + 9);
                            p.sendMessage(NPC.getNPC("Vault Keeper").getDisplayName() + ": " + ChatColor.GRAY + "Thanks for purchasing a vault upgrade! You now have " + b.getVaultSize() + " slots!");
                        } else if (b.getWallet() < 50000 && b.getVaultSize() < 54) {
                            p.closeInventory();
                            p.sendMessage(NPC.getNPC("Vault Keeper").getDisplayName() + ": " + ChatColor.RED + "You don't have enough funds!");
                        } else if (b.getVaultSize() == 54) {
                            p.closeInventory();
                            p.sendMessage(NPC.getNPC("Vault Keeper").getDisplayName() + ": " + ChatColor.RED + "There are no more upgrades available!");
                        }
                    }
                    case REDSTONE_BLOCK -> {
                        p.closeInventory();
                    }
                }
            }
        }


    }

    @EventHandler
    public void openVaultAccountMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Bandit b = Bandit.getPlayer(p);
        if (e.getView().getTitle().equalsIgnoreCase(p.getDisplayName() + ": " + ChatColor.DARK_AQUA + "Open a Vault Account")) {
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);
                switch (e.getCurrentItem().getType()) {
                    case EMERALD_BLOCK -> {
                        if (b.getWallet() >= 25000 && b.getVaultSize() == 0) {
                            b.setVaultSize(9);
                            b.updateWallet(-1 * 25000);
                            p.closeInventory();
                            p.sendMessage(NPC.getNPC("Vault Keeper").getDisplayName() + ChatColor.GRAY + ": Thanks for opening an account with us! You can now store up to 9 items in your vault.");
                        } else if (b.getWallet() < 25000 && b.getVaultSize() == 0) {
                            p.closeInventory();
                            p.sendMessage(NPC.getNPC("Vault Keeper").getDisplayName() + ": " + ChatColor.RED + "Sorry! You aren't worth doing business with...");
                        } else if (b.getVaultSize() != 0) {
                            p.closeInventory();
                            p.sendMessage(NPC.getNPC("Vault Keeper").getDisplayName() + ": " + ChatColor.RED + "We can only offer you one vault.");
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