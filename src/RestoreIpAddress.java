import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddress {
    class Solution {
        List<String> res;

        void generateIp(String s, int cur, StringBuilder ip, int depth) {
            if (depth == 0 && cur == s.length()) {
                res.add(ip.toString());
            }

            if (cur >= s.length()) {
                return;
            }

            if (s.charAt(cur) == '0') {
                generateIp(s, cur + 1, new StringBuilder(ip).append(".0"), depth - 1);
            } else {
                generateIp(s, cur + 1, new StringBuilder(ip).append(".").append(s.substring(cur, cur + 1)), depth - 1);
                if (cur + 2 <= s.length()) {
                    generateIp(s, cur + 2, new StringBuilder(ip).append(".").append(s.substring(cur, cur + 2)), depth - 1);
                }
                if (cur + 3 <= s.length() && Integer.parseInt(s.substring(cur, cur + 3)) < 256) {
                    generateIp(s, cur + 3, new StringBuilder(ip).append(".").append(s.substring(cur, cur + 3)), depth - 1);
                }
            }
        }

        public List<String> restoreIpAddresses(String s) {
            res = new ArrayList<String>();
            if (s.length() < 4 || s.length() > 12) {
                return res;
            }
            if (s.charAt(0) == '0') {
                generateIp(s, 1, new StringBuilder("0"), 3);
            } else {
                generateIp(s, 1, new StringBuilder(s.substring(0, 1)), 3);
                generateIp(s, 2, new StringBuilder(s.substring(0, 2)), 3);
                if (Integer.parseInt(s.substring(0, 3)) < 256) {
                    generateIp(s, 3, new StringBuilder(s.substring(0, 3)), 3);
                }
            }
            return res;
        }
    }
}
