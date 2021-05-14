public class Utils {
    public static long size(int[] dims) {
        long product = 1;
        for (int v : dims) {
            product *= v;
        }
        return product;
    }

    public static long sizeButLast(int[] dims) {
        long product = 1;
        for (int i = 0; i < dims.length - 1; ++i) {
            product *= dims[i];
        }
        return product;
    }
}
