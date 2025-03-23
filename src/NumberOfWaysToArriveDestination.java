import java.util.Arrays;
import java.util.PriorityQueue;

public class NumberOfWaysToArriveDestination {
    static final long MOD = 1000000007;
    class DijkstraSolution {

        int n;
        long[][] graph;

        public int countPaths(int n, int[][] roads) {
            this.n = n;
            graph = new long[n][n];
            for (long[] a : graph) {
                Arrays.fill(a, -1);
            }

            for (int[] e : roads) {
                graph[e[0]][e[1]] = graph[e[1]][e[0]] = e[2];
            }

            long[] dist = new long[n];
            long[] count = new long[n];
            Arrays.fill(dist, Long.MAX_VALUE);

            PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> (int) (a[1] - b[1]));
            dist[0] = 0;
            count[0] = 1;
            pq.add(new long[]{0, 0});

            while (!pq.isEmpty()) {
                long[] node = pq.poll();

                int curNode = (int)node[0];
                long curDist = node[1];

                if (curDist > dist[curNode]) {
                    // nodes with larger edge value might be present when running dijkstra
                    // reaching from older nodes
                    continue;
                }

                for (int i = 0; i < n; i++) {
                    if (graph[curNode][i] != -1) {
                        if (curDist + graph[curNode][i] < dist[i]) {
                            dist[i] = dist[curNode] + graph[curNode][i];
                            count[i] = count[curNode];
                            pq.add(new long[]{i, dist[i]});
                        } else if (curDist + graph[curNode][i] == dist[i]) {
                            count[i] = (count[i] + count[curNode]) % MOD;
                        }
                    }
                }
            }

            return (int) (count[n - 1] % MOD);
        }
    }

    class DijkstraAndDFSSolution {
        int n;
        long[][] graph;
        long maxDist;
        long[] dp;

        long shortestDist(int n) {
            long[] dist = new long[n];
            Arrays.fill(dist, Long.MAX_VALUE);

            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (int) (dist[a] - dist[b]));
            dist[0] = 0;
            pq.add(0);

            while (!pq.isEmpty()) {
                int node = pq.poll();
                for (int i = 0; i < n; i++) {
                    if (graph[node][i] != -1) {
                        if (dist[node] + graph[node][i] < dist[i]) {
                            dist[i] = dist[node] + graph[node][i];
                            pq.add(i);
                        }
                    }
                }
            }

            // remove edges which do not contribute to the shortest paths
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] != -1) {
                        if (dist[i] + graph[i][j] != dist[j]) {
                            graph[i][j] = -1;
                        }
                    }
                }
            }

            return dist[n - 1];

        }

        long dfs(int cur, boolean[] visited, long dist) {
            if (dist > maxDist) {
                return 0;
            }

            if (cur == n - 1) {
                return 1;
            }

            if (dp[cur] >= 0) {
                return dp[cur];
            }

            visited[cur] = true;

            long res = 0;
            for (int i = 0; i < n; i++) {
                if (graph[cur][i] != -1 && !visited[i]) {
                    res = (res + dfs(i, visited, dist + graph[cur][i])) % MOD;
                }
            }

            visited[cur] = false;

            return dp[cur] = res;
        }

        public int countPaths(int n, int[][] roads) {
            this.n = n;
            graph = new long[n][n];
            for (long[] a : graph) {
                Arrays.fill(a, -1);
            }

            for (int[] e : roads) {
                graph[e[0]][e[1]] = graph[e[1]][e[0]] = e[2];
            }

            maxDist = shortestDist(n);
            dp = new long[n];
            Arrays.fill(dp, -1);

            long res = dfs(0, new boolean[n], 0);
            return (int) (res % MOD);

        }
    }

}
