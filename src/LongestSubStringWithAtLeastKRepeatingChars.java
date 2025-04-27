public class LongestSubStringWithAtLeastKRepeatingChars {
    class Solution {

        int ls(char[] s, int start, int end, int k) {
            if (end >= s.length || start < 0 || end - start + 1 < k) {
                return 0;
            }

            int[] freq = new int[26];
            for (int i = start; i <= end; i++) {
                freq[s[i] - 'a']++;
            }

            for (int i = start; i <= end; i++) {
                if (freq[s[i] - 'a'] < k) {
                    int j = i;
                    while (j <= end && freq[s[j] - 'a'] < k) {
                        j++;
                    }
                    return Math.max(ls(s, start, i - 1, k), ls(s, j, end, k));
                }
            }

            return end - start + 1;
        }

        public int longestSubstring(String s, int k) {
            int n = s.length();
            return ls(s.toCharArray(), 0, n - 1, k);
        }
    }
}
