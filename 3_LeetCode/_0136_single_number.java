import java.util.*;

public class _0136_single_number {

    // XOR Approach - O(n) time, O(1) space
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    // Hashmap approach - O(n) time, O(n) space
    public int singleNumberUsingMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1)
                return entry.getKey();
        }
        return -1; // shouldn't reach here
    }

    public static void main(String[] args) {
        _0136_single_number solver = new _0136_single_number();

        // Test Case 1
        int[] nums1 = { 2, 2, 1 };
        System.out.println("Test Case 1: " + solver.singleNumber(nums1)); // Output: 1

        // Test Case 2
        int[] nums2 = { 4, 1, 2, 1, 2 };
        System.out.println("Test Case 2: " + solver.singleNumber(nums2)); // Output: 4

        // Test Case 3
        int[] nums3 = { 1 };
        System.out.println("Test Case 3: " + solver.singleNumber(nums3)); // Output: 1

        // Test Case 4: Negative numbers
        int[] nums4 = { -1, -1, -2 };
        System.out.println("Test Case 4: " + solver.singleNumber(nums4)); // Output: -2

        // Test Case 5: Large array
        int[] nums5 = new int[200001];
        for (int i = 0; i < 100000; i++) {
            nums5[i * 2] = i;
            nums5[i * 2 + 1] = i;
        }
        nums5[200000] = 999999; // the single number
        System.out.println("Test Case 5: " + solver.singleNumber(nums5)); // Output: 999999
    }
}
