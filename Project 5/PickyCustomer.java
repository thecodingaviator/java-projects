import java.util.ArrayList;

/*
Name: Parth Parth
Date: 10/25/2021
File: PickyCustomer.java
Section: A
*/

public class PickyCustomer extends Customer {
  public PickyCustomer(int num_items, int num_lines) {
    super(num_items, num_lines);
  }

  public int chooseLine(ArrayList<CheckoutAgent> checkouts) {
    int count=checkouts.get(0).getNumInQueue();
    int index=0;

    for(int i=0; i<checkouts.size(); i++) {
      if(checkouts.get(i).getNumInQueue()<count) {
        index=i;
        count=checkouts.get(i).getNumInQueue();
      }
    }

    return index;
  }
}
