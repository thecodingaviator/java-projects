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
  LinkedList<Agent> list;

  public Landscape(int w, int h) {
    this.w=0;
    this.h=0;
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
    ArrayList<Agent> members=new ArrayList<Agent>();
    for(Agent e: this.list) {
      double distance=this.distance(x0, y0, e.getX(), e.getY());
      if(distance<radius) {
        members.add(e);
      }
    }
    return members;
  }

  private double distance(double x0, double y0, double x1, double y1) {
    double x=x1-x0;
    double y=y1-y0;
    x=Math.pow(x, 2);
    y=Math.pow(y, 2);

    return Math.sqrt(x+y);
  }

  public void draw(Graphics g) {
    for(Agent e: list) {
      e.draw(g);
    }
  }
}
