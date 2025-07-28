/**
 * Stack implementation using a Linked List
 * 
 * This class demonstrates how to implement a Stack data structure
 * using a singly linked list. The stack follows LIFO (Last-In-First-Out)
 * principle where the last element added is the first one to be removed.
 * 
 * Time Complexity:
 * - Push: O(1)
 * - Pop: O(1)
 * - Peek: O(1)
 * - isEmpty: O(1)
 * - size: O(1)
 */
public class StackLinkedList {
    /**
     * Node class for the linked list implementation
     * Each node contains data and a reference to the next node
     */
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    /**
     * Stack class that implements stack operations using linked list
     */
    static class Stack {
        private static Node head = null; // Top of the stack
        private static int size = 0;    // Track the size of stack

        /**
         * Adds an element to the top of the stack
         * 
         * @param data The element to be pushed onto the stack
         * @return void
         * Time Complexity: O(1)
         */
        public static void push(int data) {
            Node newNode = new Node(data);

            if (head == null) {
                head = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
            size++;
            System.out.println(data + " pushed to stack");
        }

        /**
         * Checks if the stack is empty
         * 
         * @return true if stack is empty, false otherwise
         * Time Complexity: O(1)
         */
        public static boolean isEmpty() {
            return head == null;
        }

        /**
         * Removes and returns the top element from the stack
         * 
         * @return The top element if stack is not empty, -1 otherwise
         * Time Complexity: O(1)
         */
        public static int pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow");
                return -1;
            }
            Node topNode = head;
            head = head.next;
            size--;
            System.out.println(topNode.data + " popped from stack");
            return topNode.data;
        }

        /**
         * Returns the top element without removing it
         * 
         * @return The top element if stack is not empty, -1 otherwise
         * Time Complexity: O(1)
         */
        public static int peek() {
            if (isEmpty()) {
                System.out.println("Stack is Empty");
                return -1;
            }
            return head.data;
        }
        
        /**
         * Returns the current size of the stack
         * 
         * @return The number of elements in the stack
         * Time Complexity: O(1)
         */
        public static int size() {
            return size;
        }
        
        /**
         * Displays all elements in the stack from top to bottom
         * 
         * Time Complexity: O(n) where n is the number of elements
         */
        public static void display() {
            if (isEmpty()) {
                System.out.println("Stack is Empty");
                return;
            }
            
            System.out.print("Stack (Top to Bottom): ");
            Node current = head;
            while (current != null) {
                System.out.print(current.data);
                current = current.next;
                if (current != null) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Main method to demonstrate stack operations
     */
    public static void main(String args[]) {
        System.out.println("===== Stack Implementation using Linked List =====\n");
        
        Stack stack = new Stack();
        
        // Demonstrating push operations
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        
        // Display current stack state
        System.out.println("\nCurrent stack:");
        stack.display();
        System.out.println("Size: " + stack.size());
        
        // Demonstrating peek operation
        System.out.println("\nTop element: " + stack.peek());
        
        // Demonstrating pop operations
        System.out.println("\nPopping elements:");
        while (!stack.isEmpty()) {
            stack.pop();
            if (!stack.isEmpty()) {
                System.out.println("Current top: " + stack.peek());
            }
        }
        
        // Demonstrating empty stack behavior
        System.out.println("\nTrying to pop from empty stack:");
        stack.pop();
        
        System.out.println("\nStack size: " + stack.size());
    }
}