package droidco.west3.ironsight.Bandit.Commands;

import droidco.west3.ironsight.Items.CustomItem;
import droidco.west3.ironsight.Items.ItemTable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p) {
            if (strings.length > 0) {
                String itemCode = strings[0];
                System.out.println(itemCode);
                int amount = 1;
                if (strings.length > 1) {
                    amount = Integer.parseInt(strings[1]);
                }
                CustomItem customItem = CustomItem.getCustomItem(itemCode);
                if (customItem != null) {
                    p.getInventory().addItem(customItem.getItemStack(amount));
                }
            }

        }
        return false;
    }


}

