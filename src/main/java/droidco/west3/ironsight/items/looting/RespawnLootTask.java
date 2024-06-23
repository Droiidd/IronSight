package droidco.west3.ironsight.items.looting;

import droidco.west3.ironsight.globals.utils.GlobalUtils;
import droidco.west3.ironsight.globals.utils.Hologram;
import droidco.west3.ironsight.IronSight;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class RespawnLootTask extends BukkitRunnable {
    private final ArrayList<RespawnLootTask> tasks = new ArrayList<>();
    private int tick;
    private int seconds;
    private final int respawnTime;
    private final Location blockLoc;
    private IronSight plugin;
    private double currentTime;
    private final Hologram hologram;

    //PlayerCore core,
    public RespawnLootTask(IronSight plugin, int respawnTime, Location blockLoc, ItemStack[] prevContents) {
        this.plugin = plugin;
        tick = 0;
        seconds = 0;
        this.respawnTime = respawnTime;
        this.blockLoc = blockLoc;
        this.currentTime = 0;
        tasks.add(this);

        Location hologramLoc = new Location(blockLoc.getWorld(), blockLoc.getX() + 0.5, blockLoc.getY() - 1, blockLoc.getZ() + 0.5);
        hologram = new Hologram(plugin, hologramLoc, "");
        this.runTaskTimer(plugin, 0, 10);
        blockLoc.getBlock().setType(Material.TRAPPED_CHEST);
        blockLoc.getWorld().playSound(blockLoc, Sound.BLOCK_IRON_TRAPDOOR_OPEN, 1, 1);
        Block newChest = blockLoc.getBlock();
        Chest chest = (Chest) newChest.getState();
        chest.getInventory().setContents(prevContents);

    }

    @Override
    public void run() {
        if (tick % 2 == 0) {
            //Check respawn time
            seconds++;
            currentTime++;
            if (seconds == respawnTime) {
                //RESPAWN CROP HERE
                blockLoc.getBlock().setType(Material.CHEST);//Replaces the stone to its previous ore block
                blockLoc.getWorld().playSound(blockLoc, Sound.BLOCK_AMETHYST_BLOCK_CHIME, 1, 1);
                blockLoc.getWorld().playSound(blockLoc, Sound.BLOCK_BARREL_OPEN, 1, 1);
                blockLoc.getWorld().playSound(blockLoc, Sound.BLOCK_BAMBOO_HIT, 1, 0);
                GlobalUtils.displayParticles(blockLoc, Particle.COMPOSTER, Particle.VILLAGER_HAPPY, 5);
                clearBar();
                //CANCEL TASK HERE
                this.cancel();
                tasks.remove(this);
                return;
            }

        }
        double percent = Math.floor((currentTime * 25.0 / respawnTime) * 25.0) / 25.0;
        updatePercentageBar(percent);
        tick++;

    }

    private void updatePercentageBar(double percent) {
        int left = (int) (25.0 - percent);
        StringBuilder proccess = new StringBuilder();

        for (int i = 0; i < percent; i++) {
            proccess.append(ChatColor.GREEN).append("|");
        }

        for (int i = 0; i < left; i++) {
            proccess.append(ChatColor.RED).append("|");
        }
        //CREATE HOLOGRAM HERE
        hologram.updateHologram(proccess + "   " + ChatColor.WHITE + ((int) percent * 4) + "%");
    }

    private void clearBar() {
        hologram.removeHologram();
    }
}