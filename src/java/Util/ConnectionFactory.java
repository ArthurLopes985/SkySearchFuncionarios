package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConexao() throws ClassNotFoundException, SQLException {
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/SkySearchFuncionarios";
        String USER = "root"; 
        String PASSWORD = "0908"; 
        
        Class.forName(DRIVER);
        
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
