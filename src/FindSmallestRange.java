import java.util.ArrayList;

public class FindSmallestRange {
    class Solution {
        public ArrayList<Integer> findSmallestRange(int[][] arr) {
            int k = arr.length;
            int n = arr[0].length;

            int min = 0;
            int max = Integer.MAX_VALUE;

            int[] idxs = new int[k];

            boolean allListsHaveElements = true;
            while (allListsHaveElements) {
                int minIndex = -1;
                int curMin = Integer.MAX_VALUE;
                int curMax = Integer.MIN_VALUE;

                for (int i = 0; i < k; i++) {
                    if (idxs[i] >= n) {
                        allListsHaveElements = false;
                        break;
                    }

                    int val = arr[i][idxs[i]];
                    if (val > curMax) {
                        curMax = val;
                    }

                    if (val < curMin) {
                        curMin = val;
                        minIndex = i;
                    }
                }

                if (!allListsHaveElements) {
                    break;
                }

                if (curMax - curMin < max - min) {
                    max = curMax;
                    min = curMin;
                }

                idxs[minIndex]++;
            }

            ArrayList<Integer> res = new ArrayList<>();
            res.add(min);
            res.add(max);
            return res;
        }
    }
}
