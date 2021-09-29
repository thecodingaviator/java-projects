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
        this.game.set(randomRow, randomCol, randomValue, true);
        counter++;
      }
    }
  }

  public boolean solve() {
    int numFree=81-this.game.numLocked();
    CellStack stack=new CellStack(numFree);

    // while stack is smaller than number of free cells
    while(stack.size()<numFree) {
      // get next best cell
      Cell next=this.nextBestCell();

      // if next Cell exists and its value is valid
      if(next!=null && this.nextValidValue(next)<10) {
        // push next on stack and update it on the board
        stack.push(next);
        this.updateBoard(next);
      }
      // else
      else {
        // while the game is NOT solved and stack is NOT empty
        while(!this.game.validSolution() && !stack.empty()) {
          // pop a cell and get next possible value for cell
          Cell popped=stack.pop();
          int value=this.nextValidValue(popped);

          // if value is valid i.e. <10 in this case
          if(value<10) {
            // update the value of popped and push it on to stack
            popped.setValue(value);
            stack.push(popped);
            // pseudocode also said to update cell on board but
            // the following line is unnecessary since the board
            // will automatically update when popped.setValue() is called
            //this.game.set(popped.getRow(), popped.getCol(), value);
            // break
            break;
          }
          // if value is invalid
          else {
            // set cell value to 0
            popped.setValue(0);
          }
        }
        // if stack is empty, return that puzzle cannot be solved
        if(stack.empty()) {
          return false;
        }
      }
    }

    // if we have reached here, the puzzle is solved
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
    int startNumber=20;
    // if a command line argument is passed, use it as no of locked Cells
    if(args.length>0) {
      startNumber=Integer.parseInt(args[0]);
    }

    Sudoku s=new Sudoku(startNumber);
    System.out.println(s.game);
    // s.game.read("test.txt");
    // System.out.println(s.game);
    System.out.println(s.game.validSolution());
    s.solve();
    System.out.println(s.game);
    System.out.println(s.game.validSolution());
  }
}
