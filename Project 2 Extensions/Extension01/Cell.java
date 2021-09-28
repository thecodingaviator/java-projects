/*
Name: Parth Parth
Date: 09/27/2021
File: Cell.java
Section: A
*/

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Cell {
  private boolean isAlive;

  public Cell() {
    this.isAlive=false;
  }

  public Cell(boolean alive) {
    this.isAlive=alive;
  }

  public boolean getAlive() {
    return this.isAlive;
  }

  public void reset() {
    this.isAlive=false;
  }

  public void setAlive(boolean alive)
  {
    this.isAlive=alive;
  }

  public String toString() {
    if(this.isAlive) {
      return "⬜";
    }
    else {
      return " ";
    }
  }

  public void draw(Graphics g, int x, int y, int scale) {
    // is cell is alive
    if(this.isAlive) {
      // set cell color to grey
      g.setColor(Color.GRAY);
      // fill the cell with a rectangle
      g.fillRect(x, y, scale, scale);
    }
  }

  public void updateState(ArrayList<Cell> neighbours) {
    int numberOfNeighboursAlive=0;

    // loop through cell's neighbours
    for(Cell i: neighbours) {
      // if cell i is alive
      if(i.getAlive()) {
        numberOfNeighboursAlive++;
      }
    }

    // if alive and have 2 or 3 live neighbours, then cell will remain alive
    if(this.getAlive() && (numberOfNeighboursAlive==2 || numberOfNeighboursAlive==3)) {
      this.setAlive(true);
    }
    // if cell is dead and has 3 live neighbours, it will become live
    else if(!this.getAlive() && numberOfNeighboursAlive==3) {
      this.setAlive(true);
    }
    // all other cells will die/remain dead
    else {
      this.setAlive(false);
    }
  }

  public static void main(String[] args) {
    Cell cell1=new Cell(); // ends when main method ends
    System.out.println(cell1.getAlive());
    System.out.println(cell1);
    cell1.setAlive(true);
    System.out.println(cell1.getAlive());
    System.out.println(cell1);
    cell1=new Cell(true);
    System.out.println(cell1.getAlive());
    System.out.println(cell1);  
  }
}
