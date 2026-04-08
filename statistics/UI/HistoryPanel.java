package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
import javax.swing.border.LineBorder;

import app.mainN;
import storage.statisDb.Experiment;

public class HistoryPanel extends JPanel {

    private final DefaultListModel<Experiment> historyModel;
    private final JList<Experiment> historyList;
    private final JTextArea detailsArea;
    
    // ألوان التصميم الموحدة
    private final Color PRIMARY_BLUE = new Color(41, 128, 185);
    private final Color BACKGROUND_COLOR = new Color(245, 247, 250);

    public HistoryPanel(ui mainFrame) {

        setLayout(new BorderLayout(15, 15));
        setBackground(BACKGROUND_COLOR);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Experiments History", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(new Color(44, 62, 80));
        add(title, BorderLayout.NORTH);

        historyModel = new DefaultListModel<>();
        historyList = new JList<>(historyModel);
        historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        historyList.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        historyList.setBorder(new EmptyBorder(5, 5, 5, 5));

        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        detailsArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane listScrollPane = new JScrollPane(historyList);
        listScrollPane.setPreferredSize(new Dimension(300, 0));
        listScrollPane.setBorder(new LineBorder(new Color(200, 200, 200)));

        JScrollPane detailsScrollPane = new JScrollPane(detailsArea);
        detailsScrollPane.setBorder(new LineBorder(new Color(200, 200, 200)));

        JPanel centerPanel = new JPanel(new BorderLayout(15, 0));
        centerPanel.setOpaque(false);
        centerPanel.add(listScrollPane, BorderLayout.WEST);
        centerPanel.add(detailsScrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // تعديل: إنشاء زر رجوع بستايل عصري (Modern Style)
        JButton backButton = createModernButton("Back To Home", "🏠");
        backButton.addActionListener(e -> mainFrame.showPage("Home"));
        
        // وضع الزر في لوحة سفلية لإعطائه مساحة مناسبة
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        historyList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showExperimentDetails(historyList.getSelectedValue());
            }
        });

        refreshHistory();
    }

    // دالة مساعدة لإنشاء الزر بنفس أسلوب InputPanel للاتساق
    private JButton createModernButton(String text, String icon) {
        JButton btn = new JButton(icon + " " + text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(PRIMARY_BLUE);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(200, 40));
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // تأثير الحركية عند تمرير الماوس
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(PRIMARY_BLUE.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(PRIMARY_BLUE);
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