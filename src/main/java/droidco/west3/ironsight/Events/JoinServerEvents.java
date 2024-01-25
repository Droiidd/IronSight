package droidco.west3.ironsight.Events;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Player.IronPlayer;
import droidco.west3.ironsight.Player.Tasks.PlayerTask;
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
        IronPlayer iPlayer = PlayerConnector.fetchPlayer(p);
        if(iPlayer == null){
            System.out.println("New player!");
            p.sendMessage("New player!");
            iPlayer = new IronPlayer(p.getUniqueId().toString());
        }

        iPlayer.setOnlinePlayer(p);
        PlayerUtils.displayBasicStats(iPlayer, p);
        PlayerTask playerLifeTracker = new PlayerTask(plugin, iPlayer, p);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();
        PlayerConnector.updatePlayer(IronPlayer.getPlayer(p));
    }

}



