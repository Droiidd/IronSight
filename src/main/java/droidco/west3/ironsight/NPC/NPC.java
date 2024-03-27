package droidco.west3.ironsight.NPC;

import org.bukkit.ChatColor;

import java.util.HashMap;

public class NPC {
    private String displayName;

    private NPCType type;

    private double x, y, z;

    private static HashMap<String, NPC> npcs = new HashMap<>();

    private ChatColor nameColor;

    private boolean isLegal;
    private boolean isOfficer;

    public NPC(String displayName, NPCType type, double x, double y, double z, ChatColor nameColor, boolean isLegal, boolean isOfficer) {

        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
        this.nameColor = nameColor;
        this.isLegal = isLegal;
        this.isOfficer = isOfficer;
        npcs.put(displayName, this);
        this.displayName = String.valueOf(nameColor) + displayName;

    }

    public static NPC getNPC(String displayName) {
        return npcs.get(displayName);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public NPCType getType() {
        return type;
    }

    public void setType(NPCType type) {
        this.type = type;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public HashMap<String, NPC> getNpcs() {
        return npcs;
    }

    public void setNpcs(HashMap<String, NPC> npcs) {
        this.npcs = npcs;
    }

    public ChatColor getNameColor() {
        return nameColor;
    }

    public void setNameColor(ChatColor nameColor) {
        this.nameColor = nameColor;
    }

    public boolean isLegal() {
        return isLegal;
    }

    public void setLegal(boolean legal) {
        isLegal = legal;
    }

    public boolean isOfficer() {
        return isOfficer;
    }

    public void setOfficer(boolean officer) {
        isOfficer = officer;
    }
}
