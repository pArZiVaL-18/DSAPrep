import java.util.Arrays;

public class _0088_merge_two_sorted_array {

    // In-place merge function (your implementation)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m + n - 1;
        int l1 = m - 1;
        int l2 = n - 1;

        while (l1 >= 0 && l2 >= 0) {
            if (nums1[l1] > nums2[l2]) {
                nums1[i--] = nums1[l1--];
            } else {
                nums1[i--] = nums2[l2--];
            }
        }

        // If nums2 still has elements left
        while (l2 >= 0) {
            nums1[i--] = nums2[l2--];
        }
    }

    // Test runner
    public static void main(String[] args) {
        _0088_merge_two_sorted_array merger = new _0088_merge_two_sorted_array();

        // Test Case 1
        int[] nums1 = { 1, 2, 3, 0, 0, 0 };
        int[] nums2 = { 2, 5, 6 };
        merger.merge(nums1, 3, nums2, 3);
        System.out.println("Merged: " + Arrays.toString(nums1)); // [1, 2, 2, 3, 5, 6]

        // Test Case 2: nums1 is empty, nums2 has elements
        int[] nums3 = { 0 };
        int[] nums4 = { 1 };
        merger.merge(nums3, 0, nums4, 1);
        System.out.println("Merged: " + Arrays.toString(nums3)); // [1]

        // Test Case 3: nums2 is empty, nums1 remains unchanged
        int[] nums5 = { 1 };
        int[] nums6 = {};
        merger.merge(nums5, 1, nums6, 0);
        System.out.println("Merged: " + Arrays.toString(nums5)); // [1]

        // Test Case 4: Duplicates and unordered mix
        int[] nums7 = { 4, 5, 6, 0, 0, 0 };
        int[] nums8 = { 1, 2, 3 };
        merger.merge(nums7, 3, nums8, 3);
        System.out.println("Merged: " + Arrays.toString(nums7)); // [1, 2, 3, 4, 5, 6]
    }
}
