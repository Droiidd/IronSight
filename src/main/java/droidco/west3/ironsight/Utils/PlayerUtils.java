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
        p.sendMessage("Broken Legs: "+iP.isBleeding());
        p.sendMessage("Bounty: "+iP.isBleeding());
        p.sendMessage("Bank: "+iP.isBleeding());
        p.sendMessage("Wallet: "+iP.isBleeding());
        p.sendMessage("EXPERIENCE INFO: ");
        p.sendMessage("Combat Level: "+iP.isBleeding());
        p.sendMessage("Peacemaker Level: "+iP.isBleeding());
        p.sendMessage("Combat XP: "+iP.isBleeding());
        p.sendMessage("Peacemaker XP: "+iP.isBleeding());
        p.sendMessage("Wanted Kills: "+iP.isBleeding());

    }
    public Player getIronPlayer(){
        List<IronPlayer> players = IronPlayer.getPlayerList();
        for(IronPlayer player : players){
            if()
        }

}

