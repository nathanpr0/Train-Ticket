package server.controller.booking_menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class BookingCodeGenerator {

    // GENERATE BOOKING CODE RANDOM
    public String generateBookingCode(Connection conn) throws SQLException {
        String code;
        do {
            code = "BK-" + UUID.randomUUID()
                    .toString()
                    .substring(0, 6)
                    .toUpperCase();
        } while (bookingCodeExists(conn, code));
        return code;
    }

    // CEK DUPLIKAT BOOKING CODE
    private boolean bookingCodeExists(Connection conn, String code) throws SQLException {
        String sql = "SELECT 1 FROM booking WHERE booking_code=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            return ps.executeQuery().next();
        }
    }
}
