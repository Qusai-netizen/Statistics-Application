package statistics.UIData;

import statistics.StatisCalc.StatisticsCalc;

public abstract class StatisticsDataset {
    public StatisticsCalc calc;

    public abstract void build();

    public abstract Object[][] getMainTable(int rank);

    public abstract String[] getMainColumns(int rank);
}
