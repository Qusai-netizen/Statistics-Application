package app;

import java.util.ArrayList;

import UI.ChartsPanel;
import UI.StatisPanel;
import UI.TablesPanel;
import UI.ui;
import UIData.ClassFrequencyDataset;
import UIData.NumericSamplesDataset;
import UIData.StringSamplesDataset;
import UIData.Table;
import storage.statisDb;
import storage.statisDb.Experiment;

public class mainN {

    public static statisDb db;

    public static void main(String[] args) {
        new ui();
    }

    public static StatisPanel buildStatisPanel(Experiment experiment) {
        return buildStatisPanel(experiment, null);
    }

    public static StatisPanel buildStatisPanel(Experiment experiment, ui mainFrame) {
        if (experiment == null) {
            throw new IllegalArgumentException("Experiment cannot be null.");
        }

        if (experiment.type == Experiment.enType.QUAL) {
            StringSamplesDataset dataset = new StringSamplesDataset(experiment.samplesS, experiment.isOrdinal);
            dataset.build();

            Table table = new Table(dataset.getMainTable(1), dataset.getMainColumns(1));
            return new StatisPanel(table.scrollPane, mainFrame);
        }

        if (experiment.type == Experiment.enType.QUAN) {
            NumericSamplesDataset dataset = new NumericSamplesDataset(experiment.samplesInt, experiment.getClassesNum());
            dataset.build();

            ArrayList<ArrayList<ArrayList<Object>>> tablesData = new ArrayList<>();
            tablesData.add(dataset.getMainTable(1));
            tablesData.add(dataset.getMainTable(2));

            ArrayList<ArrayList<String>> tablesColumns = new ArrayList<>();
            tablesColumns.add(dataset.getMainColumns(1));
            tablesColumns.add(dataset.getMainColumns(2));

            TablesPanel tablesPanel = new TablesPanel(tablesData, tablesColumns);
            ChartsPanel chartsPanel = new ChartsPanel(
                    dataset.calc.quan.classRelativeF,
                    dataset.calc.quan.freq,
                    dataset.calc.quan.midPoint,
                    dataset.calc.quan.classBoundariesLowUp,
                    dataset.calc.quan.ascendingComulativeFreq,
                    buildBoundaries(dataset.calc.quan.classBoundlower, dataset.calc.quan.classBoundupper));

            return new StatisPanel(tablesPanel, chartsPanel, mainFrame);
        }

        ClassFrequencyDataset dataset = new ClassFrequencyDataset(experiment.classes, experiment.freq);
        dataset.build();

        ArrayList<ArrayList<ArrayList<Object>>> tablesData = new ArrayList<>();
        tablesData.add(dataset.getMainTable(1));
        tablesData.add(dataset.getMainTable(2));

        ArrayList<ArrayList<String>> tablesColumns = new ArrayList<>();
        tablesColumns.add(dataset.getMainColumns(1));
        tablesColumns.add(dataset.getMainColumns(2));

        TablesPanel tablesPanel = new TablesPanel(tablesData, tablesColumns);
        ChartsPanel chartsPanel = new ChartsPanel(
                dataset.calc.quanC.classRelativeF,
                dataset.calc.quanC.freq,
                dataset.calc.quanC.midPoint,
                dataset.calc.quanC.classBoundariesLowUp,
                dataset.calc.quanC.ascendingComulativeFreq,
                buildBoundaries(dataset.calc.quanC.classBoundlower, dataset.calc.quanC.classBoundupper));

        return new StatisPanel(tablesPanel, chartsPanel, mainFrame);
    }

    private static ArrayList<Double> buildBoundaries(ArrayList<Double> lowerBounds, ArrayList<Double> upperBounds) {
        ArrayList<Double> boundaries = new ArrayList<>(lowerBounds);

        if (!upperBounds.isEmpty()) {
            boundaries.add(upperBounds.get(upperBounds.size() - 1));
        }

        return boundaries;
    }
}
