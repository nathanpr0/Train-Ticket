package server.controller.booking_menu;

import server.models.BookingSummary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.sessions.UserSession;

public class InputBookingTicket {

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

    // INSERT BOOKING
    public static String createBookingByScheduleId(
            Connection conn,
            String bookingName,
            int idSchedule
    ) throws Exception {

        String adminId = UserSession.getAdminId();
        if (adminId == null) {
            throw new Exception("Session admin tidak ditemukan!");
        }

        // AMBIL DATA SCHEDULE
        ScheduleService.ScheduleData s
                = ScheduleService.getScheduleData(conn, idSchedule);

        int additionalCost = BiayaAdmin.getFee(s.kelas);
        int totalCost = s.price + additionalCost;

        BookingCodeGenerator bikin_kode_booking = new BookingCodeGenerator();
        String bookingCode = bikin_kode_booking.generateBookingCode(conn);

        String sql = """
            INSERT INTO booking
            (booking_code, name, booking_date, status,
             additional_cost, total_cost, id_schedule, id_admin)
            VALUES (?, ?, NOW(), 'CONFIRMED', ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bookingCode);
            ps.setString(2, bookingName);
            ps.setInt(3, additionalCost);
            ps.setInt(4, totalCost);
            ps.setInt(5, idSchedule);
            ps.setString(6, adminId);
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
            b.booking_code,
            b.name AS booking_name,
            b.status,
            b.booking_date,
            b.additional_cost,
            b.total_cost,

            s.origin,
            s.destination,
            s.train_number,
            s.class,
            s.carriages,
            s.departure_time,
            s.price
        FROM booking b
        JOIN schedule s ON b.id_schedule = s.id_schedule
        WHERE b.booking_code = ?
    """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bookingCode);

            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {
                    throw new SQLException("Booking tidak ditemukan!");
                }

                BookingSummary summary = new BookingSummary();

                // ===== BOOKING =====
                summary.setBookingCode(rs.getString("booking_code"));
                summary.setBookingName(rs.getString("booking_name"));
                summary.setStatus(rs.getString("status"));
                summary.setDate(rs.getDate("booking_date").toString());

                // ===== ROUTE =====
                summary.setOrigin(rs.getString("origin"));
                summary.setDestination(rs.getString("destination"));

                // ===== TRAIN =====
                summary.setTrainNumber(rs.getString("train_number"));
                summary.setKelas(rs.getString("class"));
                summary.setCarriages(rs.getInt("carriages"));
                summary.setDepartureTime(rs.getString("departure_time"));

                // ===== COST =====
                summary.setCost(rs.getInt("price"));
                summary.setAdditionalCost(rs.getInt("additional_cost"));
                summary.setTotalCost(rs.getInt("total_cost"));

                return summary;
            }
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

}
