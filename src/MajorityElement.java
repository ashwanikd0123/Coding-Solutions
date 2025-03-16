import java.util.ArrayList;
import java.util.List;


// Boyer-Moore Algorithm
public class MajorityElement {
    class Solution {
        public List<Integer> majorityElement(int[] nums) {
            int count1 = 0;
            int maj1 = Integer.MAX_VALUE;

            int count2 = 0;
            int maj2 = Integer.MAX_VALUE;

            for (int x : nums) {
                if (x == maj1) {
                    count1++;
                } else if (x == maj2) {
                    count2++;
                } else if (count1 == 0) {
                    maj1 = x;
                    count1++;
                } else if (count2 == 0) {
                    maj2 = x;
                    count2++;
                } else {
                    count1--;
                    count2--;
                }
            }

            // verify
            count1 = count2 = 0;
            for (int x : nums) {
                if (x == maj1) {
                    count1++;
                } else if (x == maj2) {
                    count2++;
                }
            }

            int n = nums.length;
            List<Integer> res = new ArrayList<>();
            if (count1 > n / 3) {
                res.add(maj1);
            }
            if (count2 > n / 3) {
                res.add(maj2);
            }

            return res;
        }
    }
}
