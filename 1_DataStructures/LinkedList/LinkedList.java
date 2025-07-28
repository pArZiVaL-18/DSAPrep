public class LinkedList {
    /**
     * Node class represents each element in the linked list
     */
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head; // Points to the first node of the linked list
    public static Node tail; // Points to the last node of the linked list
    public static int size; // Tracks the number of nodes in the linked list

    /**
     * Adds a new node at the beginning of the linked list
     * Time Complexity: O(1)
     */
    public void addFirst(int data) {
        // Step 1: Create new node
        Node newNode = new Node(data);
        size++;

        // If list is empty, both head and tail point to the new node
        if (head == null) {
            head = tail = newNode;
            return;
        }

        // Step 2: Link new node to the current head
        newNode.next = head;

        // Step 3: Update head to point to the new node
        head = newNode;
    }

    /**
     * Adds a new node at the end of the linked list
     * Time Complexity: O(1)
     */
    public void addLast(int data) {
        // Create new node
        Node newNode = new Node(data);
        size++;

        // If list is empty, both head and tail point to the new node
        if (head == null) {
            head = tail = newNode;
            return;
        }

        // Link the current tail to the new node and update tail
        tail.next = newNode;
        tail = newNode;
    }

    /**
     * Prints all elements of the linked list
     * Time Complexity: O(n)
     */
    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + "->");
            current = current.next;
        }
        System.out.println("null");
    }

    /**
     * Adds a new node at the specified index
     * Time Complexity: O(n)
     */
    public void add(int index, int data) {
        // If index is 0, add at the beginning
        if (index == 0) {
            addFirst(data);
            return;
        }

        // Create new node
        Node newNode = new Node(data);
        size++;

        // Find the node at index-1 (previous node)
        Node previous = head;
        int i = 0;
        while (i < index - 1) {
            previous = previous.next;
            i++;
        }

        // Insert the new node between previous and its next
        newNode.next = previous.next;
        previous.next = newNode;
    }

    /**
     * Removes the first node from the linked list
     * Time Complexity: O(1)
     * 
     * @return the data of the removed node
     */
    public int removeFirst() {
        // Handle empty list case
        if (size == 0) {
            System.out.println("Linked List is empty");
            return Integer.MIN_VALUE;
        }
        // Handle single node case
        else if (size == 1) {
            int value = head.data;
            head = tail = null;
            size = 0;
            return value;
        }

        // Store the data to return
        int value = head.data;
        // Move head to the next node
        head = head.next;
        size--;
        return value;
    }

    /**
     * Removes the last node from the linked list
     * Time Complexity: O(n)
     * 
     * @return the data of the removed node
     */
    public int removeLast() {
        // Handle empty list case
        if (size == 0) {
            System.out.println("Linked List is empty");
            return Integer.MIN_VALUE;
        }
        // Handle single node case
        else if (size == 1) {
            int value = head.data;
            head = tail = null;
            size = 0;
            return value;
        }

        // Find the second-to-last node (previous)
        Node previous = head;
        for (int i = 0; i < size - 2; i++) {
            previous = previous.next;
        }

        // Store the data to return
        int value = previous.next.data; // tail.data
        // Update tail and remove the last node
        previous.next = null;
        tail = previous;
        size--;
        return value;
    }

    /**
     * Searches for a key using iteration
     * Time Complexity: O(n)
     * 
     * @return index of the key if found, -1 otherwise
     */
    public int iterativeSearch(int key) {
        Node current = head;
        int index = 0;

        while (current != null) {
            if (current.data == key) { // Key found
                return index;
            }
            current = current.next;
            index++;
        }

        // Key not found
        return -1;
    }

    /**
     * Helper method for recursive search
     * Time Complexity: O(n)
     */
    private int recursiveHelper(Node current, int key) {
        // Base case: reached end of list
        if (current == null) {
            return -1;
        }

        // Base case: key found
        if (current.data == key) {
            return 0;
        }

        // Recursive case: search in the rest of the list
        int index = recursiveHelper(current.next, key);
        if (index == -1) {
            return -1;
        }

        return index + 1;
    }

    /**
     * Searches for a key using recursion
     * Time Complexity: O(n)
     * 
     * @return index of the key if found, -1 otherwise
     */
    public int recursiveSearch(int key) {
        return recursiveHelper(head, key);
    }

    /**
     * Reverses the linked list
     * Time Complexity: O(n)
     */
    public void reverse() {
        // Initialize pointers for reversal
        Node previous = null;
        Node current = head;
        Node next;

        // Update tail to point to the current head
        tail = current;

        // Reverse the links
        while (current != null) {
            next = current.next; // Store next node
            current.next = previous; // Reverse the link
            previous = current; // Move previous forward
            current = next; // Move current forward
        }

        // Update head to point to the new first node (previously last)
        head = previous;
    }

    /**
     * Deletes the nth node from the end
     * Time Complexity: O(n)
     */
    public void deleteNthFromEnd(int n) {
        // Calculate size of the linked list
        int listSize = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            listSize++;
        }

        // If removing the first node
        if (n == listSize) {
            head = head.next; // removeFirst
            return;
        }

        // Find the node before the one to be deleted
        int i = 1;
        int positionToFind = listSize - n;
        Node previous = head;
        while (i < positionToFind) {
            previous = previous.next;
            i++;
        }

        // Remove the target node
        previous.next = previous.next.next;
    }

    /**
     * Finds the middle node of the linked list using slow-fast approach
     * Time Complexity: O(n)
     */
    private Node findMiddle(Node start) {
        Node slow = start;
        Node fast = start;

        while (fast != null && fast.next != null) {
            slow = slow.next; // Move one step
            fast = fast.next.next; // Move two steps
        }
        return slow; // slow is the middle node
    }

    /**
     * Checks if the linked list is a palindrome
     * Time Complexity: O(n)
     */
    public boolean isPalindrome() {
        if (head == null || head.next == null) {
            return true; // Empty list or single node is a palindrome
        }

        // Step 1: Find the middle node
        Node middleNode = findMiddle(head);

        // Step 2: Reverse the second half
        Node previous = null;
        Node current = middleNode;
        Node next;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        // Step 3: Compare first half with reversed second half
        Node rightHalf = previous; // Head of right half
        Node leftHalf = head; // Head of left half

        while (rightHalf != null) {
            if (leftHalf.data != rightHalf.data) {
                return false; // Not a palindrome
            }
            leftHalf = leftHalf.next;
            rightHalf = rightHalf.next;
        }

        return true; // Is a palindrome
    }

    /**
     * Checks if the linked list has a cycle
     * Time Complexity: O(n)
     */
    public static boolean hasCycle() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // Move one step
            fast = fast.next.next; // Move two steps
            if (slow == fast) {
                return true; // Cycle detected
            }
        }

        return false; // No cycle
    }

    /**
     * Removes a cycle from the linked list if present
     * Time Complexity: O(n)
     */
    public static void removeCycle() {
        // Step 1: Detect cycle
        Node slow = head;
        Node fast = head;
        boolean cycleExists = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                cycleExists = true;
                break;
            }
        }

        if (!cycleExists) {
            return; // No cycle to remove
        }

        // Step 2: Find meeting point (start of cycle)
        slow = head;
        Node previous = null; // Track node before fast
        while (slow != fast) {
            previous = fast;
            slow = slow.next;
            fast = fast.next;
        }

        // Step 3: Break the cycle
        previous.next = null;
    }

    /**
     * Helper method to find the middle node for merge sort
     * Time Complexity: O(n)
     */
    private Node getMiddleForSort(Node start) {
        Node slow = start;
        Node fast = start.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow; // Middle node
    }

    /**
     * Merges two sorted linked lists
     * Time Complexity: O(n+m) where n and m are lengths of the lists
     */
    private Node mergeSortedLists(Node list1, Node list2) {
        // Dummy node to simplify merging
        Node mergedList = new Node(-1);
        Node current = mergedList;

        // Compare and merge nodes from both lists
        while (list1 != null && list2 != null) {
            if (list1.data <= list2.data) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // Attach remaining nodes from list1 if any
        while (list1 != null) {
            current.next = list1;
            list1 = list1.next;
            current = current.next;
        }

        // Attach remaining nodes from list2 if any
        while (list2 != null) {
            current.next = list2;
            list2 = list2.next;
            current = current.next;
        }

        return mergedList.next; // Skip the dummy node
    }

    /**
     * Sorts the linked list using merge sort
     * Time Complexity: O(n log n)
     */
    public Node mergeSort(Node start) {
        // Base case: empty list or single node
        if (start == null || start.next == null) {
            return start;
        }

        // Step 1: Find the middle node
        Node middle = getMiddleForSort(start);

        // Step 2: Split the list into two halves
        Node rightHalf = middle.next;
        middle.next = null; // Break the list

        // Step 3: Recursively sort both halves
        Node sortedLeft = mergeSort(start);
        Node sortedRight = mergeSort(rightHalf);

        // Step 4: Merge the sorted halves
        return mergeSortedLists(sortedLeft, sortedRight);
    }

    /**
     * Rearranges the linked list in a zigzag pattern
     * Time Complexity: O(n)
     */
    public void zigZag() {
        // Step 1: Find the middle node
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node middle = slow;

        // Step 2: Reverse the second half
        Node current = middle.next;
        middle.next = null; // Break the list
        Node previous = null;
        Node next;

        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        // Step 3: Merge the two halves in zigzag pattern
        Node leftHalf = head;
        Node rightHalf = previous;
        Node nextLeft, nextRight;

        // Alternate merging of nodes
        while (leftHalf != null && rightHalf != null) {
            // Save next pointers
            nextLeft = leftHalf.next;
            nextRight = rightHalf.next;

            // Connect in zigzag pattern
            leftHalf.next = rightHalf;
            rightHalf.next = nextLeft;

            // Move to next nodes
            leftHalf = nextLeft;
            rightHalf = nextRight;
        }
    }

    /**
     * Tests all the methods of the LinkedList class
     */
    public static void testAllMethods() {
        LinkedList list = new LinkedList();

        // Test addFirst and addLast
        System.out.println("Adding elements to the list:");
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(3);
        list.addLast(4);
        list.print(); // Expected: 1->2->3->4->null
        System.out.println("Size: " + LinkedList.size); // Expected: 4

        // Test add at index
        System.out.println("\nAdding element at index 2:");
        list.add(2, 10);
        list.print(); // Expected: 1->2->10->3->4->null

        // Test search operations
        System.out.println("\nSearching for elements:");
        System.out.println("Element 10 found at index (iterative): " + list.iterativeSearch(10)); // Expected: 2
        System.out.println("Element 5 found at index (iterative): " + list.iterativeSearch(5)); // Expected: -1
        System.out.println("Element 10 found at index (recursive): " + list.recursiveSearch(10)); // Expected: 2
        System.out.println("Element 5 found at index (recursive): " + list.recursiveSearch(5)); // Expected: -1

        // Test remove operations
        System.out.println("\nRemoving elements:");
        System.out.println("Removed first element: " + list.removeFirst()); // Expected: 1
        list.print(); // Expected: 2->10->3->4->null
        System.out.println("Removed last element: " + list.removeLast()); // Expected: 4
        list.print(); // Expected: 2->10->3->null

        // Test reverse
        System.out.println("\nReversing the list:");
        list.reverse();
        list.print(); // Expected: 3->10->2->null

        // Test deleteNthFromEnd
        System.out.println("\nDeleting 2nd node from end:");
        list.deleteNthFromEnd(2);
        list.print(); // Expected: 3->2->null

        // Test palindrome check
        System.out.println("\nTesting palindrome check:");
        LinkedList palindromeList = new LinkedList();
        palindromeList.addLast(1);
        palindromeList.addLast(2);
        palindromeList.addLast(2);
        palindromeList.addLast(1);
        palindromeList.print(); // Expected: 1->2->2->1->null
        System.out.println("Is palindrome: " + palindromeList.isPalindrome()); // Expected: true

        // Test merge sort
        System.out.println("\nTesting merge sort:");
        LinkedList unsortedList = new LinkedList();
        unsortedList.addLast(4);
        unsortedList.addLast(2);
        unsortedList.addLast(1);
        unsortedList.addLast(3);
        System.out.println("Before sorting:");
        unsortedList.print(); // Expected: 4->2->1->3->null

        unsortedList.head = unsortedList.mergeSort(unsortedList.head);
        System.out.println("After sorting:");
        unsortedList.print(); // Expected: 1->2->3->4->null

        // Test zigzag
        System.out.println("\nTesting zigzag rearrangement:");
        LinkedList zigzagList = new LinkedList();
        zigzagList.addLast(1);
        zigzagList.addLast(2);
        zigzagList.addLast(3);
        zigzagList.addLast(4);
        zigzagList.addLast(5);
        System.out.println("Before zigzag:");
        zigzagList.print(); // Expected: 1->2->3->4->5->null

        zigzagList.zigZag();
        System.out.println("After zigzag:");
        zigzagList.print(); // Expected: 1->5->2->4->3->null

        // Note: Cycle detection and removal are not tested here as they would create a
        // non-printable list
    }

    public static void main(String args[]) {
        testAllMethods();
    }
}