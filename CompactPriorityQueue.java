import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CompactPriorityQueue implements Iterable<int[]> {
    private final BigDecimal[][] values;

    public CompactPriorityQueue(BigDecimal[][] values) {
        this.values = values;
    }

    @Override
    public Iterator<int[]> iterator() {
        return new Iterator_(values);
    }

    private static class Iterator_ implements Iterator<int[]> {
        private final BigDecimal[][] values;
        private final CachedSum cache;
        private final LongPriorityQueue queue;
        private final int[] index;

        public Iterator_(BigDecimal[][] values) {
            this.values = values;
            cache = new CachedSum(values, 100003 /* 1000003 10000019 */);
            long size = Utils.sizeButLast(values);
            assert size < Integer.MAX_VALUE;
            queue = new LongPriorityQueue(
                (int)size,
                // reverse order
                (i1, i2) -> cache.sum(i2).compareTo(cache.sum(i1))
            );
            queue.put(0);
            index = new int[values.length];
        }

        @Override
        public boolean hasNext() {
            return queue.size() > 0;
        }

        @Override
        public int[] next() {
            if (queue.size() == 0) {
                throw new NoSuchElementException();
            }
            long index = queue.getMax();
            {
                long index2 = index;
                for (int i = 0; i < values.length; ++i) {
                    this.index[i] = (int)(index2 % values[i].length);
                    index2 /= values[i].length;
                }
            }
            int j;
            for (j = values.length - 1; j > 0; --j) {
                if (this.index[j] > 0) {
                    break;
                }
            }
            long p = 1;
            for (int i = 0; i < values.length; ++i) {
                if (i >= j && this.index[i] < values[i].length - 1) {
                    queue.put(index + p);
                }
                p *= values[i].length;
            } 
            return this.index;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
