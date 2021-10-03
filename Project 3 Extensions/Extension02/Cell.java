/*
Name: Parth Parth
Date: 09/27/2021
File: Cell.java
Section: A
*/

import java.awt.*;

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
    // do nothing if value is locked
    if(!this.isLocked)
      this.value=newval;
  }

  public boolean isLocked() {
    return this.isLocked;
  }

  public void setLocked(boolean lock) {
    this.isLocked=lock;
  }

  public Cell clone() {
    return new Cell(this.row, this.col, this.value, this.isLocked);
  }

  public void draw(Graphics g, int x0, int y0, int scale) {
    char[] draw={(char)('0' + this.value), 0};
    g.drawChars(draw, 0, 1, x0*scale, y0*scale);
  }

  public String toString() {
    return String.valueOf(this.value);
  }

  public static void main(String[] args) {
    Cell a=new Cell(0, 7, 5, true);
    System.out.println("Row: " + a.getRow());
    System.out.println("Col: " + a.getCol());
    System.out.println("Value: " + a.getValue());
    System.out.println("Locked: " + a.isLocked());

    a.setLocked(true);
    a.setValue(5);

    System.out.println("Value: " + a.getValue());
    System.out.println("Locked: " + a.isLocked());
  }
}
