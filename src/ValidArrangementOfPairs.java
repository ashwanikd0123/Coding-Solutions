import java.util.*;

public class ValidArrangementOfPairs {
    class Solution {
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>();
        HashMap<Integer, Integer> indegree = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> outdegree = new HashMap<Integer, Integer>();

        void addNode(int u) {
            if (!adj.containsKey(u)) {
                adj.put(u, new ArrayList<Integer>());
                indegree.put(u, 0);
                outdegree.put(u, 0);
            }
        }

        public int[][] validArrangement(int[][] pairs) {
            adj.clear();
            indegree.clear();
            outdegree.clear();

            for (int[] p : pairs) {
                int u = p[0];
                int v = p[1];
                addNode(u);
                addNode(v);

                adj.get(u).add(v);
                indegree.put(v, indegree.get(v) + 1);
                outdegree.put(u, outdegree.get(u) + 1);
            }

            int startNode = pairs[0][0];
            for (Map.Entry<Integer, ArrayList<Integer>> e : adj.entrySet()) {
                int x = e.getKey();
                if (outdegree.get(x) - indegree.get(x) == 1) {
                    startNode = x;
                    break;
                }
            }

            ArrayList<Integer> eulerPath = new ArrayList<Integer>();
            Stack<Integer> s = new Stack<Integer>();
            s.push(startNode);

            ArrayList<Integer> temp;
            while (!s.isEmpty()) {
                int cur = s.peek();
                temp = adj.get(cur);
                if (!temp.isEmpty()) {
                    int nbr = temp.get(temp.size() - 1);
                    temp.remove(temp.size() - 1);
                    s.push(nbr);
                } else {
                    eulerPath.add(cur);
                    s.pop();
                }
            }

            Collections.reverse(eulerPath);

            int[][] res = new int[pairs.length][2];
            int k = 0;
            for (int i = 0; i < eulerPath.size() - 1; i++) {
                res[k][0] = eulerPath.get(i);
                res[k][1] = eulerPath.get(i + 1);
                k++;
            }

            return res;
        }
    }
}
