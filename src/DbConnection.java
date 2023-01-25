import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/contactManagement";
        String userName = "root";
        String password = "Maxstuart2@";
        Connection connection = DriverManager.getConnection(url,userName,password);
        return connection;
    }
}
