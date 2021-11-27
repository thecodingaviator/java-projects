/*
Name: Parth Parth
Date: 11/29/2021
File: Vertex.java
Section: A
*/

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Vertex implements Comparable<Vertex> {
  private ArrayList<Vertex> adj;
  private int x;
  private int y;
  private boolean isVisible;
  private double distance;
  private boolean isVisited;
  private Vertex parent;

  public Vertex(int x, int y) {
    this.x = x;
    this.y = y;
    this.adj = new ArrayList<Vertex>();
    this.isVisible = false;
    this.distance = 0;
    this.isVisited = false;
    this.parent = null;
  }

  public Vertex(int x, int y, boolean isVisited) {
    this.x = x;
    this.y = y;
    this.adj = new ArrayList<Vertex>();
    this.isVisible = false;
    this.distance = 0;
    this.isVisited = isVisited;
    this.parent = null;
  }

  public double distance(Vertex other) {
    // use cartesian distance
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }

  public void connect(Vertex other) {
    this.adj.add(other);
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
    return "(" + this.x + ", " + this.y + ") Neighbors: " + this.numNeighbors() + " Cost: " + df.format(this.getDistance()) + " Visited: " + this.isVisible;
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
    return this.distance;
  }

  public boolean isVisited() {
    return this.isVisited;
  }

  public static boolean matchPosition(Vertex a, Vertex b) {
    return a.x == b.x && a.y == b.y;
  }

  public static void main(String[] args) {
    Vertex a = new Vertex(1, 1);
    Vertex b = new Vertex(2, 2);
    Vertex c = new Vertex(3, 3);

    a.connect(b);
    a.connect(c);

    System.out.println(a);

    for(Vertex v : a.getNeighbors()) {
      System.out.println(v);
    }

    Vertex d = new Vertex(1, 1);

    System.out.println(matchPosition(a, d));
    System.out.println(c.compareTo(d));
  }
}