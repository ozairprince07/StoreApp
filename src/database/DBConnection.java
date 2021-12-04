package database;

import java.sql.*;

public class DBConnection {

    private static final String DB_NAME = "Inventory";
    private static final String USER = "root";
    private static final String PASS = "welcome123";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/".concat(DB_NAME);

    private static Connection connection;

    private static ResultSet resultSet;

    public DBConnection() throws SQLException {
        dbConnect();
    }

    public static void dbConnect() throws SQLException {
        connection = DriverManager.getConnection(CONNECTION_URL, USER, PASS);
    }

    public static void disConnect() throws SQLException {
        if (!(connection == null)) {
            connection.close();
        }
    }

    public static void dbExecute(String sql) throws SQLException {
        dbConnect();
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
    }

    public static ResultSet dbExecuteQuery(String sql) throws SQLException {
        dbConnect();
        PreparedStatement ps = connection.prepareStatement(sql);
        resultSet = ps.executeQuery();
        return resultSet;
    }

}
