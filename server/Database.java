package server;

import java.sql.*;

public class Database {

    private static final String databaseName = "groupM";
    private static final String userName = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost/" + databaseName;

    public static Connection Connect() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }

    public static boolean checkRepresentativeLogin(String userName, String password) {
        String sql = "SELECT * FROM SchoolRep WHERE userName = ? AND password = ?";
        try (Connection connection = Database.Connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Returns true if there is a matching record
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }  
}
public static boolean checkParticipantLogin(String userName, String password) {
    String sql = "SELECT * FROM participant WHERE userName = ? AND password = ?";
    try (Connection connection = Database.Connect();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next(); // Returns true if there is a matching record
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}
