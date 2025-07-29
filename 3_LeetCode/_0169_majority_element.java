import java.util.HashMap;
import java.util.Map;

public class _0169_majority_element {

    // 1. Brute Force Approach - O(n^2)
    public static int majorityElementBrute(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (nums[j] == nums[i]) {
                    count++;
                }
            }
            if (count > n / 2) {
                return nums[i];
            }
        }

        return -1; // Shouldn't happen as per problem
    }

    // 2. Better Approach using HashMap - O(n) Time, O(n) Space
    public static int majorityElementHashing(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        int n = nums.length;

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
            if (freq.get(num) > n / 2) {
                return num;
            }
        }

        return -1; // Shouldn't happen
    }

    // 3. Optimal Approach - Moore’s Voting Algorithm - O(n) Time, O(1) Space
    public static int majorityElementMoore(int[] nums) {
        int count = 0;
        int candidate = 0;

        // Step 1: Find a candidate
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        // Step 2: Verify the candidate (optional if majority always exists)
        count = 0;
        for (int num : nums) {
            if (num == candidate)
                count++;
        }

        return (count > nums.length / 2) ? candidate : -1;
    }

    // Test all methods on multiple test cases
    public static void runTests() {
        int[][] testCases = {
                { 3, 3, 4 }, // Expected: 3
                { 2, 2, 1, 1, 1, 2, 2 }, // Expected: 2
                { 1, 1, 1, 1, 2, 3 }, // Expected: 1
                { 4, 4, 4, 4, 4, 5, 6 }, // Expected: 4
                { 6, 5, 5, 5, 5, 5, 5 } // Expected: 5
        };

        for (int[] arr : testCases) {
            System.out.print("Input: ");
            printArray(arr);
            System.out.println("Brute Force: " + majorityElementBrute(arr));
            System.out.println("Hashing:     " + majorityElementHashing(arr));
            System.out.println("Moore's:     " + majorityElementMoore(arr));
            System.out.println("-------------------------------------------------");
        }
    }

    // Utility function to print array
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        runTests();
    }
}
