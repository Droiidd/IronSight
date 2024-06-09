package droidco.west3.ironsight.Contracts.Utils;

import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import org.bukkit.ChatColor;

import java.util.List;

public class ContractUtils
{
    public static String getDifficultyScale(Difficulty difficulty){
        switch(difficulty){
            case ROOKIE -> {
                return String.valueOf(ChatColor.GREEN) +"Difficulty: I";
            }
            case APPRENTICE -> {
                return String.valueOf(ChatColor.YELLOW) +"Difficulty: II";
            }
            case EXPERIENCED -> {
                return String.valueOf(ChatColor.RED) +"Difficulty: III";
            }
            case MASTER -> {
                return String.valueOf(ChatColor.DARK_RED) +"Difficulty: IV";
            }
        }
        return "";
    }
    public static String getTypeString(ContractType type){
        switch(type){
            case OIL_FIELD -> {
                return "Oil Field Raid";
            }
            case BOUNTY -> {
                return "Bounty Hunter";
            }
            case DELIVERY -> {
                return "DELIVERY";
            }
        }
        return "";
    }

    public static Contract getSingleContract(List<Contract> contracts){
        int odds = GlobalUtils.getRandomNumber(contracts.size());
        return contracts.get(odds);
    }
}

