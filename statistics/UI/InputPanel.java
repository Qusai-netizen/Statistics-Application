package UI;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import app.mainN;
import storage.statisDb;

public class InputPanel extends JPanel {

    private JTextField nameField;
    private JTextArea numDataArea;
    private JTextArea txtDataArea;

    private statisDb.Experiment.enType selectedType;

    private JLabel label1;
    private JLabel label2;

    private JScrollPane scrollPane;
    private JScrollPane scrollPane2;
    private StatisPanel statisticsPanel;
    HistoryPanel historyPanel;

    public InputPanel(ui mainFrame) {
        Theme.panel(this);
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(30, 50, 30, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        JLabel title = new JLabel("New Statistical Analysis", SwingConstants.CENTER);
        Theme.title(title, 26);
        gbc.insets = new Insets(0, 0, 20, 0);
        add(title, gbc);

        add(createLabel("Experiment Name:"), gbc);
        nameField = createModernTextField("Enter name here...");
        add(nameField, gbc);

        label1 = createLabel("");
        add(label1, gbc);

        txtDataArea = new JTextArea(4, 20);
        txtDataArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtDataArea.setLineWrap(true);
        txtDataArea.setForeground(Theme.text());
        Theme.surface(txtDataArea);
        scrollPane = new JScrollPane(txtDataArea);
        scrollPane.setPreferredSize(new Dimension(300, 100));
        add(scrollPane, gbc);

        label2 = createLabel("");
        add(label2, gbc);

        numDataArea = new JTextArea(4, 20);
        numDataArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        numDataArea.setLineWrap(true);
        numDataArea.setForeground(Theme.text());
        Theme.surface(numDataArea);
        scrollPane2 = new JScrollPane(numDataArea);
        scrollPane2.setPreferredSize(new Dimension(300, 100));
        add(scrollPane2, gbc);

        label1.setVisible(false);
        label2.setVisible(false);
        scrollPane.setVisible(false);
        scrollPane2.setVisible(false);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 40));
        buttonPanel.setOpaque(false);

        JButton qualBtn = createModernButton("Qualitative", "[TXT]");
        JButton quanBtn = createModernButton("Quantitative", "[NUM]");
        JButton quanCFBtn = createModernButton("Quantitative (Classes, Frequency)", "[CLS]");
        JButton submitBtn = createModernButton("Submit", "[OK]");
        JButton backButton = createModernButton("Back To Home", "[HOME]");

        buttonPanel.add(qualBtn);
        buttonPanel.add(quanBtn);
        buttonPanel.add(quanCFBtn);
        buttonPanel.add(submitBtn);
        
        gbc.insets = new Insets(20, 0, 10, 0);
        add(buttonPanel, gbc);

        add(backButton, gbc);

        qualBtn.addActionListener(e -> {
            selectedType = statisDb.Experiment.enType.QUAL;
            label1.setText("Qualitative Samples (Comma separated):");
            updateVisibility(true, false);
        });

        quanBtn.addActionListener(e -> {
            selectedType = statisDb.Experiment.enType.QUAN;
            label1.setText("Number of classes");
            label2.setText("Numeric Samples (Comma separated):");
            updateVisibility(true, true);
        });

        quanCFBtn.addActionListener(e -> {
            selectedType = statisDb.Experiment.enType.QUANCF;
            label1.setText("Classes (Comma separated) (X - Y):");
            label2.setText("Frequencies (Comma separated):");
            updateVisibility(true, true);
        });

        submitBtn.addActionListener(e -> {
            if (selectedType == null) {
                JOptionPane.showMessageDialog(this, "Please select a type first!");
                return;
            }

            statisDb.Experiment savedExperiment = processData(selectedType);

            if (savedExperiment != null) {
                mainFrame.showStatistics(savedExperiment);
            }
        });

        backButton.addActionListener(e -> mainFrame.showPage("Home"));

    }

    private void updateVisibility(boolean showScroll1, boolean showScroll2) {
        label1.setVisible(showScroll1);
        label2.setVisible(showScroll2);
        scrollPane.setVisible(showScroll1);
        scrollPane2.setVisible(showScroll2);
        revalidate();
        repaint();
    }

    private statisDb.Experiment processData(statisDb.Experiment.enType type) {
        String experName = nameField.getText().trim();
        String rawData1 = txtDataArea.getText().trim();
        String rawData2 = numDataArea.getText().trim();

        if (experName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an experiment name!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (mainN.db.getExperByName(experName) != null) {
            JOptionPane.showMessageDialog(this, "An experiment with this name already exists.", "Duplicate Name",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }

        ArrayList<String> sData = new ArrayList<>();
        ArrayList<Integer> iData = new ArrayList<>();
        int classesNum = 0;

        try {
            if (type == statisDb.Experiment.enType.QUAL) {
                if (rawData1.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please, enter Qualitative samples.", "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return null;
                }

                sData = parseTextValues(rawData1);
            }

            else if (type == statisDb.Experiment.enType.QUAN) {
                if (rawData1.isEmpty() || rawData2.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Please, enter The number of classes and Numeric samples.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return null;
                }

                classesNum = Integer.parseInt(rawData1);

                for (String value : parseTextValues(rawData2)) {
                    iData.add(Integer.parseInt(value));
                }
            }

            else if (type == statisDb.Experiment.enType.QUANCF) {

                if (rawData1.isEmpty() || rawData2.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please, fill both Classes and Frequencies fields.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                    return null;
                }

                sData = parseTextValues(rawData1);

                for (String value : parseTextValues(rawData2)) {
                    iData.add(Integer.parseInt(value));
                }

                if (sData.size() != iData.size()) {
                    JOptionPane.showMessageDialog(this, "The number of Classes must match the number of Frequencies!",
                            "Data Mismatch", JOptionPane.WARNING_MESSAGE);
                    return null;
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid numeric input! Please enter valid numbers separated by commas.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        statisDb.Experiment savedExperiment;
        if (type == statisDb.Experiment.enType.QUAN) {
            savedExperiment = mainN.db.addExper(type, experName, sData, iData, classesNum);
        } else {
            savedExperiment = mainN.db.addExper(type, experName, sData, iData);
        }

        JOptionPane.showMessageDialog(this, "Data Saved Successfully!");
        clearFields();
        return savedExperiment;
    }

    private void clearFields() {
        nameField.setText("");
        txtDataArea.setText("");
        numDataArea.setText("");
    }

    private ArrayList<String> parseTextValues(String rawData) {
        ArrayList<String> values = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < rawData.length(); i++) {
            char ch = rawData.charAt(i);
            if (ch == ',') {
                values.add(current.toString().trim());
                current.setLength(0);
            } else {
                current.append(ch);
            }
        }

        values.add(current.toString().trim());

        return values;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        Theme.label(label);
        return label;
    }

    private JTextField createModernTextField(String placeholder) {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tf.setForeground(Theme.text());
        Theme.surface(tf);
        return tf;
    }

    private JButton createModernButton(String text, String icon)
    {
        JButton btn = new JButton(icon + " " + text);
        Theme.button(btn);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

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
}
