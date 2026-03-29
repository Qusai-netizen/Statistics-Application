package statistics.StatisCalc;

public class QuanSambles extends Quan {

    public int[] samplesInt;

    private int sampNum;

    public QuanSambles(int[] samplesInt, int classNum) {
        this.sampNum = samplesInt.length;
        this.samplesInt = samplesInt;
        this.classNum = classNum;

        _class = new String[classNum];
        classlower = new int[classNum];
        classupper = new int[classNum];
        freq = new int[classNum];
        classBoundaries = new String[classNum];
        classBoundlower = new double[classNum];
        classBoundupper = new double[classNum];
        midPoint = new double[classNum];
        classRelativeF = new double[classNum];
        lessComulativeF = new String[classNum + 1];
        greaterComulativeF = new String[classNum + 1];
        cumulativeFreq = new int[classNum];
        ascendingComulativeFreq = new int[classNum + 1];
        descendingComulativeFreq = new int[classNum + 1];
        classBoundariesLowUp = new double[classNum + 1];
    }

    private void makeClassN() {
        int current = StatisTools.getLeastSample(samplesInt),
                classWidth = StatisTools.getClassWidth(samplesInt, classNum);

        for (int i = 0; i < classNum; ++i) {
            classlower[i] = current;
            classupper[i] = current + classWidth - 1;
            _class[i] = classlower[i] + " - " + classupper[i];
            current += classWidth;
        }
    }

    private void makeFreqI() {
        for (int i = 0; i < classNum; ++i) {
            for (int j = 0; j < sampNum; ++j) {
                if (samplesInt[j] >= classlower[i] && samplesInt[j] <= classupper[i]) {
                    ++freq[i];
                }
            }
        }
    }

    public int getMode(int[] elements) {
        java.util.Map<Integer, Integer> frequencyMap = new java.util.HashMap<>();
        for (int num : elements) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        int mode = elements[0];
        int maxCount = 0;
        for (java.util.Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mode = entry.getKey();
            }
        }
        return mode;
    }

    public double getMode(double[] elements) {
        java.util.Map<Double, Integer> frequencyMap = new java.util.HashMap<>();
        for (double num : elements) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        double mode = elements[0];
        int maxCount = 0;
        for (java.util.Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mode = entry.getKey();
            }
        }
        return mode;
    }

    public double getQuartile(int i) {
        return samplesInt[(int) ((sampNum + 1) * i / 4) - 1];
    }

    // Main method

    public void makeN() {
        makeClassN();
        makeFreqI();
        makeClassBoundaries();
        makeClassBoundariesLowUp();
        makeMidPoint();
        freqSum = StatisTools.getFreqSum(freq, classNum);
        makeRelativeF();
        makeLessComulativeF();
        makeGreaterComulativeF();
        makeAscendingComulativeFreq();
        cumulativeFreq = StatisTools.getCumulativeFreq(freq, classNum);
        makeDescendingComulativeFreq();
    }
}

