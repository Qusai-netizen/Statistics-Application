package statistics.Charts;

import javax.swing.*;
import java.awt.*;

public class FreqPolygon extends JPanel {

    ColumnChart columnChart;

    public FreqPolygon(int[] freq, double[] midPoint) {
        setLayout(new BorderLayout());

        columnChart = new ColumnChart(freq, midPoint);
        columnChart.title = "Frequency Polygon";

        add(columnChart, BorderLayout.CENTER);
    }
}
