import java.math.BigDecimal;
import java.io.InputStream;
import java.util.Scanner;

public class Utils {
    public static BigDecimal[][] readValues(InputStream is) {
        Scanner s = new Scanner(is);
        int n = s.nextInt();
        BigDecimal[][] values = new BigDecimal[n][];
        for (int i = 0; i < n; ++i) {
            int m = s.nextInt();
            values[i] = new BigDecimal[m];
            for (int j = 0; j < m; ++j) {
                values[i][j] = s.nextBigDecimal();
                if (j > 0) {
                    assert values[i][j - 1].compareTo(values[i][j]) <= 0;
                }
            }
        }
        return values;
    }

    public static long size(BigDecimal[][] values) {
        long product = 1;
        for (BigDecimal[] v : values) {
            product *= v.length;
        }
        return product;
    }

    public static long sizeButLast(BigDecimal[][] values) {
        long product = 1;
        for (int i = 0; i < values.length - 1; ++i) {
            product *= values[i].length;
        }
        return product;
    }

    public static BigDecimal sum(BigDecimal[][] values, int[] indices) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0; i < values.length; ++i) {
            sum = sum.add(values[i][indices[i]]);
        }
        return sum;
    }

    public static BigDecimal sum(BigDecimal[][] values, long index) {
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal[] v : values) {
            sum = sum.add(v[(int)(index % v.length)]);
            index /= v.length;
        }
        return sum;
    }
}
