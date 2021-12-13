/*
Name: Parth Parth
Date: 11/29/2021
File: GraphTest.java
Section: A
*/

public class GraphTest {
  public static void main(String[] args) {
    Graph g = new Graph();
    Vertex[][] grid = new Vertex[4][4];

    // create 25 vertices
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        grid[i][j] = new Vertex(i, j, false);
      }
    }

    System.out.println("Pre-test");

    String s = "";
    for (int i = 0; i < 4; i++) {
      String t = "";
      for (int j = 0; j < 4; j++) {
        s += grid[i][j].getDistance();
        if (j < 4 - 1) {
          s += " -- ";
        }
        t += " |     ";
      }
      s += "\n";
      if (i < 4 - 1) {
        s += t + "\n";
      }
    }
    System.out.println(s);

    g.addBiEdge(grid[0][0], grid[0][1]);
    g.addBiEdge(grid[0][0], grid[0][2]);
    g.addBiEdge(grid[0][0], grid[0][3]);

    g.addBiEdge(grid[0][1], grid[1][0]);
    g.addBiEdge(grid[0][1], grid[1][1]);

    g.addBiEdge(grid[0][2], grid[2][0]);
    g.addBiEdge(grid[0][2], grid[2][1]);
    g.addBiEdge(grid[0][2], grid[2][2]);

    g.addBiEdge(grid[0][3], grid[3][0]);
    g.addBiEdge(grid[0][3], grid[3][1]);
    g.addBiEdge(grid[0][3], grid[3][2]);

    g.addBiEdge(grid[1][0], grid[0][1]);

    g.addBiEdge(grid[1][1], grid[0][1]);
    g.addBiEdge(grid[1][1], grid[1][2]);

    g.addBiEdge(grid[1][2], grid[1][1]);
    g.addBiEdge(grid[1][2], grid[2][2]);

    g.addBiEdge(grid[1][3], grid[3][1]);

    g.addBiEdge(grid[2][0], grid[0][2]);
    g.addBiEdge(grid[2][0], grid[2][1]);

    g.addBiEdge(grid[2][1], grid[2][0]);
    g.addBiEdge(grid[2][1], grid[2][2]);

    g.addBiEdge(grid[2][2], grid[1][2]);
    g.addBiEdge(grid[2][2], grid[2][1]);
    g.addBiEdge(grid[2][2], grid[3][2]);

    g.addBiEdge(grid[2][3], grid[0][1]);

    g.addBiEdge(grid[3][0], grid[0][3]);
    g.addBiEdge(grid[3][0], grid[3][1]);

    g.addBiEdge(grid[3][1], grid[3][0]);
    g.addBiEdge(grid[3][1], grid[3][2]);

    g.addBiEdge(grid[3][2], grid[2][2]);
    g.addBiEdge(grid[3][2], grid[3][1]);
    g.addBiEdge(grid[3][2], grid[3][3]);

    g.addBiEdge(grid[3][3], grid[0][1]);

    System.out.println("Running on grid[1][1]\n");

    g.shortestPath(grid[1][1]);

    s = "";
    for (int i = 0; i < 4; i++) {
      String t = "";
      for (int j = 0; j < 4; j++) {
        s += grid[i][j].getDistance();
        if (j < 4 - 1) {
          s += " -- ";
        }
        t += " |     ";
      }
      s += "\n";
      if (i < 4 - 1) {
        s += t + "\n";
      }
    }
    System.out.println(s);

    System.out.println("Additional test");

    System.out.println("\nRunning on grid[3][0]\n");

    g.shortestPath(grid[3][0]);

    s = "";
    for (int i = 0; i < 4; i++) {
      String t = "";
      for (int j = 0; j < 4; j++) {
        s += grid[i][j].getDistance();
        if (j < 4 - 1) {
          s += " -- ";
        }
        t += " |     ";
      }
      s += "\n";
      if (i < 4 - 1) {
        s += t + "\n";
      }
    }
    System.out.println(s);
  }
}