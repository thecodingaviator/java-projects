/*
Name: Parth Parth
Date: 09/27/2021
File: LifeSimulation.java
Section: A
*/

import java.util.Random;
import java.util.Scanner;

public class LifeSimulation {

  static void screenshot(LandscapeDisplay display) {
    display.saveImage("gameOfLife_" + display.getScreenshotIndex() +".png");
    System.out.println("Screenshot saved as: " + "gameOfLife_" + display.getScreenshotIndex() +".png");
    display.setScreenshotIndex(display.getScreenshotIndex()+1);
  }
  
  public static void main(String[] args) throws InterruptedException {
    // default value for rows and columns
    int rows=100, cols=100;

    // if user has given >=2 command line arguments
    if(args.length>=2) {
      rows=Integer.parseInt(args[0]);
      cols=Integer.parseInt(args[1]);
    }

    Landscape scape = new Landscape(rows, cols);
    Random gen = new Random();
    double density = 0.5;

    // initialize the grid to be 50% full
    for (int i = 0; i < scape.getRows(); i++) {
        for (int j = 0; j < scape.getCols(); j++ ) { 
            scape.getCell( i, j ).setAlive( gen.nextDouble() <= density );
        }
    }

    LandscapeDisplay display = new LandscapeDisplay(scape, 8);

    Scanner input=new Scanner(System.in);

    while(true) {
      System.out.print("Do you want to take a screenshot? Enter true or false: ");
      if(input.nextBoolean()) {
        screenshot(display);
      }

      System.out.print("Do you want to continue? Enter true to continue or false to stop: ");
      boolean toContinue=input.nextBoolean();

      // if user does not want to continue, stop execution
      if(!toContinue) {
        break;
      }

      // Advance
      scape.advance();
      display.repaint();
    }

    input.close();

    // force an exit as the program wont close otherwise
    System.exit(0);
  }
}
