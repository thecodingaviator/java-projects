/*
Name: Parth Parth
Date: 10/31/2021
File: PickItemCustomer.java
Section: A
*/

import java.util.ArrayList;

public class PickItemCustomer extends Customer {
  public PickItemCustomer(int num_items, int num_customers) {
    super(num_items, num_customers);
  }

  public int chooseLine(ArrayList<CheckoutAgent> checkouts) {
    int line=0;
    int min=checkouts.get(0).getNumItems();

    for(int i=0; i<checkouts.size(); i++) {
      if(checkouts.get(i).getNumItems()<min) {
        min=checkouts.get(i).getNumItems();
        line=i;
      }
    }

    return line;
  }
}