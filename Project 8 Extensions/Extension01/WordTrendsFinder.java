/*
Name: Parth Parth
Date: 11/15/2021
File: WordTrendsFinder.java
Section: A
*/

public class WordTrendsFinder {
  public static void main(String[] args) {
    String baseFile = args[0];
    int fileNumberBegin = Integer.parseInt(args[1]);
    int fileNumberEnd = Integer.parseInt(args[2]);
    String words[] = new String[args.length - 3];
    for (int i = 3; i < args.length; i++) {
      words[i - 3] = args[i];
    }
    for(String word : words) {
      System.out.print(word + ",");
      for(int i = fileNumberBegin; i <= fileNumberEnd; i++) {
        String fileName = baseFile + i + ".txt";
        WordCounter wc = new WordCounter();
        wc.readWordCountFile(fileName);
        System.out.print(wc.getFrequency(word) + ",");
      }
      System.out.println();
    }
  }
}
