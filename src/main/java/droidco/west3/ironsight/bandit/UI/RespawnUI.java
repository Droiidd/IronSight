package droidco.west3.ironsight.bandit.UI;

import droidco.west3.ironsight.items.ItemIcon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class RespawnUI {
    public static Inventory openRespawnSelect(Player p) {
        Inventory contractUi = Bukkit.createInventory(p, 9, ChatColor.DARK_GRAY + "Choose Town:");
        //Bandit b = Bandit.getPlayer(p);
        contractUi.setItem(2, ItemIcon.getIcon("new_orleans_respawn").getItem());
        contractUi.setItem(4, ItemIcon.getIcon("santa_fe_respawn").getItem());
        contractUi.setItem(6, ItemIcon.getIcon("republic_of_texas_respawn").getItem());
        return contractUi;
    }

}
