/*
Name: Parth Parth
Date: 10/08/2021
File: SocialAgent.java
Section: A
*/

import java.awt.*;
import java.awt.geom.Ellipse2D;

import java.util.Random;

public class SocialAgent extends Agent {
  protected boolean moved;
  private int radius;

  public SocialAgent(double x0, double y0, int r0) {
    super(x0, y0);
    this.radius=r0;
  }

  public void setRadius(int radius) {
    this.radius=radius;
  }

  public int getRadius() {
    return this.radius;
  }

  public void draw(Graphics g) {
    Graphics2D gr=(Graphics2D) g;
    gr.setColor(Color.CYAN);
    gr.setPaint(Color.CYAN);
    if(this.moved) {
      gr.setColor(Color.BLUE);
      gr.setPaint(Color.BLUE);
    }

    Ellipse2D.Double circle=new Ellipse2D.Double(this.getX(), this.getY(), 10, 10);
    gr.fill(circle);
    gr.draw(circle);
  }

  public void updateState(Landscape scape) {
    Random gen=new Random();
    this.moved=false;
    if(scape.getNeighbors(this.getX(), this.getY(), this.getRadius()).size()>3) {
      if(gen.nextInt(100) == 0) {
        this.moved=true;
        this.setX(this.getX()+gen.nextDouble(-10, 10));
        this.setY(this.getY()+gen.nextDouble(-10, 10));
      }
    }
    else {
      this.moved=true;
      this.setX(this.getX()+gen.nextDouble(-10, 10));
      this.setY(this.getY()+gen.nextDouble(-10, 10));
    }
  }

  public static void main(String[] args) {
    SocialAgent a=new SocialAgent(1.7, 2.555, 7);
    System.out.println("X: " + a.getX());
    System.out.println("Y: " + a.getY());
    System.out.println("Coordinates: " + a);
    System.out.println("Radius: " + a.getRadius());

    System.out.println("changing values now");

    a.setX(7.66);
    a.setY(9.677);
    a.setRadius(20);
    System.out.println("X: " + a.getX());
    System.out.println("Y: " + a.getY());
    System.out.println("Coordinates: " + a);
    System.out.println("Radius: " + a.getRadius());
  }
}
