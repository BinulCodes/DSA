import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {
    private static class Node<E> {
        E data;
        Node<E> prev;
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
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    public void addLast(T item) {
        Node<T> node = new Node<>(item);
        if (tail == null) {
            head = tail = node;
        } else {
            node.prev = tail;
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
        Node<T> next = nodeAt(index);
        Node<T> prev = next.prev;
        Node<T> node = new Node<>(item);
        node.prev = prev;
        node.next = next;
        prev.next = node;
        next.prev = node;
        size++;
    }

    public T removeFirst() {
        if (head == null) throw new NoSuchElementException("List is empty");
        T item = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        size--;
        return item;
    }

    public T removeLast() {
        if (tail == null) throw new NoSuchElementException("List is empty");
        T item = tail.data;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        size--;
        return item;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index);
        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();
        Node<T> node = nodeAt(index);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        return node.data;
    }

    public boolean remove(T item) {
        int i = indexOf(item);
        if (i == -1) return false;
        removeAt(i);
        return true;
    }

    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index);
        return nodeAt(index).data;
    }

    public int indexOf(T item) {
        int i = 0;
        for (Node<T> cur = head; cur != null; cur = cur.next, i++) {
            if (item == null ? cur.data == null : item.equals(cur.data)) return i;
        }
        return -1;
    }

    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    public void reverse() {
        Node<T> cur = head;
        Node<T> temp = null;
        while (cur != null) {
            temp = cur.prev;
            cur.prev = cur.next;
            cur.next = temp;
            cur = cur.prev;
        }
        temp = head;
        head = tail;
        tail = temp;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public void displayForward() {
        StringBuilder sb = new StringBuilder("null <-> ");
        for (Node<T> cur = head; cur != null; cur = cur.next) {
            sb.append(cur.data).append(" <-> ");
        }
        System.out.println(sb.append("null"));
    }

    public void displayBackward() {
        StringBuilder sb = new StringBuilder("null <-> ");
        for (Node<T> cur = tail; cur != null; cur = cur.prev) {
            sb.append(cur.data).append(" <-> ");
        }
        System.out.println(sb.append("null"));
    }

    private Node<T> nodeAt(int index) {
        Node<T> cur;
        if (index < size / 2) {
            cur = head;
            for (int i = 0; i < index; i++) cur = cur.next;
        } else {
            cur = tail;
            for (int i = size - 1; i > index; i--) cur = cur.prev;
        }
        return cur;
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(20);
        list.addLast(30);
        list.addFirst(10);
        list.add(3, 40);
        list.add(2, 25);
        list.displayForward();
        list.displayBackward();
        System.out.println("Get(3): " + list.get(3));
        list.remove(25);
        System.out.println("RemoveFirst: " + list.removeFirst());
        System.out.println("RemoveLast: " + list.removeLast());
        list.displayForward();
        list.reverse();
        list.displayForward();
        list.displayBackward();
        System.out.println("Size: " + list.size());
    }
}
