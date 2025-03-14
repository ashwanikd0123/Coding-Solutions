public class SubarraysWithFixedBounds {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;

        long res = 0;

        int minPos = -1;
        int maxPos = -1;
        int culprit = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == minK) {
                minPos = i;
            }

            if (nums[i] == maxK) {
                maxPos = i;
            }

            if (nums[i] < minK || nums[i] > maxK) {
                culprit = i;
                continue;
            }

            long minStartIdx = Math.min(minPos, maxPos);
            res += Math.max(0, minStartIdx - culprit);
        }

        return res;
    }
}
