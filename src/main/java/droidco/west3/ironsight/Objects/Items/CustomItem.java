package droidco.west3.ironsight.Objects.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.HashMap;

public class
CustomItem {
    private String itemCode;
    private String itemName;
    private String rarityLore;
    private String description;
    private String loreLine3;
    private boolean isLegal;
    private boolean isOfficer;
    private Material material;
    private static HashMap<String,CustomItem> items = new HashMap<>();

    public CustomItem(String itemName, int rarity, boolean isLegal, boolean isOfficer,String description, Material material) {
        this.itemCode = itemName;
        this.itemName = ChatColor.WHITE+itemName;
        this.rarityLore = getRarityString(rarity);
        this.isLegal = isLegal;
        this.isOfficer = isOfficer;
        this.description = ChatColor.DARK_GRAY+description;
        this.material = material;
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

    public CustomItem getCustomItem(String itemCode){
        if(items.containsKey(itemCode)){
            return items.get(itemCode);
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
}