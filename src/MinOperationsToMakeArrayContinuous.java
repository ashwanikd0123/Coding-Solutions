import java.util.Arrays;
import java.util.HashSet;

public class MinOperationsToMakeArrayContinuous {
    class Solution {

        private static int[] getUniqueElements(int[] nums) {
            HashSet<Integer> unique = new HashSet<>();
            for (int num : nums) {
                unique.add(num);
            }
            int[] res = new int[unique.size()];
            int i = 0;
            for (int x : unique) {
                res[i++] = x;
            }
            return res;
        }

        public int minOperations(int[] nums) {
            int n = nums.length;

            int[] uniques = getUniqueElements(nums);
            Arrays.sort(uniques);

            int j = 0;
            int res = n;
            for (int i = 0; i < uniques.length; i++) {
                int min = uniques[i];
                while (j < uniques.length && uniques[j] < min + n) {
                    j++;
                }

                int count = n - (j - i);
                res = Math.min(res, n - count);
            }

            return res;
        }
    }
}
