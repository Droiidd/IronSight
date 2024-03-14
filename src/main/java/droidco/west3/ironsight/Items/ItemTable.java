package droidco.west3.ironsight.Items;

import java.util.ArrayList;

public class ItemTable {
    private static ArrayList<CustomItem> one = new ArrayList<>();
    private static ArrayList<CustomItem> two = new ArrayList<>();
    private static ArrayList<CustomItem> thr = new ArrayList<>();
    private static ArrayList<CustomItem> fou = new ArrayList<>();
    private static ArrayList<CustomItem> fiv = new ArrayList<>();
    private static ArrayList<CustomItem> six = new ArrayList<>();
    private static ArrayList<CustomItem> sev = new ArrayList<>();
    private static ArrayList<CustomItem> eig = new ArrayList<>();
    //Sum to 100
    //Rarities relative to other rarities
    private int common = 30;
    private int uncommon = 30;
    private int rare = 30;
    private int legendary = 100 - common - uncommon - rare;

    //rarity relative in a rarity class
    //i.e all ones = 60%, meaning twos are 40%
    private int com_split = 50;
    private int unc_split = 50;
    private int rare_split = 50;
    private int leg_split = 50;
    public CustomItem getItem(String rarity){
        int split = (int)Math.random() * 100;
        switch (rarity) {
            case "Common":{
                if (split < com_split){
                    return one.get((int)Math.random()*one.size());
                }
                else{
                    return two.get((int)Math.random()*one.size());
                }
            }
            case "Uncommon":{
                if (split < unc_split){
                    return thr.get((int)Math.random()*one.size());
                }
                else{
                    return fou.get((int)Math.random()*one.size());
                }
            }
            case "Rare":{
                if (split < rare_split) {
                    return fiv.get((int)Math.random()*one.size());
                }
                else{
                    return six.get((int)Math.random()*one.size());
                }
            }
            case "Legendary":{
                if (split < leg_split){
                    return sev.get((int)Math.random()*one.size());
                }
                else{
                    return eig.get((int)Math.random()*one.size());
                }
            }
        }
        return null;
    }

    public static void addItem(CustomItem item){
        int rarity = item.getRarity();
        switch (rarity) {
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

    public ArrayList<CustomItem> getNumItems(int num_items){
        ArrayList<CustomItem> out = new ArrayList<>();

        for (int i = 0; i < num_items; i++){
            int rand = (int)(Math.random() * 100);
            if (rand < common){
                out.add(getItem("Common"));
            }
            else if (rand < uncommon){
                out.add(getItem("Uncommon"));
            }
            else if (rand < rare){
                out.add(getItem("Rare"));
            }
            else{
                out.add(getItem("Legendary"));
            }

        }
        return out;
    }

    public ArrayList<CustomItem> getCommonList(){
        ArrayList<CustomItem> out = new ArrayList<>();
        out.addAll(one);
        out.addAll(two);
        return out;
    }

    public ArrayList<CustomItem> getUncommonList(){
        ArrayList<CustomItem> out = new ArrayList<>();
        out.addAll(thr);
        out.addAll(fou);
        return out;
    }
    public ArrayList<CustomItem> getRareList(){
        ArrayList<CustomItem> out = new ArrayList<>();
        out.addAll(fiv);
        out.addAll(six);
        return out;
    }
    public ArrayList<CustomItem> getLegendaryList(){
        ArrayList<CustomItem> out = new ArrayList<>();
        out.addAll(sev);
        out.addAll(eig);
        return out;
    }
}
