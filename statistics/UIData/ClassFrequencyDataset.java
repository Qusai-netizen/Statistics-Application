package UIData;

import java.util.ArrayList;

import StatisCalc.StatisticsCalc;

public class ClassFrequencyDataset extends StatisticsDataset {

    private ArrayList<Integer> freq;
    private int classNum;

    public ClassFrequencyDataset(ArrayList<String> classes, ArrayList<Integer> freq) {
        this.classNum = classes.size();
        this.calc = new StatisticsCalc(classes, freq);
        this.freq = new ArrayList<>(freq);
    }

    @Override
    public void build() {
        calc.quanC.makeN();
    }

    @Override
    public ArrayList<ArrayList<Object>> getMainTable(int rank) {
        if (rank == 1) {
            ArrayList<ArrayList<Object>> data = new ArrayList<>();
            final int rows = classNum + 1;

            for (int i = 0; i < rows; i++) {
                ArrayList<Object> row = new ArrayList<>();
                row.add(get(calc.quanC._class, i));
                row.add(get(calc.quanC.freq, i));
                row.add(get(calc.quanC.classBoundaries, i));
                row.add(get(calc.quanC.midPoint, i));
                row.add(get(calc.quanC.classRelativeF, i));
                row.add(get(calc.quanC.ascendingComulativeFreq, i));
                row.add(get(calc.quanC.descendingComulativeFreq, i));
                data.add(row);
            }

            return data;
        }

        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        ArrayList<Object> row = new ArrayList<>();
        row.add(calc.quanC.getWeightedMean(calc.quanC.midPoint, freq));
        row.add(calc.quanC.getGroupedMedian());
        row.add(calc.quanC.getGroupedMode());
        row.add(calc.quanC.getQuartileC(1));
        row.add(calc.quanC.getQuartileC(2));
        row.add(calc.quanC.getQuartileC(3));
        data.add(row);
        return data;
    }

    @Override
    public ArrayList<String> getMainColumns(int rank) {
        ArrayList<String> columns = new ArrayList<>();

        if (rank == 1) {
            columns.add("Class");
            columns.add("Frequency");
            columns.add("Class Boundaries");
            columns.add("Mid Point");
            columns.add("Relative Frequency");
            columns.add("Cum. Frequency (Ascending)");
            columns.add("Cum. Frequency (Descending)");
            return columns;
        }

        columns.add("Weighted Mean");
        columns.add("Median");
        columns.add("Mode");
        columns.add("Quartile 1");
        columns.add("Quartile 2");
        columns.add("Quartile 3");
        return columns;
    }

    private Object get(ArrayList<?> values, int index) {
        return index < values.size() ? values.get(index) : "";
    }
}
