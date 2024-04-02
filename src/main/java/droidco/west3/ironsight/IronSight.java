package droidco.west3.ironsight;

import droidco.west3.ironsight.Contracts.ContractMenuCmd;
import droidco.west3.ironsight.Contracts.UI.ContractUiEvents;
import droidco.west3.ironsight.FrontierMobs.FrontierMob;
import droidco.west3.ironsight.Globals.Events.BlockBreakingEvents;
import droidco.west3.ironsight.Globals.Utils.GameContentLoader;
import droidco.west3.ironsight.Bandit.UI.RespawnUIEvents;
import droidco.west3.ironsight.Bandit.Commands.AdminCommands;
import droidco.west3.ironsight.Bandit.Commands.PlayerStatsCmd;
import droidco.west3.ironsight.Globals.Events.JoinServerEvents;
import droidco.west3.ironsight.Bandit.Events.CombatEvents;
import droidco.west3.ironsight.Bandit.Events.GeneralEvents;
import droidco.west3.ironsight.Items.MasterList.MasterListCmd;
import droidco.west3.ironsight.Items.MasterList.MasterListEvents;
import droidco.west3.ironsight.Tracker.TrackerEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
        GameContentLoader.loadCustomItems();
        GameContentLoader.loadLocations(this);
        GameContentLoader.loadBrewing();
        GameContentLoader.loadItemTables();
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
        System.out.println("Disabling IronSight");
        // Plugin shutdown logic
        for(Player p : Bukkit.getOnlinePlayers()){
            p.kickPlayer("Ironsight server meshing...");
        }
        killAllMobs();
        System.out.println("All mobs killed.");
        System.out.println("IronSight shutting down...");
    }

    public void loadAllEvents()
    {
        getServer().getPluginManager().registerEvents(new GeneralEvents(), this);
        getServer().getPluginManager().registerEvents(new JoinServerEvents(this), this);
        getServer().getPluginManager().registerEvents(new CombatEvents(), this);
        getServer().getPluginManager().registerEvents(new ContractUiEvents(),this);
        getServer().getPluginManager().registerEvents(new RespawnUIEvents(), this);
        getServer().getPluginManager().registerEvents(new TrackerEvents(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakingEvents(this), this);
        getServer().getPluginManager().registerEvents(new MasterListEvents(), this);
    }
    public void loadAllCommands() {
        getCommand("stats").setExecutor(new PlayerStatsCmd());
        getCommand("ironsight").setExecutor(new AdminCommands());
        getCommand("contract").setExecutor(new ContractMenuCmd());
        getCommand("masterlist").setExecutor(new MasterListCmd());
    }
    public void killAllMobs()
    {
        HashMap<UUID, LivingEntity> entities = FrontierMob.getEntities();
        for(Map.Entry<UUID,LivingEntity> mob : entities.entrySet()){
            mob.getValue().damage(100);
            System.out.println("Mob killed.");
        }
    }
}
