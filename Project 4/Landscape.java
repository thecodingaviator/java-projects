/*
Name: Parth Parth
Date: 10/11/2021
File: Landscape.java
Section: A
*/

import java.util.ArrayList;
import java.awt.Graphics;

public class Landscape {
  private int w;
  private int h;
  private LinkedList<Agent> list;

  public Landscape(int w, int h) {
    this.w=w;
    this.h=h;
    this.list=new LinkedList<Agent>();
  }

  public int getHeight() {
    return this.h;
  }

  public int getWidth() {
    return this.w;
  }

  public void addAgent(Agent a) {
    this.list.addFirst(a);
  }

  public String toString() {
    return ""+this.list.size();
  }

  public ArrayList<Agent> getNeighbors(double x0, double y0, double radius) {
    // to store all neighbours in radius
    ArrayList<Agent> members=new ArrayList<Agent>();

    // loop over the list
    for(Agent e: this.list) {
      // get distance between e and (x0, y0)
      double distance=this.distance(x0, y0, e.getX(), e.getY());
      // if distance is less than radius
      if(distance<radius) {
        // add it to members
        members.add(e);
      }
    }
    return members;
  }

  private double distance(double x0, double y0, double x1, double y1) {
    // use the distance formula
    double x=x1-x0;
    double y=y1-y0;
    x=Math.pow(x, 2);
    y=Math.pow(y, 2);

    return Math.sqrt(x+y);
  }

  public void draw(Graphics g) {
    for(Agent e: this.list) {
      e.draw(g);
    }
  }

  public void updateAgents() {
    // get shuffled list from the current list
    ArrayList<Agent> shuffled=this.list.toShuffledList();

    // create new landscape
    Landscape scape=new Landscape(500, 500);

    // add agents to landscape
    for(Agent e: shuffled) {
      scape.addAgent(e);
    }
    // update all agents
    for(Agent e: shuffled) {
      e.updateState(scape);
    }
  }

  // helper method for SocialAgentSimulation
  LinkedList<Agent> getList() {
    return this.list;
  }

  public static void main(String[] args) {
    Landscape scape=new Landscape(500, 500);
    SocialAgent a=new SocialAgent(5, 5, 5),
    b=new SocialAgent(10, 10, 5),
    c=new SocialAgent(15, 15, 5),
    d=new SocialAgent(20, 20, 5);
    scape.addAgent(a);
    scape.addAgent(b);
    scape.addAgent(c);
    scape.addAgent(d);

    a.updateState(scape);
    System.out.println("Neighbours in 10: " + (scape.getNeighbors(5, 5, 10).size()));

    System.out.println("Resetting");
    a=new SocialAgent(5, 5, 5);
    b=new SocialAgent(10, 10, 5);
    c=new SocialAgent(15, 15, 5);
    d=new SocialAgent(20, 20, 5);
    a.updateState(scape);
    System.out.println("Neighbours in 15: " + (scape.getNeighbors(5, 5, 15).size()));

    System.out.println("Resetting");
    a=new SocialAgent(5, 5, 5);
    b=new SocialAgent(10, 10, 5);
    c=new SocialAgent(15, 15, 5);
    d=new SocialAgent(20, 20, 5);
    a.updateState(scape);
    System.out.println("Neighbours in 25: " + (scape.getNeighbors(5, 5, 25).size()));
  }
}
