package timingtest;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        Ns.addLast(1000);
        Ns.addLast(2000);
        Ns.addLast(4000);
        Ns.addLast(8000);
        Ns.addLast(16000);
        Ns.addLast(32000);
        Ns.addLast(64000);
        Ns.addLast(128000);
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        opCounts.addLast(10000);
        opCounts.addLast(10000);
        opCounts.addLast(10000);
        opCounts.addLast(10000);
        opCounts.addLast(10000);
        opCounts.addLast(10000);
        opCounts.addLast(10000);
        opCounts.addLast(10000);
        for(int i = 0, len = Ns.size(); i < len; i ++){
            SLList<Integer> testSLList = new SLList<>();
            addCount(testSLList, Ns.get(i));
            Stopwatch stopwatch = new Stopwatch();
            for(int j = 0; j < opCounts.get(i); j ++){
                testSLList.getLast();
            }
            times.addLast(stopwatch.elapsedTime());
        }
        printTimingTable(Ns, times, opCounts);
    }
    public static void addCount(SLList<Integer>s, int cnt){
        for(int i = 0; i < cnt; i ++){
            s.addLast(i);
        }
    }
}
