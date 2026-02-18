package Database;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:C:/Users/Wren/IdeaProjects/Password_Generator_GUI/DataBase";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Opened database connection!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close(); //Ensures connection is closed
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public static String hashPassword(String Password) {
        return BCrypt.hashpw(Password, BCrypt.gensalt(12));
    }
}
