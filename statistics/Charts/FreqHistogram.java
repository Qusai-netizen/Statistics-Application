package statistics.Charts;

import javax.swing.*;
import java.awt.*;

public class FreqHistogram extends JPanel {

    private int[] freq;
    private double[] classBoundaries;

    public FreqHistogram(int[] freq, double[] classBoundaries) {
        this.freq = freq;
        this.classBoundaries = classBoundaries;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

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

        // Axes
        g2.setColor(Color.GRAY);
        g2.drawLine(xBase, yBase, xBase + chartWidth, yBase);
        g2.drawLine(xBase, yBase, xBase, top);

        // Max frequency
        int maxFreq = 0;
        for (int f : freq)
            maxFreq = Math.max(maxFreq, f);
        maxFreq += 2;

        int barCount = freq.length;
        int barWidth = chartWidth / (barCount + 1);

        // Y-axis labels
        g2.setColor(Color.BLACK);
        for (int i = 0; i <= 4; i++) {
            int value = i * maxFreq / 4;
            int y = yBase - (i * chartHeight / 4);
            g2.drawString(String.valueOf(value) + " -", xBase - 12, y + 2);
        }

        int xParts = chartWidth / (classBoundaries.length + 1);
        {
            // X-axis labels
            int x = xBase;
            for (int i = 0; i < classBoundaries.length; i++) {
                x += xParts;
                g2.drawString(String.valueOf(classBoundaries[i]), x, yBase + 20);
            }
        }

        // Bars
        g2.setColor(new Color(60, 120, 200));
        int x = xBase;
        for (int i = 0; i < barCount; i++) {
            int barHeight = (int) ((freq[i] / (double) maxFreq) * chartHeight);
            x += xParts;
            int y = yBase - barHeight;

            g2.fillRect(x, y, barWidth - 4, barHeight);
        }

        // Title
        g2.setColor(Color.BLACK);
        g2.drawString("Frequency Histogram", xBase + chartWidth / 3, top - 10);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 300);
    }
}
