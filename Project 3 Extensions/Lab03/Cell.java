/*
Name: Parth Parth
Date: 09/27/2021
File: Cell.java
Section: A
*/

public class Cell {
  private int row, col, value;
  private boolean isLocked;

  public Cell() {
    this.row=this.col=this.value=0;
    this.isLocked=false;
  }

  public Cell(int row, int col, int value) {
    this.row=row;
    this.col=col;
    this.value=value;
    this.isLocked=false;
  }

  public Cell(int row, int col, int value, boolean locked) {
    this.row=row;
    this.col=col;
    this.value=value;
    this.isLocked=locked;
  }

  public int getRow() {
    return this.row;
  }

  public int getCol() {
    return this.col;
  }

  public int getValue() {
    return this.value;
  }

  public void setValue(int newval) {
    this.value=newval;
  }

  public boolean isLocked() {
    return this.isLocked;
  }

  public void setLocked(boolean lock)
  {
    this.isLocked=lock;
  }

  public Cell clone() {
    return new Cell(this.row, this.col, this.value, this.isLocked);
  }

  public String toString() {
    return String.valueOf(this.value);
  }
}
