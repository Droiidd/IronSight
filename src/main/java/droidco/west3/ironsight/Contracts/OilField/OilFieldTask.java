package droidco.west3.ironsight.Contracts.OilField;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Contracts.Utils.ContractType;
import droidco.west3.ironsight.Contracts.Utils.Difficulty;
import droidco.west3.ironsight.FrontierLocation.FrontierLocation;
import droidco.west3.ironsight.IronSight;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class OilFieldTask extends BukkitRunnable {
    private int tick;
    private int second;
    private int minute;
    private OilFieldCrate activeCrate;
    private List<OilFieldCrate> crates;
    private List<OilFieldTask> tasks = new ArrayList<>();
    private FrontierLocation frontierLocation;
    private boolean firstPlayerJoin;
    private IronSight plugin;
    public OilFieldTask(IronSight plugin, FrontierLocation frontierLocation){
        this.frontierLocation = frontierLocation;
        this.crates =  OilFieldCrate.getCratesByLocation(frontierLocation);
        this.activeCrate = OilFieldCrate.getRandomCrate(frontierLocation);
        this.firstPlayerJoin = false;
        this.plugin = plugin;
        this.tasks.add(this);
        this.runTaskTimer(plugin,0,10);
        //this.runTaskTimer(plugin, 0, 10);
        System.out.println("Oil field has begun drilling...");
    }
    public void stopTask(){
        this.cancel();
        tasks.remove(this);
    }
    public void spawnCrate(World world, FrontierLocation frontierLocation)
    {
        System.out.println("spawning crate");
        Location location = new Location(world,activeCrate.getCrateX(),activeCrate.getCrateY(),activeCrate.getCrateZ());
        List<OilFieldCrate> cratesByLoc = OilFieldCrate.getCratesByLocation(frontierLocation);
        for(int i =0;i<cratesByLoc.size();i++){
            if(cratesByLoc.get(i).equals(this.activeCrate)){
                List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
                for(Player player : players){
                    player.teleport(location);
                }
                Bukkit.broadcastMessage(location.getX()+" "+location.getY()+" "+location.getZ());

                location.getBlock().setType(Material.LIGHT_GRAY_SHULKER_BOX);
                Bukkit.getServer().broadcastMessage("A crate has spawned at "+frontierLocation.getLocName());
            }else{
                location.getBlock().setType(Material.AIR);
            }
        }
    }
    public void crateInitialization()
    {
        System.out.println("Init crates");
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        boolean contractorMarker = false;
        for(Player player : players){
            Bandit b = Bandit.getPlayer(player);
            if(b.getActiveContract() != null){
                if(b.getActiveContract().getContractType().compareTo(ContractType.OilField)==0){
                    //Ignore timer
                    contractorMarker = true;
                }
            }
        }
        if(contractorMarker){
            //players with the contract active, spawn the crate
            //RESET THE CHEST, THERES NO ACTIVE CONTRACTORS
            spawnCrate(players.get(0).getWorld(),frontierLocation);
        }
    }
    public boolean checkForCrateStartUp()
    {
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        for(Player player : players){
            if(firstPlayerJoin == false){
                firstPlayerJoin = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {

        if(tick %2==0){
            second++;
            //System.out.println("Actively mining");
            if(checkForCrateStartUp()){
                crateInitialization();
            }
            List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
            for(Player player : players){
                Bandit b = Bandit.getPlayer(player);
                if(b.getCurrentLocation().getLocName().equalsIgnoreCase(this.frontierLocation.getLocName())){
                      crateInitialization();
                }
            }
        }
        if(second == 60){
            //crateInitialization();
            minute++;
            second = 0;
        }
        if(minute == 60){
            //crateInitialization();
            minute = 0;
        }
        tick++;
    }
}
