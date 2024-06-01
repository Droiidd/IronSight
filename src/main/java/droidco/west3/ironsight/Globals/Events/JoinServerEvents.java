package droidco.west3.ironsight.Globals.Events;

import droidco.west3.ironsight.Horse.FrontierHorse;
import droidco.west3.ironsight.Horse.FrontierHorseType;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Bandit.Tasks.BanditTask;
import droidco.west3.ironsight.Database.PlayerConnector;
import droidco.west3.ironsight.Globals.Utils.BanditUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class JoinServerEvents implements Listener{
    IronSight plugin;
    public JoinServerEvents(IronSight plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
         Player p = e.getPlayer();
        p.sendMessage(p.getUniqueId().toString());
        Bandit b = PlayerConnector.fetchAllPlayerData(p);
        p.getWorld().setGameRule(GameRule.DO_IMMEDIATE_RESPAWN,true);
        p.sendMessage(ChatColor.GRAY+"Right click with your "+ChatColor.AQUA+"tracker "+ ChatColor.GRAY+"in hand to navigate to a location.");
        if(b == null){
            System.out.println("New player!");
            p.sendMessage("New player!");
            b = new Bandit(p.getUniqueId().toString());
            BanditTask playerLifeTracker = new BanditTask(plugin, b, p);
            List<FrontierHorse> horses = b.getHorses();
            horses.add( new FrontierHorse(p.getUniqueId().toString(),"Starter", FrontierHorseType.STANDARD));
            p.teleport(new Location(p.getWorld(),1055,94,-1950));
            p.setRespawnLocation(new Location(p.getWorld(),1055,94,-1950));
        }else{
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



