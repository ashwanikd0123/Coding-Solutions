import java.util.Stack;

public class MaximalRectangle {
    static class DPSolution {
        static int maxAreaHist(int[] hist) {
            int n = hist.length;

            int[] nsl = new int[n];
            int[] nsr = new int[n];

            nsl[0] = -1;
            for (int i = 0; i < n; i++) {
                int p = i - 1;
                while (p >= 0 && hist[p] >= hist[i]) {
                    p = nsl[p];
                }
                nsl[i] = p;
            }

            nsr[n - 1] = n;
            for (int i = n - 1; i >= 0; i--) {
                int p = i + 1;
                while (p < n && hist[p] >= hist[i]) {
                    p = nsr[p];
                }
                nsr[i] = p;
            }

            int res = 0;
            for (int i = 0; i < n; i++) {
                res = Math.max(res, ((nsr[i] - 1) - (nsl[i] + 1) + 1) * hist[i]);
            }

            return res;
        }
    }

    static class StackSolution {
        static int maxAreaHist(int[] hist) {
            int n = hist.length;

            Stack<Integer> s = new Stack<Integer>();

            int[] nsl = new int[n];
            int[] nsr = new int[n];

            nsr[n - 1] = n;
            s.push(n - 1);
            for (int i = n - 2; i >= 0; i--) {
                while (!s.isEmpty() && hist[i] <= hist[s.peek()]) {
                    s.pop();
                }
                if (s.isEmpty()) {
                    nsr[i] = n;
                } else {
                    nsr[i] = s.peek();
                }
                s.push(i);
            }

            s.clear();
            nsl[0] = -1;
            s.push(0);
            for (int i = 1; i < n; i++) {
                while (!s.isEmpty() && hist[i] <= hist[s.peek()]) {
                    s.pop();
                }
                if (s.isEmpty()) {
                    nsl[i] = -1;
                } else {
                    nsl[i] = s.peek();
                }
                s.push(i);
            }

            int res = 0;
            for (int i = 0; i < n; i++) {
                res = Math.max(res, ((nsr[i] - 1) - (nsl[i] + 1) + 1) * hist[i]);
            }

            return res;
        }
    }


    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] hist = new int[cols];

        int res = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    hist[j]++;
                } else {
                    hist[j] = 0;
                }
            }
            res = Math.max(res, DPSolution.maxAreaHist(hist));
        }

        return res;
    }
}
