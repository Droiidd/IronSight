package droidco.west3.ironsight.FrontierMobs;

import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.FrontierLocation.LocationType;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.Items.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FrontierMob {
    private FrontierLocation location;
    private LocationType locationType;
    private FrontierMobType type;
    private UUID mobId;
    private LivingEntity entity;
    private static HashMap<UUID, FrontierMob> mobs = new HashMap<>();
    private static HashMap<UUID, LivingEntity> entities = new HashMap<>();
    public FrontierMob(FrontierMobType type)
    {
        this.type = type;
    }

    public void spawnMob(Player p){
        Block spawnBlock = GlobalUtils.getRandomSurfaceBlock(p);
        p.sendMessage("X: "+spawnBlock.getX()+" Y: "+spawnBlock.getY()+" Z: "+spawnBlock.getZ());

        Location spawnLoc = new Location(p.getWorld(),spawnBlock.getX(),spawnBlock.getY(),spawnBlock.getZ());

        switch(type){
            case UNDEAD_MINER -> {
                Skeleton miner = p.getWorld().spawn(spawnLoc,Skeleton.class);
                miner.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10000000, 1, false, false));
                miner.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 1, false, false));
                miner.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10000000, 2, false, false));
                miner.setCustomName(ChatColor.GRAY + "Undead Miner");
                miner.setCustomNameVisible(true);

                miner.getEquipment().setBoots(CustomItem.getCustomItem("Huntsmen Boots").getItemStack());
                miner.getEquipment().setChestplate(CustomItem.getCustomItem("Huntsmen Jacket").getItemStack());
                miner.getEquipment().setLeggings(CustomItem.getCustomItem("Huntsmen Trousers").getItemStack());
                miner.getEquipment().setHelmet(CustomItem.getCustomItem("Huntsmen Hat").getItemStack());

                this.mobId = miner.getUniqueId();
                mobs.put(mobId,this);
                entities.put(mobId,miner);
            }
            case BERSERKER_MINER -> {
                WitherSkeleton miner = p.getWorld().spawn(spawnLoc,WitherSkeleton.class);
                miner.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10000000, 1, false, false));
                miner.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 1, false, false));
                miner.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10000000, 2, false, false));
                miner.setCustomName(ChatColor.GRAY + "Berserker Miner");
                miner.setCustomNameVisible(true);

                this.mobId = miner.getUniqueId();
                mobs.put(mobId,this);
                entities.put(mobId,miner);
            }case CORRODED_MINER -> {
                Stray miner = p.getWorld().spawn(spawnLoc,Stray.class);
                miner.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10000000, 1, false, false));
                miner.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 1, false, false));
                miner.setCustomName(ChatColor.GRAY + "Corroded Miner");
                miner.setCustomNameVisible(true);
                miner.getEquipment().setChestplate(CustomItem.getCustomItem("Huntsmen Jacket").getItemStack());
                miner.getEquipment().setLeggings(CustomItem.getCustomItem("Huntsmen Trousers").getItemStack());
                int chance = GlobalUtils.getRandomNumber(101);
                if (chance % 7 == 0) {
                    miner.getEquipment().setItemInMainHand(CustomItem.getCustomItem("Flame Bow").getItemStack());
                }else{
                    miner.getEquipment().setItemInMainHand(CustomItem.getCustomItem("Hard Bow").getItemStack());
                }
                this.mobId = miner.getUniqueId();
                mobs.put(mobId,this);
                entities.put(mobId,miner);
            }
            case WILD_DOG -> {
                Wolf wolf = p.getWorld().spawn(spawnLoc, Wolf.class);
                mobId = wolf.getUniqueId();
                wolf.setAngry(true);
                //wolf.setAware(true);
                wolf.setCustomName(ChatColor.GRAY + "Rabid Hound");
                wolf.setCustomNameVisible(true);
                wolf.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 2, false, false));
                wolf.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 1, false, false));
                wolf.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false));
                mobs.put(mobId,this);
                entities.put(mobId,wolf);
            }
            case RANGER -> {
                Pillager raider = p.getWorld().spawn(spawnLoc, Pillager.class);
                mobId = raider.getUniqueId();
                raider.setCustomName(ChatColor.GRAY + "Raider");
                raider.setCustomNameVisible(true);
                raider.setCanJoinRaid(false);
                raider.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 1, false, false));
                int chance = GlobalUtils.getRandomNumber(101);
                if (chance % 7 == 0) {
                    raider.getEquipment().setItemInMainHand(CustomItem.getCustomItem("Flame Bow").getItemStack());
                }else{
                    raider.getEquipment().setItemInMainHand(CustomItem.getCustomItem("Hard Bow").getItemStack());
                }
                mobs.put(mobId,this);
                entities.put(mobId,raider);
            }
            case RAIDER -> {
                Vindicator raider = p.getWorld().spawn(spawnLoc, Vindicator.class);
                mobId = raider.getUniqueId();
                raider.setCustomName(ChatColor.GRAY + "Raider");
                raider.setCustomNameVisible(true);
                raider.setCanJoinRaid(false);
                raider.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 1, false, false));
                mobs.put(mobId,this);
                entities.put(mobId,raider);
            }
            case RAIDER_BRUTE -> {
                PiglinBrute raider = p.getWorld().spawn(spawnLoc, PiglinBrute.class);
                mobId = raider.getUniqueId();
                raider.setCustomName(ChatColor.GRAY + "Brute Raider");
                raider.setCustomNameVisible(true);
                raider.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 1, false, false));
                raider.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 1, false, false));
                mobs.put(mobId,this);
                entities.put(mobId,raider);
            }
            case BOAR -> {
                Hoglin animal = p.getWorld().spawn(spawnLoc, Hoglin.class);
                mobId = animal.getUniqueId();
                animal.setAdult();
                animal.setCustomName(ChatColor.GRAY + "Brute Raider");
                animal.setCustomNameVisible(true);
                //animal.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 2, false, false));
                animal.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false));
                mobs.put(mobId,this);
                entities.put(mobId,animal);

            }
            case RABBIT -> {
                Hoglin animal = p.getWorld().spawn(spawnLoc, Hoglin.class);
                mobId = animal.getUniqueId();
                animal.setAdult();
                animal.setCustomName(ChatColor.GRAY + "Brute Raider");
                animal.setCustomNameVisible(true);
                animal.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 2, false, false));
                animal.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false));
                mobs.put(mobId,this);
                entities.put(mobId,animal);
            }
            case RED_FOX -> {
                Hoglin animal = p.getWorld().spawn(spawnLoc, Hoglin.class);
                mobId = animal.getUniqueId();
                animal.setAdult();
                animal.setCustomName(ChatColor.GRAY + "Brute Raider");
                animal.setCustomNameVisible(true);
                animal.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 2, false, false));
                animal.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false));
                mobs.put(mobId,this);
                entities.put(mobId,animal);
            }
            case WILD_COW -> {
                Hoglin animal = p.getWorld().spawn(spawnLoc, Hoglin.class);
                mobId = animal.getUniqueId();
                animal.setAdult();
                animal.setCustomName(ChatColor.GRAY + "Brute Raider");
                animal.setCustomNameVisible(true);
                animal.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 2, false, false));
                animal.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false));
                mobs.put(mobId,this);
                entities.put(mobId,animal);
            }
        }
    }
    public static HashMap<UUID,FrontierMob> getMobs(){
        return mobs;
    }
    public static HashMap<UUID,LivingEntity> getEntities(){
        return entities;
    }


}
