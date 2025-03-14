import java.util.Arrays;

public class MaxGap {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int x : nums) {
            max = Math.max(max, x);
            min = Math.min(min, x);
        }

        final int range = max - min;

        if (range == 0) {
            return 0;
        }

        int[] bucketsMin = new int[n];
        int[] bucketsMax = new int[n];

        Arrays.fill(bucketsMin, Integer.MAX_VALUE);
        Arrays.fill(bucketsMax, Integer.MIN_VALUE);

        boolean[] visited = new boolean[n];

        double delta = (double) (max - min) / (double) (n - 1);

        for (int i = 0; i < n; i++) {
            int idx = (int) ((nums[i] - min) / delta);
            bucketsMin[idx] = Math.min(bucketsMin[idx], nums[i]);
            bucketsMax[idx] = Math.max(bucketsMax[idx], nums[i]);
            visited[idx] = true;
        }

        int i = 0;
        int gap = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && !visited[j]) {
                j++;
            }
            if (j < n) {
                gap = Math.max(gap, bucketsMin[j] - bucketsMax[i]);
            }
            i = j;
        }

        return gap;
    }
}