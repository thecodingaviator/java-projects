/*
Name: Parth Parth
Date: 10/08/2021
File: SocialAgent.java
Section: A
*/

import java.awt.Color;
import java.awt.Graphics;

public class SocialAgent extends Agent {
  private boolean moved;
  private int R;

  public SocialAgent(double x0, double y0, int r0) {
    super(x0, y0);
    this.R=r0;
  }

  public void setRadius(int radius) {
    this.R=radius;
  }

  public int getRadius() {
    return this.R;
  }

  public void draw(Graphics g) {
    g.setColor(Color.GRAY);
    if(this.moved) {
      g.setColor(Color.BLUE);
    }
    g.fillOval((int)this.x, (int)this.y, 10, 10);
  }
  
  public static void main(String[] args) {
    SocialAgent s=new SocialAgent(1.7, 2.5, 7);    
  }
}
