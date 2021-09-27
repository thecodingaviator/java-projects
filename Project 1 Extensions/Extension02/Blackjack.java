/*
Name: Parth Parth
Date: 09/18/2021
File: Blackjack.java
Section: A
*/

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
    while(this.dealerHand.getTotalValue()<17)
    this.dealerHand.add(deck.deal());
    return !(this.dealerHand.getTotalValue()>21);
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

  public static void main(String[] args) {
    Blackjack gameMain=new Blackjack(26);
    for(int i=1; i<4; i++)
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
    // if player loses
    if(!this.playerTurn())
      result=-1;
    // if dealer loses
    else if(!this.dealerTurn())
      result=1;
    // if no one lost
    else
    {
      // calculate result using total of hand
      int playerTotal = this.playerHand.getTotalValue();
      int dealerTotal = this.dealerHand.getTotalValue();
      if(playerTotal==dealerTotal)
        result=0;
      else if(playerTotal>dealerTotal)
        result=1;
      else result=-1;
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
