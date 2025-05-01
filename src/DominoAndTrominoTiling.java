import java.util.Arrays;

public class DominoAndTrominoTiling {
    class Solution {
        static final long MOD = 1000000007;
        long[][] dp;
        long tile(int n, int i, int j) {
            if (i == n - 2) {
                if (j == 0) {
                    return dp[j][i] = 2;
                } else {
                    return dp[j][i] = 1;
                }
            } else if (i == n - 1) {
                if (j == 0) {
                    return dp[j][i] = 1;
                } else {
                    return dp[j][i] = 0;
                }
            } else if (i >= n) {
                return 0;
            }

            if (dp[j][i] != -1) {
                return dp[j][i];
            }

            long res = 0;
            if (j == 0) {
                long a = tile(n, i + 1, 0);
                long b = tile(n, i + 2, 0);
                long c = tile(n, i + 1, 1);
                long d = tile(n, i + 1, 2);
                res = (((((a + b) % MOD) + c) % MOD) + d) % MOD;
            } else if (j == 1) {
                long a = tile(n, i + 1, 2);
                long b = tile(n, i + 2, 0);
                res = (a + b) % MOD;
            } else if (j == 2) {
                long a = tile(n, i + 1, 1);
                long b = tile(n, i + 2, 0);
                res = (a + b) % MOD;
            }
            return dp[j][i] = res;
        }

        public int numTilings(int n) {
            if (n == 1) {
                return 1;
            }

            dp = new long[3][n];
            Arrays.fill(dp[0], -1);
            Arrays.fill(dp[1], -1);
            Arrays.fill(dp[2], -1);

            return (int) tile(n, 0, 0);
        }
    }
}
