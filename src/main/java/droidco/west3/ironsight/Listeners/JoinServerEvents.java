package droidco.west3.ironsight.Listeners;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Player.IronPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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
        displayBasicStats(newPlayer, p);
    }

    public void displayBasicStats(IronPlayer iP, Player p)
    {
        p.sendMessage("Iron Sight Player Stats:");
        p.sendMessage("============");
        p.sendMessage("Bleeding: "+iP.isBleeding());
        p.sendMessage("Broken Legs: "+iP.isBleeding());
        p.sendMessage("Bounty: "+iP.isBleeding());
        p.sendMessage("Bank: "+iP.isBleeding());
        p.sendMessage("Wallet: "+iP.isBleeding());
        p.sendMessage("EXPERIENCE INFO: ");
        p.sendMessage("Combat Level: "+iP.isBleeding());
        p.sendMessage("Peacemaker Level: "+iP.isBleeding());
        p.sendMessage("Combat XP: "+iP.isBleeding());
        p.sendMessage("Peacemaker XP: "+iP.isBleeding());
        p.sendMessage("Wanted Kills: "+iP.isBleeding());

    }

}



