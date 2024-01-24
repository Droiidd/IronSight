package droidco.west3.ironsight.Utils;

import droidco.west3.ironsight.Player.IronPlayer;
import org.bukkit.entity.Player;

import java.sql.*;

public class PlayerConnector {
    private static final String jdbcURL = "jdbc:mysql://na02-db.cus.mc-panel.net:3306/db_592480";
    //String jdbcURL = "jdbc:mysql://na02-db.cus.mc-panel.net:3306/mydb";
    private static final String username = "db_592480";
    private static final String password = "13282ce72e";
    public static void updatePlayer(IronPlayer p){

        System.out.println("Connecting");
        Connection conn = null;
        try {
            // below two lines are used for connectivity.
            System.out.println(jdbcURL);
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    jdbcURL,
                    username, password);


            System.out.println("CONNECTED!!!");
//            String sql = "insert into iron_player (pId, wallet, bank, isBleeding, brokenLegs, isWanted, isJailed, isCombatBlocked, bounty," +
//                    "pceContractXP, cmbtContractXp, pceContractLvl, cmbtContractLvl,wantedKills) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
//
            String sql = "UPDATE iron_player " +
                    "set wallet ="+p.getWallet() +
                    "set bank ="+p.getBank() +
                    "set isBleeding ="+p.isBleeding() +
                    "set isBrokenLegs ="+p.isBrokenLegs() +
                    "set isWanted ="+p.isWanted() +
                    "set isJailed ="+p.isJailed()+
                    "set isCombatBlocked ="+p.isCombatBlocked() +
                    "set bounty ="+p.getBounty() +
                    "set pceContractXp ="+p.getPceContractXp() +
                    "set pceContractLvl ="+p.getPceContractLvl() +
                    "set cmbtContractXp ="+p.getCmbtContractXp() +
                    "set cmbtContractLvl ="+p.getCmbtContractXp() +
                    "set wantedKills ="+p.getWantedKills()+
                    "Where iron_player.pId = "+p.getpId();
            PreparedStatement prepedStmt = conn.prepareStatement(sql);

            int updateVal = prepedStmt.executeUpdate();
            if(updateVal > 0){
                System.out.println("Updating the player....");
            }else{
                System.out.println("Could not update player, inserting new columnn.");
                            String sqlInsert = "insert into iron_player (pId, wallet, bank, isBleeding, brokenLegs, isWanted, isJailed, isCombatBlocked, bounty," +
                    "pceContractXP, cmbtContractXp, pceContractLvl, cmbtContractLvl,wantedKills) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                PreparedStatement insertStmt = conn.prepareStatement(sqlInsert);
                prepedStmt.setString(1, p.getpId());
                prepedStmt.setDouble(2,p.getWallet());
                prepedStmt.setDouble(3,p.getBank());
                prepedStmt.setBoolean(4, p.isBleeding());
                prepedStmt.setBoolean(5, p.isBrokenLegs());
                prepedStmt.setBoolean(6,p.isWanted());
                prepedStmt.setBoolean(7,p.isJailed());
                prepedStmt.setBoolean(8,p.isCombatBlocked());
                prepedStmt.setInt(9,p.getBounty());

                prepedStmt.setInt(10,p.getPceContractXp());
                prepedStmt.setInt(11,p.getCmbtContractXp());
                prepedStmt.setInt(12,p.getPceContractLvl());
                prepedStmt.setInt(13,p.getCmbtContractLvl());
                prepedStmt.setInt(14,p.getWantedKills());
                int insertVal = prepedStmt.executeUpdate();
                if(insertVal > 0){
                    System.out.println("Insertion failed.");
                }else{
                    System.out.println("Player successfully added.");
                }
            }
            conn.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }

    }

    public static IronPlayer fetchPlayer(Player p) {
        System.out.println("Connecting");
        Connection conn = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    jdbcURL,
                    username, password);

            System.out.println("CONNECTED!!!");
            String sql = "select * from iron_player where iron_player.pId = " + p.getUniqueId().toString();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (!rs.next()) {
                //It could not read a line
                return null;
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

                int pceContractXp = rs.getInt("pceContractXp");
                int pceContractLvl = rs.getInt("pceContractLvl");
                int cmbtContractLvl = rs.getInt("cmbtContractLvl");
                int cmbtContractXp = rs.getInt("cmbtContractXp");

                IronPlayer player = new IronPlayer(pId, wallet, bank, isBleeding, isJailed, isWanted, isCmbtBlocked, brokenLegs, bounty, wantedKills, pceContractLvl, pceContractXp, cmbtContractLvl, cmbtContractXp);
                st.close();
                return player;
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return null;
    }



}
