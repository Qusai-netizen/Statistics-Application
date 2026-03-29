package statistics.Charts;

import javax.swing.*;
import java.awt.*;

public class Ogive extends JPanel {

    ColumnChart columnChart;

    public Ogive(int[] cumulativeFreq, double[] boundaries) {
        setLayout(new BorderLayout());

        columnChart = new ColumnChart(cumulativeFreq, boundaries);
        columnChart.title = "Ogive";

        add(columnChart, BorderLayout.CENTER);
    }
}
