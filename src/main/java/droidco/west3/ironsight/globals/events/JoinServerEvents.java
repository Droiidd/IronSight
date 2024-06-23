package droidco.west3.ironsight.globals.events;

import droidco.west3.ironsight.horse.FrontierHorse;
import droidco.west3.ironsight.horse.FrontierHorseType;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.bandit.Bandit;
import droidco.west3.ironsight.bandit.tasks.BanditTask;
import droidco.west3.ironsight.database.PlayerConnector;
import droidco.west3.ironsight.globals.utils.BanditUtils;
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
        Bandit b = PlayerConnector.pullAllPlayerData(p);
        p.getWorld().setGameRule(GameRule.DO_IMMEDIATE_RESPAWN,true);
        p.sendMessage(ChatColor.WHITE+"===--- "+ ChatColor.DARK_RED+"WELCOME TO IRON SIGHT"+ChatColor.WHITE+" ---===");
        p.sendMessage(ChatColor.WHITE+"->"+ChatColor.GRAY+" Earn "+ChatColor.GREEN+"gold "+ChatColor.GRAY+" by completing contracts!");
        p.sendMessage(ChatColor.WHITE+ "->"+ChatColor.GRAY+" Navigate to a contractor to view available quests.");
        p.sendMessage(ChatColor.WHITE+"->"+ChatColor.GRAY+ " Right click with your "+ChatColor.AQUA+"tracker "+ ChatColor.GRAY+"in hand to navigate to a location.");

        if(b == null){
            b = new Bandit(p.getUniqueId().toString());
            BanditTask playerLifeTracker = new BanditTask(plugin, b, p);
            List<FrontierHorse> horses = b.getHorses();
            horses.add( new FrontierHorse(p.getUniqueId().toString(),"Starter", FrontierHorseType.STANDARD));
            p.teleport(new Location(p.getWorld(),1055,94,-1950));
            p.setRespawnLocation(new Location(p.getWorld(),1055,94,-1950));
            BanditUtils.getStarterItems(p);
        }else{
            b.setOnlinePlayer(p);
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
        Bandit b = Bandit.getPlayer(p);
        if(b.isCombatBlocked()){
            p.damage(10000.0);
            b.setCombatBlocked(false);
        }
        PlayerConnector.updatePlayer(b,p);
    }

}



