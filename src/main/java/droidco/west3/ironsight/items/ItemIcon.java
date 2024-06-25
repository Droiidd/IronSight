package droidco.west3.ironsight.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemIcon {
  private static HashMap<String, ItemIcon> icons = new HashMap<>();
  private String displayName;
  private String iconCode;
  private String description;
  private Material material;

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
