import java.util.HashMap;
import java.util.Map;

public class RabbitsInForest {
    class Solution {
        public int numRabbits(int[] answers) {
            HashMap<Integer, Integer> ansCount = new HashMap<>();
            for (int ans : answers) {
                ansCount.put(ans, ansCount.getOrDefault(ans, 0) + 1);
            }

            int res = 0;
            for (Map.Entry<Integer, Integer> e : ansCount.entrySet()) {
                int ans = e.getKey();
                int count = e.getValue();
                int groupsCount = (int) Math.ceil(count / (double) (ans + 1));
                res += groupsCount * (ans + 1);
            }

            return res;
        }
    }
}
