/*
Name: Parth Parth
Date: 11/15/2021
File: CommonWordsFinder.java
Section: A
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CommonWordsFinder {

  private PQHeap<KeyValuePair<String, Integer>> heap;
  private int numWords;
  private final int N;

  CommonWordsFinder(int N) {
    this.N = N;
    this.heap = new PQHeap<KeyValuePair<String, Integer>>(new AscendingKVP());
  }

  private void readFile(String fileName) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      String line = br.readLine();
      if (line.startsWith("totalWordCount")) {
        String words[] = line.split(" ");
        this.numWords = Integer.parseInt(words[1]);
        line = br.readLine();
      }
      while (line != null) {
        String[] words = line.split(" ");
        this.heap.add(new KeyValuePair<String, Integer>(words[0], Integer.parseInt(words[1])));
        line = br.readLine();
      }
      br.close();
    } catch (IOException e) {
      System.out.println("Error reading file");
    }
  }

  private void analyze() {
    for(int i = 0; i < this.N; i++) {
      KeyValuePair<String, Integer> kvp = this.heap.remove();
      double frequency = kvp.getValue() / (this.numWords * 1.0);
      System.out.println(kvp.getKey() + "\t|\t" + frequency);
    }
    System.out.println();
  }

  public static void main(String[] args) {
    // args = "10 reddit_comments_analyzed_2008.txt reddit_comments_analyzed_2009.txt reddit_comments_analyzed_2010.txt reddit_comments_analyzed_2011.txt reddit_comments_analyzed_2012.txt reddit_comments_analyzed_2013.txt reddit_comments_analyzed_2014.txt reddit_comments_analyzed_2015.txt".split(" ");
    int numWords = Integer.parseInt(args[0]);
    for (int i=1; i<args.length; i++) {
      System.out.println("Analyzing " + args[i]);
      CommonWordsFinder finder = new CommonWordsFinder(numWords);
      finder.readFile(args[i]);
      finder.analyze();
    }
  }
}