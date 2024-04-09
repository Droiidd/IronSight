package droidco.west3.ironsight.Processors;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.Globals.Utils.Hologram;
import droidco.west3.ironsight.IronSight;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class ProcessorTask extends BukkitRunnable {
    public static ArrayList<ProcessorTask> tasks = new ArrayList<>();
    private final Player p;
    private final ItemStack input;
    private final double value;
    private final Processor processor;
    private int tick = 0;
    private final double processTime;
    private double currentTime;
    private final int unprocAmount;
    private final Location procLocation;
    private final IronSight plugin;
    private final Hologram hologram;
    private final String processorType;
    private double seconds = 0;

    public ProcessorTask(String processorType, Processor processor, IronSight plugin, Player p, double processTime, ItemStack input, double value, int unprocAmount, Location procLocation) {
        this.processor = processor;
        this.plugin = plugin;
        this.p = p;
        this.processorType = processorType;
        this.input = input;
        this.value = value;
        this.currentTime = 0;
        this.unprocAmount = unprocAmount;
        this.procLocation = procLocation;
        hologram = new Hologram(plugin, procLocation, "");
//        this.itemBeingProcessed = (input.hasItemMeta() && input.getItemMeta().hasDisplayName())
//                ? ChatColor.stripColor(input.getItemMeta().getDisplayName())
//                : StringUtils.capitalize(input.getType().name());
        this.processTime = processTime;
        processor.setProcessing(true);

//        input.setAmount(1);
//        output.setAmount(1);

        tasks.add(this);

        this.runTaskTimer(plugin, 0, 1);
    }

    @Override
    public void run() {
        if (tick == 0) {
            if (!startProcess())
                return;
        }

        double percent = Math.floor((currentTime * 50 / processTime) * 50.0) / 50.0;
        if (seconds == processTime) {
            finishProcess();
            return;
        }
        if (tick % 5 == 0) {
            //p.sendMessage(""+seconds+"");
            seconds++;
            currentTime += 1;
        }

        if (!p.isOnline()) {
            cancelProcess();
            return;
        }

        if (tick % 5 == 0 && p.getLocation().distance(procLocation) > 4) {
            cancelProcess();
            p.sendMessage(ChatColor.GRAY + String.valueOf(ChatColor.ITALIC) + "Out of range...");
            return;
        }

        updatePercentageBar(percent);
        tick++;
    }

    private boolean startProcess() {
//        if (Officer.isOfficer(p.getUniqueId().toString())) {
//            p.sendMessage(ChatColor.RED + "Officer's can't process drugs!");
//            this.cancel();
//            tasks.remove(this);
//            processor.setProcessing(false);
//            return false;
//        }
        if (p.getInventory().containsAtLeast(input, unprocAmount)) {
            for (int i = 0; i < unprocAmount; i++) {
                p.getInventory().removeItem(input);
            }
            return true;
        } else {
            p.sendMessage(ChatColor.RED + "You don't have any " + input.getItemMeta().getDisplayName() + " to process.");
            tasks.remove(this);
            processor.setProcessing(false);
            this.cancel();
            return false;
        }
    }

    private void finishProcess() {

//        if (!p.addItem(output)) {
//            cancelProcess();
//            p.sendMessage(ChatColor.GRAY + "Inventory full...");
//            return;
//        }
        Bandit b = Bandit.getPlayer(p);
        b.updateBounty(25);

        p.sendTitle("", ChatColor.WHITE + "Finished processing " + input.getItemMeta().getDisplayName(), 8, 12, 8);
        p.playSound(p.getLocation(), Sound.ENTITY_ALLAY_ITEM_THROWN, 1, 1);
        GlobalUtils.displayParticles(procLocation, Particle.SMOKE_NORMAL, Particle.VILLAGER_HAPPY, 10);
        clearBar();
        tasks.remove(this);
        processor.setProcessing(false);
        this.cancel();
    }

    public void cancelProcess() {
        if (p.getPlayer().isOnline()) {
            p.sendMessage(ChatColor.RED + "Process of " + input.getItemMeta().getDisplayName() + " cancelled.");

            for (int i = 0; i < unprocAmount; i++) {
                p.getInventory().addItem(input);
            }
        }
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String command = "minecraft:kill @e[type=armor_stand]";
        Bukkit.dispatchCommand(console, command);
        clearBar();
        tasks.remove(this);
        processor.setProcessing(false);
        this.cancel();
    }

    private void updatePercentageBar(double percent) {
        int left = (int) (50.0 - percent);
        StringBuilder proccess = new StringBuilder();

        for (int i = 0; i < percent; i++) {
            proccess.append(ChatColor.GREEN).append("|");
        }

        for (int i = 0; i < left; i++) {
            proccess.append(ChatColor.RED).append("|");
        }
        //CREATE HOLOGRAM HERE
        hologram.updateHologram(proccess + "   " + ChatColor.RED + ((int) percent * 2) + "%");
    }

    private void clearBar() {
        hologram.removeHologram();
    }
}