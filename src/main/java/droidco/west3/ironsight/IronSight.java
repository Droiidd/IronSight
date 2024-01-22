package droidco.west3.ironsight;

import droidco.west3.ironsight.Player.Commands.AdminCommands;
import droidco.west3.ironsight.Player.Commands.PlayerStatsCmd;
import droidco.west3.ironsight.Events.JoinServerEvents;
import droidco.west3.ironsight.Player.Events.GeneralEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class IronSight extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Iron Sight plugin starting....");
        System.out.println("Loading all Events.");
        loadAllEvents();
        System.out.println("Events loaded!");
        System.out.println("Loading all commands.");
        loadAllCommands();
        System.out.println("Commands loaded!");

        System.out.println("Iron Sight successfully loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadAllEvents()
    {
        getServer().getPluginManager().registerEvents(new GeneralEvents(), this);
        getServer().getPluginManager().registerEvents(new JoinServerEvents(this), this);
    }
    public void loadAllCommands() {
        getCommand("stats").setExecutor(new PlayerStatsCmd());
        getCommand("ironsight").setExecutor(new AdminCommands());
    }
}
