package droidco.west3.ironsight.Objects.Contracts.Utils;

import droidco.west3.ironsight.Objects.Contracts.Contract;
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
}
