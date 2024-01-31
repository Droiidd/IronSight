package droidco.west3.ironsight.Objects.Player.Events;

import droidco.west3.ironsight.Objects.Player.IronPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CombatEvents implements Listener
{
    @EventHandler
    public void startCombatLog(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player p){
            //p.sendMessage("You are combat logged!");
            IronPlayer iPlayer = IronPlayer.getPlayer(p);
            iPlayer.setCombatBlockFlag(true);
            if(!iPlayer.isCombatBlocked()){
             iPlayer.setCombatBlocked(true);
            }
        }else if(e.getDamager() instanceof Player p){
                //p.sendMessage("You are combat logged!");
                IronPlayer iPlayer = IronPlayer.getPlayer(p);
                iPlayer.setCombatBlockFlag(true);
                if(!iPlayer.isCombatBlocked()){
                    iPlayer.setCombatBlocked(true);
                    p.sendMessage(ChatColor.GRAY+"You are "+ChatColor.RED+"combat blocked "+ChatColor.GRAY+"don't log-out!");
                }
        }
    }
}
