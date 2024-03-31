package droidco.west3.ironsight.Globals.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
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
    public static double getRandomCord(double min, double max){
        double val = 0.0;
        if(max < min ){
            double tmp = max;
            max = min;
            min = tmp;
        }
        Random rand = new Random(System.currentTimeMillis());
        if(Double.valueOf(max - min).isInfinite() == false ){
            val = min + (max - min) * rand.nextDouble();
        }
        return val;
    }
    public static Block getSurfaceYVal(World w, double x, double z){
       // double y = w.getHighestBlockYAt((int) x,(int) z);
        Block b = w.getHighestBlockAt((int) x,(int) z);
        switch(b.getType()){
            case OAK_LEAVES,DARK_OAK_LEAVES,BIRCH_LEAVES,SPRUCE_LEAVES,ACACIA_LEAVES,JUNGLE_LEAVES ->{
                return null;
            }
        }
        return b;
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
