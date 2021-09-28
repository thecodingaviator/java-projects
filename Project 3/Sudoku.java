/*
Name: Parth Parth
Date: 09/27/2021
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

    while(stack.size()<numFree) {
      Cell next=this.nextBestCell();
      // System.out.println(next.getRow()+" "+next.getCol());

      if(next!=null && nextValidValue(next)!=10) {
        stack.push(next);
        updateBoard(next);
      }
      else {
        while(!this.game.validSolution() && stack.size()>-1) {
          Cell popped=stack.pop();
          int value=0;

          
          for(value=popped.getValue()+1; value<10; value++) {
            if(this.game.validValue(popped.getRow(), popped.getCol(), value)) {
              break;
            }
          }


          if(popped.getValue()!=value) {
            popped.setValue(value);
            updateBoard(popped);
          }
            else {
              popped.setValue(0);
          }
        }
        if(stack.empty()) {
          return false;
        }
      }
    }

    return true;
  }

  private Cell nextBestCell() {
    Cell best=null;
    int numOfSolutions=9;

    // for(int row=0; row<this.game.getRows(); row++) {
    //   for(int col=0; col<this.game.getCols(); col++) {
    //     // retrieve Cell at row,col
    //     Cell temp=this.game.get(row, col);

    //     // if Cell is locked or if it is already filled, skip Cell
    //     if(temp.isLocked() || temp.getValue()!=0) {
    //       continue;
    //     }

    //     // count number of solutions
    //     int tempNumOfSolutions=0;
    //     for(int value=1; value<10; value++) {
    //       if(this.game.validValue(row, col, value)) {
    //         tempNumOfSolutions++;
    //       }
    //     }

    //     // if Cell at row,col has less solutions than previous Cell
    //     // make it new best Cell
    //     if(tempNumOfSolutions<numOfSolutions) {
    //       best=temp;
    //       numOfSolutions=tempNumOfSolutions;
    //     }
    //   }
    // }

    for(int i=0; i<9; i++)
      for(int j=0; j<9; j++)
        if(this.game.get(i, j).getValue()==0)
          return this.game.get(i,j);

    // by this point either best has a best cell in it
    // or it is still null which would happen only when
    // no unlocked empty cells or ones valid values could be found
    return null;
  }

  private void updateBoard(Cell temp) {
    temp.setValue(nextValidValue(temp));
  }

  private int nextValidValue(Cell temp) {
    int row=temp.getRow();
    int col=temp.getCol();

    int value=0;

    for(value=temp.getValue()+1; value<10; value++) {
      if(this.game.validValue(row, col, value)) {
        break;
      }
    }

    return value;
  }

  public static void main(String[] args) {
    int startNumber=80;
    if(args.length>0) {
      startNumber=Integer.parseInt(args[0]);
    }
    Sudoku s=new Sudoku();
    s.game.read("test.txt");
    System.out.println(s.game);
    s.solve();
    System.out.println(s.game);
  }
}
