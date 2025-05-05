public class QuadTreeConstruction {
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    class Solution {
        Node construct(int[][] grid, int r1, int r2, int c1, int c2) {
            Node res = new Node();
            if (r1 == r2 && c1 == c2) {
                res.val = grid[r1][c1] == 1;
                res.isLeaf = true;
                return res;
            }

            res.isLeaf = false;
            res.val = true;

            int h = (r2 - r1) / 2;
            res.topLeft = construct(grid, r1, r1 + h, c1, c1 + h);
            res.topRight = construct(grid, r1, r1 + h, c1 + h + 1, c2);
            res.bottomLeft = construct(grid, r1 + h + 1, r2, c1, c1 + h);
            res.bottomRight = construct(grid, r1 + h + 1, r2, c1 + h + 1, c2);

            if (!res.topLeft.val && !res.bottomLeft.val && !res.topRight.val && !res.bottomRight.val) {
                if (res.topLeft.isLeaf && res.bottomLeft.isLeaf && res.topRight.isLeaf && res.bottomRight.isLeaf) {
                    res.val = false;
                    res.topLeft = res.bottomLeft = res.topRight = res.bottomRight = null;
                    res.isLeaf = true;
                }
            } else if (res.topLeft.val && res.bottomLeft.val && res.topRight.val && res.bottomRight.val) {
                if (res.topLeft.isLeaf && res.bottomLeft.isLeaf && res.topRight.isLeaf && res.bottomRight.isLeaf) {
                    res.topLeft = res.bottomLeft = res.topRight = res.bottomRight = null;
                    res.isLeaf = true;
                }
            }

            return res;
        }

        public Node construct(int[][] grid) {
            int n = grid.length;
            return construct(grid, 0 , n - 1, 0, n - 1);
        }
    }
}
