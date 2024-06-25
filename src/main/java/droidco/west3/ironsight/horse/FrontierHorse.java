package droidco.west3.ironsight.horse;

import droidco.west3.ironsight.items.ItemIcon;
import java.util.HashMap;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
public class FrontierHorse {
  private static HashMap<UUID, FrontierHorse> horses = new HashMap<>();
  private static HashMap<UUID, LivingEntity> summonedHorses = new HashMap<>();
  private final String ownerId;
  private final String horseName;
  private FrontierHorseType horseType;
  private Horse horse;
  private Donkey donkey;
  private SkeletonHorse skeleHorse;
  private boolean summoned;
  private UUID uuid;
  private boolean inventoryLoaded;
  private ItemStack[] inventory = new ItemStack[0];

  public FrontierHorse(String ownerId, String horseName, FrontierHorseType horseType) {
    this.horseName = horseName;
    this.ownerId = ownerId;
    this.horseType = horseType;
    this.summoned = false;
    this.inventoryLoaded = false;
  }

  public static FrontierHorse getHorse(UUID horseId) {
    return horses.get(horseId);
  }

  public static LivingEntity getSummonedHorse(UUID horseId) {
    return summonedHorses.get(horseId);
  }

  public void summonHorse(Player p) {
    summoned = true;
    switch (horseType) {
      case DONKEY -> {
        donkey = p.getWorld().spawn(p.getLocation(), Donkey.class);
        uuid = donkey.getUniqueId();
        donkey.setAdult();
        donkey.setOwner(p);
        donkey.setTamed(true);
        donkey.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        donkey.setCarryingChest(true);
        donkey.setCustomName(horseName);
        donkey.setCustomNameVisible(true);
        donkey.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.2);
        donkey.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(15);
        horses.put(uuid, this);
        summonedHorses.put(uuid, donkey);
      }
      case THOROUGHBRED, STANDARD -> {
        horse = p.getWorld().spawn(p.getLocation(), Horse.class);
        uuid = horse.getUniqueId();
        horse.setOwner(p);
        horse.setAdult();
        horse.setTamed(true);
        horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        horse.setCustomName(horseName);
        horse.setCustomNameVisible(true);
        horses.put(uuid, this);
        summonedHorses.put(uuid, horse);

        if (horseType.equals(FrontierHorseType.STANDARD)) {
          horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
          horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(15);
        } else if (horseType.equals(FrontierHorseType.THOROUGHBRED)) {
          horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.45);
          horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(15);
          horse.setStyle(Horse.Style.WHITEFIELD);
        }
      }
    }
  }

  public UUID getHorseId() {
    return horse.getUniqueId();
  }

  public void openHorseInventory(Player p) {
    switch (horseType) {
      case DONKEY -> {
        Inventory inv = Bukkit.createInventory(p, 18, horseName + "'s saddle-pack storage");
        inv.setContents(inventory);
        p.openInventory(inv);
      }
      case THOROUGHBRED -> {
        Inventory inv = Bukkit.createInventory(p, 9, horseName + "'s saddle-pack storage");
        inv.setContents(inventory);
        for (int i = 1; i < inv.getSize(); i++) {
          inv.setItem(i, ItemIcon.getIcon("empty_horse_slot").getItem());
        }
        p.openInventory(inv);
      }
      case STANDARD -> {
        Inventory inv = Bukkit.createInventory(p, 9, horseName + "'s saddle-pack storage");
        inv.setContents(inventory);
        for (int i = 4; i < inv.getSize(); i++) {
          inv.setItem(i, ItemIcon.getIcon("empty_horse_slot").getItem());
        }
        p.openInventory(inv);
      }
    }
  }

  public void setHorseInv(ItemStack[] items) {
    inventory = items;
    this.inventoryLoaded = true;
  }
}
