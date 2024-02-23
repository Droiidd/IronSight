package droidco.west3.ironsight.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemIcon
{
    private String displayName;
    private String iconCode;
    private String description;
    private Material material;
    private static HashMap<String, ItemIcon> icons = new HashMap<>();
    public ItemIcon(String iconName, String description,Material material){
        this.iconCode = iconName;
        this.displayName = ChatColor.WHITE+iconName;
        this.description = description;
        this.material = material;
        icons.put(iconName, this);
    }
    public ItemStack getItem(){
        ItemStack item = new ItemStack(this.material);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(this.displayName);
        List<String> lore = new ArrayList<>();
        lore.add(description);
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public static ItemIcon getIcon(String iconName){
        if(icons.containsKey(iconName)){
            return icons.get(iconName);
        }
        return null;
    }
}
