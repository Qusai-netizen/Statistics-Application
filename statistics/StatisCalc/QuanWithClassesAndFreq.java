package statistics.StatisCalc;

public class QuanWithClassesAndFreq extends Quan {

    // For frequency distribution with given classes and frequencies
    public QuanWithClassesAndFreq(String[] classes, int[] freq) {

        this._class = classes;
        this.freq = freq;
        this.classNum = classes.length;
        this._class = classes;

        classlower = new int[classNum];
        classupper = new int[classNum];
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

        for (int i = 0; i < classNum; ++i) {
            classlower[i] = Integer.parseInt(_class[i].substring(0, _class[i].indexOf(' ')));
            classupper[i] = Integer.parseInt(_class[i].substring(_class[i].lastIndexOf(' ') + 1));

            System.out.println(classlower[i] + "   " + classupper[i]);
        }
    }

    private void makeFreqI(int[] freq) {
        this.freq = freq;
    }

    public double getQuartileC(int i) {

        double MdOrder = (double) (freqSum * i) / 4;
        int rank = 0;
        for (int j = 0; j < classNum; ++j) {
            if (MdOrder <= cumulativeFreq[j]) {
                rank = j;
                break;
            }
        }

        int n = freqSum,
                MdF = freq[rank],
                width = (int) (classBoundupper[0] - classBoundlower[0]);

        double cf = (rank == 0) ? 0 : cumulativeFreq[rank - 1],
                MdLm = classBoundlower[rank];

        return MdLm + ((i * n / 4.0 - cf) / MdF) * width;
    }

    // Main method

    public void makeN(int[] freq) {
        makeClassN();
        makeFreqI(freq);
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