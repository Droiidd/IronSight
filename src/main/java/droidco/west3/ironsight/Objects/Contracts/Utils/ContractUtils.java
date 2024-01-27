package droidco.west3.ironsight.Objects.Contracts.Utils;

import droidco.west3.ironsight.Objects.Contracts.Contract;
import droidco.west3.ironsight.Objects.Player.IronPlayer;
import droidco.west3.ironsight.Utils.GlobalUtils;

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
    public static int getDifficultyReward(Difficulty difficulty){
        switch(difficulty){
            case Rookie -> {
                int rewardLow = 750;
                int rewardHigh = 1500;
                return GlobalUtils.getRandomRange(rewardLow,rewardHigh);
            }
            case Apprentice -> {
                int rewardLow = 1750;
                int rewardHigh = 2500;
                return GlobalUtils.getRandomRange(rewardLow,rewardHigh);
            }
            case Experienced -> {
                int rewardLow = 2750;
                int rewardHigh = 4500;
                return GlobalUtils.getRandomRange(rewardLow,rewardHigh);
            }
            case Master -> {
                int rewardLow = 5550;
                int rewardHigh = 8500;
                return GlobalUtils.getRandomRange(rewardLow,rewardHigh);
            }

        }
        return 0;
    }
    public static String getTypeString(ContractType type){
        switch(type){
            case DrugRunner -> {
                return "Drug Runner";
            }
            case HeadHunter -> {
                return "Head Hunter";
            }
            case Miner -> {
                return "Miner";
            }
        }
        return "";
    }
    public static void initializeContracts(IronPlayer p){
        //Goes through every contract and makes a list of rookie specific

        List<Contract> rookieContracts = ContractUtils.getContractByDiff(Difficulty.Rookie);
        List<Contract> apprenticeContracts = ContractUtils.getContractByDiff(Difficulty.Apprentice);
        List<Contract> experiencedContracts = ContractUtils.getContractByDiff(Difficulty.Experienced);
        List<Contract> masterContracts = ContractUtils.getContractByDiff(Difficulty.Master);

        //NOW WE CREATE THE ITEM THAT REPRESENTS THE CONTRACT IN THE MENU
        //Choosing random contract type for each difficulty slot (Rook, Appre, Exper)
        Contract rook = getSingleContract(rookieContracts);
        Contract aprnt = getSingleContract(apprenticeContracts);
        Contract exprn = getSingleContract(experiencedContracts);
        Contract master = getSingleContract(masterContracts);
        //Setting the actual slots with the contract
        p.setRookieContract(rook);
        p.setApprenticeContract(aprnt);
        /*
        Now for the Experienced slot, there's a small chance you can get a master quest
        for more money and experience.
         */
          p.setExperiencedContract(master);
//        int odds = GlobalUtils.getRandomNumber(101);
//        if(odds<10){
//            p.setExperiencedContract(master);
//        }else{
//            p.setExperiencedContract(exprn);
//        }
    }
    public static Contract getSingleContract(List<Contract> contracts){
        int odds = GlobalUtils.getRandomNumber(contracts.size());
        return contracts.get(odds);
    }
}
