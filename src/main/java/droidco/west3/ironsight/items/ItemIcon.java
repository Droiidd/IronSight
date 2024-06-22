package droidco.west3.ironsight.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemIcon {
    private static final HashMap<String, ItemIcon> icons = new HashMap<>();
    private final String displayName;
    private final String iconCode;
    private final String description;
    private final Material material;

    public ItemIcon(String iconName, String iconCode, String description, Material material) {
        this.iconCode = iconCode;
        this.displayName = ChatColor.WHITE + iconName;
        this.description = description;
        this.material = material;
        icons.put(iconCode, this);
    }

    public static ItemIcon getIcon(String iconName) {
        if (icons.containsKey(iconName)) {
            return icons.get(iconName);
        }
        return null;
    }

    public ItemStack getItem() {
        ItemStack item = new ItemStack(this.material);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(this.displayName);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + description);
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
}
