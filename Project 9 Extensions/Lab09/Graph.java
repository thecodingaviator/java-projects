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
    // if vertex v1 is not in the graph, add it
    if (!this.inGraph(v1)) {
      this.vertices.add(v1);
    }
    // if vertex v2 is not in the graph, add it
    if (!this.inGraph(v2)) {
      this.vertices.add(v2);
    }
    // add an edge from v1 to v2
    v1.connect(v2);
  }

  public void addBiEdge(Vertex v1, Vertex v2) {
    // if vertex v1 is not in the graph, add it
    if (!this.inGraph(v1)) {
      this.vertices.add(v1);
    }
    // if vertex v2 is not in the graph, add it
    if (!this.inGraph(v2)) {
      this.vertices.add(v2);
    }
    // add an edge from v1 to v2
    v1.connect(v2);
    // add an edge from v2 to v1
    v2.connect(v1);
  }

  public void shortestPath(Vertex v0) {
    for(Vertex v : this.vertices) {
      // set all vertices to have a distance of infinity
      v.setDistance(Double.POSITIVE_INFINITY);
      // set all vertices to have no previous vertex
      v.setParent(null);
      // set all vertices to have not been visited
      v.setVisited(false);
    }

    PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();

    // set the distance of v0 to 0
    v0.setDistance(0);
    pq.add(v0);

    // while the priority queue is not empty
    while(!pq.isEmpty()) {
      // get the vertex with the smallest distance
      Vertex v = pq.poll();
      // if vertex v has been visited, continue
      if(v.isVisited()) {
        continue;
      }

      // set vertex v to visited
      v.setVisited(true);
      
      // for each vertex adjacent to v
      for(Vertex w : v.getNeighbors()) {
        // get the distance of vertex v from w
        double distance = v.distance(w);
        // if w is not visited and v.cost + distance < w.cost
        if(!w.isVisited() && v.getDistance() + distance < w.getDistance()) {
          // w.cost = v.cost + distance
          // make v the parent of w
          // add w to pq
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

  public ArrayList<Vertex> getVertices() {
    return this.vertices;
  }

  public static void main(String[] args) {
    Graph g = new Graph();
    Vertex v1 = new Vertex(1, 0);
    Vertex v2 = new Vertex(2, 0);
    Vertex v3 = new Vertex(3, 0);
    Vertex v4 = new Vertex(4, 0);
    Vertex v5 = new Vertex(5, 0);
    Vertex v6 = new Vertex(6, 0);

    g.addBiEdge(v1, v2);
    g.addBiEdge(v1, v3);
    g.addBiEdge(v2, v4);
    g.addBiEdge(v2, v5);
    g.addBiEdge(v3, v6);
    g.addBiEdge(v4, v6);

    System.out.println(g);

    g.shortestPath(v3);

    System.out.println(g);

    System.out.println("Vertex count: " + g.vertexCount());
    System.out.println("In graph v1? " + g.inGraph(v1));
  }
}
