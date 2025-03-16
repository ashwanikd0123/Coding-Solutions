/**
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 */
public class NumberOfDigitOne {
    class Solution {
        public int countDigitOne(int n) {
            int place = 1;
            int power = 1;
            int res = 0;
            while (place <= 10 && n >= power) {
                int divisor = power * 10;
                res += ((n / divisor) * power) + Math.max(0, Math.min(n % divisor - power + 1, power));
                place++;
                power *= 10;
            }
            return res;
        }
    }
}
