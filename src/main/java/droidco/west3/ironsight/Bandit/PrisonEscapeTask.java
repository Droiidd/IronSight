package droidco.west3.ironsight.Bandit;

import droidco.west3.ironsight.IronSight;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class PrisonEscapeTask extends BukkitRunnable {
    private ArrayList<PrisonEscapeTask> tasks = new ArrayList<>();
    private final IronSight plugin;
    private int tick = 0;
    private final int maxTime = 10;
    private int escapeTime = 0;
    private Bandit b;
    private Player p;

    public PrisonEscapeTask(IronSight plugin, Player p){
        this.b = Bandit.getPlayer(p);
        this.p = p;
        this.tasks.add(this);
        this.plugin = plugin;
        this.runTaskTimer(plugin, 0, 10);
    }
    public void stopTask(){
        this.cancel();
        tasks.remove(this);
    }
    @Override
    public void run() {
        if(tick % 3 == 0){
            escapeTime++;
            if(b.isEscaping()){
                p.sendMessage((maxTime - escapeTime)+" seconds remaining.");
                if(escapeTime == maxTime){
                    p.sendMessage("DIE");
                }
            }else{
                //NO LONGER ESCAPING, CANCEL TIMER
                stopTask();
            }
        }
        if(p.isDead()){
            b.setEscaping(false);
            stopTask();
        }
        if (!p.isOnline()) {
            stopTask();
        }
        tick++;
    }
}
