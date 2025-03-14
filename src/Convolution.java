public class Convolution {
    static int convolve(int[][] img1, int[][] img2) {
        int n = img1.length;
        int max = 2 * n - 1;
        int res = 0;
        int cur;
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                cur = 0;

                int r1 = Math.max(0, n - 1 - i);
                int c1 = Math.max(0, n - 1 - j);

                int r2 = Math.max(0, i - n + 1);
                int c2 = Math.max(0, j - n + 1);

                int width = Math.min(n - c1, n - c2);
                int height = Math.min(n - r1, n - r2);

                for (int k = 0; k < height; k++) {
                    for (int l = 0; l < width; l++) {
                        cur += img1[r1 + k][c1 + l] * img2[r2 + k][c2 + l];
                    }
                }

                res = Math.max(cur, res);
            }
        }

        return res;
    }

    public int largestOverlap(int[][] img1, int[][] img2) {
        return convolve(img1, img2);
    }
}
