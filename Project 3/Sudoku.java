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
