package droidco.west3.ironsight.Globals.Events;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Bandit.Tasks.BlockHarvestTask;
import droidco.west3.ironsight.Globals.Utils.BlockType;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Items.CustomItem;
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
            //breakCustomBlock(p,block,BlockType.FOLIAGE,CustomItem.getCustomItem());
        }

        if (block.getType() == Material.LILY_OF_THE_VALLEY) {
            breakCustomBlock(p,block,BlockType.FOLIAGE,CustomItem.getCustomItem("Life Seeds"),1);
        }

        if (block.getType() == Material.BLUE_ORCHID) {
            breakCustomBlock(p,block,BlockType.FOLIAGE,CustomItem.getCustomItem("Blue malt Petal"),1);
        }

        if (block.getType() == Material.SWEET_BERRY_BUSH) {
            breakCustomBlock(p,block,BlockType.FOLIAGE,CustomItem.getCustomItem("Heart Fruit"),1);
        }
        if (block.getType() == Material.TORCHFLOWER) {
            breakCustomBlock(p,block,BlockType.FOLIAGE,CustomItem.getCustomItem("Frenzied Stems"),1);
        }
        if (block.getType() == Material.WARPED_FUNGUS) {
            breakCustomBlock(p,block,BlockType.FOLIAGE,CustomItem.getCustomItem("Moles Breath"),1);
        }
        if (block.getType() == Material.IRON_ORE) {
            breakCustomBlock(p,block,BlockType.MINERALS,CustomItem.getCustomItem("Iron Ore"),1);
        }
        if (block.getType() == Material.COPPER_ORE) {
            breakCustomBlock(p,block,BlockType.MINERALS,CustomItem.getCustomItem("Copper Ore"),1);
        }
        if (block.getType() == Material.GOLD_ORE) {
            breakCustomBlock(p,block,BlockType.MINERALS,CustomItem.getCustomItem("Gold Ore"),1);
        }
        if (block.getType() == Material.RAW_IRON_BLOCK) {
            breakCustomBlock(p,block,BlockType.MINERALS,CustomItem.getCustomItem("Iron Ore"),9);
        }
        if (block.getType() == Material.RAW_COPPER_BLOCK) {
            breakCustomBlock(p,block,BlockType.MINERALS,CustomItem.getCustomItem("Copper Ore"),9);
        }
        if (block.getType() == Material.RAW_GOLD_BLOCK) {
            breakCustomBlock(p,block,BlockType.MINERALS,CustomItem.getCustomItem("Gold Ore"),9);
        }
        if (block.getType() == Material.DEAD_BUSH) {
            breakCustomBlock(p,block,BlockType.FOLIAGE,CustomItem.getCustomItem("Unprocessed Spice"),1);
        }
        if (block.getType() == Material.JUNGLE_SAPLING) {
            breakCustomBlock(p,block,BlockType.FOLIAGE,CustomItem.getCustomItem("Unprocessed Smokeleaf"),1);
        }


    }
    public void breakCustomBlock(Player p, Block block, BlockType type, CustomItem item,int amount){
        BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, BlockType.MINERALS);
        if(type.equals(BlockType.MINERALS)){
            int geodeOdds = GlobalUtils.getRandomNumber(101);
            if(geodeOdds < 3){
                item = CustomItem.getCustomItem("Geode");
            }else if (geodeOdds < 10){
                item = CustomItem.getCustomItem("Crystalized Geode");
            }
        }
        ItemStack tmp = item.getItemStack();
        tmp.setAmount(amount);
        block.getWorld().dropItemNaturally(block.getLocation(),tmp);
    }
}
