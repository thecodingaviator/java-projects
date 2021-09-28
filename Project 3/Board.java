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
          // create new cell at row,j with the value read from the board and set locked
          this.grid[row][j]=new Cell(row, j, Integer.parseInt(split[j]), Integer.parseInt(split[j])!=0);
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

  public boolean validValue(int row, int col, int value) {

    // sanity check for bounds of grid
    if(row<0 || col<0 || row>8 || col>8) {
      return false;
    }

    // check if value exists in row
    for(int i=0; i<this.getCols(); i++) {
      if(i==col) {
        continue;
      }
      if(this.grid[row][i].getValue()==value) {
        return false;
      }
    }

    // check if value exists in column
    for(int i=0; i<this.getRows(); i++) {
      if(i==row) {
        continue;
      }
      if(this.grid[i][col].getValue()==value) {
        return false;
      }
    }

    // check if value exists in 3*3
    // calculate bounds
    int topBound=row-(row%3);
    int leftBound=col-(col%3);
    // loop through 3*3
    for(int i=topBound; i<topBound+3; i++) {
      for(int j=leftBound; j<leftBound+3; j++) {
        if(i==row && j==col) {
          continue;
        }
        if(this.grid[i][j].getValue()==value) {
          return false;
        }
      }
    }

    return true;
  }

  public boolean validSolution() {
    for(int i=0; i<this.getRows(); i++) {
      for(int j=0; j<this.getCols(); j++) {
        // if value 0, invalid. return false
        if(this.grid[i][j].getValue()==0)
          return false;
        // if value invalid, return false
        if(!this.validValue(i, j, this.grid[i][j].getValue())) {
          return false;
        }
      }
    }
    return true;
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
    Board tester=new Board();
    String file="test.txt";
    if(args.length>0)
      file=args[0];
    
    tester.read(file);

    System.out.println(tester.validSolution());
  }
}
