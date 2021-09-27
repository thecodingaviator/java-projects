/*
Name: Parth Parth
Date: 09/27/2021
File: LifeSimulation.java
Section: A
*/

import java.util.Random;

public class LifeSimulation {
  public static void main(String[] args) throws InterruptedException {
    Landscape scape = new Landscape(100, 100);
    Random gen = new Random();
    double density = 0.5;

    // initialize the grid to be 50% full
    for (int i = 0; i < scape.getRows(); i++) {
        for (int j = 0; j < scape.getCols(); j++ ) { 
            scape.getCell( i, j ).setAlive( gen.nextDouble() <= density );
        }
    }

    LandscapeDisplay display = new LandscapeDisplay(scape, 8);

    for(int i=0; i<1000; i++) {
      Thread.sleep(250);
      scape.advance();
      display.repaint();

      // the following line can be uncommented if it is needed to take snapshot of each frame of the game
      // display.saveImage( "data/life_frame_" + String.format( "%03d", i ) + ".png" );
    }
  }
}
