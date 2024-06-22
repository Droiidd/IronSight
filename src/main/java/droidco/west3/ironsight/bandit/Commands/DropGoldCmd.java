package droidco.west3.ironsight.bandit.Commands;

import droidco.west3.ironsight.bandit.Bandit;
import droidco.west3.ironsight.items.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DropGoldCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player p) {
            Bandit b = Bandit.getPlayer(p);
            if (strings.length != 1) {
                //Player entered commands with no args
                //show options
                p.sendMessage("Iron Sight Command options:");
                p.sendMessage("/dropgold *amount*");

            } else {
                double amt = Double.parseDouble(strings[0]);
                if (amt > 0) {
                    if (b.getWallet() >= amt) {
                        b.updateWallet(-amt);
                        p.sendMessage(ChatColor.GRAY + "You dropped " + ChatColor.GREEN + amt + "g");
                        p.getWorld().dropItemNaturally(new Location(p.getWorld(), p.getLocation().getX() + 2, p.getLocation().getY(), p.getLocation().getZ()), new CustomItem(amt + "", 1, true, false, "", Material.GOLD_NUGGET, 0.0, 0.0).getItemStack());
                    } else {
                        p.sendMessage(ChatColor.RED + "Not enough funds");
                    }
                }

            }
        }


        return true;
    }
}
