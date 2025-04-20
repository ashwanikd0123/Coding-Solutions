public class CreateMaximumNumber {
    class Solution {

        int[] maxSubSequence(int[] nums, int k) {
            int[] res = new int[k];
            int size = 0;
            int drops = nums.length - k;
            for (int val : nums) {
                while (size > 0 && drops > 0 && res[size - 1] < val) {
                    size--;
                    drops--;
                }
                if (size < k) {
                    res[size++] = val;
                } else {
                    drops--;
                }
            }
            return res;
        }

        int[] merge(int[] a, int[] b) {
            int[] res = new int[a.length + b.length];
            int size = 0;
            int i = 0;
            int j = 0;
            while (i < a.length || j < b.length) {
                if (greater(a, i, b, j)) {
                    res[size++] = a[i++];
                } else {
                    res[size++] = b[j++];
                }
            }
            return res;
        }

        boolean greater(int[] nums1, int i, int[] nums2, int j) {
            while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
                i++;
                j++;
            }
            return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
        }

        public int[] maxNumber(int[] nums1, int[] nums2, int k) {
            if (nums1.length > nums2.length) {
                return maxNumber(nums2, nums1, k);
            }

            int maxLeft = Math.min(nums1.length, k);
            int minLeft = Math.max(0, k - nums2.length);

            int[] res = null;
            for (int i = minLeft; i <= maxLeft; i++) {
                int[] leftSubSequence = maxSubSequence(nums1, i);
                int[] rightSubSequence = maxSubSequence(nums2, k - i);
                int[] cur = merge(leftSubSequence, rightSubSequence);
                if (res == null) {
                    res = cur;
                } else if (greater(cur, 0, res, 0)) {
                    res = cur;
                }
            }
            return res;
        }
    }
}
