package statistics.StatisCalc;

public class StatisticsCalc {

    public QualitaveSambles qual;
    public QuanSambles quan;
    public QuanWithClassesAndFreq quanC;

    // For descriptive statistics
    public StatisticsCalc(String[] samplesS) {
        qual = new QualitaveSambles(samplesS);
    }

    // For frequency distribution
    public StatisticsCalc(int[] samplesInt, int classNum) {
        quan = new QuanSambles(samplesInt, classNum);
    }

    // For frequency distribution with given classes and frequencies
    public StatisticsCalc(String[] classes, int[] freq) {
        quanC = new QuanWithClassesAndFreq(classes, freq);
    }

}
