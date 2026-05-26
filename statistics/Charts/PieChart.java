package Charts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

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
        PINK(Color.PINK);

        final Color awtColor;

        MyColors(Color awtColor) {
            this.awtColor = awtColor;
        }
    }

    ArrayList<Integer> degrees;
    ArrayList<Double> classRelativeF;

    public PieChart(ArrayList<Double> classRelativeF) {
        this.classRelativeF = new ArrayList<>(classRelativeF);
        this.degrees = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (classRelativeF.isEmpty()) {
            return;
        }

        degrees.clear();
        for (double relativeFrequency : classRelativeF) {
            degrees.add((int) Math.round(relativeFrequency * 360));
        }

        int x1 = 150;
        int y1 = 150;
        int r = 50;
        int degree = 0;
        int xRect = 50;

        g.setColor(Color.black);
        g.drawOval(100, 100, 100, 100);
        g.drawLine(x1, y1, 200, 150);

        ArrayList<MyColors> colors = getColors();
        for (int i = 0; i < degrees.size(); ++i) {
            g.setColor(colors.get(i % colors.size()).awtColor);
            g.fillArc(100, 100, 2 * r, 2 * r, degree, degrees.get(i));
            degree += degrees.get(i);

            g.fillRect(xRect, y1 + 100, 10, 10);
            g.setColor(Color.black);
            g.drawString("Class " + (i + 1), xRect + 17, y1 + 110);
            xRect += 70;
        }

        g.setColor(Color.black);
        g.drawString("Pie Chart", 300, 100);
    }

    private ArrayList<MyColors> getColors() {
        ArrayList<MyColors> colors = new ArrayList<>();
        colors.add(MyColors.BLUE);
        colors.add(MyColors.WHITE);
        colors.add(MyColors.CYAN);
        colors.add(MyColors.GRAY);
        colors.add(MyColors.YELLOW);
        colors.add(MyColors.ORANGE);
        colors.add(MyColors.MAGENTA);
        colors.add(MyColors.GREEN);
        colors.add(MyColors.RED);
        colors.add(MyColors.PINK);
        return colors;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }
}
