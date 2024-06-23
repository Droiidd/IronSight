package droidco.west3.ironsight.Globals.Utils;

import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.Contracts.Utils.ContractType;
import droidco.west3.ironsight.Contracts.Utils.ContractUtils;
import droidco.west3.ironsight.Contracts.Utils.Difficulty;
import droidco.west3.ironsight.Horse.FrontierHorseType;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    public static double getRandomNumberDoub(int sizeOfNumberPool){
        Random rand = new Random(System.currentTimeMillis());
        return rand.nextDouble(sizeOfNumberPool);
    }
    public static int getRandomRange(int low, int high){
        Random rand = new Random(System.currentTimeMillis());
        return rand.nextInt(high-low)+low;
    }
    public static double getRandomRangeDoub(int low, int high){
        Random rand = new Random(System.currentTimeMillis());
        return rand.nextDouble(high-low)+low;
    }
    public static double getDistanceBetweenPoints(Player p, double targetx2, double targety2){
        int x1 = p.getLocation().getBlockX();
        int y1 = p.getLocation().getBlockZ();
        int x2 = (int) targetx2;
        int y2 = (int) targety2;
        p.sendMessage(ChatColor.GREEN+ "PLAYER X "+x1+" Y "+y1);
        p.sendMessage(ChatColor.RED+"TARGET X "+x2+" Y "+y2);
        int num1 = x2-x1;
        int num2 = y2-y1;
        p.sendMessage(ChatColor.AQUA+ "DISTANCE: "+ Math.sqrt((num1*num1)+(num2*num2)));
        p.sendMessage("==============");
        return Math.sqrt((num1*num1)+(num2*num2));
    }
    public static ItemStack getContractSlot(Contract selected, Difficulty difficulty) {
        //Basic item set up

        //LORE STRUCTURE FOR CONTRACTS IS ALWAYS:
        /*
        NAME
        ---
        Location
        Reward
        TargetName / Requested Goods (If applicable)
         */
        ItemStack contract = selected.getContractIcon();
        ItemMeta contractMeta = contract.getItemMeta();
        ArrayList<String> contractLore = new ArrayList<>();
        //Setting up the strings for the lore

        //LORE
        contractLore.add((ContractUtils.getDifficultyScale(difficulty).equalsIgnoreCase("IV") ?
                ChatColor.RED + ContractUtils.getDifficultyScale(difficulty) : ContractUtils.getDifficultyScale(difficulty)));
        contractLore.add(ChatColor.GRAY + "Location: " + selected.getLocation().getLocName());
        if (selected.getContractType().equals(ContractType.DELIVERY)) {
            contractLore.add(String.valueOf(ChatColor.GRAY) + selected.getRequestedAmount() + " " + selected.getRequestedItem().getItemMeta().getDisplayName());
        }
        contractLore.add(ChatColor.GRAY + "Reward: " + selected.getReward() + " g");
        //This displays the contracts type
        //contractLore.add(ChatColor.GRAY+ContractUtils.getTypeString(selected.getType()));
        contractMeta.setLore(contractLore);
        contract.setItemMeta(contractMeta);
        return contract;
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
    public static Block getRandomSurfaceBlock(Player p){
       // double y = w.getHighestBlockYAt((int) x,(int) z);
        Block b = null;
        double x = 0.0;
        double z = 0.0;
        boolean safeSpawn = false;
        ArrayList<Material> unsafeBlocks = new ArrayList<>();
        unsafeBlocks.add(Material.OAK_LEAVES);
        unsafeBlocks.add(Material.AIR);
        unsafeBlocks.add(Material.LAVA);
        unsafeBlocks.add(Material.JUNGLE_LEAVES);
        unsafeBlocks.add(Material.SPRUCE_LEAVES);
        unsafeBlocks.add(Material.BIRCH_LEAVES);
        unsafeBlocks.add(Material.ACACIA_LEAVES);
        unsafeBlocks.add(Material.DARK_OAK_LEAVES);
        unsafeBlocks.add(Material.MANGROVE_LEAVES);
        unsafeBlocks.add(Material.CHERRY_LEAVES);
        unsafeBlocks.add(Material.AZALEA_LEAVES);
        unsafeBlocks.add(Material.OAK_LEAVES);
        unsafeBlocks.add(Material.WATER);
        while(!safeSpawn){
            x = getRandomCord(p.getLocation().getX()-30.0,p.getLocation().getX()+30.0);
            z = getRandomCord(p.getLocation().getZ()-30.0,p.getLocation().getZ()+30.0);
            b = p.getWorld().getHighestBlockAt((int) x,(int) z);
            if(!unsafeBlocks.contains(b.getType())){
                safeSpawn = true;
            }
        }
        return b = b.getWorld().getBlockAt(b.getLocation().add(0.0,1.0 ,0.0));
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
    public static void saveBackpack(ItemStack[] items) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeInt(items.length);
                for(ItemStack item : items) {
                    dataOutput.writeObject(item);

                }
            dataOutput.close();
            String serialized = Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static FrontierHorseType getHorseTypeFromStr(String type ){
        switch(type){
            case "thoroughbred" -> {
                return FrontierHorseType.THOROUGHBRED;
            }
            case "standard" -> {
                return FrontierHorseType.STANDARD;
            }
            case "donkey" -> {
                return FrontierHorseType.DONKEY;
            }
        }
        return null;
    }
    public static String getHorseTypeString(FrontierHorseType horseType){
        switch(horseType){
            case DONKEY -> {
                return "donkey";
            }case THOROUGHBRED -> {
                return "thoroughbred";
            }case STANDARD -> {
                return "standard";
            }
        }
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
            double rand = getRandomRangeDoub(0,1);
            blockLoc.getWorld().spawnParticle(p1, blockLoc.getX() + rand, blockLoc.getY() + rand, blockLoc.getZ() + rand, 0);
            rand = getRandomRangeDoub(0,1);
            blockLoc.getWorld().spawnParticle(p2, blockLoc.getX() + rand, blockLoc.getY() + rand, blockLoc.getZ() - rand, 0);
        }
    }
    public static Double getGoldStrToD(ItemStack goldItem, Player p) {
        String amount = ChatColor.stripColor(goldItem.getItemMeta().getDisplayName());
        String numberOnly = amount.replaceAll("[^0-9]", "");
        Double depositGold = (GlobalUtils.StrToDNoMsg(numberOnly)) / 10;
        return depositGold;
    }
    public static Double StrToDNoMsg(String s) {
        try {
            Double amount = Double.parseDouble(s);
            return amount;
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        return -1.0;
    }



}
