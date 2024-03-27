package droidco.west3.ironsight.NPC;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Tracker.TrackerUIs;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;


import java.net.http.WebSocket;

public class NPCEvents implements Listener {
    @EventHandler
    public void npcRightClick(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        String clickedNPCname = e.getRightClicked().getCustomName();
        NPC clickedNPC = NPC.getNPC(clickedNPCname);
        Bandit b = Bandit.getPlayer(p);
        switch (clickedNPC.getType()){
            case ARMORER -> {
                p.openInventory(NPCUI.armorerUI(p));
                e.setCancelled(true);


                break;
            }
            case ILLEGAL_ARMORER -> {
                p.openInventory(NPCUI.illegalArmorerUI(p));
                break;
            }
            case SHOPKEEPER -> {
                p.openInventory(NPCUI.shopkeeperUI(p));
                break;
            }
            case FISHERMAN -> {
                p.openInventory(NPCUI.fishermanUI(p));
                break;
            }
            case PHARMACIST -> {
                p.openInventory(NPCUI.pharmacistUI(p));
                break;
            }
            case OFFICER_ARMS_DEALER -> {
                p.openInventory(NPCUI.officerArmsUI(p));
                break;
            }
            case ARMS_DEALER -> {
                p.openInventory(NPCUI.armsDealerUI(p));
                break;
            }
            case ILL_ARMS_DEALER -> {
                p.openInventory(NPCUI.illegalArmsUI(p));
                break;
            }
            case GEOLOGIST -> {
                p.openInventory(NPCUI.geologistUI(p));
                break;
            }
            case STABLE_MANAGER -> {
                p.openInventory(NPCUI.stableManagerUI(p));
                break;
            }
            case CONDUCTOR -> {
                p.openInventory(NPCUI.conductorUI(p));
                break;
            }
            case FERRY_CAPTAIN -> {
                p.openInventory(NPCUI.ferryCaptainUI(p));
                break;
            }
            case BANKER -> {
                p.openInventory(NPCUI.bankerUI(p));
                break;

            }
            case VAULT_KEEPER -> {
                p.openInventory(NPCUI.vaultKeeperUI(p));
                break;
            }
            case CONTRACTOR -> {
                p.openInventory(NPCUI.contractorUI(p));
                break;
            }
            case CHIEF_OF_POLICE -> {
                p.openInventory(NPCUI.chiefUI(p));
                break;
            }

        }
    }
}
