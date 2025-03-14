class MaxProduct {
    static int max(int... a) {
        int max = Integer.MIN_VALUE;
        for (int i : a) {
            max = Math.max(max, i);
        }
        return max;
    }

    static int min(int... a) {
        int min = Integer.MAX_VALUE;
        for (int i : a) {
            min = Math.min(min, i);
        }
        return min;
    }

    static public int maxProduct(int[] nums) {
        int n = nums.length;

        int min = nums[0];
        int max = nums[0];
        int res = nums[0];

        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            int t = max(cur, cur * min, cur * max);
            min = min(cur, cur * min, cur * max);
            max = t;
            res = max(res, max);
        }

        return res;
    }
}