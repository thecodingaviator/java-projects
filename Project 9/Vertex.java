/*
Name: Parth Parth
Date: 11/29/2021
File: Vertex.java
Section: A
*/

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.*;

public class Vertex implements Comparable<Vertex> {
  private ArrayList<Vertex> adj;
  private int x;
  private int y;
  private boolean isVisible;
  private double distance;
  private boolean isVisited;
  private Vertex parent;
  private Vertex N, S, E, W;

  public Vertex(int x, int y) {
    this.x = x;
    this.y = y;
    this.adj = new ArrayList<Vertex>();
    this.isVisible = false;
    this.distance = 0;
    this.isVisited = false;
    this.parent = null;
    this.N = null;
    this.S = null;
    this.E = null;
    this.W = null;
  }

  public Vertex(int x, int y, boolean isVisited) {
    this.x = x;
    this.y = y;
    this.adj = new ArrayList<Vertex>();
    this.isVisible = false;
    this.distance = 0;
    this.isVisited = isVisited;
    this.parent = null;
    this.N = null;
    this.S = null;
    this.E = null;
    this.W = null;
  }

  public enum Direction {
    NORTH, SOUTH, EAST, WEST
  }

  public double distance(Vertex other) {
    // use cartesian distance
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }

  public Vertex getNeighbor(int x, int y) {
    for (Vertex v : adj) {
      // if the x and y coordinates of vertex match, return it
      if (v.x == this.x && v.y == this.y) {
        return v;
      }
    }
    return null;
  }

  public ArrayList<Vertex> getNeighbors() {
    return this.adj;
  }

  public int numNeighbors() {
    return this.adj.size();
  }

  public String toString() {
    DecimalFormat df = new DecimalFormat("0.00");
    return "(" + this.x + ", " + this.y + ") Neighbors: " + this.numNeighbors() + " Cost: " + df.format(this.getDistance()) + " Visited: " + this.isVisited;
  }

  public int compareTo(Vertex other) {
    return (int) (this.distance - other.distance);
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void setVisible(boolean isVisible) {
    this.isVisible = isVisible;
  }

  public void setDistance(double distance) {
    this.distance = distance;
  }

  public void setVisited(boolean isVisited) {
    this.isVisited = isVisited;
  }

  public void setParent(Vertex parent) {
    this.parent = parent;
  }

  public double getDistance() {
    int hundredMultiple = (int) (this.distance * 100);
    return (double) hundredMultiple / 100;
  }

  public boolean isVisited() {
    return this.isVisited;
  }

  public static boolean matchPosition(Vertex a, Vertex b) {
    return a.x == b.x && a.y == b.y;
  }

  public void connect(Vertex other, Vertex.Direction dir) {
    switch (dir) {
      case NORTH:
        this.connect(other);
        this.N = other;
        break;
      case SOUTH:
        this.connect(other);
        this.S = other;
        break;
      case EAST:
        this.connect(other);
        this.E = other;
        break;
      case WEST:
        this.connect(other);
        this.W = other;
        break;
    }
  }

  public void connect(Vertex other) {
    this.adj.add(other);
  }

  public void draw(Graphics g, int scale) {
    if (!this.isVisible)
      return;
    int xpos = (int) this.getX() * scale + 20;
    int ypos = (int) this.getY() * scale + 20;
    int border = 2;
    int half = scale / 2;
    int eighth = scale / 8;
    int sixteenth = scale / 16;

    // draw rectangle for the walls of the room
    if (this.getDistance() <= 2)
      // wumpus is nearby
      g.setColor(Color.red);
    else
      // wumpus is not nearby
      g.setColor(Color.gray);

    g.drawRect(xpos + border, ypos + border, scale - 2 * border, scale - 2 * border);

    // draw doorways as boxes
    g.setColor(Color.black);
    if (this.getNeighbor(this.getX(), this.getY() - 1) != null)
      g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
    if (this.getNeighbor(this.getX(), this.getY() + 1) != null)
      g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth),
          eighth, eighth + sixteenth);
    if (this.getNeighbor(this.getX() - 1, this.getY()) != null)
      g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
    if (this.getNeighbor(this.getX() + 1, this.getY()) != null)
      g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth,
        eighth + sixteenth, eighth);
  }

  public static void main(String[] args) {
    Vertex a = new Vertex(1, 1);
    Vertex b = new Vertex(2, 2);
    Vertex c = new Vertex(3, 3);

    a.connect(b);
    a.connect(c);

    System.out.println(a);

    for (Vertex v : a.getNeighbors()) {
      System.out.println(v);
    }

    Vertex d = new Vertex(1, 1);

    System.out.println(matchPosition(a, d));
    System.out.println(c.compareTo(d));
  }
}