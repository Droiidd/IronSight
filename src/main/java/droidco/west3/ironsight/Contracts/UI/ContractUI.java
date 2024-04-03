package droidco.west3.ironsight.Contracts.UI;

import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.Contracts.Utils.ContractType;
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

    public static Inventory openContractorTitleSelectUi(Player p){
        Inventory contractUi = Bukkit.createInventory(p, 27, ChatColor.DARK_GRAY+"Contractor Title Select:");
        Bandit b = Bandit.getPlayer(p);
        contractUi.setItem(10,ItemIcon.getIcon("cowboy_prefix").getItem());
        contractUi.setItem(11,ItemIcon.getIcon("tracker_prefix").getItem());
        contractUi.setItem(12,ItemIcon.getIcon("raider_prefix").getItem());
        contractUi.setItem(14,ItemIcon.getIcon("miner_prefix").getItem());
        contractUi.setItem(15,ItemIcon.getIcon("medic_prefix").getItem());
        contractUi.setItem(16,ItemIcon.getIcon("explorer_prefix").getItem());
        return contractUi;
    }
    public static Inventory openContractUi(Player p){
        Inventory contractUi = Bukkit.createInventory(p, 27, ChatColor.DARK_GRAY + "Available Contracts: (Click to start!)");
        Bandit b = Bandit.getPlayer(p);

        p.sendMessage(b.getRookieContract().getContractName());
        p.sendMessage(b.getApprenticeContract().getContractName());
        p.sendMessage(b.getExperiencedContract().getContractName());

        contractUi.setItem(2,ItemIcon.getIcon("contractor_title").getItem());
        contractUi.setItem(4,getContractorIcon(p));
        contractUi.setItem(7,getActiveContractIcon());
        contractUi.setItem(11, getContractSlot(b.getRookieContract(), Difficulty.Rookie));
        contractUi.setItem(13, getContractSlot(b.getApprenticeContract(),Difficulty.Apprentice));
        contractUi.setItem(15, getContractSlot(b.getExperiencedContract(),Difficulty.Experienced));
        return contractUi;
    }
    public static ItemStack getContractSlot(Contract selected, Difficulty difficulty){
        String title = selected.getListingName();
//        switch(difficulty){
//            case Rookie -> {
//                title = ChatColor.GREEN+"Rookie Contract";
//            }
//            case Apprentice -> {
//                title = ChatColor.YELLOW+"Apprentice Contract";
//            }
//            case Experienced -> {
//                title = ChatColor.RED+"Experienced Contract";
//            }
//        }
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
        if(selected.getContractType().equals(ContractType.Delivery)){
            contractLore.add(String.valueOf(ChatColor.GRAY)+ selected.getRequestedAmount()+" "+ selected.getRequestedItem().getItemMeta().getDisplayName());
        }
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

}
