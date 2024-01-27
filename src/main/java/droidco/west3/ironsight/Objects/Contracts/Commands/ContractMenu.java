package droidco.west3.ironsight.Objects.Contracts.Commands;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Objects.Contracts.Contract;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContractMenu implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p){
            IronPlayer iPlayer = IronPlayer.getPlayer(p);
            if(strings.length == 0){
                p.openInventory(getContractUi(p));
            }else if(strings[0].equalsIgnoreCase("reset")){
                ContractUtils.initializeContracts(iPlayer);
            }


//            p.sendMessage(iPlayer.getApprenticeContract().getContractName());
//            p.sendMessage(iPlayer.getExperiencedContract().getContractName());
//            p.sendMessage(iPlayer.getRookieContract().getContractName());
        }
        return true;
    }
    public Inventory getContractUi(Player p){
        Inventory contractUi = Bukkit.createInventory(p, 27, ChatColor.BLUE + "Contracts");
        IronPlayer iPlayer = IronPlayer.getPlayer(p);

        contractUi.setItem(11, getContractSlot(iPlayer.getRookieContract()));
        contractUi.setItem(13, getContractSlot(iPlayer.getApprenticeContract()));
        contractUi.setItem(15, getContractSlot(iPlayer.getExperiencedContract()));
        return contractUi;
    }
    public ItemStack getContractSlot(Contract selected){

        //Basic item set up

        //LORE STRUCTURE FOR CONTRACTS IS ALWAYS:
        /*
        NAME
        ---
        Location
        Reward
        TargetName / Requested Goods (If applicable)
         */
        ItemStack contract = new ItemStack(Material.BOOK);
        ItemMeta contractMeta = contract.getItemMeta();
        ArrayList<String> contractLore = new ArrayList<>();
        //Setting up the strings for the lore
        //This string is the name of the selected contract + the difficulty rating
        String listingName = selected.getListingName();
        //LORE
        contractMeta.setDisplayName(listingName);
        contractLore.add(ChatColor.GRAY+selected.getLocation().getLocName());
        contractLore.add(ChatColor.GRAY +""+selected.getReward()+" g");
        //This displays the contracts type
        contractLore.add(ChatColor.GRAY+ContractUtils.getTypeString(selected.getType()));

        contractMeta.setLore(contractLore);
        contract.setItemMeta(contractMeta);
        return contract;
    }


}
