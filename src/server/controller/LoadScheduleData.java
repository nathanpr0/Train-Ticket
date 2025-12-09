package server.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import server.Koneksi;

public class LoadScheduleData {

    public static DefaultTableModel getScheduleData() {
        DefaultTableModel model = new DefaultTableModel(
                new String[]{
                    "Id Schedule",
                    "Train Number",
                    "Machinist",
                    "Origin",
                    "Destination",
                    "Departure Time",
                    "Carriages",
                    "Class",
                    "Price"
                }, 0
        );

        try (Connection conn = Koneksi.getConnection(); 
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM schedule"); 
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_schedule"),
                    rs.getString("train_number"),
                    rs.getString("machinist"),
                    rs.getString("origin"),
                    rs.getString("destination"),
                    rs.getString("departure_time"),
                    rs.getInt("carriages"),
                    rs.getString("class"),
                    rs.getInt("price")
                });
            }

        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
            return null;
        }

        return model;
    }
}
