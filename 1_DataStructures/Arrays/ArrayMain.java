import java.util.Scanner;

public class ArrayMain {

    // Utility method to print an array
    public static void printArray(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    // Insert element at a given position
    public static int[] insertElement(int[] arr, int index, int element) {
        if (index < 0 || index > arr.length) {
            System.out.println("Invalid index");
            return arr;
        }
        int[] newArr = new int[arr.length + 1];
        for (int i = 0; i < index; i++) {
            newArr[i] = arr[i];
        }
        newArr[index] = element;
        for (int i = index; i < arr.length; i++) {
            newArr[i + 1] = arr[i];
        }
        return newArr;
    }

    // Delete element at a given position
    public static int[] deleteElement(int[] arr, int index) {
        if (index < 0 || index >= arr.length) {
            System.out.println("Invalid index");
            return arr;
        }
        int[] newArr = new int[arr.length - 1];
        for (int i = 0, j = 0; i < arr.length; i++) {
            if (i != index) {
                newArr[j++] = arr[i];
            }
        }
        return newArr;
    }

    // Reverse the array in place
    public static void reverseArray(int[] arr) {
        int start = 0, end = arr.length - 1;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // Rotate array to the left by k positions
    public static void rotateLeft(int[] arr, int k) {
        k = k % arr.length;
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
    }

    // Rotate array to the right by k positions
    public static void rotateRight(int[] arr, int k) {
        k = k % arr.length;
        reverse(arr, 0, arr.length - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
    }

    // Reverse helper
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // Check if array is sorted
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1])
                return false;
        }
        return true;
    }

    // Find second largest element
    public static int findSecondLargest(int[] arr) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for (int val : arr) {
            if (val > first) {
                second = first;
                first = val;
            } else if (val > second && val != first) {
                second = val;
            }
        }
        return (second == Integer.MIN_VALUE) ? -1 : second;
    }

    // Move all zeros to the end
    public static void moveZerosToEnd(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[count++] = arr[i];
            }
        }
        while (count < arr.length) {
            arr[count++] = 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = { 1, 2, 0, 4, 0, 5 };

        System.out.println("Original array:");
        printArray(arr);

        System.out.println("\nReversed array:");
        reverseArray(arr);
        printArray(arr);

        System.out.println("\nRotate left by 2:");
        rotateLeft(arr, 2);
        printArray(arr);

        System.out.println("\nRotate right by 2:");
        rotateRight(arr, 2);
        printArray(arr);

        System.out.println("\nSecond largest: " + findSecondLargest(arr));

        System.out.println("\nMove zeros to end:");
        moveZerosToEnd(arr);
        printArray(arr);

        System.out.println("\nCheck if sorted: " + isSorted(arr));

        System.out.println("\nInsert 10 at index 2:");
        arr = insertElement(arr, 2, 10);
        printArray(arr);

        System.out.println("\nDelete element at index 3:");
        arr = deleteElement(arr, 3);
        printArray(arr);

        sc.close();

        /*
         * Array Learning Challenges:
         * 1. Write a method to check if an array is a palindrome.
         * 2. Find the number of distinct elements in the array.
         * 3. Implement a method to remove duplicates from unsorted array.
         * 4. Implement a menu-driven program to perform all above operations with user
         * input.
         */
    }
}
