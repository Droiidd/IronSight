package droidco.west3.ironsight.Items;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.Contracts.ContractUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MasterListCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p){
            Bandit b = Bandit.getPlayer(p);
            if(strings.length == 0){
                p.openInventory(MasterItemListUI.openMasterListPage1(p));
            }

        }
        return true;

    }
}
