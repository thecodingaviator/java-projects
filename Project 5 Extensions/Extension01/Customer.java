/*
Name: Parth Parth
Date: 10/25/2021
File: Customer.java
Section: A
*/

import java.util.ArrayList;

public abstract class Customer {
  private int items;
  private int steps;

  public Customer(int num_items) {
    this.items=num_items;
    this.steps=0;
  }

  public Customer(int num_items, int time_steps) {
    this.items=num_items;
    this.steps=time_steps;
  }

  public void incrementTime() {
    this.steps++;
  }

  public int getTime() {
    return this.steps;
  }

  public void giveUpItem() {
    this.items--;
  }

  public int getNumItems() {
    return this.items;
  }
  
  public abstract int chooseLine(ArrayList<CheckoutAgent> checkouts);
}