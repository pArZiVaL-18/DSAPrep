import java.util.*;

public class _2965_missing_repeating_value {

    // ✅ 1. Using Hashing - O(n) time, O(n) space
    public static int[] findWithHashing(int[] arr) {
        int n = arr.length;
        int[] freq = new int[n + 1];
        int repeating = -1, missing = -1;

        for (int num : arr) {
            freq[num]++;
        }

        for (int i = 1; i <= n; i++) {
            if (freq[i] == 2) {
                repeating = i;
            } else if (freq[i] == 0) {
                missing = i;
            }
        }

        return new int[] { repeating, missing };
    }

    // ✅ 2. Using Math - O(n) time, O(1) space
    public static int[] findWithMath(int[] arr) {
        int n = arr.length;
        long sumN = (long) n * (n + 1) / 2;
        long sumSqN = (long) n * (n + 1) * (2 * n + 1) / 6;

        long sum = 0, sumSq = 0;
        for (int num : arr) {
            sum += num;
            sumSq += (long) num * num;
        }

        long diff = sum - sumN; // x - y
        long sqDiff = sumSq - sumSqN; // x^2 - y^2

        long sumXY = sqDiff / diff; // x + y

        int repeating = (int) ((diff + sumXY) / 2);
        int missing = (int) (sumXY - repeating);

        return new int[] { repeating, missing };
    }

    // ✅ Test function
    public static void runTests() {
        int[][] testArrays = {
                { 3, 1, 2, 5, 3 }, // Expected: [3, 4]
                { 1, 2, 2, 4 }, // Expected: [2, 3]
                { 1, 1 }, // Expected: [1, 2]
                { 4, 3, 6, 2, 1, 1 }, // Expected: [1, 5]
        };

        System.out.println("🔍 Testing findWithHashing():");
        for (int[] test : testArrays) {
            System.out.println("Input: " + Arrays.toString(test));
            System.out.println("Output: " + Arrays.toString(findWithHashing(test)) + "\n");
        }

        System.out.println("🔍 Testing findWithMath():");
        for (int[] test : testArrays) {
            System.out.println("Input: " + Arrays.toString(test));
            System.out.println("Output: " + Arrays.toString(findWithMath(test)) + "\n");
        }
    }

    public static void main(String[] args) {
        runTests();
    }
}
