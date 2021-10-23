/*
Name: Parth Parth
Date: 10/23/2021
File: ArrayList.java
Section: A
*/

import java.util.Iterator;
import java.util.Random;

public class ArrayList<T> implements Iterable<T> {
  private Object[] list;
  private int index;

  public ArrayList() {
    this.list=new Object[10];
    this.index=0;
  }

  public int size() {
    return this.index+1;
  }

  public void add(T item) {
    // if space exists, add
    if(this.index<this.list.length-1) {
      this.list[this.index++]=item;
    }
    // else expand array, copy and add
    else {
      Object[] temp=new Object[this.list.length*2];
      for(int i=0; i<this.list.length; i++) {
        temp[i]=this.list[i];
      }
      this.list=temp;
      this.list[this.index++]=item;
    }
  }

  public void add(int index, T item) {
    if(index>this.index) {
      System.out.println("Cannot add " + item + " at index " + index + ". Out of bounds");
    }
    else if(this.index+1<this.list.length) {
      for(int i=this.index; i>index; i--) {
        this.list[i+1]=this.list[i];
      }
      this.list[index+1]=item;
      this.index++;
    }
    else {
      Object[] temp=new Object[this.list.length*2];
      for(int i=0; i<this.list.length; i++) {
        temp[i]=this.list[i];
      }
      for(int i=this.index; i>index; i--) {
        temp[i+1]=temp[i];
      }
      this.list=temp;
      this.list[index+1]=item;
      this.index++;
    }
  }

  public void set(int index, T element) {
    this.list[index]=element;
  }

  public T get(int index) {
    return index<this.list.length?(T) this.list[index]:null;
  }

  public Object remove(int index) {
    Object item=this.list[index];
    // copy one element back for all remaining
    for(int i=index; i<this.list.length-1; i++) {
      this.list[i]=this.list[i+1];
    }
    return item;
  }

  public String toString() {
    String result="";
    for(int i=0; i<this.list.length; i++) {
      if(this.list[i]==null) {
        break;
      }
      result+=this.list[i] + ", ";
    }
    return result;
  }

  public void shuffle() {
    Random gen=new Random();

    // fisher-yates shuffle
    for(int i=this.index-1; i>-1; i--) {
      int randIndex=gen.nextInt(i+1);
      T item=this.get(i);
      this.set(i, this.get(randIndex));
      this.set(randIndex, item);
    }
  }

  // reset array and index
  public void clear() {
    this.list=new Object[this.list.length];
    this.index=0;
  }

  private class ALIterator implements Iterator<T> {
    private int currentIndex;

    public ALIterator() {
      this.currentIndex=0;
    }

    public boolean hasNext() {
      return this.currentIndex<index;
    }

    public T next() {
      return (T) list[currentIndex++];
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new ALIterator();
  }

  public static void main(String[] args) {
    ArrayList<Integer> list=new ArrayList<>();
    for(int i=0; i<15; i++) {
      list.add(i);
    }
    System.out.println(list);
    System.out.println("Removing at index 3: " + list.remove(3));;
    System.out.println(list);
    list.clear();
    for(int i=0; i<15; i++) {
      list.add(i);
    }
    System.out.println(list);
    list.shuffle();
    System.out.println(list);


    list.clear();
    for(int i=0; i<15; i++) {
      list.add(i);
    }

    for(Integer e: list) {
      System.out.println(e);
    }

    System.out.println(list);
    System.out.println("Adding 100 at index 2");
    list.add(2, 100);
    System.out.println(list);
    System.out.println("Adding 500 at index 18");
    list.add(18, 100);
    System.out.println(list);
    System.out.println("Adding 9 more 100s");
    list.add(2, 100);
    list.add(2, 100);
    list.add(2, 100);
    list.add(2, 100);
    list.add(2, 100);
    list.add(2, 100);
    list.add(2, 100);
    list.add(2, 100);
    list.add(2, 100);
    System.out.println(list);
  }
}