package droidco.west3.ironsight.horse;

import droidco.west3.ironsight.bandit.Bandit;
import droidco.west3.ironsight.globals.Utils.GlobalUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class HorseUI {
    public static Inventory openAvailableHorses(Player p) {
        Inventory playerHorses = Bukkit.createInventory(p, 9, ChatColor.DARK_RED + ChatColor.stripColor(p.getDisplayName()) + ChatColor.DARK_GRAY + "'s horses");
        Bandit b = Bandit.getPlayer(p);
        List<FrontierHorse> horses = b.getHorses();
        int count = 0;
        for (FrontierHorse horse : horses) {
            //if (!horse.isSummoned()) {
            //HORSE IS NOT SUMMONED THEREFORE IN STORAGE
            if (!horse.isSummoned()) {
                ItemStack horseItem = new ItemStack(Material.NAME_TAG);
                ItemMeta meta = horseItem.getItemMeta();
                meta.setDisplayName(horse.getHorseName());
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + GlobalUtils.getHorseTypeString(horse.getHorseType()));
                meta.setLore(lore);
                horseItem.setItemMeta(meta);
                playerHorses.setItem(count, horseItem);

            }
            count++;
            //}
        }
        for (int i = 3; i < 9; i++) {
            ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(" ");
            item.setItemMeta(meta);
            playerHorses.setItem(i, item);
        }
        return playerHorses;
    }

    public static Inventory openHorseMenu(Player p, FrontierHorse horse) {
        Inventory horseInv = Bukkit.createInventory(p, 9, horse.getHorseName() + "'s saddle-pack");
        horseInv.setItem(8, getExitButton());
        horseInv.setItem(0, sendToStable());
        horseInv.setItem(4, openHorseInventory());
        return horseInv;
    }

    public static ItemStack sendToStable() {
        ItemStack item = new ItemStack(Material.HAY_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Send horse to stable");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "Call back with /call");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack openHorseInventory() {
        ItemStack item = new ItemStack(Material.CHEST);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Open Saddle-pack storage");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getExitButton() {
        ItemStack exit = new ItemStack(Material.BARRIER);
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(ChatColor.WHITE + "Leave");
        exit.setItemMeta(exitMeta);
        return exit;
    }
}
