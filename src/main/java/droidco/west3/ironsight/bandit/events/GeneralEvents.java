package droidco.west3.ironsight.bandit.events;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.bandit.Bandit;
import droidco.west3.ironsight.bandit.tasks.BrokenWindowTask;
import droidco.west3.ironsight.frontierlocation.LocationType;
import droidco.west3.ironsight.globals.utils.BanditUtils;
import droidco.west3.ironsight.globals.utils.GlobalUtils;
import droidco.west3.ironsight.items.CustomItem;
import droidco.west3.ironsight.items.potions.BrewingRecipe;
import droidco.west3.ironsight.items.potions.CustomPotion;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@AllArgsConstructor
public class GeneralEvents implements Listener {
  private IronSight plugin;

  @EventHandler
  public void onLegBreak(EntityDamageEvent e) {
    if (e.getEntity() instanceof Player p) {
      float fall = p.getFallDistance();
      if (fall > 9 && p.getHealth() > 0 && (p.getHealth() - e.getDamage()) > 0) {
        Bandit b = Bandit.getPlayer(p);
        if (b.isBrokenLegs()) {
          // Players legs are already broken, damage harder
          p.damage(3.0);
        } else {
          // Player broke their legs!
          b.setBrokenLegs(true);
          p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 3));
          p.sendMessage(ChatColor.RED + "You broke your legs!");
        }
      }
    }
  }

  @EventHandler
  public void drinkPotion(PlayerItemConsumeEvent e) {
    ItemStack item = e.getItem();
    Player p = e.getPlayer();
    Bandit b = Bandit.getPlayer(p);

    int chance = GlobalUtils.getRandomNumber(101);
    int odds = 0;
    if (b.getContractorLvl() == 12) {
      odds = 1;
    } else if (b.getContractorLvl() == 13) {
      odds = 3;
    }
    if (chance < odds) {
      p.sendMessage(
          ChatColor.LIGHT_PURPLE
              + "[Potion] "
              + ChatColor.GRAY
              + "Quenched your thirst in one sip!");
      if (ChatColor.stripColor(item.getItemMeta().getDisplayName()).equals("Medicine")) {
        ItemStack pot = CustomPotion.getCustomPotion("Medicine").getItemStack();
        pot.setAmount(1);
        e.getPlayer().getInventory().addItem(pot);
      } else if (ChatColor.stripColor(item.getItemMeta().getDisplayName()).equals("Morphine")) {
        ItemStack pot = CustomPotion.getCustomPotion("Morphine").getItemStack();
        pot.setAmount(1);
        e.getPlayer().getInventory().addItem(pot);
      } else if (ChatColor.stripColor(item.getItemMeta().getDisplayName()).equals("Whiskey")) {
        ItemStack pot = CustomPotion.getCustomPotion("Whiskey").getItemStack();
        pot.setAmount(1);
        e.getPlayer().getInventory().addItem(pot);
      }
    }
  }

  @EventHandler
  public void specialBlockHandling(EntityDamageEvent e) {
    if (e.getEntity().getType().equals(EntityType.ITEM_FRAME)) {
      e.setCancelled(true);
    } else if (e.getEntity().getType().equals(EntityType.GLOW_ITEM_FRAME)) {
      e.setCancelled(true);
    } else if (e.getEntity().getType().equals(EntityType.PAINTING)) {
      e.setCancelled(true);
    }
  }

  @EventHandler
  public void specialEntityHandling(PlayerInteractAtEntityEvent e) {
    if (e.getRightClicked().getType().equals(EntityType.ITEM_FRAME)) {
      e.setCancelled(true);
    } else if (e.getRightClicked().getType().equals(EntityType.GLOW_ITEM_FRAME)) {
      e.setCancelled(true);
    } else if (e.getRightClicked().getType().equals(EntityType.ARMOR_STAND)) {
      e.setCancelled(true);
    }
  }

  @EventHandler
  public void onMedUse(PlayerInteractEvent e) {
    Player p = e.getPlayer();
    Bandit b = Bandit.getPlayer(p);
    ItemStack inHand = p.getInventory().getItemInMainHand();
    if (inHand.hasItemMeta()) {
      if (inHand
          .getItemMeta()
          .getDisplayName()
          .equalsIgnoreCase(
              CustomItem.getCustomItem("Bandage").getItemStack().getItemMeta().getDisplayName())) {
        // They are using a bandage
        if (b.isBleeding()) {
          p.playSound(p.getLocation(), Sound.ENTITY_LEASH_KNOT_PLACE, 1, 1);
          p.playSound(p.getLocation(), Sound.BLOCK_WOOL_PLACE, 1, 0);
          b.setBleeding(false);
          p.sendMessage("You have patched up your wounds!");
          // p.getInventory().removeItem(bandage);
        }
      }
      if (inHand
          .getItemMeta()
          .getDisplayName()
          .equalsIgnoreCase(
              CustomItem.getCustomItem("Splint").getItemStack().getItemMeta().getDisplayName())) {
        if (b.isBrokenLegs()) {
          p.playSound(p.getLocation(), Sound.ITEM_AXE_STRIP, 1, 0);
          b.setBrokenLegs(false);
          p.removePotionEffect(PotionEffectType.SLOW);
          // remove splint
        }
      }
    }
  }

  @EventHandler
  public void onGoldPickUp(PlayerPickupItemEvent e) {
    Player p = e.getPlayer();
    // Ensuring its a gold coin and not something else
    ItemStack pickedUpGold = e.getItem().getItemStack();
    Double namedGold = GlobalUtils.getGoldStrToD(pickedUpGold, p);

    if (pickedUpGold.getType().equals(Material.GOLD_NUGGET)) {
      if (namedGold == -1.0) {
        e.setCancelled(false);
      } else if (namedGold > 0.0) {
        // remove the coin to avoid duping money
        e.getItem().remove();
        e.setCancelled(true);
        // Confirm pick up and give the player their money
        p.sendMessage(ChatColor.GRAY + "You picked up " + ChatColor.GOLD + namedGold + "g");
        Bandit b = Bandit.getPlayer(p);
        b.updateWallet(namedGold);
      }
    }
  }

  @EventHandler
  public void onPlayerBleed(EntityDamageByEntityEvent e) {
    if (e.getEntity() instanceof Player p) {
      int odds = GlobalUtils.getRandomNumber(101);
      if (odds < 5) {
        Bandit b = Bandit.getPlayer(p);
        b.setBleeding(true);
        p.sendMessage(ChatColor.RED + "You are bleeding, you need to bandage your wounds!");
      }
    }
  }

  @EventHandler
  public void globalChatEvents(AsyncPlayerChatEvent e) {
    Player p = e.getPlayer();
    Bandit b = Bandit.getPlayer(p);
    p.setDisplayName(ChatColor.GRAY + p.getDisplayName() + ChatColor.RESET);
    e.setFormat(
        b.getTitle()
            + ChatColor.GRAY
            + "["
            + BanditUtils.getContractorLvlColor(b.getContractorLvl())
            + b.getContractorLvl()
            + ChatColor.GRAY
            + "] "
            + ChatColor.RESET
            + e.getFormat());
  }

  @EventHandler
  public void breakGlassEvent(ProjectileHitEvent e) {
    if (e.getEntityType().equals(EntityType.SNOWBALL)
        || e.getEntityType().equals(EntityType.ARROW)) {
      if (e.getHitBlock() != null) {
        switch (e.getHitBlock().getType()) {
          case LIGHT_GRAY_STAINED_GLASS_PANE,
              GRAY_STAINED_GLASS_PANE,
              GLASS_PANE,
              WHITE_STAINED_GLASS,
              BLACK_STAINED_GLASS,
              BLACK_STAINED_GLASS_PANE,
              LIGHT_GRAY_STAINED_GLASS,
              GRAY_STAINED_GLASS -> {
            e.getHitBlock()
                .getLocation()
                .getWorld()
                .playSound(e.getHitBlock().getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 1);
            new BrokenWindowTask(plugin, e.getHitBlock());
            // start pane respawn timer
          }
        }
      }
    }
  }

  // >>>===--- ENVIRONMENT EVENTS ---===<<<
  @EventHandler
  public void disableUsableBlockClick(PlayerInteractEvent e) {
    Player p = e.getPlayer();
    Block block = e.getClickedBlock();

    if (block != null && !p.isOp()) {
      switch (block.getType()) {
        case BREWING_STAND,
            TRAPPED_CHEST,
            SPRUCE_DOOR,
            BIRCH_DOOR,
            OAK_DOOR,
            SPRUCE_FENCE_GATE,
            OAK_FENCE_GATE,
            DARK_OAK_FENCE_GATE,
            IRON_ORE,
            RAW_IRON_BLOCK,
            RAW_GOLD_BLOCK,
            GOLD_ORE,
            COPPER_ORE,
            RAW_COPPER_BLOCK,
            JUNGLE_SAPLING,
            WITHER_ROSE,
            BLUE_ORCHID,
            TORCHFLOWER,
            WARPED_FUNGUS,
            SWEET_BERRY_BUSH,
            LILY_OF_THE_VALLEY -> {
          e.setCancelled(false);
        }
        case CHEST -> {
          Bandit b = Bandit.getPlayer(p);
          if (b.getCurrentLocation().getType().compareTo(LocationType.TOWN) == 0) {
            e.setCancelled(true);
          } else {
            e.setCancelled(false);
          }
        }
        default -> {
          e.setCancelled(true);
        }
      }
    }
  }

  @EventHandler
  public void enderPearlUse(ProjectileLaunchEvent e) {
    if (e.getEntity() instanceof EnderPearl) {
      e.setCancelled(true);
    }
  }

  @EventHandler
  public void onTreeGrow(StructureGrowEvent e) {
    e.setCancelled(true);
  }

  @EventHandler
  public void onBlockFade(BlockFadeEvent e) {
    e.setCancelled(true);
  }

  @EventHandler
  public void onBlockGrow(BlockGrowEvent e) {
    e.setCancelled(true);
  }

  @EventHandler
  public void onBlockBurn(BlockBurnEvent e) {
    e.setCancelled(true);
  }

  // ------------Crafting Events--------------

  @EventHandler(priority = EventPriority.HIGHEST)
  public void potionItemPlacer(final InventoryClickEvent e) {
    if (e.getClickedInventory() == null) return;
    if (e.getClickedInventory().getType() != InventoryType.BREWING) return;
    if (!(e.getClick() == ClickType.LEFT)) // Make sure we are placing an item
    return;
    final ItemStack is = e.getCurrentItem(); // We want to get the item in the slot
    final ItemStack is2 = e.getCursor().clone(); // And the item in the cursor
    if (is2 == null) // We make sure we got something in the cursor
    return;
    if (is2.getType() == Material.AIR) return;

    Bukkit.getScheduler()
        .scheduleSyncDelayedTask(
            IronSight.instance,
            new Runnable() {
              @SuppressWarnings("deprecation")
              @Override
              public void run() {
                e.setCursor(is); // Now we make the switch
                e.getClickedInventory().setItem(e.getSlot(), is2);
                if (((BrewerInventory) e.getInventory()).isEmpty()) {
                  System.out.println("IN ingredient null");
                  return;
                }
                System.out.println("before recipe");

                BrewingRecipe recipe =
                    BrewingRecipe.getRecipe((BrewerInventory) e.getClickedInventory());
                System.out.println("after recipesssss");
                if (recipe == null) {
                  System.out.println("Null Recipe");
                  return;
                }
                recipe.startBrewing((BrewerInventory) e.getClickedInventory());
                System.out.println("after start brewing");
              }
            },
            1L); // (Delay in 1 tick)
    ((Player) e.getView().getPlayer()).updateInventory(); // And we update the inventory
  }
  /*
  @EventHandler(priority = EventPriority.NORMAL)
  public void PotionListener(InventoryClickEvent e){
      if(e.getClickedInventory() == null)
          return;
      if(e.getClickedInventory().getType() != InventoryType.BREWING)
          return;
      System.out.println("IN");

      //is empty when not empty >:|
      if(((BrewerInventory)e.getInventory()).isEmpty()) {
          System.out.println("IN ingredient null");
          return;
      }
      System.out.println("before recipe");

      BrewingRecipe recipe = BrewingRecipe.getRecipe((BrewerInventory) e.getClickedInventory());
      System.out.println("after recipesssss");
      if(recipe == null) {
          System.out.println("Null Recipe");
          return;
      }
      recipe.startBrewing((BrewerInventory) e.getClickedInventory());
      System.out.println("after start brewing");
  }
  */
  // >>>===--- LOOTING EVENTS ---===<<<
  /*  @EventHandler
   public void onOpenChest(InventoryOpenEvent e) {
       if (e.getInventory().getHolder() instanceof Chest){
           ItemTable table = Location.getLocation("North Oil Field").getItemTable();
           ArrayList<ItemStack> list = table.getNumItems(8);
           for (int i = 0; i < list.size(); i++){
               e.getInventory().addItem(list.get(i));
           }


       }
   }

  */
}
