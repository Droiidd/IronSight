package droidco.west3.ironsight.NPC;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Contracts.UI.ContractUI;
import droidco.west3.ironsight.Globals.Utils.BanditUtils;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.Horse.FrontierHorse;
import droidco.west3.ironsight.Horse.FrontierHorseType;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Items.CustomItem;
import droidco.west3.ironsight.Items.ItemIcon;
import droidco.west3.ironsight.Items.ItemTable;
import droidco.west3.ironsight.Processors.LoadProcessor;
import droidco.west3.ironsight.Processors.Processor;
import droidco.west3.ironsight.Processors.ProcessorTask;
import droidco.west3.ironsight.Tracker.TrackerUI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.ConsoleCommandSender;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.List;

public class NPCEvents implements Listener {

    private IronSight plugin;

    public NPCEvents(IronSight plugin){
        plugin = plugin;
    }
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
    public void processorRightClick(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        if(e.getRightClicked().getType().equals(EntityType.VILLAGER)) {
            String clickedNPCname = ChatColor.stripColor(e.getRightClicked().getCustomName());
            switch (clickedNPCname) {
                case "Smokeleaf Processor 1" -> {
                    p.openInventory(NPCUI.openSmokeleafProcessor(p, 1));
                }
                case "Smokeleaf Processor 2" -> {
                    p.openInventory(NPCUI.openSmokeleafProcessor(p, 2));
                }
                case "Smokeleaf Processor 3" -> {
                    p.openInventory(NPCUI.openSmokeleafProcessor(p, 3));
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
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Bandage").getMaterial()) == 0) {
                purchaseItem(b,p,CustomItem.getCustomItem("Bandage"),NPC.getNPC("Shopkeeper"));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Splint").getMaterial()) ) {
                purchaseItem(b,p,CustomItem.getCustomItem("Splint"),NPC.getNPC("Shopkeeper"));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Tracker").getMaterial())) {
                purchaseItem(b,p,CustomItem.getCustomItem("Tracker"),NPC.getNPC("Shopkeeper"));
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
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Geologist")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Broken Pick").getMaterial()) == 0) {
                purchaseItem(b,p,CustomItem.getCustomItem("Broken Pick"),NPC.getNPC("Geologist"));
            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Old Miner's Pick").getMaterial()) == 0) {
                purchaseItem(b,p,CustomItem.getCustomItem("Old Miner's Pick"),NPC.getNPC("Shopkeeper"));
            }
            if (e.getCurrentItem().getType().compareTo(CustomItem.getCustomItem("Explorer's Pick").getMaterial()) == 0) {
                purchaseItem(b,p,CustomItem.getCustomItem("Explorer's Pick"),NPC.getNPC("Shopkeeper"));
            }
            if (e.getCurrentItem().getType().compareTo(ItemIcon.getIcon("open_geode").getItem().getType()) == 0) {
                if(p.getInventory().containsAtLeast(CustomItem.getCustomItem("geode").getItemStack(),1)){
                    p.getInventory().remove(CustomItem.getCustomItem("geode").getItemStack());
                    openGeode(75,p,b);
                }else{
                    p.closeInventory();
                    p.sendMessage(ChatColor.RED+"No geodes to open!");
                }

            }

        }
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA+"Fisherman")){
                e.setCancelled(true);
                if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Sea Slug").getMaterial()) ) {
                    purchaseItem(b,p,CustomItem.getCustomItem("Sea Slug"),NPC.getNPC("Shopkeeper"));
                }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Hermit Crab").getMaterial()) ) {
                purchaseItem(b,p,CustomItem.getCustomItem("Hermit Crab"),NPC.getNPC("Shopkeeper"));
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CustomItem.getCustomItem("Wooden Fishing Rod").getItemStack().getItemMeta().getDisplayName())) {
                purchaseItem(b,p,CustomItem.getCustomItem("Wooden Fishing Rod"),NPC.getNPC("Shopkeeper"));
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CustomItem.getCustomItem("Steel Lined Rod").getItemStack().getItemMeta().getDisplayName()) ) {
                purchaseItem(b,p,CustomItem.getCustomItem("Steel Lined Rod"),NPC.getNPC("Shopkeeper"));
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CustomItem.getCustomItem("Expedition Rod").getItemStack().getItemMeta().getDisplayName()) ) {
                purchaseItem(b,p,CustomItem.getCustomItem("Expedition Rod"),NPC.getNPC("Shopkeeper"));
            }
        }
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA+"Armorer")){
            e.setCancelled(true);
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Farm Hand Boots").getMaterial()) ) {
                purchaseItem(b,p,CustomItem.getCustomItem("Farm Hand Boots"),NPC.getNPC("Shopkeeper"));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Farm Hand Shirt").getMaterial()) ) {
                purchaseItem(b,p,CustomItem.getCustomItem("Farm Hand Shirt"),NPC.getNPC("Shopkeeper"));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Farm Hand Chaps").getMaterial()) ) {
                purchaseItem(b,p,CustomItem.getCustomItem("Farm Hand Chaps"),NPC.getNPC("Shopkeeper"));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Huntsmen Boots").getMaterial()) ) {
                purchaseItem(b,p,CustomItem.getCustomItem("Huntsmen Boots"),NPC.getNPC("Shopkeeper"));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Huntsmen Jacket").getMaterial()) ) {
                purchaseItem(b,p,CustomItem.getCustomItem("Huntsmen Jacket"),NPC.getNPC("Shopkeeper"));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Huntsmen Trousers").getMaterial()) ) {
                purchaseItem(b,p,CustomItem.getCustomItem("Huntsmen Trousers"),NPC.getNPC("Shopkeeper"));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Farm Hand Hat").getMaterial()) ) {
                purchaseItem(b,p,CustomItem.getCustomItem("Farm Hand Hat"),NPC.getNPC("Shopkeeper"));
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Huntsmen Hat").getMaterial()) ) {
                purchaseItem(b,p,CustomItem.getCustomItem("Huntsmen Hat"),NPC.getNPC("Shopkeeper"));
            }
        }
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA+"Arms Dealer")){
            e.setCancelled(true);
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Colt Patterson").getMaterial()) ) {
                purchaseFirearm(b,p,CustomItem.getCustomItem("Colt Patterson"),NPC.getNPC("Arms Dealer"),"coltpatterson");
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Sharps Rifle").getMaterial()) ) {
                purchaseFirearm(b,p,CustomItem.getCustomItem("Sharps Rifle"),NPC.getNPC("Arms Dealer"),"sharps");
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("66 Winchester").getMaterial()) ) {
                purchaseFirearm(b,p,CustomItem.getCustomItem("66 Winchester"),NPC.getNPC("Arms Dealer"),"winchester");
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Henry Model 3").getMaterial()) ) {
                purchaseFirearm(b,p,CustomItem.getCustomItem("Henry Model 3"),NPC.getNPC("Arms Dealer"),"henry");
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Winchester 1873").getMaterial()) ) {
                purchaseFirearm(b,p,CustomItem.getCustomItem("Winchester 1873"),NPC.getNPC("Arms Dealer"),"winchesterillegal");
            }
        }
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA+"Stable Manager")){
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CustomItem.getCustomItem("Standard").getItemStack().getItemMeta().getDisplayName())) {
                purchaseHorse(b,p,CustomItem.getCustomItem("Standard"),NPC.getNPC("Stable Manager"),FrontierHorseType.STANDARD);
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CustomItem.getCustomItem("Thoroughbred").getItemStack().getItemMeta().getDisplayName())) {
                purchaseHorse(b,p,CustomItem.getCustomItem("Thoroughbred"),NPC.getNPC("Stable Manager"),FrontierHorseType.THOROUGHBRED);
            }
            if (e.getCurrentItem().getType().equals(CustomItem.getCustomItem("Donkey").getMaterial()) ) {
                purchaseHorse(b,p,CustomItem.getCustomItem("Donkey"),NPC.getNPC("Stable Manager"),FrontierHorseType.DONKEY);
            }
        }
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA+"Vault Keeper")){
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case ENDER_CHEST -> {
                    if (b.getVaultSize() != 0) {
                        p.openInventory(BanditUtils.vaultUI(p));
                    }
                    else {
                        p.closeInventory();
                        p.sendMessage(NPC.getNPC("Vault Keeper").getDisplayName()+": " + ChatColor.RED+ "You don't have an item vault!");

                    }
                }
                case CHEST -> {
                    p.openInventory(BanditUtils.newVaultUI(p));
                }
                case LIME_BANNER -> {
                    if (b.getVaultSize() != 0) {
                        p.openInventory(BanditUtils.vaultUpgradeUI(p));
                    }
                    else {
                        p.closeInventory();
                        p.sendMessage(NPC.getNPC("Vault Keeper").getDisplayName()+": " + ChatColor.RED+ "You don't have an item vault!");

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
                if(NPC.getShoppingPlayers().containsKey(p.getUniqueId().toString())){
                    p.sendMessage("ERROR");
                }
                if (b.isDepositing()) {
                    e.setCancelled(true);
                    b.setDepositing(false);
                    if (d > b.getWallet()) {
                        p.sendMessage(NPC.getNPC("Bank Teller").getDisplayName()+ChatColor.RED+ ": You don't have enough funds!");
                    }
                    else {
                        p.sendMessage(NPC.getNPC("Bank Teller").getDisplayName()+ChatColor.GRAY+ ": You have deposited "+ChatColor.GREEN+ d + "g!");
                        b.updateWallet(-1 * d);
                        b.updateBank(d);
                    }
                }
                if (b.isWithdrawing()) {
                    b.setWithdrawing(false);
                    e.setCancelled(true);
                    if (d > b.getBank()) {
                        p.sendMessage(NPC.getNPC("Bank Teller").getDisplayName()+ChatColor.RED+ ": You don't have enough funds!");
                    }
                    else {
                        p.sendMessage(NPC.getNPC("Bank Teller").getDisplayName()+ChatColor.GRAY+ ": You have withdrew "+ChatColor.GREEN + d + "g!");
                        b.updateBank(-1 * d);
                        b.updateWallet(d);
                    }
                }

            }

        }
    }

    public void purchaseItem(Bandit b, Player p, CustomItem item, NPC npc )
    {
        p.sendMessage("Purchase price: "+item.getPurchasePrice());
        if (b.getWallet() >= item.getPurchasePrice()) {
            b.updateWallet(-1 * item.getPurchasePrice());
            p.sendMessage(ChatColor.GREEN + "Purchased "+item.getItemStack().getItemMeta().getDisplayName());
            p.getInventory().addItem(item.getItemStack());
        } else {
            p.closeInventory();
            p.sendMessage( npc.getDisplayName()+ ChatColor.GRAY+ ": Not enough funds!");
        }
    }
    public void purchaseFirearm(Bandit b, Player p, CustomItem item, NPC npc, String gunName )
    {
        if (b.getWallet() >= item.getPurchasePrice()) {
            b.updateWallet(-1 * item.getPurchasePrice());
            p.sendMessage(ChatColor.GREEN + "Purchased "+item.getItemStack().getItemMeta().getDisplayName());
            String weapon = "shot give " + p.getDisplayName() + " " + gunName;

            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Bukkit.dispatchCommand(console, weapon);
        } else {
            p.closeInventory();
            p.sendMessage( npc.getDisplayName()+ ChatColor.GRAY+ ": Not enough funds!");
        }
    }
    public void purchaseHorse(Bandit b, Player p, CustomItem item, NPC npc, FrontierHorseType type)
    {
        if (b.getWallet() >= item.getPurchasePrice()) {
            if(b.getHorses().size() <3){
                b.updateWallet(-1 * item.getPurchasePrice());
                p.sendMessage(ChatColor.GREEN + "Purchased "+item.getItemStack().getItemMeta().getDisplayName());
                b.getHorses().add(new FrontierHorse(p.getUniqueId().toString(),GlobalUtils.getHorseTypeString(type)+(b.getHorses().size()+1),type));
            }else{
                p.closeInventory();
                p.sendMessage( npc.getDisplayName()+ ChatColor.GRAY+ ": You have the "+ChatColor.RED+"max amount"+ChatColor.GRAY+" of horses!");
            }
        } else {
            p.closeInventory();
            p.sendMessage( npc.getDisplayName()+ ChatColor.GRAY+ ": Not enough funds!");
        }
    }
    public void openGeode(double geodeCost, Player p, Bandit b)
    {
        if(b.getWallet() >= geodeCost){
            b.updateWallet(-1* geodeCost);
            ItemStack item = ItemTable.getTable("Geode").getItem(3).getItemStack();
            p.getInventory().addItem(item);
            p.sendMessage(ChatColor.GRAY+"Geode opened... "+ChatColor.WHITE+"+"+item.getAmount()+ item.getItemMeta().getDisplayName());
        }else{
            p.sendMessage( ChatColor.GRAY+ ": Not enough funds!");
            p.closeInventory();
        }

    }


}
