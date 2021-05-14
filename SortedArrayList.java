import java.util.ArrayList;
import java.util.Iterator;

public class SortedArrayList implements Iterable<int[]> {
    private final Iterable<int[]> iterable;

    public SortedArrayList(Values values) {
        int[] dims = values.getDims();
        long size = Utils.size(dims);
        assert(size <= Integer.MAX_VALUE);
        int[] indices = new int[dims.length];

        ArrayList<int[]> list = new ArrayList<int[]>();
        list.ensureCapacity((int)size);
        for (; ; ) {
            list.add(indices.clone());
            int i;
            for (i = indices.length - 1; i >= 0; --i) {
                if (indices[i] < dims[i] - 1) {
                    break;
                }
            }
            if (i < 0) {
                break;
            }
            ++indices[i];
            for (int j = i + 1; j < indices.length; ++j) {
                indices[j] = 0;
            }
        }

        list.sort(values.getComparator());
        iterable = list;
    }

    @Override
    public Iterator<int[]> iterator() {
        return iterable.iterator();
    }
}
