package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConexao() throws ClassNotFoundException, SQLException {
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/SkySearchFuncionarios?useUnicode=true&characterEncoding=UTF-8&serverTimezone=America/Sao_Paulo";
        String USER = ""; //alterar para o nome de usuário do banco de dados
        String PASSWORD = "";  //alterar para a senha do banco de dados
        
        Class.forName(DRIVER);
        
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
