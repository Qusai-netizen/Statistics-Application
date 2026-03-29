package statistics.UIData;

import java.awt.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table {

    public JTable table;
    public JScrollPane scrollPane;

    public Table(Object[][] data, String[] columns) {
        table = new JTable(new DefaultTableModel(data, columns));
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 1000));
    }
}
