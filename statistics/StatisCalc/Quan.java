package StatisCalc;

import java.util.ArrayList;
import java.util.Collections;

public class Quan {

    public ArrayList<Integer> classlower;
    public ArrayList<Integer> classupper;
    public ArrayList<Integer> freq;
    public ArrayList<Integer> cumulativeFreq;
    public ArrayList<Integer> ascendingComulativeFreq;
    public ArrayList<Integer> descendingComulativeFreq;

    public ArrayList<Double> classRelativeF;
    public ArrayList<Double> classBoundariesLowUp;
    public ArrayList<Double> midPoint;
    public ArrayList<Double> classBoundlower;
    public ArrayList<Double> classBoundupper;

    public ArrayList<String> _class;
    public ArrayList<String> classBoundaries;

    public int freqSum;
    protected int classNum;

    protected void makeClassBoundaries() {
        classBoundlower.clear();
        classBoundupper.clear();
        classBoundaries.clear();

        for (int i = 0; i < classNum; ++i) {
            double lower = classlower.get(i) - 0.5;
            double upper = classupper.get(i) + 0.5;
            classBoundlower.add(lower);
            classBoundupper.add(upper);
            classBoundaries.add(lower + " - " + upper);
        }
    }

    protected void makeClassBoundariesLowUp() {
        classBoundariesLowUp.clear();

        for (double lower : classBoundlower) {
            classBoundariesLowUp.add(lower);
        }

        if (!classBoundupper.isEmpty()) {
            classBoundariesLowUp.add(classBoundupper.get(classBoundupper.size() - 1));
        }
    }

    protected void makeMidPoint() {
        midPoint.clear();

        for (int i = 0; i < classNum; ++i) {
            midPoint.add((classBoundlower.get(i) + classBoundupper.get(i)) / 2.0);
        }
    }

    protected void makeRelativeF() {
        classRelativeF.clear();

        for (int value : freq) {
            classRelativeF.add(freqSum == 0 ? 0.0 : (double) value / freqSum);
        }
    }

    public int getLessThanNInSamplesInt(double n) {
        int counter = 0;

        for (int i = 0; i < classNum; ++i) {
            if (classBoundupper.get(i) <= n) {
                counter += freq.get(i);
            }
        }

        return counter;
    }

    public int getGreaterThanNInSamplesInt(double n) {
        int counter = 0;

        for (int i = 0; i < classNum; ++i) {
            if (classBoundlower.get(i) >= n) {
                counter += freq.get(i);
            }
        }

        return counter;
    }

    protected void makeAscendingComulativeFreq() {
        ascendingComulativeFreq.clear();
        ascendingComulativeFreq.add(0);

        int sum = 0;
        for (int value : freq) {
            sum += value;
            ascendingComulativeFreq.add(sum);
        }
    }

    protected void makeDescendingComulativeFreq() {
        descendingComulativeFreq.clear();

        int sum = freqSum;
        descendingComulativeFreq.add(sum);
        for (int value : freq) {
            sum -= value;
            descendingComulativeFreq.add(sum);
        }
    }

    public double getMean(ArrayList<Integer> elements) {
        if (elements.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (int num : elements) {
            sum += num;
        }
        return sum / elements.size();
    }

    public double getWeightedMean(ArrayList<Double> elements, ArrayList<Integer> weight) {
        double sum = 0.0;
        double weightSum = 0.0;

        for (int i = 0; i < elements.size() && i < weight.size(); i++) {
            sum += elements.get(i) * weight.get(i);
            weightSum += weight.get(i);
        }

        return weightSum == 0.0 ? 0.0 : sum / weightSum;
    }

    public double getMedian(ArrayList<Integer> elements) {
        if (elements.isEmpty()) {
            return 0.0;
        }

        ArrayList<Integer> sorted = new ArrayList<>(elements);
        Collections.sort(sorted);
        int n = sorted.size();

        if (n % 2 == 0) {
            return (sorted.get(n / 2 - 1) + sorted.get(n / 2)) / 2.0;
        }

        return sorted.get(n / 2);
    }
}