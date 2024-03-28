package droidco.west3.ironsight.Items;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.net.http.WebSocket;

public class MasterListEvents implements Listener {

    @EventHandler
    public void onItemClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY+"Master List 1")){
            if(e.getCurrentItem().getType().compareTo(ItemIcon.getIcon("NextPage").getItem().getType())==0){
                e.setCancelled(true);
                p.openInventory(MasterItemListUI.openMasterListPage2(p));
            }
        }
    }
}
