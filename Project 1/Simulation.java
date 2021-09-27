/*
Name: Parth Parth
Date: 09/18/2021
File: Simulation.java
Section: A
*/

public class Simulation {
  public static void main(String[] args) {
    Blackjack game=new Blackjack(26);
    int player=0, dealer=0, push=0;
    // So that we can play more games if needed
    int numOfGames=1000;
    
    // Simulate numOfGames games and get results
    for(int i=1; i<=numOfGames; i++)
    {
      // Get result of 1 game
      int result=game.game(false);
      // Increment 1 in counters according to the results
      if(result==0)
        push++;
      if(result==1)
        player++;
      if(result==-1)
        dealer++;
    }

    // Print results in tabular form
    System.out.println("\tGames\tPercentage");
    System.out.println("Player\t"+player+"\t"+player*100.0/numOfGames+"%");
    System.out.println("Dealer\t"+dealer+"\t"+dealer*100.0/numOfGames+"%");
    System.out.println("Push\t"+push+"\t"+push*100.0/numOfGames+"%");
  }
}
