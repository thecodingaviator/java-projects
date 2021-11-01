/*
Name: Parth Parth
Date: 10/25/2021
File: Landscape.java
Section: A
*/

import java.awt.Graphics;
import java.util.ArrayList;

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
    return "Checkouts: " + this.list.size() + " Finished: " + this.completed.size();
  }

  public void addFinishedCustomer(Customer c) {
    this.completed.addLast(c);
  }

  public void draw(Graphics g) {
    for(CheckoutAgent e: this.list) {
      e.draw(g);
    }
  }

  public void updateCheckouts() {
    for (CheckoutAgent e: this.list) {
      e.updateState(this);
    }
  }

  public void printFinishedCustomerStatistics() {
    // calculate average
    double time=0.0;
    for(Customer e: this.completed) {
      time+=e.getTime();
    }
    time/=this.completed.size();
    System.out.println("Average time: " + time);

    // calculate standard deviation
    double sd=0.0;
    for(Customer e: this.completed) {
      sd+=Math.pow(e.getTime()-time, 2);
    }
    sd/=this.completed.size()-1;
    sd=Math.sqrt(sd);

    System.out.println("Standard deviation: " + sd);
  }
}
