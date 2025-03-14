import java.util.HashMap;

public class InterLeavingStrings {
    HashMap<String, Boolean> dp;

    boolean interleave(String s1, String s2, String s3) {
        String key = s1 + " " + s2;
        if (s3.isEmpty()) {
            return s2.equals(s1);
        }

        if (s1.isEmpty()) {
            return s2.equals(s3);
        }

        if (s2.isEmpty()) {
            return s1.equals(s3);
        }

        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        if (s1.charAt(0) == s3.charAt(0)) {
            if (interleave(s1.substring(1), s2, s3.substring(1))) {
                dp.put(key, true);
                return true;
            }
        }

        if (s2.charAt(0) == s3.charAt(0)) {
            if (interleave(s1, s2.substring(1), s3.substring(1))) {
                dp.put(key, true);
                return true;
            }
        }

        dp.put(key, false);
        return false;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != (s1.length() + s2.length())) {
            return false;
        }

        dp = new HashMap<>();

        return interleave(s1, s2, s3);
    }
}
