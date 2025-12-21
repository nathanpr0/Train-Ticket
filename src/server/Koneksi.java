package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/train_ticket";
            String user = "root";
            String pass = "";
            
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Koneksi berhasil");
            return conn;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
            return null;
        }
    }
}
