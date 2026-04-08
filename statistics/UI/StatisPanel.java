package UI;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class StatisPanel extends JPanel {

    public StatisPanel(JScrollPane tableScrollPane) {
        setLayout(new BorderLayout());
        add(tableScrollPane, BorderLayout.CENTER);
    }

    public StatisPanel(TablesPanel tablesPanel, ChartsPanel chartsPanel) {
        setLayout(new BorderLayout(10, 0));

        if (tablesPanel != null) {
            add(tablesPanel, BorderLayout.CENTER);
        }

        if (chartsPanel != null) {
            add(chartsPanel, BorderLayout.WEST);
        }
    }
}
