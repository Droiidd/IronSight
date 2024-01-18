package droidco.west3.ironsight.Commands;

import droidco.west3.ironsight.Player.IronPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class AdminCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p){
            if(strings.length == 0){
                //Player entered commands with no args
                //show options
            }
            else{
                if(strings[0].equalsIgnoreCase("gold")){
                    switch(strings[1]){
                        case "add":
                            IronPlayer.getPlayer(p);

                            break;
                        case "remove":

                            break;
                    }
                }
            }



        }



        return false;
    }
}
