package droidco.west3.ironsight.contracts;

import droidco.west3.ironsight.bandit.Bandit;
import droidco.west3.ironsight.contracts.ui.ContractUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ContractMenuCmd implements CommandExecutor {
  @Override
  public boolean onCommand(
      CommandSender commandSender, Command command, String s, String[] strings) {
    if (commandSender instanceof Player p) {
      Bandit b = Bandit.getPlayer(p);
      if (strings.length == 0) {
        p.openInventory(ContractUI.openContractorInfo(p));
      } else if (strings[0].equalsIgnoreCase("reset")) {
        Contract.assignPlayerContracts(p, b);
      } else if (strings[0].equalsIgnoreCase("menu")) {
        p.openInventory(ContractUI.openContractorInfo(p));
      }
    }
    return true;
  }
}
