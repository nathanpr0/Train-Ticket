package server.controller.booking_menu;

import java.sql.*;

public class ScheduleService {

    // DTO kecil biar rapi
    public static class ScheduleData {

        public final String origin;
        public final String destination;
        public final String kelas;
        public final int price;

        public ScheduleData(String origin, String destination, String kelas, int price) {
            this.origin = origin;
            this.destination = destination;
            this.kelas = kelas;
            this.price = price;
        }
    }

    public static ScheduleData getScheduleData(
            Connection conn,
            int idSchedule
    ) throws Exception {

        String sql = """
            SELECT origin, destination, `class`, price
            FROM schedule
            WHERE id_schedule = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idSchedule);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new Exception("Schedule tidak ditemukan!");
            }

            return new ScheduleData(
                    rs.getString("origin"),
                    rs.getString("destination"),
                    rs.getString("class"),
                    rs.getInt("price")
            );
        }
    }

}
