import java.util.NoSuchElementException;

/**
 * Generic FIFO Queue implemented using a singly linked list with head and tail pointers.
 * Enqueue at the tail, dequeue from the head - both O(1).
 */
public class LinkedListFIFOQueue<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head; // front of the queue
    private Node<T> tail; // back of the queue
    private int size;

    public void enqueue(T item) {
        Node<T> node = new Node<>(item);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        T data = head.data;
        head = head.next;
        if (head == null) tail = null; // queue became empty
        size--;
        return data;
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        StringBuilder sb = new StringBuilder("FRONT -> ");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(" -> ");
            current = current.next;
        }
        sb.append(" <- BACK");
        System.out.println(sb);
    }

    // ---------- demo ----------
    public static void main(String[] args) {
        LinkedListFIFOQueue<String> queue = new LinkedListFIFOQueue<>();

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        System.out.print("After enqueues: ");
        queue.display();

        System.out.println("peek    = " + queue.peek());
        System.out.println("dequeue = " + queue.dequeue());
        System.out.println("dequeue = " + queue.dequeue());
        System.out.print("Now:            ");
        queue.display();
        System.out.println("size = " + queue.size());

        queue.dequeue();
        queue.dequeue();
        System.out.println("isEmpty = " + queue.isEmpty());

        try {
            queue.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("Underflow caught: cannot dequeue from an empty queue");
        }
    }
}
