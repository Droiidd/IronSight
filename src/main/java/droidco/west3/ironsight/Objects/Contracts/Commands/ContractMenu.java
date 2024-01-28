package droidco.west3.ironsight.Objects.Contracts.Commands;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Objects.Contracts.Contract;
import droidco.west3.ironsight.Objects.Contracts.Utils.ContractUI;
import droidco.west3.ironsight.Objects.Contracts.Utils.ContractUtils;
import droidco.west3.ironsight.Objects.Contracts.Utils.Difficulty;
import droidco.west3.ironsight.Objects.Location.Location;
import droidco.west3.ironsight.Objects.Player.IronPlayer;
import droidco.west3.ironsight.Utils.GlobalUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ContractMenu implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p){
            IronPlayer iPlayer = IronPlayer.getPlayer(p);
            if(strings.length == 0){
                p.openInventory(ContractUI.getContractUi(p));
            }else if(strings[0].equalsIgnoreCase("reset")){
                ContractUtils.initializeContracts(iPlayer);
            }


//            p.sendMessage(iPlayer.getApprenticeContract().getContractName());
//            p.sendMessage(iPlayer.getExperiencedContract().getContractName());
//            p.sendMessage(iPlayer.getRookieContract().getContractName());
        }
        return true;
    }




}
