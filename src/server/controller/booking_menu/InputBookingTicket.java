package server.controller.booking_menu;

import server.models.BookingSummary;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import server.sessions.UserSession;
import java.time.LocalDate;

public class InputBookingTicket {

    private static final int biaya_admin = 2000;

    // VALIDASI INPUT
    public void cekInputKosong(String bookingName,
            String destination,
            String kelas,
            String price,
            String departure) throws Exception {

        if (bookingName.isEmpty()
                || destination == null
                || kelas == null
                || price == null
                || departure == null) {
            throw new Exception("Semua data booking wajib diisi!");
        }
    }

    // AMBIL HARGA DARI SCHEDULE
    public int getPriceBySchedule(Connection conn, int idSchedule) throws Exception {
        String sql = "SELECT price FROM schedule WHERE id_schedule=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idSchedule);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("price");
            }
            throw new Exception("Harga tidak ditemukan!");
        }
    }

    // GENERATE BOOKING CODE RANDOM
    private String generateBookingCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        int length = 6;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(index));
        }
        return "BK-" + sb.toString();
    }

    // INSERT BOOKING
    public String createBookingByScheduleId(
            Connection conn,
            String bookingName,
            int idSchedule
    ) throws Exception {

        String adminId = UserSession.getAdminId();
        if (adminId == null) {
            throw new Exception("Session admin tidak ditemukan!");
        }

        int price = getPriceBySchedule(conn, idSchedule);
        int kena_biaya_admin = price + biaya_admin;

        String bookingCode = generateBookingCode();

        String sql = """
        INSERT INTO booking
        (booking_code, name, booking_date, status,
         additional_cost, total_cost, id_schedule, id_admin)
        VALUES (?, ?, NOW(), ?, ?, ?, ?, ?)
    """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bookingCode);
            ps.setString(2, bookingName);
            ps.setString(3, "CONFIRMED");
            ps.setInt(4, biaya_admin);
            ps.setInt(5, kena_biaya_admin);
            ps.setInt(6, idSchedule);
            ps.setString(7, adminId);
            ps.executeUpdate();
        }

        return bookingCode;
    }

    // UNTUK SUMMARY PANEL
    public BookingSummary getBookingSummary(
            Connection conn,
            String bookingCode
    ) throws SQLException {

        String sql = """
        SELECT
            b.name AS booking_name,
            b.booking_date,
            b.additional_cost,
            b.total_cost,
            s.origin,
            s.destination,
            s.price
        FROM booking b
        JOIN schedule s ON b.id_schedule = s.id_schedule
        WHERE b.booking_code = ?
    """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bookingCode);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Booking tidak ditemukan!");
            }

            BookingSummary summary = new BookingSummary();
            summary.setBookingName(rs.getString("booking_name"));
            summary.setDate(rs.getDate("booking_date").toString());
            summary.setOrigin(rs.getString("origin"));
            summary.setDestination(rs.getString("destination"));
            summary.setCost(rs.getInt("price"));
            summary.setAdditionalCost(rs.getInt("additional_cost"));
            summary.setTotalCost(rs.getInt("total_cost"));

            return summary;
        }
    }

    // CEK DUPLIKAT BOOKING CODE
    private boolean bookingCodeExists(Connection conn, String code) throws SQLException {
        String sql = "SELECT 1 FROM booking WHERE booking_code=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            return ps.executeQuery().next();
        }
    }

    // MEMBUKA TABLE SCHEDULE
    public int findScheduleId(
            Connection conn,
            String destination,
            String kelas,
            String departureTime
    ) throws Exception {

        String sql = """
        SELECT id_schedule, price
        FROM schedule
        WHERE destination = ?
        AND `class` = ?
        AND departure_time = ?
    """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, destination);
            ps.setString(2, kelas);
            ps.setString(3, departureTime);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_schedule");
            } else {
                throw new Exception("Jadwal tidak ditemukan!");
            }
        }
    }

    // MENGAMBIL DATA destination DARI TABLE SCHEDULE
    public ResultSet loadDestinations(Connection conn) throws SQLException {
        String sql = "SELECT DISTINCT destination FROM schedule";
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps.executeQuery();
    }

    // MENGAMBIL DATA class DARI TABLE SCHEDULE
    public ResultSet loadClasses(Connection conn, String destination) throws SQLException {
        String sql = "SELECT DISTINCT `class` FROM schedule WHERE destination=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, destination);
        return ps.executeQuery();
    }

    // MENGAMBIL DATA departure_time DARI TABLE SCHEDULE
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

    // MENGAMBIL DATA train_number, carriages, & price dari TABLE SCHEDULE
    public ResultSet loadTrainCarriageAndPrice(
            Connection conn,
            String destination,
            String kelas,
            String departure
    ) throws Exception {

        String sql = """
        SELECT id_schedule, train_number, carriages, price
        FROM schedule
        WHERE destination = ?
        AND class = ?
        AND departure_time = ?
    """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, destination);
        ps.setString(2, kelas);
        ps.setString(3, departure);

        return ps.executeQuery();
    }

    public ResultSet loadScheduleDetail(
            Connection conn,
            String destination,
            String kelas,
            String departure
    ) throws SQLException {

        String sql = """
        SELECT id_schedule, carriages, price
        FROM schedule
        WHERE destination = ?
        AND `class` = ?
        AND departure_time = ?
        LIMIT 1
    """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, destination);
        ps.setString(2, kelas);
        ps.setString(3, departure);
        return ps.executeQuery();
    }

}
