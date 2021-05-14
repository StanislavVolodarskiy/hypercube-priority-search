import java.math.BigDecimal;

public class DriverBigDecimal {
    public static void main(String... args) {
        BigDecimal[][] values = Utils.readValues(System.in);
        startReporter();
        test(values, getIterable(args[0], values));
    }

    private static Iterable<int[]> getIterable(String what, BigDecimal[][] values) {
        switch (what) {
        case "SortedArrayList":
            return new SortedArrayList(values);
        case "CompactSortedArrayList":
            return new CompactSortedArrayList(values);
        case "CompactPriorityQueue":
            return new CompactPriorityQueue(values);
        default:
            assert false;
        }
        return null;
    }

    private static void test(BigDecimal[][] values, Iterable<int[]> iterable) {
        long n = 0;
        BigDecimal lastSum = null;
        for (int[] indices : iterable) {
            ++n;
            if (n % 1000000 == 0) {
                log("n", n);
            }
            BigDecimal sum = Utils.sum(values, indices);
            assert lastSum == null || lastSum.compareTo(sum) <= 0;
            lastSum = sum;
        }
        assert n == Utils.size(values);
    }

    private static void startReporter() {
        Thread thread = new Thread(() -> {
            for (; ; ) {
                log("heap", Runtime.getRuntime().totalMemory());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private static void log(String what, long value) {
        System.err.println("" + System.currentTimeMillis() + " " + what + " " + value);
    }
}
