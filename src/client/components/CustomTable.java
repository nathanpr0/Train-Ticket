package client.components;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class CustomTable extends JTable {

    public CustomTable() {
        super(new DefaultTableModel(
                new Object[][]{
                        {"Sample", "Sample", "Sample"}
                },
                new String[]{"Sample", "Sample", "Sample"}
        ));

        setRowHeight(30);
        setShowGrid(true);
        setGridColor(new Color(200, 200, 200));

        // =========================
        // FIX HEADER COLOR 100%
        // =========================
        JTableHeader header = getTableHeader();
        header.setPreferredSize(new Dimension(0, 35));
        header.setReorderingAllowed(false);
        header.setDefaultRenderer(new HeaderRenderer(this));
    }

    // Custom Header Renderer
    private static class HeaderRenderer implements TableCellRenderer {

        private final JTable table;

        public HeaderRenderer(JTable table) {
            this.table = table;
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            JLabel label = new JLabel(value.toString());
            label.setOpaque(true);

            // === WARNA HEADER FIX (tidak akan dioverride LAF) ===
            label.setBackground(new Color(68, 68, 68));
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Inter", Font.BOLD, 14));
            label.setHorizontalAlignment(SwingConstants.CENTER);

            return label;
        }
    }
}