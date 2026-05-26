package UIData;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import UI.Theme;

public class Table {

    public JTable table;
    public JScrollPane scrollPane;

    public Table(ArrayList<ArrayList<Object>> data, ArrayList<String> columns) {
        table = new JTable(new DefaultTableModel(toRows(data), new Vector<>(columns)));
        table.setBackground(Theme.surface());
        table.setForeground(Theme.text());
        table.setGridColor(Theme.border());
        table.getTableHeader().setBackground(Theme.primary());
        table.getTableHeader().setForeground(java.awt.Color.WHITE);
        table.setRowHeight(28);
        scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Theme.surface());
        scrollPane.setPreferredSize(new Dimension(1000, 1000));
    }

    private Vector<Vector<Object>> toRows(ArrayList<ArrayList<Object>> data) {
        Vector<Vector<Object>> rows = new Vector<>();

        for (ArrayList<Object> row : data) {
            rows.add(new Vector<>(row));
        }

        return rows;
    }
}
