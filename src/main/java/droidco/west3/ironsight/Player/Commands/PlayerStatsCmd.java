package droidco.west3.ironsight.Player.Commands;

import droidco.west3.ironsight.Player.IronPlayer;
import droidco.west3.ironsight.Utils.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerStatsCmd implements CommandExecutor
{


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        //Args.length 0 :: User only typed /COMMAND ...(nothing)...
        //Ars.length > 0 :: User typed the main command "/Command" with additional arguments:
        // "/command something something" would be args.length == 2

        //First check the command is being executed by a player and not something else:
        if(commandSender instanceof Player p){
                IronPlayer iPlayer = IronPlayer.getPlayer(p);
                PlayerUtils.displayBasicStats(iPlayer,p);
        }




        return false;
    }
}
