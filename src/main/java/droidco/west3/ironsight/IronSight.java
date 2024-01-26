package droidco.west3.ironsight;

import droidco.west3.ironsight.Objects.Location.Location;
import droidco.west3.ironsight.Objects.Location.LocationType;
import droidco.west3.ironsight.Objects.Player.Commands.AdminCommands;
import droidco.west3.ironsight.Objects.Player.Commands.PlayerStatsCmd;
import droidco.west3.ironsight.Events.JoinServerEvents;
import droidco.west3.ironsight.Objects.Player.Events.GeneralEvents;
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
        Location testLoc = new Location("test",-40,-64,-2938,-2914,"Welcome to test area!", LocationType.TOWN);
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
