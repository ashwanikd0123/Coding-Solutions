public class MultiplyTwoStrings {
    class Solution {
        String trimZerosAndSign(String val) {
            int i = 0;
            if (val.charAt(0) == '-' || val.charAt(0) == '+') {
                i++;
            }
            while (i < val.length() && val.charAt(i) == '0') {
                i++;
            }
            if (i < val.length()) {
                return val.substring(i);
            }
            return "";
        }

        String multiply(String val, char m, int trailZeroCount) {
            if (m == '0') return "0";
            int mul = m - '0';

            StringBuilder res = new StringBuilder();
            int carry = 0;
            for (int i = val.length() - 1; i >= 0; i--) {
                int cur = mul * (val.charAt(i) - '0') + carry;
                res.append(cur % 10);
                carry = cur / 10;
            }
            if (carry > 0) {
                res.append(carry);
            }
            res.reverse();

            for (int i = 0; i < trailZeroCount; i++) {
                res.append('0');
            }

            return res.toString();
        }

        String add(String a, String b) {
            int carry = 0;
            StringBuilder res = new StringBuilder();
            int i = a.length() - 1;
            int j = b.length() - 1;

            while (i >= 0 || j >= 0 || carry > 0) {
                int val = carry;
                if (i >= 0) {
                    val += a.charAt(i) - '0';
                }
                if (j >= 0) {
                    val += b.charAt(j) - '0';
                }

                res.append(val % 10);
                carry = val / 10;

                i--;
                j--;
            }
            res.reverse();

            return res.toString();
        }

        public String multiplyStrings(String s1, String s2) {
            boolean neg = s1.charAt(0) == '-' ^ s2.charAt(0) == '-';

            s1 = trimZerosAndSign(s1);
            s2 = trimZerosAndSign(s2);
            if (s1.isEmpty() || s2.isEmpty()) {
                return "0";
            }

            int trailZeroCount = s2.length() - 1;
            String res = "0";
            for (char c : s2.toCharArray()) {
                res = add(res, multiply(s1, c, trailZeroCount));
                trailZeroCount--;
            }

            if (neg) {
                res = "-" + res;
            }

            return res;
        }
    }
}
