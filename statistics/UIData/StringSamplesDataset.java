package statistics.UIData;

import statistics.StatisCalc.StatisticsCalc;

public class StringSamplesDataset extends StatisticsDataset {
    StatisticsCalc calc;

    public StringSamplesDataset(String[] samples) {
        this.calc = new StatisticsCalc(samples);
    }

    @Override
    public void build() {
        calc.qual.makeS();
    }

    @Override
    public Object[][] getMainTable(int rank) {
        int classNum = calc.qual._class.length;
        Object[][] data = new Object[classNum][4];

        for (int i = 0; i < classNum; i++) {
            data[i][0] = calc.qual._class[i];
            data[i][1] = calc.qual.freq[i];
        }

        return data;
    }

    @Override
    public String[] getMainColumns(int rank) {
        return new String[] {
                "class",
                "Frequency",
                "Ascending Comulative Frequency",
                "Descending Comulative Frequency"
        };
    }

}
