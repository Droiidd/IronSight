package droidco.west3.ironsight.Objects.Player.Events;

import droidco.west3.ironsight.Objects.Location.Location;
import droidco.west3.ironsight.Objects.Location.LocationUI;
import droidco.west3.ironsight.Objects.Player.IronPlayer;
import droidco.west3.ironsight.Utils.GlobalUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GeneralEvents implements Listener {
    @EventHandler
    public void respawnHandler(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        //p.openInventory(LocationUI.openContractorTitleSelectUi(p));
//        Location santafe = Location.getLocation("Santa Fe");
//        Location neworleans = Location.getLocation("New Orleans");
//        Location texas = Location.getLocation("Republic of Texas");
//        Get the bukkit location of the respawn points from the Iron Sight Location (confusing)
//        org.bukkit.Location sfRespawn = new org.bukkit.Location(p.getWorld(),santafe.getSpawnX(),santafe.getSpawnY(),santafe.getSpawnZ());
//        org.bukkit.Location noRespawn = new org.bukkit.Location(p.getWorld(),neworleans.getSpawnX(),neworleans.getSpawnY(),neworleans.getSpawnZ());
//        org.bukkit.Location rotRespawn = new org.bukkit.Location(p.getWorld(),texas.getSpawnX(),texas.getSpawnY(),texas.getSpawnZ());
//        p.teleport(noRespawn);

        IronPlayer iPlayer = IronPlayer.getPlayer(p);
        iPlayer.setRespawning(true);
    }
    @EventHandler
    public void onLegBreak(EntityDamageEvent e){
        if(e.getEntity() instanceof Player p){
            float fall = p.getFallDistance();
            if(fall > 9) {
                IronPlayer iPlayer = IronPlayer.getPlayer(p);
                if(iPlayer.isBrokenLegs()){
                    //Players legs are already broken, damage harder
                    p.damage(3.0);
                }
                else{
                    //Player broke their legs!
                    iPlayer.setBrokenLegs(true);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 3));
                    p.sendMessage("You broke your legs!");
                }
            }
        }
    }
    @EventHandler
    public void onMedUse(PlayerInteractEvent e){
        Player p = e.getPlayer();
        IronPlayer iPlayer = IronPlayer.getPlayer(p);
        ItemStack inHand = p.getInventory().getItemInMainHand();
        if(inHand.hasItemMeta()){
            if (inHand.getItemMeta().getDisplayName().equalsIgnoreCase(
                    "bandage")) {
                //They are using a bandage
                if (iPlayer.isBleeding()) {
                    p.playSound(p.getLocation(), Sound.ENTITY_LEASH_KNOT_PLACE, 1, 1);
                    p.playSound(p.getLocation(), Sound.BLOCK_WOOL_PLACE, 1, 0);
                    iPlayer.setBleeding(false);
                    p.sendMessage("You have patched up your wounds!");
                    //p.getInventory().removeItem(bandage);
                }
            }
            if(inHand.getItemMeta().getDisplayName().equalsIgnoreCase(
                    "Splint")){
                if(iPlayer.isBrokenLegs()){
                    p.playSound(p.getLocation(), Sound.ITEM_AXE_STRIP, 1, 0);
                    iPlayer.setBrokenLegs(false);
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
                IronPlayer iPlayer = IronPlayer.getPlayer(p);
                iPlayer.setBleeding(true);
                p.sendMessage(ChatColor.RED+"You are bleeding, you need to bandage your wounds!");
            }
        }
    }

    @EventHandler
    public void globalChatEvents(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        IronPlayer iPlayer = IronPlayer.getPlayer(p);
        e.setFormat(iPlayer.getTitle()+ChatColor.RESET+e.getFormat());
    }

    // >>>===--- ENVIRONMENT EVENTS ---===<<<
    @EventHandler
    public void onBarrelClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block block = e.getClickedBlock();
        if(block != null){
            if (block.getType() == Material.BARREL) {
                p.sendMessage("SHIT");
                e.setCancelled(true);
            }
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
}
