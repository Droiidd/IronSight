package droidco.west3.ironsight.Objects.Contracts.Commands;

import droidco.west3.ironsight.Objects.Contracts.Contract;
import droidco.west3.ironsight.Objects.Contracts.Utils.ContractUtils;
import droidco.west3.ironsight.Objects.Contracts.Utils.Difficulty;
import droidco.west3.ironsight.Objects.Location.Location;
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
            p.openInventory(getContractUi(p));
        }
        return true;
    }
    public Inventory getContractUi(Player p){
        Inventory contractUi = Bukkit.createInventory(p, 27, ChatColor.BLUE + "Contracts");

        contractUi.setItem(11, getContractSlot(Difficulty.Rookie));
        contractUi.setItem(13, getContractSlot(Difficulty.Apprentice));
        contractUi.setItem(15, getContractSlot(Difficulty.Master));
        return contractUi;
    }
    public ItemStack getContractSlot(Difficulty difficulty){
        //Goes through every contract and makes a list of rookie specific
        List<Contract> contracts = ContractUtils.getContractByDiff(difficulty);

        //NOW WE CREATE THE ITEM THAT REPRESENTS THE CONTRACT IN THE MENU
        //Choosing random rookie contract
        int odds = GlobalUtils.getRandomNumber(contracts.size());
        Contract selected = contracts.get(odds);
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
        String listingName = ChatColor.WHITE+selected.getContractName()+" - "+ ContractUtils.getDifficultyScale(selected.getDifficulty());
        Location location = selected.getRandomLocation();
        int reward = ContractUtils.getDifficultyReward(difficulty);
        //LORE
        contractMeta.setDisplayName(ChatColor.WHITE + listingName);
        contractLore.add(ChatColor.GRAY+location.getLocName());
        contractLore.add(ChatColor.GRAY +""+reward+" g");
        //This displays the contracts type
        contractLore.add(ContractUtils.getTypeString(selected.getType()));

        contractMeta.setLore(contractLore);
        contract.setItemMeta(contractMeta);
        return contract;
    }


}
