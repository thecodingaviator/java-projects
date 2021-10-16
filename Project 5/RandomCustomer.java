/*
Name: Parth Parth
Date: 10/25/2021
File: RandomCustomer.java
Section: A
*/

import java.util.ArrayList;
import java.util.Random;

public class RandomCustomer extends Customer {
  public RandomCustomer(int num_items) {
    super(num_items, 1);
  }

  public int chooseLine(ArrayList<CheckoutAgent> checkouts) {
    Random gen=new Random();
    return gen.nextInt(checkouts.size());
  }
}
