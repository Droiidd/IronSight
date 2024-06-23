package droidco.west3.ironsight.Bandit.Commands;

import droidco.west3.ironsight.Bandit.Bandit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class PlayCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p){
            Bandit b = Bandit.getPlayer(p);
            if(strings.length != 0){
                //Player entered commands with no args
                //show options
                p.sendMessage(ChatColor.DARK_RED+ "Iron Sight"+ChatColor.GRAY+" Command options:");
                p.sendMessage(  ChatColor.WHITE+"-> "+ChatColor.AQUA+"/play ");

            }
            else {
                String str = "mv tp IronSight";
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, str);
            }
        }


        return true;
    }
}
