import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class MinOperationsToMakeArrayContinuous {
    class Solution {

        private static ArrayList<Integer> getUniqueElements(int[] nums) {
            HashSet<Integer> unique = new HashSet<>();
            for (int num : nums) {
                unique.add(num);
            }
            return new ArrayList<>(unique);
        }

        public int minOperations(int[] nums) {
            int n = nums.length;

            ArrayList<Integer> uniques = getUniqueElements(nums);
            Collections.sort(uniques);

            int j = 0;
            int res = n;
            for (int i = 0; i < uniques.size(); i++) {
                int min = uniques.get(i);
                while (j < uniques.size() && uniques.get(j) < min + n) {
                    j++;
                }

                int count = j - i;
                res = Math.min(res, n - count);
            }

            return res;
        }
    }
}
