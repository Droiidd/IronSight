package droidco.west3.ironsight.Bandit.Officer;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.Contracts.UI.ActiveContractUI;
import droidco.west3.ironsight.Contracts.UI.ContractUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OfficerEvents {

    public static void wantedDeath(Bandit killer, Bandit killed){
        if (killer.isOfficer() && killed.isWanted()){
            killer.incrWantedKills();
        }
    }
}
