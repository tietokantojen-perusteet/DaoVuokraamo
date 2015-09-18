package tikape;

import java.sql.*;

public class Database {

    private String databaseAddress;

    public Database(String driver, String databaseAddress) throws ClassNotFoundException {
        Class.forName(driver);
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
}
