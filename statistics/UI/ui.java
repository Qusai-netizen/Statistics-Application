package UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import app.mainN;
import storage.statisDb.Experiment;

public class ui {

    private final Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension d = toolKit.getScreenSize();
    private CardLayout cardLayout;
    private JFrame frame;
    private HistoryPanel historyPanel;
    private StatisPanel statisticsPanel;
    public JPanel mainContainer;

    public ui() {
        mainN.db = new storage.statisDb();

        // To fit with the screen size directly.
        d.setSize(d.width, d.height - 40);

        frame = new JFrame("Statistics Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(d);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);
        rebuildPages();

        frame.add(mainContainer);

        showPage("Home");
        frame.setVisible(true);
    }

    public void showPage(String pageName) {
        if (pageName.equals("History") && historyPanel != null) {
            historyPanel.refreshHistory();
        }

        cardLayout.show(mainContainer, pageName);
    }

    public void showStatistics(Experiment experiment) {
        if (statisticsPanel != null) {
            mainContainer.remove(statisticsPanel);
        }

        statisticsPanel = mainN.buildStatisPanel(experiment, this);
        mainContainer.add(statisticsPanel, "Statistics");
        showPage("Statistics");
        mainContainer.revalidate();
        mainContainer.repaint();
    }

    public void setNightMode(boolean nightMode) {
        Theme.nightMode = nightMode;
        rebuildPages();
        showPage("Settings");
        frame.revalidate();
        frame.repaint();
    }

    private void rebuildPages() {
        mainContainer.removeAll();
        historyPanel = new HistoryPanel(this);
        mainContainer.add(new HomePanel(this), "Home");
        mainContainer.add(new InputPanel(this), "Input");
        mainContainer.add(historyPanel, "History");
        mainContainer.add(new SettingsPanel(this), "Settings");
        statisticsPanel = null;
        showPage("Home");
    }
}
