package statistics.UIData;

import statistics.StatisCalc.StatisticsCalc;

public class NumericSamplesDataset extends StatisticsDataset {

    private int[] samplesInt;
    private int classNum;

    public NumericSamplesDataset(int[] samplesInt, int classNum) {
        this.classNum = classNum;
        this.samplesInt = samplesInt;
        this.calc = new StatisticsCalc(samplesInt, classNum);
    }

    @Override
    public void build() {
        calc.quan.samplesInt = samplesInt;
        calc.quan.makeN();
    }

    @Override
    public Object[][] getMainTable(int rank) {

        if (rank == 1) {
            // Table data
            Object[][] dataT1 = new Object[classNum][9];

            for (int i = 0; i < classNum; i++) {
                dataT1[i][0] = calc.quan._class[i];
                dataT1[i][1] = calc.quan.freq[i];
                dataT1[i][2] = calc.quan.classBoundaries[i];
                dataT1[i][3] = calc.quan.midPoint[i];
                dataT1[i][4] = calc.quan.classRelativeF[i];
                dataT1[i][5] = calc.quan.lessComulativeF[i];
                dataT1[i][6] = calc.quan.greaterComulativeF[i];
                dataT1[i][7] = calc.quan.ascendingComulativeFreq[i];
                dataT1[i][8] = calc.quan.descendingComulativeFreq[i];
            }
            return dataT1;
        }

        else {
            // Table data
            Object[][] dataT2 = new Object[1][7];

            dataT2[0][0] = calc.quan.getMean(calc.quan.samplesInt);
            dataT2[0][1] = calc.quan.getMean(calc.quan.midPoint, calc.quan.freq);
            dataT2[0][2] = calc.quan.getMedian(calc.quan.samplesInt);
            dataT2[0][3] = calc.quan.getMode(calc.quan.samplesInt);
            dataT2[0][4] = calc.quan.getQuartile(1);
            dataT2[0][5] = calc.quan.getQuartile(2);
            dataT2[0][6] = calc.quan.getQuartile(3);
            return dataT2;
        }
    }

    @Override
    public String[] getMainColumns(int rank) {
        if (rank == 1) {
            return new String[] {
                    "Class",
                    "Frequency",
                    "Class Boundaries",
                    "Mid Point",
                    "Relative Frequency",
                    "Less Comulative F",
                    "Greater Comulative F",
                    "Ascending Comulative Frequency",
                    "Descending Comulative Frequency"
            };
        }

        else {
            return new String[] {
                    "Mean",
                    "Weighted Mean",
                    "Median",
                    "Mode",
                    "Quartile 1",
                    "Quartile 2",
                    "Quartile 3"
            };
        }
    }
}
