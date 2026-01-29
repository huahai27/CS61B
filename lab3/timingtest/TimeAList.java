package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> size = new AList<>();
        size.addLast(1000);
        size.addLast(2000);
        size.addLast(4000);
        size.addLast(8000);
        size.addLast(16000);
        size.addLast(32000);
        size.addLast(64000);
        size.addLast(128000);

        int tests = size.size();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        for (int i = 0; i < tests; i++) {
            Stopwatch sw = new Stopwatch();
            int call_times = size.get(i);
            Ns.addLast(call_times);
            AList<Integer> list = new AList<>();

            for (int t = 0; t < call_times; t++) {
                list.addLast(t);
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
        }

        printTimingTable(Ns, times, Ns);
    }
}
