package droidco.west3.ironsight.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PlayerConnector {

    public static void updatePlayer(){
        String jdbcURL = "jdbc:mysql://na02-db.cus.mc-panel.net:3306";
        //String jdbcURL = "jdbc:mysql://na02-db.cus.mc-panel.net:3306/mydb";
        String username = "db_592480";
        String password = "13282ce72e";
        System.out.println("Connecting");
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    jdbcURL,
                    username, password);
            System.out.println("CONNECTED!!!");
        }
        catch (Exception exception) {
            System.out.println(exception);
        }

    }
}
