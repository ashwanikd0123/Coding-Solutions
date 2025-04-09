import java.util.ArrayList;

public class ArticulationPoints {
    class Solution {
        static int[] degree;
        static int[] time;
        static int[] lowTime;
        static ArrayList<Integer>[] adj;
        static ArrayList<Integer> res;
        static int timer;

        static void dfs(boolean[] visited, int node, int parent) {
            if (visited[node]) {
                return;
            }

            visited[node] = true;
            time[node] = lowTime[node] = timer++;

            boolean isArticulationPoint = false;
            int childCount = 0;

            for (int x : adj[node]) {
                if (x == parent) {
                    continue;
                }

                if (visited[x]) {
                    lowTime[node] = Math.min(lowTime[node], time[x]);
                    continue;
                }

                dfs(visited, x, node);

                lowTime[node] = Math.min(lowTime[node], lowTime[x]);

                if (lowTime[x] >= time[node] && parent != -1) {
                    isArticulationPoint = true;
                }

                childCount++;
            }

            if ((parent == -1 && childCount > 1) || (parent != -1 && isArticulationPoint)) {
                res.add(node);
            }
        }


        static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
            degree  = new int[V];
            time    = new int[V];
            lowTime = new int[V];

            adj = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new ArrayList<Integer>();
            }

            for (int[] e : edges) {

                if (adj[e[0]].contains(e[1])) {
                    continue;
                }

                adj[e[0]].add(e[1]);
                adj[e[1]].add(e[0]);
                degree[e[0]]++;
                degree[e[1]]++;
            }

            boolean[] visited = new boolean[V];

            res = new ArrayList<Integer>();

            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    timer = 0;
                    dfs(visited, i, -1);
                }
            }

            if (res.isEmpty()) {
                res.add(-1);
            }

            return res;
        }
    }
}
