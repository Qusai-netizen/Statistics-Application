package UI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import UIData.Table;

public class TablesPanel extends JPanel {

    Table t1;
    Table t2;

    public TablesPanel(ArrayList<ArrayList<ArrayList<Object>>> tablesData, ArrayList<ArrayList<String>> tablesColumns) {
        setLayout(new GridLayout(2, 1));
        Theme.panel(this);

        t1 = new Table(tablesData.get(0), tablesColumns.get(0));
        t2 = new Table(tablesData.get(1), tablesColumns.get(1));

        add(t1.scrollPane);
        add(t2.scrollPane);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 1000);
    }
}
