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
import org.bukkit.entity.Entity;
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
        if(e.getRightClicked().getType().equals(EntityType.VILLAGER)){
            String clickedNPCname = e.getRightClicked().getCustomName();
            NPC clickedNPC = NPC.getNPC(ChatColor.stripColor(clickedNPCname));
            if(clickedNPC != null){
                e.setCancelled(true);
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
                        p.openInventory(NPCUI.openBankerUI(p));
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


        }


    }
    @EventHandler
    public void traderMenuSelect(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Bandit b = Bandit.getPlayer(p);
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Shopkeeper")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Smoked Salmon").getMaterial()) == 0) {
                purchaseItem(b,p,CustomItem.getCustomItem("Smoked Salmon"),NPC.getNPC("Shopkeeper"));

            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Charred Potato").getMaterial()) == 0) {
                purchaseItem(b,p,CustomItem.getCustomItem("Charred Potato"),NPC.getNPC("Shopkeeper"));

            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Brown Stew").getMaterial()) == 0) {
                purchaseItem(b,p,CustomItem.getCustomItem("Brown Stew"),NPC.getNPC("Shopkeeper"));

            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Cooked Fox").getMaterial()) == 0) {
                purchaseItem(b,p,CustomItem.getCustomItem("Cooked Fox"),NPC.getNPC("Shopkeeper"));

            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Rabbit Stew").getMaterial()) == 0) {
                purchaseItem(b,p,CustomItem.getCustomItem("Rabbit Stew"),NPC.getNPC("Shopkeeper"));

            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Cooked Rabbit").getMaterial()) == 0) {
                purchaseItem(b,p,CustomItem.getCustomItem("Cooked Rabbit"),NPC.getNPC("Shopkeeper"));
            }
        }
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Bank Teller")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case EMERALD_BLOCK -> {
                    b.setDepositing(true);
                    p.closeInventory();
                    NPC.getNPC("Bank Teller").addShoppingPlayer(p);
                    p.sendMessage(NPC.getNPC("Bank Teller").getDisplayName()+ChatColor.GRAY+ ": How much money do you want to deposit?");

                }

                case REDSTONE_BLOCK -> {
                    b.setWithdrawing(true);
                    p.closeInventory();
                    NPC.getNPC("Bank Teller").addShoppingPlayer(p);
                    p.sendMessage(NPC.getNPC("Bank Teller").getDisplayName()+ChatColor.GRAY+ ": How much money do you want to withdraw?");

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
                NPC.getShoppingPlayers().remove(p.getUniqueId().toString());
                if(NPC.getShoppingPlayers().containsKey(p.getUniqueId().toString())){
                    p.sendMessage("ERROR");
                }
                if (b.isDepositing()) {
                    b.setDepositing(false);
                    if (d > b.getWallet()) {
                        p.sendMessage(NPC.getNPC("Bank Teller").getDisplayName()+ChatColor.RED+ ": You don't have enough funds!");
                    }
                    else {
                        p.sendMessage(NPC.getNPC("Bank Teller").getDisplayName()+ChatColor.GRAY+ ": You have deposited "+ d + "g!");
                        b.updateWallet(-1 * d);
                        b.updateBank(d);
                    }
                }
                if (b.isWithdrawing()) {
                    b.setWithdrawing(false);
                    if (d > b.getBank()) {
                        p.sendMessage(NPC.getNPC("Bank Teller").getDisplayName()+ChatColor.RED+ ": You don't have enough funds!");
                    }
                    else {
                        p.sendMessage(NPC.getNPC("Bank Teller").getDisplayName()+ChatColor.GRAY+ ": You have withdrew "+ d + "g!");
                        b.updateBank(-1 * d);
                        b.updateWallet(d);
                    }
                }

            }

        }
    }
    public void purchaseItem(Bandit b, Player p, CustomItem item, NPC npc )
    {
        if (b.getWallet() >= item.getPurchasePrice()) {
            b.updateWallet(-1 * item.getPurchasePrice());
            p.sendMessage(ChatColor.GREEN + "Purchased "+item.getItemStack().getItemMeta().getDisplayName());
            p.getInventory().addItem(item.getItemStack());
        } else {
            p.closeInventory();
            p.sendMessage( npc.getDisplayName()+ ChatColor.GRAY+ ": Not enough funds!");
        }
    }
}
