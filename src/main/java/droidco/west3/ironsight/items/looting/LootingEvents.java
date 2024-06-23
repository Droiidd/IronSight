package droidco.west3.ironsight.items.looting;

import droidco.west3.ironsight.bandit.Bandit;
import droidco.west3.ironsight.frontierlocation.LocationType;
import droidco.west3.ironsight.IronSight;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

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
        if(block != null){
            if (block.getType().equals(Material.CHEST)) {
                spawnChestLoot(b,block,ItemTable.getTable("Drug Base"),LocationType.ILLEGAL,3);
            }
            if (block.getType().equals(Material.CHEST)) {
                spawnChestLoot(b,block,ItemTable.getTable("Oil Field"),LocationType.OIL_FIELD,3);
            }
            if (block.getType().equals(Material.CHEST)) {
                spawnChestLoot(b,block,ItemTable.getTable("Mines"),LocationType.MINE,3);
            }
            if (block.getType().equals(Material.CHEST)) {
                spawnChestLoot(b,block,ItemTable.getTable("Scavenger Town"),LocationType.EVENT,3);
            }
            if (block.getType().equals(Material.CHEST)) {
                spawnChestLoot(b,block,ItemTable.getTable("Hunting Grounds"),LocationType.NATURAL,3);
            }
        }


    }

    public void spawnChestLoot(Bandit b, Block block, ItemTable table,LocationType locType,int quantity){
        if (b.getCurrentLocation().getType().equals(locType)) {
            ArrayList<ItemStack> contents = table.getNumItems(quantity);
            Chest chest = (Chest) block.getState();
            for (ItemStack item : contents) {
                chest.getInventory().addItem(item);
            }
            new RespawnLootTask(plugin, 900, block.getLocation(), chest.getInventory().getContents());
        }
    }

}
