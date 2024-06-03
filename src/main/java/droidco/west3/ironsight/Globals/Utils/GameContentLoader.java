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
import droidco.west3.ironsight.Items.Looting.ItemTable;
import droidco.west3.ironsight.Items.Potions.BrewingRecipe;
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.FrontierLocation.LocationType;
import droidco.west3.ironsight.Items.Looting.Quantity;
import droidco.west3.ironsight.Items.Potions.CustomPotion;
import droidco.west3.ironsight.Processors.Processor;
import droidco.west3.ironsight.Processors.ProcessorCoordinate;
import droidco.west3.ironsight.Processors.ProcessorType;
import org.bukkit.ChatColor;
import droidco.west3.ironsight.NPC.NPC;
import droidco.west3.ironsight.NPC.NPCType;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GameContentLoader {
    public static void loadCustomItems() {
        System.out.println("Loading custom items");
        //COMMONS
        new CustomItem("Brown Stew", 1, true, false, "What's in this..?", Material.MUSHROOM_STEW, 0.0, 2.0, 1);
        new CustomItem("Charred Potato", 1, true, false, "Cooked on the coals.", Material.BAKED_POTATO, 0.0, 4.0, 4);
        new CustomItem("Cooked Fox", 1, true, false, "Bigger drumstick than chicken!", Material.COOKED_CHICKEN, 0.0, 5.0, 4);
        new CustomItem("Rabbit Stew", 2, true, false, "Delicious with bread", Material.RABBIT_STEW, 0.0, 3.0, 1);
        new CustomItem("Cooked Rabbit", 2, true, false, "Get's you through winter", Material.COOKED_RABBIT, 0.0, 5.0, 4);
        new CustomItem("Seaweed", 1, true, false, "Useless", Material.KELP_PLANT, 2.0, 0.0);
        new CustomItem("Reed", 1, true, false, "Useless", Material.BAMBOO, 2.0, 0.0);
        CustomItem brokenPick = new CustomItem("Broken Pick", 2, true, false, "A good starter pick", Material.STONE_PICKAXE, 0.0, 200.0, 1);
        CustomItem shotAmmo = new CustomItem("Shotgun Ammo", 2, true, false, "Buckshot only.", Material.WHEAT_SEEDS, 0.0, 20.0, 6);
        CustomItem pistolAmmo = new CustomItem("Pistol Ammo", 1, true, false, "Load your favorite .22", Material.NETHER_WART, 0.0, 17.0, 8);
        CustomItem rifleAmmo = new CustomItem("Rifle Ammo", 2, true, false, "Imported from China", Material.CLAY_BALL, 0.0, 24.0, 8);
        CustomItem bandage = new CustomItem("Bandage", 2, true, false, "Used to heal bloody wounds", Material.PAPER, 0.0, 9.0, 6);
        CustomItem splint = new CustomItem("Splint", 2, true, false, "Used to heal broken bones", Material.STICK, 0.0, 8.0, 4);
        CustomItem tracker = new CustomItem("Tracker", 1, true, false, "Track different locations", Material.COMPASS, 0.0, 10.0, 1);
        CustomItem glassBottle = new CustomItem("Glass Bottle", 2, true, false, "Used for brewing drinks", Material.GLASS_BOTTLE, 0.0, 15.0, 3);
        CustomItem fishingRod = new CustomItem("Wooden Fishing Rod", 2, true, false, "Basic stick and line", Material.FISHING_ROD, 0.0, 200.0, 1);
        CustomItem unSmokeLeaf = new CustomItem("Unprocessed Smokeleaf", 2, false, false, "Process to consume", Material.GREEN_DYE, 0.0, 0.0);
        CustomItem unSpice = new CustomItem("Unprocessed Spice", 2, false, false, "Process to consume", Material.GLOWSTONE_DUST, 0.0, 0.0);
        CustomItem iron = new CustomItem("Iron Ore", 2, true, false, "Can be refined or sold", Material.RAW_IRON, 10.0, 0.0);
        CustomItem copper = new CustomItem("Copper Ore", 1, true, false, "Can be refined or sold", Material.RAW_COPPER, 14.0, 0.0);

        //UNCOMMON
        CustomItem smokedSalmon = new CustomItem("Smoked Salmon", 3, true, false, "Fresh caught, fresh smoked", Material.COOKED_SALMON, 0.0, 10.0, 3);
        CustomItem fermentedLiquor = new CustomItem("Fermented Liquor", 4, true, false, "Extra kick to any home brew", Material.DRAGON_BREATH, 16.0, 0.0, 2);
        CustomItem crappie = new CustomItem("Poor Mans Crappie", 3, true, false, "Skinniest fish", Material.COD, 10.0, 0.0);
        CustomItem grayHerring = new CustomItem("Gray Stoned Herring", 3, true, false, "The cheapest of Herring", Material.SALMON, 13.0, 0.0);
        CustomItem chub = new CustomItem("Cactus Pronged Chub", 3, true, false, "Too spikey to eat", Material.TROPICAL_FISH, 16.0, 0.0);
        CustomItem boarCarcass = new CustomItem("Boar Carcass", 3, true, false, "Right click to skin", Material.MUSIC_DISC_MALL, 55.0, 0.0);
        CustomItem cowCarcass = new CustomItem("Cow Carcass", 3, true, false, "Right click to skin", Material.MUSIC_DISC_STRAD, 40.0, 0.0);
        CustomItem gold = new CustomItem("Gold Ore", 3, true, false, "Can be refined or sold", Material.RAW_GOLD, 19.0, 0.0);
        CustomItem slug = new CustomItem("Sea Slug", 3, true, false, "Reaks of the sea", Material.SPIDER_EYE, 4.0, 8.0, 4);
        CustomItem boarmeat = new CustomItem("Boar Meat", 3, true, false, "Closest thing to bacon", Material.PORKCHOP, 7.0, 0.0);
        CustomItem cowmeat = new CustomItem("Cow Meat", 3, true, false, "Prime beef", Material.BEEF, 9.0, 0.0);
        CustomItem rabbitmeat = new CustomItem("Rabbit Meat", 3, true, false, "Not too gamey", Material.RABBIT, 6.0, 0.0);
        CustomItem foxmeat = new CustomItem("Fox Meat", 4, true, false, "Better than chicken!", Material.CHICKEN, 4.0, 0.0);
        CustomItem boarhide = new CustomItem("Boar Hide", 4, true, false, "Extermely tough hide", Material.RABBIT_HIDE, 10.0, 0.0);
        CustomItem cowhide = new CustomItem("Cow Hide", 4, true, false, "Good for boots", Material.LEATHER, 13.0, 0.0);
        CustomItem rabbithide = new CustomItem("Rabbit Hide", 4, true, false, "Good for small leather work", Material.LIGHT_GRAY_DYE, 18.0, 0.0);
        CustomItem foxfur = new CustomItem("Fox Fur", 4, true, false, "Warmest fur on the market", Material.ORANGE_DYE, 16.0, 0.0);
        CustomItem geode = new CustomItem("Geode", 4, true, false, "Bring to geologist to open", Material.FIREWORK_STAR, 0.0, 0.0);
        CustomItem minerspick = new CustomItem("Old Miner's Pick", 4, true, false, "Steeled with rust", Material.IRON_PICKAXE, 0.0, 530.0, 1);
        CustomItem procsmokeleaf = new CustomItem("Processed Smokeleaf", 4, false, false, "Process to consume", Material.ENDER_PEARL, 19.0, 0.0);
        CustomItem spicebottle = new CustomItem("Spice", 4, false, false, "Smell's like the future", Material.HONEY_BOTTLE, 68.0, 0.0);
        CustomItem oil = new CustomItem("Unrefined Oil", 4, false, false, "Refine for higher sale value", Material.BUCKET, 0.0, 0.0);
        CustomItem frenzyrecipe = new CustomItem("Miner's Frenzy Brew Recipe", 3, true, false, "Right click to view recipe", Material.FLOWER_BANNER_PATTERN, 1500.0, 0.0, 1);
        CustomItem doublespaderecipe = new CustomItem("Miner's Double Spade Brew Recipe", 3, true, false, "Right click to view recipe", Material.FLOWER_BANNER_PATTERN, 1500.0, 0.0, 1);
        CustomItem greenthumbrecipe = new CustomItem("Green Thumb Brew Recipe", 3, true, false, "Right click to view recipe", Material.FLOWER_BANNER_PATTERN, 1500.0, 0.0, 1);
        CustomItem fermentliquorrecipe = new CustomItem("Fermented Liquor Recipe", 3, true, false, "Right click to view recipe", Material.FLOWER_BANNER_PATTERN, 225.0, 0.0, 1);

        //RARE ITEMS
        new CustomItem("Life Seeds", 7, true, false, "Brews into a slow heal", Material.PUMPKIN_SEEDS, 0.0, 0.0, 1);
        CustomItem frenziedStems = new CustomItem("Frenzied Stems", 5, true, false, "Used on workers for productivity", Material.FIRE_CORAL, 0.0, 0.0, 1);
        CustomItem heartFruit = new CustomItem("Heart Fruit", 5, true, false, "Still beating", Material.SWEET_BERRIES, 0.0, 0.0, 1);

        CustomItem amethystBud = new CustomItem("Amethyst Bud", 6, true, false, "Like a blossoming rose", Material.AMETHYST_SHARD, 85.0, 0.0, 1);
        CustomItem mossyJade = new CustomItem("Mossy Jade", 5, true, false, "Look's alive under light", Material.SLIME_BALL, 65.0, 0.0, 1);

        CustomItem hermitCrab = new CustomItem("Hermit Crab", 5, true, false, "Fish love it", Material.NAUTILUS_SHELL, 8.0, 15.0, 3);
        CustomItem refinedOil = new CustomItem("Refined Oil", 6, false, false, "Highly sought after", Material.LAVA_BUCKET, 90.0, 0.0, 1);
        CustomItem southernsalmon = new CustomItem("Southern Salmon", 6, true, false, "Migrated south through southern tributaries", Material.MUSIC_DISC_FAR, 175.0, 0.0, 1);
        CustomItem arcticsalmon = new CustomItem("Arctic Salmon", 6, true, false, "Still in the frozen north", Material.MUSIC_DISC_13, 180.0, 0.0, 1);

        CustomItem oilfieldkey = new CustomItem("Crate Key", 6, false, false, "Unlocks crates at oil field", Material.TRIPWIRE_HOOK, 0.0, 0.0, 1);
        CustomItem steelLinedRod = new CustomItem("Steel Lined Rod", 6, true, false, "Double sided hook!", Material.FISHING_ROD, 0.0, 1950.0, 1, Enchantment.LURE, 1);
        CustomItem explorerspick = new CustomItem("Explorer's Pick", 6, true, false, "Aids in climbing glaciers", Material.DIAMOND_PICKAXE, 0.0, 1450.0, 1, Enchantment.DIG_SPEED, 1);

        //LEGENDARY
        CustomItem alligator = new CustomItem("Alligator", 8, true, false, "Crikey!", Material.MUSIC_DISC_CHIRP, 425.0, 0.0, 1);
        CustomItem sunkenCatfish = new CustomItem("Sunken Catfish", 8, true, false, "Evolved in the low-lands", Material.MUSIC_DISC_BLOCKS, 400.0, 0.0, 1);
        CustomItem goldstonnedherring = new CustomItem("Gold Stoned Herring", 7, true, false, "The rare mutation of the gray herring", Material.MUSIC_DISC_MELLOHI, 250.0, 0.0, 1);
        CustomItem pearlrivertrout = new CustomItem("Pearl River Trout", 7, true, false, "Alluring rainbow scales", Material.MUSIC_DISC_CAT, 275.0, 0.0, 1);
        CustomItem expaditionRod = new CustomItem("Expedition Rod", 8, true, false, "A proper line.", Material.FISHING_ROD, 0.0, 5950.0, 1, Enchantment.LURE, 5);

            new CustomItem("Pirates booty", 8, true, false, "Richest dabloon", Material.SUNFLOWER, 0.0, 0.0, 1);
        new CustomItem("Oil Barrel", 7, false, false, "A large amount of refined oil", Material.WATER_BUCKET, 650.0, 0.0, 1);
        new CustomItem("Golden Gamble Petal", 7, true, false, "Said to be lucky!", Material.HONEYCOMB, 0.0, 0.0, 1);
        new CustomItem("Moles Breath Spores", 7, true, false, "Moles use it to dig quicker", Material.FROGSPAWN, 0.0, 0.0, 1);
        new CustomItem("Blue Malt Petal", 7, true, false, "It smell makes dreams come true", Material.GLOW_INK_SAC, 0.0, 0.0, 1);

        new CustomItem("Crystalized Geode", 8, true, false, "Crystals explode out of the sides", Material.MUSIC_DISC_11, 0.0, 0.0, 1);
        new CustomItem("River Diamond", 7, true, false, "The root of many wars", Material.DIAMOND, 555.0, 0.0, 1);
        new CustomItem("Baron's Emerald", 7, true, false, "An old king was fond of these", Material.EMERALD, 575.0, 0.0, 1);
        new CustomItem("Void Opal", 8, true, false, "Stare into the depths of the universe", Material.ECHO_SHARD, 635.0, 0.0, 1);

        //ARMOR
        new CustomItem("Farm Hand Boots", 1, true, false, "'Size too big", Material.LEATHER_BOOTS, 0.0, 350.0, 1);
        new CustomItem("Farm Hand Chaps", 2, true, false, "Stained with mud", Material.LEATHER_LEGGINGS, 0.0, 450.0, 1);
        new CustomItem("Farm Hand Shirt", 2, true, false, "Couple 'a holes", Material.LEATHER_CHESTPLATE, 0.0, 500.0, 1);
        new CustomItem("Farm Hand Hat", 1, true, false, "Keeps the sun away", Material.LEATHER_HELMET, 0.0, 325.0, 1);

        new CustomItem("Huntsmen Boots", 3, true, false, "Rusty Spurs", Material.IRON_BOOTS, 0.0, 924.0, 1);
        new CustomItem("Huntsmen Trousers", 4, true, false, "Mostly fur", Material.IRON_LEGGINGS, 0.0, 1188.0, 1);
        new CustomItem("Huntsmen Jacket", 4, true, false, "Made with boar hide", Material.IRON_CHESTPLATE, 0.0, 1320.0, 1);
        new CustomItem("Huntsmen Hat", 3, true, false, "Fox fur cap", Material.IRON_HELMET, 0.0, 858.0, 1);

        new CustomItem("Frontier Boots", 5, false, false, "Steeled leather", Material.IRON_BOOTS, 0.0, 2156.0, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        new CustomItem("Frontier Pants", 6, false, false, "With knee pads!", Material.IRON_LEGGINGS, 0.0, 2772.0, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        new CustomItem("Frontier Duster", 6, false, false, "Made with beast hide", Material.IRON_CHESTPLATE, 0.0, 3080.0, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        new CustomItem("Frontier Hat", 5, false, false, "Steeled leather hat", Material.IRON_HELMET, 0.0, 2002.0, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 1);

        new CustomItem("Journeymen Boots", 7, false, false, "Silver spurs", Material.NETHERITE_BOOTS, 0.0, 3000.0, 1);
        new CustomItem("Journeymen Pants", 8, false, false, "Plenty of pockets", Material.NETHERITE_LEGGINGS, 0.0, 5000.0, 1);
        new CustomItem("Journeymen Duster", 8, false, false, "Plated with steel", Material.NETHERITE_CHESTPLATE, 0.0, 5000.0, 1);
        new CustomItem("Journeymen Hat", 7, false, false, "Soul of every Journeymen", Material.NETHERITE_HELMET, 0.0, 3000.0, 1);


        new CustomItem("Sheriff Boots", 3, true, true, "", Material.CHAINMAIL_BOOTS, 0.0, 924.0, 1);
        new CustomItem("Sheriff Pants", 4, true, true, "", Material.CHAINMAIL_LEGGINGS, 0.0, 1188.0, 1);
        new CustomItem("Sheriff Jacket", 4, true, true, "", Material.CHAINMAIL_CHESTPLATE, 0.0, 1320.0, 1);
        new CustomItem("Sheriff Hat", 3, true, true, "", Material.CHAINMAIL_HELMET, 0.0, 858.0, 1);

        new CustomItem("Deputy Boots", 3, true, true, "", Material.CHAINMAIL_BOOTS, 0.0, 2730.0, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        new CustomItem("Deputy Pants", 4, true, true, "", Material.CHAINMAIL_LEGGINGS, 0.0, 3510.0, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        new CustomItem("Deputy Jacket", 4, true, true, "", Material.CHAINMAIL_CHESTPLATE, 0.0, 3900.0, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        new CustomItem("Deputy Hat", 3, true, true, "", Material.CHAINMAIL_HELMET, 0.0, 2335.0, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 2);

        new CustomItem("Marshall Boots", 3, true, true, "Freshly shined", Material.DIAMOND_BOOTS, 0.0, 5950.0, 1);
        new CustomItem("Marshall Pants", 4, true, true, "", Material.DIAMOND_LEGGINGS, 0.0, 7650.0, 1);
        new CustomItem("Marshall Jacket", 4, true, true, "Boys in blue!", Material.DIAMOND_CHESTPLATE, 0.0, 8500.0, 1);
        new CustomItem("Marshall Hat", 3, true, true, "The true officer.", Material.DIAMOND_HELMET, 0.0, 5525.0, 1);

        new CustomItem("Flame Bow", 8, false, false, "", Material.BOW, 0.0, 0.0, 1, Enchantment.ARROW_FIRE, 2);
        new CustomItem("Hard Bow", 8, false, false, "", Material.BOW, 0.0, 0.0, 1, Enchantment.ARROW_DAMAGE, 6);

        new CustomItem("Colt Patterson", 2, true, false, "", Material.STONE_SHOVEL, 0.0, 650.0, 1);
        new CustomItem("Sharps Rifle", 7, true, false, "", Material.STONE_AXE, 0.0, 2450.0, 1);
        new CustomItem("66 Winchester", 5, true, false, "", Material.DIAMOND_HOE, 0.0, 1800.0, 1);
        new CustomItem("Henry Model 3", 5, true, false, "", Material.STONE_HOE, 0.0, 2800.0, 1);

        new CustomItem("Winchester 1873", 6, false, false, "", Material.IRON_AXE, 0.0, 2950.0, 1);
        new CustomItem("Colt Navy",4,false,false,"",Material.IRON_SHOVEL,0.0,1780,1);
        new CustomItem("Sawed-Off Shotgun",8,false,false,"",Material.IRON_HOE,0.0,3650,1);
        new CustomItem("Navy 1851 OKH",7,false,false,"",Material.DIAMOND_SHOVEL,0.0,3650,1);

        System.out.println("custom items loaded");

        new CustomItem("Standard", 1, true, false, "Get's you where you need", Material.HORSE_SPAWN_EGG, 0.0, 2100.0, 1);
        new CustomItem("Thoroughbred", 1, true, false, "Fastest steed you can find", Material.HORSE_SPAWN_EGG, 0.0, 35250.0, 1);
        new CustomItem("Donkey", 1, true, false, "Slow but lots of storage", Material.DONKEY_SPAWN_EGG, 0.0, 48500.0, 1);
    }

    public static void loadItemTables() {
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
        mines.put("Moles Breath Spores", ones);
        ItemTable mines_table = new ItemTable(mines, "Mines");

        //add farmhand set
        HashMap<String, Quantity> drugbase = new HashMap<>();
        drugbase.put("Glass Bottle", ones);
        oilfield.put("Rifle Ammo", new Quantity(4, 8));
        oilfield.put("Shotgun Ammo", new Quantity(2, 4));
        oilfield.put("Pistol Ammo", new Quantity(4, 8));
        drugbase.put("Cooked Rabbit", ones);
        drugbase.put("Brown Stew", ones);
        drugbase.put("Bandage", ones);
        drugbase.put("Splint", ones);
        drugbase.put("Sea Slug", ones);
        drugbase.put("Fermented Liquor", ones);
        ItemTable drug_base_table = new ItemTable(drugbase, "Drug Base");

        //add gold, huntsman's jacket/trousers, Winchester 1873, Colt Navy
        HashMap<String, Quantity> scavtown = new HashMap<>();
        scavtown.put("Sea Slug", ones);
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
        lt_milcrate.put("Sea Slug", new Quantity(8, 18));
        lt_milcrate.put("Fermented Liquor", new Quantity(6, 15));
        lt_milcrate.put("Amethyst Bud", new Quantity(1, 5));
        lt_milcrate.put("Smoked Salmon", new Quantity(9, 19));
        lt_milcrate.put("Steel Lined Rod", ones);
        ItemTable lt_milcrate_table = new ItemTable(lt_milcrate, "Low-Tier Military Crate");

        //add all
        //journeyman pants/boots, sharps rifle, winchester 1873, frontier set, double spade brew
        String[] hightier_military_crate = {};

        //      ===--- GEODES ---===
        HashMap<String, Quantity> geodeMap = new HashMap<>();
        geodeMap.put("Amethyst Bud", ones);
        geodeMap.put("Mossy Jade", ones);
        geodeMap.put("Baron's Emerald", ones);
        geodeMap.put("Iron Ore", new Quantity(1, 8));
        geodeMap.put("Copper Ore", new Quantity(1, 8));
        geodeMap.put("Gold Ore", new Quantity(1, 8));

        ItemTable geodeTable = new ItemTable(geodeMap, "Geode");

        //      ===--- FISH TABLES ---===

        //LOWER GUADLUPE
        HashMap<String, Quantity> guadalupeMap = new HashMap<>();
        guadalupeMap.put("Reed", ones);
        guadalupeMap.put("Seaweed", ones);
        guadalupeMap.put("Cactus Pronged Chub", ones);
        guadalupeMap.put("Poor Mans Crappie", ones);
        guadalupeMap.put("Gray Stoned Herring", ones);
        guadalupeMap.put("Sunken Catfish", ones);

        ItemTable guadalupeTable = new ItemTable(guadalupeMap, "Guadalupe Fish");

        //THREE FORKS DELTA
        HashMap<String, Quantity> deltaMap = new HashMap<>();
        deltaMap.put("Reed", ones);
        deltaMap.put("Seaweed", ones);
        deltaMap.put("Cactus Pronged Chub", ones);
        deltaMap.put("Poor Mans Crappie", ones);
        deltaMap.put("Gray Stoned Herring", ones);
        deltaMap.put("Alligator", ones);

        ItemTable deltaTable = new ItemTable(deltaMap, "Three Forks Fish");

        //PEARL RIVER
        HashMap<String, Quantity> pearlMap = new HashMap<>();
        pearlMap.put("Reed", ones);
        pearlMap.put("Seaweed", ones);
        pearlMap.put("Cactus Pronged Chub", ones);
        pearlMap.put("Poor Mans Crappie", ones);
        pearlMap.put("Gray Stoned Herring", ones);
        pearlMap.put("Arctic Salmon", ones);
        pearlMap.put("Pearl River Trout", ones);

        ItemTable pearlTable = new ItemTable(pearlMap, "Pearl River Fish");

        //SLOUGH CREEK
        HashMap<String, Quantity> sloughMap = new HashMap<>();
        sloughMap.put("Reed", ones);
        sloughMap.put("Seaweed", ones);
        sloughMap.put("Cactus Pronged Chub", ones);
        sloughMap.put("Poor Mans Crappie", ones);
        sloughMap.put("Gray Stoned Herring", ones);
        sloughMap.put("Southern Salmon", ones);
        sloughMap.put("Gold Stoned Herring", ones);

        ItemTable sloughTable = new ItemTable(sloughMap, "Slough Creek Fish");
        //GLOBAL FISH
        HashMap<String, Quantity> globalFishMap = new HashMap<>();
        globalFishMap.put("Reed", ones);
        globalFishMap.put("Seaweed", ones);
        globalFishMap.put("Cactus Pronged Chub", ones);
        globalFishMap.put("Poor Mans Crappie", ones);
        globalFishMap.put("Gray Stoned Herring", ones);

        new ItemTable(globalFishMap, "Global Fish");

    }

    public static void loadLocations(IronSight plugin) {
        System.out.println("Loading all locations");

        //Locations

        new FrontierLocation("Storm Point", "Drug Base", LocationType.ILLEGAL, 26, -157, -2788, -3015);
        new FrontierLocation("Red Ash Camp", "Drug Base", LocationType.ILLEGAL, -1256, -1429, 523, 686);

        FrontierLocation northoil = new FrontierLocation("North Moraine Oil Field", "Illegal area!", LocationType.ILLEGAL, 2827, 3041, -2951, -3189);
        FrontierLocation southoil = new FrontierLocation("South Oil Field", "Illegal area!", LocationType.ILLEGAL, 778, 602, 1480, 1720);

        new OilFieldCrate(1, northoil, 2857, 101, -3048);
        new OilFieldCrate(2, northoil, 2911, 101, -3037);
        new OilFieldCrate(3, northoil, 2924, 101, -3083);
        new OilFieldCrate(4, northoil, 2936, 101, -2988);
        new OilFieldCrate(5, northoil, 2959, 101, -3037);
        new OilFieldCrate(6, northoil, 2992, 101, -3087);
        new OilFieldCrate(7, northoil, 2984, 101, -3154);

        new OilFieldTask(plugin, northoil);
        //OilFieldTask oil2 = new OilFieldTask(southoil);
        //TOWNS
        new FrontierLocation("New Orleans", "PvP disabled!", LocationType.TOWN, -1181, -1407, -1842, -1662, -1367.0, 92.0, -1764.0);
        new FrontierLocation("Santa Fe", "PvP Disabled", LocationType.TOWN, 1119, 888, -1755, -2066, 1055.0, 94.0, -1955.0);
        new FrontierLocation("Republic of Texas", "PvP Disabled", LocationType.TOWN, -1197, -831, 2628, 2214, -1030.0, 72.0, 2520.0);
        // MISC
        new FrontierLocation("Slough Creek", "Scav Town", LocationType.ILLEGAL, 2589, 2835, 799, 471);
        new FrontierLocation("Prison", "JaiL!", LocationType.PRISON, 2079, 1794, -799, -959, 1987, 94, -920);
        new FrontierLocation("Wilderness", "Yeehaw", LocationType.WILDERNESS, 0, 0, 0, 0);
        new FrontierLocation("Sierra Gorge Camp", "Scavenger Town", LocationType.EVENT, 2834, 2543, 819, 466);
        // MINES
        new FrontierLocation("Black Spur Mines", "Be weary of the depths", LocationType.MINE, 1542, 2248, -2102, -1775);
        // RIVERSS
        new FrontierLocation("Slough Creek River", "Fishings good", LocationType.RIVER, 2542, 2766, 445, 69);
        new FrontierLocation("Pearl River", "Ice cold rapids!", LocationType.RIVER, 2599, 2083, -2596, -2475);
        new FrontierLocation("Three Forks Delta", "A thick and nasty swamp", LocationType.RIVER, -1402, -1161, -2967, -2044);
        new FrontierLocation("Lower Guadalupe River", "Sunk into the canyon long ago", LocationType.RIVER, -1847, -1740, 1185, 291);
        // FIELDS
        new FrontierLocation("Smokeleaf Field", "Grown in damp ground", LocationType.ILLEGAL, 1014, 947, -2857, -2778);
        new FrontierLocation("Spice Field", "Harvested spice burns the earth", LocationType.ILLEGAL, -1550, -1447, 636, 725);
        System.out.println("Locations loaded");
    }

    public static void loadIcons() {
        System.out.println("loading all icons");
        //      ===--- RESPAWN UI ICONS ---===
        ItemIcon santafeRespawn = new ItemIcon("Santa Fe", "santa_fe_respawn", "Click to respawn here", Material.WHITE_BANNER);
        ItemIcon neworleansRespawn = new ItemIcon("New Orleans", "new_orleans_respawn", "Click to respawn here", Material.BLUE_BANNER);
        ItemIcon texasRespawn = new ItemIcon("Republic Of Texas", "republic_of_texas_respawn", "Click to respawn here", Material.YELLOW_BANNER);

        //      ===--- CONTRACT UI ICONS ---===
        ItemIcon contractLoc = new ItemIcon(String.valueOf(ChatColor.BOLD) + "Locations:", "contract_location", "Go here to complete.", Material.COMPASS);
        ItemIcon contractReq = new ItemIcon(String.valueOf(ChatColor.BOLD) + "Request:", "contract_req", "Requested items:", Material.DIAMOND);
        ItemIcon contractDesc = new ItemIcon(String.valueOf(ChatColor.BOLD) + "Description", "contract_desc", "What to do:", Material.MOJANG_BANNER_PATTERN);

        ItemIcon nextPage = new ItemIcon("Next Page", "next_page", "Click to go to next page", Material.MAGENTA_GLAZED_TERRACOTTA);
        new ItemIcon("Previous Page", "previous_page", "Click to go to previous page", Material.MAGENTA_GLAZED_TERRACOTTA);


        ItemIcon miner = new ItemIcon("Miner", "miner_prefix", "Choose miner (Level 3)", Material.STONE_PICKAXE);
        ItemIcon cowboy = new ItemIcon("Cowboy", "cowboy_prefix", "Choose cowboy (Level 4)", Material.HAY_BLOCK);
        ItemIcon tracker = new ItemIcon("Tracker", "tracker_prefix", "Choose tracker (Level 5)", Material.LEATHER_BOOTS);
        ItemIcon medic = new ItemIcon("Medic", "medic_prefix", "Choose medic (Level 7)", Material.PAPER);
        ItemIcon raider = new ItemIcon("Raider", "raider_prefix", "Choose raider (Level 9)", Material.SKELETON_SKULL);
        ItemIcon explorer = new ItemIcon("Explorer", "explorer_prefix", "Choose explorer (Level 10)", Material.SPYGLASS);
        ItemIcon contractorTitle = new ItemIcon("Contractor Title", "contractor_title", "Select your prefix", Material.SPRUCE_HANGING_SIGN);
        new ItemIcon("Remove Title", "remove_title", "Click to reset prefix", Material.BARRIER);

        new ItemIcon(String.valueOf(ChatColor.GREEN)+ "Rookie Difficulty","rookie_slot","",Material.PLAYER_HEAD);
        new ItemIcon(String.valueOf(ChatColor.YELLOW)+"Apprentice Difficulty","apprentice_slot","",Material.SKELETON_SKULL);
        new ItemIcon(String.valueOf(ChatColor.RED)+"Experienced Difficulty","experienced_slot","",Material.WITHER_SKELETON_SKULL);

        //      ===--- TRACKER UI ICONS ---===
        new ItemIcon("Spice Field", "spice_field_tracker", "Directions to Spice Field", Material.HONEY_BOTTLE);
        new ItemIcon("Towns", "town_tracker", "Find town", Material.DARK_OAK_HANGING_SIGN);
        new ItemIcon("Santa Fe", "santa_fe_tracker", "Directions to Santa Fe", Material.WHITE_BANNER);
        new ItemIcon("New Orleans", "new_orleans_tracker", "Directions to New Orleans", Material.YELLOW_BANNER);
        new ItemIcon("Republic of Texas", "republic_of_texas_tracker", "Directions to the Republic of Texas", Material.BLUE_BANNER);
        new ItemIcon("Players", "player_tracker", "Find players", Material.PLAYER_HEAD);
        new ItemIcon("Nearest Wanted Player", "wanted_tracker", "Nearest wanted player", Material.ZOMBIE_HEAD);
        new ItemIcon("Contracts", "contract_tracker", "Contract location", Material.BOOK);
        // contract icons ???
        //      ===--- TRACKER NPC ---===
        new ItemIcon("Merchants", "merchant_tracker", "Find Merchants", Material.BELL);
        new ItemIcon("Fisherman", "fisherman_tracker", "Find Fisherman", Material.FISHING_ROD);
        new ItemIcon("Pharmacist", "pharmacist_tracker", "Find Pharmacist", Material.PAPER);
        new ItemIcon("Arms Dealer", "arms_dealer_tracker", "Find Arms Dealer", Material.STONE_AXE);
        new ItemIcon("Illegal Arms Dealer", "illegal_arms_tracker", "Illegal Arms Dealer", Material.IRON_AXE);
        ItemIcon armorer = new ItemIcon("Armorer", "armorer_tracker", "Find Armorer", Material.LEATHER_CHESTPLATE);
        ItemIcon illegalArmor = new ItemIcon("Illegal Armorer", "illegal_armorer_tracker", "Find Illegal Armorer", Material.NETHERITE_CHESTPLATE);
        ItemIcon generalStore = new ItemIcon("Shopkeeper", "shopkeeper_tracker", "Find Shopkeeper", Material.COOKED_BEEF);
        ItemIcon geologist = new ItemIcon("Geologist", "geologist_tracker", "Find Geologist", Material.STONE);
        ItemIcon stableManager = new ItemIcon("Stable Manager", "stable_manager_tracker", "Find Stable Manager", Material.SADDLE);
        ItemIcon npc = new ItemIcon("NPCs", "npc_tracker", "Find NPCs", Material.BOOKSHELF);
        ItemIcon conductor = new ItemIcon("Conductor", "conductor_tracker", "Find Conductor", Material.RAIL);
        ItemIcon ferryCaptain = new ItemIcon("Ferry Captain", "ferry_captain_tracker", "Find Ferry Captain", Material.BIRCH_BOAT);
        ItemIcon bankTeller = new ItemIcon("Bank Teller", "bank_teller_tracker", "Find Bank Teller", Material.GOLD_INGOT);
        ItemIcon itemVault = new ItemIcon("Vault Keeper", "vault_keeper_tracker", "Find Vault Keeper", Material.ENDER_CHEST);
        ItemIcon contractor = new ItemIcon("Contractor", "contractor_tracker", "Find Contractor", Material.BOOK);
        ItemIcon chief = new ItemIcon("Chief of Police", "chief_of_police_tracker", "Find the Chief", Material.PIGLIN_HEAD);

        //      ===--- TRACKER LOCATION ---===
        new ItemIcon("Locations", "location_tracker", "Find locations", Material.COMPASS);

        new ItemIcon("Mines", "mine_tracker", "Find Mines", Material.IRON_PICKAXE);
        new ItemIcon("Black Spur Mines", "black_spur_mines_tracker", "Find the Black Spur Mines", Material.IRON_ORE);
        new ItemIcon("Barron's Canyon", "barrons_canyon_tracker", "Find Barron's Canyon", Material.TERRACOTTA);
        new ItemIcon("Half Dome Mines", "half_dome_tracker", "Find the Half Dome Mines", Material.STONE);

        new ItemIcon("Rivers", "river_tracker", "Find Rivers", Material.WATER_BUCKET);
        new ItemIcon("Pearl River", "pearl_river_tracker", "Find Pearl River", Material.MUSIC_DISC_CAT);
        new ItemIcon("Three Forks Delta", "three_forks_delta_tracker", "Find Three Forks Delta", Material.MUSIC_DISC_CHIRP);
        new ItemIcon("Lower Guadalupe River", "lower_guadalupe_tracker", "Find Guadalupe River", Material.MUSIC_DISC_BLOCKS);
        new ItemIcon("Slough Creek River", "slough_creek_river_tracker", "Find Slough Creek", Material.MUSIC_DISC_FAR);

        new ItemIcon("Forest Reserves", "forest_reserves_tracker", "Find Forest Reserves", Material.OAK_SAPLING);
        new ItemIcon("Grizzly Ridge", "grizzly_ridge_tracker", "Find Grizzly Ridge", Material.OAK_SAPLING);
        new ItemIcon("Marston Glacier", "marston_glacier_tracker", "Find Marston Glacier", Material.BIRCH_SAPLING);
        new ItemIcon("Hawk Ridge Forest", "hawk_ridge_forest_tracker", "Find Hawk Ridge Forest", Material.ACACIA_SAPLING);

        new ItemIcon("Sentinel Rock", "sentinel_rock_tracker", "Find Sentinel Rock", Material.QUARTZ);
        new ItemIcon("Bandit Camps", "bandit_camp_tracker", "Find Bandit Camps", Material.SKELETON_SKULL);
        new ItemIcon("Red Ash Camp", "red_ash_camp_tracker", "Find Red Ash Camp", Material.REDSTONE);
        new ItemIcon("Storm Point Rebel Base", "storm_point_tracker", "Find Storm Point", Material.WITHER_SKELETON_SKULL);
        new ItemIcon("Scav Towns", "scav_town_tracker", "Find Scav Towns", Material.OAK_DOOR);
        new ItemIcon("Florence Peak", "florence_peak_tracker", "Find Florence Peak", Material.ACACIA_DOOR);
        new ItemIcon("Washington Column", "washington_column_tracker", "Find Washington Column", Material.SPRUCE_DOOR);
        new ItemIcon("Sierra Gorge", "sierra_gorge_tracker", "Find Sierra Gorge", Material.BIRCH_DOOR);
        new ItemIcon("Oil Fields", "oil_field_tracker", "Find Oil Fields", Material.COAL);
        new ItemIcon("North Moraine Oil Field", "north_moraine_oil_field_tracker", "Find North Moraine Oil Field", Material.BUCKET);
        new ItemIcon("Drug Fields", "drug_field_tracker", "Find Drug Fields", Material.KELP);
        new ItemIcon("Smokeleaf Drug Field", "smokeleaf_field_tracker", "Find Smokeleaf Drug Field", Material.ENDER_PEARL);

        new ItemIcon("Complete Contract", "complete_contract", "Click if all steps are complete", Material.EMERALD_BLOCK);
        //      ===--- BANKER ITEMS ---===
        new ItemIcon("Deposit", "bank_deposit", "Make a deposit", Material.EMERALD_BLOCK);
        new ItemIcon("Withdraw", "bank_withdraw", "Make a withdrawal", Material.REDSTONE_BLOCK);
        new ItemIcon("Open Geode", "open_geode", "Click to crack open a geode", Material.IRON_PICKAXE);
        //      ===--- VAULTKEEPER ITEMS ---===
        new ItemIcon("Open Vault", "open_vault", "Open your item vault", Material.ENDER_CHEST);
        new ItemIcon("Upgrade Vault", "upgrade_vault", "Upgrade your item vault", Material.LIME_BANNER);
        new ItemIcon(" ", "empty_slot", "Upgrade for more storage", Material.GRAY_STAINED_GLASS_PANE);
        new ItemIcon("Upgrade Vault", "upgrade_vault_confirm", "50,000g for 6 more slots", Material.EMERALD_BLOCK);
        new ItemIcon("Upgrade Vault", "close_vault", "Close menu", Material.REDSTONE_BLOCK);
        new ItemIcon("Open Account", "open_account_menu", "Open an item vault account", Material.CHEST);
        new ItemIcon("Open Account", "open_account", "Open an item vault account for 25,000g", Material.EMERALD_BLOCK);
        new ItemIcon("Close Menu", "close_menu", "", Material.BARRIER);
        new ItemIcon(" ", "empty_horse_slot", "", Material.GRAY_STAINED_GLASS_PANE);
        new ItemIcon(" ", "empty_slot", "Upgrade for more storage", Material.GRAY_STAINED_GLASS_PANE);
        System.out.println("Icons loaded");
    }

    public static void loadBrewing() {
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

        new NPC("Shopkeeper", NPCType.SHOPKEEPER, 964, 93, -1909, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Santa Fe"));
        new NPC("Contractor", NPCType.CONTRACTOR, 1055, 94, -1957, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Santa Fe"));
        new NPC("Bank Teller", NPCType.BANKER, 918, 93, -1925, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Santa Fe"));
        new NPC("Arms Dealer", NPCType.ARMS_DEALER, 973, 94, -1951, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Santa Fe"));
        new NPC("Fisherman", NPCType.FISHERMAN, 981, 91, -1819, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Santa Fe"));
        new NPC("Armorer", NPCType.ARMORER, 980, 93, -1912, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Santa Fe"));
        new NPC("Geologist", NPCType.GEOLOGIST, 1029, 92, -1900, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Santa Fe"));
        new NPC("Stable Manager", NPCType.STABLE_MANAGER, 1110, 92, -2037, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Santa Fe"));
        new NPC("Vault Keeper", NPCType.VAULT_KEEPER, 918, 93, -1931, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Santa Fe"));
        new NPC("Pharmacist", NPCType.PHARMACIST, 986, 93, -1909, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Santa Fe"));


        new NPC("Shopkeeper", NPCType.SHOPKEEPER, -1305, 90, -1712, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("New Orleans"));
        new NPC("Contractor", NPCType.CONTRACTOR, -1372, 93, -1764, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("New Orleans"));
        new NPC("Bank Teller", NPCType.BANKER, -1293, 90, -1738, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("New Orleans"));
        new NPC("Arms Dealer", NPCType.ARMS_DEALER, -1283, 90, -1699, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("New Orleans"));
        new NPC("Fisherman", NPCType.FISHERMAN, -1253, 90, -1727, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("New Orleans"));
        new NPC("Armorer", NPCType.ARMORER, -1270, 91, -1719, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("New Orleans"));
        new NPC("Geologist", NPCType.GEOLOGIST, -1268, 90, -1731, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("New Orleans"));
        new NPC("Stable Manager", NPCType.STABLE_MANAGER, -1250, 90, -1761, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("New Orleans"));
        new NPC("Vault Keeper", NPCType.VAULT_KEEPER, -1288, 90, -1738, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("New Orleans"));
        new NPC("Pharmacist", NPCType.PHARMACIST, -1294, 90, -1724, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("New Orleans"));

        new NPC("Shopkeeper", NPCType.SHOPKEEPER, -1056, 73, 2568, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Republic of Texas"));
        new NPC("Contractor", NPCType.CONTRACTOR, -1030, 72, 2514, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Republic of Texas"));
        new NPC("Bank Teller", NPCType.BANKER, -1076, 72, 2521, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Republic of Texas"));
        new NPC("Arms Dealer", NPCType.ARMS_DEALER, -1054, 73, 2614, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Republic of Texas"));
        new NPC("Fisherman", NPCType.FISHERMAN, -1121, 72, 2340, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Republic of Texas"));
        new NPC("Armorer", NPCType.ARMORER, -1095, 72, 2565, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Republic of Texas"));
        new NPC("Geologist", NPCType.GEOLOGIST, -1055, 72, 2553, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Republic of Texas"));
        new NPC("Stable Manager", NPCType.STABLE_MANAGER, -1121, 71, 2552, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Republic of Texas"));
        new NPC("Vault Keeper", NPCType.VAULT_KEEPER, -1074, 72, 2521, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Republic of Texas"));
        new NPC("Pharmacist", NPCType.PHARMACIST, -1033, 72, 2554, ChatColor.DARK_AQUA, true, false, FrontierLocation.getLocation("Republic of Texas"));

    new NPC("Illegal Armorer", NPCType.ILLEGAL_ARMORER, -46, 109, -2901, ChatColor.DARK_AQUA, false, false, FrontierLocation.getLocation("Storm Point"));
    new NPC("Illegal Arms Dealer", NPCType.ILL_ARMS_DEALER, -17, 105, -2907, ChatColor.DARK_AQUA, false, false, FrontierLocation.getLocation("Storm Point"));

    new NPC("Illegal Armorer", NPCType.ILLEGAL_ARMORER, -1392, 53, 577, ChatColor.DARK_AQUA, false, false, FrontierLocation.getLocation("Red Ash Camp"));
    new NPC("Illegal Arms Dealer", NPCType.ILL_ARMS_DEALER, -1316, 70, 679, ChatColor.DARK_AQUA, false, false, FrontierLocation.getLocation("Red Ash Camp"));

    }

    public static void loadProcessors() {

        new Processor("Smoke leaf processor 1", ProcessorType.SMOKE_LEAF, FrontierLocation.getLocation("Storm Point"), CustomItem.getCustomItem("Unprocessed Smokeleaf").getItemStack(), CustomItem.getCustomItem("Processed Smokeleaf").getItemStack());
        new Processor("Smoke leaf processor 2", ProcessorType.SMOKE_LEAF, FrontierLocation.getLocation("Storm Point"), CustomItem.getCustomItem("Unprocessed Smokeleaf").getItemStack(), CustomItem.getCustomItem("Processed Smokeleaf").getItemStack());
        new Processor("Smoke leaf processor 3", ProcessorType.SMOKE_LEAF, FrontierLocation.getLocation("Storm Point"), CustomItem.getCustomItem("Unprocessed Smokeleaf").getItemStack(), CustomItem.getCustomItem("Processed Smokeleaf").getItemStack());

        new Processor("Spice processor 1", ProcessorType.SPICE, FrontierLocation.getLocation("Red Ash Camp"), CustomItem.getCustomItem("Unprocessed Spice").getItemStack(), CustomItem.getCustomItem("Spice").getItemStack());
        new Processor("Spice processor 2", ProcessorType.SPICE, FrontierLocation.getLocation("Red Ash Camp"), CustomItem.getCustomItem("Unprocessed Spice").getItemStack(), CustomItem.getCustomItem("Spice").getItemStack());
        new Processor("Spice processor 3", ProcessorType.SPICE, FrontierLocation.getLocation("Red Ash Camp"), CustomItem.getCustomItem("Unprocessed Spice").getItemStack(), CustomItem.getCustomItem("Spice").getItemStack());


        List<ProcessorCoordinate> smokeCords = new ArrayList<>();
        smokeCords.add(new ProcessorCoordinate(-53, 108, -2920));
        smokeCords.add(new ProcessorCoordinate(-50, 106, -2936));
        smokeCords.add(new ProcessorCoordinate(-63, 112, -2934));
        smokeCords.add(new ProcessorCoordinate(-46, 107, -2964));
        smokeCords.add(new ProcessorCoordinate(-46, 111, -2959));
        smokeCords.add(new ProcessorCoordinate(-43, 119, -2968));
        smokeCords.add(new ProcessorCoordinate(-14, 106, -2933));
        smokeCords.add(new ProcessorCoordinate(-15, 112, -2936));
        smokeCords.add(new ProcessorCoordinate(-18, 117, -2935));
        Processor.addProcCoords(ProcessorType.SMOKE_LEAF, smokeCords);

        List<ProcessorCoordinate> spiceCords = new ArrayList<>();
        spiceCords.add(new ProcessorCoordinate(-1310, 81, 587));
        spiceCords.add(new ProcessorCoordinate(-1307, 69, 585));
        spiceCords.add(new ProcessorCoordinate(-1295, 69, 580));
        spiceCords.add(new ProcessorCoordinate(-1294, 75, 568));
        spiceCords.add(new ProcessorCoordinate(-1377, 60, 600));
        spiceCords.add(new ProcessorCoordinate(-1383, 53, 596));
        spiceCords.add(new ProcessorCoordinate(-1318, 52, 663));
        spiceCords.add(new ProcessorCoordinate(-1334, 52, 678));
        spiceCords.add(new ProcessorCoordinate(-1335, 63, 659));
        spiceCords.add(new ProcessorCoordinate(-1373, 55, 584));
        Processor.addProcCoords(ProcessorType.SPICE, spiceCords);

        System.out.println("Processors successfully loaded!");
    }

    public static void loadPotions() {
        System.out.println("loading potions");
        new CustomPotion("Whiskey", "Makes the pain go away", 0.0, 150, 4, 135, 99, 38, 600, PotionEffectType.DAMAGE_RESISTANCE, 0);
        new CustomPotion("Morphine", "Slowly rejuvenates", 0.0, 175, 4, 20, 151, 163, 600, PotionEffectType.REGENERATION, 0);
        new CustomPotion("Medicine", "Grows new limbs", 0.0, 100, 3, 253, 94, 94, 10, PotionEffectType.HEAL, 1);
    }

}
