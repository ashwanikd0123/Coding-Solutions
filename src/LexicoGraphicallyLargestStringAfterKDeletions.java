public class LexicoGraphicallyLargestStringAfterKDeletions {
    class Solution {
        public static String maxSubseq(String s, int k) {
            int toRemove = k;
            StringBuilder stack = new StringBuilder();

            for (char c : s.toCharArray()) {
                while (stack.length() > 0 && toRemove > 0 && stack.charAt(stack.length() - 1) < c) {
                    stack.deleteCharAt(stack.length() - 1);
                    toRemove--;
                }
                stack.append(c);
            }

            return stack.substring(0, s.length() - k);
        }
    }
}
