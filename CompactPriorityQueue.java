import java.util.Iterator;
import java.util.NoSuchElementException;

public class CompactPriorityQueue implements Iterable<int[]> {
    private final Values values;

    public CompactPriorityQueue(Values values) {
        this.values = values;
    }

    @Override
    public Iterator<int[]> iterator() {
        return new Iterator_(values);
    }

    private static class Iterator_ implements Iterator<int[]> {
        private final int[] dims;
        private final LongPriorityQueue queue;
        private final int[] index;

        public Iterator_(Values values) {
            dims = values.getDims();
            LongHeap.Comparator comparator = values.getCachedLongComparator(/* 1 */ /* 101 */ 100003 /* 1000003 */ /* 10000019 */);
            long size = values.sizeButLast();
            assert size < Integer.MAX_VALUE;
            queue = new LongPriorityQueue(
                (int)size,
                // reverse order
                (i1, i2) -> comparator.compare(i2, i1)
            );
            queue.put(0);
            index = new int[dims.length];
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
                for (int i = 0; i < dims.length; ++i) {
                    this.index[i] = (int)(index2 % dims[i]);
                    index2 /= dims[i];
                }
            }
            int j;
            for (j = dims.length - 1; j > 0; --j) {
                if (this.index[j] > 0) {
                    break;
                }
            }
            long p = 1;
            for (int i = 0; i < dims.length; ++i) {
                if (i >= j && this.index[i] < dims[i] - 1) {
                    queue.put(index + p);
                }
                p *= dims[i];
            } 
            return this.index;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
