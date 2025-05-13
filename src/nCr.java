public class nCr {
    class Solution {
        public int nCr(int n, int r) {
            long res = 1;
            for (int i = 1; i <= r; i++) {
                res = res * (n - i + 1) / i;
            }
            return (int) res;
        }
    }
}
