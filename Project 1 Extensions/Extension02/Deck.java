/*
Name: Parth Parth
Date: 09/18/2021
File: Deck.java
Section: A
*/

import java.util.ArrayList;
import java.util.Random;

public class Deck {
  private ArrayList<Card> deck;

  public Deck() {
    build();
  }

  public void build() {
    deck=new ArrayList<Card>();
    // Function modified to include 24 each of cards with values 2-9 and 11, and 96 cards with the value 10.
    for(int i=1; i<25; i++) {
      for(int j=2; j<12; j++) {
        deck.add(new Card(j));
      }
    }
    for(int i=1; i<73; i++) {
      deck.add(new Card(10));
    }
  }

  public int size() {
    return deck.size();
  }

  public Card deal() {
    if(deck.size()>0)
      return deck.remove(0);
    // error case. if no cards are left, none can be removed
    System.out.println("Deck has no cards left. -1 returned");
    return new Card(-1);
  }

  public Card pick(int i) {
    if(i<deck.size())
      return deck.remove(i);
    // error case if invalid index was passed to be removed
    System.out.println("Invalid index to remove cards passed. -1 returned");
    return new Card(-1);
  }

  public void shuffle() {
    Random generator=new Random();
    ArrayList<Card> shuffled=new ArrayList<Card>();
    while(deck.size() != 0) {
      // Get an index that will have its value assigned to the new ArrayList
      int index=generator.nextInt(deck.size());
      // Add the randomly recieved number to the new ArrayList
      shuffled.add(deck.get(index));
      // Remove the randomly selected value from the old ArrayList
      deck.remove(index);
    }
    deck=shuffled;
  }

  public String toString() {
    String deckString="";
    // Loop through all Card-s in hand
    for(Card card: deck) {
      deckString += card.toString()+" ";
    }
    return deckString;
  }

  public static void main(String[] args) {
    // Creating deck object and testing it
    Deck deck1=new Deck();
    System.out.println("Original deck: \n" + deck1);
    deck1.shuffle();
    System.out.println("Shuffled deck: \n" + deck1);
    System.out.println("Deck size: " + deck1.size());
    System.out.println("Dealing card now. Card dealt: " + deck1.deal());
    System.out.println("Dealing card now. Card dealt: " + deck1.deal());
    System.out.println("Deck size: " + deck1.size());
    System.out.println("Deck: \n" + deck1);
    System.out.println("Rebuilding deck");
    deck1.build();
    System.out.println("Deck: \n" + deck1);
    System.out.println("Reshuffling deck");
    deck1.shuffle();
    System.out.println("Deck: \n" + deck1);
    System.out.println("Trying to remove card 53 now");
    deck1.pick(52);
  }
}
