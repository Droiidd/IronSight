package droidco.west3.ironsight.contracts.ui;

import droidco.west3.ironsight.bandit.Bandit;
import droidco.west3.ironsight.contracts.Contract;
import droidco.west3.ironsight.globals.utils.BanditUtils;
import droidco.west3.ironsight.globals.utils.GlobalUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public class ContractUiEvents implements Listener {
    //Nav for navigation :#
    @EventHandler
    public void navContractMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Select a Contract: (Click to start!)")) {
            Bandit b = Bandit.getPlayer(p);
            e.setCancelled(true);
            //In the contract UI menu
            //Find what they clicked on
            if (e.getCurrentItem() != null) {
                Contract selected = null;
                if (e.getCurrentItem().getType().equals(b.getRookieContract().getContractIcon().getType())) {
                    selected = b.getRookieContract();
                    setActiveContract(b, p, selected);
                } else if (e.getCurrentItem().getType().equals(b.getApprenticeContract().getContractIcon().getType())) {
                    selected = b.getApprenticeContract();
                    setActiveContract(b, p, selected);
                } else if (e.getCurrentItem().getType().equals(b.getExperiencedContract().getContractIcon().getType())) {
                    selected = b.getExperiencedContract();
                    setActiveContract(b, p, selected);
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Contractor Info:")) {
            Bandit b = Bandit.getPlayer(p);
            e.setCancelled(true);
            //In the contract UI menu
            //Find what they clicked on
            if (e.getCurrentItem() != null) {
                switch (e.getCurrentItem().getType()) {
                    case EMERALD_BLOCK -> {
                        p.closeInventory();
                        p.sendMessage(b.isDoingContract() + "");
                        if (b.isDoingContract()) {
                            Contract active = b.getActiveContract();
                            double goldBonus = 1.0;
                            switch (b.getContractorLvl()) {
                                case 2 -> {
                                    goldBonus = 1.05;
                                }
                                case 5 -> {
                                    goldBonus = 1.15;
                                }
                                case 9 -> {
                                    goldBonus = 1.25;
                                }
                            }
                            b.getActiveContract().setReward(b.getActiveContract().getReward() * goldBonus);
                            switch (active.getContractType()) {
                                case DELIVERY -> {
                                    if (p.getInventory().containsAtLeast(active.getRequestedItem(), active.getRequestedAmount())) {
                                        for (int i = 0; i < active.getRequestedAmount(); i++) {
                                            p.getInventory().removeItem(active.getRequestedItem());
                                        }
                                        p.sendMessage(ChatColor.RED + "[Alert] " + ChatColor.GRAY + "Contract complete! +" + active.getRewardXp() + ChatColor.AQUA + " XP" + ChatColor.GRAY + "+ " + active.getReward() + "g");

                                        p.sendTitle(ChatColor.GREEN + String.valueOf(ChatColor.BOLD) + "Contract Complete!", ChatColor.GRAY + "+" + active.getReward() + "g   +" + active.getRewardXp() + ChatColor.AQUA + " XP");
                                        switch (b.getContractorLvl()) {
                                            case 2 -> {
                                                p.sendMessage(ChatColor.GRAY+"Level ["+BanditUtils.getContractorLvlColor(b.getContractorLvl())+ChatColor.GRAY+"] bonus applied: "+ChatColor.WHITE+"+5% gold");
                                            }
                                            case 5 -> {
                                                p.sendMessage(ChatColor.GRAY+"Level ["+BanditUtils.getContractorLvlColor(b.getContractorLvl())+ChatColor.GRAY+"] bonus applied: "+ChatColor.WHITE+"+15% gold");
                                            }
                                            case 9 -> {
                                                p.sendMessage(ChatColor.GRAY+"Level ["+BanditUtils.getContractorLvlColor(b.getContractorLvl())+ChatColor.GRAY+"] bonus applied: "+ChatColor.WHITE+"+25% gold");
                                            }
                                        }
                                        b.updateWallet(active.getReward() * goldBonus);
                                        b.updateContractorXp(active.getRewardXp());
                                        checkLevelUp(p, b);
                                        Contract.assignPlayerContracts(p, b);
                                        b.setDoingContract(false);
                                        b.setActiveContract(null);
                                    } else {
                                        p.sendMessage(ChatColor.RED + "You need more " + active.getRequestedItem().getItemMeta().getDisplayName());
                                    }
                                }
                            }

                        } else {
                            p.sendMessage(ChatColor.RED + "Select a contract first!");
                        }
                    }
                    case SPRUCE_HANGING_SIGN -> {
                        //They want to change their Contractor Title
                        p.openInventory(ContractUI.openContractorTitleSelectUi(p));
                    }
                    case COMPASS -> {
                        //They want to view their active contract information
                        Contract active = b.getActiveContract();
                        if (active == null) {
                            p.sendMessage(ChatColor.RED + "No active contract!");
                        } else {
                            //p.sendMessage(active.getContractName());
                            p.openInventory(ActiveContractUI.openActiveContractUi(p, active));
                        }
                    }
                    case BARRIER -> {
                        p.closeInventory();
                    }
                    case PLAYER_HEAD -> {
                        p.openInventory(ContractUI.openLvlRewardMenu(p));
                    }
                    //Can view what you get from leveling up??
                }
            }
        }
    }

    @EventHandler
    public void navActiveContractMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Active Contract 1")) {
            Bandit b = Bandit.getPlayer(p);
            //In the contract UI menu
            //Find what they clicked on
            switch (e.getCurrentItem().getType()) {
                case BARRIER -> {

                    p.openInventory(ContractUI.openContractorInfo(p, false));
                }
                case TNT -> {
                    //DEACTIVATE CONTRACT
                    p.closeInventory();
                    p.sendMessage("Resigned current contract.");
                    b.setActiveContract(null);
                    b.setDoingContract(false);
                    b.removeActiveContract(b.getActiveContract());
                }
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void navContractorTitleSelectMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Contractor Title Select:")) {
            Bandit b = Bandit.getPlayer(p);
            e.setCancelled(true);
            //In the contract UI menu
            //Find what they clicked on
            if (e.getCurrentItem() != null) {
                switch (e.getCurrentItem().getType()) {
                    case STONE_PICKAXE -> {
                        updateContractorTitle(3, p, b, 1);
                    }
                    case HAY_BLOCK -> {
                        updateContractorTitle(4, p, b, 2);
                    }
                    case LEATHER_BOOTS -> {
                        updateContractorTitle(5, p, b, 3);
                    }
                    case PAPER -> {
                        updateContractorTitle(7, p, b, 4);
                    }
                    case SKELETON_SKULL -> {
                        updateContractorTitle(9, p, b, 5);
                    }
                    case SPYGLASS -> {
                        updateContractorTitle(10, p, b, 6);
                    }
                    case PLAYER_HEAD -> {
                        updateContractorTitle(0, p, b, 0);
                    }
                    case BARRIER -> {
                        p.openInventory(ContractUI.openContractorInfo(p, false));
                    }
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Level Up Perks:")) {
            Bandit b = Bandit.getPlayer(p);
            e.setCancelled(true);
            //In the contract UI menu
            //Find what they clicked on
            if (e.getCurrentItem() != null && e.getCurrentItem().getType().equals(Material.BARRIER)) {
                p.openInventory(ContractUI.openContractorInfo(p, false));
            }
        }
    }

    public void setActiveContract(Bandit b, Player p, Contract selected) {
        if (b.getContracts().isEmpty() && b.getContractorLvl() < 7 ) {
            //Check what slot they chose.
            if (selected != null) {
                b.setActiveContract(selected);
                b.addActiveContract(selected);
                b.setDoingContract(true);
                p.closeInventory();
                b.getActiveContract().startContract(p);
                p.sendMessage(ChatColor.GREEN + "Contract selected." + ChatColor.GRAY + " View your contract by typing" + ChatColor.WHITE + "\"/contract \"" + ChatColor.GRAY + "or " + ChatColor.WHITE + "/c");
            }
        } else {
            //They are doing a contract!
            p.closeInventory();
            p.sendMessage(ChatColor.RED + "Active contract slots full!" +ChatColor.GRAY+" Unlock more active contract slots by leveling up.");
        }
    }

    public void updateContractorTitle(int requiredLevel, Player p, Bandit b, int title) {
        if (b.getContractorLvl() >= requiredLevel) {
            p.sendMessage(ChatColor.GREEN + "Title selected!");
            b.setContractorTitle(title);
        } else {
            p.closeInventory();
            p.sendMessage(ChatColor.RED + "Not a high enough level!");
        }
        p.closeInventory();
    }

    public void checkLevelUp(Player p, Bandit b) {
        List<Integer> reqs = BanditUtils.getLevelXpRequirementList();
        for (int i = 0; i < reqs.size(); i++) {
            if (b.getContractorLvl() < i && b.getContractorXp() >= reqs.get(i)) {
                //Level up player!
                levelUpContractor(p, b, i);
            }
        }
    }

    public void levelUpContractor(Player p, Bandit b, int level) {
        if (b.getContractorLvl() != level) {
            b.setContractorLvl(level);
            p.sendMessage(ChatColor.GRAY + "You have reached " + ChatColor.GREEN + "Contractor Level: " + level);
            p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1, 1);
            p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1, 1);
            GlobalUtils.displayParticles(p.getLocation(), Particle.GLOW_SQUID_INK, Particle.VILLAGER_HAPPY, 10);
        }

    }
}
