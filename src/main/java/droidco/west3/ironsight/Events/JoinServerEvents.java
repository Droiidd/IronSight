package droidco.west3.ironsight.Events;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Objects.Player.Bandit;
import droidco.west3.ironsight.Objects.Player.Tasks.BanditTask;
import droidco.west3.ironsight.Database.PlayerConnector;
import droidco.west3.ironsight.Utils.PlayerUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinServerEvents implements Listener{
    IronSight plugin;
    public JoinServerEvents(IronSight plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        Bandit iPlayer = PlayerConnector.fetchPlayer(p);
        if(iPlayer == null){
            System.out.println("New player!");
            p.sendMessage("New player!");
            iPlayer = new Bandit(p.getUniqueId().toString());
        }

        iPlayer.setOnlinePlayer(p);
        PlayerUtils.displayBasicStats(iPlayer, p);
        BanditTask playerLifeTracker = new BanditTask(plugin, iPlayer, p);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();
        Bandit iPlayer = Bandit.getPlayer(p);
        if(iPlayer.isCombatBlocked()){
            p.damage(10000.0);
            iPlayer.setCombatBlocked(false);
        }
        PlayerConnector.updatePlayer(Bandit.getPlayer(p));
    }

}



