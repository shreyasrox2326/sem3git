class SimpleStack {
    private char[] stack;  // Array to store stack elements
    private int size;  // Current size of the stack
    private int capacity;  // Maximum capacity of the stack

    // Constructor to initialize the stack with a specific capacity
    public SimpleStack(int capacity) {
        this.capacity = capacity;
        this.stack = new char[capacity];
        this.size = 0;
    }

    // Pushes an element onto the stack
    public void push(char val) {
        if (size >= capacity) {
            throw new IllegalStateException("Stack is full");
        }
        stack[size++] = val;
    }

    // Removes the element on the top of the stack
    public void pop() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        size--;
    }

    // Returns the element at the top of the stack but does not remove it
    public char peek() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[size - 1];
    }

    // Returns true if the stack is empty
    public boolean isEmpty() {
        return size == 0;
    }
}
public class l2q4 {

    // Function to check if the brackets in the input string are balanced
    public static boolean isBalanced(String s) {
        // Initialize the stack with a reasonable capacity
        SimpleStack stack = new SimpleStack(s.length());

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                // If the character is an opening bracket, push it onto the stack
                stack.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                // If the character is a closing bracket
                if (stack.isEmpty()) {
                    // No opening bracket for this closing bracket
                    return false;
                }

                char top = stack.peek();
                if ((ch == ')' && top == '(') ||
                    (ch == '}' && top == '{') ||
                    (ch == ']' && top == '[')) {
                    // The top of the stack matches the current closing bracket
                    stack.pop();
                } else {
                    // Mismatch between the top of the stack and the current closing bracket
                    return false;
                }
            }
        }

        // If the stack is empty, all opening brackets have been matched
        return stack.isEmpty();
    }

    // Main method to test the function
    public static void main(String[] args) {
        String[] testCases = {
            "{[()]}",
            "{[(])}",
            "{{[[(())]]}}",
            "({[({})]})",
            "((()))",
            "[{]}",
            "{[}"
        };

        for (String testCase : testCases) {
            System.out.println("Input: " + testCase);
            System.out.println("Is balanced: " + isBalanced(testCase));
            System.out.println();
        }
    }
}
