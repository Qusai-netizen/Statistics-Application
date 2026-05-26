package UI;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import app.mainN;
import storage.statisDb.Experiment;

public class HistoryPanel extends JPanel {

    private final DefaultListModel<Experiment> historyModel;
    private final JList<Experiment> historyList;
    private final JTextArea detailsArea;

    public HistoryPanel(ui mainFrame) {
        setLayout(new BorderLayout(15, 15));
        setBorder(new EmptyBorder(24, 28, 24, 28));
        Theme.panel(this);

        JLabel title = new JLabel("Experiments History", SwingConstants.CENTER);
        Theme.title(title, 26);
        add(title, BorderLayout.NORTH);

        historyModel = new DefaultListModel<>();
        historyList = new JList<>(historyModel);
        historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        historyList.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        historyList.setBorder(new EmptyBorder(8, 8, 8, 8));
        historyList.setBackground(Theme.surface());
        historyList.setForeground(Theme.text());

        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        detailsArea.setForeground(Theme.text());
        Theme.surface(detailsArea);

        JScrollPane listScrollPane = new JScrollPane(historyList);
        listScrollPane.setPreferredSize(new Dimension(320, 0));
        Theme.surface(listScrollPane);

        JScrollPane detailsScrollPane = new JScrollPane(detailsArea);
        Theme.surface(detailsScrollPane);

        JPanel centerPanel = new JPanel(new BorderLayout(15, 0));
        centerPanel.setOpaque(false);
        centerPanel.add(listScrollPane, BorderLayout.WEST);
        centerPanel.add(detailsScrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        JButton viewButton = createModernButton("Open Statistics", "[VIEW]");
        viewButton.addActionListener(e -> {
            Experiment selected = historyList.getSelectedValue();
            if (selected != null) {
                mainFrame.showStatistics(selected);
            }
        });

        JButton backButton = createModernButton("Back To Home", "[HOME]");
        backButton.addActionListener(e -> mainFrame.showPage("Home"));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        bottomPanel.setOpaque(false);
        bottomPanel.add(viewButton);
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        historyList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showExperimentDetails(historyList.getSelectedValue());
            }
        });

        refreshHistory();
    }

    private JButton createModernButton(String text, String icon) {
        JButton btn = new JButton(icon + " " + text);
        btn.setPreferredSize(new Dimension(200, 42));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Theme.button(btn);

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(Theme.primaryHover());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(Theme.primary());
            }
        });

        return btn;
    }

    public void refreshHistory() {
        historyModel.clear();

        ArrayList<Experiment> expers = mainN.db.getAllExperiments();
        for (Experiment exper : expers) {
            historyModel.addElement(exper);
        }

        if (historyModel.isEmpty()) {
            detailsArea.setText("No experiments saved yet.");
        } else {
            historyList.setSelectedIndex(0);
        }
    }

    private void showExperimentDetails(Experiment experiment) {
        if (experiment == null) {
            detailsArea.setText("No experiment selected.");
            return;
        }

        StringBuilder details = new StringBuilder();
        details.append("Experiment Details\n");
        details.append("--------------------------\n");
        details.append("Name: ").append(experiment.experimentName).append("\n");
        details.append("Type: ").append(experiment.type).append("\n\n");

        if (experiment.type == Experiment.enType.QUAL) {
            details.append("Data Samples:\n").append(experiment.samplesS);
        } else if (experiment.type == Experiment.enType.QUAN) {
            details.append("Classes Number: ").append(experiment.getClassesNum()).append("\n");
            details.append("Data Samples:\n").append(experiment.samplesInt);
        } else if (experiment.type == Experiment.enType.QUANCF) {
            details.append("Classes Definitions:\n").append(experiment.classes).append("\n\n");
            details.append("Frequencies:\n").append(experiment.freq);
        }

        detailsArea.setText(details.toString());
        detailsArea.setCaretPosition(0);
    }
}
