package droidco.west3.ironsight.Bandit.Officer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OfficerData {
    static private int sheriff_kills = 10;
    static private int deputy_kills = 30;
    static private int marshall_kills = 50;
    static private int sheriff_gold = 100;
    static private int deputy_gold = 300;
    static private int marshall_gold = 500;
    static private List<String> titles = Arrays.asList("Sheriff", "Deputy", "Marshall");

    static public int getSheriff_kills() {
        return sheriff_kills;
    }

    static public int getDeputy_kills() {
        return deputy_kills;
    }

    static public int getMarshall_kills() {
        return marshall_kills;
    }

    static public int getSheriff_gold() {
        return sheriff_gold;
    }

    static public int getDeputy_gold() {
        return deputy_gold;
    }

    static public int getMarshall_gold() {
        return marshall_gold;
    }
    static public List<String> getTitles(){
        return titles;
    }
    static public int[] getGoldArray(){
        int[] out = {sheriff_gold, deputy_gold, marshall_gold};
        return out;
    }
    static public int[] getKillsArray(){
        int[] out = {sheriff_kills, deputy_kills, marshall_kills};
        return out;
    }
}
