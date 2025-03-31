import java.util.Arrays;

public class PutMarblesInABag {
    class Solution {
        public long putMarbles(int[] weights, int k) {
            if (k == 1) {
                return 0;
            }

            int n = weights.length;

            int[] pairSums = new int[n - 1];

            for (int i = 0; i < weights.length - 1; i++) {
                pairSums[i] = weights[i] + weights[i + 1];
            }

            Arrays.sort(pairSums);

            long minScore = 0, maxScore = 0;
            for (int i = 0; i < k - 1; i++) {
                minScore += pairSums[i];
                maxScore += pairSums[n - 2 - i];
            }

            return maxScore - minScore;
        }
    }
}
