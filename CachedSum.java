import java.math.BigDecimal;
import java.util.Arrays;

public class CachedSum {
    private final BigDecimal[][] values;
    private final int size;
    private final long[] cacheX;
    private final BigDecimal[] cacheY;

    public CachedSum(BigDecimal[][] values, int size) {
        this.values = values;
        this.size = size;
        cacheX = new long[size];
        Arrays.fill(cacheX, -1);
        cacheY = new BigDecimal[size];
    }

    public BigDecimal sum(long index) {
        int j = (int)(index % size);
        if (index != cacheX[j]) {
            cacheX[j] = index;
            cacheY[j] = Utils.sum(values, index);
        }
        return cacheY[j];
    }
}


