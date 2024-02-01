package droidco.west3.ironsight.Objects.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.HashMap;

public class CustomItem {
    private String itemCode;
    private String itemName;
    private String rarityLore;
    private String loreLine2;
    private String loreLine3;
    private Material material;
    private static HashMap<String,CustomItem> items = new HashMap<>();

    public CustomItem(String itemCode, String itemName, Rarity rarity, String loreLine2, String loreLine3, Material material) {
        this.itemCode = itemCode;
        this.itemName = ChatColor.WHITE+itemName;
        this.rarityLore = getRarityString(rarity);
        this.loreLine2 = loreLine2;
        this.loreLine3 = loreLine3;
        this.material = material;
        items.put(this.itemCode, this);
    }
    public String getRarityString(Rarity rarity){
        switch(rarity){
            case COMMON -> {
                return ChatColor.WHITE+String.valueOf(ChatColor.BOLD)+"Common";
            }
            case UNCOMMON -> {
                return ChatColor.DARK_GREEN.toString()+String.valueOf(ChatColor.BOLD)+"Uncommon";
            }
            case RARE -> {
                return ChatColor.LIGHT_PURPLE.toString()+String.valueOf(ChatColor.BOLD)+"Rare";
            }
            case LEGENDARY -> {
                return ChatColor.YELLOW.toString()+String.valueOf(ChatColor.BOLD)+"Legendary";
            }
            case FORAGE -> {
                return ChatColor.GREEN.toString()+String.valueOf(ChatColor.BOLD)+"Forage";
            }
            case ILLEGAL -> {
                return ChatColor.RED.toString()+String.valueOf(ChatColor.BOLD)+"Illegal";
            }
            case OFFICER -> {
                return ChatColor.DARK_AQUA.toString()+String.valueOf(ChatColor.BOLD)+"Officer";
            }
            case LEGAL -> {
                return ChatColor.BLUE.toString()+String.valueOf(ChatColor.BOLD)+"Legal";
            }
        }
        return "";
    }

    public CustomItem getCustomItem(String itemCode){
        if(items.containsKey(itemCode)){
            return items.get(itemCode);
        }
        return null;
    }
}
