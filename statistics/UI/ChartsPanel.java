package UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import Charts.FreqHistogram;
import Charts.FreqPolygon;
import Charts.Ogive;
import Charts.PieChart;

public class ChartsPanel extends JPanel {

    private CardLayout cardLayout;
    private JPanel cards;

    public ChartsPanel(ArrayList<Double> classRelativeF,
            ArrayList<Integer> freq,
            ArrayList<Double> midPoint,
            ArrayList<Double> classBoundaries,
            ArrayList<Integer> freqCumulative,
            ArrayList<Double> boundaries) {

        setLayout(new GridLayout(3, 1));
        Theme.panel(this);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buttons.setOpaque(false);

        JButton pieBtn = new JButton("Pie");
        JButton polyBtn = new JButton("Polygon");
        JButton histBtn = new JButton("Histogram");
        JButton ogiveBtn = new JButton("Ogive");

        Theme.button(pieBtn);
        Theme.button(polyBtn);
        Theme.button(histBtn);
        Theme.button(ogiveBtn);

        buttons.add(pieBtn);
        buttons.add(polyBtn);
        buttons.add(histBtn);
        buttons.add(ogiveBtn);

        add(buttons, BorderLayout.CENTER);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        cards.add(new PieChart(classRelativeF), "PIE");
        cards.add(new FreqPolygon(freq, midPoint), "POLY");
        cards.add(new FreqHistogram(freq, classBoundaries), "HIST");
        cards.add(new Ogive(freqCumulative, boundaries), "OGIVE");

        add(cards, BorderLayout.WEST);

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
