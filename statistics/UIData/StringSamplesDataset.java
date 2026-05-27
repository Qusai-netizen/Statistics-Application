package UIData;

import java.util.ArrayList;

import StatisCalc.StatisticsCalc;

public class StringSamplesDataset extends StatisticsDataset {

    public StringSamplesDataset(ArrayList<String> samples) {
        this.calc = new StatisticsCalc(samples);
    }

    @Override
    public void build() {
        calc.qual.makeS();
    }

    @Override
    public ArrayList<ArrayList<Object>> getMainTable(int rank) {
        ArrayList<ArrayList<Object>> data = new ArrayList<>();

        for (int i = 0; i < calc.qual._class.size(); i++) {
            ArrayList<Object> row = new ArrayList<>();
            row.add(calc.qual._class.get(i));
            row.add(calc.qual.freq.get(i));
            data.add(row);
        }

        return data;
    }

    @Override
    public ArrayList<String> getMainColumns(int rank) {
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Class");
        columns.add("Frequency");
        columns.add("Ascending Comulative Frequency");
        columns.add("Descending Comulative Frequency");
        return columns;
    }
}
