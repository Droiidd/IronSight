package droidco.west3.ironsight.blockharvesting;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.bandit.Bandit;
import droidco.west3.ironsight.globals.Utils.BlockType;
import droidco.west3.ironsight.globals.Utils.GlobalUtils;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class BlockHarvestTask extends BukkitRunnable {
    private final IronSight plugin;
    private final int maxTime = 30;
    private final ArrayList<BlockHarvestTask> tasks = new ArrayList<>();
    private final Bandit b;
    private final Player p;
    private final BlockType blockType;
    private final Block block;
    private int tick = 0;
    private int timeElapsed = 0;
    private Material harvestedBlock;

    private Material originalBlock;

    public BlockHarvestTask(IronSight plugin, Player p, Block block, BlockType blockType) {
        this.b = Bandit.getPlayer(p);
        this.p = p;
        this.tasks.add(this);
        this.plugin = plugin;
        this.runTaskTimer(plugin, 0, 10);
        this.block = block;
        this.blockType = blockType;
        this.originalBlock = block.getType();

        switch (blockType) {
            case FOLIAGE -> {
                harvestedBlock = Material.MANGROVE_PROPAGULE;
            }

            case MINERALS -> {
                harvestedBlock = Material.COBBLED_DEEPSLATE;
            }
        }

        block.setType(harvestedBlock);

    }

    public void stopTask() {
        int chance = GlobalUtils.getRandomNumber(1000);
        switch (blockType) {
            case FOLIAGE -> {
                block.getLocation().getWorld().playSound(block.getLocation(), Sound.ENTITY_GLOW_SQUID_AMBIENT, 1, 1);
                block.getLocation().getWorld().playSound(block.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 2, 1);
                block.getLocation().getWorld().playSound(block.getLocation(), Sound.BLOCK_AZALEA_LEAVES_PLACE, 1, 0);
                block.getLocation().getWorld().playSound(block.getLocation(), Sound.BLOCK_SWEET_BERRY_BUSH_PLACE, 1, 1);
                GlobalUtils.displayParticles(block.getLocation(), Particle.GLOW, Particle.TOTEM, 8);
                block.setType(originalBlock);
            }
            case MINERALS -> {

                if (chance % 2 == 0) {
                    block.getLocation().getWorld().playSound(block.getLocation(), Sound.BLOCK_GRINDSTONE_USE, 1, -1);
                } else {
                    block.getLocation().getWorld().playSound(block.getLocation(), Sound.ENTITY_VILLAGER_WORK_MASON, 1, 0);
                }

                int oreOdds = GlobalUtils.getRandomNumber(101);
                if (oreOdds < 10) {
                    this.originalBlock = Material.GOLD_ORE;
                } else if (oreOdds >= 10 && oreOdds < 50) {
                    this.originalBlock = Material.COPPER_ORE;
                } else {
                    this.originalBlock = Material.IRON_ORE;
                }
                int clusterChance = GlobalUtils.getRandomNumber(101);
                if (clusterChance < 5) {
                    block.setType(getCluster(this.originalBlock) != null ? getCluster(this.originalBlock) : Material.RAW_IRON);
                } else {
                    block.setType(this.originalBlock);
                }
                GlobalUtils.displayParticles(block.getLocation(), Particle.CRIT, Particle.CLOUD, 8);
            }
        }

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

    public Material getCluster(Material oreType) {
        switch (oreType) {
            case IRON_ORE -> {
                return Material.RAW_IRON_BLOCK;
            }
            case COPPER_ORE -> {
                return Material.RAW_COPPER_BLOCK;
            }
            case GOLD_ORE -> {
                return Material.RAW_GOLD_BLOCK;
            }
        }
        return null;
    }
}
