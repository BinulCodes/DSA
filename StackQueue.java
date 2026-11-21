import java.util.NoSuchElementException;

public class StackQueue<T> {
    private static class ArrayStack<E> {
        private Object[] data = new Object[8];
        private int size = 0;

        void push(E item) {
            if (size == data.length) {
                Object[] bigger = new Object[data.length * 2];
                System.arraycopy(data, 0, bigger, 0, size);
                data = bigger;
            }
            data[size++] = item;
        }

        @SuppressWarnings("unchecked")
        E pop() {
            if (size == 0) throw new NoSuchElementException("Stack is empty");
            E item = (E) data[--size];
            data[size] = null;
            return item;
        }

        @SuppressWarnings("unchecked")
        E peek() {
            if (size == 0) throw new NoSuchElementException("Stack is empty");
            return (E) data[size - 1];
        }

        boolean isEmpty() {
            return size == 0;
        }

        int size() {
            return size;
        }
    }

    private final ArrayStack<T> mainStack = new ArrayStack<>();
    private final ArrayStack<T> helperStack = new ArrayStack<>();

    public void enqueue(T item) {
        while (!mainStack.isEmpty()) {
            helperStack.push(mainStack.pop());
        }
        mainStack.push(item);
        while (!helperStack.isEmpty()) {
            mainStack.push(helperStack.pop());
        }
    }

    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return mainStack.pop();
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return mainStack.peek();
    }

    public boolean isEmpty() {
        return mainStack.isEmpty();
    }

    public int size() {
        return mainStack.size();
    }

    public static void main(String[] args) {
        StackQueue<Integer> q = new StackQueue<>();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        System.out.println("Front: " + q.peek());
        System.out.println("Dequeue: " + q.dequeue());
        q.enqueue(40);
        System.out.println("Dequeue: " + q.dequeue());
        System.out.println("Dequeue: " + q.dequeue());
        System.out.println("Dequeue: " + q.dequeue());
        System.out.println("Is empty? " + q.isEmpty());
        try {
            q.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
