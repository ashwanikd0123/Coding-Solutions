import java.util.Arrays;

public class CelebrityProblem {
    class Solution {
        public int findCelebrity(int n, int[][] trust) {
            boolean[] canBeCelebrity = new boolean[n];
            Arrays.fill(canBeCelebrity, true);
            int[] inDegree = new int[n];

            for (int[] t : trust) {
                canBeCelebrity[t[0] - 1] = false;
                inDegree[t[1] - 1]++;
            }

            for (int i = 0; i < n; i++) {
                if (canBeCelebrity[i] && inDegree[i] == n - 1) {
                    return i + 1;
                }
            }

            return -1;
        }
    }
}
