/*
Name: Parth Parth
Date: 10/25/2021
File: Landscape.java
Section: A
*/

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

public class Landscape {
  private int w;
  private int h;
  private ArrayList<CheckoutAgent> list;
  private LinkedList<Customer> completed;

  public Landscape(int w, int h, ArrayList<CheckoutAgent> checkouts) {
    this.w=w;
    this.h=h;
    this.list=checkouts;
    this.completed=new LinkedList<Customer>();
  }

  public int getHeight() {
    return this.h;
  }

  public int getWidth() {
    return this.w;
  }

  public String toString() {
    return "Checkouts: " + this.list.size() + "Finished: " + this.completed.size();
  }

  public void addFinishedCustomer(Customer c) {
    this.completed.add(c);
  }

  public void draw(Graphics g) {
    for(CheckoutAgent e: this.list) {
      e.draw(g);
    }
  }

  public void updateCheckouts() {
    Landscape scape=new Landscape(500, 500, list);
    for (CheckoutAgent e: this.list) {
      e.updateState(scape);
    }
  }
}
