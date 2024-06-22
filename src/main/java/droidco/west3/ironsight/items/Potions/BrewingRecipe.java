package droidco.west3.ironsight.items.Potions;

import droidco.west3.ironsight.IronSight;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.BrewingStand;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BrewingRecipe {
    private static final List<BrewingRecipe> recipes = new ArrayList<BrewingRecipe>();
    private final ItemStack ingredient;
    private final boolean perfect;
    private final String name;
    private final PotionEffect effect;
    private final Color color;
    String description;

    public BrewingRecipe(String name, ItemStack ingredient, boolean perfect, PotionEffectType type, int amplifier, int duration_secs, Color color, String description) {
        this.ingredient = ingredient;
        this.name = name;
        this.effect = new PotionEffect(type, duration_secs * 20, amplifier);
        this.perfect = perfect;
        this.color = color;
        this.description = description;
        recipes.add(this);
    }

    @Nullable
    public static BrewingRecipe getRecipe(BrewerInventory inventory) {
        if (inventory.getIngredient() == null) return null;
        for (BrewingRecipe recipe : recipes) {
            if (!recipe.isPerfect() && inventory.getIngredient().getType() == recipe.getIngredient().getType()) {
                return recipe;
            }
            if (recipe.isPerfect() && inventory.getIngredient().isSimilar(recipe.getIngredient())) {
                return recipe;
            }
        }
        return null;
    }

    public ItemStack Brew() {
        {//Some lambda magic
            ItemStack potion = new ItemStack(Material.POTION);
            PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();
            potionMeta.setDisplayName(this.name);
            // set potion data:
            potionMeta.addCustomEffect(this.effect, true);
            potionMeta.setColor(this.color);
            ArrayList<String> list = new ArrayList<>();
            list.add(this.description);
            potionMeta.setLore(list);
            // set potion meta:
            potion.setItemMeta(potionMeta);
            return potion;

        }
    }

    public boolean isPerfect() {
        return perfect;
    }

    public ItemStack getIngredient() {
        return ingredient;
    }

    public void startBrewing(BrewerInventory inventory) {
        new BrewClock(this, inventory);
    }

    private class BrewClock extends BukkitRunnable {
        private final BrewerInventory inventory;
        private final BrewingRecipe recipe;
        private final ItemStack ingredients;
        private final BrewingStand stand;
        private int time = 400; //Like I said the starting time is 400

        public BrewClock(BrewingRecipe recipe, BrewerInventory inventory) {
            this.recipe = recipe;
            this.inventory = inventory;
            this.ingredients = inventory.getIngredient();
            this.stand = inventory.getHolder();
            runTaskTimer(IronSight.instance, 0L, 1L);
        }

        @Override
        public void run() {
            if (time == 0) {
                ItemStack[] contents = inventory.getContents();
                boolean made = false;
                for (int i = 0; i < 3; i++) {
                    if (contents[i] != null && contents[i].getType() == Material.POTION) {
                        inventory.setItem(i, recipe.Brew());
                        made = true;
                    }
                }
                if (made) {
                    inventory.setIngredient(new ItemStack(Material.AIR));
                }
                cancel();
                return;
            }
            if (inventory.getIngredient() == null) {
                stand.setBrewingTime(400); //Reseting everything
                cancel();
                return;
            }
            //You should also add here a check to make sure that there are still items to brew
            time--;
            stand.setBrewingTime(time);
        }
    }

}
