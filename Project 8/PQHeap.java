/*
Name: Parth Parth
Date: 11/22/2021
File: PQHeap.java
Section: A
*/

import java.util.Comparator;

public class PQHeap<T> {
  // The heap
  private T[] heap;
  // The size of the heap
  private int size;
  // The comparator
  private Comparator<T> comparator;

  public PQHeap(Comparator<T> comparator) {
    this.comparator = comparator;
    this.heap = (T[]) new Object[10];
    this.size = 0;
  }

  public int size() {
    return this.size;
  }

  public void add(T obj) {
    // if the heap full, double size
    if(this.size == this.heap.length) {
      this.resize();
    }
    // add the element to heap
    this.heap[this.size] = obj;
    this.size++;
    // heapify heap
    this.heapifyUp(this.size - 1);
  }

  private void resize() {
    T[] newHeap = (T[]) new Object[this.heap.length * 2];
    for(int i = 0; i < this.heap.length; i++) {
      newHeap[i] = this.heap[i];
    }
    this.heap = newHeap;
  }

  private void heapifyUp(int index) {
    // if the index is 0, there is nothing to heapify
    if(index == 0) {
      return;
    }
    // get the parent index
    int parentIndex = (index - 1) / 2;
    // if the parent is greater than the child, swap and heapify that position
    if(this.comparator.compare(this.heap[index], this.heap[parentIndex]) > 0) {
      T temp = this.heap[index];
      this.heap[index] = this.heap[parentIndex];
      this.heap[parentIndex] = temp;
      // heapify up the parent
      this.heapifyUp(parentIndex);
    }
  }

  public T remove() {
    // if the heap is empty, return null
    if(this.size == 0) {
      return null;
    }
    // get the root
    T root = this.heap[0];
    // set the root to last element
    this.heap[0] = this.heap[this.size - 1];
    // remove the last element
    this.heap[this.size - 1] = null;
    this.size--;
    // heapify down the root
    this.heapifyDown(0);
    return root;
  }

  private void heapifyDown(int index) {
    // if the index is greater than the size, there is nothing to heapify down
    if(index >= this.size) {
      return;
    }
    // get the left and right child index
    int leftChildIndex = index * 2 + 1;
    int rightChildIndex = index * 2 + 2;
    int largestChildIndex = index;
    // if the left child is greater than the parent, set the largest child index to left child
    if(leftChildIndex < this.size && this.comparator.compare(this.heap[leftChildIndex], this.heap[largestChildIndex]) > 0) {
      largestChildIndex = leftChildIndex;
    }
    // if the right child is greater than the largest child, set the largest child index to right child
    if(rightChildIndex < this.size && this.comparator.compare(this.heap[rightChildIndex], this.heap[largestChildIndex]) > 0) {
      largestChildIndex = rightChildIndex;
    }
    // if the largest child is not the parent, swap and heapify down the largest child
    if(largestChildIndex != index) {
      T temp = this.heap[index];
      this.heap[index] = this.heap[largestChildIndex];
      this.heap[largestChildIndex] = temp;
      // heapify down the largest child
      this.heapifyDown(largestChildIndex);
    }
  }
}