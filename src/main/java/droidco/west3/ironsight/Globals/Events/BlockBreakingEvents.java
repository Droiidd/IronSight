package droidco.west3.ironsight.Globals.Events;

import droidco.west3.ironsight.Bandit.BlockHarvestTask;
import droidco.west3.ironsight.Globals.Utils.BlockType;
import droidco.west3.ironsight.IronSight;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakingEvents implements Listener {
    private IronSight plugin;

    public BlockBreakingEvents(IronSight plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        e.setCancelled(true);
        Block block = (Block) e.getBlock();

        if (block.getType() == Material.WITHER_ROSE) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);

        }

        if (block.getType() == Material.LILY_OF_THE_VALLEY) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
        }

        if (block.getType() == Material.BLUE_ORCHID) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
        }

        if (block.getType() == Material.POPPY) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
        }
        if (block.getType() == Material.TORCHFLOWER) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
        }
        if (block.getType() == Material.SWEET_BERRIES) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
        }
        if (block.getType() == Material.WARPED_FUNGUS) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
        }
        if (block.getType() == Material.CORNFLOWER) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
        }
        if (block.getType() == Material.IRON_ORE) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.MINERALS);
        }
        if (block.getType() == Material.COPPER_ORE) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.MINERALS);
        }
        if (block.getType() == Material.GOLD_ORE) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.MINERALS);
        }
        if (block.getType() == Material.RAW_IRON_BLOCK) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.MINERALS);
        }
        if (block.getType() == Material.RAW_COPPER_BLOCK) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.MINERALS);
        }
        if (block.getType() == Material.RAW_GOLD_BLOCK) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.MINERALS);
        }
        if (block.getType() == Material.DEAD_BUSH) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
        }
        if (block.getType() == Material.JUNGLE_SAPLING) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
        }


    }
}
