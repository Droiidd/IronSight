package droidco.west3.ironsight;

import droidco.west3.ironsight.Objects.Contracts.Commands.ContractMenuCmd;
import droidco.west3.ironsight.Objects.Contracts.Contract;
import droidco.west3.ironsight.Objects.Contracts.Events.ContractUiEvents;
import droidco.west3.ironsight.Objects.Contracts.Utils.CompletionType;
import droidco.west3.ironsight.Objects.Contracts.Utils.ContractType;
import droidco.west3.ironsight.Objects.Contracts.Utils.Difficulty;
import droidco.west3.ironsight.Objects.Items.ItemIcon;
import droidco.west3.ironsight.Objects.Location.Location;
import droidco.west3.ironsight.Objects.Location.LocationType;
import droidco.west3.ironsight.Objects.Player.Commands.AdminCommands;
import droidco.west3.ironsight.Objects.Player.Commands.PlayerStatsCmd;
import droidco.west3.ironsight.Events.JoinServerEvents;
import droidco.west3.ironsight.Objects.Player.Events.CombatEvents;
import droidco.west3.ironsight.Objects.Player.Events.GeneralEvents;
import droidco.west3.ironsight.Utils.GlobalUtils;
import org.bukkit.Material;
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
        GlobalUtils.loadIcons();
        GlobalUtils.loadLocations();
        GlobalUtils.loadCustomItems();
        loadContracts();
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
        getServer().getPluginManager().registerEvents(new CombatEvents(), this);
        getServer().getPluginManager().registerEvents(new ContractUiEvents(),this);
    }
    public void loadAllCommands() {
        getCommand("stats").setExecutor(new PlayerStatsCmd());
        getCommand("ironsight").setExecutor(new AdminCommands());
        getCommand("contract").setExecutor(new ContractMenuCmd());
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
