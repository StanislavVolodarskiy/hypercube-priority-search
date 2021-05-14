import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BigDecimalValues implements Values {
    private final BigDecimal[][] values;

    public BigDecimalValues(InputStream is) {
        Scanner s = new Scanner(is);
        int n = s.nextInt();
        values = new BigDecimal[n][];
        for (int i = 0; i < n; ++i) {
            int m = s.nextInt();
            values[i] = new BigDecimal[m];
            for (int j = 0; j < m; ++j) {
                values[i][j] = s.nextBigDecimal();
                if (j > 0) {
                    assert values[i][j - 1].compareTo(values[i][j]) <= 0;
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
        return (o1, o2) -> sum_(o1).compareTo(sum_(o2));
    }

    @Override
    public LongHeap.Comparator getCachedLongComparator(int size) {
        Cache cache = new Cache(size);
        return (v1, v2) -> cache.sum(v1).compareTo(cache.sum(v2));
    }

    private BigDecimal sum_(int[] indices) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0; i < values.length; ++i) {
            sum = sum.add(values[i][indices[i]]);
        }
        return sum;
    }

    private BigDecimal sum_(long index) {
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal[] v : values) {
            sum = sum.add(v[(int)(index % v.length)]);
            index /= v.length;
        }
        return sum;
    }

    private class Cache {
        private int size;
        private final long[] x;
        private final BigDecimal[] y;

        public Cache(int size) {
            this.size = size;
            x = new long[size];
            Arrays.fill(x, -1);
            y = new BigDecimal[size];
        }

        public BigDecimal sum(long index) {
            int j = (int)(index % size);
            if (index != x[j]) {
                x[j] = index;
                y[j] = sum_(index);
            }
            return y[j];
        }
    }
}
