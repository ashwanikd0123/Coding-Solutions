import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class EvaluateDivision {
    class Solution {

        private static final double INVALID = -1.0;

        double dfs(HashMap<String, HashMap<String, Double>> adj, HashSet<String> visited,
                   String node, String target, double value) {
            if (!adj.containsKey(node)) {
                return INVALID;
            }

            if (adj.get(node).containsKey(target)) {
                return value * adj.get(node).get(target);
            }

            visited.add(node);
            for (Map.Entry<String, Double> e : adj.get(node).entrySet()) {
                if (visited.contains(e.getKey())) {
                    continue;
                }
                double val = dfs(adj, visited, e.getKey(), target, e.getValue() * value);
                if (val != INVALID) {
                    return val;
                }
            }

            return INVALID;
        }

        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            HashMap<String, HashMap<String, Double>> adj = new HashMap<>();
            for (int i = 0; i < values.length; i++) {
                String num = equations.get(i).get(0);
                String den = equations.get(i).get(1);

                adj.computeIfAbsent(num, k -> new HashMap<>()).put(den, values[i]);
                adj.computeIfAbsent(den, k -> new HashMap<>()).put(num, 1.0 / values[i]);
            }

            double[] res = new double[queries.size()];
            for (int i = 0; i < queries.size(); i++) {
                String num = queries.get(i).get(0);
                String den = queries.get(i).get(1);

                res[i] = dfs(adj, new HashSet<>(), num, den, 1.0);
            }

            return res;
        }
    }
}
