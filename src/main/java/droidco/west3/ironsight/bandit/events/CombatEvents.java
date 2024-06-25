package droidco.west3.ironsight.bandit.events;

import droidco.west3.ironsight.bandit.Bandit;
import droidco.west3.ironsight.frontierlocation.FrontierLocation;
import droidco.west3.ironsight.frontierlocation.LocationType;
import droidco.west3.ironsight.globals.utils.BanditUtils;
import droidco.west3.ironsight.items.CustomItem;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffectType;

public class CombatEvents implements Listener {
  @EventHandler
  public void startCombatTimers(EntityDamageByEntityEvent e) {
    boolean playerInTown = false;
    List<EntityType> bannedTypes = new ArrayList<>();
    bannedTypes.add(EntityType.VILLAGER);
    bannedTypes.add(EntityType.ARMOR_STAND);
    bannedTypes.add(EntityType.ITEM_FRAME);
    bannedTypes.add(EntityType.GLOW_ITEM_FRAME);
    if (e.getDamager() instanceof Player p) {
      Bandit b = Bandit.getPlayer(p);
      if (b.getCurrentLocation().getType().equals(LocationType.TOWN)
          || b.getCurrentLocation().getType().equals(LocationType.PRISON)) {
        playerInTown = true;
        bannedTypes.add(EntityType.PLAYER);
        bannedTypes.add(EntityType.HORSE);
        bannedTypes.add(EntityType.CAMEL);
        bannedTypes.add(EntityType.DONKEY);
      }
    }

    // CANCEL ALL PVP
    for (EntityType type : bannedTypes) {

      if (e.getEntity().getType().equals(type)) {
        e.setCancelled(true);
      }
    }
    if (!playerInTown) {
      if ((e.getEntity() instanceof Player p) && e.getDamager() instanceof Player damager) {

        // SET VICTIM COMBAT LOGGER
        Bandit victim = Bandit.getPlayer(p);
        victim.setCombatBlockFlag(true);
        if (!victim.isCombatBlocked()) {
          victim.setCombatBlocked(true);
          p.sendMessage(
              ChatColor.GRAY
                  + "You are "
                  + ChatColor.RED
                  + "combat blocked "
                  + ChatColor.GRAY
                  + "don't log-out!");
        }
        // SET DAMAGER COMBAT LOGGER
        Bandit attacker = Bandit.getPlayer(damager);
        attacker.setCombatBlockFlag(true);
        if (!attacker.isCombatBlocked()) {
          attacker.setCombatBlocked(true);
          p.sendMessage(
              ChatColor.GRAY
                  + "You are "
                  + ChatColor.RED
                  + "combat blocked "
                  + ChatColor.GRAY
                  + "don't log-out!");
        }
        if (!attacker.isWanted() && !victim.isWanted()) {
          attacker.setWanted(true);
          Bukkit.getServer()
              .broadcastMessage(
                  attacker.getTitle()
                      + ChatColor.RESET
                      + damager.getDisplayName()
                      + " has gone "
                      + ChatColor.DARK_RED
                      + "rogue!");
        }
      }
    }
  }

  @EventHandler
  public void deathEvent(PlayerDeathEvent e) {
    Player p = e.getEntity();
    Bandit b = Bandit.getPlayer(p);
    handlePlayerDeath(p, b);
  }

  //    @EventHandler
  //    public void playerDeath(EntityDamageEvent e){
  //        if(e.getEntity() instanceof Player){
  //            Player p = (Player) e.getEntity();
  //            Bandit b = Bandit.getPlayer(p);
  //            if(p.getHealth() <= 0.0 || (p.getHealth() - e.getDamage()) <= 0.0){
  //                e.setCancelled(true);
  //                handlePlayerDeath(p,b);
  //
  //            }
  //        }
  //    }

  public void handlePlayerDeath(Player p, Bandit b) {
    // PLAYER DIED

    p.removePotionEffect(PotionEffectType.SLOW);
    p.getInventory().clear();
    if (b.isCombatBlocked()) {
      b.setCombatBlocked(false);
    }
    if (b.isWanted()) {
      b.setWanted(false);
    }
    if (b.isBleeding()) {
      b.setBleeding(false);
    }
    if (b.isBrokenLegs()) {
      b.setBrokenLegs(false);
    }
    p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 1, 0);
    p.playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_DEATH, 1, 2);
    if (b.getWallet() > 0.0) {
      p.getWorld()
          .dropItem(
              p.getLocation(),
              new CustomItem(b.getWallet() + "", 1, true, false, "", Material.GOLD_NUGGET, 0.0, 0.0)
                  .getItemStack());
    }
    b.setWallet(0);

    p.setVisualFire(false);
    p.setFireTicks(0);
    p.setHealth(20);
    p.setFoodLevel(20);
    Bukkit.broadcastMessage(
        ChatColor.WHITE
            + p.getDisplayName()
            + ChatColor.GRAY
            + " has "
            + ChatColor.DARK_RED
            + "died!");

    // SEND TO JAIL
    if (b.getBounty() >= 100) {
      b.setJailed(true);
      FrontierLocation prison = FrontierLocation.getLocation("Prison");
      p.setRespawnLocation(FrontierLocation.getLocation("Prison").getSpawnLocation(p));
      b.setJailStartTime(System.currentTimeMillis());
      p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, 1);
      p.sendTitle(
          ChatColor.GRAY + "You are now in" + ChatColor.DARK_RED + " Prison!",
          ChatColor.GRAY + "Mine to 0 bounty to leave.");
      p.teleport(FrontierLocation.getLocation("Prison").getSpawnLocation(p));
      BanditUtils.getPrisonItems(p);
    } else {
      b.setJailed(false);
      p.sendTitle(
          ChatColor.GRAY + "You " + ChatColor.DARK_RED + "Died!",
          ChatColor.GRAY + "Choose a town to respawn");
      b.setRespawning(true);
      BanditUtils.getStarterItems(p);

      FrontierLocation sf = FrontierLocation.getLocation("Santa Fe");
      p.teleport(new Location(p.getWorld(), sf.getSpawnX(), sf.getSpawnY(), sf.getSpawnZ()));
      p.setGameMode(GameMode.SPECTATOR);
    }
  }
}
