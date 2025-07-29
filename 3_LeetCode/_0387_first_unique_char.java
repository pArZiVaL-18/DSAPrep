public class _0387_first_unique_char {
    public static int firstUniqChar(String s) {
        int[] freq = new int[26];

        // First pass: count frequency of each character
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Second pass: find the first character with frequency 1
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }

    public static void runTests() {
        String[] testStrings = {
                "leetcode", // Expected: 0
                "loveleetcode", // Expected: 2
                "aabb", // Expected: -1
                "abcdabc", // Expected: 3
                "", // Expected: -1
                "x", // Expected: 0
                "aaabcccdeeef", // Expected: 9 (f)
        };

        for (String s : testStrings) {
            int index = firstUniqChar(s);
            System.out.println("Input: \"" + s + "\" → First Unique Index: " + index);
        }
    }

    public static void main(String[] args) {
        runTests();
    }
}
