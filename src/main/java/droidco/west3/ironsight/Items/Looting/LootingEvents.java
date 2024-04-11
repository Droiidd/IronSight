package droidco.west3.ironsight.Items.Looting;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.FrontierLocation.LocationType;
import droidco.west3.ironsight.IronSight;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

public class LootingEvents implements Listener {

    private IronSight plugin;
    public LootingEvents(IronSight plugin)
    {
        this.plugin = plugin;
    }


    @EventHandler
    public void playerOpensChest(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        Bandit b = Bandit.getPlayer(p);
        Block block = e.getClickedBlock();
        //CHECK IF CHEST
        if (block.getType().equals(Material.CHEST)) {
            if (b.getCurrentLocation().getType().equals(LocationType.ILLEGAL)) {
                ArrayList<ItemStack> contents = ItemTable.getTable("Drug Base").getNumItems(5);
                Chest chest = (Chest) block.getState();
                for (ItemStack item : contents) {
                    chest.getInventory().addItem(item);
                }
                new RespawnLootTask(plugin, 20, block.getLocation(), chest.getInventory().getContents());
            }
        }


    }

}
