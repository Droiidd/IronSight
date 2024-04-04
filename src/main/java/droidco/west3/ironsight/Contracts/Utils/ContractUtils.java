package droidco.west3.ironsight.Contracts.Utils;

import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContractUtils
{
    public static String getDifficultyScale(Difficulty difficulty){
        switch(difficulty){
            case Rookie -> {
                return String.valueOf(ChatColor.GREEN) +"Difficulty: I";
            }
            case Apprentice -> {
                return String.valueOf(ChatColor.YELLOW) +"Difficulty: II";
            }
            case Experienced -> {
                return String.valueOf(ChatColor.RED) +"Difficulty: III";
            }
            case Master -> {
                return String.valueOf(ChatColor.DARK_RED) +"Difficulty: IV";
            }
        }
        return "";
    }
    public static String getTypeString(ContractType type){
        switch(type){
            case OilField -> {
                return "Oil Field Raid";
            }
            case Bounty -> {
                return "Bounty Hunter";
            }
            case Delivery -> {
                return "Delivery";
            }
        }
        return "";
    }

    public static Contract getSingleContract(List<Contract> contracts){
        int odds = GlobalUtils.getRandomNumber(contracts.size());
        return contracts.get(odds);
    }
}

