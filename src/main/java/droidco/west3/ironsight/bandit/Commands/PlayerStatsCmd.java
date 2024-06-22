package droidco.west3.ironsight.bandit.Commands;

import droidco.west3.ironsight.bandit.Bandit;
import droidco.west3.ironsight.globals.Utils.BanditUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerStatsCmd implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        //Args.length 0 :: User only typed /COMMAND ...(nothing)...
        //Ars.length > 0 :: User typed the main command "/Command" with additional arguments:
        // "/command something something" would be args.length == 2

        //First check the command is being executed by a player and not something else:
        if (commandSender instanceof Player p) {
            Bandit b = Bandit.getPlayer(p);
            BanditUtils.displayBasicStats(b, p);
        }


        return false;
    }
}
