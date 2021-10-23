/*
Name: Parth Parth
Date: 10/12/2021
File: SocialAgentSimulation.java
Section: A
*/

import java.util.Random;

public class SocialAgentSimulation {
  public static void main(String[] args) throws InterruptedException {
    // set width and height
    int w=500;
    int h=500;
    // if command line arguments were used, use those
    if(args.length>1) {
      w=Integer.parseInt(args[0]);
      h=Integer.parseInt(args[1]);
    }

    // create a landscape
    Landscape scape=new Landscape(w, h);

    // number of agents
    final int N=200;
    
    Random gen = new Random();
    int radiusOfSensitivity=15;

    // create N agents on landscape scape
    for(int i=0;i<N;i++) {
      scape.addAgent(new SocialAgent(gen.nextDouble()*(scape.getWidth() - 20),
			gen.nextDouble() * (scape.getHeight() - 20), radiusOfSensitivity));
    }

    // create and paint a GUI
    LandscapeDisplay display=new LandscapeDisplay(scape);
    display.repaint();

    // update and repaint 20000 times
    for(int i=0; i<500; i++) {
      scape.updateAgents();
      display.repaint();
      Thread.sleep(10);
    }
  }
}
