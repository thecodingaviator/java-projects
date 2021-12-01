/*
Name: Parth Parth
Date: 10/11/2021
File: Landscape.java
Section: A
*/

import java.awt.Graphics;
import java.awt.Color;

public class Landscape {
  private int w;
  private int h;
  private LinkedList<Vertex> vertices;
  private Hunter hunter;
  private Wumpus wumpus;

  public Landscape(int w, int h, Hunter hunter, Wumpus wumpus) {
    this.w = w + 40;
    this.h = h + 40;
    this.vertices = new LinkedList<Vertex>();
    this.hunter = hunter;
    this.wumpus = wumpus;
  }

  public int getHeight() {
    return this.h;
  }

  public int getWidth() {
    return this.w;
  }

  public void draw(Graphics g, int scale, int state) throws InterruptedException {
    for(Vertex v : vertices) {
      v.draw(g, scale);
    }
    hunter.draw(g, scale);
    wumpus.draw(g, scale);

    if(state==1) {
      g.setColor(Color.black);
      g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
      g.setColor(Color.red);
      g.drawString("You Lost!", g.getClipBounds().width / 2 - g.getFontMetrics().stringWidth("You Lost!") / 2, g.getClipBounds().height / 2);
    }

    if(state==2) {
      g.setColor(Color.black);
      g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
      g.setColor(Color.green);
      g.drawString("You Won!", g.getClipBounds().width / 2 - g.getFontMetrics().stringWidth("You Won!") / 2, g.getClipBounds().height / 2);
    }

    if(state==3) {
      g.setColor(Color.black);
      g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
      g.setColor(Color.red);
      g.drawString("You shot the arrow in an empty room!", g.getClipBounds().width / 2 - g.getFontMetrics().stringWidth("You shot the arrow in an empty room!") / 2, g.getClipBounds().height / 2);
    }
  }

  public void addBackgroundAgent(Vertex v1) {
    this.vertices.addLast(v1);
  }
}
