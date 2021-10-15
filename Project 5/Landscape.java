import java.util.ArrayList;
import java.util.LinkedList;

/*
Name: Parth Parth
Date: 10/25/2021
File: Landscape.java
Section: A
*/

import java.awt.Graphics;

public class Landscape {
  private int w;
  private int h;
  private ArrayList<CheckoutAgent> list;
  private LinkedList<Customer> completed;

  public Landscape(int w, int h, ArrayList<CheckoutAgent> checkouts) {
    this.w=w;
    this.h=h;
    this.list=checkouts;
  }

  public int getHeight() {
    return this.h;
  }

  public int getWidth() {
    return this.w;
  }

  public String toString() {
    return "Checkouts: " + list.size() + "Finished: " + completed.size();
  }

  public void addFinishedCustomer(Customer c) {
    completed.add(c);
  }

  public void draw(Graphics g) {
    for(CheckoutAgent e: list) {
      e.draw(g);
    }
  }
}
