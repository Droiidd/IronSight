package droidco.west3.ironsight.Objects.Location;

import droidco.west3.ironsight.Objects.Items.ItemIcon;
import droidco.west3.ironsight.Objects.Player.Bandit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class LocationUI {
    public static Inventory openContractorTitleSelectUi(Player p){
        Inventory contractUi = Bukkit.createInventory(p, 9, ChatColor.DARK_GRAY+"Choose Town:");
        Bandit iPlayer = Bandit.getPlayer(p);
        contractUi.setItem(2, ItemIcon.getIcon("New Orleans").getItem());
        contractUi.setItem(4,ItemIcon.getIcon("Santa Fe").getItem());
        contractUi.setItem(6,ItemIcon.getIcon("Republic of Texas").getItem());
        return contractUi;
    }

}
