package droidco.west3.ironsight.Objects.Location;

import droidco.west3.ironsight.Objects.Contracts.Contract;
import droidco.west3.ironsight.Objects.Contracts.Utils.ContractUI;
import droidco.west3.ironsight.Objects.Player.IronPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class LocationUiEvents implements Listener {

    @EventHandler
    public void respawnMenuSelect(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Choose Town:")){
            IronPlayer iPlayer = IronPlayer.getPlayer(p);
            Location santafe = Location.getLocation("Santa Fe");
            Location neworleans = Location.getLocation("New Orleans");
            Location texas = Location.getLocation("Republic of Texas");
            org.bukkit.Location sfRespawn = new org.bukkit.Location(p.getWorld(),santafe.getSpawnX(),santafe.getSpawnY(),santafe.getSpawnZ());
            org.bukkit.Location noRespawn = new org.bukkit.Location(p.getWorld(),neworleans.getSpawnX(),neworleans.getSpawnY(),neworleans.getSpawnZ());
            org.bukkit.Location rotRespawn = new org.bukkit.Location(p.getWorld(),texas.getSpawnX(),texas.getSpawnY(),texas.getSpawnZ());
            switch(e.getCurrentItem().getType()){
                case NETHER_STAR -> {
                    p.closeInventory();
                    p.teleport(sfRespawn);
                    p.sendMessage(ChatColor.GRAY+"Welcome to "+ChatColor.YELLOW+"Santa Fe");
                }
                case CAKE -> {
                    p.closeInventory();
                    p.teleport(noRespawn);
                    p.sendMessage(ChatColor.GRAY+"Welcome to "+ChatColor.YELLOW+"New Orleans");
                }
                case DEAD_BUSH -> {
                    p.closeInventory();
                    p.teleport(rotRespawn);
                    p.sendMessage(ChatColor.GRAY+"Welcome to the "+ChatColor.YELLOW+"Republic of Texas");
                }
            }
            e.setCancelled(true);
        }
    }

}
