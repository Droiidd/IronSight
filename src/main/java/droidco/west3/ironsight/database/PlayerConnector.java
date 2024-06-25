package droidco.west3.ironsight.database;

import droidco.west3.ironsight.bandit.Bandit;
import droidco.west3.ironsight.contracts.Contract;
import droidco.west3.ironsight.frontierlocation.FrontierLocation;
import droidco.west3.ironsight.globals.utils.BanditUtils;
import droidco.west3.ironsight.globals.utils.GlobalUtils;
import droidco.west3.ironsight.horse.FrontierHorse;
import droidco.west3.ironsight.items.CustomItem;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class PlayerConnector {
  private static final String user = DbConst.LoginInfo.username;
  private static final String pass = DbConst.LoginInfo.password;
  private static final String url = DbConst.LoginInfo.jdbcURL;

  // UPDATE FULL PLAYER

  public static void updatePlayer(Bandit b, Player p) {
    System.out.println("Connecting");
    Connection conn = null;
    try {
      System.out.println(url);

      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(url, user, pass);
      System.out.println("CONNECTED!!!");
      savePlayer(b, conn);
      // resetInventories(p,conn);
      List<FrontierHorse> horses = b.getHorses();
      for (FrontierHorse horse : horses) {
        System.out.println(horse.getHorseName());
        saveHorse(p, horse, conn);
      }
      List<Contract> contracts = b.getContracts();
      for (int i = 0; i < contracts.size(); i++) {
        saveActiveContract(p, conn, contracts.get(i), i);
      }
      // ALWAYS CLOSE THE CONNECTION!
      conn.close();
    } catch (Exception exception) {
      System.out.println("Could not update new player");
      System.out.println(exception);
    }
  }

  public static Bandit pullAllPlayerData(Player p) {
    System.out.println("Connecting");
    Connection conn = null;

    try {
      // below two lines are used for connectivity.
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(url, user, pass);

      System.out.println("CONNECTED!!!");
      boolean successfulLoad = fetchPlayerData(conn, p);
      fetchPlayerHorses(conn, p);

      if (successfulLoad) {
        Bandit b = Bandit.getPlayer(p);
        fetchAvailableContract(conn, p);
        conn.close();
        return Bandit.getPlayer(p);
      } else {
        conn.close();
      }

    } catch (Exception exception) {
      System.out.println(exception);
    }

    return null;
  }

  //          ===--- FETCH DATA ---===

  public static boolean fetchPlayerData(Connection conn, Player p) {
    try {

      String sql = "select * from bandit where bandit.pId = '" + p.getUniqueId().toString() + "'";

      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(sql);
      if (!rs.next()) {
        // It could not read a line
      } else {
        String pId = rs.getString("pId");
        double wallet = rs.getDouble("wallet");
        double bank = rs.getDouble("bank");
        boolean isBleeding = rs.getBoolean("isBleeding");
        boolean brokenLegs = rs.getBoolean("brokenLegs");
        boolean isWanted = rs.getBoolean("isWanted");
        boolean isJailed = rs.getBoolean("isJailed");
        boolean isCmbtBlocked = rs.getBoolean("isCombatBlocked");
        int bounty = rs.getInt("bounty");
        int wantedKills = rs.getInt("wantedKills");
        int contractorXp = rs.getInt("contractorXp");
        int contractorLvl = rs.getInt("contractorLvl");
        int contractorTitle = rs.getInt("contractor_title");
        long jailStartTime = rs.getLong("jailStartTime");
        int vaultSize = rs.getInt("vault_size");
        int vaultLevel = rs.getInt("vault_level");
        String itemContents = rs.getString("vault_items");

        Bandit b =
            new Bandit(
                pId,
                wallet,
                bank,
                isBleeding,
                isJailed,
                isWanted,
                isCmbtBlocked,
                brokenLegs,
                bounty,
                wantedKills,
                contractorLvl,
                contractorXp,
                jailStartTime,
                contractorTitle,
                vaultSize,
                vaultLevel);
        if (itemContents.equalsIgnoreCase("0")) {
          System.out.println("No vault for " + b.getPId());
        } else {
          ItemStack[] items = itemStackArrayFromBase64(itemContents);
          b.setItemVault(Arrays.asList(items));
        }

        return true;
      }
      st.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    return false;
  }

  public static void fetchPlayerHorses(Connection conn, Player p) {
    try {
      String sql = "select * from horse where bandit_id = \'" + p.getUniqueId().toString() + "\'";

      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(sql);

      Bandit b = Bandit.getPlayer(p);
      while (rs.next()) {
        String banditId = rs.getString("bandit_id");
        String horseName = rs.getString("horse_name");
        String horseType = rs.getString("horse_type");
        String itemContents = rs.getString("item_storage");
        ItemStack[] items = itemStackArrayFromBase64(itemContents);
        FrontierHorse horse =
            new FrontierHorse(banditId, horseName, GlobalUtils.getHorseTypeFromStr(horseType));
        horse.setHorseInv(items);
        b.getHorses().add(horse);
        p.sendMessage("loaded horse: " + horseName);
      }
      st.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void fetchAvailableContract(Connection conn, Player p) {
    Bandit b = Bandit.getPlayer(p);
    try {
      String itemSql =
          "select * from available_contract where bandit_id = \'"
              + p.getUniqueId().toString()
              + "\'";
      Statement st = conn.createStatement();
      ResultSet rsItem = st.executeQuery(itemSql);
      while (rsItem.next()) {
        String banditId = rsItem.getString("bandit_id");
        String contractType = rsItem.getString("contract_type");
        String deliveryType = rsItem.getString("delivery_type");
        String difficulty = rsItem.getString("difficulty");
        String requestedItem = rsItem.getString("requested_item");
        int requestedAmt = rsItem.getInt("requested_amt");
        String listingName = rsItem.getString("listing_name");
        String frontierLocation = rsItem.getString("frontier_location");
        boolean isActive = rsItem.getBoolean("is_active");
        String slot = rsItem.getString("slot");
        if (isActive) {
          b.setActiveContract(
              new Contract(
                  CustomItem.getCustomItem(requestedItem),
                  requestedAmt,
                  listingName,
                  BanditUtils.getContractorTypeFromStr(contractType),
                  BanditUtils.getDeliveryTypeFromStr(deliveryType),
                  FrontierLocation.getLocation(frontierLocation),
                  BanditUtils.getDifficultyFromStr(difficulty)));
          b.setDoingContract(true);
          System.out.println("LOADED ACTIVE " + listingName);
        }
      }
      System.out.println("added vault items");
      st.close();
    } catch (Exception e) {
      System.out.println(e);
      System.out.println("failed getting vault items");
    }
  }

  //         ===--- SAVING DATA ---===

  public static void savePlayer(Bandit b, Connection conn) throws SQLException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      List<ItemStack> items = b.getItemVault();
      BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
      dataOutput.writeInt(items.size());
      for (ItemStack item : items) {
        dataOutput.writeObject(item);
      }
      dataOutput.close();
      String serialized = Base64Coder.encodeLines(outputStream.toByteArray());
      if (serialized != null) {
        serialized = "0";
      }
      String sql =
          "UPDATE bandit "
              + "set wallet = "
              + b.getWallet()
              + ", "
              + "bank = "
              + b.getBank()
              + ", "
              + "isBleeding = "
              + GlobalUtils.boolToInt(b.isBleeding())
              + ", "
              + "brokenLegs = "
              + GlobalUtils.boolToInt(b.isBrokenLegs())
              + ", "
              + "isWanted = "
              + GlobalUtils.boolToInt(b.isWanted())
              + ", "
              + "isJailed = "
              + b.isJailed()
              + ", "
              + "isCombatBlocked = "
              + b.isCombatBlocked()
              + ", "
              + "bounty = "
              + b.getBounty()
              + ", "
              + "contractorLvl = "
              + b.getContractorLvl()
              + ", "
              + "contractorXp = "
              + b.getContractorXp()
              + ", "
              + "wantedKills = "
              + b.getWantedKills()
              + ", "
              + "contractor_title = "
              + b.getContractorTitle()
              + ", "
              + "jailStartTime = "
              + b.getJailStartTime()
              + ", "
              + "vault_size = "
              + b.getVaultSize()
              + ", "
              + "vault_level = "
              + b.getVaultLevel()
              + ", "
              + "vault_items = "
              + serialized
              + " "
              + "Where bandit.pId = '"
              + b.getPId()
              + "'";

      PreparedStatement prepedStmt = conn.prepareStatement(sql);

      int updateVal = prepedStmt.executeUpdate();
      if (updateVal > 0) {
        // Success
        System.out.println("Player updated.");
      } else {
        // Update was not successful
        System.out.println("Could not update player, inserting new columnn.");
        try {
          String sqlInsert =
              "insert into bandit (pId, wallet, bank, isBleeding, brokenLegs, isWanted, isJailed, isCombatBlocked, bounty, contractorXp, contractorLvl, wantedKills, contractor_title, jailStartTime, vault_size, vault_level, vault_items) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
          PreparedStatement insertStmt = conn.prepareStatement(sqlInsert);
          insertStmt.setString(1, b.getPId());
          insertStmt.setDouble(2, b.getWallet());
          insertStmt.setDouble(3, b.getBank());
          insertStmt.setBoolean(4, b.isBleeding());
          insertStmt.setBoolean(5, b.isBrokenLegs());
          insertStmt.setBoolean(6, b.isWanted());
          insertStmt.setBoolean(7, b.isJailed());
          insertStmt.setBoolean(8, b.isCombatBlocked());
          insertStmt.setInt(9, b.getBounty());

          insertStmt.setInt(10, b.getContractorXp());
          insertStmt.setInt(11, b.getContractorLvl());
          insertStmt.setInt(12, b.getWantedKills());
          insertStmt.setInt(13, b.getContractorTitle());
          insertStmt.setLong(14, b.getJailStartTime());
          insertStmt.setInt(15, b.getVaultSize());
          insertStmt.setInt(16, b.getVaultLevel());
          insertStmt.setString(17, serialized);

          int insertVal = insertStmt.executeUpdate();
          System.out.println("Player inserted.");
        } catch (Exception exception) {
          System.out.println("Player update failed.");
          System.out.println(exception);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void saveHorse(Player p, FrontierHorse horse, Connection conn) throws SQLException {
    System.out.println("Updating horse " + horse.getHorseName());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      List<ItemStack> items = Arrays.asList(horse.getInventory());
      BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
      dataOutput.writeInt(items.size());
      for (ItemStack item : items) {
        dataOutput.writeObject(item);
      }
      dataOutput.close();
      String serialized = Base64Coder.encodeLines(outputStream.toByteArray());

      String sql =
          "UPDATE horse "
              + "set bandit_id = \'"
              + p.getUniqueId().toString()
              + "\', "
              + "horse_type = \'"
              + GlobalUtils.getHorseTypeString(horse.getHorseType())
              + "\', "
              + "horse_name = \'"
              + horse.getHorseName()
              + "\',"
              + "item_storage = \'"
              + serialized
              + "\'"
              + "WHERE horse_name = \'"
              + horse.getHorseName()
              + "\' AND "
              + "bandit_id = \'"
              + p.getUniqueId().toString()
              + "\'   ";
      PreparedStatement prepedStmt = conn.prepareStatement(sql);

      int updateVal = prepedStmt.executeUpdate();
      if (updateVal > 0) {
        // Success
        System.out.println("Horse updated.");
      } else {
        // Update was not successful
        System.out.println("Could not update " + horse.getHorseName() + ", inserting new columnn.");
        String sqlInsert =
            "insert into horse (bandit_id,horse_name,horse_type,item_storage) values (?,?,?,?);";
        PreparedStatement insertStmt = conn.prepareStatement(sqlInsert);
        insertStmt.setString(1, p.getUniqueId().toString());
        insertStmt.setString(2, horse.getHorseName());
        insertStmt.setString(3, GlobalUtils.getHorseTypeString(horse.getHorseType()));
        insertStmt.setString(4, serialized);

        int insertVal = insertStmt.executeUpdate();
        if (insertVal > 0) {
          // Success
          System.out.println(horse.getHorseName() + " inserted.");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void saveActiveContract(Player p, Connection conn, Contract active, int slot)
      throws SQLException {

    Bandit b = Bandit.getPlayer(p);
    if (b.getActiveContract() != null) {
      System.out.println("Updating available contract ");
      String sql =
          "UPDATE available_contract "
              + "set bandit_id = \'"
              + p.getUniqueId().toString()
              + "\', "
              + "requested_item = \'"
              + String.valueOf(
                  ChatColor.stripColor(active.getRequestedItem().getItemMeta().getDisplayName()))
              + "\', "
              + "requested_amt = \'"
              + active.getRequestedAmount()
              + "\',"
              + "contract_type = \'"
              + active.getContractType().toString()
              + "\',"
              + "listing_name = \'"
              + active.getListingName()
              + "\',"
              + "frontier_location = \'"
              + active.getLocation().getLocName()
              + "\',"
              + "difficulty = \'"
              + active.getDifficulty()
              + "\',"
              + "is_active = \'"
              + 1
              + "\',"
              + "delivery_type = \'"
              + active.getDeliveryType().toString()
              + "\',"
              + "slot = \'active_"
              + slot
              + "\'"
              + "WHERE bandit_id = \'"
              + p.getUniqueId().toString()
              + "\'";
      PreparedStatement prepedStmt = conn.prepareStatement(sql);

      int updateVal = prepedStmt.executeUpdate();
      if (updateVal > 0) {
        // Success
        System.out.println("Available Contract updated.");
      } else {
        // Update was not successful
        System.out.println("Could not update contract, inserting new columnn.");
        String sqlInsert =
            "insert into available_contract (requested_item,requested_amt,listing_name,contract_type,bandit_id,delivery_type,frontier_location,difficulty,is_active,slot) values (?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement insertStmt = conn.prepareStatement(sqlInsert);
        insertStmt.setString(
            1, b.getActiveContract().getRequestedItem().getItemMeta().getDisplayName());
        insertStmt.setInt(2, b.getActiveContract().getRequestedAmount());
        insertStmt.setString(3, b.getActiveContract().getListingName());
        insertStmt.setString(4, b.getActiveContract().getContractType().toString());
        insertStmt.setString(5, p.getUniqueId().toString());
        insertStmt.setString(6, b.getActiveContract().getDeliveryType().toString());
        insertStmt.setString(7, b.getActiveContract().getLocation().getLocName());
        insertStmt.setString(8, b.getActiveContract().getDifficulty().toString());
        insertStmt.setBoolean(9, true);
        insertStmt.setString(10, "active_" + slot);

        int insertVal = insertStmt.executeUpdate();
        if (insertVal > 0) {
          // Success
          System.out.println("contract inserted.");
        }
      }
    } else {
      System.out.println("Available contract is null");
      String sqlInsert =
          "delete from available_contract where bandit_id = \'"
              + p.getUniqueId().toString()
              + "\' AND is_active = 1";
      PreparedStatement insertStmt = conn.prepareStatement(sqlInsert);
      int insertVal = insertStmt.executeUpdate();
      if (insertVal > 0) {
        // Success
        System.out.println("contracts wiped.");
      }
    }
  }

  //          ===--- MISC METHODS ---===

  public static void removeHorse(String id, FrontierHorse horse) {
    Connection conn = null;

    // below two lines are used for connectivity.
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(url, user, pass);
      try {
        String itemSql =
            "\n"
                + "delete from horse where horse_name = \'"
                + horse.getHorseName()
                + "\'"
                + " and bandit_id = \'"
                + id
                + "\';";
        System.out.println(itemSql);
        Statement st = conn.createStatement();
        boolean rsItem = st.execute(itemSql);

        System.out.println("Removed player horse!");
        st.close();
      } catch (Exception e) {
        System.out.println(e);
        System.out.println("failed removing horse");
      }
    } catch (Exception exception) {
      System.out.println("Could not update new player");
      System.out.println(exception);
    }
  }

  public static void resetInventories(Player p, Connection conn) throws SQLException {
    try {
      String sqlInsert = "delete from bandit where pId = \'" + p.getUniqueId().toString() + "\'";
      PreparedStatement insertStmt = conn.prepareStatement(sqlInsert);
      int insertVal = insertStmt.executeUpdate();
      System.out.println("Inventory refreshed.");
    } catch (Exception exception) {
      System.out.println("Inventory refresh update failed.");
      System.out.println(exception);
    }
  }

  public static ItemStack[] itemStackArrayFromBase64(String data) throws IOException {
    if (data != null) {
      try {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
        BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
        ItemStack[] items = new ItemStack[dataInput.readInt()];

        // Read the serialized inventory
        for (int i = 0; i < items.length; i++) {
          items[i] = (ItemStack) dataInput.readObject();
        }

        dataInput.close();
        return items;
      } catch (ClassNotFoundException e) {
        throw new IOException("Unable to decode class type.", e);
      }
    }
    return null;
  }
}
