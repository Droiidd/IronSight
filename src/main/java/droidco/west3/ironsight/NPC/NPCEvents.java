package droidco.west3.ironsight.NPC;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Contracts.UI.ContractUI;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Items.CustomItem;
import droidco.west3.ironsight.Tracker.TrackerUI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import java.net.http.WebSocket;

public class NPCEvents implements Listener {
    @EventHandler
    public void npcRightClick(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        String clickedNPCname = e.getRightClicked().getCustomName();
        NPC clickedNPC = NPC.getNPC(ChatColor.stripColor(clickedNPCname));
        Bandit b = Bandit.getPlayer(p);
        switch (clickedNPC.getType()) {
            case ARMORER -> {
                p.openInventory(NPCUI.armorerUI(p));
                break;
            }
            case ILLEGAL_ARMORER -> {
                p.openInventory(NPCUI.illegalArmorerUI(p));
                break;
            }
            case SHOPKEEPER -> {
                p.openInventory(NPCUI.shopkeeperUI(p));
                break;
            }
            case FISHERMAN -> {
                p.openInventory(NPCUI.fishermanUI(p));
                break;
            }
            case PHARMACIST -> {
                p.openInventory(NPCUI.pharmacistUI(p));
                break;
            }
            case OFFICER_ARMS_DEALER -> {
                p.openInventory(NPCUI.officerArmsUI(p));
                break;
            }
            case ARMS_DEALER -> {
                p.openInventory(NPCUI.armsDealerUI(p));
                break;
            }
            case ILL_ARMS_DEALER -> {
                p.openInventory(NPCUI.illegalArmsUI(p));
                break;
            }
            case GEOLOGIST -> {
                p.openInventory(NPCUI.geologistUI(p));
                break;
            }
            case STABLE_MANAGER -> {
                p.openInventory(NPCUI.stableManagerUI(p));
                break;
            }
            case CONDUCTOR -> {
                p.openInventory(NPCUI.conductorUI(p));
                break;
            }
            case FERRY_CAPTAIN -> {
                p.openInventory(NPCUI.ferryCaptainUI(p));
                break;
            }
            case BANKER -> {
                p.openInventory(NPCUI.bankerUI(p));
                break;

            }
            case VAULT_KEEPER -> {
                p.openInventory(NPCUI.vaultKeeperUI(p));
                break;
            }
            case CONTRACTOR -> {
                p.openInventory(ContractUI.openContractUi(p));
                break;
            }
            case CHIEF_OF_POLICE -> {
                p.openInventory(NPCUI.chiefUI(p));
                break;
            }

        }


    }
    @EventHandler
    public void traderMenuSelect(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Bandit b = Bandit.getPlayer(p);
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Shopkeeper")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Smoked Salmon").getMaterial()) == 0) {
                if (b.getWallet() >= CustomItem.getCustomItem("Smoked Salmon").getPurchasePrice()) {
                    b.updateWallet(-1 * CustomItem.getCustomItem("Smoked Salmon").getPurchasePrice());
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Purchased!"));
                    p.getInventory().addItem(CustomItem.getCustomItem("Smoked Salmon").getItemStack());
                } else {
                    p.closeInventory();
                    p.sendMessage("Not enough funds!");
                }

            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Charred Potato").getMaterial()) == 0) {
                if (b.getWallet() >= CustomItem.getCustomItem("Charred Potato").getPurchasePrice()) {
                    b.updateWallet(-1 * CustomItem.getCustomItem("Charred Potato").getPurchasePrice());
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Purchased!"));
                    p.getInventory().addItem(CustomItem.getCustomItem("Charred Potato").getItemStack());
                } else {
                    p.closeInventory();
                    p.sendMessage("Not enough funds!");
                }

            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Brown Stew").getMaterial()) == 0) {
                if (b.getWallet() >= CustomItem.getCustomItem("Brown Stew").getPurchasePrice()) {
                    b.updateWallet(-1 * CustomItem.getCustomItem("Brown Stew").getPurchasePrice());
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Purchased!"));
                    p.getInventory().addItem(CustomItem.getCustomItem("Brown Stew").getItemStack());
                } else {
                    p.closeInventory();
                    p.sendMessage("Not enough funds!");
                }

            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Cooked Fox").getMaterial()) == 0) {
                if (b.getWallet() >= CustomItem.getCustomItem("Cooked Fox").getPurchasePrice()) {
                    b.updateWallet(-1 * CustomItem.getCustomItem("Cooked Fox").getPurchasePrice());
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Purchased!"));
                    p.getInventory().addItem(CustomItem.getCustomItem("Cooked Fox").getItemStack());
                } else {
                    p.closeInventory();
                    p.sendMessage("Not enough funds!");
                }

            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Rabbit Stew").getMaterial()) == 0) {
                if (b.getWallet() >= CustomItem.getCustomItem("Rabbit Stew").getPurchasePrice()) {
                    b.updateWallet(-1 * CustomItem.getCustomItem("Rabbit Stew").getPurchasePrice());
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Purchased!"));
                    p.getInventory().addItem(CustomItem.getCustomItem("Rabbit Stew").getItemStack());
                } else {
                    p.closeInventory();
                    p.sendMessage("Not enough funds!");
                }

            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Cooked Rabbit").getMaterial()) == 0) {
                if (b.getWallet() >= CustomItem.getCustomItem("Cooked Rabbit").getPurchasePrice()) {
                    b.updateWallet(-1 * CustomItem.getCustomItem("Cooked Rabbit").getPurchasePrice());
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Purchased!"));
                    p.getInventory().addItem(CustomItem.getCustomItem("Cooked Rabbit").getItemStack());

                } else {
                    p.closeInventory();
                    p.sendMessage("Not enough funds!");
                }

            }


        }
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Bank Teller")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case EMERALD -> {
                    b.setDepositing(true);
                    p.closeInventory();
                    NPC.getNPC("Bank Teller").addShoppingPlayer(p);
                    p.sendMessage("How much money do you want to deposit?");

                }

                case EMERALD_BLOCK -> {
                    b.setWithdrawing(true);
                    p.closeInventory();
                    NPC.getNPC("Bank Teller").addShoppingPlayer(p);
                    p.sendMessage("How much money do you want to withdraw?");

                }
            }



        }
    }
    @EventHandler
    public void bankInteraction(PlayerChatEvent e) {
        Player p = e.getPlayer();
        Bandit b = Bandit.getPlayer(p);
        if (NPC.getShoppingPlayers().containsKey(p.getUniqueId().toString())) {

            NPC shop = NPC.getShoppingPlayers().get(p.getUniqueId().toString());

            double d = Double.parseDouble(e.getMessage());

            if (shop.getType() == NPCType.BANKER) {
                if (b.isDepositing()) {
                    if (d > b.getWallet()) {

                        p.sendMessage("You don't have that much money in your wallet!");
                    }

                    else {

                        p.sendMessage("You have deposited " + d + "g!");
                        b.updateWallet(-1 * d);
                        b.updateBank(d);
                    }
                    b.setDepositing(false);
                }
                if (b.isWithdrawing()) {
                    if (d > b.getBank()) {

                        p.sendMessage("You don't have that much money in your bank!");
                    }

                    else {

                        p.sendMessage("You have made a withdrawal of " + d + "g!");
                        b.updateBank(-1 * d);
                        b.updateWallet(d);
                    }
                }

                NPC.getShoppingPlayers().remove(p.getUniqueId().toString());
            }

        }
    }
}
