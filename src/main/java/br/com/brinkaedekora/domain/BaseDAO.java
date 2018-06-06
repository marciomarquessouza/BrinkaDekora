package br.com.brinkaedekora.domain;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;


public class BaseDAO {

    public BaseDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/amigurumi";
        Connection conn = DriverManager.getConnection(url,"marcio.souza", "OkComputer@7545");
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        BaseDAO bd = new BaseDAO();

        Connection conn = bd.getConnection();
        System.out.println(conn);
    }

}
