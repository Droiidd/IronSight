package droidco.west3.ironsight.Horse;
import droidco.west3.ironsight.Bandit.Bandit;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public class HorseEvents implements Listener {

    @EventHandler
    public void onHorseSummon(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_RED + ChatColor.stripColor(p.getDisplayName()) + ChatColor.DARK_GRAY + "'s horses")) {
            e.setCancelled(true);
            Bandit b = Bandit.getPlayer(p);
            List<FrontierHorse> horses = b.getHorses();
            if (e.getCurrentItem().getType().equals(Material.NAME_TAG)) {
                for (FrontierHorse horse : horses) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.stripColor(horse.getHorseName()))) {
                        p.closeInventory();
                        if (b.isSummoningHorse()) {
                            p.sendMessage("Already calling a horse!");
                        } else {
                            b.setSummoningHorse(true);
                            b.setHorseBeingSummoned(horse);
                        }
                    }
                }
            }
        }
    }



}

