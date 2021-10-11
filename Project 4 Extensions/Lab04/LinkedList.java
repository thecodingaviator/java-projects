/*
Name: Parth Parth
Date: 10/04/2021
File: LinkedList.java
Section: A
*/

import java.util.Iterator;    // defines the Iterator interface
import java.util.ArrayList;   
import java.util.Collections; // contains a shuffle function

public class LinkedList<T> implements Iterable<T> {
  private class Node<T> {
    T data;
    Node<T> next;

    public Node(T item) {
      this.next=null;
      this.data=item;
    }

    public T getThing() {
      return this.data;
    }

    public void setNext(Node<T> n) {
      this.next=n;
    }

    public Node<T> getNext() {
      return this.next;
    }
  }

  Node<T> head;
  int size;

  public LinkedList() {
    // just calling clear since it does the same as the contructor
    this.clear();
  }

  public void clear() {
    this.head=null;
    this.size=0;
  }

  public int size() {
    return this.size;
  }

  public void addFirst(T item) {
    Node<T> temp=new Node<T>(item);
    if(this.size()==0) {
      this.head=temp;
    }
    else {
      temp.setNext(this.head);
      this.head=temp;
    }
    size++;
  }

  public void addLast(T item) {
    Node<T> temp=new Node<T>(item);
    if(this.size()==0) {
      this.head=temp;
    }
    else {
      this.getLast().setNext(temp);
    }
    this.size++;
  }

  public void add(int index, T item) {
    Node<T> temp=this.head;

    Node<T> current=new Node<T>(item);

    if(index==0) {
      this.addFirst(item);
    }
    else if(index==this.size) {
      this.addLast(item);
    }
    else {
      for(int i=0; i<index-1; i++) {
        temp=temp.getNext();
      }

      current.setNext(temp.getNext());
      temp.setNext(current);
      size++;
    }
  }

  // helper method to get last node
  private Node<T> getLast() {
    Node<T> current=this.head;
    if(current==null) {
      return null;
    }
    while(current.getNext()!=null) {
      current=current.getNext();
    }
    return current;
  }

  // helper method to get node at index-1
  private Node<T> getPrevNode(int index) {
    index-=2;
    Node<T> current=this.head;
    while(index>0) {
      current=current.getNext();
      index--;
    }
    return current;
  }

  public void remove(int index) {
    if(index==0) {
      this.head=this.head.getNext();
    }
    else {
      Node<T> temp=this.head;
      for(int i=0; i<index-1; i++) {
        temp=temp.getNext();
      }

      temp.setNext(temp.getNext().getNext());
      
    }
    this.size--;
  }

  private class LLIterator implements Iterator<T> {
    Node<T> nextNode;

    public LLIterator(Node<T> head) {
      this.nextNode=head;
    }

    public boolean hasNext() {
      return nextNode!=null;
    }

    public T next() {
      T item=nextNode.getThing();
      nextNode=nextNode.next;
      return item;
    }
  }

  public Iterator<T> iterator() {
    return new LLIterator(this.head);
  }

  public ArrayList<T> toArrayList() {
    ArrayList<T> list=new ArrayList<T>();
    Node<T> temp=this.head;

    while(temp!=null) {
      list.add(temp.data);
      temp=temp.getNext();
    }

    return list;
  }

  public ArrayList<T> toShuffledList() {
    ArrayList<T> list=this.toArrayList();
    Collections.shuffle(list);
    return list;
  }
}