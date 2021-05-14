import java.util.Comparator;

public abstract class Values {
    public abstract int[] getDims();
    public abstract Comparator<int[]> getComparator();
    public abstract LongHeap.Comparator getCachedLongComparator(int size);

    public long size() {
        int[] dims = getDims();
        long product = 1;
        for (int v : dims) {
            product *= v;
        }
        return product;
    }

    public long sizeButLast() {
        int[] dims = getDims();
        long product = 1;
        for (int i = 0; i < dims.length - 1; ++i) {
            product *= dims[i];
        }
        return product;
    }
}
