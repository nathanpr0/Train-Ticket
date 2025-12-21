package client.components;

import client.views.Receipt;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import server.Koneksi;

import java.sql.Connection;
import server.controller.booking_menu.InputBookingTicket;
import server.models.BookingSummary;

public class ActionButtonEditor extends DefaultCellEditor {

    private JButton button;
    private JTable table;
    private int row;

    public ActionButtonEditor(JTable table) {
        super(new JTextField());
        this.table = table;

        button = new JButton();
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);

        button.setIcon(new ImageIcon(
                ActionButtonEditor.class.getResource("/img/icons8-print-25Black.png")
        ));

        button.addActionListener(e -> {
            fireEditingStopped();

            String bookingCode = table.getValueAt(row, 0).toString();

            try (Connection conn = Koneksi.getConnection()) {

                InputBookingTicket booking = new InputBookingTicket();

                // 🔥 Ambil summary lengkap dari database
                BookingSummary summary = booking.getBookingSummary(conn, bookingCode);

                // 🔥 Buka Receipt
                Receipt receipt = new Receipt(summary);
                receipt.setLocationRelativeTo(null);
                receipt.setVisible(true);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage(),
                        "Gagal membuka receipt",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    @Override
    public java.awt.Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected,
            int row, int column) {

        this.row = row;
        return button;
    }
}
