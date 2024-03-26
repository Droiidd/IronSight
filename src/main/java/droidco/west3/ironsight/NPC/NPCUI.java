package droidco.west3.ironsight.NPC;

import org.bukkit.inventory.Inventory;import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Items.ItemIcon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public class NPCUI {

    public static Inventory traderUI(Player p) {
        Inventory trackerUi = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Trader");
        Bandit iPlayer = Bandit.getPlayer(p);

    }
}
