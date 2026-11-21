import java.util.NoSuchElementException;

public class ArrayCircularQueue<T> {
    private final Object[] data;
    private int front = 0;
    private int rear = -1;
    private int size = 0;

    public ArrayCircularQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be > 0");
        data = new Object[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    public void enqueue(T item) {
        if (isFull()) throw new IllegalStateException("Queue is full");
        rear = (rear + 1) % data.length;
        data[rear] = item;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        T item = (T) data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return (T) data[front];
    }

    public void display() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) sb.append(", ");
            sb.append(data[(front + i) % data.length]);
        }
        System.out.println(sb.append("]"));
    }

    public static void main(String[] args) {
        ArrayCircularQueue<Integer> q = new ArrayCircularQueue<>(4);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.display();
        System.out.println("Full? " + q.isFull());
        System.out.println("Dequeue: " + q.dequeue());
        System.out.println("Dequeue: " + q.dequeue());
        q.enqueue(5);
        q.enqueue(6);
        q.display();
        System.out.println("Front: " + q.peek());
        try {
            q.enqueue(7);
        } catch (IllegalStateException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
