package droidco.west3.ironsight.Items;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

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
    private HashMap<String, Quantity> quantities;
    private static HashMap<String, ItemTable> tables = new HashMap<String, ItemTable>() {
    };
    public ItemTable(HashMap<String, Quantity> items_map, String name){
        String[] items = items_map.keySet().toArray(new String[0]);
        for (int i = 0; i < items.length; i++) {
            CustomItem item = CustomItem.getCustomItem(items[i]);
            if (item == null) {
                System.out.println("\nItemTables init" + items[i] + " is not in CustomItems.items\n");
            } else {
                switch (item.getRarity()) {
                    case 1:
                        one.add(item);
                    case 2:
                        two.add(item);
                    case 3:
                        thr.add(item);
                    case 4:
                        fou.add(item);
                    case 5:
                        fiv.add(item);
                    case 6:
                        six.add(item);
                    case 7:
                        sev.add(item);
                    case 8:
                        eig.add(item);
                }
            }
        }
        this.quantities = items_map;
        tables.put(name, this);
    }

    public CustomItem getItem(int rarity){
        int split = (int)(Math.random() * 100);
        if (rarity == 0){
                if (!one.isEmpty() && split < com_split ){
                    return one.get((int)(Math.random()*one.size()));
                }
                else if (!two.isEmpty()){
                    return two.get((int)(Math.random()*two.size()));
                }
            }
        if (rarity <= 1){
                if (!thr.isEmpty() && split < unc_split){
                    return thr.get((int)(Math.random()*thr.size()));
                }
                else if (!fou.isEmpty()){
                    return fou.get((int)(Math.random()*fou.size()));
                }
            }
        if (rarity <= 2){
                if (!fiv.isEmpty() && split < rare_split) {
                    return fiv.get((int)(Math.random()*fiv.size()));
                }
                else if (!six.isEmpty()){
                    return six.get((int)(Math.random()*six.size()));
                }
            }
        if (rarity <= 3){
                if (!sev.isEmpty() && split < leg_split){
                    return sev.get((int)(Math.random()*sev.size()));
                }
                else if (!eig.isEmpty()){
                    return eig.get((int)(Math.random()*eig.size()));
                }
            }
        return null;
    }


    public ArrayList<ItemStack> getNumItems(int num_items){
        ArrayList<ItemStack> out = new ArrayList<>();

        for (int i = 0; i < num_items; i++){
            int rand = (int)(Math.random() * 100);
            CustomItem item;
            if (rand < common){
                item = getItem(0);
            }
            else if (rand < uncommon){
                item = getItem(1);
            }
            else if (rand < rare){
                item = getItem(2);
            }
            else{
                item = getItem(3);
            }
            int quant = quantities.get(item.getItemCode()).getNum();
            if (quant != 1){
                out.add(item.getItemStack(quant));
                continue;
            }
            else {
                out.add(item.getItemStack());
            }

        }
        return out;
    }

    public static ItemTable getTable(String name){
        return tables.get(name);
    }

}
