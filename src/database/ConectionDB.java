package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionDB {

    public static Connection conector() throws Exception {
        Connection conexao = null;

        String url = "jdbc:mysql://localhost:3306/livraria";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String password = "";

        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (SQLException e) {
           throw new SQLException("Sem conexao ao banco!!!");
        }
    }
}