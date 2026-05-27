package UIData;

import java.util.ArrayList;

import StatisCalc.StatisticsCalc;

public class NumericSamplesDataset extends StatisticsDataset {

    private ArrayList<Integer> samplesInt;
    private int classNum;

    public NumericSamplesDataset(ArrayList<Integer> samplesInt, int classNum) {
        this.classNum = classNum;
        this.samplesInt = new ArrayList<>(samplesInt);
        this.calc = new StatisticsCalc(samplesInt, classNum);
    }

    @Override
    public void build() {
        calc.quan.samplesInt = new ArrayList<>(samplesInt);
        calc.quan.makeN();
    }

    @Override
    public ArrayList<ArrayList<Object>> getMainTable(int rank) {
        if (rank == 1) {
            ArrayList<ArrayList<Object>> data = new ArrayList<>();
            final int rows = classNum + 1;

            for (int i = 0; i < rows; i++) {
                ArrayList<Object> row = new ArrayList<>();
                row.add(get(calc.quan._class, i));
                row.add(get(calc.quan.freq, i));
                row.add(get(calc.quan.classBoundaries, i));
                row.add(get(calc.quan.midPoint, i));
                row.add(get(calc.quan.classRelativeF, i));
                row.add(get(calc.quan.ascendingComulativeFreq, i));
                row.add(get(calc.quan.descendingComulativeFreq, i));
                data.add(row);
            }

            return data;
        }

        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        ArrayList<Object> row = new ArrayList<>();
        row.add(calc.quan.getMean(calc.quan.samplesInt));
        row.add(calc.quan.getMedian(calc.quan.samplesInt));
        row.add(calc.quan.getMode());
        row.add(calc.quan.getQuartile(1));
        row.add(calc.quan.getQuartile(2));
        row.add(calc.quan.getQuartile(3));
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

        columns.add("Mean");
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
