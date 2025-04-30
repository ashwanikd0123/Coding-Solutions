import java.util.ArrayList;

public class ReorderPathsToMakeAllCitiesLeadToZero {
    class Solution {

        int res;

        void dfs(ArrayList<Integer>[] adj, boolean[] visited, int node) {
            visited[node] = true;

            for (int x : adj[node]) {
                if (!visited[Math.abs(x)]) {
                    if (x > 0) {
                        res++;
                    }
                    dfs(adj, visited, Math.abs(x));
                }
            }
        }

        public int minReorder(int n, int[][] connections) {
            ArrayList<Integer>[] adj = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<>();
            }

            for (int[] e : connections) {
                adj[e[0]].add(e[1]);
                adj[e[1]].add(-e[0]);
            }

            boolean[] visited = new boolean[n];
            res = 0;
            dfs(adj, visited, 0);

            return res;
        }
    }
}
