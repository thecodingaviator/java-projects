/*
Name: Parth Parth
Date: 11/29/2021
File: Graph.java
Section: A
*/

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Graph {
  private ArrayList<Vertex> vertices;

  public Graph() {
    this.vertices = new ArrayList<Vertex>();
  }

  public int vertexCount() {
    return this.vertices.size();
  }

  public boolean inGraph(Vertex v) {
    return this.vertices.contains(v);
  }

  public void addUniEdge(Vertex v1, Vertex v2) {
    if (!this.inGraph(v1)) {
      this.vertices.add(v1);
    }
    if (!this.inGraph(v2)) {
      this.vertices.add(v2);
    }
    v1.connect(v2);
  }

  public void addBiEdge(Vertex v1, Vertex v2) {
    if (!this.inGraph(v1)) {
      this.vertices.add(v1);
    }
    if (!this.inGraph(v2)) {
      this.vertices.add(v2);
    }
    v1.connect(v2);
    v2.connect(v1);
  }

  public void shortestPath(Vertex v0) {
    for(Vertex v : this.vertices) {
      v.setDistance(Double.POSITIVE_INFINITY);
      v.setParent(null);
      v.setVisited(false);
    }

    PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();

    v0.setDistance(0);
    pq.add(v0);

    while(!pq.isEmpty()) {
      Vertex v = pq.poll();
      if(v.isVisited()) {
        continue;
      }

      v.setVisited(true);
      
      for(Vertex w : v.getNeighbors()) {
        double distance = v.distance(w);
        if(!w.isVisited() && v.getDistance() + distance < w.getDistance()) {
          w.setDistance(v.getDistance() + distance);
          w.setParent(v);
          pq.add(w);
        }
      }
    }
  }

  public String toString() {
    String s = "";
    for(Vertex v : this.vertices) {
      s += v + "\n";
    }
    return s;
  }

  public static void main(String[] args) {
    Graph g = new Graph();
    Vertex v1 = new Vertex(1, 0);
    Vertex v2 = new Vertex(2, 0);
    Vertex v3 = new Vertex(3, 0);
    Vertex v4 = new Vertex(4, 0);
    Vertex v5 = new Vertex(5, 0);
    Vertex v6 = new Vertex(6, 0);

    g.addUniEdge(v1, v2);
    g.addUniEdge(v1, v3);
    g.addUniEdge(v2, v4);
    g.addUniEdge(v2, v5);
    g.addUniEdge(v3, v6);
    g.addUniEdge(v4, v6);

    System.out.println(g);

    g.shortestPath(v3);

    System.out.println(g);
  }

  public ArrayList<Vertex> getVertices() {
    return this.vertices;
  }
}
