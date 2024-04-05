package droidco.west3.ironsight.Horse;

import droidco.west3.ironsight.Bandit.Bandit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CallHorseCommand implements CommandExecutor{
@Override
public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
    if (commandSender instanceof Player p) {
        Bandit b = Bandit.getPlayer(p);
        if (strings.length != 0) {
            //Player entered commands with no args
            //show options
            p.sendMessage("To summon horse:");
            p.sendMessage("/call");
        } else {
            p.openInventory(CallHorseUI.callHorseGui(p));
        }

    }
    return true;
}
}
