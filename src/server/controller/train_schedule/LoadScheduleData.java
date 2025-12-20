package server.controller.train_schedule;

import server.Koneksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class LoadScheduleData {

    // MODEL DATA TABEL JADWAL KERETA
    private static DefaultTableModel createModel() {
        return new DefaultTableModel(
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
    }

    // GET ALL DATA
    public static DefaultTableModel getScheduleData() {
        DefaultTableModel model = createModel();

        String sql = "SELECT * FROM schedule";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
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
            System.out.println("Load data gagal: " + e.getMessage());
            return null;
        }

        return model;
    }

    // SEARCH DATA & SORT
    public static DefaultTableModel searchAndSortSchedule(
            String keyword,
            String sortBy,
            String order
    ) {

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

        // DEFAULT
        String orderBy = "";
        String orderType = "";

        // MAPPING ATAU PEMETAAN SORT BY UNTUK MENGUBAH PENULISAN VALUE NYA
        switch (sortBy) {
            case "Id Schedule" -> orderBy = "id_schedule";
            case "Train Number" -> orderBy = "train_number";
            case "Machinist" -> orderBy = "machinist";
            case "Origin" -> orderBy = "origin";
            case "Destination" -> orderBy = "destination";
            case "Departure Time" -> orderBy = "departure_time";
            case "Carriages" -> orderBy = "carriages";
            case "Class" -> orderBy = "class";
            case "Price" -> orderBy = "price";
        }

        // PENGURUTAN DESCENDING ATAU ASCENDING UNTUK ORDER
        if ("Descending".equals(order)) {
            orderType = "DESC";
        }else {
            orderType = "ASC";
        }

        // MELAKUKAN SEARCH
        String sql = """
            SELECT * FROM schedule
            WHERE train_number LIKE ?
               OR machinist LIKE ?
               OR origin LIKE ?
               OR destination LIKE ?
               OR departure_time LIKE ?
               OR carriages LIKE ?
               OR class LIKE ?
               OR price = ?
            """ + " ORDER BY " + orderBy + " " + orderType;

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String key = "%" + keyword + "%";

            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);
            ps.setString(4, key);
            ps.setString(5, key);
            ps.setString(6, key);
            ps.setString(7, key);
            ps.setString(8, keyword);

            ResultSet rs = ps.executeQuery();

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
            System.out.println("Error load schedule: " + e.getMessage());
        }

        return model;
    }
}