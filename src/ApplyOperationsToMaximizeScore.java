import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class ApplyOperationsToMaximizeScore {

    class Solution {
        private static final long MOD = (long) (1e9 + 7);

        private static int getScore(int x) {
            int res = 0;
            for (int i = 2; i * i <= x; i++) {
                if (x % i == 0) {
                    res++;
                    while (x % i == 0) {
                        x /= i;
                    }
                }
            }
            return x > 1 ? res + 1: res;
        }

        private static int[] convertListToArray(List<Integer> nums) {
            int[] res = new int[nums.size()];
            int i = 0;
            for (int x : nums) {
                res[i++] = x;
            }
            return res;
        }

        private static long pow(long x, long y) {
            long ans = 1;
            while (y > 0) {
                if ((y & 1) == 1) {
                    ans = ans * x % MOD;
                }
                y >>= 1;
                x = x * x % MOD;
            }
            return ans;
        }

        public int maximumScore(List<Integer> numsList, long k) {
            int n = numsList.size();
            int[] nums = convertListToArray(numsList);
            int[] score = new int[n];
            for (int i = 0; i < n; i++) {
                score[i] = getScore(nums[i]);
            }

            Stack<Integer> stack = new Stack<>();

            int[] left = new int[n];
            for (int i = 0; i < n; i++) {
                while (!stack.isEmpty() && score[stack.peek()] < score[i]) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    left[i] = -1;
                } else {
                    left[i] = stack.peek();
                }
                stack.push(i);
            }

            stack.clear();
            int[] right = new int[n];
            for (int i = n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && score[stack.peek()] <= score[i]) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    right[i] = n;
                } else {
                    right[i] = stack.peek();
                }
                stack.push(i);
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> nums[b]- nums[a]);
            for (int i = 0; i < n; i++) {
                pq.add(i);
            }

            long finalScore = 1;
            while (!pq.isEmpty()) {
                int i = pq.poll();
                long leftCount = i - left[i];
                long rightCount = right[i] - i;
                long maxPossibleCount = leftCount * rightCount;
                if (maxPossibleCount < k) {
                    finalScore = (finalScore * pow(nums[i], maxPossibleCount)) % MOD;
                    k -= maxPossibleCount;
                } else {
                    finalScore = (finalScore * pow(nums[i], k)) % MOD;
                    break;
                }
            }

            return (int) finalScore;
        }
    }
}
