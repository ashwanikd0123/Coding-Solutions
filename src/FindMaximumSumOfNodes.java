public class FindMaximumSumOfNodes {
    class Solution {
        public long maximumValueSum(int[] values, int k, int[][] edges) {
            int count = 0;
            long idealSum = 0;
            long minLoss = Long.MAX_VALUE;
            for (int x : values) {
                int loss = 0;
                if ((x ^ k) > x) {
                    count++;
                    idealSum += x ^ k;
                    loss = (x ^ k) - x;
                } else {
                    idealSum += x;
                    loss = x - (x ^ k);
                }
                minLoss = Math.min(minLoss, loss);
            }
            long res = idealSum;
            if (count % 2 != 0) {
                res -= minLoss;
            }
            return res;
        }
    }
}
