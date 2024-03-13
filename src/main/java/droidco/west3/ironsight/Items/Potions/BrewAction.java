package droidco.west3.ironsight.Items.Potions;

import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;

/*
Interface for creating BrewActions

BrewActions call brew() on completion of a potion. This is what handles removing ingredients, and giving the player the item
I used labmda notation in the GameContentLoader, though it is mostly boilerplate
 */
public interface BrewAction {
    public ItemStack brew(BrewerInventory inventory, ItemStack ingredient);
}
