package statistics;

import statistics.UI.ChartsPanel;
import statistics.UI.TablesPanel;
import statistics.UI.ui;
import statistics.UIData.ClassFrequencyDataset;
import statistics.UIData.NumericSamplesDataset;
import statistics.UIData.StringSamplesDataset;
import statistics.UIData.Table;

public class mainN {

    public static void main(String[] args) {
        String type = "fc";
        int classNum = 5;

        if (type == "s") {

            String[] samplesS = new String[] { "A", "B", "A", "D", "B" };

            StringSamplesDataset dataset = new StringSamplesDataset(samplesS);

            Table t = new Table(dataset.getMainTable(1), dataset.getMainColumns(1));

            new ui(t.scrollPane);
        }

        // Fill samples
        else if (type == "n") {

            // Sample data
            int[] Samples = new int[] {
                    62, 72, 66, 79, 83, 61, 62, 95, 72, 64, 74, 71, 42, 38, 91, 66, 77, 90, 74, 63, 64, 68, 42
            };

            // Create calculator
            NumericSamplesDataset dataset = new NumericSamplesDataset(Samples, classNum);

            // Generate statistics
            dataset.build();

            // Combine tables data
            Object[][][] tablesData = new Object[2][][];
            tablesData[0] = dataset.getMainTable(1);
            tablesData[1] = dataset.getMainTable(2);

            // Combine tables columns
            String[][] tabesColumns = new String[2][];
            tabesColumns[0] = dataset.getMainColumns(1);
            tabesColumns[1] = dataset.getMainColumns(2);

            TablesPanel tP = new TablesPanel(tablesData, tabesColumns);

            double[] boundaries = new double[dataset.calc.quan.classBoundlower.length + 1];
            for (int i = 0; i < dataset.calc.quan.classBoundlower.length; i++) {
                boundaries[i] = dataset.calc.quan.classBoundlower[i];
            }
            boundaries[boundaries.length
                    - 1] = dataset.calc.quan.classBoundupper[dataset.calc.quan.classBoundupper.length - 1];

            ChartsPanel charts = new ChartsPanel(
                    dataset.calc.quan.classRelativeF,
                    dataset.calc.quan.freq,
                    dataset.calc.quan.midPoint,
                    dataset.calc.quan.classBoundariesLowUp,
                    dataset.calc.quan.ascendingComulativeFreq,
                    boundaries);

            new ui(tP, charts);
        }

        else if (type == "fc") {

            // Sample data
            String[] _class = { "80 - 109", "110 - 139", "140 - 169", "170 - 199", "200 - 229", "230 - 259" };
            int[] freq = { 26, 78, 122, 34, 14, 8 };

            // Create calculator
            ClassFrequencyDataset dataset = new ClassFrequencyDataset(_class, freq);

            // Generate statistics
            dataset.build();

            // Combine tables data
            Object[][][] tablesData = new Object[2][][];
            tablesData[0] = dataset.getMainTable(1);
            tablesData[1] = dataset.getMainTable(2);

            // Combine tables columns
            String[][] tabesColumns = new String[2][];
            tabesColumns[0] = dataset.getMainColumns(1);
            tabesColumns[1] = dataset.getMainColumns(2);

            TablesPanel tP = new TablesPanel(tablesData, tabesColumns);

            double[] boundaries = new double[dataset.calc.quanC.classBoundlower.length + 1];
            for (int i = 0; i < dataset.calc.quanC.classBoundlower.length; i++) {
                boundaries[i] = dataset.calc.quanC.classBoundlower[i];
            }
            boundaries[boundaries.length
                    - 1] = dataset.calc.quanC.classBoundupper[dataset.calc.quanC.classBoundupper.length - 1];

            ChartsPanel charts = new ChartsPanel(
                    dataset.calc.quanC.classRelativeF,
                    dataset.calc.quanC.freq,
                    dataset.calc.quanC.midPoint,
                    dataset.calc.quanC.classBoundariesLowUp,
                    dataset.calc.quanC.ascendingComulativeFreq,
                    boundaries);

            new ui(tP, charts);
        }
    }
}
