package droidco.west3.ironsight.Commands;

import droidco.west3.ironsight.Player.IronPlayer;
import droidco.west3.ironsight.Utils.GlobalUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static droidco.west3.ironsight.Utils.GlobalUtils.checkStrToDErrMsg;

public class AdminCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p){
            if(strings.length == 0){
                //Player entered commands with no args
                //show options
                p.sendMessage("Iron Sight Command options:");
                p.sendMessage("/is gold add *amount* -> adds funds to players bank");

            }
            else{
                if(strings[0].equalsIgnoreCase("gold")){
                    if(strings.length == 3){
                        switch(strings[1]){
                            case "add":
                                IronPlayer iPlayer =  IronPlayer.getPlayer(p);
                                double amount = GlobalUtils.checkStrToDErrMsg(strings[2], p);
                                iPlayer.updateBank(amount);
                                p.sendMessage("Gold added to account.");
                                break;
                            case "remove":

                                break;
                        }
                    }

                }
            }



        }



        return false;
    }


}
