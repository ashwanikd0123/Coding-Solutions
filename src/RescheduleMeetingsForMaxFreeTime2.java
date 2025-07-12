public class RescheduleMeetingsForMaxFreeTime2 {
    class Solution {

        int getGap(int[] startTime, int[] endTime, int eventTime, int i) {
            int left = (i == 0) ? 0 : endTime[i - 1];
            int right = (i == startTime.length - 1) ? eventTime : startTime[i + 1];
            return right - left;
        }

        public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
            int n = startTime.length;

            boolean[] canRemove = new boolean[n];

            int maxGap = 0;
            for (int i = 0; i < n; i++) {
                int dur = endTime[i] - startTime[i];
                if (dur <= maxGap) {
                    canRemove[i] = true;
                }
                maxGap = Math.max(maxGap, i == 0 ? startTime[i] : startTime[i] - endTime[i - 1]);
            }

            maxGap = 0;
            for (int i = n - 1; i >= 0; i--) {
                int dur = endTime[i] - startTime[i];
                if (dur <= maxGap) {
                    canRemove[i] = true;
                }
                maxGap = Math.max(maxGap, i == n - 1 ? eventTime - endTime[i] : startTime[i + 1] - endTime[i]);
            }

            int res = 0;
            for (int i = 0; i < n; i++) {
                int dur = endTime[i] - startTime[i];
                int gap = getGap(startTime, endTime, eventTime, i);
                if (canRemove[i]) {
                    res = Math.max(res, gap);
                } else {
                    res = Math.max(res, gap - dur);
                }
            }

            return res;
        }
    }
}
