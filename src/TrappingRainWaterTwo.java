import java.util.PriorityQueue;

public class TrappingRainWaterTwo {

    class Solution {

        static final int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        public int trapRainWater(int[][] heightMap) {
            int rows = heightMap.length;
            int cols = heightMap[0].length;
            boolean[][] visited = new boolean[rows][cols];
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
            for (int i = 0; i < cols; i++) {
                visited[0][i] = visited[rows - 1][i] = true;
                pq.add(new int[]{0, i, heightMap[0][i]});
                pq.add(new int[]{rows - 1, i, heightMap[rows - 1][i]});
            }
            for (int i = 0; i < rows; i++) {
                visited[i][0] = visited[i][cols - 1] = true;
                pq.add(new int[]{i, 0, heightMap[i][0]});
                pq.add(new int[]{i, cols - 1, heightMap[i][cols - 1]});
            }

            int water = 0;
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int r = cur[0];
                int c = cur[1];
                int h = cur[2];
                for (int[] dir : dirs) {
                    int r1 = r + dir[0];
                    int c1 = c + dir[1];
                    if (r1 < 0 || c1 < 0 || r1 >= rows || c1 >= cols || visited[r1][c1]) {
                        continue;
                    }
                    visited[r1][c1] = true;
                    if (h - heightMap[r1][c1] > 0) {
                        water += h - heightMap[r1][c1];
                    }
                    pq.add(new int[]{r1, c1, Math.max(h, heightMap[r1][c1])});
                }
            }

            return water;
        }
    }
}
