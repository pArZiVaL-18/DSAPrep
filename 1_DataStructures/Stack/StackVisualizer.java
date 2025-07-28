/**
 * Stack Visualizer
 * 
 * This class provides a visual representation of stack operations
 * using ASCII art to help understand how stacks work.
 */

import java.util.Scanner;

public class StackVisualizer {
    
    // Maximum capacity of our visual stack
    private static final int MAX_CAPACITY = 5;
    
    // Array to store stack elements
    private static Integer[] stack = new Integer[MAX_CAPACITY];
    
    // Current size of stack
    private static int size = 0;
    
    /**
     * Pushes an element onto the stack and visualizes the operation
     */
    public static void push(int value) {
        System.out.println("\n===== PUSH OPERATION: " + value + " =====\n");
        
        if (size >= MAX_CAPACITY) {
            System.out.println("Stack Overflow! Cannot push " + value);
            return;
        }
        
        // Show stack before operation
        System.out.println("Before Push:");
        visualizeStack();
        
        // Perform push operation
        stack[size] = value;
        size++;
        
        // Show stack after operation
        System.out.println("\nAfter Push:");
        visualizeStack();
        
        System.out.println("\nElement " + value + " pushed to stack");
    }
    
    /**
     * Pops an element from the stack and visualizes the operation
     */
    public static Integer pop() {
        System.out.println("\n===== POP OPERATION =====\n");
        
        if (size <= 0) {
            System.out.println("Stack Underflow! Cannot pop from empty stack");
            return null;
        }
        
        // Show stack before operation
        System.out.println("Before Pop:");
        visualizeStack();
        
        // Perform pop operation
        Integer poppedValue = stack[size - 1];
        stack[size - 1] = null;
        size--;
        
        // Show stack after operation
        System.out.println("\nAfter Pop:");
        visualizeStack();
        
        System.out.println("\nElement " + poppedValue + " popped from stack");
        return poppedValue;
    }
    
    /**
     * Peeks at the top element of the stack and visualizes the operation
     */
    public static Integer peek() {
        System.out.println("\n===== PEEK OPERATION =====\n");
        
        if (size <= 0) {
            System.out.println("Stack is empty! Nothing to peek");
            return null;
        }
        
        // Show stack with peek highlight
        visualizeStackWithPeek();
        
        Integer topValue = stack[size - 1];
        System.out.println("\nTop element is: " + topValue);
        return topValue;
    }
    
    /**
     * Visualizes the current state of the stack using ASCII art
     */
    public static void visualizeStack() {
        System.out.println("  +---+");
        
        // Print stack elements from top to bottom
        for (int i = MAX_CAPACITY - 1; i >= 0; i--) {
            if (i < size) {
                System.out.printf("  | %d |\n", stack[i]);
            } else {
                System.out.println("  |   |");
            }
            System.out.println("  +---+");
        }
        
        // Print stack base
        System.out.println("  =====\n");
        System.out.println("  Stack");
    }
    
    /**
     * Visualizes the stack with the top element highlighted for peek operation
     */
    public static void visualizeStackWithPeek() {
        System.out.println("  +---+");
        
        // Print stack elements from top to bottom
        for (int i = MAX_CAPACITY - 1; i >= 0; i--) {
            if (i == size - 1 && i >= 0) {
                // Highlight the top element
                System.out.printf("->| %d |<- (Top)\n", stack[i]);
            } else if (i < size) {
                System.out.printf("  | %d |\n", stack[i]);
            } else {
                System.out.println("  |   |");
            }
            System.out.println("  +---+");
        }
        
        // Print stack base
        System.out.println("  =====\n");
        System.out.println("  Stack");
    }
    
    /**
     * Demonstrates how function calls use a stack
     */
    public static void demonstrateFunctionCallStack() {
        System.out.println("\n===== FUNCTION CALL STACK DEMONSTRATION =====\n");
        System.out.println("When functions are called, they are added to the call stack.");
        System.out.println("When they return, they are removed from the stack.\n");
        
        // Clear the stack for demonstration
        stack = new Integer[MAX_CAPACITY];
        size = 0;
        
        System.out.println("Starting with main():\n");
        push(1); // Represent main function
        System.out.println("\nmain() calls function A():\n");
        push(2); // Represent function A
        System.out.println("\nfunction A() calls function B():\n");
        push(3); // Represent function B
        System.out.println("\nfunction B() calls function C():\n");
        push(4); // Represent function C
        
        System.out.println("\nNow functions return in reverse order:\n");
        System.out.println("function C() returns to function B():\n");
        pop(); // C returns
        System.out.println("\nfunction B() returns to function A():\n");
        pop(); // B returns
        System.out.println("\nfunction A() returns to main():\n");
        pop(); // A returns
        System.out.println("\nmain() completes execution:\n");
        pop(); // main returns
    }
    
    /**
     * Demonstrates how to check for balanced parentheses using a stack
     */
    public static void demonstrateBalancedParentheses(String expression) {
        System.out.println("\n===== BALANCED PARENTHESES DEMONSTRATION =====\n");
        System.out.println("Checking if expression has balanced parentheses: " + expression + "\n");
        
        // Clear the stack for demonstration
        stack = new Integer[MAX_CAPACITY];
        size = 0;
        
        boolean isBalanced = true;
        
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            
            if (ch == '(') {
                System.out.println("Found opening parenthesis at position " + i + ", pushing to stack\n");
                push((int)ch);
            } else if (ch == ')') {
                System.out.println("Found closing parenthesis at position " + i);
                
                if (size == 0) {
                    System.out.println("No matching opening parenthesis found!\n");
                    isBalanced = false;
                    break;
                } else {
                    System.out.println("Popping matching opening parenthesis from stack\n");
                    pop();
                }
            }
        }
        
        if (size > 0) {
            System.out.println("Stack is not empty! Some opening parentheses don't have matching closing ones.\n");
            isBalanced = false;
        }
        
        System.out.println("Expression " + expression + " has " + 
                          (isBalanced ? "balanced" : "unbalanced") + " parentheses.");
    }
    
    /**
     * Main method with interactive menu to demonstrate stack operations
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        
        System.out.println("===== STACK VISUALIZER =====\n");
        System.out.println("This program helps you visualize how a stack works\n");
        
        while (choice != 7) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Push an element");
            System.out.println("2. Pop an element");
            System.out.println("3. Peek at top element");
            System.out.println("4. Visualize current stack");
            System.out.println("5. Demonstrate function call stack");
            System.out.println("6. Check balanced parentheses");
            System.out.println("7. Exit");
            System.out.print("\nEnter your choice (1-7): ");
            
            try {
                choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter value to push: ");
                        int value = scanner.nextInt();
                        push(value);
                        break;
                        
                    case 2:
                        pop();
                        break;
                        
                    case 3:
                        peek();
                        break;
                        
                    case 4:
                        System.out.println("\n===== CURRENT STACK =====\n");
                        visualizeStack();
                        break;
                        
                    case 5:
                        demonstrateFunctionCallStack();
                        break;
                        
                    case 6:
                        scanner.nextLine(); // Clear buffer
                        System.out.print("Enter an expression with parentheses: ");
                        String expression = scanner.nextLine();
                        demonstrateBalancedParentheses(expression);
                        break;
                        
                    case 7:
                        System.out.println("\nThank you for using Stack Visualizer!");
                        break;
                        
                    default:
                        System.out.println("\nInvalid choice! Please enter a number between 1 and 7.");
                }
            } catch (Exception e) {
                System.out.println("\nInvalid input! Please enter a valid number.");
                scanner.nextLine(); // Clear buffer
            }
        }
        
        scanner.close();
    }
}