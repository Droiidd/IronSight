package droidco.west3.ironsight.Bandit;

import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.Items.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.C;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FishingEvents implements Listener {

    public void handleCaughtFish(){

    }
    public void caughtFishEffects(Player p) {
        p.sendMessage(ChatColor.AQUA + "Fish on!");
        GlobalUtils.displayParticles(p.getLocation(), Particle.GLOW_SQUID_INK, Particle.VILLAGER_HAPPY, 10);
        p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
        p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_PUFFER_FISH_DEATH, 1, 0);
        p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_TROPICAL_FISH_FLOP, 1, 0);
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent e){
        Player p = e.getPlayer();
        Bandit b = Bandit.getPlayer(p);
        ItemStack hermitBait = CustomItem.getCustomItem("Hermit Crab").getItemStack();
        ItemStack slugBait = CustomItem.getCustomItem("Sea Slug").getItemStack();
        int fishAmt = 1;

        //HANDLE BAIT REMOVING:
        if(p.getInventory().containsAtLeast(hermitBait,1)){
            p.getInventory().removeItem(hermitBait);
        }
        else if(p.getInventory().containsAtLeast(slugBait,1)){
            p.getInventory().removeItem(slugBait);
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

        if(b.getCurrentLocation().getLocName().equalsIgnoreCase(FrontierLocation.getLocation("Pearl River").getLocName())){
            //ArrayList<ItemStack> fishList = getLoot();
            //int ranFish = GlobalUtils.getRandomNumber(loot.size());
            //ItemStack caughtFish = fishList.get(rand);
            //caughtFish.setAmount(fishAmt);
            //p.getWorld().dropItemNaturally(p.getLocation(),caughtFish);


        }else if(b.getCurrentLocation().getLocName().equalsIgnoreCase(FrontierLocation.getLocation("Three Forks Delta").getLocName())){

        }
        else if(b.getCurrentLocation().getLocName().equalsIgnoreCase(FrontierLocation.getLocation("Lower Guadalupe Rier").getLocName())){

        }else if(b.getCurrentLocation().getLocName().equalsIgnoreCase(FrontierLocation.getLocation("Slough Creek River").getLocName())){

        }else{
            //FISHING IN THE MIDDLE OF NOWHERE
            //NO RARE FISH

        }



    }


}
