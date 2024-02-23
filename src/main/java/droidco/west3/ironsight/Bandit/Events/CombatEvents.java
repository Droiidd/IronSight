package droidco.west3.ironsight.Bandit.Events;

import droidco.west3.ironsight.Bandit.Bandit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class CombatEvents implements Listener
{
    @EventHandler
    public void startCombatTimers(EntityDamageByEntityEvent e){
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
            if(!b.isCombatBlocked()){
                b.setCombatBlocked(true);
                p.sendMessage(ChatColor.GRAY+"You are "+ChatColor.RED+"combat blocked "+ChatColor.GRAY+"don't log-out!");

            }
            if(!b.isWanted()){
                b.setWanted(true);
                Bukkit.getServer().broadcastMessage(b.getTitle()+ChatColor.RESET+p.getDisplayName()+" has gone "+ChatColor.DARK_RED+"rogue!");
            }
        }
    }

    @EventHandler

    public void playerDies(PlayerDeathEvent e){
            Bandit b = Bandit.getPlayer(e.getEntity());
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
            if(b.getBounty() > 200){
                //Send em to prison
                b.setJailed(true);
            }else{
                b.setJailed(false);
                b.setBounty(0);
            }
    }
}
