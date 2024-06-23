package droidco.west3.ironsight.blockharvesting;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.bandit.Bandit;
import droidco.west3.ironsight.globals.utils.BlockType;
import droidco.west3.ironsight.globals.utils.GlobalUtils;
import droidco.west3.ironsight.items.CustomItem;
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
  private int plantDropMultiplier = 1;
  private int oreDropMultiplier = 1;

  public BlockBreakingEvents(IronSight plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onBreak(BlockBreakEvent e) {
    Player p = e.getPlayer();
    e.setCancelled(true);
    Block block = (Block) e.getBlock();
    e.setDropItems(false);
    Bandit b = Bandit.getPlayer(p);
    if (b.isJailed()) {
      if (block.getType() == Material.IRON_ORE) {
        minePrisonOre(b, p, block, 4 - 40, CustomItem.getCustomItem("Iron Ore"));
      }
      if (block.getType() == Material.COPPER_ORE) {
        minePrisonOre(b, p, block, -80, CustomItem.getCustomItem("Copper Ore"));
      }
      if (block.getType() == Material.GOLD_ORE) {
        minePrisonOre(b, p, block, -160, CustomItem.getCustomItem("Gold Ore"));
      }
      if (block.getType() == Material.RAW_IRON_BLOCK) {
        minePrisonOre(b, p, block, -300, CustomItem.getCustomItem("Iron Ore"));
      }
      if (block.getType() == Material.RAW_COPPER_BLOCK) {
        minePrisonOre(b, p, block, -500, CustomItem.getCustomItem("Copper Ore"));
      }
      if (block.getType() == Material.RAW_GOLD_BLOCK) {
        minePrisonOre(b, p, block, -700, CustomItem.getCustomItem("Gold Ore"));
      }
    }

    if (p.hasPotionEffect(PotionEffectType.LUCK)) {
      plantDropMultiplier = 2;
    }
    if (b.getContractorLvl() >= 10) {
      int chance = GlobalUtils.getRandomNumber(101);
      if (chance < 3) {
        oreDropMultiplier *= 2;
      }
    }
    if (b.getContractorLvl() >= 11) {
      int chance = GlobalUtils.getRandomNumber(101);
      if (chance < 3) {
        plantDropMultiplier *= 2;
      }
    }

    if (block.getType() == Material.WITHER_ROSE) {
      breakCustomBlock(
          p,
          block,
          BlockType.FOLIAGE,
          CustomItem.getCustomItem("Unprocessed Spice"),
          plantDropMultiplier);
    }
    if (block.getType() == Material.JUNGLE_SAPLING) {
      breakCustomBlock(
          p,
          block,
          BlockType.FOLIAGE,
          CustomItem.getCustomItem("Unprocessed Smokeleaf"),
          plantDropMultiplier);
    }
    if (block.getType() == Material.LILY_OF_THE_VALLEY) {
      breakCustomBlock(
          p, block, BlockType.FOLIAGE, CustomItem.getCustomItem("Life Seeds"), plantDropMultiplier);
    }

    if (block.getType() == Material.BLUE_ORCHID) {
      breakCustomBlock(
          p,
          block,
          BlockType.FOLIAGE,
          CustomItem.getCustomItem("Blue malt Petal"),
          plantDropMultiplier);
    }

    if (block.getType() == Material.SWEET_BERRY_BUSH) {
      breakCustomBlock(
          p,
          block,
          BlockType.FOLIAGE,
          CustomItem.getCustomItem("Heart Fruit"),
          plantDropMultiplier);
    }
    if (block.getType() == Material.TORCHFLOWER) {
      breakCustomBlock(
          p,
          block,
          BlockType.FOLIAGE,
          CustomItem.getCustomItem("Frenzied Stems"),
          plantDropMultiplier);
    }
    if (block.getType() == Material.WARPED_FUNGUS) {
      breakCustomBlock(
          p,
          block,
          BlockType.FOLIAGE,
          CustomItem.getCustomItem("Moles Breath"),
          plantDropMultiplier);
    }
    if (block.getType() == Material.IRON_ORE) {
      breakCustomBlock(
          p, block, BlockType.MINERALS, CustomItem.getCustomItem("Iron Ore"), oreDropMultiplier);
    }
    if (block.getType() == Material.COPPER_ORE) {
      breakCustomBlock(
          p, block, BlockType.MINERALS, CustomItem.getCustomItem("Copper Ore"), oreDropMultiplier);
    }
    if (block.getType() == Material.GOLD_ORE) {
      breakCustomBlock(
          p, block, BlockType.MINERALS, CustomItem.getCustomItem("Gold Ore"), oreDropMultiplier);
    }
    if (block.getType() == Material.RAW_IRON_BLOCK) {
      breakCustomBlock(
          p,
          block,
          BlockType.MINERALS,
          CustomItem.getCustomItem("Iron Ore"),
          9 * oreDropMultiplier);
    }
    if (block.getType() == Material.RAW_COPPER_BLOCK) {
      breakCustomBlock(
          p,
          block,
          BlockType.MINERALS,
          CustomItem.getCustomItem("Copper Ore"),
          9 * oreDropMultiplier);
    }
    if (block.getType() == Material.RAW_GOLD_BLOCK) {
      breakCustomBlock(
          p,
          block,
          BlockType.MINERALS,
          CustomItem.getCustomItem("Gold Ore"),
          9 * oreDropMultiplier);
    }
  }

  public void breakCustomBlock(Player p, Block block, BlockType type, CustomItem item, int amount) {
    BlockHarvestTask changeBlock = new BlockHarvestTask(plugin, p, block, type);
    if (type.equals(BlockType.MINERALS)) {
      int geodeOdds = GlobalUtils.getRandomNumber(1001);
      if (geodeOdds < 25) {
        item = CustomItem.getCustomItem("Crystalized Geode");
      } else if (geodeOdds < 125) {
        item = CustomItem.getCustomItem("Geode");
      }
    }
    ItemStack tmp = item.getItemStack();
    tmp.setAmount(amount);
    block.getWorld().dropItemNaturally(block.getLocation(), tmp);
  }

  public void minePrisonOre(Bandit b, Player p, Block block, int bountyDecrease, CustomItem item) {
    b.updateBounty(bountyDecrease);
    breakCustomBlock(p, block, BlockType.MINERALS, item, 0);
    p.spigot()
        .sendMessage(
            ChatMessageType.ACTION_BAR,
            new TextComponent(
                ChatColor.GRAY
                    + "Ore mined. "
                    + ChatColor.GREEN
                    + ""
                    + bountyDecrease
                    + " bounty!"));
  }
}
