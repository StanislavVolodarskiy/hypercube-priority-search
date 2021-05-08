public class LongSort {
    public static void sort(long[] array, LongHeap.Comparator comparator) {
        LongHeap heap = new LongHeap(array, comparator);
        heap.heapify();
        for (int i = array.length - 1; i >= 0; --i) {
            heap.swap(0, i);
            heap.siftDown(0, i);
        }
    }
}
