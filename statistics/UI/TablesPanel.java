package UI;

import javax.swing.*;

import UIData.Table;

import java.awt.*;

public class TablesPanel extends JPanel {

    Table t1;
    Table t2;

    public TablesPanel(Object[][][] tablesData, String[][] tablesColumns) {

        setLayout(new GridLayout(2, 1));

        t1 = new Table(tablesData[0], tablesColumns[0]);
        t2 = new Table(tablesData[1], tablesColumns[1]);

        add(t1.scrollPane);
        add(t2.scrollPane);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 1000);
    }
}
