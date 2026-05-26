package StatisCalc;

import java.util.ArrayList;

public class QuanWithClassesAndFreq extends Quan {

    public QuanWithClassesAndFreq(ArrayList<String> classes, ArrayList<Integer> freq) {
        this._class = new ArrayList<>(classes);
        this.freq = new ArrayList<>(freq);
        this.classNum = classes.size();

        classlower = new ArrayList<>();
        classupper = new ArrayList<>();
        classBoundaries = new ArrayList<>();
        classBoundlower = new ArrayList<>();
        classBoundupper = new ArrayList<>();
        midPoint = new ArrayList<>();
        classRelativeF = new ArrayList<>();
        lessComulativeF = new ArrayList<>();
        greaterComulativeF = new ArrayList<>();
        cumulativeFreq = new ArrayList<>();
        ascendingComulativeFreq = new ArrayList<>();
        descendingComulativeFreq = new ArrayList<>();
        classBoundariesLowUp = new ArrayList<>();
    }

    private void makeClassN() {
        classlower.clear();
        classupper.clear();

        for (String classText : _class) {
            int separator = classText.indexOf('-');
            if (separator < 0) {
                throw new IllegalArgumentException("Class must use this format: X - Y");
            }

            classlower.add(Integer.parseInt(classText.substring(0, separator).trim()));
            classupper.add(Integer.parseInt(classText.substring(separator + 1).trim()));
        }
    }

    public double getGroupedMedian() {
        return getQuartileC(2);
    }

    public double getGroupedMode() {
        int rank = 0;
        for (int i = 1; i < freq.size(); i++) {
            if (freq.get(i) > freq.get(rank)) {
                rank = i;
            }
        }

        int modalFreq = freq.get(rank);
        int previousFreq = rank == 0 ? 0 : freq.get(rank - 1);
        int nextFreq = rank == freq.size() - 1 ? 0 : freq.get(rank + 1);
        double width = classBoundupper.get(rank) - classBoundlower.get(rank);
        double denominator = (modalFreq - previousFreq) + (modalFreq - nextFreq);

        if (denominator == 0.0) {
            return midPoint.get(rank);
        }

        return classBoundlower.get(rank) + ((modalFreq - previousFreq) / denominator) * width;
    }

    public double getQuartileC(int i) {
        double order = (freqSum * i) / 4.0;
        int rank = 0;

        for (int j = 0; j < cumulativeFreq.size(); ++j) {
            if (order <= cumulativeFreq.get(j)) {
                rank = j;
                break;
            }
        }

        int classFreq = freq.get(rank);
        double width = classBoundupper.get(rank) - classBoundlower.get(rank);
        double previousCumulative = rank == 0 ? 0 : cumulativeFreq.get(rank - 1);
        double lowerBound = classBoundlower.get(rank);

        return classFreq == 0 ? lowerBound : lowerBound + ((order - previousCumulative) / classFreq) * width;
    }

    public void makeN() {
        makeClassN();
        makeClassBoundaries();
        makeClassBoundariesLowUp();
        makeMidPoint();
        freqSum = StatisTools.getFreqSum(freq);
        makeRelativeF();
        makeLessComulativeF();
        makeGreaterComulativeF();
        makeAscendingComulativeFreq();
        cumulativeFreq = StatisTools.getCumulativeFreq(freq);
        makeDescendingComulativeFreq();
    }
}
