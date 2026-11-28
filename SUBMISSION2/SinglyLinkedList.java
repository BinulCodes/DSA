import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T item) {
        Node<T> node = new Node<>(item);
        node.next = head;
        head = node;
        if (tail == null) tail = node;
        size++;
    }

    public void addLast(T item) {
        Node<T> node = new Node<>(item);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void add(int index, T item) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index: " + index);
        if (index == 0) {
            addFirst(item);
            return;
        }
        if (index == size) {
            addLast(item);
            return;
        }
        Node<T> prev = nodeAt(index - 1);
        Node<T> node = new Node<>(item);
        node.next = prev.next;
        prev.next = node;
        size++;
    }

    public T removeFirst() {
        if (head == null) throw new NoSuchElementException("List is empty");
        T item = head.data;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return item;
    }

    public T removeLast() {
        if (head == null) throw new NoSuchElementException("List is empty");
        if (head == tail) return removeFirst();
        Node<T> prev = head;
        while (prev.next != tail) prev = prev.next;
        T item = tail.data;
        prev.next = null;
        tail = prev;
        size--;
        return item;
    }

    public boolean remove(T item) {
        Node<T> prev = null;
        Node<T> cur = head;
        while (cur != null) {
            if (equal(cur.data, item)) {
                if (prev == null) {
                    removeFirst();
                } else {
                    prev.next = cur.next;
                    if (cur == tail) tail = prev;
                    size--;
                }
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index);
        return nodeAt(index).data;
    }

    public int indexOf(T item) {
        int i = 0;
        for (Node<T> cur = head; cur != null; cur = cur.next, i++) {
            if (equal(cur.data, item)) return i;
        }
        return -1;
    }

    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    public void reverse() {
        Node<T> prev = null;
        Node<T> cur = head;
        tail = head;
        while (cur != null) {
            Node<T> next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        head = prev;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public void display() {
        StringBuilder sb = new StringBuilder();
        for (Node<T> cur = head; cur != null; cur = cur.next) {
            sb.append(cur.data).append(" -> ");
        }
        System.out.println(sb.append("null"));
    }

    private Node<T> nodeAt(int index) {
        Node<T> cur = head;
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur;
    }

    private boolean equal(T a, T b) {
        return a == null ? b == null : a.equals(b);
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addLast(20);
        list.addLast(30);
        list.addFirst(10);
        list.add(3, 40);
        list.display();
        list.add(2, 25);
        list.display();
        System.out.println("Get(2): " + list.get(2));
        System.out.println("IndexOf(30): " + list.indexOf(30));
        list.remove(25);
        System.out.println("RemoveFirst: " + list.removeFirst());
        System.out.println("RemoveLast: " + list.removeLast());
        list.display();
        list.reverse();
        list.display();
        System.out.println("Size: " + list.size());
    }
}
