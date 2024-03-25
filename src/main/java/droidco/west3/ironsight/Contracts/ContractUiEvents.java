package droidco.west3.ironsight.Contracts;

import droidco.west3.ironsight.Bandit.Bandit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ContractUiEvents implements Listener {
//Nav for navigation :#
    @EventHandler
    public void navContractMenu(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Available Contracts: (Click to start!)")){
            Bandit b = Bandit.getPlayer(p);
            e.setCancelled(true);
            //In the contract UI menu
            //Find what they clicked on
            switch(e.getCurrentItem().getType()){
                case BOOK -> {
                    //They selected are selecting a contract.
                    //Check if they are doing one already
                    p.sendMessage("CLICKED BOOK");
                    if(!b.isDoingContract()){
                        p.sendMessage("P{ NOT WORKING");
                        //Check what slot they chose.
                        System.out.println(e.getCurrentItem().getItemMeta().getDisplayName());
                        String name = e.getCurrentItem().getItemMeta().getDisplayName();
                        System.out.println(ChatColor.stripColor(name));
                        if(ChatColor.stripColor(name).equalsIgnoreCase("Rookie Contract")){
                            p.sendMessage("Rookie");
                            b.setActiveContract(b.getRookieContract());
                            b.setDoingContract(true);
                            p.closeInventory();
                            p.sendMessage("Contract selected. View your contract by typing \"/contract active\" or \"/c a");
                        } else if(ChatColor.stripColor(name).equalsIgnoreCase("Apprentice Contract")){
                            p.sendMessage("Apprentice");
                            b.setActiveContract(b.getApprenticeContract());
                            b.setDoingContract(true);
                            p.closeInventory();
                            p.sendMessage("Contract selected. View your contract by typing \"/contract active\" or \"/c a");
                        }
                        else if(ChatColor.stripColor(name).equalsIgnoreCase("Experienced Contract")){
                            p.sendMessage("Exper");
                            b.setActiveContract(b.getExperiencedContract());
                            b.setDoingContract(true);
                            p.closeInventory();
                            p.sendMessage("Contract selected. View your contract by typing \"/contract active\" or \"/c a");
                        }

                    }else{
                        //They are doing a contract!
                        p.closeInventory();
                        p.sendMessage("Already doing a contract!");
                    }

                }
                case SPRUCE_HANGING_SIGN -> {
                    //They want to change their Contractor Title
                    p.openInventory(ContractUI.openContractorTitleSelectUi(p));
                }
                case COMPASS -> {
                    //They want to view their active contract information
                    Contract active = b.getActiveContract();
                    if(active == null){
                        p.closeInventory();
                        p.sendMessage("No active contract!");
                    }
                    else {
                        p.openInventory(OpenContractUI.openActiveContractUi(p,active));
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
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Active Contract info:")){
            Bandit b = Bandit.getPlayer(p);
            //In the contract UI menu
            //Find what they clicked on
            switch(e.getCurrentItem().getType()){
                case BARRIER -> {
                    //DEACTIVATE CONTRACT
                    p.closeInventory();
                    p.sendMessage("Resigned current contract.");
                    b.setActiveContract(null);
                    b.setDoingContract(false);
                }
            }
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void navContractorTitleSelectMenu(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Contractor Title Select:")){
            Bandit b = Bandit.getPlayer(p);
            //In the contract UI menu
            //Find what they clicked on
            switch(e.getCurrentItem().getType()) {
                case HAY_BLOCK -> {
                    if(b.getCmbtContractLvl() >= 3){
                        b.setContractorTitle(1);
                    }
                    else{
                        p.closeInventory();
                        p.sendMessage(ChatColor.RED+ "Not a high enough level!");
                    }
                }
                case LEATHER_BOOTS -> {
                    if(b.getCmbtContractLvl() >= 6){
                        b.setContractorTitle(2);
                    }
                    else{
                        p.closeInventory();
                        p.sendMessage(ChatColor.RED+ "Not a high enough level!");
                    }
                }
                case SKELETON_SKULL -> {
                    if(b.getCmbtContractLvl() >= 9){
                        b.setContractorTitle(3);
                    }
                    else{
                        p.closeInventory();
                        p.sendMessage(ChatColor.RED+ "Not a high enough level!");
                    }
                }
                case STONE_PICKAXE -> {
                    if(b.getCmbtContractLvl() >= 4){
                        b.setContractorTitle(4);
                    }
                    else{
                        p.closeInventory();
                        p.sendMessage(ChatColor.RED+ "Not a high enough level!");
                    }
                }
                case PAPER -> {
                    if(b.getCmbtContractLvl() >= 6){
                        b.setContractorTitle(5);
                    }
                    else{
                        p.closeInventory();
                        p.sendMessage(ChatColor.RED+ "Not a high enough level!");
                    }
                }
                case SPYGLASS -> {
                    if(b.getCmbtContractLvl() >= 10){
                        b.setContractorTitle(6);
                    }
                    else{
                        p.closeInventory();
                        p.sendMessage(ChatColor.RED+ "Not a high enough level!");
                    }
                }

            }
            e.setCancelled(true);
        }
    }
}
