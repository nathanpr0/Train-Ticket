package server.controller.admin_panel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteSchedule {

    // VALIDASI INPUT
    public void cekInputKosong(String idSchedule) throws Exception {
        if (idSchedule == null || idSchedule.trim().isEmpty()) {
            throw new Exception("ID Schedule tidak boleh kosong!");
        }
    }

    // DELETE BY ID
    public void deleteByIdSchedule(Connection conn, String idSchedule)
            throws SQLException, Exception {

        String sql = "DELETE FROM schedule WHERE id_schedule = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, idSchedule);

            int result = pst.executeUpdate();
            if (result == 0) {
                throw new Exception("ID Schedule tidak ditemukan!");
            }
        }
    }
}
