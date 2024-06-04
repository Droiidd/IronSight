package droidco.west3.ironsight.Contracts.UI;

import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.Contracts.Utils.ContractType;
import droidco.west3.ironsight.Contracts.Utils.ContractUtils;
import droidco.west3.ironsight.Contracts.Utils.Difficulty;
import droidco.west3.ironsight.Globals.Utils.BanditUtils;
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
import java.util.List;
import java.util.stream.Collectors;

public class ContractUI {

    public static Inventory openContractorTitleSelectUi(Player p) {
        Inventory contractUi = Bukkit.createInventory(p, 27, ChatColor.DARK_GRAY + "Contractor Title Select:");
        Bandit b = Bandit.getPlayer(p);
        contractUi.setItem(0,ItemIcon.getIcon("back_button").getItem());
        contractUi.setItem(22, ItemIcon.getIcon("remove_title").getItem());
        contractUi.setItem(11, ItemIcon.getIcon("cowboy_prefix").getItem());
        contractUi.setItem(12, ItemIcon.getIcon("tracker_prefix").getItem());
        contractUi.setItem(15, ItemIcon.getIcon("raider_prefix").getItem());
        contractUi.setItem(10, ItemIcon.getIcon("miner_prefix").getItem());
        contractUi.setItem(14, ItemIcon.getIcon("medic_prefix").getItem());
        contractUi.setItem(16, ItemIcon.getIcon("explorer_prefix").getItem());
        return contractUi;
    }

    public static Inventory openContractOptionsUi(Player p) {
        Inventory contractUi = Bukkit.createInventory(p, 27, ChatColor.DARK_GRAY + "Select a Contract: (Click to start!)");
        Bandit b = Bandit.getPlayer(p);

        contractUi.setItem(11, getContractSlot(b.getRookieContract(), Difficulty.Rookie));
        contractUi.setItem(13, getContractSlot(b.getApprenticeContract(), Difficulty.Apprentice));
        contractUi.setItem(15, getContractSlot(b.getExperiencedContract(), Difficulty.Experienced));

        contractUi.setItem(2, ItemIcon.getIcon("rookie_slot").getItem());
        contractUi.setItem(4, ItemIcon.getIcon("apprentice_slot").getItem());
        contractUi.setItem(6, ItemIcon.getIcon("experienced_slot").getItem());

        return contractUi;
    }

    public static Inventory openContractorInfo(Player p) {
        Inventory contractUi = Bukkit.createInventory(p, 27, ChatColor.DARK_GRAY + "Contractor Info:");
        Bandit b = Bandit.getPlayer(p);
        contractUi.setItem(0, ItemIcon.getIcon("close_menu").getItem());
        contractUi.setItem(11, ItemIcon.getIcon("contractor_title").getItem());
        contractUi.setItem(4, getContractorIcon(p));
        contractUi.setItem(15, getActiveContractIcon(p, b));
        contractUi.setItem(22, ItemIcon.getIcon("complete_contract").getItem());
        return contractUi;
    }

    public static Inventory openContractorRewardUi(Player p) {
        Inventory contractUi = Bukkit.createInventory(p, 27, ChatColor.DARK_GRAY + "");
        Bandit b = Bandit.getPlayer(p);

        contractUi.setItem(1, ItemIcon.getIcon("previous_page").getItem());
        return contractUi;
    }

