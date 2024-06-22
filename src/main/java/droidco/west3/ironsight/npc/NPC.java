package droidco.west3.ironsight.npc;

import droidco.west3.ironsight.frontierlocation.FrontierLocation;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.*;

public class NPC {
    private static HashMap<String, NPC> npcs = new HashMap<>();
    private static HashMap<String, NPC> shoppingPlayers = new HashMap<>();
    private static HashMap<UUID, LivingEntity> entities = new HashMap<>();
    private static HashMap<UUID, NPC> npcsById = new HashMap<>();
    private String displayName;
    private String keyName;
    private NPCType type;
    private double x, y, z;
    private ChatColor nameColor;
    private boolean isLegal;
    private boolean isOfficer;
    private FrontierLocation frontierLocation;
    private UUID npcId;

    public NPC(String displayName, NPCType type, double x, double y, double z, ChatColor nameColor, boolean isLegal, boolean isOfficer, FrontierLocation frontierLocation) {

        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
        this.nameColor = nameColor;
        this.isLegal = isLegal;
        this.isOfficer = isOfficer;
        this.displayName = String.valueOf(nameColor) + displayName;
        System.out.println("Display: " + displayName);
        this.frontierLocation = frontierLocation;
        this.keyName = displayName + frontierLocation.getLocName();
        npcs.put(displayName + frontierLocation.getLocName(), this);
    }

    public static List<NPC> getNPCsByType(NPCType type) {
        List<NPC> list = new ArrayList<>();
        for (Map.Entry<String, NPC> npc : npcs.entrySet()) {
            if (npc.getValue().getType() == type) {
                list.add(npc.getValue());
            }
        }
        return list;
    }

    public static NPC getNPC(String displayName) {
        return npcs.get(displayName);
    }

    public static HashMap<UUID, LivingEntity> getEntities() {
        return entities;
    }

    public static void setEntities(HashMap<UUID, LivingEntity> entities) {
        NPC.entities = entities;
    }

    public static HashMap<String, NPC> getNPCs() {
        return npcs;
    }

    public static HashMap<String, NPC> getShoppingPlayers() {
        return shoppingPlayers;
    }

    public static void setShoppingPlayers(HashMap<String, NPC> shoppingPlayers) {
        NPC.shoppingPlayers = shoppingPlayers;
    }

    public static HashMap<UUID, NPC> getNpcsById() {
        return npcsById;
    }

    public static void setNpcsById(HashMap<UUID, NPC> npcsById) {
        NPC.npcsById = npcsById;
    }

    public void addShoppingPlayer(Player p) {
        if (shoppingPlayers.containsKey(p.getUniqueId().toString())) {
            shoppingPlayers.replace(p.getUniqueId().toString(), this);
        } else {
            shoppingPlayers.put(p.getUniqueId().toString(), this);
        }
    }

    public void spawnNPC(Player p) {
        Villager npc = (Villager) p.getWorld().spawnEntity(new Location(p.getWorld(), this.x, this.y, this.z), EntityType.VILLAGER);
        npc.setAI(false);
        npc.setCustomName(displayName);
        npc.setCustomNameVisible(true);
        npc.setInvulnerable(true);
        switch (this.type) {
            case BANKER -> {
                npc.setProfession(Villager.Profession.CARTOGRAPHER);
                npc.setVillagerType(Villager.Type.SAVANNA);
            }
            case CONTRACTOR -> {
                npc.setProfession(Villager.Profession.WEAPONSMITH);
                npc.setVillagerType(Villager.Type.SNOW);
            }
        }
        this.npcId = npc.getUniqueId();
        entities.put(npc.getUniqueId(), npc);
        npcsById.put(npc.getUniqueId(), this);
    }

    public NPC getNPCByUUID(UUID id) {
        return npcsById.get(id);
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
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
        NPC.npcs = npcs;
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

    public FrontierLocation getFrontierLocation() {
        return frontierLocation;
    }

    public void setFrontierLocation(FrontierLocation frontierLocation) {
        this.frontierLocation = frontierLocation;
    }
}
