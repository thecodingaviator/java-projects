/*
Name: Parth Parth
Date: 09/18/2021
File: Card.java
Section: A
*/

public class Card {

  private int value;

  // default constructor in case user passes no value
  public Card() {
    value=2;
    System.out.println("The card has been assigned a default value of 2");
  }

  public Card(int val) {
    // sanity check to make sure data is in range
    if(val>1 && val<12) {
      value=val;
    // if value isnt in range
    } else if(val==-1) {
      // i am returning -1 in some invalid cases
      System.out.println("A value of -1 was returned with the card. This is probably due to some error. Please see above for additional information.");
    } else {
      System.out.println("Value entered (" + val + ") was out of allowed range (2-11). -1 has been assigned to card");
      value=-1;
    }
  }

  // return value of card as int
  public int getValue() {
    return value;
  }

  //return value of card as String
  public String toString() {
    return Integer.toString(value);
  }

  public static void main(String[] args) {
    // Creating card objects and testing them
    Card myCard1=new Card();
    Card myCard2=new Card(10);
    Card myCard3=new Card(17);

    System.out.println("Value of Card 1: " + myCard1.getValue());
    System.out.println("Value of Card 2: " + myCard2.getValue());
    System.out.println("Value of Card 3: " + myCard3.getValue());

    System.out.println("String Value of Card 1: " + myCard1.toString());
    System.out.println("String Value of Card 2: " + myCard2.toString());
    System.out.println("String Value of Card 3: " + myCard3.toString());
  }
}