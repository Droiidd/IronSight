package droidco.west3.ironsight.items;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class
CustomItem {
    private String itemCode;
    private String itemName;
    private String rarityLore;
    private String description;
    private double salePrice;
    private double purchasePrice;
    private boolean legal;
    private boolean officer;
    private int rarity;
    private Material material;
    private Enchantment enchant;
    private int enchantMultiplier;
    private int amountForSale;
    private static HashMap<String,CustomItem> items = new HashMap<>();

    public CustomItem(String itemName, int rarity, boolean legal, boolean officer, String description, Material material,
                      double salePrice, double purchasePrice, int amountForSale) {
        this.itemCode = itemName;
        this.itemName = ChatColor.WHITE+itemName;
        this.rarity = rarity;
        this.rarityLore = getRarityString(rarity);
        this.legal = legal;
        this.officer = officer;
        this.description = ChatColor.GRAY+description;
        this.material = material;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
        this.amountForSale = amountForSale;
        items.put(this.itemCode, this);
       // ItemTable.addItem(this);
    }
    public CustomItem(String itemName, int rarity, boolean legal, boolean officer, String description, Material material,
                      double salePrice, double purchasePrice) {
        this.itemCode = itemName;
        this.itemName = ChatColor.WHITE+itemName;
        this.rarity = rarity;
        this.rarityLore = getRarityString(rarity);
        this.legal = legal;
        this.officer = officer;
        this.description = ChatColor.GRAY+description;
        this.material = material;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
        items.put(this.itemCode, this);
        // ItemTable.addItem(this);
    }
    public CustomItem(String itemName, int rarity, boolean legal, boolean officer, String description, Material material,
                      double salePrice, double purchasePrice, int amountForSale, Enchantment enchant, int enchantMultiplier) {
        this.itemCode = itemName;
        this.itemName = ChatColor.WHITE+itemName;
        this.rarity = rarity;
        this.rarityLore = getRarityString(rarity);
        this.legal = legal;
        this.officer = officer;
        this.description = ChatColor.GRAY+description;
        this.material = material;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
        this.amountForSale = amountForSale;
        this.enchant = enchant;
        this.enchantMultiplier = enchantMultiplier;
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
    public static HashMap<String,CustomItem> getItems()
    {
        return items;
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
        double totalPrice = this.purchasePrice * this.amountForSale;
        String price = String.valueOf(ChatColor.GRAY)+totalPrice +"g";
        lore.add(ChatColor.GRAY+"Click to purchase!");
        lore.add(price);
        meta.setLore(lore);
        item.setAmount(this.amountForSale);
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
        if(enchant != null){
            meta.addEnchant(enchant,enchantMultiplier,true);
        }
        if(description.length() > 0){
            lore.add(description);
        }
        if(amountForSale != 0){
            item.setAmount(amountForSale);
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack getItemStack(int num){
        ItemStack item = new ItemStack(this.material, num);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(this.itemName);
        List<String> lore = new ArrayList<>();
        lore.add(this.rarityLore);
        String legalityTrait = getLegalityTrait();
        int test = 0;
        if(legalityTrait != null){
            lore.add(legalityTrait);
        }
        if(enchant != null){
            meta.addEnchant(enchant,enchantMultiplier,true);
        }
        if(description.length() > 0){
            lore.add(description);
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public String getLegalityTrait()
    {
        String trait;
        if(!this.legal){
            //IS NOT LEGAL
            trait = String.valueOf(ChatColor.BOLD)+String.valueOf(ChatColor.DARK_RED)+"Illegal";
            return trait;
        }
        if(this.officer){
            trait = String.valueOf(ChatColor.BOLD)+String.valueOf(ChatColor.BLUE)+"Officer";
            return trait;
        }
        return null;
    }

}
