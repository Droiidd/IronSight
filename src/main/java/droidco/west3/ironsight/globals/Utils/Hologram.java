package droidco.west3.ironsight.globals.Utils;

import droidco.west3.ironsight.IronSight;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;

public class Hologram {
    private final Location location;
    private final String title;
    private final ArrayList<Hologram> hologramList = new ArrayList<>();
    private final ArmorStand hologram;
    private final IronSight plugin;

    public Hologram(IronSight plugin, Location location, String title) {
        this.location = location;
        this.title = title;
        hologramList.add(this);
        this.plugin = plugin;
        hologram = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        updateHologram(title);
    }

    public void updateHologram(String title) {
        hologram.setVisible(false);
        hologram.setCustomNameVisible(true);
        hologram.setCustomName(title);
        hologram.setInvulnerable(true);
    }

    public void spawnHologram() {

    }

    public void removeHologram() {
        hologram.remove();
    }
}