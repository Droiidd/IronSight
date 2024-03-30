package droidco.west3.ironsight.Globals.Events;

import droidco.west3.ironsight.Bandit.Tasks.BlockHarvestTask;
import droidco.west3.ironsight.Globals.Utils.BlockType;
import droidco.west3.ironsight.IronSight;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;



public class BlockBreakingEvents implements Listener {
    private IronSight plugin;
    private int dropMultiplier = 1;


    public BlockBreakingEvents(IronSight plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        e.setCancelled(true);
        Block block = (Block) e.getBlock();
        e.setDropItems(false);

        if (p.hasPotionEffect(PotionEffectType.LUCK)) {
            dropMultiplier = 2;
        }


        if (block.getType() == Material.WITHER_ROSE) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
            // ItemStack nightShadePedal = new ItemStack(Material.PLACEHOLDER, dropMultiplier);
            // block.getWorld().dropItemNaturally(block.getLocation(), nightShadePedal);
        }

        if (block.getType() == Material.LILY_OF_THE_VALLEY) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
            ItemStack lifeSeeds = new ItemStack(Material.PUMPKIN_SEEDS, dropMultiplier);
            block.getWorld().dropItemNaturally(block.getLocation(), lifeSeeds);

        }

        if (block.getType() == Material.BLUE_ORCHID) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
            ItemStack blueMaltPedal = new ItemStack(Material.GLOW_INK_SAC, dropMultiplier);
            block.getWorld().dropItemNaturally(block.getLocation(), blueMaltPedal);
        }

        if (block.getType() == Material.POPPY) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
            // ItemStack IGNORE = new ItemStack(Material.PLACEHOLDER, dropMultiplier);
            // block.getWorld().dropItemNaturally(block.getLocation(), IGNORE);
        }
        if (block.getType() == Material.TORCHFLOWER) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
            ItemStack frenziedStems = new ItemStack(Material.FIRE_CORAL, dropMultiplier);
            block.getWorld().dropItemNaturally(block.getLocation(), frenziedStems);
        }
        if (block.getType() == Material.SWEET_BERRIES) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
            ItemStack lifeFruit = new ItemStack(Material.POPPY, dropMultiplier);
            block.getWorld().dropItemNaturally(block.getLocation(), lifeFruit);
        }
        if (block.getType() == Material.WARPED_FUNGUS) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
            ItemStack molesBreath = new ItemStack(Material.TUBE_CORAL_FAN, dropMultiplier);
            block.getWorld().dropItemNaturally(block.getLocation(), molesBreath);
        }
        if (block.getType() == Material.CORNFLOWER) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
            //ItemStack piratesBooty = new ItemStack(Material.PLACEHOLDER, dropMultiplier);
            //block.getWorld().dropItemNaturally(block.getLocation(), piratesBooty);

        }
        if (block.getType() == Material.IRON_ORE) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.MINERALS);
            ItemStack ironOre = new ItemStack(Material.RAW_IRON, dropMultiplier);
            block.getWorld().dropItemNaturally(block.getLocation(), ironOre);
        }
        if (block.getType() == Material.COPPER_ORE) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.MINERALS);
            ItemStack copperOre = new ItemStack(Material.RAW_COPPER, dropMultiplier);
            block.getWorld().dropItemNaturally(block.getLocation(), copperOre);
        }
        if (block.getType() == Material.GOLD_ORE) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.MINERALS);
            ItemStack goldOre = new ItemStack(Material.RAW_GOLD, dropMultiplier);
            block.getWorld().dropItemNaturally(block.getLocation(), goldOre);
        }
        if (block.getType() == Material.RAW_IRON_BLOCK) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.MINERALS);
            ItemStack ironCluster = new ItemStack(Material.IRON_ORE, 9*dropMultiplier);
            block.getWorld().dropItemNaturally(block.getLocation(), ironCluster);
        }
        if (block.getType() == Material.RAW_COPPER_BLOCK) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.MINERALS);
            ItemStack copperCluster = new ItemStack(Material.COPPER_ORE, 9*dropMultiplier);
            block.getWorld().dropItemNaturally(block.getLocation(), copperCluster);
        }
        if (block.getType() == Material.RAW_GOLD_BLOCK) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.MINERALS);
            ItemStack goldCluster = new ItemStack(Material.GOLD_ORE, 9*dropMultiplier);
            block.getWorld().dropItemNaturally(block.getLocation(), goldCluster);
        }
        if (block.getType() == Material.DEAD_BUSH) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
            ItemStack unprocSpice = new ItemStack(Material.CHORUS_FRUIT, dropMultiplier);
            block.getWorld().dropItemNaturally(block.getLocation(), unprocSpice);
        }
        if (block.getType() == Material.JUNGLE_SAPLING) {
            BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.FOLIAGE);
            ItemStack unprocSmoke = new ItemStack(Material.KELP, dropMultiplier);
            block.getWorld().dropItemNaturally(block.getLocation(), unprocSmoke);
        }


    }
}
