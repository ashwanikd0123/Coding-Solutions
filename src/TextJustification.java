import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    class Solution {
        String justify(String[] words, int i, int j, int charSize, int maxLength) {
            StringBuilder res = new StringBuilder();

            if (i == j) {
                res.append(words[i]);
                res.append(" ".repeat(maxLength - res.length()));
                return res.toString();
            }

            if (j == words.length - 1) {
                while (i < j) {
                    res.append(words[i]);
                    res.append(" ");
                    i++;
                }
                res.append(words[j]);
            } else {
                int spaces = maxLength - charSize;
                int division;
                int dist = j - i;

                while (i < j) {
                    res.append(words[i]);
                    if (spaces % dist == 0) {
                        division = spaces / dist;
                    } else {
                        division = spaces / dist + 1;
                    }
                    res.append(" ".repeat(division));
                    spaces -= division;
                    dist--;
                    i++;
                }

                res.append(words[j]);
            }

            res.append(" ".repeat(maxLength - res.length()));

            return res.toString();
        }

        public List<String> fullJustify(String[] words, int maxWidth) {
            int n = words.length;
            ArrayList<String> res = new ArrayList<>();

            int i = 0;
            int j = i;
            while (i < n) {
                j = i;
                int curSize = words[j].length();

                j++;
                while (j < n && curSize + 1 + words[j].length() <= maxWidth) {
                    curSize = curSize + 1 + words[j].length();
                    j++;
                }
                j--;

                res.add(justify(words, i, j, curSize - (j - i), maxWidth));

                i = j + 1;
            }

            return res;
        }
    }
}
