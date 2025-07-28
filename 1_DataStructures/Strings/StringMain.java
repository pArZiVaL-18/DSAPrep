import java.util.Scanner;

public class StringMain {

    // Reverse a string
    public static String reverseString(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    // Check if a string is a palindrome
    public static boolean isPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    // Count vowels and consonants
    public static void countVowelsAndConsonants(String str) {
        int vowels = 0, consonants = 0;
        str = str.toLowerCase();
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                if ("aeiou".indexOf(c) != -1)
                    vowels++;
                else
                    consonants++;
            }
        }
        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
    }

    // Remove duplicate characters
    public static String removeDuplicates(String str) {
        StringBuilder result = new StringBuilder();
        boolean[] seen = new boolean[256];
        for (char c : str.toCharArray()) {
            if (!seen[c]) {
                seen[c] = true;
                result.append(c);
            }
        }
        return result.toString();
    }

    // Convert to title case
    public static String toTitleCase(String str) {
        String[] words = str.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0)
                result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase())
                        .append(" ");
        }
        return result.toString().trim();
    }

    // Count frequency of each character
    public static void characterFrequency(String str) {
        int[] freq = new int[256];
        for (char c : str.toCharArray()) {
            freq[c]++;
        }
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0)
                System.out.println((char) i + ": " + freq[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = "hello world";

        System.out.println("Original String: " + str);

        System.out.println("\nReversed: " + reverseString(str));

        System.out.println("\nIs Palindrome: " + isPalindrome(str));

        System.out.println("\nVowel and Consonant Count:");
        countVowelsAndConsonants(str);

        System.out.println("\nRemove Duplicates: " + removeDuplicates(str));

        System.out.println("\nTitle Case: " + toTitleCase(str));

        System.out.println("\nCharacter Frequency:");
        characterFrequency(str);

        sc.close();

        /*
         * String Practice Challenges:
         * 1. Find the longest word in a string.
         * 2. Replace all spaces with dashes.
         * 3. Check if two strings are anagrams.
         * 4. Count number of words in the string.
         * 5. Implement a menu-driven program for all operations.
         */
    }
}
