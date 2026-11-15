
public class UnboundedArrayStack {
private static final int INITIAL_CAPACITY = 4;
private int[] data = new int[INITIAL_CAPACITY];
    private int size = 0;

    public void push(int x) {
        if (size == data.length) resize(data.length * 2);
        data[size++] = x;
    }

    public int pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        int x = data[--size];
        if (data.length > INITIAL_CAPACITY && size <= data.length / 4) {
            resize(Math.max(INITIAL_CAPACITY, data.length / 2));
        }
        
        return x;
    }

    public int peek()
     {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return data[size - 1];
    }
 public boolean isEmpty()
  { return size == 0; }
    public int size(

    ) { return size; }
    public int capacity()
     { return data.length; }

    private void resize(int newCapacity) {
        int[] copy = new int[newCapacity];
        for (int i = 0; i < size; i++) 
            copy[i] = data[i];
        data = copy;
        System.out.println("   [resize -> capacity " + newCapacity + "]");
    }

    public static void main(String[] args) {
        UnboundedArrayStack s = new UnboundedArrayStack();
        System.out.println("=== Unbounded Array Stack ===");
        System.out.println("-- pushing 1..20 --");
        for (int i = 1; i <= 20; i++) {
            s.push(i);
            System.out.println("push " + i + " | size=" + s.size() + " capacity=" + s.capacity());
        }
        System.out.println("peek = " + s.peek());
        System.out.println("-- popping everything --");
        while (!s.isEmpty()) {
            int v = s.pop();
            System.out.println("pop  " + v + " | size=" + s.size() + " capacity=" + s.capacity());
        }
        try { s.pop(); } catch (RuntimeException e) { System.out.println("pop on empty -> " + e.getMessage()); }
    }
}
