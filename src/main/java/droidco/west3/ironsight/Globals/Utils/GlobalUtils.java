package droidco.west3.ironsight.Globals.Utils;

import droidco.west3.ironsight.Items.CustomItem;
import droidco.west3.ironsight.Items.ItemIcon;
<<<<<<< HEAD
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.FrontierLocation.LocationType;
=======
import droidco.west3.ironsight.Location.LocationType;
>>>>>>> 0e87fc57c114b06cd9c7f60b73793cabdd0d6e93
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.Random;

public class GlobalUtils {
    public static Double checkStrToDErrMsg(String s, Player p) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            p.sendMessage(ChatColor.RED + "Incorrect usage " + ChatColor.GRAY + "please enter a valid amount.");
        }
        return -1.0;
    }
    public static int getRandomNumber(int sizeOfNumberPool){
        Random rand = new Random(System.currentTimeMillis());
        return rand.nextInt(sizeOfNumberPool);
    }
    public static int getRandomRange(int low, int high){
        Random rand = new Random(System.currentTimeMillis());
        return rand.nextInt(high-low)+low;
    }
    public static int boolToInt(boolean bool){
        if(bool){
            return 1;
        }
        else{
            return 0;
        }
    }
    public static void displayParticles(Location blockLoc, Particle p1, Particle p2, int amount) {
        for (int i = 0; i < amount; i++) {
            blockLoc.getWorld().spawnParticle(p1, blockLoc.getX() + 0.85, blockLoc.getY() + 0.85, blockLoc.getZ() + 0.85, 0);
            blockLoc.getWorld().spawnParticle(p2, blockLoc.getX() + 0.85, blockLoc.getY() + 0.8, blockLoc.getZ() - 0.85, 0);
            blockLoc.getWorld().spawnParticle(p1, blockLoc.getX() + 0.85, blockLoc.getY() - 0.85, blockLoc.getZ() + 0.85, 0);
            blockLoc.getWorld().spawnParticle(p2, blockLoc.getX() + 0.85, blockLoc.getY() - 0.8, blockLoc.getZ() - 0.85, 0);
            blockLoc.getWorld().spawnParticle(p1, blockLoc.getX() - 0.85, blockLoc.getY() + 0.85, blockLoc.getZ() + 0.85, 0);
            blockLoc.getWorld().spawnParticle(p2, blockLoc.getX() - 0.85, blockLoc.getY() + 0.8, blockLoc.getZ() - 0.85, 0);
            blockLoc.getWorld().spawnParticle(p1, blockLoc.getX() - 0.85, blockLoc.getY() - 0.8, blockLoc.getZ() + 0.85, 0);
            blockLoc.getWorld().spawnParticle(p2, blockLoc.getX() - 0.85, blockLoc.getY() - 0.85, blockLoc.getZ() - 0.85, 0);
        }
    }



}
