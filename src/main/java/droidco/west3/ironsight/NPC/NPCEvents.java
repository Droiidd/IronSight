package droidco.west3.ironsight.NPC;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Items.CustomItem;
import droidco.west3.ironsight.Tracker.TrackerUIs;
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
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
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
        NPC clickedNPC = NPC.getNPC(clickedNPCname);
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
                p.openInventory(NPCUI.contractorUI(p));
                break;
            }
            case CHIEF_OF_POLICE -> {
                p.openInventory(NPCUI.chiefUI(p));
                break;
            }

        }


    }

    public void traderMenuSelect(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Bandit b = Bandit.getPlayer(p);
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Shopkeeper")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Smoked Salmon").getMaterial()) == 0) {
                if (b.getWallet() >= CustomItem.getCustomItem("Smoked Salmon").getSalePrice()) {
                    b.updateWallet(-1 * CustomItem.getCustomItem("Smoked Salmon").getSalePrice());
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Purchased!"));
                } else {
                    p.closeInventory();
                    p.sendMessage("Not enough funds!");
                }

            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Charred Potato").getMaterial()) == 0) {
                if (b.getWallet() >= CustomItem.getCustomItem("Charred Potato").getSalePrice()) {
                    b.updateWallet(-1 * CustomItem.getCustomItem("Charred Potato").getSalePrice());
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Purchased!"));
                } else {
                    p.closeInventory();
                    p.sendMessage("Not enough funds!");
                }

            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Brown Stew").getMaterial()) == 0) {
                if (b.getWallet() >= CustomItem.getCustomItem("Brown Stew").getSalePrice()) {
                    b.updateWallet(-1 * CustomItem.getCustomItem("Brown Stew").getSalePrice());
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Purchased!"));
                } else {
                    p.closeInventory();
                    p.sendMessage("Not enough funds!");
                }

            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Cooked Fox").getMaterial()) == 0) {
                if (b.getWallet() >= CustomItem.getCustomItem("Cooked Fox").getSalePrice()) {
                    b.updateWallet(-1 * CustomItem.getCustomItem("Cooked Fox").getSalePrice());
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Purchased!"));
                } else {
                    p.closeInventory();
                    p.sendMessage("Not enough funds!");
                }

            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Rabbit Stew").getMaterial()) == 0) {
                if (b.getWallet() >= CustomItem.getCustomItem("Rabbit Stew").getSalePrice()) {
                    b.updateWallet(-1 * CustomItem.getCustomItem("Rabbit Stew").getSalePrice());
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Purchased!"));
                } else {
                    p.closeInventory();
                    p.sendMessage("Not enough funds!");
                }

            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Cooked Rabbit").getMaterial()) == 0) {
                if (b.getWallet() >= CustomItem.getCustomItem("Cooked Rabbit").getSalePrice()) {
                    b.updateWallet(-1 * CustomItem.getCustomItem("Cooked Rabbit").getSalePrice());
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Purchased!"));

                } else {
                    p.closeInventory();
                    p.sendMessage("Not enough funds!");
                }

            }


        }


    }
}
