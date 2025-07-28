# Stack Data Structure

## Introduction
A stack is a linear data structure that follows the Last-In-First-Out (LIFO) principle. This means that the last element added to the stack will be the first one to be removed. Think of it like a stack of plates - you can only take the top plate off, and you can only add a new plate to the top.

## Basic Operations

1. **Push**: Add an element to the top of the stack
   - Time Complexity: O(1)

2. **Pop**: Remove the top element from the stack
   - Time Complexity: O(1)

3. **Peek/Top**: View the top element without removing it
   - Time Complexity: O(1)

4. **isEmpty**: Check if the stack is empty
   - Time Complexity: O(1)

5. **Size**: Get the number of elements in the stack
   - Time Complexity: O(1)

## Implementations

This directory contains three different implementations of a stack:

### 1. [StackArrayList.java](./StackArrayList.java)
Implements a stack using Java's ArrayList.

**Advantages:**
- Better memory locality and cache performance
- Less memory overhead per element
- Dynamic resizing handled by ArrayList

**Disadvantages:**
- Potential performance hit when resizing is needed

### 2. [StackLinkedList.java](./StackLinkedList.java)
Implements a stack using a singly linked list.

**Advantages:**
- Constant time insertions and deletions
- Dynamic size without resizing operations
- No wasted space for unused capacity

**Disadvantages:**
- Extra memory overhead for node pointers
- Poor cache locality

### 3. [StackComparison.java](./StackComparison.java)
Compares both implementations and demonstrates their differences in terms of:
- Performance
- Memory usage
- Advantages and disadvantages
- Use cases

## Common Applications of Stacks

1. **Function Call Management**
   - Call stack for managing function calls and returns
   - Recursion implementation

2. **Expression Evaluation**
   - Infix to postfix conversion
   - Evaluating postfix expressions
   - Checking balanced parentheses

3. **Undo Mechanisms**
   - Implementing undo functionality in applications
   - Browser history (back button)

4. **Backtracking Algorithms**
   - Maze solving
   - Game state management

5. **Syntax Parsing**
   - Compiler syntax checking
   - HTML/XML tag matching

## Time and Space Complexity

| Operation | Time Complexity | Space Complexity |
|-----------|-----------------|------------------|
| Push      | O(1)            | O(1)             |
| Pop       | O(1)            | O(1)             |
| Peek      | O(1)            | O(1)             |
| isEmpty   | O(1)            | O(1)             |
| Size      | O(1)            | O(1)             |

The overall space complexity of a stack is O(n), where n is the number of elements in the stack.

## Practice Problems

To strengthen your understanding of stacks, try solving these common problems:

1. **Valid Parentheses**: Check if a string containing parentheses, brackets, and curly braces is valid.

2. **Evaluate Reverse Polish Notation**: Evaluate an arithmetic expression in Reverse Polish Notation.

3. **Min Stack**: Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

4. **Implement Queue using Stacks**: Implement a first-in-first-out (FIFO) queue using only two stacks.

5. **Next Greater Element**: Find the next greater element for each element in an array.

## Learning Path

1. Start with the basic stack operations in either implementation.
2. Compare the two implementations using StackComparison.java.
3. Implement some of the practice problems mentioned above.
4. Try to implement a generic stack that can store any data type using Java Generics.
5. Explore advanced applications like expression evaluation or backtracking algorithms.

## Additional Resources

- [GeeksforGeeks - Stack Data Structure](https://www.geeksforgeeks.org/stack-data-structure/)
- [Visualgo - Stack Visualization](https://visualgo.net/en/list)
- [LeetCode Stack Problems](https://leetcode.com/tag/stack/)