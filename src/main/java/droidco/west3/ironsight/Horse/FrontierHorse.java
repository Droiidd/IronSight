package droidco.west3.ironsight.Horse;

import droidco.west3.ironsight.Items.ItemIcon;
import org.bukkit.entity.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

public class FrontierHorse {
    private final String ownerId;
    private final String horseName;
    private FrontierHorseType horseType;
    private Horse horse;
    private Donkey donkey;
    private SkeletonHorse skeleHorse;
    private boolean isSummoned;
    private UUID iD;
    private boolean isInventoryLoaded;
    private ItemStack[] inventory = new ItemStack[0];
    private static HashMap<UUID,FrontierHorse> horses = new HashMap<>();
    private static HashMap<UUID, LivingEntity> summonedHorses = new HashMap<>();


    public FrontierHorse(String ownerId, String horseName, FrontierHorseType horseType) {
        this.horseName = horseName;
        this.ownerId = ownerId;
        this.horseType = horseType;
        this.isSummoned = false;
        this.isInventoryLoaded = false;
    }
    public void summonHorse(Player p) {
        isSummoned = true;
        switch(horseType){
            case DONKEY -> {
                donkey = p.getWorld().spawn(p.getLocation(), Donkey.class);
                iD = donkey.getUniqueId();
                donkey.setAdult();
                donkey.setOwner(p);
                donkey.setTamed(true);
                donkey.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                donkey.setCarryingChest(true);
                donkey.setCustomName(horseName);
                donkey.setCustomNameVisible(true);
                donkey.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.2);
                donkey.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(15);
                horses.put(iD,this);
                summonedHorses.put(iD, donkey);
            }case THOROUGHBRED,STANDARD -> {
                horse = p.getWorld().spawn(p.getLocation(), Horse.class);
                iD = horse.getUniqueId();
                horse.setOwner(p);
                horse.setAdult();
                horse.setTamed(true);
                horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                horse.setCustomName(horseName);
                horse.setCustomNameVisible(true);
                horses.put(iD,this);
                summonedHorses.put(iD, horse);

                if(horseType.equals(FrontierHorseType.STANDARD)){
                    horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
                    horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(15);
                }else if(horseType.equals(FrontierHorseType.THOROUGHBRED)){
                    horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.45);
                    horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(15);
                    horse.setStyle(Horse.Style.WHITEFIELD);
                }
            }
        }

    }
    public static FrontierHorse getHorse(UUID horseId){
        return horses.get(horseId);
    }
    public static LivingEntity getSummonedHorse(UUID horseId){
        return summonedHorses.get(horseId);
    }

    public UUID getHorseId() {
        return horse.getUniqueId();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public boolean isInventoryLoaded() {
        return isInventoryLoaded;
    }

    public String getHorseName() {
        return horseName;
    }

    public boolean isSummoned() {
        return isSummoned;
    }

    public void setSummoned(boolean status) {
        this.isSummoned = status;
    }

    public UUID getHorseUUID() {
        return iD;
    }


    public FrontierHorseType getHorseType() {
        return this.horseType;
    }


    public void openHorseInventory(Player p) {
        switch (horseType){
            case DONKEY -> {
                Inventory inv = Bukkit.createInventory(p, 18, horseName + "'s saddle-pack storage");
                inv.setContents(inventory);
                p.openInventory(inv);
            }
            case THOROUGHBRED -> {
                Inventory inv = Bukkit.createInventory(p, 9, horseName + "'s saddle-pack storage");
                inv.setContents(inventory);
                for(int i =1;i<inv.getSize();i++){
                    inv.setItem(i,ItemIcon.getIcon("empty_horse_slot").getItem());
                }
                p.openInventory(inv);
            }case STANDARD -> {
                Inventory inv = Bukkit.createInventory(p, 9, horseName + "'s saddle-pack storage");
                inv.setContents(inventory);
                for(int i =4;i<inv.getSize();i++){
                    inv.setItem(i, ItemIcon.getIcon("empty_horse_slot").getItem());
                }
                p.openInventory(inv);
            }
        }
    }

    public ItemStack[] getHorseInv() {
        return inventory;
    }

    public void setHorseInv(ItemStack[] items) {
        inventory = items;
        this.isInventoryLoaded = true;
    }
}