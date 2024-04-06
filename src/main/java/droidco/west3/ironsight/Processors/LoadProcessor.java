package droidco.west3.ironsight.Processors;

import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class LoadProcessor {

        private final String jackFruitProcName = ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Jack-Fruit Processor";
        private static final String smokeLeafProcName1 = ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Smokeleaf Processor 1";
        private static final String smokeLeafProcName2 = ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Smokeleaf Processor 2";
        private static final String smokeLeafProcName3 = ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Smokeleaf Processor 3";
        private final String spiceProcName = ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Spice Processor";

        public static void spawnProcessors(Player p) {



            Location loc1 = new Location(p.getLocation().getWorld(),-53,110,-2920);
            Location loc2 = new Location(p.getLocation().getWorld(),-50,106,-2936);
            Location loc3 = new Location(p.getLocation().getWorld(),-63,112,-2934);

            Location loc4 = new Location(p.getLocation().getWorld(),-46,107,-2964);
            Location loc5 = new Location(p.getLocation().getWorld(),-46,111,-2959);
            Location loc6 = new Location(p.getLocation().getWorld(),-43,119,-2968);

            Location loc7 = new Location(p.getLocation().getWorld(),-14,108,-2933);
            Location loc8 = new Location(p.getLocation().getWorld(),-15,112,-2936);
            Location loc9 = new Location(p.getLocation().getWorld(),-18,117,-2935);


            Processor proc1 = new Processor("smokeleaf1", loc1, loc2, loc3);
            createVillager(smokeLeafProcName1, proc1.getDefaultLocation());
            Processor proc2 = new Processor("smokeleaf2", loc4, loc5, loc6);
            createVillager(smokeLeafProcName2, proc2.getDefaultLocation());
            Processor proc3 = new Processor("smokeleaf3", loc7, loc8, loc9);
            createVillager(smokeLeafProcName3, proc3.getDefaultLocation());
            System.out.println("Processors successfully loaded!");
        }

        public static void createVillager(String villagerName, Location loc) {
            Villager vil = loc.getWorld().spawn(loc, Villager.class);
            vil.setCustomName(villagerName);
            vil.setCustomNameVisible(true);
            vil.setAI(false);
            vil.setInvulnerable(true);
            loc.getWorld().playSound(loc, Sound.BLOCK_BEACON_POWER_SELECT, 1, 0);
            GlobalUtils.displayParticles(loc, Particle.CLOUD, Particle.GLOW, 15);
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "minecraft:kill @e[type=minecraft:armor_stand]";
            Bukkit.dispatchCommand(console, command);
        }

}
