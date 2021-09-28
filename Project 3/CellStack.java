/*
Name: Parth Parth
Date: 09/27/2021
File: CellStack.java
Section: A
*/

public class CellStack {
  Cell[] stack;
  int pos;

  CellStack() {
    this.stack=new Cell[10];
    this.pos=-1;
  }

  CellStack(int max) {
    this.stack=new Cell[max];
    this.pos=-1;
  }

  public void push(Cell c) {
    // if stack is full
    if(this.pos==this.stack.length-1) {
      // create a temp stack of double size
      Cell[] temp=new Cell[this.stack.length*2];
      // copy everything
      for(int i=0; i<this.stack.length; i++) {
        temp[i]=this.stack[i];
      }
      // make temp stack original stack
      this.stack=temp;
    }

    this.stack[++this.pos]=c;
  }

  public Cell pop() {
    if(this.pos>-1) {
      return this.stack[this.pos--];
    }
    // if stack is empty return invalid cell
    return new Cell(-1, -1, -1, false);
  }

  public int size() {
    return this.pos+1;
  }

  public boolean empty() {
    return this.pos==-1;
  }
}
