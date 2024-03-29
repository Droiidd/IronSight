package droidco.west3.ironsight.Contracts;

import droidco.west3.ironsight.Contracts.Utils.ContractUtils;
import droidco.west3.ironsight.Bandit.Bandit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ContractMenuCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p){
            Bandit b = Bandit.getPlayer(p);
            if(strings.length == 0){
                p.openInventory(ContractUI.openContractUi(p));
            }else if(strings[0].equalsIgnoreCase("reset")){
                Contract.assignPlayerContracts(p,b);
            }else if(strings[0].equalsIgnoreCase("active")){
                if(b.getActiveContract() == null){
                    p.sendMessage("You do not have an active contract.");
                }else{
                    p.openInventory(ContractUI.openContractUi(p));
                }
            }


//            p.sendMessage(iPlayer.getApprenticeContract().getContractName());
//            p.sendMessage(iPlayer.getExperiencedContract().getContractName());
//            p.sendMessage(iPlayer.getRookieContract().getContractName());
        }
        return true;
    }




}
