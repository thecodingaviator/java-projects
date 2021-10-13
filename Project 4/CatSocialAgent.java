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
    return ""+this.category;
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
    ArrayList<Agent> neighbours=scape.getNeighbors(this.getX(), this.getY(), this.getRadius());
    if(neighbours.size()>1 && moreSameCategory(neighbours)) {
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

  private boolean moreSameCategory(ArrayList<Agent> neighbours) {
    int counter=0;
    for(Agent e: neighbours) {
      if(((CatSocialAgent) e).getCategory()==this.getCategory()) {
        counter++;
      }
    }
    return counter>neighbours.size()-counter;
  }
}
