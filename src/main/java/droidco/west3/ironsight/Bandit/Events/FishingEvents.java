package droidco.west3.ironsight.Bandit.Events;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.FrontierLocation.LocationType;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.Items.CustomItem;
import droidco.west3.ironsight.Items.ItemTable;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class FishingEvents implements Listener {

    @EventHandler
    public void onPlayerFish(PlayerFishEvent e){
        Player p = e.getPlayer();
        Bandit b = Bandit.getPlayer(p);
        ItemStack hermitBait = CustomItem.getCustomItem("Hermit Crab").getItemStack();
        ItemStack slugBait = CustomItem.getCustomItem("Sea Slug").getItemStack();
        int fishAmt = 1;
        boolean hasBait = false;

        //HANDLE BAIT REMOVING:
        if(p.getInventory().containsAtLeast(hermitBait,1)){
            p.getInventory().removeItem(hermitBait);
            hasBait = true;
        }
        else if(p.getInventory().containsAtLeast(slugBait,1)){
            p.getInventory().removeItem(slugBait);
            hasBait = true;
        }
        else{
            e.setCancelled(true);
            p.sendMessage(ChatColor.GRAY + "Seems I need bait...");
        }
        //HANDLE BETTER FISHING ROD ODDS:
        if(p.getInventory().getItemInMainHand().equals(CustomItem.getCustomItem("Expedition Rod").getItemStack())){
            int rand = GlobalUtils.getRandomNumber(101);
            if (rand < 20) {
                //20 % chance to catch double the fish!
                fishAmt = 2;
            }
        }
        if(hasBait && e.getCaught() != null){
            if(b.getCurrentLocation().getLocName().equalsIgnoreCase(FrontierLocation.getLocation("Pearl River").getLocName())){
                e.getCaught().remove();
                successfulFish(p,fishAmt,"Pearl River Fish");
            }else if(b.getCurrentLocation().getLocName().equalsIgnoreCase(FrontierLocation.getLocation("Three Forks Delta").getLocName())){
                e.getCaught().remove();
                successfulFish(p,fishAmt,"Three Forks Fish");
            }
            else if(b.getCurrentLocation().getLocName().equalsIgnoreCase(FrontierLocation.getLocation("Lower Guadalupe River").getLocName())){
                e.getCaught().remove();
                successfulFish(p,fishAmt,"Guadalupe Fish");
            }else if(b.getCurrentLocation().getLocName().equalsIgnoreCase(FrontierLocation.getLocation("Slough Creek River").getLocName())){
                e.getCaught().remove();
                successfulFish(p,fishAmt,"Slough Creek Fish");
            }else if(!b.getCurrentLocation().getType().equals(LocationType.RIVER) ||b.getCurrentLocation().getLocName().equalsIgnoreCase("Wilderness") ){
                //FISHING IN THE MIDDLE OF NOWHERE
                //NO RARE FISH
                e.getCaught().remove();
                successfulFish(p,fishAmt,"Global Fish");
            }
        }
    }
    public void caughtFishEffects(Player p) {
        p.spigot().sendMessage(
                ChatMessageType.ACTION_BAR,
                new TextComponent(String.valueOf(ChatColor.BOLD)+ ChatColor.AQUA + "Fish On!"));

        GlobalUtils.displayParticles(p.getLocation(), Particle.GLOW_SQUID_INK, Particle.VILLAGER_HAPPY, 10);
        p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
        p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_PUFFER_FISH_DEATH, 1, 0);
        p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_TROPICAL_FISH_FLOP, 1, 0);
    }
    public void successfulFish(Player p, int fishAmt, String lootTableName)
    {
        caughtFishEffects(p);
        ArrayList<ItemStack> fishList = ItemTable.getTable(lootTableName).getNumItems(10);
        int randFish = GlobalUtils.getRandomNumber(fishList.size());
        ItemStack caughtFish = fishList.get(randFish);
        caughtFish.setAmount(fishAmt);
        p.getWorld().dropItemNaturally(p.getLocation(),caughtFish);
    }


}
