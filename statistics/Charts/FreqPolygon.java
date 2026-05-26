package Charts;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class FreqPolygon extends JPanel {

    ColumnChart columnChart;

    public FreqPolygon(ArrayList<Integer> freq, ArrayList<Double> midPoint) {
        setLayout(new BorderLayout());

        columnChart = new ColumnChart(freq, midPoint);
        columnChart.title = "Frequency Polygon";

        add(columnChart, BorderLayout.CENTER);
    }
}
