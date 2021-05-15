import java.io.InputStream;
import java.util.Comparator;

public class Driver {
    public static void main(String... args) {
        Values values = getValues(args[0], System.in);
        startReporter();
        test(values, getIterable(args[1], values));
    }

    private static Values getValues(String what, InputStream is) {
        switch (what) {
        case "LongValues":
            return new LongValues(is);
        case "BigDecimalValues":
            return new BigDecimalValues(is);
        default:
            assert false;
        }
        return null;
    }

    private static Iterable<int[]> getIterable(String what, Values values) {
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

    private static void test(Values values, Iterable<int[]> iterable) {
        Comparator<int[]> comparator = values.getComparator();
        
        long n = 0;
        int[] prev = null;
        for (int[] indices : iterable) {
            ++n;
            if (n % 1000000 == 0) {
                log("n", n);
            }
            if (prev == null) {
                prev = indices.clone();
            } else {
                assert comparator.compare(prev, indices) <= 0;
                System.arraycopy(indices, 0, prev, 0, indices.length);
            }
        }
        assert n == values.size();
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
