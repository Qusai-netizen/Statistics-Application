package StatisCalc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class QuanSambles extends Quan {

    public ArrayList<Integer> samplesInt;

    public QuanSambles(ArrayList<Integer> samplesInt, int classNum) {
        this.samplesInt = new ArrayList<>(samplesInt);
        this.classNum = classNum;

        _class = new ArrayList<>();
        classlower = new ArrayList<>();
        classupper = new ArrayList<>();
        freq = new ArrayList<>();
        classBoundaries = new ArrayList<>();
        classBoundlower = new ArrayList<>();
        classBoundupper = new ArrayList<>();
        midPoint = new ArrayList<>();
        classRelativeF = new ArrayList<>();
        cumulativeFreq = new ArrayList<>();
        ascendingComulativeFreq = new ArrayList<>();
        descendingComulativeFreq = new ArrayList<>();
        classBoundariesLowUp = new ArrayList<>();
    }

    public void makeN() {
        makeClassN();
        makeFreqI();
        makeClassBoundaries();
        makeClassBoundariesLowUp();
        makeMidPoint();
        freqSum = StatisTools.getFreqSum(freq);
        makeRelativeF();
        makeAscendingComulativeFreq();
        cumulativeFreq = StatisTools.getCumulativeFreq(freq);
        makeDescendingComulativeFreq();
    }

    private void makeClassN() {
        _class.clear();
        classlower.clear();
        classupper.clear();

        int current = StatisTools.getLeastSample(samplesInt);
        int classWidth = StatisTools.getClassWidth(samplesInt, classNum);

        for (int i = 0; i < classNum; ++i) {
            int lower = current;
            int upper = current + classWidth - 1;
            classlower.add(lower);
            classupper.add(upper);
            _class.add(lower + " - " + upper);
            current += classWidth;
        }
    }

    private void makeFreqI() {
        freq.clear();

        for (int i = 0; i < classNum; ++i) {
            int count = 0;

            for (int sample : samplesInt) {
                if (sample >= classlower.get(i) && sample <= classupper.get(i)) {
                    count++;
                }
            }

            freq.add(count);
        }
    }

    public ArrayList<Integer> getMode() {
        ArrayList<Integer> modes = new ArrayList<>();
        LinkedHashMap<Integer, Integer> frequencyMap = new LinkedHashMap<>();
        int maxCount = 0;

        for (int num : samplesInt) {
            int count = frequencyMap.getOrDefault(num, 0) + 1;
            frequencyMap.put(num, count);
            maxCount = Math.max(maxCount, count);
        }

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxCount) {
                modes.add(entry.getKey());
            }
        }

        return modes;
    }

    public double getQuartile(int i) {
        if (samplesInt.isEmpty() || i < 1 || i > 3) {
            return 0.0;
        }

        ArrayList<Integer> sorted = new ArrayList<>(samplesInt);
        Collections.sort(sorted);
        double position = (sorted.size() + 1) * i / 4.0;

        if (position <= 1) {
            return sorted.get(0);
        }

        if (position >= sorted.size()) {
            return sorted.get(sorted.size() - 1);
        }

        int lowerIndex = (int) Math.floor(position) - 1;
        double fraction = position - Math.floor(position);
        return sorted.get(lowerIndex) + fraction * (sorted.get(lowerIndex + 1) - sorted.get(lowerIndex));
    }
}