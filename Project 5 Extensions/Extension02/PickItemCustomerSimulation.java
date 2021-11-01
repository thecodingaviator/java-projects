/*
Name: Parth Parth
Date: 10/31/2021
File: PickyCustomerSimulation.java
Section: A
*/

import java.util.Random;
import java.util.ArrayList;

public class PickItemCustomerSimulation {
  public static void main(String[] args) throws InterruptedException {
    Random gen=new Random();
    ArrayList<CheckoutAgent> checkouts=new ArrayList<CheckoutAgent>(5);

    for(int i=0; i<5; i++) {
      CheckoutAgent checkout=new CheckoutAgent(i*100 + 50, 480);
      checkouts.add(checkout);
    }
    
    Landscape scape=new Landscape(500, 500, checkouts);
    LandscapeDisplay display=new LandscapeDisplay(scape);

    for(int i=1; i<=2000; i++) {
      int customers=0;

      for(CheckoutAgent checkout: checkouts) {
        for(Customer cust: checkout.getQueue()) {
          customers+=cust.getNumItems();
        }
      }

      Customer cust=new PickItemCustomer(1+gen.nextInt(8), customers);
      int choice=cust.chooseLine(checkouts);
      checkouts.get(choice).addCustomerToQueue(cust);
      scape.updateCheckouts();
      if(i%100==0) {
        System.out.println("\nAfter " + i + " time-steps:");
        scape.printFinishedCustomerStatistics();
      }
      display.repaint();
      Thread.sleep(0);
    }
  }
}