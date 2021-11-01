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
    int a=gen.nextInt(checkouts.size());
    int b=gen.nextInt(checkouts.size());
    
    while(a==b){
      b=gen.nextInt(checkouts.size());
    }

    return checkouts.get(a).getNumInQueue()<checkouts.get(b).getNumInQueue()?a:b;
  }
}
