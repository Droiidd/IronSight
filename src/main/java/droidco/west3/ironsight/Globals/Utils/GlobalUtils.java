package droidco.west3.ironsight.Globals.Utils;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
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
        double x = 0.0;
        double y = 0.0;
        double z = 0.0;
        boolean safeSpawn = false;
        boolean mainBlock = false;
        boolean upperBlocks = false;
        Block b = null;
        Block b1 = null;
        Block b2 = null;
        Location blockLoc = null;
        ArrayList<Material> unsafeBlocks = new ArrayList<>();
        unsafeBlocks.add(Material.OAK_LEAVES);
        unsafeBlocks.add(Material.AIR);
        unsafeBlocks.add(Material.LAVA);
        unsafeBlocks.add(Material.JUNGLE_LEAVES);
        unsafeBlocks.add(Material.SPRUCE_LEAVES);
        unsafeBlocks.add(Material.BIRCH_LEAVES);
        unsafeBlocks.add(Material.ACACIA_LEAVES);
        unsafeBlocks.add(Material.WATER);

        while(safeSpawn == false){
            x=getRandomCord(p.getLocation().getX()-30.0,p.getLocation().getX()+30.0);
            y=getRandomCord(p.getLocation().getY()-20.0,p.getLocation().getY()+20.0);
            z=getRandomCord(p.getLocation().getZ()-30.0,p.getLocation().getZ()+30.0);
            blockLoc = new Location(p.getWorld(),x,y,z);
            b = p.getWorld().getBlockAt(blockLoc);
            b1 = p.getWorld().getBlockAt(blockLoc.add(0.0,1.0,0.0));
            b2 = p.getWorld().getBlockAt(blockLoc.add(0.0,2.0,0.0));
            if(!unsafeBlocks.contains(b.getType())){
                mainBlock = true;
            }
            if(b1.getType().isAir() && b2.getType().isAir()){
                upperBlocks = true;
            }
            if(upperBlocks && mainBlock){
                safeSpawn = true;
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
