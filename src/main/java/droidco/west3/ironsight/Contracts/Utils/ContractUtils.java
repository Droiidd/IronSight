package droidco.west3.ironsight.Contracts.Utils;

import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
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
                return "I";
            }
            case Apprentice -> {
                return "II";
            }
            case Experienced -> {
                return "III";
            }
            case Master -> {
                return "IV";
            }
        }
        return "";
    }
    public static List<Contract> getContractByDiff(Difficulty difficulty){
        HashMap<String, Contract> contracts = Contract.getContracts();
        List<Contract> targeted = new ArrayList<>();
        contracts.forEach((key, contract) -> {
            if(contract.getDifficulty().compareTo(difficulty) == 0){
                targeted.add(contract);
            }
        });
        System.out.println("target:"+targeted);
        if(targeted.isEmpty()){
            return new ArrayList<>();
        }else{
            return targeted;
        }
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
