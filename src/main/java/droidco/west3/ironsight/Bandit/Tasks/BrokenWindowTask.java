package droidco.west3.ironsight.Bandit.Tasks;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.BlockHarvesting.BlockHarvestTask;
import droidco.west3.ironsight.Globals.Utils.BlockType;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.IronSight;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class BrokenWindowTask extends BukkitRunnable {
    private ArrayList<BrokenWindowTask> tasks = new ArrayList<>();
    private final IronSight plugin;
    private int tick = 0;
    private final int maxTime = 15;

    private int timeElapsed = 0;
    private Block block;

    public BrokenWindowTask(IronSight plugin,  Block block) {
        this.tasks.add(this);
        this.plugin = plugin;
        this.runTaskTimer(plugin, 0, 10);
        this.block = block;
        block.setType(Material.FIRE);

    }

    public void stopTask() {
                    block.getLocation().getWorld().playSound(block.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, -1);
                    block.getLocation().getWorld().playSound(block.getLocation(), Sound.ENTITY_GENERIC_EXTINGUISH_FIRE, 1, 0);
                    block.setType(Material.AIR);
                    GlobalUtils.displayParticles(block.getLocation(), Particle.CRIT, Particle.CLOUD, 8);
        this.cancel();
        tasks.remove(this);
    }

    @Override
    public void run() {

        if (tick % 3 == 0) {
            timeElapsed++;
        }
        if (timeElapsed == maxTime) {
            stopTask();
        }

        tick++;
    }
 }