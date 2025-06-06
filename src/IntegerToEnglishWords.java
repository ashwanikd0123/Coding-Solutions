import java.util.HashMap;

public class IntegerToEnglishWords {
    class Solution {
        private static final HashMap<Integer, String> wordMap;
        private static final HashMap<Integer, String> powerMap;

        static {
            wordMap = new HashMap<>();
            wordMap.put(0, "Zero");
            wordMap.put(1, "One");
            wordMap.put(2, "Two");
            wordMap.put(3, "Three");
            wordMap.put(4, "Four");
            wordMap.put(5, "Five");
            wordMap.put(6, "Six");
            wordMap.put(7, "Seven");
            wordMap.put(8, "Eight");
            wordMap.put(9, "Nine");

            wordMap.put(10, "Ten");
            wordMap.put(11, "Eleven");
            wordMap.put(12, "Twelve");
            wordMap.put(13, "Thirteen");
            wordMap.put(14, "Fourteen");
            wordMap.put(15, "Fifteen");
            wordMap.put(16, "Sixteen");
            wordMap.put(17, "Seventeen");
            wordMap.put(18, "Eighteen");
            wordMap.put(19, "Nineteen");


            wordMap.put(20, "Twenty");
            wordMap.put(30, "Thirty");
            wordMap.put(40, "Forty");
            wordMap.put(50, "Fifty");
            wordMap.put(60, "Sixty");
            wordMap.put(70, "Seventy");
            wordMap.put(80, "Eighty");
            wordMap.put(90, "Ninety");

            powerMap = new HashMap<>();
            powerMap.put(2, "Hundred");
            powerMap.put(3, "Thousand");
            powerMap.put(6, "Million");
            powerMap.put(9, "Billion");
            powerMap.put(12, "Trillion");
            powerMap.put(15, "Quadrillion");
            powerMap.put(18, "Quintillion");
            powerMap.put(21, "Sextillion");
        }

        public String numberToWords(int num) {
            if (num == 0) {
                return wordMap.get(0);
            }

            String number = Integer.toString(num);
            int maxPower = 3 * (number.length() / 3);
            int lastLen = number.length() % 3;
            StringBuilder res = new StringBuilder();

            int idx = 0;

            if (lastLen == 2) {
                if (number.charAt(0) == '1') {
                    int x = 10 + (number.charAt(1) - '0');
                    res.append(wordMap.get(x)).append(" ");
                } else {
                    res.append(wordMap.get(10 * (number.charAt(0) - '0'))).append(" ");
                    if (!(number.charAt(lastLen - 1) == '0')) {
                        res.append(wordMap.get(number.charAt(lastLen - 1) - '0')).append(" ");
                    }
                }
                idx = 2;
                if (idx < number.length()) {
                    res.append(powerMap.get(maxPower)).append(" ");
                }
            } else if (lastLen == 1) {
                res.append(wordMap.get(number.charAt(0) - '0')).append(" ");
                idx = 1;
                if (idx < number.length()) {
                    res.append(powerMap.get(maxPower)).append(" ");
                }
            }

            while (maxPower > 0) {
                boolean powerEntered = false;
                if (number.charAt(idx) != '0') {
                    res.append(wordMap.get(number.charAt(idx) - '0')).append(" ");
                    res.append(powerMap.get(2)).append(" ");
                    powerEntered = true;
                }
                idx++;
                if (number.charAt(idx) == '1') {
                    idx++;
                    int x = 10 + (number.charAt(idx) - '0');
                    res.append(wordMap.get(x)).append(" ");
                    idx++;
                    powerEntered = true;
                } else {
                    if (number.charAt(idx) != '0') {
                        res.append(wordMap.get(10 * (number.charAt(idx) - '0'))).append(" ");
                        idx++;
                        if (!(number.charAt(idx) == '0')) {
                            res.append(wordMap.get(number.charAt(idx) - '0')).append(" ");
                        }
                        idx++;
                        powerEntered = true;
                    } else {
                        idx++;
                        if (!(number.charAt(idx) == '0')) {
                            res.append(wordMap.get(number.charAt(idx) - '0')).append(" ");
                            powerEntered = true;
                        }
                        idx++;
                    }
                }
                maxPower -= 3;
                if (maxPower > 0 && powerEntered) {
                    res.append(powerMap.get(maxPower)).append(" ");
                }
            }

            res.deleteCharAt(res.length() - 1);

            return res.toString();
        }
    }
}
