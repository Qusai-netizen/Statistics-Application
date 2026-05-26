package UIData;

import java.util.ArrayList;

import StatisCalc.StatisticsCalc;

public abstract class StatisticsDataset {
    public StatisticsCalc calc;

    public abstract void build();

    public abstract ArrayList<ArrayList<Object>> getMainTable(int rank);

    public abstract ArrayList<String> getMainColumns(int rank);
}
