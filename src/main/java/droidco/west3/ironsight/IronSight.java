package droidco.west3.ironsight;

import droidco.west3.ironsight.Bandit.Commands.TestItemCommand;
import droidco.west3.ironsight.Contracts.ContractMenuCmd;
import droidco.west3.ironsight.Contracts.UI.ContractUiEvents;
import droidco.west3.ironsight.Globals.Utils.GameContentLoader;
import droidco.west3.ironsight.FrontierLocation.LocationUiEvents;
import droidco.west3.ironsight.Bandit.Commands.AdminCommands;
import droidco.west3.ironsight.Bandit.Commands.PlayerStatsCmd;
import droidco.west3.ironsight.Globals.Events.JoinServerEvents;
import droidco.west3.ironsight.Bandit.Events.CombatEvents;
import droidco.west3.ironsight.Bandit.Events.GeneralEvents;
import droidco.west3.ironsight.Items.MasterList.MasterListCmd;
import droidco.west3.ironsight.Items.MasterList.MasterListEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
        GameContentLoader.loadIcons();
        GameContentLoader.loadLocations(this);
        GameContentLoader.loadCustomItems();
        GameContentLoader.loadBrewing();
        GameContentLoader.loadContracts();
        System.out.println("Contracts loaded!");
        System.out.println("Iron Sight successfully loaded!");
    }
    //Allows bukkit scheduling, i.e offsetting actions by one tick
    public static IronSight instance;

    public void onLoad(){
        instance = this;
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for(Player p : Bukkit.getOnlinePlayers()){
            p.kickPlayer("Ironsight server meshing...");
        }
    }

    public void loadAllEvents()
    {
        getServer().getPluginManager().registerEvents(new GeneralEvents(), this);
        getServer().getPluginManager().registerEvents(new JoinServerEvents(this), this);
        getServer().getPluginManager().registerEvents(new CombatEvents(), this);
        getServer().getPluginManager().registerEvents(new ContractUiEvents(),this);
        getServer().getPluginManager().registerEvents(new LocationUiEvents(), this);
        getServer().getPluginManager().registerEvents(new MasterListEvents(), this);
    }
    public void loadAllCommands() {
        getCommand("stats").setExecutor(new PlayerStatsCmd());
        getCommand("ironsight").setExecutor(new AdminCommands());
        getCommand("contract").setExecutor(new ContractMenuCmd());
        getCommand("give_common").setExecutor(new TestItemCommand());
        getCommand("masterlist").setExecutor(new MasterListCmd());
    }
}
