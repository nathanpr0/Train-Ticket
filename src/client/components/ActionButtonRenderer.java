package client.components;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ActionButtonRenderer extends JButton implements TableCellRenderer {

    public ActionButtonRenderer() {
        setText(""); // hapus teks
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);

        setIcon(new ImageIcon(
                ActionButtonRenderer.class.getResource("/img/icons8-print-25Black.png")
        ));
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        return this;
    }
}
