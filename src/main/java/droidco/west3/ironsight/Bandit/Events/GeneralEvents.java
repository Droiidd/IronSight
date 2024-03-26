package droidco.west3.ironsight.Bandit.Events;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Items.Potions.BrewingRecipe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class GeneralEvents implements Listener {
    @EventHandler
    public void respawnHandler(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        Bandit b = Bandit.getPlayer(p);
        if(b.isJailed()){
            b.setJailedFlag(true);
        }
            b.setRespawning(true);
    }
    @EventHandler
    public void onLegBreak(EntityDamageEvent e){
        if(e.getEntity() instanceof Player p){
            float fall = p.getFallDistance();
            if(fall > 9) {
                Bandit b = Bandit.getPlayer(p);
                if(b.isBrokenLegs()){
                    //Players legs are already broken, damage harder
                    p.damage(3.0);
                }
                else{
                    //Player broke their legs!
                    b.setBrokenLegs(true);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 3));
                    p.sendMessage("You broke your legs!");
                }
            }
        }
    }
    @EventHandler
    public void onMedUse(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Bandit b = Bandit.getPlayer(p);
        ItemStack inHand = p.getInventory().getItemInMainHand();
        if(inHand.hasItemMeta()){
            if (inHand.getItemMeta().getDisplayName().equalsIgnoreCase(
                    "Bandage")) {
                //They are using a bandage
                if (b.isBleeding()) {
                    p.playSound(p.getLocation(), Sound.ENTITY_LEASH_KNOT_PLACE, 1, 1);
                    p.playSound(p.getLocation(), Sound.BLOCK_WOOL_PLACE, 1, 0);
                    b.setBleeding(false);
                    p.sendMessage("You have patched up your wounds!");
                    //p.getInventory().removeItem(bandage);
                }
            }
            if(inHand.getItemMeta().getDisplayName().equalsIgnoreCase(
                    "Splint")){
                if(b.isBrokenLegs()){
                    p.playSound(p.getLocation(), Sound.ITEM_AXE_STRIP, 1, 0);
                    b.setBrokenLegs(false);
                    p.removePotionEffect(PotionEffectType.SLOW);
                    //remove splint
                }
            }
        }

    }
    @EventHandler
    public void onPlayerBleed(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player p){
            int odds = GlobalUtils.getRandomNumber(101);
            if(odds < 5){
                Bandit b = Bandit.getPlayer(p);
                b.setBleeding(true);
                p.sendMessage(ChatColor.RED+"You are bleeding, you need to bandage your wounds!");
            }
        }
    }

    @EventHandler
    public void globalChatEvents(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        Bandit b = Bandit.getPlayer(p);
        e.setFormat(b.getTitle()+ChatColor.RESET+e.getFormat());
    }

    // >>>===--- ENVIRONMENT EVENTS ---===<<<
    @EventHandler
    public void onBarrelClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block block = e.getClickedBlock();
        if(block != null){
            if (block.getType() == Material.BARREL) {
                //p.sendMessage("SHIT");
                e.setCancelled(true);
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
    public void onTreeGrow(StructureGrowEvent e) {e.setCancelled(true);}
    @EventHandler
    public void onBlockFade(BlockFadeEvent e) {e.setCancelled(true);}
    @EventHandler
    public void onBlockGrow(BlockGrowEvent e) {e.setCancelled(true);}
    @EventHandler
    public void onBlockBurn(BlockBurnEvent e){ e.setCancelled(true);}

    // ------------Crafting Events--------------

    @EventHandler(priority = EventPriority.HIGHEST)
    public void potionItemPlacer(final InventoryClickEvent e) {
        if (e.getClickedInventory() == null)
            return;
        if (e.getClickedInventory().getType() != InventoryType.BREWING)
            return;
        if (!(e.getClick() == ClickType.LEFT)) //Make sure we are placing an item
            return;
        final ItemStack is = e.getCurrentItem(); //We want to get the item in the slot
        final ItemStack is2 = e.getCursor().clone(); //And the item in the cursor
        if(is2 == null) //We make sure we got something in the cursor
            return;
        if(is2.getType() == Material.AIR)
            return;

        Bukkit.getScheduler().scheduleSyncDelayedTask(IronSight.instance, new Runnable() {
            @SuppressWarnings("deprecation")
            @Override
            public void run() {
                e.setCursor(is);//Now we make the switch
                e.getClickedInventory().setItem(e.getSlot(), is2);
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
        }, 1L);//(Delay in 1 tick)
        ((Player)e.getView().getPlayer()).updateInventory();//And we update the inventory


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

}
