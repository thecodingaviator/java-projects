/*
Name: Parth Parth
Date: 10/25/2021
File: Pick2Customer.java
Section: A
*/

import java.util.ArrayList;
import java.util.Random;

public class Pick2Customer extends Customer {
  public Pick2Customer(int num_items) {
    super(num_items, 2);
  }

  public int chooseLine(ArrayList<CheckoutAgent> checkouts) {
    Random gen=new Random();
    int a=0;
    int b=0;
    do {
      a=gen.nextInt(checkouts.size());
      b=gen.nextInt(checkouts.size());
    } while(a!=b);

    return checkouts.get(a).getNumInQueue()<checkouts.get(b).getNumInQueue()?a:b;
  }
}
