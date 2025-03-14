public class SegmentTree {
    static int[] st;

    public static int[] constructST(int[] arr, int n) {
        st = new int[2 * n - 1];
        buildTree(st, arr, 0, 0, arr.length - 1);
        return st;
    }


    public static int RMQ(int[] st, int n, int l, int r) {

    }

    private static void buildTree(int[] st, int arr[], int i, int l, int r) {
        if (l > r) {
            return;
        }
        if (l == r) {
            st[i] = arr[l];
            return;
        }
        int mid = (l + r) / 2;
        buildTree(st, arr, left(i), l, mid);
        buildTree(st, arr, right(i), mid + 1, r);
        st[i] = Math.min(arr[left(i)], arr[right(i)]);
    }


    private static int parent(int x) {
        return (x - 1) / 2;
    }

    private static int left(int x) {
        return 2 * x + 1;
    }

    private static int right(int x) {
        return 2 * x + 2;
    }

}
