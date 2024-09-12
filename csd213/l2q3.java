class MinimumStack {
    private int[] stack;  // Array to store the actual elements
    private int[] minStack;  // Array to store the minimum elements
    private int size;  // The current size of the stack
    private int capacity;  // The maximum capacity of the stack

    // Constructor to initialize the stack with a specific capacity
    public MinimumStack(int capacity) {
        this.capacity = capacity;
        this.stack = new int[capacity];
        this.minStack = new int[capacity];
        this.size = 0;
    }

    // Pushes an element onto the stack
    public void push(int val) {
        if (size >= capacity) {
            throw new IllegalStateException("Stack is full");
        }
        stack[size] = val;
        // If minStack is empty or the new value is less than or equal to the current minimum, update minStack
        if (size == 0 || val <= minStack[size - 1]) {
            minStack[size] = val;
        } else {
            minStack[size] = minStack[size - 1];
        }
        size++;
    }

    // Removes the element on the top of the stack
    public void pop() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        size--;
    }

    // Returns the element at the top of the stack but does not remove it
    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[size - 1];
    }

    // Gets the top element of the stack and also removes it from the stack
    public int top() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[--size];
    }

    // Retrieves the minimum element in the stack
    public int getMin() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return minStack[size - 1];
    }
}
    public class l2q3 {
    // Main method to demonstrate functionality
    public static void main(String[] args) {
        MinimumStack minStack = new MinimumStack(10);

        minStack.push(5);
        System.out.println("Current minimum: " + minStack.getMin()); // Output: 5
        minStack.push(2);
        System.out.println("Current minimum: " + minStack.getMin()); // Output: 2
        minStack.push(3);
        System.out.println("Current minimum: " + minStack.getMin()); // Output: 2
        minStack.push(1);
        System.out.println("Current minimum: " + minStack.getMin()); // Output: 1
        minStack.push(4);
        System.out.println("Current minimum: " + minStack.getMin()); // Output: 1

        System.out.println("Top element: " + minStack.top()); // Output: 4
        System.out.println("Current minimum after pop: " + minStack.getMin()); // Output: 1

        minStack.pop();
        System.out.println("Top element after pop: " + minStack.peek()); // Output: 1
        System.out.println("Current minimum after pop: " + minStack.getMin()); // Output: 1

        minStack.pop(); // Removing 1
        minStack.pop(); // Removing 3
        minStack.pop(); // Removing 2
        System.out.println("Top element after popping all but one: " + minStack.peek()); // Output: 5
        System.out.println("Final minimum: " + minStack.getMin()); // Output: 5
    }
}
