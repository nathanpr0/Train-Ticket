package server.controller.booked_data;

import server.Koneksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class LoadBookedData {

    // MODEL DATA TABEL JADWAL KERETA
    private static DefaultTableModel createModel() {
        return new DefaultTableModel(
                new String[]{
                    "Booking Code",
                    "Name",
                    "Booking Date",
                    "Status",
                    "Additional Cost",
                    "Total Cost",
                    "Id Schedule",
                    "Id Admin"
                }, 0
        );
    }

    // GET ALL DATA
    public static DefaultTableModel getBookedData() {
        DefaultTableModel model = createModel();

        String sql = "SELECT * FROM booking";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("booking_code"),
                    rs.getString("name"),
                    rs.getString("booking_date"),
                    rs.getString("status"),
                    rs.getString("additional_cost"),
                    rs.getString("total_cost"),
                    rs.getInt("id_schedule"),
                    rs.getString("id_admin")
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
                    "Booking Code",
                    "Name",
                    "Booking Date",
                    "Status",
                    "Additional Cost",
                    "Total Cost",
                    "Id Schedule",
                    "Id Admin"
                }, 0
        );

        // DEFAULT
        String orderBy = "";
        String orderType = "";

        // MAPPING ATAU PEMETAAN SORT BY UNTUK MENGUBAH PENULISAN VALUE NYA
        switch (sortBy) {
            case "Booking Code" -> orderBy = "booking_code";
            case "Name" -> orderBy = "name";
            case "Booking Date" -> orderBy = "booking_date";
            case "Status" -> orderBy = "status";
            case "Id Schedule" -> orderBy = "Id Schedule";
            case "Id Admin" -> orderBy = "id_admin";
        }

        // PENGURUTAN DESCENDING ATAU ASCENDING
        if ("Descending".equals(order)) {
            orderType = "DESC";
        }else {
            orderType = "ASC";
        }

        String sql = """
            SELECT * FROM booking
            WHERE name LIKE ?
               OR booking_date LIKE ?
               OR status LIKE ?
               OR additional_cost LIKE ?
               OR total_cost LIKE ?
               OR id_schedule LIKE ?
               OR id_admin LIKE ?
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
                    rs.getInt("booking_code"),
                    rs.getString("name"),
                    rs.getString("booking_date"),
                    rs.getString("status"),
                    rs.getString("additional_cost"),
                    rs.getString("total_cost"),
                    rs.getInt("id_schedule"),
                    rs.getString("id_admin")
                });
            }

        } catch (SQLException e) {
            System.out.println("Error load schedule: " + e.getMessage());
        }

        return model;
    }
}