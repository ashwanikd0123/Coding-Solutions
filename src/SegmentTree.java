public class SegmentTree {
    static int[] st;

    public static int[] constructST(int[] arr, int n) {
        st = new int[4 * n];
        buildTree(st, arr, 0, 0, n - 1);
        return st;
    }

    public static int RMQ(int[] st, int n, int l, int r) {
        return query(st, 0, 0, n - 1, l, r);
    }

    private static int query(int[] st, int i, int l, int r, int rangeL, int rangeR) {
        if (r < rangeL || l > rangeR) {
            return Integer.MAX_VALUE;
        }
        if (l >= rangeL && r <= rangeR) {
            return st[i];
        }
        int mid = (l + r) / 2;
        return Math.min(
                query(st, left(i), l, mid, rangeL, rangeR),
                query(st, right(i), mid + 1, r, rangeL, rangeR)
        );
    }

    private static void buildTree(int[] st, int[] arr, int i, int l, int r) {
        if (l == r) {
            st[i] = arr[l];
            return;
        }
        int mid = (l + r) / 2;
        buildTree(st, arr, left(i), l, mid);
        buildTree(st, arr, right(i), mid + 1, r);
        st[i] = Math.min(st[left(i)], st[right(i)]);
    }

    private static int left(int x) {
        return 2 * x + 1;
    }

    private static int right(int x) {
        return 2 * x + 2;
    }

}
