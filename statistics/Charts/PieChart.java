package statistics.Charts;

import javax.swing.*;
import java.awt.*;

public class PieChart extends JPanel {

    enum MyColors {
        BLUE(Color.BLUE),
        WHITE(Color.WHITE),
        CYAN(Color.CYAN),
        GRAY(Color.GRAY),
        YELLOW(Color.YELLOW),
        ORANGE(Color.ORANGE),
        MAGENTA(Color.MAGENTA),
        GREEN(Color.GREEN),
        RED(Color.RED),
        PINK(Color.PINK),
        ;

        final Color awtColor;

        MyColors(Color awtColor) {
            this.awtColor = awtColor;
        }
    }

    int[] degrees;
    double[] classRelativeF;

    public PieChart(double[] classRelativeF) {
        this.classRelativeF = classRelativeF;
        degrees = new int[classRelativeF.length];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.drawOval(100, 100, 100, 100);

        for (int i = 0; i < classRelativeF.length; ++i) {
            degrees[i] = (int) (classRelativeF[i] * 360);
            System.out.println(degrees[i]);
        }

        final int x1 = 150, y1 = 150, r = 50;

        g.drawLine(x1, y1, 200, 150);

        int degree = 0, xRect = 50;

        for (int i = 0; i < classRelativeF.length; ++i) {

            g.setColor(MyColors.values()[i].awtColor);
            g.fillArc(100, 100, 2 * r, 2 * r, degree, degrees[i]);
            degree += degrees[i];

            g.fillRect(xRect, y1 + 100, 10, 10);
            g.setColor(Color.black);
            g.drawString("Class " + (i + 1), xRect + 17, y1 + 110);
            xRect += 70;
            // int x2 = (int)(x1 + r * Math.cos(Math.toRadians(degree))),
            // y2 = (int)(y1 - r * Math.sin(Math.toRadians(degree)));

            // g.drawLine(x1, y1, x2, y2);
        }
        g.setColor(Color.black);
        g.drawString("Pie Chart", 300, 100);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

}
