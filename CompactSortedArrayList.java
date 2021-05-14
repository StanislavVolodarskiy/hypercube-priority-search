import java.util.Iterator;
import java.util.NoSuchElementException;

public class CompactSortedArrayList implements Iterable<int[]> {
    private final int[] dims;
    private final long[] array;

    public CompactSortedArrayList(Values values) {
        dims = values.getDims();

        long size = values.size();
        assert(size <= Integer.MAX_VALUE);

        array = new long[(int)size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = i;
        }

        LongSort.sort(
            array,
            values.getCachedLongComparator(/* 1 */ /* 101 */ 100003 /* 1000003 */ /* 10000019 */)
        );
    }

    @Override
    public Iterator<int[]> iterator() {
        return new Iterator_(dims, array);
    }

    private static class Iterator_ implements Iterator<int[]> {
        private final int[] dims;
        private final long[] indices;
        private final int[] index;
        private int i;

        public Iterator_(int[] dims, long[] indices) {
            this.dims = dims;
            this.indices = indices;
            index = new int[dims.length];
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
            for (int i = 0; i < dims.length; ++i) {
                this.index[i] = (int)(index % dims[i]);
                index /= dims[i];
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
