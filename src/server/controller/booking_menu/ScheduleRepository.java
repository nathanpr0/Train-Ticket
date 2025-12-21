package server.controller.booking_menu;

import java.sql.*;

public class ScheduleRepository {

    // DESTINATION
    public ResultSet loadDestinations(Connection conn) throws SQLException {
        String sql = "SELECT DISTINCT destination FROM schedule";
        return conn.prepareStatement(sql).executeQuery();
    }

    // CLASS BERDASARKAN DESTINATION
    public ResultSet loadClasses(Connection conn, String destination)
            throws SQLException {

        String sql = "SELECT DISTINCT `class` FROM schedule WHERE destination=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, destination);
        return ps.executeQuery();
    }

    // DEPARTURE TIME BERDASARKAN DESTINATION + CLASS
    public ResultSet loadDepartureTimes(
            Connection conn,
            String destination,
            String kelas
    ) throws SQLException {

        String sql = """
            SELECT DISTINCT departure_time
            FROM schedule
            WHERE destination = ?
              AND `class` = ?
            ORDER BY departure_time
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, destination);
        ps.setString(2, kelas);
        return ps.executeQuery();
    }

    // TRAIN + CARRIAGE + PRICE (UNTUK COMBOBOX TERAKHIR)
    public ResultSet loadTrainCarriageAndPrice(
            Connection conn,
            String destination,
            String kelas,
            String departure
    ) throws SQLException {

        String sql = """
            SELECT id_schedule, train_number, carriages, price
            FROM schedule
            WHERE destination = ?
              AND `class` = ?
              AND departure_time = ?
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, destination);
        ps.setString(2, kelas);
        ps.setString(3, departure);
        return ps.executeQuery();
    }
}
