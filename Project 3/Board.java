/*
Name: Parth Parth
Date: 09/27/2021
File: Board.java
Section: A
*/

import java.io.*;

public class Board {
  private Cell[][] grid;
  public static final int SIZE=9;

  Board() {
    // initialise 2D array
    this.grid=new Cell[Board.SIZE][Board.SIZE];

    // loop over grid
    for(int i=0; i<Board.SIZE; i++) {
      for(int j=0; j<Board.SIZE; j++) {
        // create new Cell
        this.grid[i][j]=new Cell(i, j, 0);
      }
    }
  }

  public boolean read(String filename) {
    try {
      // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
      // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
      FileReader fr=new FileReader(filename);
      BufferedReader br=new BufferedReader(fr);

      // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
      String line=br.readLine();

      int row=0;

      // start a while loop that loops while line isn't null
      while(line!=null) {
        // assign to an array of type String the result of calling split on the line with the argument "[ ]+"
        String[] split=line.split("[ ]+");

        // create cells in row row
        for(int j=0; j<this.getCols(); j++) {
          this.grid[row][j]=new Cell(row, j, Integer.parseInt(split[j]));
        }
        
        row++;
        line=br.readLine();
      }

      // call the close method of the BufferedReader
      // return true
      br.close();
      return true;
    }
    catch(FileNotFoundException ex) {
      System.out.println("Board.read():: unable to open file " + filename );
    }
    catch(IOException ex) {
      System.out.println("Board.read():: error reading file " + filename);
    }

    return false;
  }

  public int getCols() {
    return this.grid[0].length;
  }

  public int getRows() {
    return this.grid.length;
  }

  public Cell get(int r, int c) {
    return grid[r][c];
  }

  public boolean isLocked(int r, int c) {
    return this.grid[r][c].isLocked();
  }
  
  public int numLocked() {
    int countLocked=0;
    
    // loop over the grid
    for(int i=0; i<this.getRows(); i++) {
      for(int j=0; j<this.getCols(); j++) {
        // count
        if(this.grid[i][j].isLocked()) {
          countLocked++;
        }
      }
    }

    return countLocked;
  }

  public int value(int r, int c) {
    return this.grid[r][c].getValue();
  }

  public void set(int r, int c, int value) {
    this.grid[r][c].setValue(value);
  }

  public void set(int r, int c, int value, boolean locked) {
    this.grid[r][c].setValue(value);
    this.grid[r][c].setLocked(locked);
  }

  public String toString() {
    String result="";
    
    // loop over grid
    for(int i=0; i<Board.SIZE; i++) {
      for(int j=0; j<Board.SIZE; j++) {
        // add to result
        result+=this.grid[i][j]+" ";
        // if either item 3 or 6, add seprarator
        result+=(j==2 || j==5)?"| ":"";
      }
      // if either row 3 or 6 , add separator
      result+=(i==2 || i==5)?"\n------|-------|------":"";
      result+='\n';
    }

    return result;
  }

  public static void main(String[] args) {
    Board test=new Board();
    test.read("test.txt");
    System.out.println(test);
  }
}
