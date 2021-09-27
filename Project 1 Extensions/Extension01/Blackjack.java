/*
Name: Parth Parth
Date: 09/18/2021
File: Blackjack.java
Section: A
*/

import java.util.Scanner;

public class Blackjack {
  private Deck deck;
  private Hand playerHand;
  private Hand dealerHand;
  private int minCards;

  // deafault constructor in case user doesn't pass a cutoff
  public Blackjack() {
    setReshufflecutoff(26);
    this.deck=new Deck();
    this.deck.shuffle();
    reset();
  }

  public Blackjack(int reshuffleCutoff) {
    setReshufflecutoff(reshuffleCutoff);
    this.deck=new Deck();
    this.deck.shuffle();
    reset();
  }

  public void reset() {
    playerHand=new Hand();
    dealerHand=new Hand();
    if(this.deck.size()<minCards) {
      this.deck.build();
      this.deck.shuffle();
    }
  }

  public void deal() {
    this.playerHand.add(deck.deal());
    this.dealerHand.add(deck.deal());
  }

  public boolean playerTurn() {
    while(this.playerHand.getTotalValue()<16)
      this.playerHand.add(deck.deal());
    return !(this.playerHand.getTotalValue()>21);
  }

  public boolean dealerTurn() {
    while(this.dealerHand.getTotalValue()<21)
      this.dealerHand.add(deck.deal());
    if(this.dealerHand.getTotalValue()==21)
      return true;
    if(this.dealerHand.getTotalValue()>21)
      return false;
    return false;
  }

  public void setReshufflecutoff(int cutoff) {
    this.minCards = cutoff;
  }

  public int gerReshuffleCutoff() {
    return this.minCards;
  }

  public String toString() {
    String summary="Player's hand: \n" + playerHand;
    summary += "\nTotal value of player's hand: " + playerHand.getTotalValue();
    summary += "\nDealer's hand: \n" + dealerHand;
    summary += "\nTotal value of dealer's hand: " + dealerHand.getTotalValue();
    return summary;
  }


  // EXTENSION: turn interactive
  public boolean playerTurnInteractive() {
    boolean nextGame=true;
    // System.out.println("After each turn, enter true to hit or false to stand");
    Scanner input=new Scanner(System.in);
    System.out.println("Value of player hand: " + this.playerHand.getTotalValue());
    do {
      System.out.println("\nEnter true to hit or false to stand");
      nextGame=input.nextBoolean();
      if(nextGame)
        this.playerHand.add(deck.deal());
      System.out.println("Value of player hand: " + this.playerHand.getTotalValue());
      if(this.playerHand.getTotalValue()==21)
        return true;
      if(this.playerHand.getTotalValue()>21)
        return false;
    } while(nextGame);
    return false;
  }

  public static void main(String[] args) {
    Blackjack gameMain=new Blackjack(26);
    for(int i=1; i<3; i++)
      gameMain.game(true);
  }

  public int game(boolean verbose) {

    // functionality dealing with setting the game up
    this.reset();
    this.deal();
    this.deal();
    if(verbose) {
      System.out.println("Initial hands:\n");
      System.out.println("Player hand:\n" + this.playerHand);
      System.out.println("Dealer hand:\n" + this.dealerHand);
    }

    // functionality dealing with gameplay and result calculation
    int result=0;
    // if player wins
    if(this.playerTurnInteractive())
      result=1;
    // else if dealer wins
    else if(this.dealerTurn())
      result=-1;
    // else
    else {
      // if player went bust
      if(this.playerHand.getTotalValue()>21)
        result=-1;
      // if dealer went bust
      else if(this.dealerHand.getTotalValue()>21)
        result=1;
      // if no one went bust. calculate result by value of hand
      else {
        int playerTotal = this.playerHand.getTotalValue();
        int dealerTotal = this.dealerHand.getTotalValue();
        if(playerTotal==dealerTotal)
          result=0;
        else if(playerTotal>dealerTotal)
          result=1;
        else result=-1;
      }
    }

    //functionality dealing with printing result
    if(verbose) {
      System.out.println("\n---------------\n\nFinal hands:\n");
      System.out.println("Player hand:\n" + this.playerHand);
      System.out.println("Dealer hand:\n" + this.dealerHand);
      if(result==1)
        System.out.println("\nPlayer wins\n");
      else if(result==0)
        System.out.println("Push");
      else System.out.println("Dealer wins\n");
    }
    return result;
  }
}
