/*
Name: Parth Parth
Date: 09/27/2021
File: Landscape.java
Section: A
*/

import java.util.ArrayList;
import java.awt.Graphics;

public class Landscape {

  private Cell [][]grid;
  int rows, cols;

  public Landscape(int rows, int cols) {
    this.grid=new Cell[rows][cols]; // this object end when the method in which the Landscape class has been instantiated ends
    this.rows=rows;
    this.cols=cols;

    // fill all array positions with dead cells
    for(int i=0; i<this.rows; i++) {
      for(int j=0; j<this.cols; j++) {
        this.grid[i][j]=new Cell(); // this object end when the method in which the Landscape class has been instantiated ends
      }
    }
  }

  public void reset() {
    // fill all array positions with dead cells
    for(int i=0; i<this.rows; i++) {
      for(int j=0; j<this.cols; j++) {
       this.grid[i][j].reset();
      }
    }
  }

  public int getRows() {
    return this.rows;
  }

  public int getCols() {
    return this.cols;
  }

  public Cell getCell(int row, int col) {
    return grid[row][col];
  }

  public String toString() {
    String result=""; // last reference to this object at line 50

    // loop through grid
    for(int i=0; i<this.rows; i++)
    {
      for(int j=0; j<this.cols; j++) {
        result+=this.grid[i][j];
      }
      // if row isnt the last one, add a line break
      result+=(i==this.rows-1?"":"\n");
    }
    return result;
  }

  public ArrayList<Cell> getNeighbors(int row, int col) {
    ArrayList<Cell> neighbours=new ArrayList<Cell>(); // last reference to this object at line 65

    for(int i=row-1; i<row+2; i++) {
      for(int j=col-1; j<col+2; j++) {
        // if a cell at i, j exists
        if(exists(i, j)) {
          // if it is not the cell at row, col itself
          if(!(i==row && j==col)) {
            // add it to neighbours grid
            neighbours.add(this.grid[i][j]);
          }
        }
      }
    }

    return neighbours;
  }

  // to check if an element at row, col exists
  private boolean exists(int row, int col) {
    return row>=0 && col>=0 && row<this.rows && col<this.cols;
  }

  public void draw(Graphics g, int gridScale) {
    for(int i=0; i<this.rows; i++) {
      for(int j=0; j<this.cols; j++) {
        grid[i][j].draw(g, i*gridScale, j*gridScale, gridScale);
      }
    }   
  }

  public void advance() {
    int r=this.rows;
    int c=this.cols;
    Cell[][] temp=new Cell[r][c]; // last reference to this object at line 94

    // create a copy of grid in temp
    for(int i=0; i<r; i++) {
      for(int j=0; j<c; j++) {
        temp[i][j]=new Cell(this.grid[i][j].getAlive());  // even though these objects are not directly referenced anywhere, they eventually are remove from memory when the advance method ends
      }
    }

    // advance each cell in temp to next position using results from grid
    for(int i=0; i<r; i++) {
      for(int j=0; j<c; j++) {
        temp[i][j].updateState(this.getNeighbors(i, j),i,j);
      }
    }
    
    this.grid=temp;
  }

  public static void main(String[] args) {
    Landscape landscape1=new Landscape(5, 5); // last reference to this object at line 116
    System.out.println(landscape1);
    System.out.println(landscape1.getCell(1,4));
    System.out.println(landscape1.getRows());
    System.out.println(landscape1.getCols());
    
    for(int i=0; i<landscape1.grid.length; i++) {
      for(int j=0; j<landscape1.grid[i].length; j++) {
        landscape1.grid[i][j]=new Cell(true);  // even though these objects are not directly referenced anywhere, they eventually are remove from memory when the main method ends
      }
    }

    System.out.println(landscape1);
    System.out.println(landscape1.getCell(1,4));
    System.out.println(landscape1.getRows());
    System.out.println(landscape1.getCols());
    
    ArrayList<Cell> test=new ArrayList<Cell>(); // last reference to this object at line 117
    test=landscape1.getNeighbors(0, 0);
    System.out.println(test);
    test=landscape1.getNeighbors(3, 3);
    System.out.println(test);
  }
}
