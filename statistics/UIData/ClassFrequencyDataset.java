package statistics.UIData;

import statistics.StatisCalc.StatisticsCalc;

public class ClassFrequencyDataset extends StatisticsDataset {

    private int[] freq;
    private int classNum;
    public StatisticsCalc calc;

    public ClassFrequencyDataset(String[] classes, int[] freq) {
        this.classNum = classes.length;
        calc = new StatisticsCalc(classes, freq);
        this.freq = freq;
    }

    @Override
    public void build() {
        calc.quanC.makeN(freq);
    }

    @Override
    public Object[][] getMainTable(int rank) {
        if (rank == 1) {
            // Table data
            Object[][] dataT1 = new Object[classNum][9];

            for (int i = 0; i < classNum; i++) {
                dataT1[i][0] = calc.quanC._class[i];
                dataT1[i][1] = calc.quanC.freq[i];
                dataT1[i][2] = calc.quanC.classBoundaries[i];
                dataT1[i][3] = calc.quanC.midPoint[i];
                dataT1[i][4] = calc.quanC.classRelativeF[i];
                dataT1[i][5] = calc.quanC.lessComulativeF[i];
                dataT1[i][6] = calc.quanC.greaterComulativeF[i];
                dataT1[i][7] = calc.quanC.ascendingComulativeFreq[i];
                dataT1[i][8] = calc.quanC.descendingComulativeFreq[i];
            }
            return dataT1;
        }

        else {
            // Table data
            Object[][] dataT2 = new Object[1][7];

            // dataT2[0][0] = calc.getMean(calc.samplesInt);
            // dataT2[0][1] = calc.getMean(calc.midPoint, calc.quanC.freq);
            dataT2[0][2] = calc.quanC.getQuartileC(2);
            // dataT2[0][3] = calc.getMode(calc.samplesInt);
            dataT2[0][4] = calc.quanC.getQuartileC(1);
            dataT2[0][5] = calc.quanC.getQuartileC(2);
            dataT2[0][6] = calc.quanC.getQuartileC(3);
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
