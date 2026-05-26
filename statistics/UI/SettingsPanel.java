package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SettingsPanel extends JPanel {

    public SettingsPanel(ui mainFrame) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(30, 50, 30, 50));
        Theme.panel(this);

        JLabel title = new JLabel("Settings", SwingConstants.CENTER);
        Theme.title(title, 28);
        add(title, BorderLayout.NORTH);

        JPanel content = new JPanel(new GridBagLayout());
        content.setOpaque(false);

        JPanel modePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 18, 12));
        Theme.surface(modePanel);

        JLabel modeLabel = new JLabel("Night Mode");
        Theme.label(modeLabel);

        JCheckBox nightModeBox = new JCheckBox();
        nightModeBox.setSelected(Theme.nightMode);
        nightModeBox.setOpaque(false);
        nightModeBox.setForeground(Theme.text());
        nightModeBox.addActionListener(e -> mainFrame.setNightMode(nightModeBox.isSelected()));

        modePanel.add(modeLabel);
        modePanel.add(nightModeBox);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(30, 0, 12, 0);
        content.add(modePanel, gbc);

        add(content, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false);

        JButton backButton = new JButton("[HOME] Back To Home");
        Theme.button(backButton);
        backButton.addActionListener(e -> mainFrame.showPage("Home"));
        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}
