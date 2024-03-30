package droidco.west3.ironsight.FrontierLocation;

import droidco.west3.ironsight.Items.ItemIcon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class LocationUI {
    public static Inventory openContractorTitleSelectUi(Player p){
        Inventory contractUi = Bukkit.createInventory(p, 9, ChatColor.DARK_GRAY+"Choose Town:");
        //Bandit b = Bandit.getPlayer(p);
        contractUi.setItem(2, ItemIcon.getIcon("Respawn: New Orleans").getItem());
        contractUi.setItem(4,ItemIcon.getIcon("Respawn: Santa Fe").getItem());
        contractUi.setItem(6,ItemIcon.getIcon("Respawn: Republic of Texas").getItem());
        return contractUi;
    }

}
