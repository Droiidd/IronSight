package droidco.west3.ironsight.Utils;

import droidco.west3.ironsight.Objects.Items.CustomItem;
import droidco.west3.ironsight.Objects.Items.ItemIcon;
import droidco.west3.ironsight.Objects.Location.Location;
import droidco.west3.ironsight.Objects.Location.LocationType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
    public static void loadCustomItems()
    {
        //FOODS
        CustomItem stew = new CustomItem("Brown Stew", 1, true, false,
                 "What's in this..?", Material.MUSHROOM_STEW);
        CustomItem charPot = new CustomItem("Charred Potato",1, true, false,
                "Cooked on the coals.",Material.BAKED_POTATO);
        CustomItem cookFox = new CustomItem("Cooked Fox", 1, true, false,
                "Bigger drumstick than chicken!",Material.COOKED_CHICKEN);
        CustomItem rabStew = new CustomItem("Rabbit Stew",2, true, false,
                "Delicious with bread",Material.RABBIT_STEW);
        CustomItem iron = new CustomItem("Iron Ore",2, true, false,
                "Can be refined or sold",Material.IRON_ORE);
        CustomItem copper = new CustomItem("Copper Ore",1, true, false,
                "Can be refined or sold",Material.COPPER_ORE);
        CustomItem gold = new CustomItem("Gold Ore",3, true, false,
                "Can be refined or sold",Material.GOLD_ORE);
        CustomItem slug = new CustomItem("Slug",3,true, false,
                "Reaks of the sea",Material.SPIDER_EYE);

    }
    public static void loadLocations(){
        Location stormpoint = new Location("Storm Point","Drug Base", LocationType.ILLEGAL, 26, -157, -2788, -3015);
        Location northoil = new Location("North Oil Field","Illegal area!",LocationType.ILLEGAL, 2827,3041,-2951,-3189);
        Location sloughcreek = new Location("Slough Creek","Scav Town",LocationType.ILLEGAL,2589,2835,799,471);

        Location neworleans = new Location("New Orleans", "PvP disabled!",LocationType.TOWN,-1230,-1403,-1834,-1664.0,-1253.0,86.0,-1667.0);
        Location santafe = new Location("Santa Fe","PvP Disabled",LocationType.TOWN,1119,888,-1755,-2066,1055.0,94.0,-1955.0);
        Location texas = new Location("Republic Of Texas","PvP Disabled",LocationType.TOWN,-1197,-831,2628,2214,-1034.0,72.0,2526.0);

        Location prison = new Location("Prison","JaiL!",LocationType.Prison, 47,52,1271,1277,50,67,1273);

        Location blackspur = new Location("Black Spur Mines","Be weary of the depths",LocationType.NATURAL,1542,2248,-2102,-1775);

        Location sloughcreekR = new Location("Slough Creek River","Fishings good",LocationType.River, 2545,2698,38,1243);
        Location pearlR = new Location("Pearl River","Good fishing!",LocationType.River,2599,2083,-2596,-2475);

    }
    public static void loadIcons()
    {
        ItemIcon cowboy = new ItemIcon("Cowboy","Choose cowboy!", Material.HAY_BLOCK);
        ItemIcon tracker = new ItemIcon("Tracker", "Choose tracker!",Material.LEATHER_BOOTS);
        ItemIcon raider = new ItemIcon("Raider","Choose raider!",Material.SKELETON_SKULL);

        ItemIcon miner = new ItemIcon("Miner","Choose miner!",Material.STONE_PICKAXE);
        ItemIcon medic = new ItemIcon("Medic","Choose medic!",Material.PAPER);
        ItemIcon explorer = new ItemIcon("Explorer","Choose explorer!",Material.SPYGLASS);
        ItemIcon contractorTitle = new ItemIcon("Contractor Title","Select your contractor title",Material.SPRUCE_HANGING_SIGN);

        ItemIcon santafe = new ItemIcon("Santa Fe","Click to respawn here",Material.NETHER_STAR);
        ItemIcon neworleans = new ItemIcon("New Orleans","Click to respawn here",Material.CAKE);
        ItemIcon texas = new ItemIcon("Republic of Texas", "Click to respawn here",Material.DEAD_BUSH);

    }
}
