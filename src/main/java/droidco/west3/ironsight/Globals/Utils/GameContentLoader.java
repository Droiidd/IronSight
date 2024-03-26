package droidco.west3.ironsight.Globals.Utils;

import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.Contracts.OilField.OilFieldCrate;
import droidco.west3.ironsight.Contracts.OilField.OilFieldTask;
import droidco.west3.ironsight.Contracts.Utils.ContractType;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Items.CustomItem;
import droidco.west3.ironsight.Items.ItemIcon;
import droidco.west3.ironsight.Items.Potions.BrewingRecipe;
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.FrontierLocation.LocationType;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.*;
import java.util.ArrayList;
import java.util.List;


public class GameContentLoader {
    public static void loadCustomItems()
    {
        System.out.println("Loading custom items");
        //COMMONS
        CustomItem stew = new CustomItem("Brown Stew", 1, true, false,
                "What's in this..?", Material.MUSHROOM_STEW,0.0,0.0);
        CustomItem charPot = new CustomItem("Charred Potato",1, true, false,
                "Cooked on the coals.",Material.BAKED_POTATO,0.0,0.0);
        CustomItem cookFox = new CustomItem("Cooked Fox", 1, true, false,
                "Bigger drumstick than chicken!",Material.COOKED_CHICKEN,0.0,0.0);
        CustomItem rabStew = new CustomItem("Rabbit Stew",2, true, false,
                "Delicious with bread",Material.RABBIT_STEW,0.0,0.0);
        CustomItem cookedRab = new CustomItem("Cooked Rabbit",2,true,false,
                "Get's you through winter",Material.COOKED_RABBIT,0.0,0.0);
        CustomItem seaweed = new CustomItem("Seaweed", 1,true,false,
                "Useless",Material.KELP_PLANT,0.0,0.0);
        CustomItem reed = new CustomItem("Reed",1,true,false,
                "Useless", Material.BAMBOO,0.0,0.0);
        CustomItem brokenPick = new CustomItem("Broken Pick",2,true,false,
                "A good starter pick",Material.STONE_PICKAXE,0.0,0.0);
        CustomItem shotAmmo = new CustomItem("Shotgun Ammo",2,true,false,
                "Buckshot only.",Material.WHEAT_SEEDS,0.0,0.0);
        CustomItem pistolAmmo = new CustomItem("Pistol Ammo",1,true,false,
                "Load your favorite .22",Material.NETHER_WART,0.0,0.0);
        CustomItem rifleAmmo = new CustomItem("Rifle Ammo",2,true,false,
                "Imported from China",Material.CLAY_BALL,0.0,0.0);
        CustomItem bandage = new CustomItem("Bandage",2,true,false,
                "Used to heal bloody wounds",Material.PAPER,0.0,0.0);
        CustomItem splint = new CustomItem("Splint",2,true,false,
                "Used to heal broken bones", Material.STICK,0.0,0.0);
        CustomItem tracker = new CustomItem("Tracker",1,true,false,
                "Track different locations",Material.COMPASS,0.0,0.0);
        CustomItem glassBottle = new CustomItem("Glass Bottle",2,true,false,
                "Used for brewing drinks",Material.GLASS_BOTTLE,0.0,0.0);
        CustomItem fishingRod = new CustomItem("Wooden Fishing Rod",2,true,false,
                "Basic stick and line",Material.FISHING_ROD,0.0,0.0);
        CustomItem unSmokeLeaf = new CustomItem("Unprocessed Smokeleaf",2,false,false,
                "Process to consume",Material.ENDER_PEARL,0.0,0.0);
        CustomItem unSpice = new CustomItem("Spice",2,false,false,
                "Process to consume",Material.HONEY_BOTTLE,0.0,0.0);
        CustomItem iron = new CustomItem("Iron Ore",2, true, false,
                "Can be refined or sold",Material.IRON_ORE,0.0,0.0);
        CustomItem copper = new CustomItem("Copper Ore",1, true, false,
                "Can be refined or sold",Material.COPPER_ORE,0.0,0.0);

        //UNCOMMON
        CustomItem cookedSalmon = new CustomItem("Smoked Salmon",3,true,false,
                "Fresh caught, fresh smoked",Material.COOKED_SALMON,0.0,0.0);
        CustomItem fermentedLiquor = new CustomItem("Fermented Liquor",4,true,false,
                "Extra kick to any home brew",Material.DRAGON_BREATH,0.0,0.0);
        CustomItem crappie = new CustomItem("Poor Man's Crappie",3,true,false,
                "Skinniest fish",Material.COD,0.0,0.0);
        CustomItem grayHerring = new CustomItem("Grey Stonned Herring",3,true,false,
                "The cheapest of Herring",Material.SALMON,0.0,0.0);
        CustomItem chub = new CustomItem("Cactus Pronged Chub",3,true,false,
                "Too spikey to eat",Material.TROPICAL_FISH,0.0,0.0);
        CustomItem boarCarcass = new CustomItem("Boar Carcass",3,true,false,
                "Right click to skin",Material.MUSIC_DISC_MALL,0.0,0.0);
        CustomItem cowCarcass = new CustomItem("Cow Carcass",3,true,false,
                "Right click to skin",Material.MUSIC_DISC_STRAD,0.0,0.0);
        CustomItem gold = new CustomItem("Gold Ore",3, true, false,
                "Can be refined or sold",Material.GOLD_ORE,0.0,0.0);
        CustomItem slug = new CustomItem("Slug",3,true, false,
                "Reaks of the sea",Material.SPIDER_EYE,0.0,0.0);
        CustomItem boarmeat = new CustomItem("Boar Meat",3,true,false,
                "Closest thing to bacon",Material.PORKCHOP,0.0,0.0);
        CustomItem cowmeat = new CustomItem("Cow Meat",3,true,false,
                "Prime beef",Material.BEEF,0.0,0.0);
        CustomItem rabbitmeat = new CustomItem("Rabbit Meat",3,true,false,
                "Not too gamey",Material.RABBIT,0.0,0.0);
        CustomItem foxmeat = new CustomItem("Fox Meat",4,true,false,
                "Better than chicken!",Material.CHICKEN,0.0,0.0);
        CustomItem boarhide = new CustomItem("Boar Hide",4,true,false,
                "Extermely tough hide",Material.RABBIT_HIDE,0.0,0.0);
        CustomItem cowhide = new CustomItem("Cow Hide",4,true,false,
                "Good for boots",Material.LEATHER,0.0,0.0);
        CustomItem rabbithide = new CustomItem("Rabbit Hide",4,true,false,
                "Good for small leather work",Material.LIGHT_GRAY_DYE,0.0,0.0);
        CustomItem foxfur = new CustomItem("Fox Fur",4,true,false,
                "Warmest fur on the market",Material.ORANGE_DYE,0.0,0.0);
        CustomItem geode = new CustomItem("Geode",4,true,false,
                "Bring to geologist to open",Material.FIREWORK_STAR,0.0,0.0);
        CustomItem minerspick = new CustomItem("Old Miner's Pick",4,true,false,
                "Steeled with rust",Material.IRON_PICKAXE,0.0,0.0);
        CustomItem procsmokeleaf = new CustomItem("Processed Smokeleaf",4,false,false,
                "Process to consume",Material.ENDER_PEARL,0.0,0.0);
        CustomItem spicebottle = new CustomItem("Spice Bottle",4,false,false,
                "Smell's like the future",Material.HONEY_BOTTLE,0.0,0.0);
        CustomItem oil = new CustomItem("Unrefined Oil",4,false,false,
                "Refine for higher sale value",Material.BUCKET,0.0,0.0);
        CustomItem frenzyrecipe = new CustomItem("Miner's Frenzy Brew Recipe",3,true,false,
                "Right click to view recipe",Material.FLOWER_BANNER_PATTERN,0.0,0.0);
        CustomItem doublespaderecipe = new CustomItem("Miner's Double Spade Brew Recipe",3,true,false,
                "Right click to view recipe",Material.FLOWER_BANNER_PATTERN,0.0,0.0);
        CustomItem greenthumbrecipe = new CustomItem("Green Thumb Brew Recipe",3,true,false,
                "Right click to view recipe",Material.FLOWER_BANNER_PATTERN,0.0,0.0);
        CustomItem fermentliquorrecipe = new CustomItem("Fermented Liquor Recipe",3,true,false,
                "Right click to view recipe",Material.FLOWER_BANNER_PATTERN,0.0,0.0);

        //RARE ITEMS
        CustomItem amythestBud = new CustomItem("Amythest Bud",6, true,false,
                "Like a blossoming rose",Material.AMETHYST_SHARD,0.0,0.0);
        CustomItem mossyJade = new CustomItem("Mossy Jade",5,true,false,
                "Look's alive under light",Material.SLIME_BALL,0.0,0.0);
        CustomItem hermitCrab = new CustomItem("Hermit Crab",5,true,false,
                "Fish love it",Material.NAUTILUS_SHELL,0.0,0.0);
        CustomItem frenziedStems = new CustomItem("Frenzied Stems",5,true,false,
                "Used on workers for productivity",Material.FIRE_CORAL,0.0,0.0);
        CustomItem heartFruit = new CustomItem("Heart Fruit",5,true,false,
                "Still beating",Material.SWEET_BERRIES,0.0,0.0);
        CustomItem refinedOil = new CustomItem("Refined Oil",6,false,false,
                "Highly sought after",Material.LAVA_BUCKET,0.0,0.0);
        CustomItem southernsalmon = new CustomItem("Southern Salmon",6,true,false,
                "Migrated south through the tributaries",Material.MUSIC_DISC_FAR,0.0,0.0);
        CustomItem arcticsalmon = new CustomItem("Arctic Salmon",6,true,false,
                "Still in the frozen north",Material.MUSIC_DISC_13,0.0,0.0);
        CustomItem oilfieldkey = new CustomItem("Crate Key",6,false,false,"Unlocks crates at oil field",
                Material.TRIPWIRE_HOOK,0.0,0.0);


        //LEGENDARY
        CustomItem alligator = new CustomItem("Alligator",8,true,false,
                "Crikey!",Material.MUSIC_DISC_CHIRP,0.0,0.0);
        CustomItem sunkenCatfish = new CustomItem("Sunken Catfish",8,true,false,
                "Evolved in the low-lands",Material.MUSIC_DISC_BLOCKS,0.0,0.0);
        CustomItem goldstonnedherring = new CustomItem("Gold Stonned Herring",7,true,false,
                "The rare mutation of the gray herring",Material.MUSIC_DISC_MELLOHI,0.0,0.0);
        CustomItem pearlrivertrout = new CustomItem("Pearl River Trout",7,true,false,
                "Alluring rainbow scales",Material.MUSIC_DISC_CAT,0.0,0.0);
        CustomItem piratesBooty = new CustomItem("Pirates booty",8,true,false,
                "Richest dabloon",Material.SUNFLOWER,0.0,0.0);
        CustomItem oilBarrel = new CustomItem("Oil Barrel",7,false,false,
                "A large amount of refined oil",Material.WATER_BUCKET,0.0,0.0);
        CustomItem goldengamble = new CustomItem("Golden Gamble Petal",7,true,false,
                "Said to be lucky!",Material.HONEYCOMB,0.0,0.0);
        CustomItem molesbreath = new CustomItem("Moles Breath Spores",7,true,false,
                "Moles use it to dig quicker",Material.FROGSPAWN,0.0,0.0);
        CustomItem starpetal = new CustomItem("Star Malt Petal",7,true,false,
                "It smell makes dreams come true",Material.GLOW_INK_SAC,0.0,0.0);
        CustomItem riverdiamond = new CustomItem("River Diamond",7,true,false,
                "The root of many wars",Material.DIAMOND,0.0,0.0);
        CustomItem barronsemerald = new CustomItem("Barron's Emerald",7,true,false,
                "An old king was fond of these",Material.EMERALD,0.0,0.0);
        CustomItem voidopal = new CustomItem("Void Opal",8,true,false,
                "Stare into the depths of the universe",Material.ECHO_SHARD,0.0,0.0);
        System.out.println("custom items loaded");

    }
    public static void loadLocations(IronSight plugin){
        System.out.println("Loading all locations");
        FrontierLocation stormpoint = new FrontierLocation("Storm Point","Drug Base", LocationType.ILLEGAL, 26, -157, -2788, -3015);
        FrontierLocation northoil = new FrontierLocation("North Oil Field","Illegal area!",LocationType.ILLEGAL, 2827,3041,-2951,-3189);
        FrontierLocation southoil = new FrontierLocation("South Oil Field","Illegal area!",LocationType.ILLEGAL,778,602,1480,1720);

        OilFieldCrate crate = new OilFieldCrate(1,northoil,2857,101,-3048);
        OilFieldCrate crate2 = new OilFieldCrate(2,northoil,2911,101,-3037);
        OilFieldCrate crate3 = new OilFieldCrate(3,northoil,2924,101,-3083);
        OilFieldCrate crate4 = new OilFieldCrate(4,northoil,2936,101,-2988);
        OilFieldCrate crate5 = new OilFieldCrate(5,northoil,2959,101,-3037);
        OilFieldCrate crate6 = new OilFieldCrate(6,northoil,2992,101,-3087);
        OilFieldCrate crate7 = new OilFieldCrate(7,northoil,2984,101,-3154);

        OilFieldTask oil1 = new OilFieldTask(plugin, northoil);
        //OilFieldTask oil2 = new OilFieldTask(southoil);

        FrontierLocation sloughcreek = new FrontierLocation("Slough Creek","Scav Town",LocationType.ILLEGAL,2589,2835,799,471);

        FrontierLocation neworleans = new FrontierLocation("New Orleans", "PvP disabled!",LocationType.TOWN,-1230,-1403,-1834,-1664.0,-1253.0,86.0,-1667.0);
        FrontierLocation santafe = new FrontierLocation("Santa Fe","PvP Disabled",LocationType.TOWN,1119,888,-1755,-2066,1055.0,94.0,-1955.0);
        FrontierLocation texas = new FrontierLocation("Republic Of Texas","PvP Disabled",LocationType.TOWN,-1197,-831,2628,2214,-1034.0,72.0,2526.0);

        FrontierLocation prison = new FrontierLocation("Prison","JaiL!",LocationType.PRISON, 47,52,1271,1277,50,67,1273);

        FrontierLocation blackspur = new FrontierLocation("Black Spur Mines","Be weary of the depths",LocationType.MINE,1542,2248,-2102,-1775);

        FrontierLocation sloughcreekR = new FrontierLocation("Slough Creek River","Fishings good",LocationType.RIVER, 2545,2698,38,1243);
        FrontierLocation pearlR = new FrontierLocation("Pearl River","Ice cold rapids!",LocationType.RIVER,2599,2083,-2596,-2475);
        FrontierLocation threeForks = new FrontierLocation("Three Forks River","A thick and nasty swamp",LocationType.RIVER,-1330,-1100,-2100,2955);
        FrontierLocation guadalupe = new FrontierLocation("Lower Guadalupe River", "Sunk into the canyon long ago",LocationType.RIVER,-1876,-1681,1160,341);

        FrontierLocation wilderness = new FrontierLocation("Wilderness", "Yeehaw", LocationType.WILDERNESS, 0, 0, 0, 0);
        System.out.println("Locations loaded");
    }
    public static void loadIcons()
    {
        System.out.println("loading all icons");
        ItemIcon cowboy = new ItemIcon("Cowboy","CowboyPrefix","Choose cowboy!", Material.HAY_BLOCK);
        ItemIcon tracker = new ItemIcon("Tracker","TrackerPrefix", "Choose tracker!",Material.LEATHER_BOOTS);
        ItemIcon raider = new ItemIcon("Raider","RaiderPrefix","Choose raider!",Material.SKELETON_SKULL);

        ItemIcon miner = new ItemIcon("Miner","MinerPrefix","Choose miner!",Material.STONE_PICKAXE);
        ItemIcon medic = new ItemIcon("Medic","MedicPrefix","Choose medic!",Material.PAPER);
        ItemIcon explorer = new ItemIcon("Explorer","ExplorerPrefix","Choose explorer!",Material.SPYGLASS);
        ItemIcon contractorTitle = new ItemIcon("Contractor Title","Contractor Title","Select your contractor title",Material.SPRUCE_HANGING_SIGN);

        ItemIcon santafe = new ItemIcon("Santa Fe","RespawnSF","Click to respawn here",Material.NETHER_STAR);
        ItemIcon neworleans = new ItemIcon("New Orleans","RespawnNO","Click to respawn here",Material.CAKE);
        ItemIcon texas = new ItemIcon("Republic Of Texas", "RespawnRoT","Click to respawn here",Material.DEAD_BUSH);

        ItemIcon contractLoc = new ItemIcon("Locations:","ContractLoc","Go here to complete.",Material.COMPASS);
        ItemIcon contractReq = new ItemIcon("Request:","ContractReq","Requested items:",Material.DIAMOND);
        ItemIcon contractDesc = new ItemIcon("Description","ContractDesc","What to do:",Material.MOJANG_BANNER_PATTERN);
        System.out.println("Icons loaded");
    }

