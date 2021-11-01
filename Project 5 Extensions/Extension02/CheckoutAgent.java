/*
Name: Parth Parth
Date: 10/25/2021
File: CheckoutAgent.java
Section: A
*/

import java.awt.Graphics;

public class CheckoutAgent {
  private int x;
  private int y;
  private MyQueue<Customer> queue;
  private int totalItems;

  public CheckoutAgent(int x, int y) {
    this.x=x;
    this.y=y;
    this.queue=new MyQueue<Customer>();
    this.totalItems=0;
  }

  public void addCustomerToQueue(Customer c) {
    this.totalItems+=c.getNumItems();
    this.queue.offer(c);
  }

  public int getNumInQueue() {
    return this.queue.size();
  }

  public int getNumItems() {
    return this.totalItems;
  }

  public void draw(Graphics g) {
    g.fillRect(x, y-10*getNumInQueue(), 10, 10*this.getNumInQueue());
  }

  public void updateState(Landscape scape) {
    for(Customer e: this.queue) {
      e.incrementTime();
    }
    Customer front=this.queue.peek();
    if(front!=null) {
      front.giveUpItem();
      this.totalItems--;
      if(front.getNumItems()==0) {
        scape.addFinishedCustomer(front);
        this.queue.poll();
      }
    }
  }

  public MyQueue<Customer> getQueue() {
    return this.queue;
  }
}
