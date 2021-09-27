/**
 * File: Shuffle.java
 * Author: Parth Parth
 * Date: 09/13/2021
 */


import java.util.ArrayList;
import java.util.Random;

public class Shuffle {
  public static void main(String[] args) {
    Random gen=new Random();

    ArrayList<Integer> numbers=new ArrayList<Integer>();

    // Add values to ArrayList
    System.out.println("Values being added to ArrayList in sequence:");
    for(int i=0; i<10; i++) {
      numbers.add(gen.nextInt(100));
      System.out.println(numbers.get(i));
    }

    // Print the whole thing again
    System.out.println("The final ArrayList (in sequence):");
    for(int i=0; i<10; i++) {
      System.out.println(numbers.get(i));
    }

    // Remove all members one by one
    for(int i=0; i<10; i++) {
      int index=gen.nextInt(numbers.size());
      int removed=numbers.get(index);
      System.out.print("Removed value is " + removed 
      //+ " at index " + index 
      + ". The remaining values are: ");
      for(int j=0; j<numbers.size(); j++) {
        System.out.print(numbers.get(j) + " ");
      }
      System.out.println();
      numbers.remove(index);
    }

    // Populate ArrayList with values 1 through 10
    for(int i=0; i<10; i++) {
      numbers.add(i+1);
    }

    // Create a new ArrayList that will store values in random order
    ArrayList<Integer> randomNum=new ArrayList<Integer>();

    // Loop through all indexes in randomNum and populate it
    for(int i=0; i<10; i++) {
      // Get an index that will have its value assigned to the new ArrayList
      int index=gen.nextInt(numbers.size());
      // Add the randomly recieved number to the new ArrayList
      randomNum.add(numbers.get(index));
      // Remove the randomly selected value from the old ArrayList
      numbers.remove(index);
    }

    System.out.print("ArrayList with numbers in random order is: ");
    for(int i=0; i<10; i++) {
      System.out.print(randomNum.get(i) + " ");
    }

    // Can also be done like this
    // System.out.println("ArrayList with numbers in random order is: " + randomNum);
  }
}
