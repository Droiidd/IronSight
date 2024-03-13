package droidco.west3.ironsight.Globals.Utils;

import droidco.west3.ironsight.Items.CustomItem;
import droidco.west3.ironsight.Items.ItemIcon;
import droidco.west3.ironsight.Items.Potions.BrewingRecipe;
import droidco.west3.ironsight.Location.Location;
import droidco.west3.ironsight.Location.LocationType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GameContentLoader {
    public static void loadCustomItems()
    {
        //COMMONS
        CustomItem stew = new CustomItem("Brown Stew", 1, true, false,
                "What's in this..?", Material.MUSHROOM_STEW);
        CustomItem charPot = new CustomItem("Charred Potato",1, true, false,
                "Cooked on the coals.",Material.BAKED_POTATO);
        CustomItem cookFox = new CustomItem("Cooked Fox", 1, true, false,
                "Bigger drumstick than chicken!",Material.COOKED_CHICKEN);
        CustomItem rabStew = new CustomItem("Rabbit Stew",2, true, false,
                "Delicious with bread",Material.RABBIT_STEW);
        CustomItem cookedRab = new CustomItem("Cooked Rabbit",2,true,false,
                "Get's you through winter",Material.COOKED_RABBIT);
        CustomItem seaweed = new CustomItem("Seaweed", 1,true,false,
                "Useless",Material.KELP_PLANT);
        CustomItem reed = new CustomItem("Reed",1,true,false,
                "Useless", Material.BAMBOO);
        CustomItem brokenPick = new CustomItem("Broken Pick",2,true,false,
                "A good starter pick",Material.STONE_PICKAXE);
        CustomItem shotAmmo = new CustomItem("Shotgun Ammo",2,true,false,
                "Buckshot only.",Material.WHEAT_SEEDS);
        CustomItem pistolAmmo = new CustomItem("Pistol Ammo",1,true,false,
                "Load your favorite .22",Material.NETHER_WART);
        CustomItem rifleAmmo = new CustomItem("Rifle Ammo",2,true,false,
                "Imported from China",Material.CLAY_BALL);
        CustomItem bandage = new CustomItem("Bandage",2,true,false,
                "Used to heal bloody wounds",Material.PAPER);
        CustomItem splint = new CustomItem("Splint",2,true,false,
                "Used to heal broken bones", Material.STICK);
        CustomItem tracker = new CustomItem("Tracker",1,true,false,
                "Track different locations",Material.COMPASS);
        CustomItem glassBottle = new CustomItem("Glass Bottle",2,true,false,
                "Used for brewing drinks",Material.GLASS_BOTTLE);
        CustomItem fishingRod = new CustomItem("Wooden Fishing Rod",2,true,false,
                "Basic stick and line",Material.FISHING_ROD);
        CustomItem unSmokeLeaf = new CustomItem("Unprocessed Smokeleaf",2,false,false,
                "Process to consume",Material.ENDER_PEARL);
        CustomItem unSpice = new CustomItem("Spice",2,false,false,
                "Process to consume",Material.HONEY_BOTTLE);
        CustomItem iron = new CustomItem("Iron Ore",2, true, false,
                "Can be refined or sold",Material.IRON_ORE);
        CustomItem copper = new CustomItem("Copper Ore",1, true, false,
                "Can be refined or sold",Material.COPPER_ORE);


        //UNCOMMON
        CustomItem cookedSalmon = new CustomItem("Smoked Salmon",3,true,false,
                "Fresh caught, fresh smoked",Material.COOKED_SALMON);
        CustomItem fermentedLiquor = new CustomItem("Fermented Liquor",4,true,false,
                "Extra kick to any home brew",Material.DRAGON_BREATH);
        CustomItem crappie = new CustomItem("Poor Man's Crappie",3,true,false,
                "Skinniest fish",Material.COD);
        CustomItem grayHerring = new CustomItem("Grey Stonned Herring",3,true,false,
                "The cheapest of Herring",Material.SALMON);
        CustomItem chub = new CustomItem("Cactus Pronged Chub",3,true,false,
                "Too spikey to eat",Material.TROPICAL_FISH);
        CustomItem boarCarcass = new CustomItem("Boar Carcass",3,true,false,
                "Right click to skin",Material.MUSIC_DISC_MALL);
        CustomItem cowCarcass = new CustomItem("Cow Carcass",3,true,false,
                "Right click to skin",Material.MUSIC_DISC_STRAD);
        CustomItem gold = new CustomItem("Gold Ore",3, true, false,
                "Can be refined or sold",Material.GOLD_ORE);
        CustomItem slug = new CustomItem("Slug",3,true, false,
                "Reaks of the sea",Material.SPIDER_EYE);
        CustomItem boarmeat = new CustomItem("Boar Meat",3,true,false,
                "Closest thing to bacon",Material.PORKCHOP);
        CustomItem cowmeat = new CustomItem("Cow Meat",3,true,false,
                "Prime beef",Material.BEEF);
        CustomItem rabbitmeat = new CustomItem("Rabbit Meat",3,true,false,
                "Not too gamey",Material.RABBIT);
        CustomItem foxmeat = new CustomItem("Fox Meat",4,true,false,
                "Better than chicken!",Material.CHICKEN);
        CustomItem boarhide = new CustomItem("Boar Hide",4,true,false,
                "Extermely tough hide",Material.RABBIT_HIDE);
        CustomItem cowhide = new CustomItem("Cow Hide",4,true,false,
                "Good for boots",Material.LEATHER);
        CustomItem rabbithide = new CustomItem("Rabbit Hide",4,true,false,
                "Good for small leather work",Material.LIGHT_GRAY_DYE);
        CustomItem foxfur = new CustomItem("Fox Fur",4,true,false,
                "Warmest fur on the market",Material.ORANGE_DYE);
        CustomItem geode = new CustomItem("Geode",4,true,false,
                "Bring to geologist to open",Material.FIREWORK_STAR);
        CustomItem minerspick = new CustomItem("Old Miner's Pick",4,true,false,
                "Steeled with rust",Material.IRON_PICKAXE);
        CustomItem procsmokeleaf = new CustomItem("Processed Smokeleaf",4,false,false,
                "Process to consume",Material.ENDER_PEARL);
        CustomItem spicebottle = new CustomItem("Spice Bottle",4,false,false,
                "Smell's like the future",Material.HONEY_BOTTLE);
        CustomItem oil = new CustomItem("Unrefined Oil",4,false,false,
                "Refine for higher sale value",Material.BUCKET);
        CustomItem frenzyrecipe = new CustomItem("Miner's Frenzy Brew Recipe",3,true,false,
                "Right click to view recipe",Material.FLOWER_BANNER_PATTERN);
        CustomItem doublespaderecipe = new CustomItem("Miner's Double Spade Brew Recipe",3,true,false,
                "Right click to view recipe",Material.FLOWER_BANNER_PATTERN);
        CustomItem greenthumbrecipe = new CustomItem("Green Thumb Brew Recipe",3,true,false,
                "Right click to view recipe",Material.FLOWER_BANNER_PATTERN);
        CustomItem fermentliquorrecipe = new CustomItem("Fermented Liquor Recipe",3,true,false,
                "Right click to view recipe",Material.FLOWER_BANNER_PATTERN);

        //RARE ITEMS
        CustomItem amythestBud = new CustomItem("Amythest Bud",6, true,false,
                "Like a blossoming rose",Material.AMETHYST_SHARD);
        CustomItem mossyJade = new CustomItem("Mossy Jade",5,true,false,
                "Look's alive under light",Material.SLIME_BALL);
        CustomItem hermitCrab = new CustomItem("Hermit Crab",5,true,false,
                "Fish love it",Material.NAUTILUS_SHELL);
        CustomItem frenziedStems = new CustomItem("Frenzied Stems",5,true,false,
                "Used on workers for productivity",Material.FIRE_CORAL);
        CustomItem heartFruit = new CustomItem("Heart Fruit",5,true,false,
                "Still beating",Material.SWEET_BERRIES);
        CustomItem refinedOil = new CustomItem("Refined Oil",6,false,false,
                "Highly sought after",Material.LAVA_BUCKET);
        CustomItem southernsalmon = new CustomItem("Southern Salmon",6,true,false,
                "Migrated south through the tributaries",Material.MUSIC_DISC_FAR);
        CustomItem arcticsalmon = new CustomItem("Arctic Salmon",6,true,false,
                "Still in the frozen north",Material.MUSIC_DISC_13);


        //LEGENDARY
        CustomItem alligator = new CustomItem("Alligator",8,true,false,
                "Crikey!",Material.MUSIC_DISC_CHIRP);
        CustomItem sunkenCatfish = new CustomItem("Sunken Catfish",8,true,false,
                "Evolved in the low-lands",Material.MUSIC_DISC_BLOCKS);
        CustomItem goldstonnedherring = new CustomItem("Gold Stonned Herring",7,true,false,
                "The rare mutation of the gray herring",Material.MUSIC_DISC_MELLOHI);
        CustomItem pearlrivertrout = new CustomItem("Pearl River Trout",7,true,false,
                "Alluring rainbow scales",Material.MUSIC_DISC_CAT);
        CustomItem piratesBooty = new CustomItem("Pirates booty",8,true,false,
                "Richest dabloon",Material.SUNFLOWER);
        CustomItem oilBarrel = new CustomItem("Oil Barrel",7,false,false,
                "A large amount of refined oil",Material.WATER_BUCKET);
        CustomItem goldengamble = new CustomItem("Golden Gamble Petal",7,true,false,
                "Said to be lucky!",Material.HONEYCOMB);
        CustomItem molesbreath = new CustomItem("Moles Breath Spores",7,true,false,
                "Moles use it to dig quicker",Material.FROGSPAWN);
        CustomItem starpetal = new CustomItem("Star Petal",7,true,false,
                "It smell makes dreams come true",Material.GLOW_INK_SAC);
        CustomItem riverdiamond = new CustomItem("River Diamond",7,true,false,
                "The root of many wars",Material.DIAMOND);
        CustomItem barronsemerald = new CustomItem("Barron's Emerald",7,true,false,
                "An old king was fond of these",Material.EMERALD);
        CustomItem voidopal = new CustomItem("Void Opal",8,true,false,
                "Stare into the depths of the universe",Material.ECHO_SHARD);


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

        //Change location message
        Location wilderness = new Location("Wilderness", "Yeehaw", LocationType.WILDERNESS, 0, 0, 0, 0);

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

    public static void loadBrewing(){
        System.out.println("In Load Brewing");
        new BrewingRecipe(Material.BOWL, (inventory, ingredient) -> {//Some lambda magic
            return new ItemStack(Material.BEACON);

        });
    }
}
