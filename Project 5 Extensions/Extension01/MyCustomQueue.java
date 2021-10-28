/*
Name: Parth Parth
Date: 10/27/2021
File: MyCustomQueue.java
Section: A
*/

public class MyCustomQueue<T> {
  private Object[] queue;
  private int size;

  public MyCustomQueue() {
    this.queue = new Object[10];
    this.size = 0;
  }

  public void offer(T item) {
    // if full, double the size of the queue
    if (this.size == this.queue.length) {
      this.resize();
    }
    this.queue[this.size] = item;
    this.size++;
  }

  public T poll() {
    // if empty, return null
    if (this.size == 0) {
      return null;
    }

    // get item at front of queue
    T item = (T) this.queue[0];
    // move all items in queue one index to the left
    for (int i = 0; i < this.size - 1; i++) {
      this.queue[i] = this.queue[i + 1];
    }
    this.size--;
    return item;
  }

  public T peek() {
    if (this.size == 0) {
      return null;
    }
    return (T) this.queue[0];
  }

  public T get(int index) {
    if (index < 0 || index >= this.size) {
      return null;
    }
    return (T) this.queue[index];
  }

  public int size() {
    return this.size;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  private void resize() {
    Object[] newQueue = new Object[this.queue.length * 2];
    for (int i = 0; i < this.queue.length; i++) {
      newQueue[i] = this.queue[i];
    }
    this.queue = newQueue;
  }

  public static void main(String[] args) {
    MyCustomQueue<Integer> q = new MyCustomQueue<>();

    System.out.println("Adding 4, 5, 6");
    q.offer(4);
    q.offer(5);
    q.offer(6);

    System.out.println("Peek: " + q.peek());
    System.out.println("Poll: " + q.poll());
    System.out.println("Peek: " + q.peek());
    System.out.println("Poll: " + q.poll());
    System.out.println("Adding 7");
    q.offer(7);
    System.out.println("Peek: " + q.peek());
    System.out.println("Poll: " + q.poll());
    System.out.println("Peek: " + q.peek());
    System.out.println("Poll: " + q.poll());
    System.out.println("Peek: " + q.peek());

    System.out.println("Adding 4, 5, 6\nPrinting using iterator from now");
    q.offer(4);
    q.offer(5);
    q.offer(6);
  }
}
