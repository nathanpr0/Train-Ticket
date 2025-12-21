package server.controller.admin_panel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import server.sessions.UserSession;

public class DeleteBooking {

    // VALIDASI INPUT
    public void validate(String bookingCode) throws Exception {
        if (bookingCode == null || bookingCode.trim().isEmpty()) {
            throw new Exception("Booking Ticket tidak boleh kosong!");
        }
    }

    // DELETE BOOKING
    public void deleteByBookingCode(Connection conn, String bookingCode)
            throws SQLException, Exception {

        String adminId = UserSession.getAdminId();
        if (adminId == null) {
            throw new Exception("Session admin tidak ditemukan!");
        }

        String sql = "DELETE FROM booking WHERE booking_code = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, bookingCode);

            int result = pst.executeUpdate();
            if (result == 0) {
                throw new Exception("Ticket tidak ditemukan!");
            }
        }
    }
}
