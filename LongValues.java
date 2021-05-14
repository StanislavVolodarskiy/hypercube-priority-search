import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class LongValues extends Values {
    private final long[][] values;

    public LongValues(InputStream is) {
        Scanner s = new Scanner(is);
        int n = s.nextInt();
        values = new long[n][];
        for (int i = 0; i < n; ++i) {
            int m = s.nextInt();
            values[i] = new long[m];
            for (int j = 0; j < m; ++j) {
                values[i][j] = s.nextLong();
                if (j > 0) {
                    assert values[i][j - 1] <= values[i][j];
                }
            }
        }
    }

    @Override
    public int[] getDims() {
        int[] dims = new int[values.length];
        for (int i = 0; i < values.length; ++i) {
            dims[i] = values[i].length;
        }
        return dims;
    }

    @Override
    public Comparator<int[]> getComparator() {
        return (o1, o2) -> Long.compare(sum_(o1), sum_(o2));
    }

    @Override
    public LongHeap.Comparator getCachedLongComparator(int size) {
        Cache cache = new Cache(size);
        return (v1, v2) -> Long.compare(cache.sum(v1), cache.sum(v2));
    }

    private long sum_(int[] indices) {
        long sum = 0;
        for (int i = 0; i < values.length; ++i) {
            sum += values[i][indices[i]];
        }
        return sum;
    }

    private long sum_(long index) {
        long sum = 0;
        for (long[] v : values) {
            sum += v[(int)(index % v.length)];
            index /= v.length;
        }
        return sum;
    }

    private class Cache {
        private int size;
        private final long[] x;
        private final long[] y;

        public Cache(int size) {
            this.size = size;
            x = new long[size];
            Arrays.fill(x, -1);
            y = new long[size];
        }

        public long sum(long index) {
            int j = (int)(index % size);
            if (index != x[j]) {
                x[j] = index;
                y[j] = sum_(index);
            }
            return y[j];
        }
    }
}
