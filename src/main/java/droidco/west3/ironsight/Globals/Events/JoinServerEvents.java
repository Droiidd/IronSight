package droidco.west3.ironsight.Globals.Events;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Bandit.BanditTask;
import droidco.west3.ironsight.Database.PlayerConnector;
import droidco.west3.ironsight.Globals.Utils.BanditUtils;
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
        Bandit b = PlayerConnector.fetchPlayer(p);
        if(b == null){
            System.out.println("New player!");
            p.sendMessage("New player!");
            b = new Bandit(p.getUniqueId().toString());
        }

        b.setOnlinePlayer(p);
        BanditUtils.displayBasicStats(b, p);
        BanditTask playerLifeTracker = new BanditTask(plugin, b, p);
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



