
public class QueueStackPushFriendly {

 
    private static class SimpleQueue {
        private static class Node {
            int data;
            Node next;
            Node(int data) { this.data = data; }
        }
  private Node front, rear;
        private int size;
void enqueue(int x) {
         Node n = new Node(x);
        if (rear == null) front = rear = n;
        else { rear.next = n; rear = n; }
        size++;
        }
 int dequeue() {
         int x = front.data;
         front = front.next;
        if (front == null) rear = null;
            size--;
        return x;
        }

        int peek() { return front.data; }
        int size() { return size; }
        boolean isEmpty() { return size == 0; }
    }

    private SimpleQueue q1 = new SimpleQueue();
    private SimpleQueue q2 = new SimpleQueue();

    // O(1)
    public void push(int x) {
        q1.enqueue(x);
    }

    // O(n)
    public int pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        while (q1.size() > 1) q2.enqueue(q1.dequeue());
        int top = q1.dequeue();
        swap();
        return top;
    }
 // O(n)
    public int peek() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        while (q1.size() > 1) q2.enqueue(q1.dequeue());
        int top = q1.peek();
        q2.enqueue(q1.dequeue());
        swap();
        return top;
    }
 public boolean isEmpty() { return q1.isEmpty(); }
 public int size() { return q1.size(); }

 private void swap() {
        SimpleQueue t = q1;
    q1 = q2;
    q2 = t;
    }
    public static void main(String[] args) {
        QueueStackPushFriendly s = new QueueStackPushFriendly();
        System.out.println("=== QueueStack - Push Friendly ===");
        for (int i = 1; i <= 5; i++) {
         s.push(i * 10);
         System.out.println("push " + (i * 10));
        }
        System.out.println("peek = " + s.peek());
        System.out.println("size = " + s.size());
        while (!s.isEmpty()) System.out.println("pop  = " + s.pop());
        try { s.pop(); } catch (RuntimeException e) { System.out.println("pop on empty -> " + e.getMessage()); }
    }
}
