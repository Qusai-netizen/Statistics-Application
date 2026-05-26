package UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Theme {

    public static boolean nightMode = false;

    public static Color background() {
        return nightMode ? new Color(18, 24, 32) : new Color(245, 247, 250);
    }

    public static Color surface() {
        return nightMode ? new Color(31, 41, 55) : Color.WHITE;
    }

    public static Color primary() {
        return nightMode ? new Color(96, 165, 250) : new Color(41, 128, 185);
    }

    public static Color primaryHover() {
        return nightMode ? new Color(59, 130, 246) : new Color(52, 152, 219);
    }

    public static Color text() {
        return nightMode ? new Color(241, 245, 249) : new Color(44, 62, 80);
    }

    public static Color mutedText() {
        return nightMode ? new Color(203, 213, 225) : new Color(100, 100, 100);
    }

    public static Color border() {
        return nightMode ? new Color(71, 85, 105) : new Color(200, 200, 200);
    }

    public static void panel(JComponent component) {
        component.setBackground(background());
    }

    public static void surface(JComponent component) {
        component.setBackground(surface());
        component.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(border(), 1, true),
                new EmptyBorder(12, 14, 12, 14)));
    }

    public static void title(JLabel label, int size) {
        label.setFont(new Font("Segoe UI", Font.BOLD, size));
        label.setForeground(text());
    }

    public static void label(JLabel label) {
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(mutedText());
    }

    public static void button(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(primary());
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 16, 10, 16));
    }
}
