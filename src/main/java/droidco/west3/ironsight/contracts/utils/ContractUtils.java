package droidco.west3.ironsight.contracts.utils;

import droidco.west3.ironsight.contracts.Contract;
import droidco.west3.ironsight.globals.utils.GlobalUtils;
import java.util.List;
import org.bukkit.ChatColor;

public class ContractUtils {
  public static String getDifficultyScale(Difficulty difficulty) {
    switch (difficulty) {
      case Rookie -> {
        return ChatColor.GREEN + "Difficulty: I";
      }
      case Apprentice -> {
        return ChatColor.YELLOW + "Difficulty: II";
      }
      case Experienced -> {
        return ChatColor.RED + "Difficulty: III";
      }
      case Master -> {
        return ChatColor.DARK_RED + "Difficulty: IV";
      }
    }
    return "";
  }

  public static String getTypeString(ContractType type) {
    switch (type) {
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

  public static Contract getSingleContract(List<Contract> contracts) {
    int odds = GlobalUtils.getRandomNumber(contracts.size());
    return contracts.get(odds);
  }
}
