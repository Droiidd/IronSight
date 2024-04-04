package droidco.west3.ironsight.Contracts.UI;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.Globals.Utils.BanditUtils;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
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
            if(e.getCurrentItem().getType() != null){
                Contract selected = null;
                if(e.getCurrentItem().getType().equals(b.getRookieContract().getContractIcon().getType())){
                    selected = b.getRookieContract();
                    setActiveContract(b,p,selected);
                }else if(e.getCurrentItem().getType().equals(b.getApprenticeContract().getContractIcon().getType())){
                    selected = b.getApprenticeContract();
                    setActiveContract(b,p,selected);
                } else if(e.getCurrentItem().getType().equals(b.getExperiencedContract().getContractIcon().getType())){
                    selected = b.getExperiencedContract();
                    setActiveContract(b,p,selected);
                }
                switch(e.getCurrentItem().getType()){

                    case SPRUCE_HANGING_SIGN -> {
                        //They want to change their Contractor Title
                        p.openInventory(ContractUI.openContractorTitleSelectUi(p));
                    }
                    case EMERALD_BLOCK -> {
                        p.closeInventory();
                        if(b.isDoingContract()){
                            Contract active = b.getActiveContract();
                            switch (active.getContractType()){
                                case Delivery -> {
                                    if(p.getInventory().containsAtLeast(active.getRequestedItem(),active.getRequestedAmount())){
                                        for(int i =0;i<active.getRequestedAmount();i++){
                                            p.getInventory().removeItem(active.getRequestedItem());
                                        }
                                        p.sendTitle(ChatColor.GREEN + String.valueOf(ChatColor.BOLD) + "Contract Complete!", ChatColor.GRAY + "+"+active.getReward()+"g   +"+active.getRewardXp()+ChatColor.AQUA+ " XP");
                                        b.updateContractorXp(active.getRewardXp());
                                        checkLevelUp(p,b);
                                        Contract.assignPlayerContracts(p,b);
                                        b.setDoingContract(false);
                                        b.setActiveContract(null);
                                    }else{
                                        p.sendMessage(ChatColor.RED+ "You need more "+active.getRequestedItem().getItemMeta().getDisplayName());
                                    }
                                }
                            }

                        }else{
                            p.sendMessage(ChatColor.RED+"Select a contract first!");
                        }
                    }
                    case COMPASS -> {
                        //They want to view their active contract information
                        Contract active = b.getActiveContract();
                        if(active == null){
                            p.closeInventory();
                            p.sendMessage(ChatColor.RED+"No active contract!");
                        }
                        else {
                            //p.sendMessage(active.getContractName());
                            p.openInventory(ActiveContractUI.openActiveContractUi(p,active));
                        }
                    }
                    //Case Skull:
                    //Can view what you get from leveling up??
                }
            }
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
            e.setCancelled(true);
            //In the contract UI menu
            //Find what they clicked on
            if(e.getCurrentItem().getType() != null){
                switch(e.getCurrentItem().getType()) {
                    case STONE_PICKAXE -> {
                        updateContractorTitle(3,p,b,1);
                    }
                    case HAY_BLOCK -> {
                        updateContractorTitle(4,p,b,2);
                    }
                    case LEATHER_BOOTS -> {
                        updateContractorTitle(5,p,b,3);
                    }
                    case PAPER -> {
                        updateContractorTitle(7,p,b,4);
                    }
                    case SKELETON_SKULL -> {
                        updateContractorTitle(9,p,b,5);
                    }
                    case SPYGLASS -> {
                        updateContractorTitle(10,p,b,6);
                    }
                }
            }
        }
    }
    public void setActiveContract(Bandit b, Player p, Contract selected){
        if(!b.isDoingContract()){
            //Check what slot they chose.
            if(selected != null){
                b.setActiveContract(selected);
                b.setDoingContract(true);
                p.closeInventory();
                b.getActiveContract().startContract(p);
                p.sendMessage("Contract selected. View your contract by typing \"/contract active\"");
            }
        }else{
            //They are doing a contract!
            p.closeInventory();
            p.sendMessage(ChatColor.RED+ "Already doing a contract!");
        }
    }

    public void updateContractorTitle(int requiredLevel,Player p,Bandit b, int title)
    {
        if(b.getContractorLvl() >= requiredLevel){
            b.setContractorTitle(title);
        }
        else{
            p.closeInventory();
            p.sendMessage(ChatColor.RED+ "Not a high enough level!");
        }
        p.closeInventory();
        p.sendMessage(BanditUtils.getContractorTitle(b)+ ChatColor.GRAY+" title selected.");
    }
    public void checkLevelUp(Player p, Bandit b){
        if(b.getContractorXp() >= 10 && b.getContractorXp() < 20){
            levelUpContractor(p,b,1);
        }else if(b.getContractorXp() >= 20 && b.getContractorXp() < 30){
            levelUpContractor(p,b,2);

        }else if(b.getContractorXp() >= 30 && b.getContractorXp() < 40){
            levelUpContractor(p,b,3);
        }
        else if(b.getContractorXp() >= 40 && b.getContractorXp() < 50){

            levelUpContractor(p,b,4);
        }
        else if(b.getContractorXp() >= 50 && b.getContractorXp() < 60){

            levelUpContractor(p,b,5);
        }
        else if(b.getContractorXp() >= 60 && b.getContractorXp() < 70){
            levelUpContractor(p,b,6);
        }
        else if(b.getContractorXp() >= 70 && b.getContractorXp() < 80){
            levelUpContractor(p,b,7);
        }
        else if(b.getContractorXp() >= 80 && b.getContractorXp() < 90){
            levelUpContractor(p,b,8);
        }
        else if(b.getContractorXp() >= 90 && b.getContractorXp() < 100){
            levelUpContractor(p,b,9);
        }
    }
    public void levelUpContractor(Player p,Bandit b, int level){
        if(b.getContractorLvl() != level){
            b.setContractorLvl(level);
            p.sendMessage(ChatColor.GRAY+"You have reached "+ ChatColor.GREEN+"Contractor Level: "+level);
            p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE,1,1);
            p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST,1,1);
            GlobalUtils.displayParticles(p.getLocation(), Particle.GLOW_SQUID_INK, Particle.VILLAGER_HAPPY, 10);
        }

    }
}
