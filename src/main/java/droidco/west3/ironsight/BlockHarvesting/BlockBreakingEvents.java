package droidco.west3.ironsight.BlockHarvesting;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.BlockHarvesting.BlockHarvestTask;
import droidco.west3.ironsight.Globals.Utils.BlockType;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Items.CustomItem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
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
    public void onBreak(BlockBreakEvent e) {Player p = e.getPlayer();
        e.setCancelled(true);
        Block block = (Block) e.getBlock();
        e.setDropItems(false);
        Bandit b = Bandit.getPlayer(p);
        if(b.isJailed()){
            if (block.getType() == Material.IRON_ORE) {
                minePrisonOre(b,p,block,-2,CustomItem.getCustomItem("Iron Ore"));
            }
            if (block.getType() == Material.COPPER_ORE) {
                minePrisonOre(b,p,block,-4,CustomItem.getCustomItem("Copper Ore"));
            }
            if (block.getType() == Material.GOLD_ORE) {
                minePrisonOre(b,p,block,-8,CustomItem.getCustomItem("Gold Ore"));
            }
            if (block.getType() == Material.RAW_IRON_BLOCK) {
                minePrisonOre(b,p,block,-18,CustomItem.getCustomItem("Iron Ore"));
            }
            if (block.getType() == Material.RAW_COPPER_BLOCK) {
                minePrisonOre(b,p,block,-36,CustomItem.getCustomItem("Copper Ore"));
            }
            if (block.getType() == Material.RAW_GOLD_BLOCK) {
                minePrisonOre(b,p,block,-50,CustomItem.getCustomItem("Gold Ore"));
            }
        }


        if (p.hasPotionEffect(PotionEffectType.LUCK)) {
            dropMultiplier = 2;
        }


        if (block.getType() == Material.WITHER_ROSE) {
            breakCustomBlock(p,block,BlockType.FOLIAGE,CustomItem.getCustomItem("Unprocessed Spice"),1);
        }
        if (block.getType() == Material.JUNGLE_SAPLING) {
            breakCustomBlock(p,block,BlockType.FOLIAGE,CustomItem.getCustomItem("Unprocessed Smokeleaf"),1);
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



    }
    public void breakCustomBlock(Player p, Block block, BlockType type, CustomItem item,int amount){
        BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, type);
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
    public void minePrisonOre(Bandit b, Player p, Block block, int bountyDecrease,CustomItem item)
    {
        b.updateBounty(bountyDecrease);
        breakCustomBlock(p,block,BlockType.MINERALS,item,0);
        p.spigot().sendMessage(
                ChatMessageType.ACTION_BAR,
                new TextComponent(ChatColor.GRAY + "Ore mined. "+ChatColor.GREEN+""+bountyDecrease+" bounty!"));
    }
}
