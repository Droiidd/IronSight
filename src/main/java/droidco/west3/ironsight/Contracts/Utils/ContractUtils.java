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
        return targeted;
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
    public static void initializeContracts(Bandit p){
        //Goes through every contract and makes a list of rookie specific



        //NOW WE CREATE THE ITEM THAT REPRESENTS THE CONTRACT IN THE MENU
        //Choosing random contract type for each difficulty slot (Rook, Appre, Exper)
        Contract rook = rookieContracts.size() > 0 ? getSingleContract(rookieContracts) : null;
        Contract aprnt = apprenticeContracts.size() > 0 ? getSingleContract(apprenticeContracts) : null;
        Contract exprn = experiencedContracts.size() > 0 ? getSingleContract(experiencedContracts) : null;
        Contract master = masterContracts.size() > 0 ? getSingleContract(masterContracts) : null;
        //Setting the actual slots with the contract
        p.setRookieContract(rook);
        p.setApprenticeContract(aprnt);
        /*
        Now for the Experienced slot, there's a small chance you can get a master quest
        for more money and experience.
         */
        int odds = GlobalUtils.getRandomNumber(101);
        if(odds<10){
            p.setExperiencedContract(master);

        }else{
            p.setExperiencedContract(exprn);
        }
        //Generate the contracts

//        p.getRookieContract();
//        p.getApprenticeContract();
//        p.getExperiencedContract();
    }
    public static Contract getSingleContract(List<Contract> contracts){
        int odds = GlobalUtils.getRandomNumber(contracts.size());
        return contracts.get(odds);
    }
}
