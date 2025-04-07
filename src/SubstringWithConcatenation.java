import java.util.*;

public class SubstringWithConcatenation {
    class Solution {

        static class Node {
            String val;
            int idx;
            Node(String val, int idx) {
                this.val = val;
                this.idx = idx;
            }
        }

        public List<Integer> findSubstring(String s, String[] words) {
            HashMap<String, Integer> wordCount = new HashMap<>();
            HashSet<String> wordSet = new HashSet<>();
            for (String w : words) {
                wordCount.put(w, wordCount.getOrDefault(w, 0) + 1);
                wordSet.add(w);
            }

            int wordLen = words[0].length();
            int patLength = words.length * wordLen;

            HashMap<String, Integer>[] idxWordCount = new HashMap[wordLen];
            ArrayDeque<Node>[] dq = new ArrayDeque[wordLen];
            int[] uniqueWordCount = new int[wordLen];
            for (int i = 0; i < wordLen; i++) {
                idxWordCount[i] = (HashMap<String, Integer>) wordCount.clone();
                dq[i] = new ArrayDeque<>();
                uniqueWordCount[i] = wordSet.size();
            }

            ArrayList<Integer> res = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                int idx = i % wordLen;
                if (i >= wordLen - 1) {
                    String lastWord = s.substring(i - wordLen + 1, i + 1);
                    if (wordSet.contains(lastWord)) {
                        dq[idx].add(new Node(lastWord, i - wordLen + 1));
                        idxWordCount[idx].put(lastWord, idxWordCount[idx].get(lastWord) - 1);
                        if (idxWordCount[idx].get(lastWord) == 0) {
                            uniqueWordCount[idx]--;
                        }
                        while (idxWordCount[idx].get(lastWord) < 0) {
                            String x = dq[idx].removeFirst().val;
                            idxWordCount[idx].put(x, idxWordCount[idx].get(x) + 1);
                            if (idxWordCount[idx].get(x) == 1) {
                                uniqueWordCount[idx]++;
                            }
                        }
                    }
                }

                if (i >= patLength - 1) {
                    int start = i - patLength + 1;
                    while (!dq[idx].isEmpty() && dq[idx].getFirst().idx < start) {
                        String x = dq[idx].removeFirst().val;
                        idxWordCount[idx].put(x, idxWordCount[idx].get(x) + 1);
                        if (idxWordCount[idx].get(x) == 1) {
                            uniqueWordCount[idx]++;
                        }
                    }
                    if (uniqueWordCount[idx] == 0) {
                        res.add(start);
                    }
                }
            }

            return res;
        }
    }
}