    public static ItemStack getContractSlot(Contract selected, Difficulty difficulty) {
        //Basic item set up

        //LORE STRUCTURE FOR CONTRACTS IS ALWAYS:
        /*
        NAME
        ---
        Location
        Reward
        TargetName / Requested Goods (If applicable)
         */
        ItemStack contract = selected.getContractIcon();
        ItemMeta contractMeta = contract.getItemMeta();
        ArrayList<String> contractLore = new ArrayList<>();
        //Setting up the strings for the lore

        //LORE
        contractLore.add((ContractUtils.getDifficultyScale(difficulty).equalsIgnoreCase("IV") ?
                ChatColor.RED + ContractUtils.getDifficultyScale(difficulty) : ContractUtils.getDifficultyScale(difficulty)));
        contractLore.add(ChatColor.GRAY + "Location: " + selected.getLocation().getLocName());
        if (selected.getContractType().equals(ContractType.Delivery)) {
            contractLore.add(String.valueOf(ChatColor.GRAY) + selected.getRequestedAmount() + " " + selected.getRequestedItem().getItemMeta().getDisplayName());
        }
        contractLore.add(ChatColor.GRAY + "Reward: " + selected.getReward() + " g");
        //This displays the contracts type
        //contractLore.add(ChatColor.GRAY+ContractUtils.getTypeString(selected.getType()));
        contractMeta.setLore(contractLore);
        contract.setItemMeta(contractMeta);
        return contract;
    }

    public static ItemStack getContractorIcon(Player p) {
        Bandit b = Bandit.getPlayer(p);

        boolean isNewVersion = Arrays.stream(Material.values())
                .map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
        Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
        ItemStack skull = new ItemStack(type, 1);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        ArrayList<String> skullLore = new ArrayList<>();
        skullLore.add(ChatColor.AQUA + "Level " + ChatColor.GRAY + "[" + BanditUtils.getContractorLvlColor(b.getContractorLvl()) + b.getContractorLvl() + ChatColor.GRAY + "]");
        int nextLvl = b.getContractorLvl() + 1;
        skullLore.add(ChatColor.GREEN + "Next Level " + ChatColor.GRAY + "[" + BanditUtils.getContractorLvlColor(nextLvl) + nextLvl + ChatColor.GRAY + "] " + b.getContractorXp() + " / " + BanditUtils.getXpRequiredForLevel(b) + " XP");
        meta.setLore(skullLore);
        meta.setOwner(p.getDisplayName());
        meta.setDisplayName(ChatColor.WHITE + "Contractor Info:");
        skull.setItemMeta(meta);
        return skull;
    }

    public static ItemStack getActiveContractIcon(Player p, Bandit b) {
        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(ChatColor.WHITE + "View Active Contract");
        if (b.getActiveContract() != null) {

            List<ItemStack> items = Arrays.stream(p.getInventory().getContents()).toList();
            int totalAmountReqItems = 0;
            for (ItemStack invItem : items) {
                if (invItem != null) {
                    if (invItem.getType() == b.getActiveContract().getRequestedItem().getType()) {

                        int amount = invItem.getAmount();
                        totalAmountReqItems += amount;
                    }
                }
            }
            double percentage = ((double) totalAmountReqItems / (double) b.getActiveContract().getRequestedAmount()) * 100;
            List<String> lore = new ArrayList<>();
            ChatColor color;
            if (percentage <= 30) {
                color = ChatColor.RED;
            } else if (percentage <= 70) {
                color = ChatColor.YELLOW;
            } else {
                color = ChatColor.GREEN;
            }
            if (percentage <= 100) {
                lore.add(ChatColor.GRAY + "Completion: " + color + Math.floor(percentage) + "%");
            } else {
                lore.add(ChatColor.GREEN + "Complete!" + ChatColor.GRAY + " Return to contractor.");
            }
            lore.add(ChatColor.GRAY + "" + totalAmountReqItems + " / " + b.getActiveContract().getRequestedAmount() + " " + b.getActiveContract().getRequestedItem().getItemMeta().getDisplayName());
            iMeta.setLore(lore);
        }
        item.setItemMeta(iMeta);
        //p.getInventory().containsAtLeast(b.getActiveContract().getRequestedItem(),b.getActiveContract().getRequestedAmount());
        return item;
    }

    public static ItemStack getResignContractIcon() {
        ItemStack item = new ItemStack(Material.TNT);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(ChatColor.WHITE + "Resign Active Contract");
        item.setItemMeta(iMeta);
        return item;
    }

