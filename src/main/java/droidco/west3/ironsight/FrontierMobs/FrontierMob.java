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
    public FrontierMob(LocationType locationType,FrontierMobType type)
    {
        this.type = type;
        this.locationType = locationType;
    }


    public void spawnAnimal(Player p){



    }
    public void spawnRaider(){

    }
    public void spawnUndead(Player p,FrontierLocation location){
        this.location = location;
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
                }
                this.mobId = miner.getUniqueId();
                mobs.put(mobId,this);
                entities.put(mobId,miner);
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
