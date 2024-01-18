package droidco.west3.ironsight.Commands;

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
            //If they type the command with no arguments, maybe they forgot something, so show them options:
            if(strings.length == 0){
                p.sendMessage("Iron Sight Statistic Cmd Options:");
                p.sendMessage("> Show your own: /is stats");
                p.sendMessage("> View another players: /is stats playerName");
            }
            //There are arguments, let's see if they want statistics
            if(strings[0].equalsIgnoreCase("stats")){
                //IronPlayer iP = IronPlayer.getPlayer(p.g);
                //Display the users statistics
                //PlayerUtils.displayBasicStats();
            }




        }




        return false;
    }
}
