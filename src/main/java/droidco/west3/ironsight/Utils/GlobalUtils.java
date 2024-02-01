package droidco.west3.ironsight.Utils;

import droidco.west3.ironsight.Objects.Items.CustomItem;
import droidco.west3.ironsight.Objects.Items.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;

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
    public static void loadCustomItems()
    {
        //FOODS
        CustomItem stew = new CustomItem("brown_stew","Brown Stew", Rarity.COMMON,
                ChatColor.DARK_GRAY + "What's in this..?","", Material.MUSHROOM_STEW);
        CustomItem charPot = new CustomItem("charred_potato","Charred Potato",Rarity.COMMON,
                ChatColor.DARK_GRAY+"Cooked on the coals.","",Material.BAKED_POTATO);
        CustomItem cookFox = new CustomItem("cooked_fox","Cooked Fox", Rarity.COMMON,
                ChatColor.DARK_GRAY+"Bigger drumstick than chicken!","",Material.COOKED_CHICKEN);
        CustomItem rabStew = new CustomItem("rabbit_stew","Rabbit Stew",Rarity.COMMON,
                ChatColor.DARK_GRAY+"Delicious with bread","",Material.RABBIT_STEW);
        CustomItem iron = new CustomItem("iron_ore","Iron Ore",Rarity.COMMON,
                ChatColor.DARK_GRAY+"Can be refined or sold","",Material.IRON_ORE);
        CustomItem copper = new CustomItem("copper_ore","Copper Ore", Rarity.COMMON,
                ChatColor.DARK_GRAY+"Can be refined or sold","",Material.COPPER_ORE);
        CustomItem gold = new CustomItem("gold_ore","Gold Ore",Rarity.UNCOMMON,
                ChatColor.DARK_GRAY+"Can be refined or sold", "",Material.GOLD_ORE);
        CustomItem slug = new CustomItem("sea_slug","Slug",Rarity.COMMON,
                ChatColor.DARK_GRAY+"Reaks of the sea","",Material.SPIDER_EYE);
        
    }
}
