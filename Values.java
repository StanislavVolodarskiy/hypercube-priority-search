import java.util.Comparator;

public interface Values {
    int[] getDims();
    Comparator<int[]> getComparator();
    LongHeap.Comparator getCachedLongComparator(int size);
}
