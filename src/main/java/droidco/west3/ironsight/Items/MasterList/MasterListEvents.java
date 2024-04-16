package droidco.west3.ironsight.Items.MasterList;

import droidco.west3.ironsight.Items.ItemIcon;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MasterListEvents implements Listener {

    @EventHandler
    public void onItemClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY+"Master List 1")){
            if(e.getCurrentItem().getType().compareTo(ItemIcon.getIcon("next_page").getItem().getType())==0){
                e.setCancelled(true);
                p.openInventory(MasterItemListUI.openMasterListPage2(p));
            }
        }else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY+"Master List 2")){
            if(e.getCurrentItem().getType().compareTo(ItemIcon.getIcon("next_page").getItem().getType())==0){
                e.setCancelled(true);
                p.openInventory(MasterItemListUI.openMasterListPage3(p));
            }
        }
    }
}
