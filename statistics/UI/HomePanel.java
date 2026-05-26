package UI;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class HomePanel extends JPanel {

    public HomePanel(ui mainFrame) {
        Theme.panel(this);
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(50, 50, 50, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel title = new JLabel("Statistics Dashboard", SwingConstants.CENTER);
        Theme.title(title, 30);
        gbc.insets = new Insets(0, 0, 40, 0);
        add(title, gbc);

        gbc.insets = new Insets(10, 0, 10, 0);

        JButton newCalBtn = createModernButton("New Analysis", "[NEW]");
        JButton historyBtn = createModernButton("View History", "[HIS]");
        JButton settingsBtn = createModernButton("Settings", "[SET]");

        newCalBtn.addActionListener(e -> mainFrame.showPage("Input"));
        historyBtn.addActionListener(e -> mainFrame.showPage("History"));
        settingsBtn.addActionListener(e -> mainFrame.showPage("Settings"));

        add(newCalBtn, gbc);
        add(historyBtn, gbc);
        add(settingsBtn, gbc);
    }

    private JButton createModernButton(String text, String icon) {
        JButton btn = new JButton(icon + "  " + text);
        btn.setPreferredSize(new Dimension(320, 62));
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
}
