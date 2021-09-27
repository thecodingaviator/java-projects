/*
Name: Parth Parth
Date: 09/18/2021
File: Hand.java
Section: A
*/

import java.util.ArrayList;

public class Hand {

  private ArrayList<Card> hand;

  public Hand() {
    hand=new ArrayList<Card>();
  }

  public void reset() {
    System.out.println("Hand has been reset.");
    // reset hand by basically getting rid of the old reference and assigning a new one
    hand=new ArrayList<Card>();
  }

  public void add(Card card) {
    // the following line can be uncommented to see how cards are added to hand in real time
    // System.out.println("Card with value " + card.getValue() + " added to hand.");
    hand.add(card);
  }

  public int size() {
    return hand.size();
  }

  public Card getCard(int i) {
    return hand.get(i);
  }

  public int getTotalValue() {
    int total = 0;
    // Loop through all Card-s in hand
    for(Card card: hand) {
      total += card.getValue();
    }
    return total;
  }

  public String toString() {
    String handString="The cards in the hand are:\n";
    // Loop through all Card-s in hand
    for(Card card: hand) {
      handString += card.toString()+" ";
    }
    return handString;
  }

  public static void main(String[] args) {
    // Creating hand object and testing it
    Hand hand1=new Hand();
    Card card1=new Card(5);
    hand1.add(card1);
    hand1.add(new Card(7));
    System.out.println("Size of deck is: " + hand1.size());
    System.out.println("Card no 2 in the hand is: " + hand1.getCard(1));
    hand1.reset();
    hand1.add(card1);
    card1=new Card(8);
    hand1.add(card1);
    System.out.println("Total value of hand: " + hand1.getTotalValue());
    System.out.println(hand1);
    System.out.println(hand1.toString());
  }
}
