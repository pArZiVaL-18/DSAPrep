import java.util.Stack;

public class StackApplications {
    // Valid parentheses check
    public static boolean checkBalancedParentheses(String expression) { // Time Complexity: O(n)
        Stack<Character> stack = new Stack<>();

        System.out.println("\nChecking expression: " + expression);
        System.out.println("----------------------------------------");

        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);

            // If opening bracket, push to stack
            if (current == '(' || current == '[' || current == '{') {
                stack.push(current);
                System.out.println("Push: " + current + "\tStack: " + stack);
            }

            // If closing bracket, check if stack is empty or if matching pair exists
            else if (current == ')' || current == ']' || current == '}') {
                if (stack.isEmpty()) {
                    System.out.println("Error: Extra closing bracket " + current);
                    return false;
                }

                char top = stack.pop();
                System.out.println("Pop: " + top + "\tStack: " + stack);

                // Check if popped bracket matches current closing bracket
                if ((current == ')' && top != '(') ||
                        (current == ']' && top != '[') ||
                        (current == '}' && top != '{')) {
                    System.out.println("Error: Mismatched brackets. Found " + current +
                            " but expected closing bracket for " + top);
                    return false;
                }
            }
        }

        // Check if stack is empty at the end
        if (!stack.isEmpty()) {
            System.out.println("Error: Extra opening brackets: " + stack);
            return true;
        } else {
            System.out.println("Success: Brackets are balanced");
        }

        return false;
    }

    // Converting infix expression to postfix expression
    public static String infixToPostfix(String infix) { // Time Complexity: O(n)
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        System.out.println("\nConverting infix to postfix: " + infix);
        System.out.println("----------------------------------------");
        System.out.println("Step\tChar\tAction\t\tStack\t\tPostfix");
        System.out.println("----------------------------------------");

        int step = 1;

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            // If the character is an operand, add it to the postfix expression
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
                System.out.printf("%-8d%-8c%-16s%-16s%s\n", step++, c, "Add to output", stack, postfix);
            }

            // If the character is an opening bracket, push it to the stack
            else if (c == '(') {
                stack.push(c);
                System.out.printf("%-8d%-8c%-16s%-16s%s\n", step++, c, "Push to stack", stack, postfix);
            }

            // If the character is a closing bracket, pop until opening bracket
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                    System.out.printf("%-8d%-8c%-16s%-16s%s\n", step++, c, "Pop to output", stack, postfix);
                }

                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop(); // Remove the opening bracket
                    System.out.printf("%-8d%-8c%-16s%-16s%s\n", step++, c, "Pop and discard", stack, postfix);
                }
            }

            // If the character is an operator
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                    System.out.printf("%-8d%-8c%-16s%-16s%s\n", step++, c, "Pop higher prec", stack, postfix);
                }
                stack.push(c);
                System.out.printf("%-8d%-8c%-16s%-16s%s\n", step++, c, "Push to stack", stack, postfix);
            }
        }

        // Pop all remaining operators from the stack
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                System.out.println("Error: Mismatched parentheses");
                return "Error: Mismatched parentheses";
            }
            postfix.append(stack.pop());
            System.out.printf("%-8d%-8s%-16s%-16s%s\n", step++, "End", "Pop to output", stack, postfix);
        }

        System.out.println("\nFinal postfix expression: " + postfix);
        return postfix.toString();
    }

    // Helper method
    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // Postfix Expression Evaluation
    public static int evaluatePostfix(String postfix) {
        Stack<Integer> stack = new Stack<>();

        System.out.println("\nEvaluating postfix expression: " + postfix);
        System.out.println("----------------------------------------");
        System.out.println("Step\tChar\tAction\t\tStack");
        System.out.println("----------------------------------------");

        int step = 1;

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            // If the character is an operand, push it to the stack
            if (Character.isDigit(c)) {
                stack.push(c - '0'); // Convert char to int
                System.out.printf("%-8d%-8c%-16s%s\n", step++, c, "Push operand", stack);
            }

            // If the character is an operator, pop two operands and apply the operator
            else {
                if (stack.size() < 2) {
                    System.out.println("Error: Invalid postfix expression");
                    return -1;
                }

                int val1 = stack.pop();
                int val2 = stack.pop();

                System.out.printf("%-8d%-8c%-16s%s\n", step++, c, "Pop two operands", stack);

                switch (c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;
                    case '-':
                        stack.push(val2 - val1);
                        break;
                    case '*':
                        stack.push(val2 * val1);
                        break;
                    case '/':
                        stack.push(val2 / val1);
                        break;
                }

                System.out.printf("%-8d%-8c%-16s%s\n", step++, c, "Push result", stack);
            }
        }

        if (stack.size() != 1) {
            System.out.println("Error: Invalid postfix expression");
            return -1;
        }

        int result = stack.pop();
        System.out.println("\nFinal result: " + result);
        return result;
    }

    // Implementing undo functionality
    public static void demonstrateUndoFunctionality() {
        Stack<String> undoStack = new Stack<>();
        String document = "";

        System.out.println("\nDemonstrating Undo Functionality in a Text Editor");
        System.out.println("----------------------------------------");

        // Simulate typing text
        addText(undoStack, document, "Hello");
        document = "Hello";

        addText(undoStack, document, " World");
        document = "Hello World";

        addText(undoStack, document, "! How are you?");
        document = "Hello World! How are you?";

        // Perform undo operations
        System.out.println("\nPerforming undo operations:");

        document = undo(undoStack, document);
        document = undo(undoStack, document);

        // Add more text
        addText(undoStack, document, " Universe!");
        document = "Hello Universe!";

        // Undo again
        document = undo(undoStack, document);
    }

    /**
     * Helper method to add text and save state for undo
     */
    private static void addText(Stack<String> undoStack, String currentText, String textToAdd) {
        undoStack.push(currentText);
        String newText = currentText + textToAdd;
        System.out.println("Added: '" + textToAdd + "'\tCurrent text: '" + newText + "'\tUndo stack: " + undoStack);
    }

    /**
     * Helper method to perform undo operation
     */
    private static String undo(Stack<String> undoStack, String currentText) {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo\tCurrent text: '" + currentText + "'\tUndo stack: " + undoStack);
            return currentText;
        }

        String previousText = undoStack.pop();
        System.out.println("Undo\t\tCurrent text: '" + previousText + "'\tUndo stack: " + undoStack);
        return previousText;
    }

    /**
     * Application 5: Implementing browser history (back button)
     */
    public static void demonstrateBrowserHistory() {
        Stack<String> history = new Stack<>();
        String currentPage = "Home Page";

        System.out.println("\nDemonstrating Browser History Navigation");
        System.out.println("----------------------------------------");
        System.out.println("Starting page: " + currentPage);

        // Visit some pages
        visitPage(history, currentPage, "Search Results");
        currentPage = "Search Results";

        visitPage(history, currentPage, "Product Page");
        currentPage = "Product Page";

        visitPage(history, currentPage, "Shopping Cart");
        currentPage = "Shopping Cart";

        visitPage(history, currentPage, "Checkout");
        currentPage = "Checkout";

        // Go back a few times
        System.out.println("\nGoing back:");
        currentPage = goBack(history, currentPage);
        currentPage = goBack(history, currentPage);

        // Visit a new page after going back
        visitPage(history, currentPage, "About Us");
        currentPage = "About Us";

        // Try going back again
        currentPage = goBack(history, currentPage);
        currentPage = goBack(history, currentPage);
        currentPage = goBack(history, currentPage);
    }

    /**
     * Helper method to visit a page and update history
     */
    private static void visitPage(Stack<String> history, String currentPage, String newPage) {
        history.push(currentPage);
        System.out.println("Navigated to: " + newPage + "\tHistory stack: " + history);
    }

    /**
     * Helper method to go back to previous page
     */
    private static String goBack(Stack<String> history, String currentPage) {
        if (history.isEmpty()) {
            System.out.println("Can't go back. This is the first page.\tCurrent page: " + currentPage);
            return currentPage;
        }

        String previousPage = history.pop();
        System.out.println("Went back to: " + previousPage + "\tHistory stack: " + history);
        return previousPage;
    }

    /**
     * Main method to demonstrate all stack applications
     */
    public static void main(String[] args) {
        System.out.println("===== STACK APPLICATIONS DEMONSTRATION =====\n");

        // Application 1: Balanced Parentheses
        System.out.println("\n1. CHECKING BALANCED PARENTHESES");
        System.out.println("=============================");
        checkBalancedParentheses("(a+b)*{c-d}");
        checkBalancedParentheses("(a+b))*c");
        checkBalancedParentheses("((a+b)*(c-d)");

        // Application 2: Infix to Postfix Conversion
        System.out.println("\n2. INFIX TO POSTFIX CONVERSION");
        System.out.println("=============================");
        infixToPostfix("a+b*c");
        infixToPostfix("(a+b)*(c-d)");

        // Application 3: Postfix Evaluation
        System.out.println("\n3. POSTFIX EXPRESSION EVALUATION");
        System.out.println("=============================");
        evaluatePostfix("23+4*");
        evaluatePostfix("234*+");

        // Application 4: Undo Functionality
        System.out.println("\n4. UNDO FUNCTIONALITY");
        System.out.println("=============================");
        demonstrateUndoFunctionality();

        // Application 5: Browser History
        System.out.println("\n5. BROWSER HISTORY NAVIGATION");
        System.out.println("=============================");
        demonstrateBrowserHistory();
    }
}