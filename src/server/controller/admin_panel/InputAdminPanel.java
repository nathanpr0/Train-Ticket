package server.controller.admin_panel;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InputAdminPanel {

    // VALIDASI INPUT
    public void cekInputKosong(String trainNumber, String machinist,
            String departure, String price,
            String origin, String destination) throws Exception {

        if (trainNumber.isEmpty() || machinist.isEmpty()
                || departure.isEmpty() || price.isEmpty()) {
            throw new Exception("Data tidak boleh kosong!");
        }

        if (origin.equals(destination)) {
            throw new Exception("Origin dan Destination tidak boleh sama!");
        }
    }

    // CEK BENTROK JADWAL & MASINIS
    public void cekBentrokJadwal(Connection conn,
            String trainNumber,
            String machinist,
            String departure) throws SQLException, Exception {

        // CEK KERETA
        String cekKereta = "SELECT 1 FROM schedule WHERE train_number=? AND departure_time=?";
        try (PreparedStatement ps = conn.prepareStatement(cekKereta)) {
            ps.setString(1, trainNumber);
            ps.setString(2, departure);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                throw new Exception("JADWAL BENTROK!\nKereta ini sudah ada di jam tersebut.");
            }
        }

        // CEK MASINIS
        String cekMasinis = "SELECT 1 FROM schedule WHERE machinist=? AND departure_time=?";
        try (PreparedStatement ps = conn.prepareStatement(cekMasinis)) {
            ps.setString(1, machinist);
            ps.setString(2, departure);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                throw new Exception("MASINIS BENTROK!\nMasinis ini sudah mengemudi di jam tersebut.");
            }
        }
    }

    // INSERT DATABASE
    public void inputDataSchedule(Connection conn,
            String trainNumber,
            String machinist,
            String origin,
            String destination,
            String departure,
            int carriages,
            String kelas,
            int harga) throws SQLException {

        String sql = "INSERT INTO schedule "
                + "(train_number, machinist, origin, destination, departure_time, carriages, class, price) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, trainNumber);
            pst.setString(2, machinist);
            pst.setString(3, origin);
            pst.setString(4, destination);
            pst.setString(5, departure);
            pst.setInt(6, carriages);
            pst.setString(7, kelas);
            pst.setInt(8, harga);
            pst.executeUpdate();
        }
    }
}
