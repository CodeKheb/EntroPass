package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connector {
    public static void insertPassword(int id, String serviceName, String userName, String password, String notes, String createdDate) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        String insertSQL = "INSERT INTO passwords(id, service_name, username, encrypted_password, notes, created_date) VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, serviceName);
        preparedStatement.setString(3, userName);
        preparedStatement.setString(4, password);
        preparedStatement.setString(5, notes);
        preparedStatement.setString(6, createdDate);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
