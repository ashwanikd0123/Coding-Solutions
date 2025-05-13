public class TotalCharsInStringAfterTransforms {
    class Solution {
        static final int MOD = (int) 1e9 + 7;
        public int lengthAfterTransformations(String s, int t) {
            int[] freq = new int[26];
            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }

            for (int i = 0; i < t; i++) {
                int[] newFreq = new int[26];
                newFreq[0] = freq[25];
                newFreq[1] = (freq[25] + freq[0]) % MOD;
                for (int j = 2; j < 26; j++) {
                    newFreq[j] = freq[j - 1];
                }
                freq = newFreq;
            }

            int res = 0;
            for (int x : freq) {
                res = (res + x) % MOD;
            }

            return res;
        }
    }
}
