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

  public CheckoutAgent(int x, int y) {
    this.x=x;
    this.y=y;
    this.queue=new MyQueue<Customer>();
  }

  public void addCustomerToQueue(Customer c) {
    queue.offer(c);
  }

  public int getNumInQueue() {
    int items=0;
    // will loop over each item
    for(Customer e: queue) {
      // so i can just count them
      items++;
    }
    return items;
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
      if(front.getNumItems()==0) {
        scape.addFinishedCustomer(front);
        this.queue.poll();
      }
    }
  }
}
