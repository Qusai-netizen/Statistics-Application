package StatisCalc;

import java.util.ArrayList;

public class StatisticsCalc {

    public QualitaveSambles qual;
    public QuanSambles quan;
    public QuanWithClassesAndFreq quanC;

    public StatisticsCalc(ArrayList<String> samplesS) {
        qual = new QualitaveSambles(samplesS);
    }

    public StatisticsCalc(ArrayList<Integer> samplesInt, int classNum) {
        quan = new QuanSambles(samplesInt, classNum);
    }

    public StatisticsCalc(ArrayList<String> classes, ArrayList<Integer> freq) {
        quanC = new QuanWithClassesAndFreq(classes, freq);
    }
}
