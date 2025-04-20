import java.util.PriorityQueue;

public class SuperUglyNumber {
    class Solution {
        public int nthSuperUglyNumber(int n, int[] primes) {
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
            for (int x : primes) {
                pq.add(new int[]{x, x, 0});
            }

            int[] nums = new int[n + 1];
            nums[0] = 1;

            int i = 1;
            while (i < n) {
                int[] cur = pq.poll();
                if (cur[0] != nums[i - 1]) {
                    nums[i] = cur[0];
                    i++;
                }
                pq.add(new int[]{cur[1] * nums[cur[2]], cur[1], cur[2] + 1});
            }

            return nums[n - 1];
        }
    }
}
