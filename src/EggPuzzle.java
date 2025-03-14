import java.util.Arrays;

class EggPuzzle {
    static int[][] dp;
    static int eggDrop(int eggCount, int floors) {
        if (floors == 1 || floors == 0)
            return floors;

        if (eggCount == 1)
            return floors;

        if (dp[eggCount][floors] != -1) {
            return dp[eggCount][floors];
        }

        int min = Integer.MAX_VALUE;
        int x, res;

        for (x = 1; x <= floors; x++) {
            res = Math.max(eggDrop(eggCount - 1, x - 1),
                    eggDrop(eggCount, floors - x));
            if (res < min)
                min = res;
        }

        return dp[eggCount][floors] = min + 1;
    }

    public static int twoEggDrop(int n) {
        dp = new int[3][n + 1];
        for (int[] i : dp) {
            Arrays.fill(i, -1);
        }
        return eggDrop(2, n);
    }
}