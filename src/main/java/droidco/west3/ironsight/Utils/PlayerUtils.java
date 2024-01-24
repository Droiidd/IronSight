package droidco.west3.ironsight.Utils;

import droidco.west3.ironsight.Player.IronPlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerUtils
{
    public static void displayBasicStats(IronPlayer iP, Player p)
    {
        p.sendMessage("Iron Sight Player Stats:");
        p.sendMessage("============");
        p.sendMessage("Bleeding: "+iP.isBleeding());
        p.sendMessage("Broken Legs: "+iP.isBrokenLegs());
        p.sendMessage("Bounty: "+iP.getBounty());
        p.sendMessage("Bank: "+iP.getBank());
        p.sendMessage("Wallet: "+iP.getWallet());
        p.sendMessage("EXPERIENCE INFO: ");
        p.sendMessage("Combat Level: "+iP.getCmbtContractLvl());
        p.sendMessage("Peacemaker Level: "+iP.getPceContractLvl());
        p.sendMessage("Combat XP: "+iP.getCmbtContractXp());
        p.sendMessage("Peacemaker XP: "+iP.getPceContractXp());
        p.sendMessage("Wanted Kills: "+iP.getWantedKills());

    }

}

