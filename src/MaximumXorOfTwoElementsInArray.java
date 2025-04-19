public class MaximumXorOfTwoElementsInArray {
    class Solution {
        static class TrieNode {
            TrieNode zero;
            TrieNode one;
        }

        void insert(TrieNode root, int val) {
            for (int i = 31; i >= 0; i--) {
                int bit = (val >> i) & 1;
                if (bit == 1) {
                    if (root.one == null) {
                        root.one = new TrieNode();
                    }
                    root = root.one;
                } else {
                    if (root.zero == null) {
                        root.zero = new TrieNode();
                    }
                    root = root.zero;
                }
            }
        }

        int findOp(TrieNode root, int val) {
            int res = 0;
            for (int i = 31; i >= 0; i--) {
                res <<= 1;
                int bit = (val >> i) & 1;
                if (bit == 0) {
                    if (root.one != null) {
                        root = root.one;
                        res |= 1;
                    } else {
                        root = root.zero;
                        res |= 0;
                    }
                } else {
                    if (root.zero != null) {
                        root = root.zero;
                        res |= 0;
                    } else {
                        root = root.one;
                        res |= 1;
                    }
                }
            }

            return res;
        }

        public int findMaximumXOR(int[] nums) {
            TrieNode root = new TrieNode();
            for (int val : nums) {
                insert(root, val);
            }

            int res = 0;
            for (int val : nums) {
                int maxXorVal = findOp(root, val);
                res = Math.max(res, val ^ maxXorVal);
            }

            return res;
        }
    }
}
