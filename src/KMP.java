import java.util.ArrayList;

public class KMP {
    class Solution {

        ArrayList<Integer> search(String pat, String txt) {
            char[] p = pat.toCharArray();
            char[] t = txt.toCharArray();

            int[] lps = new int[p.length];

            int len = 0;
            int i = 1;
            while (i < p.length) {
                if (p[i] == p[len]) {
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

            ArrayList<Integer> res = new ArrayList<Integer>();

            int j = 0;
            i = 0;
            while (i < t.length) {
                if (t[i] == p[j]) {
                    i++;
                    j++;
                }
                if (j == p.length) {
                    res.add(i - p.length);
                    j = lps[p.length - 1];
                } else if (i < t.length && t[i] != p[j]) {
                    if (j != 0) {
                        j = lps[j - 1];
                    } else {
                        i++;
                    }
                }
            }

            return res;
        }
    }
}
