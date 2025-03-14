import java.util.*;

public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, ArrayList<String>> adj = new HashMap<>();
        int w = beginWord.length();
        String key;

        for (String x : wordList) {
            for (int i = 0; i < w; i++) {
                key = x.substring(0, i) + "-" + x.substring(i + 1);
                adj.computeIfAbsent(key, _ -> new ArrayList<String>()).add(x);
            }
        }

        HashSet<String> visited = new HashSet<>();

        Queue<String> q = new LinkedList<String>();
        q.add(beginWord);
        visited.add(beginWord);

        int depth = 1;

        while (!q.isEmpty()) {
            int n = q.size();

            while (n > 0) {
                String x = q.poll();

                if (x.equals(endWord)) {
                    return depth;
                }

                for (int i = 0; i < w; i++) {
                    key = x.substring(0, i) + "-" + x.substring(i + 1);
                    if (adj.containsKey(key)) {
                        for (String s : adj.get(key)) {
                            if (!visited.contains(s)) {
                                q.add(s);
                                visited.add(s);
                            }
                        }
                    }
                }

                n--;
            }

            depth++;
        }

        return 0;
    }
}