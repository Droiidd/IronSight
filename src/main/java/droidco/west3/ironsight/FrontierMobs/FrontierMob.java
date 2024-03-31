package droidco.west3.ironsight.FrontierMobs;

import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.FrontierLocation.LocationType;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class FrontierMob {
    private FrontierLocation location;
    private LocationType locationType;
    private FrontierMobType type;
    private UUID mobId;
    private static HashMap<UUID, FrontierMob> mobs = new HashMap<>();
    public FrontierMob(FrontierLocation location, LocationType locationType,FrontierMobType type)
    {
        this.type = type;
        this.locationType = locationType;
        this.location = location;
    }


    public void spawnAnimal(Player p){



    }
    public void spawnRaider(){

    }
    public void spawnUndead(Player p){
        Block test = null;
        double x = 0.0;
        double z = 0.0;
       while(test == null){
            x = GlobalUtils.getRandomCord(location.getX1(),location.getX2());
            z = GlobalUtils.getRandomCord(location.getZ1(),location.getZ2());
            test = GlobalUtils.getSurfaceYVal(p.getWorld(),x,z);
       }
        p.sendMessage("X: "+x+" Y: "+test.getY()+" Z: "+z);

        Location spawnLoc = new Location(p.getWorld(),x,test.getY(),z);

        switch(type){
            case UNDEAD_MINER -> {
                Skeleton miner = p.getWorld().spawn(spawnLoc,Skeleton.class);
                miner.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION.INCREASE_DAMAGE, 10000000, 1, false, false));
                miner.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 1, false, false));
                miner.setCustomName(ChatColor.DARK_GRAY + "Corroded Miner");
                miner.setCustomNameVisible(true);

                this.mobId = miner.getUniqueId();
                mobs.put(mobId,this);
            }
        }



    }


}
