package droidco.west3.ironsight.Bandit.UI;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.IronSight;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class RespawnUIEvents implements Listener {
    private IronSight plugin;
    public RespawnUIEvents(IronSight plugin){
        this.plugin = plugin;
    }

                           @EventHandler
    public void respawnMenuSelect(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Choose Town:")){
            Bandit b = Bandit.getPlayer(p);
            e.setCancelled(true);
            FrontierLocation santafe = FrontierLocation.getLocation("Santa Fe");
            FrontierLocation neworleans = FrontierLocation.getLocation("New Orleans");
            FrontierLocation texas = FrontierLocation.getLocation("Republic of Texas");

            org.bukkit.Location sfRespawn = new org.bukkit.Location(p.getWorld(),santafe.getSpawnX(),santafe.getSpawnY(),santafe.getSpawnZ());
            org.bukkit.Location noRespawn = new org.bukkit.Location(p.getWorld(),neworleans.getSpawnX(),neworleans.getSpawnY(),neworleans.getSpawnZ());
            org.bukkit.Location rotRespawn = new org.bukkit.Location(p.getWorld(),texas.getSpawnX(),texas.getSpawnY(),texas.getSpawnZ());
            switch(e.getCurrentItem().getType()){
                case WHITE_BANNER -> {
                   handleRespawnActions(ChatColor.YELLOW+"Santa Fe",
                           ChatColor.GRAY+"PvP is "+ChatColor.RED+"disabled!",sfRespawn,b,p);
                   break;
                }
                case YELLOW_BANNER -> {
                    handleRespawnActions(ChatColor.YELLOW+"New Orleans",
                            ChatColor.GRAY+"PvP is "+ChatColor.RED+"disabled!",noRespawn,b,p);
                    break;

                }
                case BLUE_BANNER -> {
                    handleRespawnActions(ChatColor.YELLOW+"Republic of Texas",
                            ChatColor.GRAY+"PvP is "+ChatColor.RED+"disabled!",rotRespawn,b,p);
                    break;
                }
            }
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void respawnHandler(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        Bandit b = Bandit.getPlayer(p);
        if(b.isJailed()){
            p.setRespawnLocation(FrontierLocation.getLocation("Prison").getSpawnLocation(p));
            b.setRespawning(false);
                    // SEND PLAYER TO PRISON
            p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, 1);
            p.sendTitle(ChatColor.GRAY + "You are now in" + ChatColor.DARK_RED + " Prison!", ChatColor.GRAY + "Mine to 0 bounty to leave.");
            p.teleport(FrontierLocation.getLocation("Prison").getSpawnLocation(p));
        }else{
            p.setRespawnLocation(new Location(p.getWorld(),1055,94,-1950));
            b.setRespawning(true);
        }
    }

    public void handleRespawnActions(String locTitle, String welcomeMsg, org.bukkit.Location respawn, Bandit b, Player p){
      //ADD A RESPAWN SOUND
        p.sendTitle(locTitle,welcomeMsg);
        p.closeInventory();
        p.setWalkSpeed(0.2f);
        p.setFlySpeed(0.2f);
        p.teleport(respawn);
        b.setRespawning(false);
        //p.playSound(p.getLocation(), Sound.ITEM);
    }

}
