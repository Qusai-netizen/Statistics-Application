package statistics.StatisCalc;

import java.util.HashSet;
import java.util.Set;

public class QualitaveSambles {

    StatisTools statisTools = new StatisTools();

    public int[] freq;

    public double[] classBoundlower, classBoundupper,
            midPoint, classRelativeF,
            classBoundariesLowUp;

    public String[] _class;

    public String[] classBoundaries;

    public String[] lessComulativeF;

    public String[] greaterComulativeF;

    public String[] samplesS;

    public int freqSum;

    private int sampNum, classNum;

    // For descriptive statistics
    public QualitaveSambles(String[] samplesS) {
        this.sampNum = samplesS.length;
        this.samplesS = samplesS;
        samplesS = new String[sampNum];

        makeClassS();
        this.classNum = _class.length;

        freq = new int[classNum];
    }

    private void makeClassS() {
        if (samplesS == null || samplesS.length == 0 || _class != null)
            return;

        Set<String> cl = new HashSet<>();
        for (String s : samplesS) {
            if (s != null) {
                cl.add(s);
            }
        }

        _class = cl.toArray(new String[0]);
    }

    private void makeFreqS() {
        for (int i = 0; i < classNum; ++i) {
            for (int j = 0; j < sampNum; ++j) {
                if (_class[i].equals(samplesS[j])) {
                    ++freq[i];
                }
            }
        }
    }

    // Main method

    public void makeS() {
        makeFreqS();
        freqSum = StatisTools.getFreqSum(freq, classNum);
    }
}
