/*
Name: Parth Parth
Date: 10/25/2021
File: MyQueue.java
Section: A
*/

// CLASS NOT BEIN USED FOR THIS EXTENSION

import java.util.Iterator;

public class MyQueue<T> implements Iterable<T> {
  private Node<T> head;
  private Node<T> tail;


  MyQueue() {
    this.head=null;
    this.tail=null;
  }

  private class Node<T> {
    private Node<T> prev;
    private Node<T> next;
    private T data;

    Node(T data) {
      this.prev=null;
      this.next=null;
      this.data=data;
    }

    public Node<T> getPrev() {
      return this.prev;
    }

    public void setPrev(Node<T> prev) {
      this.prev=prev;
    }

    public Node<T> getNext() {
      return this.next;
    }

    public void setNext(Node<T> next) {
      this.next=next;
    }

    public T getData() {
      return this.data;
    }
  }

  public boolean offer(T item) {
    // if empty, add to front
    if(this.head==null) {
      this.head=new Node<T>(item);
      this.tail=this.head;
      return true;
    }
    // else add to end
    else {
      Node<T> newNode=new Node<T>(item);
      newNode.setPrev(this.tail);
      this.tail.setNext(newNode);
      this.tail=newNode;
      return true;
    }
  }

  public T peek() {
    if(this.head==null) {
      return null;
    }
    else {
      return this.head.getData();
    }
  }

  public T poll() {
    // if empty, return null
    if(this.head==null) {
      return null;
    }
    // else
    else {
      // if only one node is left, set head and tail to null
      T data=this.head.getData();
      if(this.head==this.tail) {
        this.head=null;
        this.tail=null;
      }
      // else set head to next node
      else {
        this.head=this.head.getNext();
        this.head.setPrev(null);
      }
      return data;
    }
  }

  private class QueueIterator implements Iterator<T> {
    Node<T> nextNode;

    public QueueIterator(Node<T> head) {
      this.nextNode=head;
    }

    public boolean hasNext() {
      return nextNode!=null;
    }

    public T next() {
      T item=nextNode.getData();
      nextNode=nextNode.next;
      return item;
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new QueueIterator(this.head);
  }

  public static void main(String[] args) {
    MyQueue<Integer> q=new MyQueue<>();

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
    for(Integer e: q) {
      System.out.println(e);
    }
    System.out.println("Poll: " + q.poll());
    System.out.println("Poll: " + q.poll());
    System.out.println("Adding 9 and 10");
    q.offer(9);
    q.offer(10);
    for(Integer e: q) {
      System.out.println(e);
    }
    System.out.println("Polling until empty");
    for(Integer e: q) {
      System.out.println(q.poll());
    }
    System.out.println("Peeking now (should be null): " + q.peek());
  }
}