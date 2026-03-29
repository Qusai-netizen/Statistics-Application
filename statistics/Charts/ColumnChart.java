package statistics.Charts;

import javax.swing.*;

import java.awt.*;

public class ColumnChart extends JPanel {

    int[] column,
            xPoints;

    int startPointRank = 1;

    double[] row;

    public String title;

    ColumnChart(int[] column, double[] row) {
        this.column = column;
        this.row = row;
        xPoints = new int[row.length];
    }

    ColumnChart(int[] column, double[] row, int startPointRank) {
        this.column = column;
        this.row = row;

        if (startPointRank < 1 || startPointRank >= column.length) {
            startPointRank = 1;
        }

        xPoints = new int[column.length - startPointRank];

        this.startPointRank = startPointRank;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int left = 30;
        int right = 40;
        int top = 34;
        int bottom = 80;

        final int yStart = height - bottom, xEnd = width - right;

        g.setColor(Color.gray);
        g.drawLine(left, yStart, width - right, yStart);
        g.drawLine(left, yStart, left, top);

        int chartWidth = width - left - right;
        // int chartHeight = height - top - bottom;

        int yEnd = 0;
        // Draw x axis numbers
        {
            int xWidth = (int) (chartWidth / (double) (row.length + 1)), i = 0;

            g.drawString("0 -", left - 13, yStart + 3);
            for (double x = row[i]; i < row.length; ++i) {
                x = row[i];
                if (i > 0) {
                    xWidth += (int) (chartWidth / (double) (row.length + 1));
                }

                if (i == 0) {
                    xPoints[i] = left + xWidth;
                    g.drawString(String.valueOf(x), xPoints[i], yStart + 20);
                } else {
                    xPoints[i] = left + xWidth;
                    g.drawString(String.valueOf(x), xPoints[i], yStart + 20);
                }
            }
        }

        for (int i = 0; i < column.length; ++i) {
            yEnd = Math.max(yEnd, column[i]);
        }
        yEnd += 2;

        while (yEnd % 4 != 0) {
            ++yEnd;
        }

        // Draw y axis numbers
        {
            int yDev = yEnd / 4, yDe = yStart / 5;
            int xDe = 13, columnLen, columnLenStanderd = 1;
            for (int y = yDev; y <= yEnd; y += yDev) {
                columnLen = String.valueOf(y).length();

                if (columnLen > columnLenStanderd) {
                    xDe += 6;
                    ++columnLenStanderd;
                }

                g.drawString(String.valueOf(y) + " -", left - xDe, yStart - yDe);
                yDe += yStart / 5;
            }
        }

        // Draw the polygon
        {

            int x1 = (int) (left + (startPointRank - 1) * (chartWidth / (double) (row.length + 1))), x2, y1 = yStart,
                    y2,

                    yDev = yEnd / 4, yDe = yStart / 5;
            ;
            for (int i = 0 + startPointRank - 1; i < column.length; ++i) {
                x2 = xPoints[i] + String.valueOf(xPoints[i]).length() * 4;
                y2 = yStart - ((int) (yDe / (double) yDev) * column[i]);
                g.drawLine(x1, y1, x2, y2);
                g.fillOval(x1 - 3, y1 - 3, 5, 5);
                x1 = x2;
                y1 = y2;
            }
            g.fillOval(x1 - 3, y1 - 3, 5, 5);
        }

        // Title
        g.setColor(Color.BLACK);
        g.drawString(title, left + (xEnd - left) / 2, top);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }
}
