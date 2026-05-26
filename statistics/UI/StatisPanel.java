package UI;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class StatisPanel extends JPanel {

    public StatisPanel(JScrollPane tableScrollPane) {
        this(tableScrollPane, null);
    }

    public StatisPanel(JScrollPane tableScrollPane, ui mainFrame) {
        setLayout(new BorderLayout());
        Theme.panel(this);
        add(tableScrollPane, BorderLayout.CENTER);
        addBackButton(mainFrame);
    }

    public StatisPanel(TablesPanel tablesPanel, ChartsPanel chartsPanel) {
        this(tablesPanel, chartsPanel, null);
    }

    public StatisPanel(TablesPanel tablesPanel, ChartsPanel chartsPanel, ui mainFrame) {
        setLayout(new BorderLayout(10, 0));
        Theme.panel(this);

        if (tablesPanel != null) {
            add(tablesPanel, BorderLayout.CENTER);
        }

        if (chartsPanel != null) {
            add(chartsPanel, BorderLayout.WEST);
        }

        addBackButton(mainFrame);
    }

    private void addBackButton(ui mainFrame) {
        if (mainFrame == null) {
            return;
        }

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        bottomPanel.setOpaque(false);
        JButton backButton = new JButton("[BACK] Back To Home");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        Theme.button(backButton);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> mainFrame.showPage("Home"));
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
