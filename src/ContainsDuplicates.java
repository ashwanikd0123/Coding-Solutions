import java.util.HashMap;

public class ContainsDuplicates {
    class Solution {

        public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
            int n = nums.length;
            HashMap<Integer, Integer> buckets = new HashMap<>();
            for (int i = 0 ; i < nums.length; i++) {
                int b = nums[i] / (valueDiff + 1);

                if (nums[i] < 0) {
                    b--;
                }

                if (buckets.containsKey(b)) {
                    return true;
                } else {
                    buckets.put(b, nums[i]);
                    if (buckets.containsKey(b - 1) && nums[i] - buckets.get(b - 1) <= valueDiff) {
                        return true;
                    }
                    if (buckets.containsKey(b + 1) && buckets.get(b + 1) - nums[i] <= valueDiff) {
                        return true;
                    }
                    if (buckets.size() > indexDiff) {
                        int removeKey = nums[i - indexDiff] / (valueDiff + 1);
                        if (nums[i - indexDiff] < 0) {
                            removeKey--;
                        }
                        buckets.remove(removeKey);
                    }
                }
            }

            return false;
        }
    }
}
