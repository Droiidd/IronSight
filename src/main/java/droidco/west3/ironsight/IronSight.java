package droidco.west3.ironsight;

import droidco.west3.ironsight.Bandit.Commands.TestItemCommand;
import droidco.west3.ironsight.Contracts.ContractMenuCmd;
import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.Contracts.ContractUiEvents;
import droidco.west3.ironsight.Contracts.Utils.CompletionType;
import droidco.west3.ironsight.Contracts.Utils.ContractType;
import droidco.west3.ironsight.Contracts.Utils.Difficulty;
import droidco.west3.ironsight.Globals.Events.BlockBreakingEvents;
import droidco.west3.ironsight.Globals.Utils.GameContentLoader;
import droidco.west3.ironsight.Location.Location;
import droidco.west3.ironsight.Location.LocationUiEvents;
import droidco.west3.ironsight.Bandit.Commands.AdminCommands;
import droidco.west3.ironsight.Bandit.Commands.PlayerStatsCmd;
import droidco.west3.ironsight.Globals.Events.JoinServerEvents;
import droidco.west3.ironsight.Bandit.Events.CombatEvents;
import droidco.west3.ironsight.Bandit.Events.GeneralEvents;
import droidco.west3.ironsight.NPC.NPCEvents;
import droidco.west3.ironsight.Tracker.TrackerEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

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
        GameContentLoader.loadLocations();
        GameContentLoader.loadCustomItems();
        GameContentLoader.loadBrewing();
        loadContracts();
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
        getServer().getPluginManager().registerEvents(new TrackerEvents(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakingEvents(this), this);
        getServer().getPluginManager().registerEvents(new NPCEvents(), this);


    }
    public void loadAllCommands() {
        getCommand("stats").setExecutor(new PlayerStatsCmd());
        getCommand("ironsight").setExecutor(new AdminCommands());
        getCommand("contract").setExecutor(new ContractMenuCmd());
        getCommand("give_common").setExecutor(new TestItemCommand());
    }
    public void loadContracts(){
        List<Location> testLocs = new ArrayList<>();
        testLocs.add(Location.getLocation("Black Spur Mines"));
        testLocs.add(Location.getLocation("Santa Fe"));
        Contract testC1 = new Contract("Looking for iron.",30, CompletionType.Delivery, ContractType.Miner, testLocs ,false, Difficulty.Rookie,1);

        List<Location> test2Locs = new ArrayList<>();
        test2Locs.add(Location.getLocation("North Oil Field"));
        test2Locs.add(Location.getLocation("Slough Creek"));
        Contract testC2 = new Contract("I need fish caught.",30, CompletionType.Delivery ,ContractType.Fisher, test2Locs ,false, Difficulty.Apprentice,1);

        List<Location> test3Locs = new ArrayList<>();
        test3Locs.add(Location.getLocation("Storm Point"));
        test3Locs.add(Location.getLocation("New Orleans"));
        Contract testC3 = new Contract("Mule across city borders",30, CompletionType.Delivery,ContractType.Fisher , test3Locs ,false, Difficulty.Experienced,1);

        Contract testC4 = new Contract("Big time moves.",30, CompletionType.Delivery,ContractType.Miner , test3Locs ,false, Difficulty.Master,1);

    }
}
