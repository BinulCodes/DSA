
public class StackQueueEnqueueFriendly {

  
    private static class SimpleStack {
    private static class Node {
        int data;
        Node next;
         Node(int data, Node next) { this.data = data; this.next = next; }
        }

 private Node top;
private int size;

     void push(int x) { top = new Node(x, top); size++; }

  int pop() {
      int x = top.data;
        top = top.next;
        size--;
        return x;
        }

    int peek() { return top.data; }
     int size() { return size; }
    boolean isEmpty() { return size == 0; }
    }

private final SimpleStack s1 = new SimpleStack();
private final SimpleStack s2 = new SimpleStack();

    // O(1)
public void enqueue(int x) {
   s1.push(x);
    }

    // O(n)
public int dequeue() {
    if (isEmpty()) throw new RuntimeException("Queue is empty");
    while (!s1.isEmpty()) s2.push(s1.pop());
    int front = s2.pop();
    while (!s2.isEmpty()) s1.push(s2.pop());
     return front;
    }
    // O(n)
public int peek() {
     if (isEmpty()) throw new RuntimeException("Queue is empty");
     while (!s1.isEmpty()) s2.push(s1.pop());
    int front = s2.peek();
     while (!s2.isEmpty()) s1.push(s2.pop());
     return front;
    }

 public boolean isEmpty() { return s1.isEmpty(); }
    public int size() { return s1.size(); }

    public static void main(String[] args) {
        StackQueueEnqueueFriendly q = new StackQueueEnqueueFriendly();
        System.out.println("=== StackQueue - Enqueue Friendly ===");
        for (int i = 1; i <= 5; i++) {
            q.enqueue(i * 10);
            System.out.println("enqueue " + (i * 10));
        }
        System.out.println("peek = " + q.peek());
        System.out.println("size = " + q.size());
        while (!q.isEmpty()) System.out.println("dequeue = " + q.dequeue());
        try { q.dequeue(); } catch (RuntimeException e) { System.out.println("dequeue on empty -> " + e.getMessage()); }
    }
}
