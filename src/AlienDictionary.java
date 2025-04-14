import java.util.*;

public class AlienDictionary {
    class Solution {
        public String findOrder(String[] words) {
            int n = words.length;

            HashSet<Character> present = new HashSet<>();

            for (char c : words[0].toCharArray()) {
                present.add(c);
            }

            HashMap<Character, HashSet<Character>> adj = new HashMap<>();
            for (int i = 0; i < n - 1; i++) {
                for (char c : words[i].toCharArray()) {
                    present.add(c);
                }
                for (char c : words[i + 1].toCharArray()) {
                    present.add(c);
                }
                if (words[i].equals(words[i + 1])) {
                    continue;
                }
                for (int j = 0; j < words[i].length(); j++) {
                    char a = words[i].charAt(j);
                    if (j == words[i + 1].length()) {
                        return "";
                    }
                    char b = words[i + 1].charAt(j);
                    if (a != b) {
                        adj.computeIfAbsent(a, k -> new HashSet<>()).add(b);
                        break;
                    }
                }
            }

            int[] indegree = new int[26];
            for (Map.Entry<Character, HashSet<Character>> e : adj.entrySet()) {
                for (char c : e.getValue()) {
                    indegree[c - 'a']++;
                }
            }

            Queue<Character> q = new LinkedList<Character>();
            for (char c = 'a'; c <= 'z'; c++) {
                if (indegree[c - 'a'] == 0) {
                    q.add(c);
                }
            }

            StringBuilder res = new StringBuilder();
            while (!q.isEmpty()) {
                char c = q.poll();
                if (present.contains(c)) {
                    res.append(c);
                }
                for (char x : adj.getOrDefault(c, new HashSet<>())) {
                    indegree[x - 'a']--;
                    if (indegree[x - 'a'] == 0) {
                        q.add(x);
                    }
                }
            }

            for (int i = 0; i < 26; i++) {
                if (indegree[i] != 0) {
                    return "";
                }
            }

            return res.toString();
        }
    }
}
