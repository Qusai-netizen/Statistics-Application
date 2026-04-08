package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HomePanel extends JPanel {
    
    private final Color PRIMARY_BLUE = new Color(41, 128, 185);
    private final Color HOVER_BLUE = new Color(52, 152, 219);
    private final Color BACKGROUND_COLOR = new Color(245, 247, 250);

    public HomePanel(ui mainFrame) {
        setBackground(BACKGROUND_COLOR);
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(50, 50, 50, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel title = new JLabel("Statistics Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(44, 62, 80));
        gbc.insets = new Insets(0, 0, 40, 0);
        add(title, gbc);

        gbc.insets = new Insets(10, 0, 10, 0);

        JButton newCalBtn = createModernButton("New Analysis", "➕");
        JButton historyBtn = createModernButton("View History", "📜");
        JButton settingsBtn = createModernButton("Settings", "⚙️");

        newCalBtn.addActionListener(e -> mainFrame.showPage("Input"));
        historyBtn.addActionListener(e -> mainFrame.showPage("History"));

        add(newCalBtn, gbc);
        add(historyBtn, gbc);
        add(settingsBtn, gbc);
    }

    private JButton createModernButton(String text, String icon) {
        JButton btn = new JButton(icon + "  " + text);
        
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btn.setPreferredSize(new Dimension(300, 60));
        
        btn.setBackground(PRIMARY_BLUE);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(HOVER_BLUE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(PRIMARY_BLUE);
            }
        });

        return btn;
    }
}
