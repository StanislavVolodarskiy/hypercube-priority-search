public class LongPriorityQueue {
    private int n;
    private final long[] buffer;
    private final LongHeap heap;

    public LongPriorityQueue(int size, LongHeap.Comparator comparator) {
        n = 0;
        buffer = new long[size];
        heap = new LongHeap(buffer, comparator);
    }

    public int size() {
        return n;
    }

    public void put(long v) {
        buffer[n] = v;
        heap.siftUp(n);
        ++n;
    }

    public long getMax() {
        assert n > 0;
        long max = buffer[0];
        --n;
        buffer[0] = buffer[n];
        heap.siftDown(0, n);
        return max;
    }
}
