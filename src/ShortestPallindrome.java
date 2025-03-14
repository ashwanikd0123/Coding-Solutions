public class ShortestPallindrome {
    class Solution {
        public String shortestPalindrome(String str) {
            StringBuilder sb = new StringBuilder(str);
            StringBuilder rev = new StringBuilder(str).reverse();
            char[] x = sb.append("-").append(rev).toString().toCharArray();

            int len = 0;
            int i = 1;
            int[] lps = new int[x.length];
            while (i < x.length) {
                if (x[i] == x[len]) {
                    len++;
                    lps[i] = len;
                    i++;
                } else {
                    if (len != 0) {
                        len = lps[len - 1];
                    } else {
                        lps[i] = 0;
                        i++;
                    }
                }
            }

            len = lps[x.length - 1];
            return rev.toString().substring(0, str.length() - len) + str;
        }
    }
}
