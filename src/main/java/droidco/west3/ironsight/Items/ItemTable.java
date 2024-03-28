package droidco.west3.ironsight.Items;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemTable {

    //Sum to 100
    //Rarities relative to other rarities
    private static int common = 30;
    private static int uncommon = 30;
    private static int rare = 30;
    private static int legendary = 100 - common - uncommon - rare;

    //rarity relative in a rarity class
    //i.e all ones = 60%, meaning twos are 40%
    private static int com_split = 50;
    private static int unc_split = 50;
    private static int rare_split = 50;
    private static int leg_split = 50;
    public ArrayList<CustomItem> one = new ArrayList<>();
    public ArrayList<CustomItem> two = new ArrayList<>();
    public ArrayList<CustomItem> thr = new ArrayList<>();
    public ArrayList<CustomItem> fou = new ArrayList<>();
    public ArrayList<CustomItem> fiv = new ArrayList<>();
    public ArrayList<CustomItem> six = new ArrayList<>();
    public ArrayList<CustomItem> sev = new ArrayList<>();
    public ArrayList<CustomItem> eig = new ArrayList<>();
    private static HashMap<String, ItemTable> tables = new HashMap<String, ItemTable>() {
    };
    public ItemTable(String items[], String name){

        for (int i = 0; i < items.length; i++){
            CustomItem item = CustomItem.getCustomItem(items[i]);
            switch (item.getRarity()){
                case 1: one.add(item);
                case 2: two.add(item);
                case 3: thr.add(item);
                case 4: fou.add(item);
                case 5: fiv.add(item);
                case 6: six.add(item);
                case 7: sev.add(item);
                case 8: eig.add(item);
            }
        }
        tables.put(name, this);
    }

    public CustomItem getItem(String rarity){
        int split = (int)(Math.random() * 100);
        switch (rarity) {
            case "Common":{
                if (split < com_split){
                    return one.get((int)(Math.random()*one.size()));
                }
                else{
                    return two.get((int)(Math.random()*two.size()));
                }
            }
            case "Uncommon":{
                if (split < unc_split){
                    return thr.get((int)(Math.random()*thr.size()));
                }
                else{
                    return fou.get((int)(Math.random()*fou.size()));
                }
            }
            case "Rare":{
                if (split < rare_split) {
                    return fiv.get((int)(Math.random()*fiv.size()));
                }
                else{
                    return six.get((int)(Math.random()*six.size()));
                }
            }
            case "Legendary":{
                if (split < leg_split){
                    return sev.get((int)(Math.random()*sev.size()));
                }
                else{
                    return eig.get((int)(Math.random()*eig.size()));
                }
            }
        }
        return null;
    }


    public ArrayList<ItemStack> getNumItems(int num_items){
        ArrayList<ItemStack> out = new ArrayList<>();

        for (int i = 0; i < num_items; i++){
            int rand = (int)(Math.random() * 100);
            if (rand < common){
                out.add(getItem("Common").getItemStack());
            }
            else if (rand < uncommon){
                out.add(getItem("Uncommon").getItemStack());
            }
            else if (rand < rare){
                out.add(getItem("Rare").getItemStack());
            }
            else{
                out.add(getItem("Legendary").getItemStack());
            }

        }
        return out;
    }

    public static ItemTable getTable(String name){
        return tables.get(name);
    }

}
