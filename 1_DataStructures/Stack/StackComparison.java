/**
 * Stack Implementations Comparison
 * 
 * This class demonstrates and compares two different stack implementations:
 * 1. ArrayList-based Stack
 * 2. LinkedList-based Stack
 * 
 * It shows the differences in implementation, advantages, disadvantages,
 * and performance characteristics of each approach.
 */

import java.util.ArrayList;

public class StackComparison {
    
    /**
     * ArrayList-based Stack implementation
     */
    static class ArrayListStack {
        private ArrayList<Integer> elements = new ArrayList<>();
        
        // Push operation - O(1) amortized time complexity
        public void push(int data) {
            elements.add(data);
        }
        
        // Pop operation - O(1) time complexity
        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow");
                return -1;
            }
            return elements.remove(elements.size() - 1);
        }
        
        // Peek operation - O(1) time complexity
        public int peek() {
            if (isEmpty()) {
                return -1;
            }
            return elements.get(elements.size() - 1);
        }
        
        // Check if stack is empty - O(1) time complexity
        public boolean isEmpty() {
            return elements.size() == 0;
        }
        
        // Get size of stack - O(1) time complexity
        public int size() {
            return elements.size();
        }
    }
    
    /**
     * LinkedList-based Stack implementation
     */
    static class LinkedListStack {
        private static class Node {
            int data;
            Node next;
            
            Node(int data) {
                this.data = data;
                this.next = null;
            }
        }
        
        private Node head = null;
        private int size = 0;
        
        // Push operation - O(1) time complexity
        public void push(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
            size++;
        }
        
        // Pop operation - O(1) time complexity
        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow");
                return -1;
            }
            int result = head.data;
            head = head.next;
            size--;
            return result;
        }
        
        // Peek operation - O(1) time complexity
        public int peek() {
            if (isEmpty()) {
                return -1;
            }
            return head.data;
        }
        
        // Check if stack is empty - O(1) time complexity
        public boolean isEmpty() {
            return head == null;
        }
        
        // Get size of stack - O(1) time complexity
        public int size() {
            return size;
        }
    }
    
    /**
     * Method to measure and compare performance of both stack implementations
     */
    public static void comparePerformance(int operations) {
        System.out.println("\n===== Performance Comparison =====\n");
        System.out.println("Performing " + operations + " push operations followed by " + operations + " pop operations\n");
        
        // Test ArrayList Stack
        long startTime = System.nanoTime();
        ArrayListStack arrayStack = new ArrayListStack();
        
        // Push operations
        for (int i = 0; i < operations; i++) {
            arrayStack.push(i);
        }
        
        // Pop operations
        for (int i = 0; i < operations; i++) {
            arrayStack.pop();
        }
        
        long arrayListTime = System.nanoTime() - startTime;
        
        // Test LinkedList Stack
        startTime = System.nanoTime();
        LinkedListStack linkedStack = new LinkedListStack();
        
        // Push operations
        for (int i = 0; i < operations; i++) {
            linkedStack.push(i);
        }
        
        // Pop operations
        for (int i = 0; i < operations; i++) {
            linkedStack.pop();
        }
        
        long linkedListTime = System.nanoTime() - startTime;
        
        // Print results
        System.out.println("ArrayList Stack time: " + arrayListTime / 1000000.0 + " ms");
        System.out.println("LinkedList Stack time: " + linkedListTime / 1000000.0 + " ms");
        
        if (arrayListTime < linkedListTime) {
            System.out.println("ArrayList implementation was faster by: " + 
                              ((linkedListTime - arrayListTime) / 1000000.0) + " ms");
        } else {
            System.out.println("LinkedList implementation was faster by: " + 
                              ((arrayListTime - linkedListTime) / 1000000.0) + " ms");
        }
    }
    
    /**
     * Method to demonstrate memory usage differences
     */
    public static void compareMemoryUsage() {
        System.out.println("\n===== Memory Usage Comparison =====\n");
        System.out.println("ArrayList Stack:");
        System.out.println("- Stores elements in contiguous memory locations");
        System.out.println("- May need to resize and copy all elements when capacity is reached");
        System.out.println("- No overhead per element beyond the data itself");
        System.out.println("- More memory-efficient for large number of elements");
        
        System.out.println("\nLinkedList Stack:");
        System.out.println("- Stores elements in nodes scattered throughout memory");
        System.out.println("- Each node requires extra memory for the 'next' reference");
        System.out.println("- No resizing operations needed");
        System.out.println("- More memory overhead per element due to node structure");
    }
    
    /**
     * Method to explain advantages and disadvantages of each implementation
     */
    public static void compareAdvantagesDisadvantages() {
        System.out.println("\n===== Advantages and Disadvantages =====\n");
        
        System.out.println("ArrayList Stack:");
        System.out.println("Advantages:");
        System.out.println("- Better memory locality and cache performance");
        System.out.println("- Less memory overhead per element");
        System.out.println("- Random access capability (though not typically used for stacks)");
        
        System.out.println("\nDisadvantages:");
        System.out.println("- Potential performance hit when resizing is needed");
        System.out.println("- Fixed capacity in basic arrays (though ArrayList handles this)");
        System.out.println("- Inefficient for insertions/deletions in the middle (not typical for stacks)");
        
        System.out.println("\nLinkedList Stack:");
        System.out.println("Advantages:");
        System.out.println("- Constant time insertions and deletions at the head (top of stack)");
        System.out.println("- Dynamic size without resizing operations");
        System.out.println("- No wasted space for unused capacity");
        
        System.out.println("\nDisadvantages:");
        System.out.println("- Extra memory overhead for node pointers");
        System.out.println("- Poor cache locality due to scattered memory allocation");
        System.out.println("- No random access capability");
    }
    
    /**
     * Main method to run the comparison
     */
    public static void main(String[] args) {
        System.out.println("===== Stack Implementations Comparison =====\n");
        
        // Compare basic operations
        System.out.println("===== Basic Operations Demonstration =====\n");
        
        System.out.println("ArrayList Stack Operations:");
        ArrayListStack arrayStack = new ArrayListStack();
        arrayStack.push(10);
        arrayStack.push(20);
        arrayStack.push(30);
        System.out.println("Top element: " + arrayStack.peek());
        System.out.println("Popped: " + arrayStack.pop());
        System.out.println("New top: " + arrayStack.peek());
        System.out.println("Size: " + arrayStack.size());
        
        System.out.println("\nLinkedList Stack Operations:");
        LinkedListStack linkedStack = new LinkedListStack();
        linkedStack.push(10);
        linkedStack.push(20);
        linkedStack.push(30);
        System.out.println("Top element: " + linkedStack.peek());
        System.out.println("Popped: " + linkedStack.pop());
        System.out.println("New top: " + linkedStack.peek());
        System.out.println("Size: " + linkedStack.size());
        
        // Compare performance
        comparePerformance(1000000);
        
        // Compare memory usage
        compareMemoryUsage();
        
        // Compare advantages and disadvantages
        compareAdvantagesDisadvantages();
        
        System.out.println("\n===== When to Use Each Implementation =====\n");
        System.out.println("Use ArrayList-based Stack when:");
        System.out.println("- Memory efficiency is a concern");
        System.out.println("- You need better cache performance");
        System.out.println("- You have a good estimate of the maximum stack size");
        
        System.out.println("\nUse LinkedList-based Stack when:");
        System.out.println("- The stack size is highly unpredictable");
        System.out.println("- You want to avoid potential resizing operations");
        System.out.println("- You need to share nodes between multiple data structures");
    }
}