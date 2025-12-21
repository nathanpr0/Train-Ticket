package client.components;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

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
            String name = table.getValueAt(row, 1).toString();
            String status = table.getValueAt(row, 3).toString();

            JOptionPane.showMessageDialog(null,
                "Booking Code : " + bookingCode +
                "\nName : " + name +
                "\nStatus : " + status,
                "Booking Detail",
                JOptionPane.INFORMATION_MESSAGE
            );
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
