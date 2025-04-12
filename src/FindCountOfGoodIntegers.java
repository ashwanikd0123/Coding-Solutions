import java.util.Arrays;
import java.util.HashSet;

public class FindCountOfGoodIntegers {
    class Solution {

        static int[] getFreq(String num) {
            int[] res = new int[10];
            for (char c : num.toCharArray()) {
                res[c - '0']++;
            }
            return res;
        }

        public long countGoodIntegers(int n, int k) {
            HashSet<String> candidates = new HashSet<>();

            int d = (n + 1) / 2;
            int start = (int) Math.pow(10, d - 1);
            int end = (int) Math.pow(10, d) - 1;


            for (int x = start; x <= end; x++) {
                String left = Integer.toString(x);
                String num = "";
                if (n % 2 == 0) {
                    num = new StringBuilder(left).append(new StringBuilder(left).reverse()).toString();
                } else {
                    num = new StringBuilder(left).append(new StringBuilder(left.substring(0, d - 1)).reverse()).toString();
                }
                long value = Long.parseLong(num);
                if (value % k == 0) {
                    char[] s = num.toCharArray();
                    Arrays.sort(s);
                    candidates.add(new String(s));
                }
            }

            long[] fact = new long[n + 1];
            fact[0] = 1;
            for (int i = 1; i <= n; i++) {
                fact[i] = fact[i - 1] * i;
            }

            long res = 0L;
            for (String candidate : candidates) {
                int[] freq = getFreq(candidate);
                long nonZeroCount = candidate.length() - freq[0];
                long permCount = nonZeroCount * fact[candidate.length() - 1];
                for (int i = 0; i < 10; i++) {
                    permCount /= fact[freq[i]];
                }
                res += permCount;
            }

            return res;
        }
    }
}
