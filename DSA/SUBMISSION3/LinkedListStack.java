import java.util.EmptyStackException;

/**
 * Generic Stack (LIFO) implemented using a singly linked list.
 * The top of the stack is the head of the list, so push and pop are both O(1).
 */
public class LinkedListStack<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> top;
    private int size;

    public void push(T item) {
        Node<T> node = new Node<>(item);
        node.next = top;
        top = node;
        size++;
    }

    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        top = null;
        size = 0;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        StringBuilder sb = new StringBuilder("TOP -> ");
        Node<T> current = top;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(" -> ");
            current = current.next;
        }
        System.out.println(sb);
    }

    // ---------- demo ----------
    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.print("After pushes: ");
        stack.display();

        System.out.println("peek = " + stack.peek());
        System.out.println("pop  = " + stack.pop());
        System.out.println("pop  = " + stack.pop());
        System.out.print("Now:          ");
        stack.display();
        System.out.println("size = " + stack.size());

        stack.pop();
        stack.pop();
        System.out.println("isEmpty = " + stack.isEmpty());

        try {
            stack.pop();
        } catch (EmptyStackException e) {
            System.out.println("Underflow caught: cannot pop from an empty stack");
        }
    }
}
