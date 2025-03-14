import java.util.Arrays;

public class MissingNumber {
    class Solution {

        int sumApproach(int[] nums) {
            int n = nums.length;
            int sum = n * (n - 1) / 2;

            boolean zeroPresent = false;

            for (int x : nums) {
                if (x < n) {
                    sum -= x;
                }
                if (x == 0) {
                    zeroPresent = true;
                }
            }

            if (!zeroPresent) {
                return 0;
            }

            if (sum == 0) {
                return n;
            }

            return sum;
        }

        int sortApproach(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length;

            if (nums[n - 1] == n - 1) {
                return n;
            }

            int i = 0;
            int j = n - 1;
            int res = 0;
            while (i <= j) {
                int mid = (i + j) / 2;
                if (nums[mid] > mid) {
                    res = mid;
                    j = mid - 1;
                } else {
                    i = mid + 1;
                }
            }

            return res;
        }

        int xorApproach(int[] nums) {
            int xor = nums.length;
            for (int i = 0; i < nums.length; i++) {
                xor = xor ^ i ^ nums[i];
            }
            return xor;
        }

        public int missingNumber(int[] nums) {
            return xorApproach(nums);
        }
    }
}
