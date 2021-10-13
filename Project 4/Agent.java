/*
Name: Parth Parth
Date: 10/08/2021
File: Agent.java
Section: A
*/

import java.awt.Graphics;

public class Agent {
  protected double x;
  protected double y;

  public Agent(double x0, double y0) {
    this.x=x0;
    this.y=y0;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public void setX(double newX) {
    this.x=newX;
  }

  public void setY(double newY) {
    this.y=newY;
  }

  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }

  public void updateState(Landscape scape) {
  }

  public void draw(Graphics g) {
  }

  public static void main(String[] args) {
    Agent a=new Agent(1.7, 2.555);
    System.out.println("X: " + a.getX());
    System.out.println("Y: " + a.getY());
    System.out.println("Coordinates: " + a);

    System.out.println("changing values now");

    a.setX(7.66);
    a.setY(9.677);
    System.out.println("X: " + a.getX());
    System.out.println("Y: " + a.getY());
    System.out.println("Coordinates: " + a);
  }
}
