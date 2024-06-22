package droidco.west3.ironsight.bandit.Tasks;

import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.bandit.Bandit;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class PrisonEscapeTask extends BukkitRunnable {
    private final IronSight plugin;
    private final int maxTime = 10;
    private final ArrayList<PrisonEscapeTask> tasks = new ArrayList<>();
    private final Bandit b;
    private final Player p;
    private int tick = 0;
    private int escapeTime = 0;

    public PrisonEscapeTask(IronSight plugin, Player p) {
        this.b = Bandit.getPlayer(p);
        this.p = p;
        this.tasks.add(this);
        this.plugin = plugin;
        this.runTaskTimer(plugin, 0, 10);
    }

    public void stopTask() {
        this.cancel();
        tasks.remove(this);
    }

    @Override
    public void run() {
        if (tick % 3 == 0) {
            escapeTime++;
            if (b.isEscaping()) {
                p.spigot().sendMessage(
                        ChatMessageType.ACTION_BAR,
                        new TextComponent(ChatColor.RED + "" + (maxTime - escapeTime) + ChatColor.RESET + " seconds remaining."));
                if (escapeTime == maxTime) {
                    b.updateBounty(1000);
                    p.sendMessage(ChatColor.GRAY + "Attempted to escape " + ChatColor.DARK_RED + "+1000 bounty");
                    p.damage(100);
                }
            } else {
                //NO LONGER ESCAPING, CANCEL TIMER
                stopTask();
            }
        }
        if (p.isDead()) {
            b.setEscaping(false);
            stopTask();
        }
        if (!p.isOnline()) {
            stopTask();
        }
        tick++;
    }
}
