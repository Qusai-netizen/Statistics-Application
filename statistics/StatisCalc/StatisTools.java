package statistics.StatisCalc;

public class StatisTools {

    // Assistance methods

    public static int getFreqSum(int[] freq, int classNum) {
        int sum = 0;
        for (int i = 0; i < classNum; ++i) {
            sum += freq[i];
        }
        return sum;
    }

    public static int[] getCumulativeFreq(int[] freq, int classNum) {
        int[] cumulativeFreq = new int[classNum];
        cumulativeFreq[0] = freq[0];
        for (int i = 1; i < classNum; ++i) {
            cumulativeFreq[i] = cumulativeFreq[i - 1] + freq[i];
        }
        return cumulativeFreq;
    }

    public static int getGreatestSample(int[] samplesInt) {
        int greatest = samplesInt[0];
        for (int i = 1; i < samplesInt.length; i++) {
            if (samplesInt[i] > greatest) {
                greatest = samplesInt[i];
            }
        }
        return greatest;
    }

    public static int getLeastSample(int[] samplesInt) {
        int least = samplesInt[0];
        for (int i = 1; i < samplesInt.length; i++) {
            if (samplesInt[i] < least) {
                least = samplesInt[i];
            }
        }
        return least;
    }

    public static int getGreatestLeastDifference(int[] samplesInt) {
        return getGreatestSample(samplesInt) - getLeastSample(samplesInt);
    }

    public static int getClassWidth(int[] samplesInt, int classNum) {
        return (int) (getGreatestLeastDifference(samplesInt) / classNum) + 1;
    }

    public static int firstEmptyInd(String[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; ++i) {
                if (arr[i] == null || arr[i].isEmpty()) {
                    return i;
                }
            }
        }
        return 0;
    }

}
