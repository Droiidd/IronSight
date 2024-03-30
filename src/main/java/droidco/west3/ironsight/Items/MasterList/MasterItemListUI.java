package droidco.west3.ironsight.Items.MasterList;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Items.CustomItem;
import droidco.west3.ironsight.Items.ItemIcon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterItemListUI {
    public static Inventory openMasterListPage1(Player p){
        Inventory itemUi = Bukkit.createInventory(p, 54, ChatColor.DARK_GRAY+"Master List 1");
        int counter =0;
        HashMap<String, CustomItem> items = CustomItem.getItems();
        for (Map.Entry<String, CustomItem> item : items.entrySet()) {
            if(counter < 53){
                itemUi.setItem(counter,item.getValue().getItemStack());
            }
            counter++;
        }
        itemUi.setItem(53, ItemIcon.getIcon("NextPage").getItem());
        return itemUi;

    }
    public static Inventory openMasterListPage2(Player p){
        Inventory itemUi = Bukkit.createInventory(p, 54, ChatColor.DARK_GRAY+"Master List 2");
        int counter =0;
        int counter2 = 0;
        HashMap<String,CustomItem> items = CustomItem.getItems();
        for (Map.Entry<String, CustomItem> item : items.entrySet()) {
            if(counter > 53){
                itemUi.setItem(counter2,item.getValue().getItemStack());
                counter2++;
            }
            counter++;
        }
        itemUi.setItem(53,ItemIcon.getIcon("NextPage").getItem());
        return itemUi;

    }

}
