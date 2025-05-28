import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MaximizeTargetNodesByConnectingTrees {
    class Solution {
        int getCount(ArrayList<Integer>[] adj, int node, int k) {
            int parent = -1;
            Queue<int[]> q = new LinkedList<int[]>();
            q.add(new int[]{node, parent});

            int res = 0;
            while (k >= 0) {
                int n = q.size();
                res += n;

                while (n > 0) {
                    int[] cur = q.poll();
                    node = cur[0];
                    parent = cur[1];

                    for (int x : adj[node]) {
                        if (x == parent) {
                            continue;
                        }
                        q.add(new int[]{x, node});
                    }

                    n--;
                }

                k--;
            }

            return res;
        }

        public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
            int n = edges1.length + 1;
            int m = edges2.length + 1;

            ArrayList<Integer>[] adj1 = new ArrayList[n];
            ArrayList<Integer>[] adj2 = new ArrayList[m];

            for (int i = 0; i < n; i++) {
                adj1[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                adj2[i] = new ArrayList<>();
            }

            for (int[] e : edges1) {
                adj1[e[0]].add(e[1]);
                adj1[e[1]].add(e[0]);
            }

            for (int[] e : edges2) {
                adj2[e[0]].add(e[1]);
                adj2[e[1]].add(e[0]);
            }

            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                res[i] = getCount(adj1, i, k);
            }

            int max = Integer.MIN_VALUE;
            for (int i = 0; i < m; i++) {
                max = Math.max(max, getCount(adj2, i, k - 1));
            }

            for (int i = 0; i < n; i++) {
                res[i] += max;
            }

            return res;
        }
    }
}
