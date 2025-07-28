/**
 * Stack implementation using an ArrayList
 * 
 * This class demonstrates how to implement a Stack data structure
 * using Java's ArrayList. The stack follows LIFO (Last-In-First-Out)
 * principle where the last element added is the first one to be removed.
 * 
 * Time Complexity:
 * - Push: O(1) amortized
 * - Pop: O(1)
 * - Peek: O(1)
 * - isEmpty: O(1)
 * - size: O(1)
 */
import java.util.ArrayList;

public class StackArrayList {
    /**
     * Stack class that implements stack operations using ArrayList
     */
    static class Stack {
        private ArrayList<Integer> elements = new ArrayList<>(); // Store stack elements
        
        /**
         * Adds an element to the top of the stack
         * 
         * @param data The element to be pushed onto the stack
         * @return void
         * Time Complexity: O(1) amortized (due to ArrayList's dynamic resizing)
         */
        public void push(int data) {
            elements.add(data);
            System.out.println(data + " pushed to stack");
        }

        /**
         * Checks if the stack is empty
         * 
         * @return true if stack is empty, false otherwise
         * Time Complexity: O(1)
         */
        public boolean isEmpty() {
            return elements.size() == 0;
        }

        /**
         * Removes and returns the top element from the stack
         * 
         * @return The top element if stack is not empty, -1 otherwise
         * Time Complexity: O(1)
         */
        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow");
                return -1;
            }
            int topElement = elements.remove(elements.size() - 1);
            System.out.println(topElement + " popped from stack");
            return topElement;
        }

        /**
         * Returns the top element without removing it
         * 
         * @return The top element if stack is not empty, -1 otherwise
         * Time Complexity: O(1)
         */
        public int peek() {
            if (isEmpty()) {
                System.out.println("Stack is Empty");
                return -1;
            }
            return elements.get(elements.size() - 1);
        }
        
        /**
         * Returns the current size of the stack
         * 
         * @return The number of elements in the stack
         * Time Complexity: O(1)
         */
        public int size() {
            return elements.size();
        }
        
        /**
         * Displays all elements in the stack from top to bottom
         * 
         * Time Complexity: O(n) where n is the number of elements
         */
        public void display() {
            if (isEmpty()) {
                System.out.println("Stack is Empty");
                return;
            }
            
            System.out.print("Stack (Top to Bottom): ");
            for (int i = elements.size() - 1; i >= 0; i--) {
                System.out.print(elements.get(i));
                if (i > 0) {
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
        System.out.println("===== Stack Implementation using ArrayList =====\n");
        
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