package droidco.west3.ironsight.bandit.Commands;

import droidco.west3.ironsight.bandit.Bandit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SuicideCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player p) {
            Bandit b = Bandit.getPlayer(p);
            if (strings.length != 0) {
                //Player entered commands with no args
                //show options
                p.sendMessage(ChatColor.DARK_RED + "Iron Sight" + ChatColor.GRAY + " Command options:");
                p.sendMessage(ChatColor.AQUA + ": /suicide ");

            } else {
                p.damage(20);
            }
        }


        return true;
    }
}
