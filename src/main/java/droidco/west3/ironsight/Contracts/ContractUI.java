package droidco.west3.ironsight.Contracts;

import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.Contracts.Utils.ContractUtils;
import droidco.west3.ironsight.Contracts.Utils.Difficulty;
import droidco.west3.ironsight.Items.ItemIcon;
import droidco.west3.ironsight.Bandit.Bandit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ContractUI {
    public static Inventory openActiveContractUi(Player p){
        Inventory contractUi = Bukkit.createInventory(p, 27, ChatColor.DARK_GRAY+"Active Contract info:");
        Bandit iPlayer = Bandit.getPlayer(p);
        contractUi.setItem(2,ItemIcon.getIcon("contractLoc").getItem());
        contractUi.setItem(4,ItemIcon.getIcon("contractReq").getItem());
        contractUi.setItem(6,ItemIcon.getIcon("contractDesc").getItem());
        contractUi.setItem(11,getResignContractIcon());
        //contractUi.setItem(13,getActiveContractItem(iPlayer));
        return contractUi;
    }
    public static Inventory openContractorTitleSelectUi(Player p){
        Inventory contractUi = Bukkit.createInventory(p, 27, ChatColor.DARK_GRAY+"Contractor Title Select:");
        Bandit iPlayer = Bandit.getPlayer(p);
        contractUi.setItem(10,ItemIcon.getIcon("Cowboy").getItem());
        contractUi.setItem(11,ItemIcon.getIcon("Tracker").getItem());
        contractUi.setItem(12,ItemIcon.getIcon("Raider").getItem());
        contractUi.setItem(14,ItemIcon.getIcon("Miner").getItem());
        contractUi.setItem(15,ItemIcon.getIcon("Medic").getItem());
        contractUi.setItem(16,ItemIcon.getIcon("Explorer").getItem());
        return contractUi;
    }
    public static Inventory openContractUi(Player p){
        Inventory contractUi = Bukkit.createInventory(p, 27, ChatColor.DARK_GRAY + "Available Contracts: (Click to start!)");
        Bandit iPlayer = Bandit.getPlayer(p);

        p.sendMessage(iPlayer.getRookieContract().getContractName());
        p.sendMessage(iPlayer.getApprenticeContract().getContractName());
        p.sendMessage(iPlayer.getExperiencedContract().getContractName());

        contractUi.setItem(2,ItemIcon.getIcon("Contractor Title").getItem());
        contractUi.setItem(4,getContractorIcon(p));
        contractUi.setItem(7,getActiveContractIcon());
        contractUi.setItem(11, getContractSlot(iPlayer.getRookieContract(), Difficulty.Rookie));
        contractUi.setItem(13, getContractSlot(iPlayer.getApprenticeContract(),Difficulty.Apprentice));
        contractUi.setItem(15, getContractSlot(iPlayer.getExperiencedContract(),Difficulty.Experienced));
        return contractUi;
    }
    public static ItemStack getContractSlot(Contract selected, Difficulty difficulty){
        String title = null;
        switch(difficulty){
            case Rookie -> {
                title = ChatColor.WHITE+"Rookie Contract";
            }
            case Apprentice -> {
                title = ChatColor.WHITE+"Apprentice Contract";
            }
            case Experienced -> {
                title = ChatColor.RED+"Experienced Contract";
            }
        }
        //Basic item set up

        //LORE STRUCTURE FOR CONTRACTS IS ALWAYS:
        /*
        NAME
        ---
        Location
        Reward
        TargetName / Requested Goods (If applicable)
         */
        ItemStack contract = new ItemStack(Material.BOOK);
        ItemMeta contractMeta = contract.getItemMeta();
        ArrayList<String> contractLore = new ArrayList<>();
        //Setting up the strings for the lore

        //LORE
        contractMeta.setDisplayName(title);
        contractLore.add(ChatColor.GRAY+"Difficulty: "+(ContractUtils.getDifficultyScale(difficulty).equalsIgnoreCase("IV") ?
                ChatColor.RED + ContractUtils.getDifficultyScale(difficulty) : ContractUtils.getDifficultyScale(difficulty)));
        contractLore.add(ChatColor.GRAY+"Location: "+selected.getLocation().getLocName());
        contractLore.add(ChatColor.GRAY +"Reward: "+selected.getReward()+" g");
        //This displays the contracts type
        //contractLore.add(ChatColor.GRAY+ContractUtils.getTypeString(selected.getType()));
        contractMeta.setLore(contractLore);
        contract.setItemMeta(contractMeta);
        return contract;
    }

    public static ItemStack getContractorIcon(Player p) {
        Bandit iPlayer = Bandit.getPlayer(p);

        boolean isNewVersion = Arrays.stream(Material.values())
                .map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
        Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
        ItemStack skull = new ItemStack(type, 1);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        ArrayList<String> skullLore = new ArrayList<>();
        skullLore.add(ChatColor.RED + "Combat Lvl: "+ChatColor.GRAY+ iPlayer.getCmbtContractLvl()+" ");
        skullLore.add(ChatColor.GREEN+"Peace maker Lvl: "+ChatColor.GRAY+iPlayer.getPceContractLvl());
        meta.setLore(skullLore);
        meta.setOwner(p.getDisplayName());
        meta.setDisplayName(ChatColor.WHITE + "Contractor Info:");
        skull.setItemMeta(meta);
        return skull;
    }
    public static ItemStack getActiveContractIcon(){
        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(ChatColor.WHITE+"View Active Contract");
        item.setItemMeta(iMeta);
        return item;
    }
    public static ItemStack getResignContractIcon(){
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(ChatColor.WHITE+"Resign Active Contract");
        item.setItemMeta(iMeta);
        return item;
    }
    public static ItemStack getActiveContractItem(Bandit iPlayer){
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setLore(iPlayer.getActiveContract().getDescription());
        iMeta.setDisplayName(ChatColor.WHITE+"Description");
        item.setItemMeta(iMeta);
        return item;
    }

}
