package UIData;

import java.util.ArrayList;

import StatisCalc.StatisticsCalc;

public class StringSamplesDataset extends StatisticsDataset {

    boolean isOrdinal;

    public StringSamplesDataset(ArrayList<String> samples, boolean isOrdinal) {
        this.isOrdinal = isOrdinal;
        this.calc = new StatisticsCalc(samples);
    }

    @Override
    public void build() {
        calc.qual.makeS(isOrdinal);
    }

    @Override
    public ArrayList<ArrayList<Object>> getMainTable(int rank) {
        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        final int rows = calc.qual._class.size() + 1;

        for (int i = 0; i < rows; i++) {
            ArrayList<Object> row = new ArrayList<>();
            row.add(get(calc.qual._class, i));
            row.add(get(calc.qual.freq, i));

            if (isOrdinal) {
            row.add(get(calc.qual.ascendingComulativeFreq, i));
            row.add(get(calc.qual.descendingComulativeFreq, i));
            }

            data.add(row);
        }

        return data;
    }

    @Override
    public ArrayList<String> getMainColumns(int rank) {
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Class");
        columns.add("Frequency");

        // Ordinal Data
        if (isOrdinal) {
            columns.add("Ascending Comulative Frequency");
            columns.add("Descending Comulative Frequency");
        }

        return columns;
    }

    private Object get(ArrayList<?> values, int index) {
        return index < values.size() ? values.get(index) : "";
    }
}
