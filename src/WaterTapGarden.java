public class WaterTapGarden {
    class Solution {
        public int minTaps(int n, int[] ranges) {
            // indicates maximum length from i to maxEnd[i] that can be filled by just one tap
            // index is left most point and right most point is maxEnd[i];
            int[] maxEnd = new int[n + 1];

            for (int i = 0; i < n + 1; i++) {
                int left = Math.max(0, i - ranges[i]);
                int right = Math.min(n, i + ranges[i]);
                maxEnd[left] = Math.max(maxEnd[left], right);
            }

            int i = 0;
            int maxWaterEnd = 0;
            int curWaterEnd = 0;
            int taps = 0;

            while (i <= n) {

                if (i > maxWaterEnd) {
                    return -1;
                }

                if (i > curWaterEnd) {
                    taps++;
                    curWaterEnd = maxWaterEnd;
                }

                maxWaterEnd = Math.max(maxWaterEnd, maxEnd[i]);

                i++;
            }

            return taps;

        }
    }
}
