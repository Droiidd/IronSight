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
    public static Block getRandomSurfaceBlock(World w, double x, double z){
       // double y = w.getHighestBlockYAt((int) x,(int) z);
        Block b = w.getHighestBlockAt((int) x,(int) z);
        switch(b.getType()){
            case OAK_LEAVES,DARK_OAK_LEAVES,BIRCH_LEAVES,SPRUCE_LEAVES,ACACIA_LEAVES,JUNGLE_LEAVES,LAVA,WATER,AIR,CAVE_AIR ->{
                return null;
            }
        }
        return b;
    }
    public static Block getRandomCaveBlock(Player p){
        double x = getRandomCord(p.getLocation().getX()-45.0,p.getLocation().getX()+45.0);
        double y = getRandomCord(p.getLocation().getY()-20.0,p.getLocation().getY()+20.0);
        double z = getRandomCord(p.getLocation().getZ()-45.0,p.getLocation().getZ()+45.0);
        Location blockLoc = new Location(p.getWorld(),x,y,z);
        Location blockLocUp1 = new Location(p.getWorld(),x,y+1.0,z);
        Location blockLocUp2 = new Location(p.getWorld(),x,y+2.0,z);
        Block b = p.getWorld().getBlockAt(blockLoc);
        Block b1 = p.getWorld().getBlockAt(blockLocUp1);
        Block b2 = p.getWorld().getBlockAt(blockLocUp2);
        switch(b.getType()){
            case OAK_LEAVES,DARK_OAK_LEAVES,BIRCH_LEAVES,SPRUCE_LEAVES,ACACIA_LEAVES,JUNGLE_LEAVES,AIR,LAVA -> {
                return null;
            }
        }
        if(b1.getType().isAir() && b2.getType().isAir()){
            p.sendMessage("NOT INSIDE BLOCK");
            return b;
        }
        p.sendMessage("INSIDE BLOCK");
        return null;
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
