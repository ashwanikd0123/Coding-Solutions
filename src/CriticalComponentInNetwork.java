import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriticalComponentInNetwork {
    class Solution {
        ArrayList<List<Integer>> res;
        int[] lowTime;
        int[] time;
        boolean[] visited;

        void tarjan(List<List<Integer>> adj, int t, int node, int parent) {
            visited[node] = true;
            lowTime[node] = time[node] = t;
            for (int x : adj.get(node)) {
                if (x == parent) {
                    continue;
                }

                if (!visited[x]) {
                    tarjan(adj, t + 1, x, node);
                }

                lowTime[node] = Math.min(lowTime[node], lowTime[x]);
                if (lowTime[x] > time[node]) {
                    res.add(Arrays.asList(x, node));
                }
            }
        }

        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            lowTime = new int[n];
            time = new int[n];
            visited = new boolean[n];
            res = new ArrayList<>();

            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }

            for (List<Integer> conn: connections) {
                adj.get(conn.get(0)).add(conn.get(1));
                adj.get(conn.get(1)).add(conn.get(0));
            }

            tarjan(adj, 0, 0, -1);
            return res;
        }
    }
}
