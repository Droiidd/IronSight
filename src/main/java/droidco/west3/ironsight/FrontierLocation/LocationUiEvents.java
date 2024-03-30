package droidco.west3.ironsight.FrontierLocation.;

import droidco.west3.ironsight.Bandit.Bandit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class LocationUiEvents implements Listener {

    @EventHandler
    public void respawnMenuSelect(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Choose Town:")){
            Bandit b = Bandit.getPlayer(p);
            e.setCancelled(true);
<<<<<<< HEAD:src/main/java/droidco/west3/ironsight/FrontierLocation/LocationUiEvents.java
            FrontierLocation santafe = FrontierLocation.getLocation("Santa Fe");
            FrontierLocation neworleans = FrontierLocation.getLocation("New Orleans");
            FrontierLocation texas = FrontierLocation.getLocation("Republic Of Texas");
=======
            Location santafe = Location.getLocation("Santa Fe");
            Location neworleans = Location.getLocation("New Orleans");
            Location texas = Location.getLocation("Republic of Texas");
>>>>>>> 0e87fc57c114b06cd9c7f60b73793cabdd0d6e93:src/main/java/droidco/west3/ironsight/Location/LocationUiEvents.java

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
                    handleRespawnActions(ChatColor.YELLOW+"Republic Of Texas",
                            ChatColor.GRAY+"PvP is "+ChatColor.RED+"disabled!",rotRespawn,b,p);
                    break;
                }
            }
            e.setCancelled(true);
        }
    }

    public void handleRespawnActions(String locTitle, String welcomeMsg, org.bukkit.Location respawn, Bandit iP, Player p){
      //ADD A RESPAWN SOUND
        p.sendTitle(locTitle,welcomeMsg);
        p.closeInventory();
        p.setWalkSpeed(0.2f);
        p.setFlySpeed(0.2f);
        p.teleport(respawn);
        //p.playSound(p.getLocation(), Sound.ITEM);
    }

}
