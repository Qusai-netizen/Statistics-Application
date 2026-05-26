package Charts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

public class ColumnChart extends JPanel {

    ArrayList<Integer> column;
    ArrayList<Integer> xPoints;
    ArrayList<Double> row;
    int startPointRank = 1;
    public String title;

    ColumnChart(ArrayList<Integer> column, ArrayList<Double> row) {
        this.column = new ArrayList<>(column);
        this.row = new ArrayList<>(row);
        xPoints = new ArrayList<>(Collections.nCopies(row.size(), 0));
    }

    ColumnChart(ArrayList<Integer> column, ArrayList<Double> row, int startPointRank) {
        this.column = new ArrayList<>(column);
        this.row = new ArrayList<>(row);

        if (startPointRank < 1 || startPointRank >= column.size()) {
            startPointRank = 1;
        }

        xPoints = new ArrayList<>(Collections.nCopies(Math.max(column.size() - startPointRank, 0), 0));
        this.startPointRank = startPointRank;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (column.isEmpty() || row.isEmpty()) {
            return;
        }

        int width = getWidth();
        int height = getHeight();
        int left = 30;
        int right = 40;
        int top = 34;
        int bottom = 80;
        int yStart = height - bottom;
        int xEnd = width - right;

        g.setColor(Color.gray);
        g.drawLine(left, yStart, width - right, yStart);
        g.drawLine(left, yStart, left, top);

        int chartWidth = width - left - right;
        int xWidth = (int) (chartWidth / (double) (row.size() + 1));

        g.drawString("0 -", left - 13, yStart + 3);
        for (int i = 0; i < row.size(); ++i) {
            int x = left + xWidth * (i + 1);
            xPoints.set(i, x);
            g.drawString(String.valueOf(row.get(i)), x, yStart + 20);
        }

        int yEnd = Collections.max(column) + 2;
        while (yEnd % 4 != 0) {
            ++yEnd;
        }

        int yDev = yEnd / 4;
        int yDe = yStart / 5;
        int xDe = 13;
        int columnLenStanderd = 1;
        for (int y = yDev; y <= yEnd; y += yDev) {
            int columnLen = String.valueOf(y).length();

            if (columnLen > columnLenStanderd) {
                xDe += 6;
                ++columnLenStanderd;
            }

            g.drawString(y + " -", left - xDe, yStart - yDe);
            yDe += yStart / 5;
        }

        int x1 = (int) (left + (startPointRank - 1) * (chartWidth / (double) (row.size() + 1)));
        int y1 = yStart;
        int yScale = yStart / 5;

        for (int i = startPointRank - 1; i < column.size() && i < xPoints.size(); ++i) {
            int x2 = xPoints.get(i) + String.valueOf(xPoints.get(i)).length() * 4;
            int y2 = yStart - ((int) (yScale / (double) yDev) * column.get(i));
            g.drawLine(x1, y1, x2, y2);
            g.fillOval(x1 - 3, y1 - 3, 5, 5);
            x1 = x2;
            y1 = y2;
        }
        g.fillOval(x1 - 3, y1 - 3, 5, 5);

        g.setColor(Color.BLACK);
        g.drawString(title, left + (xEnd - left) / 2, top);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }
}
