/**
 * Stack Practice Problems
 * 
 * This file contains common stack-based coding problems to practice
 * and improve understanding of stack data structures.
 */

import java.util.Stack;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StackPracticeProblems {

    /**
     * Problem 1: Valid Parentheses
     * 
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
     * determine if the input string is valid.
     * 
     * An input string is valid if:
     * 1. Open brackets must be closed by the same type of brackets.
     * 2. Open brackets must be closed in the correct order.
     * 
     * Time Complexity: O(n) where n is the length of the string
     * Space Complexity: O(n) in worst case
     * 
     * @param s The string to check
     * @return true if the string is valid, false otherwise
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> bracketPairs = new HashMap<>();
        bracketPairs.put(')', '(');
        bracketPairs.put('}', '{');
        bracketPairs.put(']', '[');
        
        for (char c : s.toCharArray()) {
            // If it's an opening bracket, push to stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } 
            // If it's a closing bracket
            else if (bracketPairs.containsKey(c)) {
                // If stack is empty or the top doesn't match, it's invalid
                if (stack.isEmpty() || stack.pop() != bracketPairs.get(c)) {
                    return false;
                }
            }
        }
        
        // If stack is empty, all brackets were matched
        return stack.isEmpty();
    }
    
    /**
     * Problem 2: Evaluate Reverse Polish Notation
     * 
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
     * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
     * 
     * Time Complexity: O(n) where n is the number of tokens
     * Space Complexity: O(n) in worst case
     * 
     * @param tokens The tokens of the RPN expression
     * @return The result of the expression
     */
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(a / b);
                        break;
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        
        return stack.pop();
    }
    
    /**
     * Problem 3: Min Stack
     * 
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
     */
    static class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;
        
        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }
        
        /**
         * Pushes an element onto the stack
         * Time Complexity: O(1)
         */
        public void push(int val) {
            stack.push(val);
            
            // If minStack is empty or val is less than or equal to current min,
            // push to minStack
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }
        
        /**
         * Removes the element on top of the stack
         * Time Complexity: O(1)
         */
        public void pop() {
            if (!stack.isEmpty()) {
                // If the popped element is the current minimum, remove from minStack too
                if (stack.peek().equals(minStack.peek())) {
                    minStack.pop();
                }
                stack.pop();
            }
        }
        
        /**
         * Get the top element
         * Time Complexity: O(1)
         */
        public int top() {
            if (stack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            return stack.peek();
        }
        
        /**
         * Retrieve the minimum element in the stack
         * Time Complexity: O(1)
         */
        public int getMin() {
            if (minStack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            return minStack.peek();
        }
    }
    
    /**
     * Problem 4: Implement Queue using Stacks
     * 
     * Implement a first in first out (FIFO) queue using only two stacks.
     */
    static class MyQueue {
        private Stack<Integer> inputStack;
        private Stack<Integer> outputStack;
        
        public MyQueue() {
            inputStack = new Stack<>();
            outputStack = new Stack<>();
        }
        
        /**
         * Push element x to the back of queue
         * Time Complexity: O(1)
         */
        public void push(int x) {
            inputStack.push(x);
        }
        
        /**
         * Removes the element from the front of queue and returns it
         * Time Complexity: Amortized O(1), worst case O(n)
         */
        public int pop() {
            // If outputStack is empty, transfer all elements from inputStack
            if (outputStack.isEmpty()) {
                while (!inputStack.isEmpty()) {
                    outputStack.push(inputStack.pop());
                }
            }
            
            if (outputStack.isEmpty()) {
                throw new RuntimeException("Queue is empty");
            }
            
            return outputStack.pop();
        }
        
        /**
         * Get the front element
         * Time Complexity: Amortized O(1), worst case O(n)
         */
        public int peek() {
            // If outputStack is empty, transfer all elements from inputStack
            if (outputStack.isEmpty()) {
                while (!inputStack.isEmpty()) {
                    outputStack.push(inputStack.pop());
                }
            }
            
            if (outputStack.isEmpty()) {
                throw new RuntimeException("Queue is empty");
            }
            
            return outputStack.peek();
        }
        
        /**
         * Returns whether the queue is empty
         * Time Complexity: O(1)
         */
        public boolean empty() {
            return inputStack.isEmpty() && outputStack.isEmpty();
        }
    }
    
    /**
     * Problem 5: Next Greater Element
     * 
     * Given an array, print the Next Greater Element (NGE) for every element.
     * The Next Greater Element for an element x is the first greater element 
     * on the right side of x in the array. Elements for which no greater 
     * element exists, consider the next greater element as -1.
     * 
     * Time Complexity: O(n) where n is the length of the array
     * Space Complexity: O(n)
     * 
     * @param nums The input array
     * @return An array containing the next greater element for each element
     */
    public static int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        // Initialize result array with -1
        Arrays.fill(result, -1);
        
        // Process all elements from right to left
        for (int i = n - 1; i >= 0; i--) {
            // While stack has elements and current element is greater than stack's top
            while (!stack.isEmpty() && nums[i] >= stack.peek()) {
                stack.pop(); // Remove smaller elements
            }
            
            // If stack is not empty, the top element is the next greater element
            if (!stack.isEmpty()) {
                result[i] = stack.peek();
            }
            
            // Push current element to stack
            stack.push(nums[i]);
        }
        
        return result;
    }
    
    /**
     * Problem 6: Daily Temperatures
     * 
     * Given an array of integers temperatures represents the daily temperatures,
     * return an array answer such that answer[i] is the number of days you have to wait
     * after the ith day to get a warmer temperature. If there is no future day for which
     * this is possible, keep answer[i] == 0 instead.
     * 
     * Time Complexity: O(n) where n is the length of the array
     * Space Complexity: O(n)
     * 
     * @param temperatures The array of daily temperatures
     * @return An array where each element is the number of days to wait for a warmer temperature
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>(); // Stack to store indices
        
        for (int i = 0; i < n; i++) {
            // While stack has indices and current temperature is higher than temperature at stack's top index
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                answer[prevIndex] = i - prevIndex; // Calculate days to wait
            }
            
            // Push current index to stack
            stack.push(i);
        }
        
        return answer;
    }
    
    /**
     * Main method to test all practice problems
     */
    public static void main(String[] args) {
        System.out.println("===== STACK PRACTICE PROBLEMS =====\n");
        
        // Test Problem 1: Valid Parentheses
        System.out.println("Problem 1: Valid Parentheses");
        System.out.println("---------------------------");
        String[] parenthesesTests = {"()", "()[]{}", "(]", "([)]", "{[]}"};
        for (String test : parenthesesTests) {
            System.out.println("Input: \"" + test + "\" -> Output: " + isValid(test));
        }
        
        // Test Problem 2: Evaluate Reverse Polish Notation
        System.out.println("\nProblem 2: Evaluate Reverse Polish Notation");
        System.out.println("---------------------------");
        String[][] rpnTests = {
            {"2", "1", "+", "3", "*"},
            {"4", "13", "5", "/", "+"},
            {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}
        };
        for (String[] test : rpnTests) {
            System.out.println("Input: " + Arrays.toString(test) + " -> Output: " + evalRPN(test));
        }
        
        // Test Problem 3: Min Stack
        System.out.println("\nProblem 3: Min Stack");
        System.out.println("---------------------------");
        MinStack minStack = new MinStack();
        System.out.println("Push -2, Push 0, Push -3");
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("getMin() -> " + minStack.getMin());
        System.out.println("pop()");
        minStack.pop();
        System.out.println("top() -> " + minStack.top());
        System.out.println("getMin() -> " + minStack.getMin());
        
        // Test Problem 4: Implement Queue using Stacks
        System.out.println("\nProblem 4: Implement Queue using Stacks");
        System.out.println("---------------------------");
        MyQueue queue = new MyQueue();
        System.out.println("push(1), push(2), peek(), pop(), empty()");
        queue.push(1);
        queue.push(2);
        System.out.println("peek() -> " + queue.peek());
        System.out.println("pop() -> " + queue.pop());
        System.out.println("empty() -> " + queue.empty());
        
        // Test Problem 5: Next Greater Element
        System.out.println("\nProblem 5: Next Greater Element");
        System.out.println("---------------------------");
        int[][] ngeTests = {
            {4, 5, 2, 25},
            {13, 7, 6, 12},
            {1, 2, 3, 4, 5}
        };
        for (int[] test : ngeTests) {
            System.out.println("Input: " + Arrays.toString(test) + " -> Output: " + 
                              Arrays.toString(nextGreaterElement(test)));
        }
        
        // Test Problem 6: Daily Temperatures
        System.out.println("\nProblem 6: Daily Temperatures");
        System.out.println("---------------------------");
        int[][] tempTests = {
            {73, 74, 75, 71, 69, 72, 76, 73},
            {30, 40, 50, 60},
            {30, 60, 90}
        };
        for (int[] test : tempTests) {
            System.out.println("Input: " + Arrays.toString(test) + " -> Output: " + 
                              Arrays.toString(dailyTemperatures(test)));
        }
    }
}