    public static void loadBrewing(){
        new BrewingRecipe("Miner's Double Spade Brew", new ItemStack(Material.FROGSPAWN), false, PotionEffectType.LUCK, 0, 120, Color.YELLOW, "empty");
        new BrewingRecipe("Green Thumb Brew", new ItemStack(Material.HONEYCOMB), false, PotionEffectType.LUCK, 1, 120, Color.GREEN, "empty");
        new BrewingRecipe("Double Hook Brew", new ItemStack(Material.SUNFLOWER), false, PotionEffectType.LUCK, 2, 60, Color.BLUE, "empty");

        new BrewingRecipe("Miner's Frenzy Brew", new ItemStack(Material.FIRE_CORAL), false, PotionEffectType.FAST_DIGGING, 0, 60, Color.BLACK, "empty");

        new BrewingRecipe("Instant Health", new ItemStack(Material.SWEET_BERRIES), false, PotionEffectType.HEAL, 0, 0, Color.fromRGB(253, 94, 94), "empty");
        new BrewingRecipe("Whiskey", new ItemStack(Material.GLOW_INK_SAC), false, PotionEffectType.DAMAGE_RESISTANCE, 0, 60, Color.fromRGB(135, 99, 38), "empty");
        new BrewingRecipe("Morphine", new ItemStack(Material.PUMPKIN_SEEDS), false, PotionEffectType.REGENERATION, 0, 60, Color.fromRGB(20, 151, 163), "empty");
        System.out.println("Brewing load complete.");
    }
    public static void loadContracts(){
        System.out.println("Loading contracts!");
        List<FrontierLocation> testLocs = new ArrayList<>();
        testLocs.add(FrontierLocation.getLocation("Black Spur Mines"));
        testLocs.add(FrontierLocation.getLocation("Santa Fe"));
        Contract testC1 = new Contract("Looking for iron.", ContractType.Delivery, testLocs ,1);

        List<FrontierLocation> test2Locs = new ArrayList<>();
        test2Locs.add(FrontierLocation.getLocation("North Oil Field"));
        test2Locs.add(FrontierLocation.getLocation("Slough Creek"));
        Contract testC2 = new Contract("I need fish caught.",ContractType.Delivery, test2Locs ,1);

        List<FrontierLocation> test3Locs = new ArrayList<>();
        //test3Locs.add(FrontierLocation.getLocation("Storm Point"));
        //test3Locs.add(FrontierLocation.getLocation("New Orleans"));
        test3Locs.add(FrontierLocation.getLocation("North Oil Field"));


        Contract testC3 = new Contract("Raid northern Oil Field",ContractType.OilField , test3Locs ,1);

        //Contract testC4 = new Contract("Big time moves.",ContractType.Delivery , test3Locs ,1);
        System.out.println("Contracts loaded!");
    }
}
