package droidco.west3.ironsight.Bandit.Events;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.FrontierLocation.LocationType;
import droidco.west3.ironsight.Globals.Utils.BanditUtils;
import droidco.west3.ironsight.Items.CustomItem;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class CombatEvents implements Listener
{
    @EventHandler
    public void startCombatTimers(EntityDamageByEntityEvent e){
        boolean playerInTown = false;
        List<EntityType> bannedTypes = new ArrayList<>();
        bannedTypes.add(EntityType.VILLAGER);
        bannedTypes.add(EntityType.ARMOR_STAND);
        bannedTypes.add(EntityType.ITEM_FRAME);
        bannedTypes.add(EntityType.GLOW_ITEM_FRAME);
        if(e.getDamager() instanceof Player p){
            Bandit b = Bandit.getPlayer(p);
            if(b.getCurrentLocation().getType().equals(LocationType.TOWN)){
                playerInTown = true;
                bannedTypes.add(EntityType.PLAYER);
                bannedTypes.add(EntityType.HORSE);
                bannedTypes.add(EntityType.CAMEL);
                bannedTypes.add(EntityType.DONKEY);
            }
        }

        // CANCEL ALL PVP
        for(EntityType type : bannedTypes){

            if(e.getEntity().getType().equals(type)){
                e.setCancelled(true);
            }
        }
        if(!playerInTown){
            if((e.getEntity() instanceof Player p) && e.getDamager() instanceof Player damager){

                //SET VICTIM COMBAT LOGGER
                Bandit b = Bandit.getPlayer(p);
                b.setCombatBlockFlag(true);
                if(!b.isCombatBlocked()){
                    b.setCombatBlocked(true);
                    p.sendMessage(ChatColor.GRAY+"You are "+ChatColor.RED+"combat blocked "+ChatColor.GRAY+"don't log-out!");

                }
                //SET DAMAGER COMBAT LOGGER
                Bandit b2 = Bandit.getPlayer(damager);
                b2.setCombatBlockFlag(true);
                    if(!b2.isCombatBlocked()){
                        b2.setCombatBlocked(true);
                        p.sendMessage(ChatColor.GRAY+"You are "+ChatColor.RED+"combat blocked "+ChatColor.GRAY+"don't log-out!");

                    }
                    if(!b2.isWanted()){
                        b2.setWanted(true);
                        Bukkit.getServer().broadcastMessage(b2.getTitle() +ChatColor.RESET+damager.getDisplayName()+" has gone "+ChatColor.DARK_RED+"rogue!");
                    }
            }
        }
    }

    @EventHandler
    public void playerDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            Bandit b = Bandit.getPlayer(p);
            if(p.getHealth() <= 0.0 || (p.getHealth() - e.getDamage()) <= 0.0){
                //PLAYER DIED
                e.setCancelled(true);
                p.removePotionEffect(PotionEffectType.SLOW);
                if(b.isCombatBlocked()){
                    b.setCombatBlocked(false);
                }
                if(b.isWanted()){
                    b.setWanted(false);
                }
                if(b.isBleeding()){
                    b.setBleeding(false);
                }
                if(b.isBrokenLegs()){
                    b.setBrokenLegs(false);
                }
                p.playSound(p.getLocation(),Sound.ITEM_TOTEM_USE,1 ,0);
                p.playSound(p.getLocation(),Sound.ENTITY_IRON_GOLEM_DEATH,1 ,2);
                if (b.getWallet() > 0.0) {

                    p.getWorld().dropItem(p.getLocation(), new CustomItem(b.getWallet()+"",1,true,false,"", Material.GOLD_NUGGET,0.0,0.0).getItemStack());
                }
                b.setWallet(0);
                p.teleport(new Location(p.getWorld(), 1056,317,-1957));
                p.setVisualFire(false);
                p.setFireTicks(0);
                p.setHealth(20);
                p.setFoodLevel(20);


                //SEND TO JAIL
                if(b.getBounty() >= 100){
                    b.setJailed(true);
                    FrontierLocation prison = FrontierLocation.getLocation("Prison");
                    p.setRespawnLocation(FrontierLocation.getLocation("Prison").getSpawnLocation(p));
                    b.setJailStartTime(System.currentTimeMillis());
                    p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, 1);
                    p.sendTitle(ChatColor.GRAY + "You are now in" + ChatColor.DARK_RED + " Prison!", ChatColor.GRAY + "Mine to 0 bounty to leave.");
                    p.teleport(FrontierLocation.getLocation("Prison").getSpawnLocation(p));
                }else{
                    b.setJailed(false);
                    p.sendTitle(ChatColor.GRAY + "You " + ChatColor.DARK_RED + "Died!", ChatColor.GRAY + "Choose a town to respawn");
                    b.setRespawning(true);
                }

            }
        }
    }
}
