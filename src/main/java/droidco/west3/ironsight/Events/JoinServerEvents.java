package droidco.west3.ironsight.Events;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Player.IronPlayer;
import droidco.west3.ironsight.Player.Tasks.PlayerTask;
import droidco.west3.ironsight.Utils.PlayerConnector;
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
        IronPlayer newPlayer = new IronPlayer(p.getUniqueId().toString(), plugin);
        newPlayer.setOnlinePlayer(p);
        PlayerUtils.displayBasicStats(newPlayer, p);
        PlayerTask playerLifeTracker = new PlayerTask(plugin, newPlayer, p);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        System.out.println("Updating Player...");
        PlayerConnector.updatePlayer();
    }

}



