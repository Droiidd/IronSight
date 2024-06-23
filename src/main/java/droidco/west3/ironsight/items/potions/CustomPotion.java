package droidco.west3.ironsight.items.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomPotion
{

    private String itemCode;
    private String itemName;
    private String description;
    private double salePrice;
    private double purchasePrice;
    private int amountForSale;
    private PotionEffectType effect;
    private int rColor;
    private int gColor;
    private int bColor;
    private int duration;
private int multiplier;
    private static HashMap<String, CustomPotion> items = new HashMap<>();

    public CustomPotion(String itemName, String description,
                        double salePrice, double purchasePrice, int amountForSale, int rColor, int gColor, int bColor,int duration, PotionEffectType effect, int multiplier) {
        this.itemCode = itemName;
        this.itemName = ChatColor.WHITE+itemName;
        this.description = ChatColor.GRAY+description;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
        this.amountForSale = amountForSale;
        this.rColor = rColor;
        this.gColor = gColor;
        this.bColor = bColor;
        this.effect = effect;
        this.duration = duration;
        this.multiplier = multiplier;
        items.put(this.itemCode, this);
        // ItemTable.addItem(this);
    }

    public static HashMap<String,CustomPotion> getPotions()
    {
        return items;
    }
    public static CustomPotion getCustomPotion(String itemCode){
        if(items.containsKey(itemCode)){
            return items.get(itemCode);
        }
        return null;
    }
    public ItemStack getPotionForSale(){
        ItemStack item = getItemStack();
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        String price = String.valueOf(ChatColor.GRAY)+this.purchasePrice +"g";
        lore.add(ChatColor.GRAY+"Click to purchase!");
        lore.add(price);
        meta.setLore(lore);
        item.setAmount(this.amountForSale);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getItemStack(){
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.setDisplayName(this.itemName);
        List<String> lore = new ArrayList<>();
        if(description.length() > 0){
            lore.add(description);
        }
        if(amountForSale != 0){
            item.setAmount(amountForSale);
        }
        meta.setColor(Color.fromRGB(rColor,gColor,bColor));
        meta.addCustomEffect(new PotionEffect(effect,duration,multiplier,false),false);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getAmountForSale() {
        return amountForSale;
    }

    public void setAmountForSale(int amountForSale) {
        this.amountForSale = amountForSale;
    }

    public PotionEffectType getEffect() {
        return effect;
    }

    public void setEffect(PotionEffectType effect) {
        this.effect = effect;
    }

    public int getrColor() {
        return rColor;
    }

    public void setrColor(int rColor) {
        this.rColor = rColor;
    }

    public int getgColor() {
        return gColor;
    }

    public void setgColor(int gColor) {
        this.gColor = gColor;
    }

    public int getbColor() {
        return bColor;
    }

    public void setbColor(int bColor) {
        this.bColor = bColor;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public static HashMap<String, CustomPotion> getItems() {
        return items;
    }

    public static void setItems(HashMap<String, CustomPotion> items) {
        CustomPotion.items = items;
    }
}
