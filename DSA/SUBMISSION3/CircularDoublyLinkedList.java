import java.util.NoSuchElementException;

/**
 * Generic Circular Doubly Linked List.
 * Every node has prev and next, and the last node links back to the first (and vice versa).
 */
public class CircularDoublyLinkedList<T> {

    private class Node {
        T data;
        Node prev, next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Insert at the beginning - O(1)
    public void addFirst(T data) {
        addLast(data);
        head = head.prev; // the new node becomes the head
    }

    // Insert at the end - O(1)
    public void addLast(T data) {
        Node node = new Node(data);
        if (head == null) {
            node.next = node;
            node.prev = node;
            head = node;
        } else {
            Node tail = head.prev;
            node.next = head;
            node.prev = tail;
            tail.next = node;
            head.prev = node;
        }
        size++;
    }

    // Insert at a given index (0..size) - O(n)
    public void add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else {
            Node next = nodeAt(index);
            Node prev = next.prev;
            Node node = new Node(data);
            node.prev = prev;
            node.next = next;
            prev.next = node;
            next.prev = node;
            size++;
        }
    }

    // Remove from the beginning - O(1)
    public T removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("List is empty");
        return unlink(head);
    }

    // Remove from the end - O(1)
    public T removeLast() {
        if (isEmpty()) throw new NoSuchElementException("List is empty");
        return unlink(head.prev);
    }

    // Remove at a given index - O(n)
    public T remove(int index) {
        checkIndex(index);
        return unlink(nodeAt(index));
    }

    // Remove the first occurrence of a value - O(n)
    public boolean remove(T value) {
        if (isEmpty()) return false;
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (equal(current.data, value)) {
                unlink(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T get(int index) {
        checkIndex(index);
        return nodeAt(index).data;
    }

    public T getFirst() {
        if (isEmpty()) throw new NoSuchElementException("List is empty");
        return head.data;
    }

    public T getLast() {
        if (isEmpty()) throw new NoSuchElementException("List is empty");
        return head.prev.data;
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    public int indexOf(T value) {
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (equal(current.data, value)) return i;
            current = current.next;
        }
        return -1;
    }

    // Move the head 'steps' positions forward (negative = backward). Useful for circular behaviour.
    public void rotate(int steps) {
        if (size <= 1) return;
        steps %= size;
        if (steps >= 0) {
            for (int i = 0; i < steps; i++) head = head.next;
        } else {
            for (int i = 0; i < -steps; i++) head = head.prev;
        }
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        for (int i = 0; i < size; i++) {
            sb.append(current.data);
            if (i < size - 1) sb.append(" <-> ");
            current = current.next;
        }
        sb.append("] (circular)");
        System.out.println(sb);
    }

    public void displayReverse() {
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder("[");
        Node current = head.prev;
        for (int i = 0; i < size; i++) {
            sb.append(current.data);
            if (i < size - 1) sb.append(" <-> ");
            current = current.prev;
        }
        sb.append("] (reverse)");
        System.out.println(sb);
    }

    // ---------- helpers ----------

    private Node nodeAt(int index) {
        Node current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) current = current.next;
        } else {
            current = head.prev; // tail
            for (int i = size - 1; i > index; i--) current = current.prev;
        }
        return current;
    }

    private T unlink(Node node) {
        T data = node.data;
        if (size == 1) {
            head = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            if (node == head) head = node.next;
        }
        size--;
        return data;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private boolean equal(T a, T b) {
        return a == null ? b == null : a.equals(b);
    }

    // ---------- demo ----------
    public static void main(String[] args) {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();

        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addFirst(5);
        System.out.print("After adds:        ");
        list.display();

        list.add(2, 15);
        System.out.print("add(2, 15):        ");
        list.display();
        System.out.print("Reverse:           ");
        list.displayReverse();

        System.out.println("get(3) = " + list.get(3));
        System.out.println("contains(20) = " + list.contains(20));

        list.rotate(2);
        System.out.print("rotate(2):         ");
        list.display();
        list.rotate(-2);
        System.out.print("rotate(-2):        ");
        list.display();

        System.out.println("removeFirst = " + list.removeFirst());
        System.out.println("removeLast  = " + list.removeLast());
        System.out.println("remove(1)   = " + list.remove(1));
        System.out.print("Now:               ");
        list.display();

        list.remove(Integer.valueOf(15));
        System.out.print("remove value 15:   ");
        list.display();
        System.out.println("size = " + list.size());
    }
}
