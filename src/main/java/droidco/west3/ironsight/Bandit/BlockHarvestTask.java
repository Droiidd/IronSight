package droidco.west3.ironsight.Bandit;

import droidco.west3.ironsight.Globals.Utils.BlockType;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.IronSight;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import static droidco.west3.ironsight.Globals.Utils.GlobalUtils.displayParticles;

public class BlockHarvestTask extends BukkitRunnable {
    private ArrayList<BlockHarvestTask> tasks = new ArrayList<>();
    private final IronSight plugin;
    private int tick = 0;
    private final int maxTime = 30;

    private int timeElapsed = 0;
    private Bandit b;
    private Player p;

    private BlockType blockType;

    private Block block;

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
        displayParticles(block.getLocation(), Particle.GLOW, Particle.TOTEM, 8);
        switch (blockType) {
            case FOLIAGE -> {
                block.getLocation().getWorld().playSound(block.getLocation(), Sound.ENTITY_GLOW_SQUID_AMBIENT, 1, 1);
                block.getLocation().getWorld().playSound(block.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 2, 1);
                block.getLocation().getWorld().playSound(block.getLocation(), Sound.BLOCK_AZALEA_LEAVES_PLACE, 1, 0);
                block.getLocation().getWorld().playSound(block.getLocation(), Sound.BLOCK_SWEET_BERRY_BUSH_PLACE, 1, 1);
            }
            case MINERALS -> {

                if (chance % 2 == 0) {
                    block.getLocation().getWorld().playSound(block.getLocation(), Sound.BLOCK_GRINDSTONE_USE, 1, -1);
                } else {
                    block.getLocation().getWorld().playSound(block.getLocation(), Sound.ENTITY_VILLAGER_WORK_MASON, 1, 0);
                }
                displayParticles(block.getLocation(), Particle.CRIT, Particle.CLOUD, 8);
            }
        }
        block.setType(originalBlock);
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
