package droidco.west3.ironsight.Globals.Events;

import droidco.west3.ironsight.Horse.FrontierHorse;
import droidco.west3.ironsight.Horse.FrontierHorseType;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Bandit.Tasks.BanditTask;
import droidco.west3.ironsight.Database.PlayerConnector;
import droidco.west3.ironsight.Globals.Utils.BanditUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class JoinServerEvents implements Listener{
    IronSight plugin;
    public JoinServerEvents(IronSight plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void sendJoinMessage(PlayerMoveEvent e){
        Player p = e.getPlayer();
        Bandit b = Bandit.getPlayer(p);
        if(b.isJustJoined()){
            b.setJustJoined(false);
            p.sendMessage(ChatColor.GRAY+"Right click with your "+ChatColor.AQUA+"tracker "+ ChatColor.GRAY+"in hand to navigate to a location.");
            p.playSound(p.getLocation(), Sound.AMBIENT_WARPED_FOREST_MOOD,1,0);
            p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN,1,0);
            p.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE,1,1);
            p.sendTitle(ChatColor.DARK_RED+ String.valueOf(ChatColor.BOLD) + "Iron Sight", "");
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        Bandit b = PlayerConnector.fetchAllPlayerData(p);
        p.getWorld().setGameRule(GameRule.DO_IMMEDIATE_RESPAWN,true);
        b.setJustJoined(true);

        if(b == null){
            System.out.println("New player!");
            p.sendMessage("New player!");
            b = new Bandit(p.getUniqueId().toString());
            List<FrontierHorse> horses = b.getHorses();
            horses.add( new FrontierHorse(p.getUniqueId().toString(),"Starter", FrontierHorseType.STANDARD));
            p.teleport(new Location(p.getWorld(),1055,94,-1950));
            p.setRespawnLocation(new Location(p.getWorld(),1055,94,-1950));
        }

        b.setOnlinePlayer(p);
        BanditUtils.displayBasicStats(b, p);
        BanditTask playerLifeTracker = new BanditTask(plugin, b, p);

        //check if the player is in prison, and can be released
        if(b.isJailed()){
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - b.getJailStartTime();
            int elap = (int) elapsedTime / 1000;
            if (elap >= b.getBounty()) {
                //Player has waited enough time
                BanditUtils.releasePrisoner(p,b);
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();
        Bandit iPlayer = Bandit.getPlayer(p);
        if(iPlayer.isCombatBlocked()){
            p.damage(10000.0);
            iPlayer.setCombatBlocked(false);
        }
        PlayerConnector.updatePlayer(Bandit.getPlayer(p),p);
    }

}



