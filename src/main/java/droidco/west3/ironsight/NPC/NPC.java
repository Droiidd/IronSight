package droidco.west3.ironsight.NPC;

import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.HashMap;

public class NPC {
    private String displayName;

    private NPCType type;

    private double x, y, z;

    private static HashMap<String, NPC> npcs = new HashMap<>();

    private ChatColor nameColor;

    private boolean isLegal;
    private boolean isOfficer;
    private FrontierLocation frontierLocation;

    private static HashMap<String, NPC> shoppingPlayers;

    public NPC(String displayName, NPCType type, double x, double y, double z, ChatColor nameColor, boolean isLegal, boolean isOfficer, FrontierLocation frontierLocation) {

        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
        this.nameColor = nameColor;
        this.isLegal = isLegal;
        this.isOfficer = isOfficer;
        npcs.put(displayName, this);
        this.displayName = String.valueOf(nameColor) + displayName;
        this.frontierLocation = frontierLocation;

    }

    public void addShoppingPlayer(Player p) {
        if (shoppingPlayers.containsKey(p.getUniqueId().toString())) {
            shoppingPlayers.replace(p.getUniqueId().toString(), this);
        }
        else {
            shoppingPlayers.put(p.getUniqueId().toString(), this);
        }
    }

    public void spawnNPC(Player p) {
        Villager npc = (Villager) p.getWorld().spawnEntity(new Location(p.getWorld(), this.x, this.y, this.z), EntityType.VILLAGER);
        npc.setAI(false);
        npc.setCustomName(displayName);
        npc.setCustomNameVisible(true);

    }
    public static NPC getNPC(String displayName) {
        return npcs.get(displayName);
    }

    public static HashMap<String, NPC> getNPCs() {
        return npcs;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static HashMap<String, NPC> getShoppingPlayers() {
        return shoppingPlayers;
    }

    public static void setShoppingPlayers(HashMap<String, NPC> shoppingPlayers) {
        NPC.shoppingPlayers = shoppingPlayers;
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
