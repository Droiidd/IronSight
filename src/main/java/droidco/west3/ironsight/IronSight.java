package droidco.west3.ironsight;

import droidco.west3.ironsight.Objects.Contracts.Commands.ContractMenu;
import droidco.west3.ironsight.Objects.Contracts.Contract;
import droidco.west3.ironsight.Objects.Contracts.Utils.CompletionType;
import droidco.west3.ironsight.Objects.Contracts.Utils.ContractType;
import droidco.west3.ironsight.Objects.Contracts.Utils.Difficulty;
import droidco.west3.ironsight.Objects.Location.Location;
import droidco.west3.ironsight.Objects.Location.LocationType;
import droidco.west3.ironsight.Objects.Player.Commands.AdminCommands;
import droidco.west3.ironsight.Objects.Player.Commands.PlayerStatsCmd;
import droidco.west3.ironsight.Events.JoinServerEvents;
import droidco.west3.ironsight.Objects.Player.Events.CombatEvents;
import droidco.west3.ironsight.Objects.Player.Events.GeneralEvents;
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

        loadLocations();
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
    }
    public void loadAllCommands() {
        getCommand("stats").setExecutor(new PlayerStatsCmd());
        getCommand("ironsight").setExecutor(new AdminCommands());
        getCommand("contract").setExecutor(new ContractMenu());
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
    public void loadLocations(){
        Location stormpoint = new Location("Storm Point", 26, -157, -2788, -3015, "Drug Base",LocationType.ILLEGAL);
        Location northoil = new Location("North Oil Field",2827,3041,-2951,-3189,"Illegal area!",LocationType.ILLEGAL);
        Location sloughcreek = new Location("Slough Creek",2589,2835,799,471,"Scav Town",LocationType.ILLEGAL);

        Location neworleans = new Location("New Orleans", -1230,-1403,-1834,-1664,"PvP disabled!",LocationType.TOWN);
        Location santafe = new Location("Santa Fe",1119,888,-1755,-2066,"PvP Disabled",LocationType.TOWN);
        Location texas = new Location("Republic Of Texas",-1197,-831,2628,2214,"Pvp Disabled",LocationType.TOWN);

        Location blackspur = new Location("Black Spur Mines",1542,2248,-2102,-1775,"Be weary of the depths",LocationType.NATURAL);

        Location sloughcreekR = new Location("Slough Creek River", 2545,2698,38,1243,"Fishings good",LocationType.River);
        Location pearlR = new Location("Pearl River",2599,2083,-2596,-2475,"Good fishing!",LocationType.River);

    }
}
