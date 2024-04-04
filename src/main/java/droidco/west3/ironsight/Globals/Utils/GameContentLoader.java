package droidco.west3.ironsight.Globals.Utils;

import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.Contracts.OilField.OilFieldCrate;
import droidco.west3.ironsight.Contracts.OilField.OilFieldTask;
import droidco.west3.ironsight.Contracts.Utils.ContractType;
import droidco.west3.ironsight.Contracts.Utils.DeliveryType;
import droidco.west3.ironsight.FrontierMobs.FrontierMob;
import droidco.west3.ironsight.FrontierMobs.FrontierMobType;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Items.CustomItem;
import droidco.west3.ironsight.Items.ItemIcon;
import droidco.west3.ironsight.Items.ItemTable;
import droidco.west3.ironsight.Items.Potions.BrewingRecipe;
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.FrontierLocation.LocationType;
import droidco.west3.ironsight.Items.Quantity;
import org.bukkit.ChatColor;
import droidco.west3.ironsight.NPC.NPC;
import droidco.west3.ironsight.NPC.NPCType;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GameContentLoader {
    public static void loadCustomMobs()
    {

    }
    public static void loadCustomItems()
    {
        System.out.println("Loading custom items");
        //COMMONS
        CustomItem stew = new CustomItem("Brown Stew", 1, true, false, "What's in this..?", Material.MUSHROOM_STEW,0.0,2.0);
        CustomItem charPot = new CustomItem("Charred Potato",1, true, false, "Cooked on the coals.",Material.BAKED_POTATO,0.0,4.0);
        CustomItem cookFox = new CustomItem("Cooked Fox", 1, true, false, "Bigger drumstick than chicken!",Material.COOKED_CHICKEN,0.0,5.0);
        CustomItem rabStew = new CustomItem("Rabbit Stew",2, true, false, "Delicious with bread",Material.RABBIT_STEW,0.0,3.0);
        CustomItem cookedRab = new CustomItem("Cooked Rabbit",2,true,false, "Get's you through winter",Material.COOKED_RABBIT,0.0,5.0);
        CustomItem seaweed = new CustomItem("Seaweed", 1,true,false, "Useless",Material.KELP_PLANT,2.0,0.0);
        CustomItem reed = new CustomItem("Reed",1,true,false, "Useless", Material.BAMBOO,2.0,0.0);
        CustomItem brokenPick = new CustomItem("Broken Pick",2,true,false, "A good starter pick",Material.STONE_PICKAXE,0.0,200.0);
        CustomItem shotAmmo = new CustomItem("Shotgun Ammo",2,true,false, "Buckshot only.",Material.WHEAT_SEEDS,0.0,20.0);
        CustomItem pistolAmmo = new CustomItem("Pistol Ammo",1,true,false, "Load your favorite .22",Material.NETHER_WART,0.0,17.0);
        CustomItem rifleAmmo = new CustomItem("Rifle Ammo",2,true,false, "Imported from China",Material.CLAY_BALL,0.0,24.0);
        CustomItem bandage = new CustomItem("Bandage",2,true,false, "Used to heal bloody wounds",Material.PAPER,0.0,9.0);
        CustomItem splint = new CustomItem("Splint",2,true,false, "Used to heal broken bones", Material.STICK,0.0,8.0);
        CustomItem tracker = new CustomItem("Tracker",1,true,false, "Track different locations",Material.COMPASS,0.0,10.0);
        CustomItem glassBottle = new CustomItem("Glass Bottle",2,true,false, "Used for brewing drinks",Material.GLASS_BOTTLE,0.0,15.0);
        CustomItem fishingRod = new CustomItem("Wooden Fishing Rod",2,true,false, "Basic stick and line",Material.FISHING_ROD,0.0,200.0);
        CustomItem unSmokeLeaf = new CustomItem("Unprocessed Smokeleaf",2,false,false, "Process to consume",Material.ENDER_PEARL,0.0,0.0);
        CustomItem unSpice = new CustomItem("Spice",2,false,false, "Process to consume",Material.HONEY_BOTTLE,0.0,0.0);
        CustomItem iron = new CustomItem("Iron Ore",2, true, false, "Can be refined or sold",Material.RAW_IRON,10.0,0.0);
        CustomItem copper = new CustomItem("Copper Ore",1, true, false, "Can be refined or sold",Material.RAW_COPPER,14.0,0.0);

        //UNCOMMON
        CustomItem smokedSalmon = new CustomItem("Smoked Salmon",3,true,false, "Fresh caught, fresh smoked",Material.COOKED_SALMON,0.0,10.0);
        CustomItem fermentedLiquor = new CustomItem("Fermented Liquor",4,true,false, "Extra kick to any home brew",Material.DRAGON_BREATH,16.0,0.0);
        CustomItem crappie = new CustomItem("Poor Mans Crappie",3,true,false, "Skinniest fish",Material.COD,10.0,0.0);
        CustomItem grayHerring = new CustomItem("Gray Stoned Herring",3,true,false, "The cheapest of Herring",Material.SALMON,13.0,0.0);
        CustomItem chub = new CustomItem("Cactus Pronged Chub",3,true,false, "Too spikey to eat",Material.TROPICAL_FISH,16.0,0.0);
        CustomItem boarCarcass = new CustomItem("Boar Carcass",3,true,false, "Right click to skin",Material.MUSIC_DISC_MALL,55.0,0.0);
        CustomItem cowCarcass = new CustomItem("Cow Carcass",3,true,false, "Right click to skin",Material.MUSIC_DISC_STRAD,40.0,0.0);
        CustomItem gold = new CustomItem("Gold Ore",3, true, false, "Can be refined or sold",Material.RAW_GOLD,19.0,0.0);
        CustomItem slug = new CustomItem("Slug",3,true, false, "Reaks of the sea",Material.SPIDER_EYE,4.0,8.0);
        CustomItem boarmeat = new CustomItem("Boar Meat",3,true,false, "Closest thing to bacon",Material.PORKCHOP,7.0,0.0);
        CustomItem cowmeat = new CustomItem("Cow Meat",3,true,false, "Prime beef",Material.BEEF,9.0,0.0);
        CustomItem rabbitmeat = new CustomItem("Rabbit Meat",3,true,false, "Not too gamey",Material.RABBIT,6.0,0.0);
        CustomItem foxmeat = new CustomItem("Fox Meat",4,true,false, "Better than chicken!",Material.CHICKEN,4.0,0.0);
        CustomItem boarhide = new CustomItem("Boar Hide",4,true,false, "Extermely tough hide",Material.RABBIT_HIDE,10.0,0.0);
        CustomItem cowhide = new CustomItem("Cow Hide",4,true,false, "Good for boots",Material.LEATHER,13.0,0.0);
        CustomItem rabbithide = new CustomItem("Rabbit Hide",4,true,false, "Good for small leather work",Material.LIGHT_GRAY_DYE,18.0,0.0);
        CustomItem foxfur = new CustomItem("Fox Fur",4,true,false, "Warmest fur on the market",Material.ORANGE_DYE,16.0,0.0);
        CustomItem geode = new CustomItem("Geode",4,true,false, "Bring to geologist to open",Material.FIREWORK_STAR,0.0,0.0);
        CustomItem minerspick = new CustomItem("Old Miner's Pick",4,true,false, "Steeled with rust",Material.IRON_PICKAXE,0.0,530.0);
        CustomItem procsmokeleaf = new CustomItem("Processed Smokeleaf",4,false,false, "Process to consume",Material.ENDER_PEARL,19.0,0.0);
        CustomItem spicebottle = new CustomItem("Spice Bottle",4,false,false, "Smell's like the future",Material.HONEY_BOTTLE,23.0,0.0);
        CustomItem oil = new CustomItem("Unrefined Oil",4,false,false, "Refine for higher sale value",Material.BUCKET,0.0,0.0);
        CustomItem frenzyrecipe = new CustomItem("Miner's Frenzy Brew Recipe",3,true,false, "Right click to view recipe",Material.FLOWER_BANNER_PATTERN,1500.0,0.0);
        CustomItem doublespaderecipe = new CustomItem("Miner's Double Spade Brew Recipe",3,true,false, "Right click to view recipe",Material.FLOWER_BANNER_PATTERN,1500.0,0.0);
        CustomItem greenthumbrecipe = new CustomItem("Green Thumb Brew Recipe",3,true,false, "Right click to view recipe",Material.FLOWER_BANNER_PATTERN,1500.0,0.0);
        CustomItem fermentliquorrecipe = new CustomItem("Fermented Liquor Recipe",3,true,false, "Right click to view recipe",Material.FLOWER_BANNER_PATTERN,225.0,0.0);

        //RARE ITEMS
        new CustomItem("Life Seeds",7,true,false,"Brews into a slow heal",Material.PUMPKIN_SEEDS, 0.0,0.0);
        CustomItem frenziedStems = new CustomItem("Frenzied Stems",5,true,false, "Used on workers for productivity",Material.FIRE_CORAL,0.0,0.0);
        CustomItem heartFruit = new CustomItem("Heart Fruit",5,true,false, "Still beating",Material.SWEET_BERRIES,0.0,0.0);


        CustomItem amethystBud = new CustomItem("Amethyst Bud",6, true,false, "Like a blossoming rose",Material.AMETHYST_SHARD,85.0,0.0);
        CustomItem mossyJade = new CustomItem("Mossy Jade",5,true,false, "Look's alive under light",Material.SLIME_BALL,65.0,0.0);
        CustomItem hermitCrab = new CustomItem("Hermit Crab",5,true,false, "Fish love it",Material.NAUTILUS_SHELL,8.0,15.0);
        CustomItem refinedOil = new CustomItem("Refined Oil",6,false,false, "Highly sought after",Material.LAVA_BUCKET,90.0,0.0);
        CustomItem southernsalmon = new CustomItem("Southern Salmon",6,true,false, "Migrated south through southern tributaries",Material.MUSIC_DISC_FAR,175.0,0.0);
        CustomItem arcticsalmon = new CustomItem("Arctic Salmon",6,true,false, "Still in the frozen north",Material.MUSIC_DISC_13,180.0,0.0);
        CustomItem oilfieldkey = new CustomItem("Crate Key",6,false,false,"Unlocks crates at oil field", Material.TRIPWIRE_HOOK,0.0,0.0);
        CustomItem steelLinedRod = new CustomItem("Steel Lined Rod",6,true, false, "Double sided hook!", Material.FISHING_ROD,1150.0,0.0, Enchantment.LURE,1);
        CustomItem explorerspick = new CustomItem("Explorer's Pick",6,true,false,"Aids in climbing glaciers",Material.DIAMOND,0.0,1450.0, Enchantment.DIG_SPEED, 1);

        //LEGENDARY
        CustomItem alligator = new CustomItem("Alligator",8,true,false, "Crikey!",Material.MUSIC_DISC_CHIRP,425.0,0.0);
        CustomItem sunkenCatfish = new CustomItem("Sunken Catfish",8,true,false, "Evolved in the low-lands",Material.MUSIC_DISC_BLOCKS,400.0,0.0);
        CustomItem goldstonnedherring = new CustomItem("Gold Stoned Herring",7,true,false, "The rare mutation of the gray herring",Material.MUSIC_DISC_MELLOHI,250.0,0.0);
        CustomItem pearlrivertrout = new CustomItem("Pearl River Trout",7,true,false, "Alluring rainbow scales",Material.MUSIC_DISC_CAT,275.0,0.0);
        CustomItem expaditionRod = new CustomItem("Expedition Rod",8,true, false, "A proper line.", Material.FISHING_ROD,0.0,5950.0, Enchantment.LURE,5);

        CustomItem piratesBooty = new CustomItem("Pirates booty",8,true,false, "Richest dabloon",Material.SUNFLOWER,0.0,0.0);
        CustomItem oilBarrel = new CustomItem("Oil Barrel",7,false,false, "A large amount of refined oil",Material.WATER_BUCKET,650.0,0.0);
        CustomItem goldengamble = new CustomItem("Golden Gamble Petal",7,true,false, "Said to be lucky!",Material.HONEYCOMB,0.0,0.0);
        CustomItem molesbreath = new CustomItem("Moles Breath Spores",7,true,false, "Moles use it to dig quicker",Material.FROGSPAWN,0.0,0.0);
        CustomItem maltpetal = new CustomItem("Blue Malt Petal",7,true,false, "It smell makes dreams come true",Material.GLOW_INK_SAC,0.0,0.0);

        new CustomItem("Crystalized Geode",8,true,false,"Crystals explode out of the sides",Material.MUSIC_DISC_11,0.0,0.0);
        CustomItem riverdiamond = new CustomItem("River Diamond",7,true,false, "The root of many wars",Material.DIAMOND,555.0,0.0);
        CustomItem barronsemerald = new CustomItem("Barron's Emerald",7,true,false, "An old king was fond of these",Material.EMERALD,575.0,0.0);
        CustomItem voidopal = new CustomItem("Void Opal",8,true,false, "Stare into the depths of the universe",Material.ECHO_SHARD,635.0,0.0);

        //ARMOR
        CustomItem farmBoots = new CustomItem("Farm Hand Boots",1,true,false,"'Size too big",Material.LEATHER_BOOTS,0.0,350.0);
        CustomItem farmLegs = new CustomItem("Farm Hand Chaps",2,true,false,"Stained with mud",Material.LEATHER_LEGGINGS,0.0,450.0);
        CustomItem farmChest = new CustomItem("Farm Hand Shirt",2,true,false,"Couple 'a holes",Material.LEATHER_CHESTPLATE,0.0,500.0);
        CustomItem farmHelm = new CustomItem("Farm Hand Hat",1,true,false,"Keeps the sun away",Material.LEATHER_HELMET,0.0,325.0);

        CustomItem huntsmenBoots = new CustomItem("Huntsmen Boots",3,true,false,"Rusty Spurs",Material.IRON_BOOTS,0.0,924.0);
        CustomItem huntsmenLegs = new CustomItem("Huntsmen Trousers",4,true,false,"Mostly fur",Material.IRON_LEGGINGS,0.0,1188.0);
        CustomItem huntsmenChest = new CustomItem("Huntsmen Jacket",4,true,false,"Made with boar hide",Material.IRON_CHESTPLATE,0.0,1320.0);
        CustomItem huntsmenHelm = new CustomItem("Huntsmen Hat",3,true,false,"Fox fur cap",Material.IRON_HELMET,0.0,858.0);

        CustomItem frontierBoots = new CustomItem("Frontier Boots",5,false,false,"Steeled leather",Material.IRON_BOOTS,0.0,2156.0,Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        CustomItem frontierLegs = new CustomItem("Frontier Pants",6,false,false,"With knee pads!",Material.IRON_LEGGINGS,0.0,2772.0,Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        CustomItem frontierChest = new CustomItem("Frontier Duster",6,false,false,"Made with beast hide",Material.IRON_CHESTPLATE,0.0,3080.0,Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        CustomItem frontierHelm = new CustomItem("Frontier Hat",5,false,false,"Steeled leather hat",Material.IRON_HELMET,0.0,2002.0,Enchantment.PROTECTION_ENVIRONMENTAL, 1);

        CustomItem journeyBoots = new CustomItem("Journeymen Boots",7,false,false,"Silver spurs",Material.NETHERITE_BOOTS,0.0,0.0);
        CustomItem journeyLegs = new CustomItem("Journeymen Pants",8,false,false,"Plenty of pockets",Material.NETHERITE_LEGGINGS,0.0,0.0);
        CustomItem journeyChest = new CustomItem("Journeymen Duster",8,false,false,"Plated with steel",Material.NETHERITE_CHESTPLATE,0.0,0.0);
        CustomItem journeyHelm = new CustomItem("Journeymen Hat",7,false,false,"Soul of every Journeymen",Material.NETHERITE_HELMET,0.0,0.0);


        CustomItem sheriffBoots = new CustomItem("Sheriff Boots",3,true,true,"",Material.CHAINMAIL_BOOTS,0.0,924.0);
        CustomItem sheriffLegs = new CustomItem("Sheriff Pants",4,true,true,"",Material.CHAINMAIL_LEGGINGS,0.0,1188.0);
        CustomItem sheriffChest = new CustomItem("Sheriff Jacket",4,true,true,"",Material.CHAINMAIL_CHESTPLATE,0.0,1320.0);
        CustomItem sheriffHelm = new CustomItem("Sheriff Hat",3,true,true,"",Material.CHAINMAIL_HELMET,0.0,858.0);

        CustomItem deputyBoots = new CustomItem("Deputy Boots",3,true,true,"",Material.CHAINMAIL_BOOTS,0.0,2730.0,Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        CustomItem deputyLegs = new CustomItem("Deputy Pants",4,true,true,"",Material.CHAINMAIL_LEGGINGS,0.0,3510.0,Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        CustomItem deputyChest = new CustomItem("Deputy Jacket",4,true,true,"",Material.CHAINMAIL_CHESTPLATE,0.0,3900.0,Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        CustomItem deputyHelm = new CustomItem("Deputy Hat",3,true,true,"",Material.CHAINMAIL_HELMET,0.0,2335.0,Enchantment.PROTECTION_ENVIRONMENTAL, 2);

        CustomItem marshallBoots = new CustomItem("Marshall Boots",3,true,true,"Freshly shined",Material.DIAMOND_BOOTS,0.0,5950.0);
        CustomItem marshallLegs = new CustomItem("Marshall Pants",4,true,true,"",Material.DIAMOND_LEGGINGS,0.0,7650.0);
        CustomItem marshallChest = new CustomItem("Marshall Jacket",4,true,true,"Boys in blue!",Material.DIAMOND_CHESTPLATE,0.0,8500.0);
        CustomItem marshallHelm = new CustomItem("Marshall Hat",3,true,true,"The true officer.",Material.DIAMOND_HELMET,0.0,5525.0);

        CustomItem bow = new CustomItem("Flame Bow",8,false,false,"",Material.BOW,0.0,0.0,Enchantment.ARROW_FIRE,2);
        CustomItem bow2 = new CustomItem("Hard Bow",8,false,false,"",Material.BOW,0.0,0.0,Enchantment.ARROW_DAMAGE,6);



        System.out.println("custom items loaded");


    }
    public static void loadItemTables(){
        //add "Health Potions
        Quantity ones = new Quantity(1, 1);
        HashMap<String, Quantity> oilfield = new HashMap<>();
        oilfield.put("Glass Bottle", ones);
        oilfield.put("Charred Potato", ones);
        oilfield.put("Brown Stew", ones);
        oilfield.put("Splint", ones);
        oilfield.put("Bandage", ones);
        oilfield.put("Rifle Ammo", new Quantity(4, 8));
        oilfield.put("Shotgun Ammo", new Quantity(2, 4));
        ItemTable oilfield_table = new ItemTable(oilfield, "Oil Field");

        HashMap<String, Quantity> hunting_gr = new HashMap<>();
        hunting_gr.put("Shotgun Ammo", ones);
        hunting_gr.put("Rifle Ammo", ones);
        hunting_gr.put("Cooked Fox", ones);
        hunting_gr.put("Bandage", ones);
        hunting_gr.put("Splint", ones);
        hunting_gr.put("Glass Bottle", ones);
        hunting_gr.put("Boar Hide", ones);
        hunting_gr.put("Cow Hide", ones);

        ItemTable hunting_gr_table = new ItemTable(hunting_gr, "Hunting Grounds");

        //Add Huntsman's Boots/Hat
        HashMap<String, Quantity> mines = new HashMap<>();
        mines.put("Gold Ore", ones);
        mines.put("Iron Ore", ones);
        mines.put("Copper Ore", ones);
        mines.put("Charred Potato", ones);
        mines.put("Rabbit Stew", ones);
        mines.put("Splint", ones);
        mines.put("Old Miner's Pick", ones);
        mines.put("Mole's Breath Spores", ones);
        ItemTable mines_table = new ItemTable(mines, "Mines");

        //add farmhand set
        HashMap<String, Quantity> drugbase = new HashMap<>();
        drugbase.put("Glass Bottle", ones);
        drugbase.put("Rifle Ammo", ones);
        drugbase.put("Cooked Rabbit", ones);
        drugbase.put("Brown Stew", ones);
        drugbase.put("Bandage", ones);
        drugbase.put("Splint", ones);
        drugbase.put("Slug", ones);
        drugbase.put("Fermented Liquor", ones);
        ItemTable drug_base_table = new ItemTable(drugbase, "Drug Base");

        //add gold, huntsman's jacket/trousers, Winchester 1873, Colt Navy
        HashMap<String, Quantity> scavtown = new HashMap<>();
        scavtown.put("Slug", ones);
        scavtown.put("Shotgun Ammo", ones);
        scavtown.put("Pistol Ammo", ones);
        scavtown.put("Rifle Ammo", ones);
        scavtown.put("Rabbit Stew", ones);
        scavtown.put("Charred Potato", ones);
        scavtown.put("Bandage", ones);
        scavtown.put("Splint", ones);
        scavtown.put("Rabbit Hide", ones);
        scavtown.put("Fermented Liquor", ones);
        ItemTable scav_town_table = new ItemTable(scavtown, "Scavenger Town");


        //Crates
        //Journeyman's hat/duster, frontier set, sawed off, colt navy, 66 winchester, henry model 3, explorer's pick
        //green thumb brew
        HashMap<String, Quantity> oilfield_crate = new HashMap<>();
        ItemTable oilfield_create_table = new ItemTable(oilfield_crate, "Oil Field Crate");
        //add health potions, gold, colt patterson
        HashMap<String, Quantity> lt_milcrate = new HashMap<>();
        lt_milcrate.put("Shotgun Ammo", new Quantity(18, 28));
        lt_milcrate.put("Pistol Ammo", new Quantity(18, 28));
        lt_milcrate.put("Rifle Ammo", new Quantity(18, 28));
        lt_milcrate.put("Slugs", new Quantity(8, 18));
        lt_milcrate.put("Fermented Liquor", new Quantity(6, 15));
        lt_milcrate.put("Amethyst Bud", new Quantity(1, 5));
        lt_milcrate.put("Smoked Salmon", new Quantity(9, 19));
        lt_milcrate.put("Steel Lined Rod", ones);
        ItemTable lt_milcrate_table = new ItemTable(lt_milcrate, "Low-Tier Military Crate");

        //add all
        //journeyman pants/boots, sharps rifle, winchester 1873, frontier set, double spade brew
        String[] hightier_military_crate = {};
    }
    public static void loadLocations(IronSight plugin){
        System.out.println("Loading all locations");
        //Item tables

        //Locations

        FrontierLocation stormpoint = new FrontierLocation("Storm Point","Drug Base", LocationType.ILLEGAL, 26, -157, -2788, -3015);

        FrontierLocation northoil = new FrontierLocation("North Moraine Oil Field","Illegal area!",LocationType.ILLEGAL, 2827,3041,-2951,-3189);
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

        FrontierLocation prison = new FrontierLocation("Prison","JaiL!",LocationType.PRISON, 2079,1794,-799,-959,1987,94,-920);

        FrontierLocation blackspur = new FrontierLocation("Black Spur Mines","Be weary of the depths",LocationType.MINE,1542,2248,-2102,-1775);

        FrontierLocation sloughcreekR = new FrontierLocation("Slough Creek River","Fishings good",LocationType.RIVER, 2545,2698,38,1243);
        FrontierLocation pearlR = new FrontierLocation("Pearl River","Ice cold rapids!",LocationType.RIVER,2599,2083,-2596,-2475);
        FrontierLocation threeForks = new FrontierLocation("Three Forks Delta","A thick and nasty swamp",LocationType.RIVER,-1330,-1100,-2100,2955);
        FrontierLocation guadalupe = new FrontierLocation("Lower Guadalupe River", "Sunk into the canyon long ago",LocationType.RIVER,-1876,-1681,1160,341);

        FrontierLocation wilderness = new FrontierLocation("Wilderness", "Yeehaw", LocationType.WILDERNESS, 0, 0, 0, 0);
        System.out.println("Locations loaded");
    }
    public static void loadIcons()
    {
        System.out.println("loading all icons");
        //      ===--- RESPAWN UI ICONS ---===
        ItemIcon santafeRespawn = new ItemIcon("Santa Fe","santa_fe_respawn","Click to respawn here",Material.WHITE_BANNER);
        ItemIcon neworleansRespawn = new ItemIcon("New Orleans","new_orleans_respawn","Click to respawn here",Material.BLUE_BANNER);
        ItemIcon texasRespawn = new ItemIcon("Republic Of Texas", "republic_of_texas_respawn","Click to respawn here",Material.YELLOW_BANNER);

        //      ===--- CONTRACT UI ICONS ---===
        ItemIcon contractLoc = new ItemIcon(String.valueOf(ChatColor.BOLD)+ "Locations:","contract_location","Go here to complete.",Material.COMPASS);
        ItemIcon contractReq = new ItemIcon(String.valueOf(ChatColor.BOLD)+"Request:","contract_req","Requested items:",Material.DIAMOND);
        ItemIcon contractDesc = new ItemIcon(String.valueOf(ChatColor.BOLD)+"Description","contract_desc","What to do:",Material.MOJANG_BANNER_PATTERN);

        ItemIcon nextPage = new ItemIcon("Next Page","next_page","Click to go to next page",Material.MAGENTA_GLAZED_TERRACOTTA);
        new ItemIcon("Previous Page","previous_page","Click to go to previous page",Material.MAGENTA_GLAZED_TERRACOTTA);

        ItemIcon miner = new ItemIcon("Miner","miner_prefix","Choose miner (Level 3)",Material.STONE_PICKAXE);
        ItemIcon cowboy = new ItemIcon("Cowboy","cowboy_prefix","Choose cowboy (Level 4)", Material.HAY_BLOCK);
        ItemIcon tracker = new ItemIcon("Tracker","tracker_prefix", "Choose tracker (Level 5)",Material.LEATHER_BOOTS);
        ItemIcon medic = new ItemIcon("Medic","medic_prefix","Choose medic (Level 7)",Material.PAPER);
        ItemIcon raider = new ItemIcon("Raider","raider_prefix","Choose raider (Level 9)",Material.SKELETON_SKULL);
        ItemIcon explorer = new ItemIcon("Explorer","explorer_prefix","Choose explorer (Level 10)",Material.SPYGLASS);
        ItemIcon contractorTitle = new ItemIcon("Contractor Title","contractor_title","Select your contractor title",Material.SPRUCE_HANGING_SIGN);

        //      ===--- TRACKER UI ICONS ---===
        ItemIcon town = new ItemIcon("Towns", "town_tracker", "Find town", Material.DARK_OAK_HANGING_SIGN);
            ItemIcon santaFe = new ItemIcon("Santa Fe","santa_fe_tracker", "Directions to Santa Fe", Material.WHITE_BANNER);
            ItemIcon newOrleans = new ItemIcon("New Orleans","new_orleans_tracker", "Directions to New Orleans", Material.YELLOW_BANNER);
            ItemIcon texas = new ItemIcon("Republic of Texas","republic_of_texas_tracker", "Directions to the Republic of Texas", Material.BLUE_BANNER);
        ItemIcon player = new ItemIcon("Players", "player_tracker","Find players", Material.PLAYER_HEAD);
            ItemIcon wantedPlayer = new ItemIcon("Nearest Wanted Player","wanted_tracker", "Nearest wanted player", Material.ZOMBIE_HEAD);
        ItemIcon contracts = new ItemIcon("Contracts", "contract_tracker","Contract location", Material.BOOK);
            // contract icons ???
        //      ===--- TRACKER NPC ---===
        ItemIcon merchants = new ItemIcon("Merchants", "merchant_tracker", "Find Merchants", Material.BELL);
            ItemIcon fisherman = new ItemIcon("Fisherman","fisherman_tracker", "Find Fisherman", Material.FISHING_ROD);
            ItemIcon pharmacist = new ItemIcon("Pharmacist","pharmacist_tracker", "Find Pharmacist", Material.PAPER);
            ItemIcon armsDealer = new ItemIcon("Arms Dealer", "arms_dealer_tracker","Find Arms Dealer", Material.STONE_AXE);
            ItemIcon illegalArms = new ItemIcon("Illegal Arms Dealer","illegal_arms_tracker", "Illegal Arms Dealer", Material.IRON_AXE);
            ItemIcon armorer = new ItemIcon("Armorer", "armorer_tracker","Find Armorer", Material.LEATHER_CHESTPLATE);
            ItemIcon illegalArmor = new ItemIcon("Illegal Armorer","illegal_armorer_tracker", "Find Illegal Armorer", Material.NETHERITE_CHESTPLATE);
            ItemIcon generalStore = new ItemIcon("General Store","general_store_tracker", "Find General Store", Material.COOKED_BEEF);
            ItemIcon geologist = new ItemIcon("Geologist", "geologist_tracker","Find Geologist", Material.STONE);
            ItemIcon stableManager = new ItemIcon("Stable Manager", "stable_manager_tracker","Find Stable Manager", Material.SADDLE);
        ItemIcon npc =  new ItemIcon("NPCs", "npc_tracker","Find NPCs", Material.BOOKSHELF);
            ItemIcon conductor = new ItemIcon("Conductor", "conductor_tracker","Find Conductor", Material.RAIL);
            ItemIcon ferryCaptain = new ItemIcon("Ferry Captain","ferry_captain_tracker", "Find Ferry Captain", Material.BIRCH_BOAT);
            ItemIcon bankTeller = new ItemIcon("Bank Teller","bank_teller_tracker", "Find Bank Teller", Material.GOLD_INGOT);
            ItemIcon itemVault = new ItemIcon("Vault Manager","vault_manager_tracker", "Find Vault Manager", Material.DIAMOND);
            ItemIcon contractor = new ItemIcon("Contractor", "contractor_tracker","Find Contractor", Material.FILLED_MAP);
            ItemIcon chief = new ItemIcon("Chief of Police","chief_of_police_tracker", "Find Chief", Material.PIGLIN_HEAD);
        //      ===--- TRACKER LOCATION ---===
            ItemIcon locations = new ItemIcon("Locations","location_tracker", "Find locations", Material.COMPASS);

            ItemIcon mines = new ItemIcon("Mines","mine_tracker", "Find Mines", Material.IRON_PICKAXE);
                ItemIcon blackSpur = new ItemIcon("Black Spur Mines","black_spur_mines_tracker", "Find the Black Spur Mines", Material.IRON_ORE);
                ItemIcon barron = new ItemIcon("Barron's Canyon","barrons_canyon_tracker", "Find Barron's Canyon", Material.TERRACOTTA);
                ItemIcon halfDome = new ItemIcon("Half Dome Mines","half_dome_tracker", "Find the Half Dome Mines", Material.STONE);

                ItemIcon rivers = new ItemIcon("Rivers", "river_tracker","Find Rivers", Material.WATER_BUCKET);
                ItemIcon pearl = new ItemIcon("Pearl River","pearl_river_tracker", "Find Pearl River", Material.MUSIC_DISC_CAT);
                ItemIcon threeForks = new ItemIcon("Three Forks Delta", "three_forks_delta_tracker","Find Three Forks Delta", Material.MUSIC_DISC_CHIRP);
                ItemIcon lowerGuada = new ItemIcon("Lower Guadalupe River","lower_guadalupe_tracker", "Find Guadalupe River", Material.MUSIC_DISC_BLOCKS);
                ItemIcon slough = new ItemIcon("Slough Creek River","slough_creek_river_tracker", "Find Slough Creek", Material.MUSIC_DISC_FAR);

                ItemIcon forestReserves = new ItemIcon("Forest Reserves","forest_reserves_tracker", "Find Forest Reserves", Material.OAK_SAPLING);
                ItemIcon grizzly = new ItemIcon("Grizzly Ridge","grizzly_ridge_tracker", "Find Grizzly Ridge", Material.OAK_SAPLING);
                ItemIcon marston = new ItemIcon("Marston Glacier", "marston_glacier_tracker","Find Marston Glacier", Material.BIRCH_SAPLING);
                ItemIcon hawkRidge = new ItemIcon("Hawk Ridge Forest","hawk_ridge_forest_tracker", "Find Hawk Ridge Forest", Material.ACACIA_SAPLING);

                ItemIcon sentinel = new ItemIcon("Sentinel Rock","sentinel_rock_tracker", "Find Sentinel Rock", Material.QUARTZ);
            ItemIcon banditCamps = new ItemIcon("Bandit Camps", "bandit_camp_tracker","Find Bandit Camps", Material.SKELETON_SKULL);
                ItemIcon redAsh = new ItemIcon("Red Ash Camp", "red_ash_camp_tracker","Find Red Ash Camp", Material.REDSTONE);
                ItemIcon stormPoint = new ItemIcon("Storm Point Rebel Base","storm_point_tracker", "Find Storm Point", Material.ITEM_FRAME);
            ItemIcon scavTowns = new ItemIcon("Scav Towns","scav_town_tracker", "Find Scav Towns", Material.OAK_DOOR);
                ItemIcon florence = new ItemIcon("Florence Peak", "florence_peak_tracker", "Find Florence Peak", Material.ACACIA_DOOR);
                ItemIcon washington = new ItemIcon("Washington Column", "washington_column_tracker","Find Washington Column", Material.SPRUCE_DOOR);
                ItemIcon sierra = new ItemIcon("Sierra Gorge","sierra_gorge_tracker", "Find Sierra Gorge", Material.BIRCH_DOOR);
            ItemIcon oilFields = new ItemIcon("Oil Fields","oil_field_tracker", "Find Oil Fields", Material.BUCKET);
                ItemIcon northMoraine = new ItemIcon("North Moraine Oil Field","north_moraine_oil_field_tracker", "Find North Moraine Oil Field", Material.BUCKET);
            ItemIcon drugFields = new ItemIcon("Drug Fields","drug_field_tracker", "Find Drug Fields", Material.KELP);
                ItemIcon smokeLeaf = new ItemIcon("Smokeleaf Drug Field", "smokeleaf_field_tracker","Find Smokeleaf Drug Field", Material.KELP);

                new ItemIcon("Complete Contract","complete_contract","Click if all steps are complete", Material.EMERALD_BLOCK);
                //      ===--- BANKER ITEMS ---===
            ItemIcon deposit = new ItemIcon("Deposit", "bank_deposit", "Make a deposit", Material.EMERALD_BLOCK );
            ItemIcon withdraw = new ItemIcon("Withdraw", "bank_withdraw", "Make a withdrawal", Material.REDSTONE_BLOCK );


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

    public static void loadNPCs() {
        System.out.println("Loading NPCs");

        NPC shopkeeper = new NPC("Shopkeeper", NPCType.SHOPKEEPER, 964, 93, -1909, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Santa Fe"));
        NPC contractor = new NPC("Contractor", NPCType.CONTRACTOR, 1055, 94, -1957, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Santa Fe"));
        NPC banker = new NPC("Bank Teller", NPCType.BANKER, 918, 93, -1925, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Santa Fe"));

    }
}
