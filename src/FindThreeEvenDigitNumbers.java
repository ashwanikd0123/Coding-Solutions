import java.util.ArrayList;

public class FindThreeEvenDigitNumbers {
    class Solution {

        public int[] findEvenNumbers(int[] digits) {
            int n = digits.length;
            int[] freq = new int[10];
            for (int d : digits) {
                freq[d]++;
            }

            ArrayList<Integer> res = new ArrayList<>();
            for (int i = 1; i <= 9; i++) {
                if (freq[i] > 0) {
                    freq[i]--;
                    for (int j = 0; j <= 9; j++) {
                        if (freq[j] > 0) {
                            freq[j]--;
                            for (int k = 0; k < 10; k += 2) {
                                if (freq[k] > 0) {
                                    res.add(i * 100 + j * 10 + k);
                                }
                            }
                            freq[j]++;
                        }
                    }
                    freq[i]++;
                }
            }

            int[] result = new int[res.size()];
            int i = 0;
            for (int x : res) {
                result[i++] = x;
            }

            return result;
        }
    }
}
