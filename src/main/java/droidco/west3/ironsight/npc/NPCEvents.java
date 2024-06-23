package droidco.west3.ironsight.npc;

import droidco.west3.ironsight.bandit.Bandit;
import droidco.west3.ironsight.contracts.ui.ContractUI;
import droidco.west3.ironsight.globals.utils.BanditUtils;
import droidco.west3.ironsight.globals.utils.GlobalUtils;
import droidco.west3.ironsight.horse.FrontierHorse;
import droidco.west3.ironsight.horse.FrontierHorseType;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.items.CustomItem;
import droidco.west3.ironsight.items.ItemIcon;
import droidco.west3.ironsight.items.looting.ItemTable;
import droidco.west3.ironsight.items.potions.CustomPotion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class NPCEvents implements Listener {

    private IronSight plugin;

    public NPCEvents(IronSight plugin) {
        plugin = plugin;
    }

    @EventHandler
    public void npcRightClick(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        Bandit b = Bandit.getPlayer(p);


        if (e.getRightClicked().getType().equals(EntityType.VILLAGER)) {
            String clickedNPCname = e.getRightClicked().getCustomName();
            NPC clickedNPC = NPC.getNPC(ChatColor.stripColor(clickedNPCname) + b.getCurrentLocation().getLocName());
            if (clickedNPC != null) {
                e.setCancelled(true);
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
                        p.openInventory(NPCUI.openPharmacistUI(p));
                    }
                    case OFFICER_ARMS_DEALER -> {
                        //p.openInventory(NPCUI.officerArmsUI(p));
                    }
                    case ARMS_DEALER -> {
                        p.openInventory(NPCUI.armsDealerUI(p));
                    }
                    case ILL_ARMS_DEALER -> {
                        p.openInventory(NPCUI.illegalArmsDealerUI(p));
                    }
                    case GEOLOGIST -> {
                        p.openInventory(NPCUI.geologistUI(p));
                    }
                    case STABLE_MANAGER -> {
                        p.openInventory(NPCUI.stableManagerUI(p));
                    }
                    case CONDUCTOR -> {
                        p.openInventory(NPCUI.conductorUI(p));
                    }
                    case FERRY_CAPTAIN -> {
                        p.openInventory(NPCUI.ferryCaptainUI(p));
                    }
                    case BANKER -> {
                        p.openInventory(NPCUI.openBankerUI(p));

                    }
                    case VAULT_KEEPER -> {
                        p.openInventory(NPCUI.vaultKeeperUI(p));
                    }
                    case CONTRACTOR -> {
                        if (b.getActiveContract() == null) {
                            p.openInventory(ContractUI.openContractOptionsUi(p));
                        } else {
                            p.openInventory(ContractUI.openContractorInfo(p, true));
                        }

                    }
                    case CHIEF_OF_POLICE -> {
                        p.openInventory(NPCUI.chiefUI(p));
                    }
                }
            }
        }
    }

    @EventHandler
    public void processorRightClick(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        if (e.getRightClicked().getType().equals(EntityType.VILLAGER)) {
            String clickedNPCname = ChatColor.stripColor(e.getRightClicked().getCustomName());
            switch (clickedNPCname) {
                case "Smoke leaf processor 1" -> {
                    p.openInventory(NPCUI.openSmokeleafProcessor(p, 1));
                }
                case "Smoke leaf processor 2" -> {
                    p.openInventory(NPCUI.openSmokeleafProcessor(p, 2));
                }
                case "Smoke leaf processor 3" -> {
                    p.openInventory(NPCUI.openSmokeleafProcessor(p, 3));
                }
                case "Spice processor 1" -> {
                    p.openInventory(NPCUI.openSpiceProcessor(p, 1));
                }
                case "Spice processor 2" -> {
                    p.openInventory(NPCUI.openSpiceProcessor(p, 2));
                }
                case "Spice processor 3" -> {
                    p.openInventory(NPCUI.openSpiceProcessor(p, 3));
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
                purchaseItem(b, p, CustomItem.getCustomItem("Smoked Salmon"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Charred Potato").getMaterial()) == 0) {
                purchaseItem(b, p, CustomItem.getCustomItem("Charred Potato"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Brown Stew").getMaterial()) == 0) {
                purchaseItem(b, p, CustomItem.getCustomItem("Brown Stew"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Cooked Fox").getMaterial()) == 0) {
                purchaseItem(b, p, CustomItem.getCustomItem("Cooked Fox"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Rabbit Stew").getMaterial()) == 0) {
                purchaseItem(b, p, CustomItem.getCustomItem("Rabbit Stew"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Cooked Rabbit").getMaterial()) == 0) {
                purchaseItem(b, p, CustomItem.getCustomItem("Cooked Rabbit"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Bandage").getMaterial()) == 0) {
                purchaseItem(b, p, CustomItem.getCustomItem("Bandage"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Splint").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Splint"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Tracker").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Tracker"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
        }
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Bank Teller")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case EMERALD_BLOCK -> {
                    b.setDepositing(true);
                    p.closeInventory();
                    NPC.getNPC("Bank Teller" + b.getCurrentLocation().getLocName()).addShoppingPlayer(p);
                    p.sendMessage(NPC.getNPC("Bank Teller" + b.getCurrentLocation().getLocName()).getDisplayName() + ChatColor.GRAY + ": How much money do you want to deposit?");

                }

                case REDSTONE_BLOCK -> {
                    b.setWithdrawing(true);
                    p.closeInventory();
                    NPC.getNPC("Bank Teller" + b.getCurrentLocation().getLocName()).addShoppingPlayer(p);
                    p.sendMessage(NPC.getNPC("Bank Teller" + b.getCurrentLocation().getLocName()).getDisplayName() + ChatColor.GRAY + ": How much money do you want to withdraw?");

                }
            }
        }
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Geologist")) {
            NPC npc = NPC.getNPC("Geologist" + b.getCurrentLocation().getLocName());
            e.setCancelled(true);
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Broken Pick").getMaterial()) == 0) {
                purchaseItem(b, p, CustomItem.getCustomItem("Broken Pick"), NPC.getNPC("Geologist" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Old Miner's Pick").getMaterial()) == 0) {
                purchaseItem(b, p, CustomItem.getCustomItem("Old Miner's Pick"), NPC.getNPC("Geologist" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Explorer's Pick").getMaterial()) == 0) {
                purchaseItem(b, p, CustomItem.getCustomItem("Explorer's Pick"), NPC.getNPC("Geologist" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().compareTo(ItemIcon.getIcon("open_geode").getItem().getType()) == 0) {
                if (p.getInventory().containsAtLeast(CustomItem.getCustomItem("Geode").getItemStack(), 1)) {
                    p.getInventory().remove(CustomItem.getCustomItem("Geode").getItemStack());
                    openGeode(75, p, b, "Geode", npc);
                } else if (p.getInventory().containsAtLeast(CustomItem.getCustomItem("Crystalized Geode").getItemStack(), 1)) {
                    p.getInventory().remove(CustomItem.getCustomItem("Geode").getItemStack());
                    openGeode(75, p, b, "Crystalized Geode", npc);
                } else {
                    p.closeInventory();
                    p.sendMessage(ChatColor.RED + "No geodes to open!");
                }

            }

        }
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Pharmacist")) {
            e.setCancelled(true);
            if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Medicine")) {
                purchasePotion(b, p, CustomPotion.getCustomPotion("Medicine"), NPC.getNPC("Pharmacist" + b.getCurrentLocation().getLocName()));
            }
            if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Whiskey")) {
                purchasePotion(b, p, CustomPotion.getCustomPotion("Whiskey"), NPC.getNPC("Pharmacist" + b.getCurrentLocation().getLocName()));
            }
            if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Morphine")) {
                purchasePotion(b, p, CustomPotion.getCustomPotion("Morphine"), NPC.getNPC("Pharmacist" + b.getCurrentLocation().getLocName()));
            }

        }
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Fisherman")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Sea Slug").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Sea Slug"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Hermit Crab").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Hermit Crab"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CustomItem.getCustomItem("Wooden Fishing Rod").getItemStack().getItemMeta().getDisplayName())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Wooden Fishing Rod"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CustomItem.getCustomItem("Steel Lined Rod").getItemStack().getItemMeta().getDisplayName())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Steel Lined Rod"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CustomItem.getCustomItem("Expedition Rod").getItemStack().getItemMeta().getDisplayName())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Expedition Rod"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
        }
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Armorer")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Farm Hand Boots").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Farm Hand Boots"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Farm Hand Shirt").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Farm Hand Shirt"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Farm Hand Chaps").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Farm Hand Chaps"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Huntsmen Boots").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Huntsmen Boots"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Huntsmen Jacket").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Huntsmen Jacket"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Huntsmen Trousers").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Huntsmen Trousers"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Farm Hand Hat").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Farm Hand Hat"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Huntsmen Hat").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Huntsmen Hat"), NPC.getNPC("Shopkeeper" + b.getCurrentLocation().getLocName()));
            }
        }
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Illegal Armorer")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Frontier Boots").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Frontier Boots"), NPC.getNPC("Illegal Armorer" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Frontier Duster").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Frontier Duster"), NPC.getNPC("Illegal Armorer" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Frontier Pants").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Frontier Pants"), NPC.getNPC("Illegal Armorer" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Frontier Hat").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Frontier Hat"), NPC.getNPC("Illegal Armorer" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Journeymen Boots").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Journeymen Boots"), NPC.getNPC("Illegal Armorer" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Journeymen Pants").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Journeymen Pants"), NPC.getNPC("Illegal Armorer" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Journeymen Duster").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Journeymen Duster"), NPC.getNPC("Illegal Armorer" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Journeymen Hat").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Journeymen Hat"), NPC.getNPC("Illegal Armorer" + b.getCurrentLocation().getLocName()));
            }
        }
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Arms Dealer")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Colt Patterson").getMaterial())) {
                purchaseFirearm(b, p, CustomItem.getCustomItem("Colt Patterson"), NPC.getNPC("Arms Dealer" + b.getCurrentLocation().getLocName()), "coltpatterson");
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Sharps Rifle").getMaterial())) {
                purchaseFirearm(b, p, CustomItem.getCustomItem("Sharps Rifle"), NPC.getNPC("Arms Dealer" + b.getCurrentLocation().getLocName()), "sharps");
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("66 Winchester").getMaterial())) {
                purchaseFirearm(b, p, CustomItem.getCustomItem("66 Winchester"), NPC.getNPC("Arms Dealer" + b.getCurrentLocation().getLocName()), "winchester");
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Henry Model 3").getMaterial())) {
                purchaseFirearm(b, p, CustomItem.getCustomItem("Henry Model 3"), NPC.getNPC("Arms Dealer" + b.getCurrentLocation().getLocName()), "henry");
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Winchester 1873").getMaterial())) {
                purchaseFirearm(b, p, CustomItem.getCustomItem("Winchester 1873"), NPC.getNPC("Arms Dealer" + b.getCurrentLocation().getLocName()), "winchesterillegal");
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Rifle Ammo").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Rifle Ammo"), NPC.getNPC("Arms Dealer" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Pistol Ammo").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Pistol Ammo"), NPC.getNPC("Arms Dealer" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Shotgun Ammo").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Shotgun Ammo"), NPC.getNPC("Arms Dealer" + b.getCurrentLocation().getLocName()));
            }
        }
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Illegal Arms Dealer")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Colt Navy").getMaterial())) {
                purchaseFirearm(b, p, CustomItem.getCustomItem("Colt Navy"), NPC.getNPC("Illegal Arms Dealer" + b.getCurrentLocation().getLocName()), "coltnavy");
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Navy 1851 OKH").getMaterial())) {
                purchaseFirearm(b, p, CustomItem.getCustomItem("Navy 1851 OKH"), NPC.getNPC("Illegal Arms Dealer" + b.getCurrentLocation().getLocName()), "Navy 1851");
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Sawed-Off Shotgun").getMaterial())) {
                purchaseFirearm(b, p, CustomItem.getCustomItem("Sawed-Off Shotgun"), NPC.getNPC("Illegal Arms Dealer" + b.getCurrentLocation().getLocName()), "sawed");
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Winchester 1873").getMaterial())) {
                purchaseFirearm(b, p, CustomItem.getCustomItem("Winchester 1873"), NPC.getNPC("Illegal Arms Dealer" + b.getCurrentLocation().getLocName()), "winchesterillegal");
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Rifle Ammo").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Rifle Ammo"), NPC.getNPC("Illegal Arms Dealer" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Pistol Ammo").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Pistol Ammo"), NPC.getNPC("Illegal Arms Dealer" + b.getCurrentLocation().getLocName()));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Shotgun Ammo").getMaterial())) {
                purchaseItem(b, p, CustomItem.getCustomItem("Shotgun Ammo"), NPC.getNPC("Illegal Arms Dealer" + b.getCurrentLocation().getLocName()));
            }
        }
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Stable Manager")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CustomItem.getCustomItem("Standard").getItemStack().getItemMeta().getDisplayName())) {
                purchaseHorse(b, p, CustomItem.getCustomItem("Standard"), NPC.getNPC("Stable Manager" + b.getCurrentLocation().getLocName()), FrontierHorseType.STANDARD);
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CustomItem.getCustomItem("Thoroughbred").getItemStack().getItemMeta().getDisplayName())) {
                purchaseHorse(b, p, CustomItem.getCustomItem("Thoroughbred"), NPC.getNPC("Stable Manager" + b.getCurrentLocation().getLocName()), FrontierHorseType.THOROUGHBRED);
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Donkey").getMaterial())) {
                purchaseHorse(b, p, CustomItem.getCustomItem("Donkey"), NPC.getNPC("Stable Manager" + b.getCurrentLocation().getLocName()), FrontierHorseType.DONKEY);
            }
        }
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Vault Keeper")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case ENDER_CHEST -> {
                    if (b.getVaultSize() != 0) {
                        p.openInventory(BanditUtils.vaultUI(p));
                    } else {
                        p.closeInventory();
                        p.sendMessage(NPC.getNPC("Vault Keeper" + b.getCurrentLocation().getLocName()).getDisplayName() + ": " + ChatColor.RED + "You don't have an item vault!");

                    }
                }
                case CHEST -> {
                    p.openInventory(BanditUtils.newVaultUI(p));
                }
                case LIME_BANNER -> {
                    if (b.getVaultSize() != 0) {
                        p.openInventory(BanditUtils.vaultUpgradeUI(p));
                    } else {
                        p.closeInventory();
                        p.sendMessage(NPC.getNPC("Vault Keeper" + b.getCurrentLocation().getLocName()).getDisplayName() + ": " + ChatColor.RED + "You don't have an item vault!");

                    }
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
                if (NPC.getShoppingPlayers().containsKey(p.getUniqueId().toString())) {
                    p.sendMessage("ERROR");
                }
                if (b.isDepositing()) {
                    e.setCancelled(true);
                    b.setDepositing(false);
                    if (d > b.getWallet()) {
                        p.sendMessage(NPC.getNPC("Bank Teller" + b.getCurrentLocation().getLocName()).getDisplayName() + ChatColor.RED + ": You don't have enough funds!");
                    } else {
                        p.sendMessage(NPC.getNPC("Bank Teller" + b.getCurrentLocation().getLocName()).getDisplayName() + ChatColor.GRAY + ": You have deposited " + ChatColor.GREEN + d + "g!");
                        b.updateWallet(-1 * d);
                        b.updateBank(d);
                    }
                }
                if (b.isWithdrawing()) {
                    b.setWithdrawing(false);
                    e.setCancelled(true);
                    if (d > b.getBank()) {
                        p.sendMessage(NPC.getNPC("Bank Teller" + b.getCurrentLocation().getLocName()).getDisplayName() + ChatColor.RED + ": You don't have enough funds!");
                    } else {
                        p.sendMessage(NPC.getNPC("Bank Teller" + b.getCurrentLocation().getLocName()).getDisplayName() + ChatColor.GRAY + ": You have withdrew " + ChatColor.GREEN + d + "g!");
                        b.updateBank(-1 * d);
                        b.updateWallet(d);
                    }
                }

            }

        }
    }

    public void purchaseItem(Bandit b, Player p, CustomItem item, NPC npc) {
        int amount = item.getAmountForSale();
        if (b.getWallet() >= item.getPurchasePrice() * amount) {
            b.updateWallet(-1 * item.getPurchasePrice() * amount);
            p.sendMessage(ChatColor.GREEN + "Purchased " + ChatColor.WHITE + amount + " " + item.getItemStack().getItemMeta().getDisplayName());
            //item.getItemStack().setAmount(amount);
            p.getInventory().addItem(item.getItemStack());
        } else {
            p.closeInventory();
            p.sendMessage(npc.getDisplayName() + ChatColor.GRAY + ": Not enough funds!");
        }
    }

    public void purchasePotion(Bandit b, Player p, CustomPotion item, NPC npc) {
        int amount = item.getAmountForSale();
        if (b.getWallet() >= item.getPurchasePrice() * amount) {
            b.updateWallet(-1 * item.getPurchasePrice() * amount);
            p.sendMessage(ChatColor.GREEN + "Purchased " + ChatColor.WHITE + amount + " " + item.getItemStack().getItemMeta().getDisplayName());
            //item.getItemStack().setAmount(amount);
            p.getInventory().addItem(item.getItemStack());
        } else {
            p.closeInventory();
            p.sendMessage(npc.getDisplayName() + ChatColor.GRAY + ": Not enough funds!");
        }
    }

    public void purchaseFirearm(Bandit b, Player p, CustomItem item, NPC npc, String gunName) {

        if (b.getWallet() >= item.getPurchasePrice()) {
            b.updateWallet(-1 * item.getPurchasePrice());
            p.sendMessage(ChatColor.GREEN + "Purchased x" + item.getItemStack().getItemMeta().getDisplayName());
            String weapon = "shot give " + p.getDisplayName() + " " + gunName;

            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Bukkit.dispatchCommand(console, weapon);
        } else {
            p.closeInventory();
            p.sendMessage(npc.getDisplayName() + ChatColor.GRAY + ": Not enough funds!");
        }
    }

    public void purchaseHorse(Bandit b, Player p, CustomItem item, NPC npc, FrontierHorseType type) {
        boolean availForPurchase = false;
        switch (type) {
            case STANDARD -> {
                availForPurchase = true;
            }
            case THOROUGHBRED -> {
                if (b.getContractorLvl() > 6) {
                    availForPurchase = true;
                } else {
                    p.sendMessage(npc.getDisplayName() + ChatColor.GRAY + ": Level [" + BanditUtils.getContractorLvlColor(6) + "] or higher required!");
                }
            }
            case DONKEY -> {
                if (b.getContractorLvl() > 8) {
                    availForPurchase = true;
                } else {
                    p.sendMessage(npc.getDisplayName() + ChatColor.GRAY + ": Level [" + BanditUtils.getContractorLvlColor(8) + "] or higher required!");
                }
            }
        }
        if (availForPurchase) {
            if (b.getWallet() >= item.getPurchasePrice()) {
                if (b.getHorses().size() < 3) {
                    b.updateWallet(-1 * item.getPurchasePrice());
                    p.sendMessage(ChatColor.GREEN + "Purchased " + item.getItemStack().getItemMeta().getDisplayName());
                    b.getHorses().add(new FrontierHorse(p.getUniqueId().toString(), GlobalUtils.getHorseTypeString(type) + (b.getHorses().size() + 1), type));
                } else {
                    p.closeInventory();
                    p.sendMessage(npc.getDisplayName() + ChatColor.GRAY + ": You have the " + ChatColor.RED + "max amount" + ChatColor.GRAY + " of horses!");
                }
            } else {
                p.closeInventory();
                p.sendMessage(npc.getDisplayName() + ChatColor.GRAY + ": Not enough funds!");
            }
        }
    }

    public void openGeode(double geodeCost, Player p, Bandit b, String geodeType, NPC npc) {
        if (b.getWallet() >= geodeCost) {
            b.updateWallet(-1 * geodeCost);
            ArrayList<ItemStack> contents = ItemTable.getTable(geodeType).getNumItems(10);
            int geodeChoice = GlobalUtils.getRandomNumber(contents.size());
            p.getInventory().addItem(contents.get(geodeChoice));
            p.getInventory().removeItem(CustomItem.getCustomItem(geodeType).getItemStack());
            p.sendMessage(ChatColor.GRAY + "Geode opened... " + ChatColor.WHITE + "+ " + contents.get(geodeChoice).getAmount() + " " + contents.get(geodeChoice).getItemMeta().getDisplayName());
        } else {
            p.sendMessage(npc.getDisplayName() + ChatColor.GRAY + ": Not enough funds!");
            p.closeInventory();
        }

    }


}
