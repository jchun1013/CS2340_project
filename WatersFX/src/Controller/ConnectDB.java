package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private Connection conn;
    ConnectDB() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
        }
        try {
            getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/2340_fx";
        String userid = "root";
        String userPass = "J1013h//";
        conn = DriverManager.getConnection(jdbcUrl, userid, userPass);
        return conn;
    }
}

