import java.util.*;

public class GrahamScan {
    class Solution {

        static class Point {
            int x;
            int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Point point = (Point) o;
                return x == point.x && y == point.y;
            }

            @Override
            public int hashCode() {
                return Objects.hash(x, y);
            }
        }

        int orientation(Point p1, Point p2, Point p3) {
            return (p2.y - p1.y) * (p3.x - p2.x) - (p2.x - p1.x) * (p3.y - p2.y);
        }

        public int[][] outerTrees(int[][] trees) {
            int n = trees.length;
            if (n <= 1) {
                return trees;
            }

            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                points[i] = new Point(trees[i][0], trees[i][1]);
            }

            Arrays.sort(points, (a, b) -> {
                if (a.x == b.x) {
                    return a.y - b.y;
                }
                return a.x - b.x;
            });

            List<Point> hull = new ArrayList<>();

            // Lower hull
            for (Point p : points) {
                while (hull.size() >= 2 && orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), p) < 0) {
                    hull.remove(hull.size() - 1);
                }
                hull.add(p);
            }

            // Upper hull
            for (int i = points.length - 2, t = hull.size() + 1; i >= 0; i--) {
                Point p = points[i];
                while (hull.size() >= t && orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), p) < 0) {
                    hull.remove(hull.size() - 1);
                }
                hull.add(p);
            }

            // Remove duplicate points (start and end)
            HashSet<Point> set = new HashSet<>(hull);

            // Convert to int[][]
            int[][] res = new int[set.size()][2];
            int i = 0;
            for (Point p : set) {
                res[i][0] = p.x;
                res[i][1] = p.y;
                i++;
            }

            return res;
        }
    }
}
