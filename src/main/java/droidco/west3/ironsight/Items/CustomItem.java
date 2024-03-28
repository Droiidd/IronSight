package droidco.west3.ironsight.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class
CustomItem {
    private String itemCode;
    private String itemName;
    private String rarityLore;
    private String description;
    private double salePrice;
    private double purchasePrice;
    private boolean isLegal;
    private boolean isOfficer;
    private int rarity;
    private Material material;
    private Enchantment enchant1;
    private static HashMap<String,CustomItem> items = new HashMap<>();

    public CustomItem(String itemName, int rarity, boolean isLegal, boolean isOfficer,String description, Material material,
                      double salePrice, double purchasePrice) {
        this.itemCode = itemName;
        this.itemName = ChatColor.WHITE+itemName;
        this.rarity = rarity;
        this.rarityLore = getRarityString(rarity);
        this.isLegal = isLegal;
        this.isOfficer = isOfficer;
        this.description = ChatColor.DARK_GRAY+description;
        this.material = material;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
        items.put(this.itemCode, this);
        ItemTable.addItem(this);
    }
    public CustomItem(String itemName, int rarity, boolean isLegal, boolean isOfficer, String description, Material material,
                      Enchantment enchant1) {
        this.itemCode = itemName;
        this.itemName = ChatColor.WHITE+itemName;
        this.rarityLore = getRarityString(rarity);
        this.isLegal = isLegal;
        this.isOfficer = isOfficer;
        this.description = ChatColor.DARK_GRAY+description;
        this.material = material;
        this.enchant1 = enchant1;
        items.put(this.itemCode, this);
    }
    public String getRarityString(int rarity){
        switch(rarity){
            case 1,2 -> {
                return ChatColor.WHITE+String.valueOf(ChatColor.BOLD)+"Common";
            }
            case 3,4 -> {
                return ChatColor.DARK_GREEN.toString()+String.valueOf(ChatColor.BOLD)+"Uncommon";
            }
            case 5,6-> {
                return ChatColor.LIGHT_PURPLE.toString()+String.valueOf(ChatColor.BOLD)+"Rare";
            }
            case 7,8 -> {
                return ChatColor.YELLOW.toString()+String.valueOf(ChatColor.BOLD)+"Legendary";
            }
        }
        return "";
    }

    public static CustomItem getCustomItem(String itemCode){
        if(items.containsKey(itemCode)){
            return items.get(itemCode);
        }
        return null;
    }
    public ItemStack getItemForSale(){
        ItemStack item = getItemStack();
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        String price = String.valueOf(ChatColor.GRAY)+this.salePrice +"g";
        lore.add(ChatColor.GRAY+"Click to purchase!");
        lore.add(price);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getItemStack(){
        ItemStack item = new ItemStack(this.material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(this.itemName);
        List<String> lore = new ArrayList<>();
        lore.add(this.rarityLore);
        String legalityTrait = getLegalityTrait();
        int test = 0;
        if(legalityTrait != null){
            lore.add(legalityTrait);
        }
        lore.add(description);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public String getLegalityTrait()
    {
        String trait;
        if(!this.isLegal){
            //IS NOT LEGAL
            trait = String.valueOf(ChatColor.BOLD)+String.valueOf(ChatColor.DARK_RED)+"Illegal";
        }
        if(this.isOfficer){
            trait = String.valueOf(ChatColor.BOLD)+String.valueOf(ChatColor.BLUE)+"Officer";
        }
        return null;
    }

    public boolean isLegal() {
        return isLegal;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
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
    public int getRarity(){return rarity; }

    public Material getMaterial(){return material;}
}
