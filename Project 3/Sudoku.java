/*
Name: Parth Parth
Date: 09/28/2021
File: Sudoku.java
Section: A
*/

import java.util.Random;

public class Sudoku {
  private Board game;

  Sudoku() {
    game=new Board();
  }

  Sudoku(final int N) {
    game=new Board();

    Random generator=new Random();

    int counter=0;

    // while less than N values
    while(counter<N) {
      // generate random values for row col and value
      int randomRow=generator.nextInt(this.game.getRows());
      int randomCol=generator.nextInt(this.game.getCols());
      int randomValue=generator.nextInt(9)+1;

      // if cell is empty and value is valid, assign and increment counter
      if(this.game.get(randomRow, randomCol).getValue()==0 
      && this.game.validValue(randomRow, randomCol, randomValue)) {
        this.game.set(randomRow, randomCol, randomValue);
        counter++;
      }
    }
  }

  public boolean solve() {
    int numFree=81-this.game.numLocked();
    CellStack stack=new CellStack(numFree);

//  while the stack size is less than the number of unspecified cells
    while(stack.size()<numFree) {

//    select the next cell to check (this is where you add smarts)
      Cell next=this.nextBestCell();

//    if there is a valid next cell to try
      if(next!=null && this.nextValidValue(next)<10) {
//      push the cell onto the stack
        stack.push(next);
//      update the board
        this.updateBoard(next);
      }
//    else
      else {
//      while it is necessary and possible to backtrack
        while(!this.game.validSolution() && !stack.empty()) {
//        pop a cell off the stack
          Cell popped=stack.pop();
//        check if there are other untested values this cell could try
          int value=this.nextValidValue(popped);

//        if there is another valid (<10) untested value for this cell
          if(value<10) {
//          push the cell with its new value onto the stack
            popped.setValue(value);
            stack.push(popped);
//          update the board (the following line is unnecessary since the board
//          will automatically update when popped.setValue() is called)
            //this.game.set(popped.getRow(), popped.getCol(), value);
//          break
            break;
          }
//        else
          else {
//          set this cell's value to 0 on the board
            popped.setValue(0);
          }
        }
//      if the stack size is 0 (no more backtracking possible)
//        return false: there is no solution
        if(stack.empty()) {
          return false;
        }
      }
    }

//  return true: the board contains the solution
    return true;
  }

  private Cell nextBestCell() {
    Cell best=null;
    int numOfSolutions=9;

    for(int row=0; row<this.game.getRows(); row++) {
      for(int col=0; col<this.game.getCols(); col++) {
        // retrieve Cell at row,col
        Cell temp=this.game.get(row, col);

        // if Cell is locked or if it is already filled, skip Cell
        if(temp.isLocked() || temp.getValue()!=0) {
          continue;
        }

        // count number of solutions
        int tempNumOfSolutions=0;
        for(int value=1; value<10; value++) {
          if(this.game.validValue(row, col, value)) {
            tempNumOfSolutions++;
          }
        }

        // if Cell at row,col has less solutions than previous Cell
        // make it new best Cell
        if(tempNumOfSolutions<numOfSolutions) {
          best=temp;
          numOfSolutions=tempNumOfSolutions;
        }
      }
    }

    // by this point either best has a best cell in it
    // or it is still null which would happen only when
    // no unlocked empty cells or ones valid values could be found
    return best;
  }

  private boolean updateBoard(Cell temp) {
    int value=nextValidValue(temp);

    // if value is valid, return true and set value
    if(value<10) {
      temp.setValue(value);
      return true;
    }
    // else return false
    else {
      return false;
    }
  }

  private int nextValidValue(Cell temp) {
    int row=temp.getRow();
    int col=temp.getCol();

    int value=0;

    // check value start from present value+1 till 9
    for(value=temp.getValue()+1; value<10; value++) {
      // if value is valid, break
      if(this.game.validValue(row, col, value)) {
        break;
      }
    }

    // will return value if a value in Cell temp is possible else 10
    return value;
  }

  public static void main(String[] args) {
    int startNumber=10;
    // if a command line argument is passed, use it as no of locked Cells
    if(args.length>0) {
      startNumber=Integer.parseInt(args[0]);
    }

    Sudoku s=new Sudoku(startNumber);
    s.game.read("test.txt");
    System.out.println(s.game);
    s.solve();
    System.out.println(s.game);
    System.out.println(s.game.validSolution());
  }
}
