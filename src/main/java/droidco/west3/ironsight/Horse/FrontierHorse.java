package droidco.west3.ironsight.Horse;

import org.bukkit.inventory.Inventory;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Bukkit;

import java.util.UUID;

public class FrontierHorse {
    private final String ownerId;
    private final String horseName;
    private FrontierHorseType horseType;
    private Horse horse;
    private Donkey donkey;
    private SkeletonHorse skeleHorse;
    private boolean isSummoned;
    private UUID horseUUID;
    private UUID donkeyUUID;
    private ItemStack[] inventory = new ItemStack[0];


    public FrontierHorse(String ownerId, String horseName, FrontierHorseType horseType) {
        this.horseName = horseName;
        this.ownerId = ownerId;
        this.horseType = horseType;
        this.isSummoned = false;
    }

    public FrontierHorse(String ownerId, String horseName,  FrontierHorseType horseType, ItemStack[] inventory) {
        this.horseName = horseName;
        this.ownerId = ownerId;
        this.horseType = horseType;
        this.inventory = inventory;
        this.isSummoned = false;
    }

    public void summonHorse(Player p) {
        isSummoned = true;
        switch(horseType){
            case DONKEY -> {
                donkey = p.getWorld().spawn(p.getLocation(), Donkey.class);
                donkeyUUID = donkey.getUniqueId();
                donkey.setAdult();
                donkey.setOwner(p);
                donkey.setTamed(true);
                donkey.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                donkey.setCarryingChest(true);
                donkey.setCustomName(horseName);
                donkey.setCustomNameVisible(true);
                donkey.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.2);
                donkey.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(15);
            }case SPEEDY -> {
                horse = p.getWorld().spawn(p.getLocation(), Horse.class);
                horseUUID = horse.getUniqueId();
                horse.setOwner(p);
                horse.setAdult();
                horse.setTamed(true);
                horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                horse.setCustomName(horseName);
                horse.setCustomNameVisible(true);

                horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.45);
                horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(15);
                horse.setStyle(Horse.Style.WHITEFIELD);

            }case DEFAULT -> {
                horse = p.getWorld().spawn(p.getLocation(), Horse.class);
                horseUUID = horse.getUniqueId();
                horse.setOwner(p);
                horse.setAdult();
                horse.setTamed(true);
                horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                horse.setCustomName(horseName);
                horse.setCustomNameVisible(true);
                horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
                horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(15);
            }
        }

    }

    public UUID getHorseId() {
        return horse.getUniqueId();
    }

    public String getOwnerId() {
        return ownerId;
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
        return horseUUID;
    }

    public UUID getDonkeyUUID() {
        return donkeyUUID;
    }

    public FrontierHorseType getHorseType() {
        return this.horseType;
    }

    public void openHorseInventory(Player p) {
        switch (horseType){
            case DONKEY -> {
                Inventory inv = Bukkit.createInventory(p, 27, horseName + "'s saddle-pack storage");
                inv.setContents(inventory);
                p.openInventory(inv);
            }
            case SPEEDY -> {
                Inventory inv = Bukkit.createInventory(p, 18, horseName + "'s saddle-pack storage");
                inv.setContents(inventory);
                p.openInventory(inv);
            }case DEFAULT -> {
                Inventory inv = Bukkit.createInventory(p, 9, horseName + "'s saddle-pack storage");
                inv.setContents(inventory);
                p.openInventory(inv);
            }
        }
    }

    public ItemStack[] getHorseInv() {
        return inventory;
    }

    public void setHorseInv(ItemStack[] items) {
        inventory = items;
    }
}