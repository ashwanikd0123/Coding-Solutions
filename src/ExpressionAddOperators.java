import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {

    class Solution {

        ArrayList<String> res;

        void solve(String num, ArrayList<String> dq, long value, long target) {
            if (value == target && num.isEmpty()) {
                StringBuilder exp = new StringBuilder();
                for (String x : dq) {
                    exp.append(x);
                }
                res.add(exp.toString());
                return;
            }

            if (num.isEmpty()) {
                return;
            }

            int maxLen = num.charAt(0) == '0' ? 1 : num.length();

            if (dq.isEmpty()) {
                for (int i = 1; i <= maxLen; i++) {
                    String e = num.substring(0, i);
                    long val = Long.parseLong(e);
                    dq.addLast(e);
                    solve(num.substring(i), dq, val, target);
                    dq.removeLast();
                }
            } else {
                // +
                for (int i = 1; i <= maxLen; i++) {
                    String e = num.substring(0, i);
                    long val = value + Long.parseLong(e);
                    dq.addLast("+");
                    dq.addLast(e);
                    solve(num.substring(i), dq, val, target);
                    dq.removeLast();
                    dq.removeLast();
                }

                // -
                for (int i = 1; i <= maxLen; i++) {
                    String e = num.substring(0, i);
                    long val = value - Long.parseLong(e);
                    dq.addLast("-");
                    dq.addLast(e);
                    solve(num.substring(i), dq, val, target);
                    dq.removeLast();
                    dq.removeLast();
                }

                // *
                for (int i = 1; i <= maxLen; i++) {
                    String e = num.substring(0, i);
                    long val = multiplicationValue(dq, value, e);
                    dq.addLast("*");
                    dq.addLast(e);
                    solve(num.substring(i), dq, val, target);
                    dq.removeLast();
                    dq.removeLast();
                }
            }
        }

        private static long multiplicationValue(ArrayList<String> dq, long value, String e) {
            int j = dq.size() - 1;
            long val = Long.parseLong(dq.get(j));
            while (j >= 1 && dq.get(j - 1).equals("*")) {
                val *= Long.parseLong(dq.get(j - 2));
                j -= 2;
            }
            if (j <= 0) {
                val = val * Long.parseLong(e);
            } else {
                if (dq.get(j - 1).equals("+")) {
                    val = (value - val) + (val * Long.parseLong(e));
                } else {
                    val = (value + val) - (val * Long.parseLong(e));
                }
            }
            return val;
        }

        public List<String> addOperators(String num, int target) {
            res = new ArrayList<>();
            solve(num, new ArrayList<>(), 0, target);
            return res;
        }
    }
}
