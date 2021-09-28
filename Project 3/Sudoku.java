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
    CellStack stack=new CellStack(this.game.numOfZeroes());

    int numOfZeroes=this.game.numOfZeroes();
    while(stack.size()<numOfZeroes) {
      int i=0, j=0, iReal=0;

      // find the first empty cell
      for(; i<Board.SIZE; i++) {
        for(j=0; j<Board.SIZE; j++) {
          if(this.game.get(i, j).getValue()==0) {
            iReal=i;
            i=Board.SIZE+1;
            break;
          }
        }
      }

      // keep adding values until a valid value is found
      // if no valid value is found, pop off the stack

      // start to find a valid value
      int value=1;
      while(!this.game.validValue(iReal, j, value)) {
        value++;
      }

      // if value is valid
      if(value<10) {
        // set value on board
        this.game.set(iReal, j, value);
        // push to stack
        stack.push(this.game.get(iReal, j));
      }
      // if value iis invalid
      else {
        // pop cell
        Cell popped=stack.pop();
        // if stack is empty, game impossible
        if(popped.getValue()==-1) {
          return false;
        }
        // if stack isnt empty
        else {
          // 
          popped.setValue(popped.getValue()+1);
          if(popped.getValue()>9) {
            return false;
          }
        }
      }
    }

    
    return true;
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
