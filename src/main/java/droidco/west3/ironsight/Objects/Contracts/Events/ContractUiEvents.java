package droidco.west3.ironsight.Objects.Contracts.Events;

import droidco.west3.ironsight.Objects.Contracts.Contract;
import droidco.west3.ironsight.Objects.Contracts.Utils.ContractUI;
import droidco.west3.ironsight.Objects.Player.IronPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.net.http.WebSocket;

public class ContractUiEvents implements Listener {
//Nav for navigation :#
    @EventHandler
    public void navContractMenu(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.GRAY + "Available Contracts:")){
            IronPlayer iPlayer = IronPlayer.getPlayer(p);
            //In the contract UI menu
            //Find what they clicked on
            switch(e.getCurrentItem().getType()){
                case BOOK -> {
                    //They selected a contract
                    //Check what slot they chose.
                    switch(e.getCurrentItem().getItemMeta().getDisplayName()){
                        case "Rookie Contract" -> {
                            iPlayer.setActiveContract(iPlayer.getRookieContract());
                        }
                        case "Apprentice Contract" -> {
                            iPlayer.setActiveContract(iPlayer.getApprenticeContract());
                        }
                        case "Experienced Contract" -> {
                            iPlayer.setActiveContract(iPlayer.getExperiencedContract());
                        }
                    }
                    p.closeInventory();
                    p.sendMessage("Contract selected. View your contract by typing \"/contract active\" or \"/c a");
                }
                case OAK_SIGN -> {
                    //They want to change their Contractor Title
                }
                case COMPASS -> {
                    //They want to view their active contract information
                    Contract active = iPlayer.getActiveContract();
                    if(active == null){
                        p.closeInventory();
                        p.sendMessage("No active contract!");
                    }
                    else {
                        p.openInventory(ContractUI.getActiveContractUi(p));
                    }
                }
                //Case Skull:
                //Can view what you get from leveling up??
            }
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void navActiveContractMenu(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.GRAY + "Active Contract info:")){
            IronPlayer iPlayer = IronPlayer.getPlayer(p);
            //In the contract UI menu
            //Find what they clicked on
            switch(e.getCurrentItem().getType()){
                case BARRIER -> {
                    //DEACTIVATE CONTRACT
                    p.closeInventory();
                    p.sendMessage("Resigned current contract.");
                    iPlayer.setActiveContract(null);
                }
            }
            e.setCancelled(true);
        }
    }
}
