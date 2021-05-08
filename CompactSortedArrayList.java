import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CompactSortedArrayList implements Iterable<int[]> {
    private final BigDecimal[][] values;
    private final long[] array;

    public CompactSortedArrayList(BigDecimal[][] values) {
        this.values = values;

        long size = Utils.size(values);
        assert(size <= Integer.MAX_VALUE);

        array = new long[(int)size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = i;
        }

        CachedSum cache = new CachedSum(values, 100003 /* 1000003 10000019 */);
        LongSort.sort(
            array,
            (i1, i2) -> cache.sum(i1).compareTo(cache.sum(i2))
        );
    }

    @Override
    public Iterator<int[]> iterator() {
        return new Iterator_(values, array);
    }

    private static class Iterator_ implements Iterator<int[]> {
        private final BigDecimal[][] values;
        private final long[] indices;
        private final int[] index;
        private int i;

        public Iterator_(BigDecimal[][] values, long[] indices) {
            this.values = values;
            this.indices = indices;
            index = new int[values.length];
            i = 0;
        }

        @Override
        public boolean hasNext() {
            return i < indices.length;
        }

        @Override
        public int[] next() {
            if (i >= indices.length) {
                throw new NoSuchElementException();
            }
            long index = indices[i];
            for (int i = 0; i < values.length; ++i) {
                this.index[i] = (int)(index % values[i].length);
                index /= values[i].length;
            }
            ++i;
            return this.index;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
