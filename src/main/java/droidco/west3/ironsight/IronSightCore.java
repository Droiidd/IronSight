package droidco.west3.ironsight;

import droidco.west3.ironsight.Listeners.JoinServerEvents;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public final class IronSightCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        loadAllEvents();
        System.out.println("Events loaded");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadAllEvents()
    {
        getServer().getPluginManager().registerEvents(new JoinServerEvents(), this);
    }
}
