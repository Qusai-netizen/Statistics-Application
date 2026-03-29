package statistics.StatisCalc;

public class Quan {

    public int[] classlower,
            classupper,
            freq,
            ascendingComulativeFreq,
            descendingComulativeFreq,
            cumulativeFreq;

    public double[] classRelativeF,
            classBoundariesLowUp,
            midPoint,
            classBoundlower,
            classBoundupper;

    public String[] _class,
            classBoundaries,
            lessComulativeF,
            greaterComulativeF;

    public int freqSum;

    protected int classNum;

    protected void makeClassBoundaries() {
        for (int i = 0; i < classNum; ++i) {
            classBoundlower[i] = (double) classlower[i] - .5;
            classBoundupper[i] = (double) classupper[i] + .5;
            classBoundaries[i] = classBoundlower[i] + " - " + classBoundupper[i];
        }
    }

    protected void makeClassBoundariesLowUp() {
        for (int i = 0; i < classBoundlower.length; ++i) {
            classBoundariesLowUp[i] = classBoundlower[i];
        }
        classBoundariesLowUp[classBoundariesLowUp.length - 1] = classBoundupper[classBoundupper.length - 1];
    }

    protected void makeMidPoint() {
        for (int i = 0; i < classNum; ++i) {
            midPoint[i] = (classBoundlower[i] + classBoundupper[i]) / 2.0;
        }
    }

    protected void makeRelativeF() {
        for (int i = 0; i < classNum; ++i) {
            classRelativeF[i] = (double) freq[i] / freqSum;
        }
    }

    public int getLessThanNInSamplesInt(double n) {
        int counter = 0;
        for (int i = 0; i < classNum; ++i) {
            if (classBoundupper[i] <= n)
                counter += freq[i];
            else
                break;
        }
        return counter;
    }

    public int getGreaterThanNInSamplesInt(double n) {
        int counter = 0;
        for (int i = 0; i < classNum; ++i) {
            if (classBoundlower[i] >= n)
                counter += freq[i];
        }
        return counter;
    }

    protected void makeLessComulativeF() {
        double compare = classBoundlower[0];
        double classbounWidth = classBoundupper[0] - classBoundlower[0];

        for (int i = 0; i < lessComulativeF.length; ++i) {
            if (i < classNum) {
                lessComulativeF[i] = "Less (" + compare + ") = " + getLessThanNInSamplesInt(compare);
                compare += classbounWidth;
            } else {
                lessComulativeF[i] = "Less (end) = " + getLessThanNInSamplesInt(classBoundupper[classNum - 1]);
            }
        }
    }

    protected void makeGreaterComulativeF() {
        double compare = classBoundlower[0];
        double classWidth = classBoundupper[0] - classBoundlower[0];

        for (int i = 0; i < greaterComulativeF.length; ++i) {
            if (i < classNum) {
                greaterComulativeF[i] = "Greater (" + compare + ") = " + getGreaterThanNInSamplesInt(compare);
                compare += classWidth;
            } else {
                // لتجنب ArrayIndexOutOfBounds عند آخر عنصر
                greaterComulativeF[i] = "Greater (end) = " + getGreaterThanNInSamplesInt(classBoundlower[classNum - 1]);
            }
        }
    }

    protected void makeAscendingComulativeFreq() {
        int sum = 0;
        ascendingComulativeFreq[0] = sum;
        for (int i = 1; i <= classNum; ++i) {
            sum += freq[i - 1];
            ascendingComulativeFreq[i] = sum;
        }
    }

    protected void makeDescendingComulativeFreq() {
        int sum = freqSum;
        descendingComulativeFreq[classNum] = sum;
        for (int i = 0; i < classNum; ++i) {
            if (i != 0)
                sum -= freq[i - 1];
            descendingComulativeFreq[i] = sum;

        }
    }

    public double getMean(double[] elements) {
        double sum = 0.0;
        for (double num : elements) {
            sum += num;
        }
        return sum / elements.length;
    }

    public double getMean(int[] elements) {
        double sum = 0.0;
        for (double num : elements) {
            sum += num;
        }
        return (double) sum / elements.length;
    }

    public double getMean(double[] elements, int[] weight) {
        double sum = 0.0, weightSum = 0.0;
        for (int i = 0; i < elements.length; i++) {
            sum += elements[i] * weight[i];
            weightSum += weight[i];
        }
        return sum / weightSum;
    }

    public double getMean(int[] elements, int[] weight) {
        double sum = 0.0, weightSum = 0.0;
        for (int i = 0; i < elements.length; i++) {
            sum += elements[i] * weight[i];
            weightSum += weight[i];
        }
        return (double) sum / weightSum;
    }

    public double getMedian(int[] elements) {
        int n = elements.length;
        java.util.Arrays.sort(elements);
        if (n % 2 == 0) {
            return (elements[n / 2 - 1] + elements[n / 2]) / 2.0;
        } else {
            return elements[n / 2];
        }
    }

    public double getMedian(double[] elements) {
        int n = elements.length;
        java.util.Arrays.sort(elements);
        if (n % 2 == 0) {
            return (elements[n / 2 - 1] + elements[n / 2]) / 2.0;
        } else {
            return elements[n / 2];
        }
    }

}
