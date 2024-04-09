package droidco.west3.ironsight.Bandit.Events;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.FrontierLocation.LocationType;
import droidco.west3.ironsight.Globals.Utils.BanditUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class CombatEvents implements Listener
{
    @EventHandler
    public void startCombatTimers(EntityDamageByEntityEvent e){
        if(e.getEntity().getType().equals(EntityType.VILLAGER)){
            e.setCancelled(true);
        }
        if(e.getEntity() instanceof Player p){
            //p.sendMessage("You are combat logged!");
            Bandit b = Bandit.getPlayer(p);
            b.setCombatBlockFlag(true);
            if(!b.isCombatBlocked()){
                b.setCombatBlocked(true);
                p.sendMessage(ChatColor.GRAY+"You are "+ChatColor.RED+"combat blocked "+ChatColor.GRAY+"don't log-out!");

            }
        }else if(e.getDamager() instanceof Player p){
            Bandit b = Bandit.getPlayer(p);
            b.setCombatBlockFlag(true);
            if(!b.getCurrentLocation().getType().equals(LocationType.TOWN)){
                if(!b.isCombatBlocked()){
                    b.setCombatBlocked(true);
                    p.sendMessage(ChatColor.GRAY+"You are "+ChatColor.RED+"combat blocked "+ChatColor.GRAY+"don't log-out!");

                }
                if(!b.isWanted()){
                    b.setWanted(true);
                    Bukkit.getServer().broadcastMessage(b.getTitle() +ChatColor.RESET+p.getDisplayName()+" has gone "+ChatColor.DARK_RED+"rogue!");
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
                    b.setRespawning(true);
                }

            }
        }
    }
}
