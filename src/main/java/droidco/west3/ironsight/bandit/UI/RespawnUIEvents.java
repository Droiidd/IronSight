package droidco.west3.ironsight.bandit.UI;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.bandit.Bandit;
import droidco.west3.ironsight.frontierlocation.FrontierLocation;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class RespawnUIEvents implements Listener {
    private final IronSight plugin;

    public RespawnUIEvents(IronSight plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void respawnMenuSelect(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Choose Town:")) {
            Bandit b = Bandit.getPlayer(p);
            e.setCancelled(true);
            FrontierLocation santafe = FrontierLocation.getLocation("Santa Fe");
            FrontierLocation neworleans = FrontierLocation.getLocation("New Orleans");
            FrontierLocation texas = FrontierLocation.getLocation("Republic of Texas");

            org.bukkit.Location sfRespawn = new org.bukkit.Location(p.getWorld(), santafe.getSpawnX(), santafe.getSpawnY(), santafe.getSpawnZ(), 180, 0);
            org.bukkit.Location noRespawn = new org.bukkit.Location(p.getWorld(), neworleans.getSpawnX(), neworleans.getSpawnY(), neworleans.getSpawnZ(), 90, 0);
            org.bukkit.Location rotRespawn = new org.bukkit.Location(p.getWorld(), texas.getSpawnX(), texas.getSpawnY(), texas.getSpawnZ(), 180, 0);
            switch (e.getCurrentItem().getType()) {
                case WHITE_BANNER -> {
                    handleRespawnActions(ChatColor.YELLOW + "Santa Fe",
                            ChatColor.GRAY + "PvP is " + ChatColor.RED + "disabled!", sfRespawn, b, p);
                    break;
                }
                case BLUE_BANNER -> {
                    handleRespawnActions(ChatColor.YELLOW + "New Orleans",
                            ChatColor.GRAY + "PvP is " + ChatColor.RED + "disabled!", noRespawn, b, p);
                    break;

                }
                case YELLOW_BANNER -> {
                    handleRespawnActions(ChatColor.YELLOW + "Republic of Texas",
                            ChatColor.GRAY + "PvP is " + ChatColor.RED + "disabled!", rotRespawn, b, p);
                    break;
                }
            }
            e.setCancelled(true);
        }
    }

    public void handleRespawnActions(String locTitle, String welcomeMsg, org.bukkit.Location respawn, Bandit b, Player p) {
        //ADD A RESPAWN SOUND
        p.sendTitle(locTitle, welcomeMsg);
        p.closeInventory();
        p.setWalkSpeed(0.2f);
        p.setFlySpeed(0.2f);
        p.teleport(respawn);
        b.setRespawning(false);
    }

}
