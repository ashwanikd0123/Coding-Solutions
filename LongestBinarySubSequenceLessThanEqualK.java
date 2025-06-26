public class LongestBinarySubSequenceLessThanEqualK {
    class DPSolution {
        int[][] dp;
        int maxBitDist;
        int prefixZeroCount[];

        int ls(char[] s, int k, int i, long val, int d) {
            if (i < 0) {
                return 0;
            }

            if (dp[i][d] >= 0) {
                return dp[i][d];
            }

            if (s[i] == '0') {
                return dp[i][d] = 1 + ls(s, k, i - 1, val, d + 1);
            }

            int res = 0;
            if (d <= maxBitDist && (1L << d) + val <= k) {
                res = 1 + ls(s, k, i - 1, val + (1L << d), d + 1);
            }
            res = Math.max(res, prefixZeroCount[i]);

            return dp[i][d] = res;
        }

        void setMaxBitDist(int k) {
            maxBitDist = 0;
            while (k > 0) {
                maxBitDist++;
                k >>= 1;
            }
        }

        void setPrefixZeroCount(char[] s) {
            prefixZeroCount = new int[s.length];
            int count = 0;
            int i = 0;
            for (char c : s) {
                if (c == '0') {
                    count++;
                }
                prefixZeroCount[i++] = count;
            }
        }

        public int longestSubsequence(String s, int k) {
            int n = s.length();

            setMaxBitDist(k);

            setPrefixZeroCount(s.toCharArray());

            dp = new int[n + 1][n + 1];
            for (int[] d : dp) {
                Arrays.fill(d, -1);
            }

            return ls(s.toCharArray(), k, n - 1, 0, 0);
        }
    }

    class GreedySolution {

        int getMaxBitDist(int k) {
            int maxBitDist = 0;
            while (k > 0) {
                maxBitDist++;
                k >>= 1;
            }
            return maxBitDist;
        }

        public int longestSubsequence(String s, int k) {
            int n = s.length();
            int res = 0;
            int sum = 0;
            int maxBitDist = getMaxBitDist(k);
            for (int i = n - 1; i >= 0; i--) {
                if (s.charAt(i) == '0') {
                    res++;
                } else {
                    if (res + 1 <= maxBitDist && (1 << res) + sum <= k) {
                        sum = (1 << res) + sum;
                        res++;
                    }
                }
            }
            return res;
        }
    }
}
