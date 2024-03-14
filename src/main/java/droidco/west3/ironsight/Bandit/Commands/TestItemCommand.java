package droidco.west3.ironsight.Bandit.Commands;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.Items.ItemTable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TestItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p) {
            p.getInventory().addItem(new ItemStack(ItemTable.getItem("Common").getMaterial()));
        }
        return false;
    }


}
