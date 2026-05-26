package StatisCalc;

import java.util.ArrayList;
import java.util.Collections;

public class StatisTools {

    public static int getFreqSum(ArrayList<Integer> freq) {
        int sum = 0;
        for (int value : freq) {
            sum += value;
        }
        return sum;
    }

    public static ArrayList<Integer> getCumulativeFreq(ArrayList<Integer> freq) {
        ArrayList<Integer> cumulativeFreq = new ArrayList<>();
        int sum = 0;

        for (int value : freq) {
            sum += value;
            cumulativeFreq.add(sum);
        }

        return cumulativeFreq;
    }

    public static int getGreatestSample(ArrayList<Integer> samplesInt) {
        return Collections.max(samplesInt);
    }

    public static int getLeastSample(ArrayList<Integer> samplesInt) {
        return Collections.min(samplesInt);
    }

    public static int getGreatestLeastDifference(ArrayList<Integer> samplesInt) {
        return getGreatestSample(samplesInt) - getLeastSample(samplesInt);
    }

    public static int getClassWidth(ArrayList<Integer> samplesInt, int classNum) {
        return (int) Math.ceil((getGreatestLeastDifference(samplesInt) + 1) / (double) classNum);
    }

    public static int firstEmptyInd(ArrayList<String> values) {
        for (int i = 0; i < values.size(); ++i) {
            String value = values.get(i);
            if (value == null || value.isEmpty()) {
                return i;
            }
        }
        return values.size();
    }
}
