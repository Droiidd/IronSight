package droidco.west3.ironsight.bandit.tasks;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.globals.utils.GlobalUtils;
import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class BrokenWindowTask extends BukkitRunnable {
  private final IronSight plugin;
  private final int maxTime = 15;
  private final ArrayList<BrokenWindowTask> tasks = new ArrayList<>();
  private final Block block;
  private int tick = 0;
  private int timeElapsed = 0;

  public BrokenWindowTask(IronSight plugin, Block block) {
    this.tasks.add(this);
    this.plugin = plugin;
    this.runTaskTimer(plugin, 0, 10);
    this.block = block;
    block.setType(Material.FIRE);
  }

  public void stopTask() {
    block
        .getLocation()
        .getWorld()
        .playSound(block.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, -1);
    block
        .getLocation()
        .getWorld()
        .playSound(block.getLocation(), Sound.ENTITY_GENERIC_EXTINGUISH_FIRE, 1, 0);
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
