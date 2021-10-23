/*
Name: Parth Parth
Date: 10/12/2021
File: CatSocialAgent.java
Section: A
*/

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

public class CatSocialAgent extends SocialAgent {
  private int category;

  public CatSocialAgent(double x0, double y0, int cat) {
    super(x0, y0, 15);
    this.category=cat;
  }

  public CatSocialAgent(double x0, double y0, int cat, int radiusOfSensitivity) {
    super(x0, y0, radiusOfSensitivity);
    this.category=cat;
  }

  public int getCategory() {
    return this.category;
  }

  public String toString() {
    return "(" + this.x + ", " + this.y + ")" + " Category: " + this.category;
  }

  public void draw(Graphics g) {
    Graphics2D gr=(Graphics2D) g;

    // if category is 0
    // if cell is moving, color is cyan
    gr.setColor(Color.CYAN);
    gr.setPaint(Color.CYAN);

    // if cell has moved, set color to blue
    if(this.moved) {
      gr.setColor(Color.BLUE);
      gr.setPaint(Color.BLUE);      
    }

    // if category is 1, the colors will be overwritten
    if(this.getCategory()==1) {
      gr.setColor(Color.GRAY);
      gr.setPaint(Color.GRAY);
      
      if(this.moved) {
        gr.setColor(Color.BLACK);
        gr.setPaint(Color.BLACK);
      }
    }

    // draw circle in circle
    Ellipse2D.Double circle=new Ellipse2D.Double(this.getX(), this.getY(), 10, 10);
    // fill circle
    gr.fill(circle);
    gr.draw(circle);
  }

  public void updateState(Landscape scape) {
    Random gen=new Random();
    this.moved=false;
    ArrayList<Agent> neighbours=scape.getNeighbors(this.getX(), this.getY(), this.getRadius());

    // if >=2 neighbours and more are of the same category
    if(neighbours.size()>1 && moreSameCategory(neighbours)) {
      // with a 1% probability
      if(gen.nextInt(100) == 0) {
        // set moved to true and move it
        this.moved=true;
        this.setX(this.getX()+gen.nextDouble(-10, 10));
        this.setY(this.getY()+gen.nextDouble(-10, 10));
      }
    }
    else {
      // set moved to true and move it
      this.moved=true;
      this.setX(this.getX()+gen.nextDouble(-10, 10));
      this.setY(this.getY()+gen.nextDouble(-10, 10));
    }

    if(this.getX()>scape.getWidth()) {
      this.setX(scape.getWidth());
    }
    if(this.getY()>scape.getHeight()) {
      this.setY(scape.getHeight());
    }
  }

  private boolean moreSameCategory(ArrayList<Agent> neighbours) {
    int counter=0;

    // iterate over neighbours
    for(Agent e: neighbours) {
      // if same category as given CatSocialAgent
      if(((CatSocialAgent) e).getCategory()==this.getCategory()) {
        counter++;
      }
    }

    // return if number of elements w/ category same as this is more than remaining elements
    return counter>neighbours.size()-counter;
  }

  public static void main(String[] args) {
    CatSocialAgent a=new CatSocialAgent(1, 1, 0, 15);
    CatSocialAgent b=new CatSocialAgent(2, 2, 0, 15);
    CatSocialAgent c=new CatSocialAgent(14, 14, 0, 15);
    CatSocialAgent d=new CatSocialAgent(5, 5, 1, 15);
    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
    System.out.println(d);

    ArrayList<Agent> neighbours=new ArrayList<Agent>();
    neighbours.add(a);
    neighbours.add(b);
    neighbours.add(c);
    neighbours.add(d);

    System.out.println("More of same category as first agent? " + a.moreSameCategory(neighbours));

    System.out.println("Change category of third agent to 1");
    c=new CatSocialAgent(14, 14, 1, 15);
    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
    System.out.println(d);
    neighbours=new ArrayList<Agent>();
    neighbours.add(a);
    neighbours.add(b);
    neighbours.add(c);
    neighbours.add(d);

    System.out.println("More of same category as first cell? " + a.moreSameCategory(neighbours));
  }
}
