package Charts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

public class FreqHistogram extends JPanel {

    private ArrayList<Integer> freq;
    private ArrayList<Double> classBoundaries;

    public FreqHistogram(ArrayList<Integer> freq, ArrayList<Double> classBoundaries) {
        this.freq = new ArrayList<>(freq);
        this.classBoundaries = new ArrayList<>(classBoundaries);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (freq.isEmpty() || classBoundaries.isEmpty()) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int left = 30;
        int right = 40;
        int top = 40;
        int bottom = 150;
        int chartWidth = width - left - right;
        int chartHeight = height - top - bottom;
        int xBase = left;
        int yBase = top + chartHeight;

        g2.setColor(Color.GRAY);
        g2.drawLine(xBase, yBase, xBase + chartWidth, yBase);
        g2.drawLine(xBase, yBase, xBase, top);

        int maxFreq = Collections.max(freq) + 2;
        int barWidth = chartWidth / (freq.size() + 1);

        g2.setColor(Color.BLACK);
        for (int i = 0; i <= 4; i++) {
            int value = i * maxFreq / 4;
            int y = yBase - (i * chartHeight / 4);
            g2.drawString(value + " -", xBase - 12, y + 2);
        }

        int xParts = chartWidth / (classBoundaries.size() + 1);
        int x = xBase;
        for (double boundary : classBoundaries) {
            x += xParts;
            g2.drawString(String.valueOf(boundary), x, yBase + 20);
        }

        g2.setColor(new Color(60, 120, 200));
        x = xBase;
        for (int value : freq) {
            int barHeight = (int) ((value / (double) maxFreq) * chartHeight);
            x += xParts;
            int y = yBase - barHeight;
            g2.fillRect(x, y, barWidth - 4, barHeight);
        }

        g2.setColor(Color.BLACK);
        g2.drawString("Frequency Histogram", xBase + chartWidth / 3, top - 10);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 300);
    }
}
