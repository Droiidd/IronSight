package droidco.west3.ironsight.Location;

import droidco.west3.ironsight.Items.ItemIcon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class LocationUI {
    public static Inventory openContractorTitleSelectUi(Player p){
        Inventory contractUi = Bukkit.createInventory(p, 9, ChatColor.DARK_GRAY+"Choose Town:");
        //Bandit b = Bandit.getPlayer(p);
        contractUi.setItem(2, ItemIcon.getIcon("New Orleans Town Hall").getItem());
        contractUi.setItem(4,ItemIcon.getIcon("Santa Fe Town Hall").getItem());
        contractUi.setItem(6,ItemIcon.getIcon("Texas Town Hall").getItem());
        return contractUi;
    }

}
