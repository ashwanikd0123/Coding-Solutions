import java.util.HashMap;

public class RabbitsInForest {
    class Solution {
        public int numRabbits(int[] answers) {
            HashMap<Integer, Integer> map = new HashMap<>();
            int res = 0;
            for (int ans : answers) {
                int count = map.getOrDefault(ans, 0);
                if (count == 0) {
                    res += 1 + ans;
                    map.put(ans, ans);
                } else {
                    map.put(ans, count - 1);
                }
            }
            return res;
        }
    }
}
