package droidco.west3.ironsight.Database;

import droidco.west3.ironsight.Player.IronPlayer;
import droidco.west3.ironsight.Utils.GlobalUtils;
import org.bukkit.entity.Player;

import java.sql.*;

public class PlayerConnector {
    private static final String user = DbConst.LoginInfo.username;
    private static final String pass = DbConst.LoginInfo.password;
    private static final String url = DbConst.LoginInfo.jdbcURL;
    public static void updatePlayer(IronPlayer p){
        System.out.println("Connecting");
        Connection conn = null;
            try{
                System.out.println(url);

                Class.forName("com.mysql.cj.jdbc.Driver");
                        conn = DriverManager.getConnection(url, user, pass);
                System.out.println("CONNECTED!!!");

                String sql = "UPDATE iron_player " +
                        "set wallet = "+p.getWallet() + ", "+
                        "bank = "+p.getBank() +", "+
                        "isBleeding = "+ GlobalUtils.boolToInt(p.isBleeding()) +", "+
                        "brokenLegs = "+GlobalUtils.boolToInt(p.isBrokenLegs()) +", "+
                        "isWanted = "+GlobalUtils.boolToInt(p.isWanted()) +", "+
                        "isJailed = "+p.isJailed()+", "+
                        "isCombatBlocked = "+p.isCombatBlocked() +", "+
                        "bounty = "+p.getBounty() +", "+
                        "pceContractXp = "+p.getPceContractXp() +", "+
                        "pceContractLvl = "+p.getPceContractLvl() +", "+
                        "cmbtContractXp = "+p.getCmbtContractXp() +", "+
                        "cmbtContractLvl = "+p.getCmbtContractXp() +", "+
                        "wantedKills = "+p.getWantedKills()+" "+
                        "Where iron_player.pId = '"+p.getpId()+"'";

                PreparedStatement prepedStmt = conn.prepareStatement(sql);

                int updateVal = prepedStmt.executeUpdate();
                if(updateVal > 0){
                    //Success
                    System.out.println("Player updated.");
                }else{
                    //Update was not successful
                    System.out.println("Could not update player, inserting new columnn.");
                    try{
                        String sqlInsert = "insert into iron_player (pId, wallet, bank, isBleeding, brokenLegs, isWanted, isJailed, isCombatBlocked, bounty, pceContractXP, cmbtContractXp, pceContractLvl, cmbtContractLvl,wantedKills) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
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
//                        if(insertVal > 0){
//                            System.out.println("Insertion failed.");
//                        }else{
//                            System.out.println("Player successfully added.");
//                        }
                    }catch (Exception exception) {
                        System.out.println("Player update failed.");
                        System.out.println(exception);
                    }
                }
                //ALWAYS CLOSE THE CONNECTION!
                conn.close();
            }catch (Exception exception) {
                System.out.println("Could not update new player");
                System.out.println(exception);
            }
    }

    public static IronPlayer fetchPlayer(Player p) {
        System.out.println("Connecting");
        Connection conn = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);

            System.out.println("CONNECTED!!!");
            String sql = "select * from iron_player where iron_player.pId = '" + p.getUniqueId().toString()+"'";

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
