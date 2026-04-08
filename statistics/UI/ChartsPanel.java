package UI;

import javax.swing.*;

import Charts.FreqHistogram;
import Charts.FreqPolygon;
import Charts.Ogive;
import Charts.PieChart;

import java.awt.*;

public class ChartsPanel extends JPanel {

    private CardLayout cardLayout;
    private JPanel cards;

    public ChartsPanel(double[] classRelativeF,
            int[] freq,
            double[] midPoint,
            double[] classBoundaries,
            int[] freqCumulative,
            double[] boundaries) {

        setLayout(new GridLayout(3, 1));

        // ===== Buttons =====
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JButton pieBtn = new JButton("Pie");
        JButton polyBtn = new JButton("Polygon");
        JButton histBtn = new JButton("Histogram");
        JButton ogiveBtn = new JButton("Ogive");

        buttons.add(pieBtn);
        buttons.add(polyBtn);
        buttons.add(histBtn);
        buttons.add(ogiveBtn);

        add(buttons, BorderLayout.CENTER);

        // Cards
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        cards.add(new PieChart(classRelativeF), "PIE");

        cards.add(new FreqPolygon(freq, midPoint), "POLY");

        cards.add(new FreqHistogram(freq, classBoundaries), "HIST");

        cards.add(new Ogive(freqCumulative, boundaries), "OGIVE");

        add(cards, BorderLayout.WEST);

        // ===== Button actions =====
        pieBtn.addActionListener(e -> cardLayout.show(cards, "PIE"));
        polyBtn.addActionListener(e -> cardLayout.show(cards, "POLY"));
        histBtn.addActionListener(e -> cardLayout.show(cards, "HIST"));
        ogiveBtn.addActionListener(e -> cardLayout.show(cards, "OGIVE"));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
}
