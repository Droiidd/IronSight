package droidco.west3.ironsight.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItem {
  private static final HashMap<String, CustomItem> items = new HashMap<>();
  private final String itemCode;
  private final String itemName;
  private final String rarityLore;
  private final String description;
  private double salePrice;
  private double purchasePrice;
  private boolean isLegal;
  private boolean isOfficer;
  private int rarity;
  private Material material;
  private Enchantment enchant;
  private int enchantMultiplier;
  private int amountForSale;

  public CustomItem(
      String itemName,
      int rarity,
      boolean isLegal,
      boolean isOfficer,
      String description,
      Material material,
      double salePrice,
      double purchasePrice,
      int amountForSale) {
    this.itemCode = itemName;
    this.itemName = ChatColor.WHITE + itemName;
    this.rarity = rarity;
    this.rarityLore = getRarityString(rarity);
    this.isLegal = isLegal;
    this.isOfficer = isOfficer;
    this.description = ChatColor.GRAY + description;
    this.material = material;
    this.salePrice = salePrice;
    this.purchasePrice = purchasePrice;
    this.amountForSale = amountForSale;
    items.put(this.itemCode, this);
    // ItemTable.addItem(this);
  }

  public CustomItem(
      String itemName,
      int rarity,
      boolean isLegal,
      boolean isOfficer,
      String description,
      Material material,
      double salePrice,
      double purchasePrice) {
    this.itemCode = itemName;
    this.itemName = ChatColor.WHITE + itemName;
    this.rarity = rarity;
    this.rarityLore = getRarityString(rarity);
    this.isLegal = isLegal;
    this.isOfficer = isOfficer;
    this.description = ChatColor.GRAY + description;
    this.material = material;
    this.salePrice = salePrice;
    this.purchasePrice = purchasePrice;
    items.put(this.itemCode, this);
    // ItemTable.addItem(this);
  }

  public CustomItem(
      String itemName,
      int rarity,
      boolean isLegal,
      boolean isOfficer,
      String description,
      Material material,
      double salePrice,
      double purchasePrice,
      int amountForSale,
      Enchantment enchant,
      int enchantMultiplier) {
    this.itemCode = itemName;
    this.itemName = ChatColor.WHITE + itemName;
    this.rarity = rarity;
    this.rarityLore = getRarityString(rarity);
    this.isLegal = isLegal;
    this.isOfficer = isOfficer;
    this.description = ChatColor.GRAY + description;
    this.material = material;
    this.salePrice = salePrice;
    this.purchasePrice = purchasePrice;
    this.amountForSale = amountForSale;
    this.enchant = enchant;
    this.enchantMultiplier = enchantMultiplier;
    items.put(this.itemCode, this);
  }

  public static HashMap<String, CustomItem> getItems() {
    return items;
  }

  public static CustomItem getCustomItem(String itemCode) {
    if (items.containsKey(itemCode)) {
      return items.get(itemCode);
    }
    return null;
  }

  public String getRarityString(int rarity) {
    switch (rarity) {
      case 1, 2 -> {
        return ChatColor.WHITE + String.valueOf(ChatColor.BOLD) + "Common";
      }
      case 3, 4 -> {
        return ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Uncommon";
      }
      case 5, 6 -> {
        return ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Rare";
      }
      case 7, 8 -> {
        return ChatColor.YELLOW.toString() + ChatColor.BOLD + "Legendary";
      }
    }
    return "";
  }

  public ItemStack getItemForSale() {
    ItemStack item = getItemStack();
    ItemMeta meta = item.getItemMeta();
    List<String> lore = new ArrayList<>();
    String price = String.valueOf(ChatColor.GRAY) + this.purchasePrice + "g";
    lore.add(ChatColor.GRAY + "Click to purchase!");
    lore.add(price);
    meta.setLore(lore);
    item.setAmount(this.amountForSale);
    item.setItemMeta(meta);
    return item;
  }

  public ItemStack getItemStack() {
    ItemStack item = new ItemStack(this.material);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(this.itemName);
    List<String> lore = new ArrayList<>();
    lore.add(this.rarityLore);
    String legalityTrait = getLegalityTrait();
    int test = 0;
    if (legalityTrait != null) {
      lore.add(legalityTrait);
    }
    if (enchant != null) {
      meta.addEnchant(enchant, enchantMultiplier, true);
    }
    if (description.length() > 0) {
      lore.add(description);
    }
    if (amountForSale != 0) {
      item.setAmount(amountForSale);
    }
    meta.setLore(lore);
    item.setItemMeta(meta);
    return item;
  }

  public ItemStack getItemStack(int num) {
    ItemStack item = new ItemStack(this.material, num);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(this.itemName);
    List<String> lore = new ArrayList<>();
    lore.add(this.rarityLore);
    String legalityTrait = getLegalityTrait();
    int test = 0;
    if (legalityTrait != null) {
      lore.add(legalityTrait);
    }
    if (enchant != null) {
      meta.addEnchant(enchant, enchantMultiplier, true);
    }
    if (description.length() > 0) {
      lore.add(description);
    }
    meta.setLore(lore);
    item.setItemMeta(meta);
    return item;
  }

  public String getLegalityTrait() {
    String trait;
    if (!this.isLegal) {
      // IS NOT LEGAL
      trait = String.valueOf(ChatColor.BOLD) + ChatColor.DARK_RED + "Illegal";
      return trait;
    }
    if (this.isOfficer) {
      trait = String.valueOf(ChatColor.BOLD) + ChatColor.BLUE + "Officer";
      return trait;
    }
    return null;
  }

  public boolean isLegal() {
    return isLegal;
  }

  public void setLegal(boolean legal) {
    isLegal = legal;
  }

  public boolean isOfficer() {
    return isOfficer;
  }

  public void setOfficer(boolean officer) {
    isOfficer = officer;
  }

  public int getRarity() {
    return rarity;
  }

  public void setRarity(int rarity) {
    this.rarity = rarity;
  }

  public double getSalePrice() {
    return salePrice;
  }

  public void setSalePrice(double salePrice) {
    this.salePrice = salePrice;
  }

  public double getPurchasePrice() {
    return purchasePrice;
  }

  public void setPurchasePrice(double purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  public Enchantment getEnchant() {
    return enchant;
  }

  public void setEnchant(Enchantment enchant) {
    this.enchant = enchant;
  }

  public int getEnchantMultiplier() {
    return enchantMultiplier;
  }

  public void setEnchantMultiplier(int enchantMultiplier) {
    this.enchantMultiplier = enchantMultiplier;
  }

  public Material getMaterial() {
    return material;
  }

  public void setMaterial(Material material) {
    this.material = material;
  }

  public String getItemCode() {
    return this.itemCode;
  }

  public int getAmountForSale() {
    return amountForSale;
  }

  public void setAmountForSale(int amountForSale) {
    this.amountForSale = amountForSale;
  }
}
