public class LongHeap {
    public static interface Comparator {
        int compare(long v1, long v2);
    }

    private final long[] array;
    private final Comparator comparator;

    public LongHeap(long[] array, Comparator comparator) {
        this.array = array;
        this.comparator = comparator;
    }

    public void heapify() {
        for (int i = parent(array.length - 1); i >= 0; --i) {
            siftDown(i, array.length);
        }
    }

    public void swap(int i, int j) {
        long tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public void siftDown(int begin, int end) {
        int parent = begin;
        for (; ; ) {
            int greatest = parent;
            int left = leftChild(parent);
            if (left < end && not_ordered(greatest, left)) {
                greatest = left;
            }
            int right = left + 1;
            if (right < end && not_ordered(greatest, right)) {
                greatest = right;
            }
            if (greatest == parent) {
                break;
            }
            swap(parent, greatest);
            parent = greatest;
        }
    }

    public void siftUp(int i) {
        int child = i;
        while (child > 0) {
            int parent = parent(child);
            if (not_ordered(parent, child)) {
                swap(parent, child);
                child = parent;
            } else {
                break;
            }
        }
    }

    private boolean not_ordered(int i, int j) {
        return comparator.compare(array[i], array[j]) < 0;
    }

    private static int parent(int child) {
        return (child - 1) / 2;
    }

    private static int leftChild(int parent) {
        return 2 * parent + 1;
    }
}
