import java.util.Arrays;

public class BurstBalloons {

    class SolutionRecursive {

        int solve(int[] nums, int[][] dp, int start, int end) {
            if (start > end || start < 0 || start >= nums.length || end >= nums.length) {
                return 0;
            }

            if (dp[start][end] != -1) {
                return dp[start][end];
            }

            int left = start == 0 ? 1 : nums[start - 1];
            int right = end == nums.length - 1 ? 1 : nums[end + 1];

            if (start == end) {
                return left * nums[start] * right;
            }

            int res = 0;

            for (int i = start; i <= end; i++) {
                int val = left * nums[i] * right;
                int leftVal = solve(nums, dp, start, i - 1);
                int rightVal = solve(nums, dp, i + 1, end);
                res = Math.max(res, leftVal + val + rightVal);
            }

            return dp[start][end] = res;
        }

        public int maxCoins(int[] nums) {
            int n = nums.length;
            int[][] dp = new int[n][n];
            for (int[] d : dp) {
                Arrays.fill(d, -1);
            }
            return solve(nums, dp, 0, n - 1);
        }
    }
}
