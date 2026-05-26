package Charts;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Ogive extends JPanel {

    ColumnChart columnChart;

    public Ogive(ArrayList<Integer> cumulativeFreq, ArrayList<Double> boundaries) {
        setLayout(new BorderLayout());

        columnChart = new ColumnChart(cumulativeFreq, boundaries);
        columnChart.title = "Ogive";

        add(columnChart, BorderLayout.CENTER);
    }
}
