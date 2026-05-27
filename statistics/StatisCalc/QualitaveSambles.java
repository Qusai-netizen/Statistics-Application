package StatisCalc;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class QualitaveSambles {

    public ArrayList<Integer> freq;
    public ArrayList<Integer> ascendingComulativeFreq;
    public ArrayList<Integer> descendingComulativeFreq;
    public ArrayList<String> _class;
    public ArrayList<String> samplesS;
    public int freqSum;

    public QualitaveSambles(ArrayList<String> samplesS) {
        this.samplesS = new ArrayList<>(samplesS);
        this._class = new ArrayList<>();
        this.freq = new ArrayList<>();
        this.ascendingComulativeFreq = new ArrayList<>();
        this.descendingComulativeFreq = new ArrayList<>();

        makeClassS();
    }

    private void makeClassS() {
        LinkedHashSet<String> classes = new LinkedHashSet<>();

        for (String sample : samplesS) {
            if (sample != null && !sample.trim().isEmpty()) {
                classes.add(sample.trim());
            }
        }

        _class.addAll(classes);
    }

    private void makeFreqS() {
        freq.clear();

        for (String classValue : _class) {
            int count = 0;

            for (String sample : samplesS) {
                if (classValue.equals(sample)) {
                    count++;
                }
            }

            freq.add(count);
        }
    }

    private void makeAscendingComulativeFreq() {
        ascendingComulativeFreq.clear();
        int sum = 0;

        for (int value : freq) {
            sum += value;
            ascendingComulativeFreq.add(sum);
        }
    }

    private void makeDescendingComulativeFreq() {
        descendingComulativeFreq.clear();
        int sum = freqSum;

        for (int value : freq) {
            descendingComulativeFreq.add(sum);
            sum -= value;
        }
    }

    public void makeS() {
        makeFreqS();
        freqSum = StatisTools.getFreqSum(freq);
        makeAscendingComulativeFreq();
        makeDescendingComulativeFreq();
    }
}
