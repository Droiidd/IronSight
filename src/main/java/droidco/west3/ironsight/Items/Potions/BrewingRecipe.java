package droidco.west3.ironsight.Items.Potions;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import droidco.west3.ironsight.IronSight;
import org.bukkit.Material;
import org.bukkit.block.BrewingStand;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class BrewingRecipe {
    private static List<BrewingRecipe> recipes = new ArrayList<BrewingRecipe>();
    private ItemStack ingredient;
    private BrewAction action;
    private boolean perfect;

    public BrewingRecipe(ItemStack ingredient , BrewAction action , boolean perfect) {
        this.ingredient = ingredient;
        this.action = action;
        this.perfect = perfect;
        recipes.add(this);
    }

    public BrewingRecipe(Material ingredient , BrewAction action)
    {
        this(new ItemStack(ingredient), action, false);
    }

    public boolean isPerfect() {
        return perfect;
    }
    @Nullable
    public static BrewingRecipe getRecipe(BrewerInventory inventory)
    {
        if (inventory.getIngredient() == null) return null;
        for(BrewingRecipe recipe : recipes)
        {
            System.out.println("in getrecipe");
            System.out.println(recipe.getIngredient().getType());
            if(!recipe.isPerfect() && inventory.getIngredient().getType() == recipe.getIngredient().getType())
            {
                return recipe;
            }
            if(recipe.isPerfect() && inventory.getIngredient().isSimilar(recipe.getIngredient()))
            {
                return recipe;
            }
        }
        return null;
    }
    public ItemStack getIngredient(){
        return ingredient;
    }

    public BrewAction getAction() {
        return action;
    }
    public void startBrewing(BrewerInventory inventory)
    {
        new BrewClock(this, inventory);
    }

    private class BrewClock extends BukkitRunnable
    {
        private BrewerInventory inventory;
        private BrewingRecipe recipe;
        private ItemStack ingredients;
        private BrewingStand stand;
        private int time = 400; //Like I said the starting time is 400

        public BrewClock(BrewingRecipe recipe , BrewerInventory inventory) {
            System.out.println("BrewClock Created");
            this.recipe = recipe;
            this.inventory = inventory;
            this.ingredients = inventory.getIngredient();
            this.stand = inventory.getHolder();
            runTaskTimer(IronSight.instance, 0L, 1L);
        }

        @Override
        public void run() {
            System.out.println("Time: " + time);
            if(time == 0)
            {
                System.out.println("In time 0");

                inventory.setItem(0, recipe.getAction().brew(inventory, inventory.getIngredient()));
                inventory.setIngredient(new ItemStack(Material.AIR));
                System.out.println("before cancel");
                cancel();
                return;
            }
            if(!inventory.getIngredient().isSimilar(ingredients))
            {
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