    public static Inventory openLvlRewardMenu(Player p) {
        Inventory menu = Bukkit.createInventory(p, 27, ChatColor.DARK_GRAY + "Level Up Perks:");
        Bandit b = Bandit.getPlayer(p);
        menu.setItem(0, ItemIcon.getIcon("back_button").getItem());
        menu.setItem(9, rewardIcon(Material.LIGHT_GRAY_WOOL,0,"One Contract Slot",null,null,null));
        menu.setItem(10, rewardIcon(Material.LIGHT_GRAY_WOOL,1,null,null,null,null));
        menu.setItem(11, rewardIcon(Material.YELLOW_WOOL,2,"10% bonus to all contracts",null,null,null));
        menu.setItem(12, rewardIcon(Material.YELLOW_WOOL,3,"Unlock Miner Prefix",null,null,null));
        menu.setItem(13, rewardIcon(Material.YELLOW_WOOL,4,"Unlock Cowboy Prefix","Thoroughbred horse available for purchase","10% bonus to all contracts (stacked)",null));
        menu.setItem(14, rewardIcon(Material.LIME_WOOL,5,"Unlock Tracker Prefix","Animal hide sells for 10% more gold",null,null));
        menu.setItem(15, rewardIcon(Material.LIME_WOOL,6,"Morphine potion is now craftable","Donkey available for purchase",null,null));
        menu.setItem(16, rewardIcon(Material.LIME_WOOL,7,"Unlock Second Contract Slot","Miner's Frenzy Brew is now craftable",null,null));
        menu.setItem(17, rewardIcon(Material.LIGHT_BLUE_WOOL,8,"Camel available for purchase","Whiskey potion is now craftable",null,null));
        menu.setItem(18, rewardIcon(Material.LIGHT_BLUE_WOOL,9,"Unlock Raider Prefix","Double spade brew is now craftable","5% bonus to all contracts (stacked)",null));
        menu.setItem(19, rewardIcon(Material.MAGENTA_WOOL,10,"Unlock Explorer Prefix","2.5% chance to mine double the ore",null,null));
        menu.setItem(20, rewardIcon(Material.MAGENTA_WOOL,11,"Unlock Farmer Prefix","Green thumb brew is now craftable","2.5% chance to harvest double the crops",null));
        menu.setItem(21, rewardIcon(Material.RED_WOOL,12,"Unlock Medic Prefix","Medicine potion is now craftable","1% chance not to consume potions","5% bonus to all contracts (stacked)"));
        menu.setItem(22, rewardIcon(Material.RED_GLAZED_TERRACOTTA,13,"Unlock third contract slot","4% chance not to consume pots","10% chance not to consume ammo",null));

        return menu;
    }

    public static ItemStack rewardIcon(Material mat,int level, String reward1, String reward2, String reward3, String reward4) {
        ItemStack item = new ItemStack(mat);
        ItemMeta iMeta = item.getItemMeta();
        List<Integer> reqs = BanditUtils.getLevelXpRequirementList();
        int xpForLvl = reqs.get(level);
        iMeta.setDisplayName(ChatColor.AQUA+"Level "+ChatColor.GRAY+"["+BanditUtils.getContractorLvlColor(level)+level+ChatColor.GRAY+"] : "+xpForLvl+" XP");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Rewards:");
        if(reward1 != null) {
          lore.add(ChatColor.GRAY+"-> "+ChatColor.WHITE +reward1);
        }

        if(reward2 != null) {
            lore.add(ChatColor.GRAY+"-> "+ChatColor.WHITE+reward2);
        }

        if(reward3 != null) {
            lore.add(ChatColor.GRAY+"-> "+ChatColor.WHITE+reward3);
        }
        if(reward4 != null) {
            lore.add(ChatColor.GRAY+"-> "+ChatColor.WHITE+reward4);
        }
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }


}
