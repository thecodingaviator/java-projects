/*
Name: Parth Parth
Date: 11/15/2021
File: CommonWordsFinderArrayList.java
Section: A
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class CommonWordsFinderArrayList {
  
  private ArrayList<KeyValuePair<String, Integer>> list;
  private int numWords;
  private final int N;

  CommonWordsFinderArrayList(int N) {
    this.N = N;
    this.list = new ArrayList<KeyValuePair<String, Integer>>();
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
        this.list.add(new KeyValuePair<String, Integer>(words[0], Integer.parseInt(words[1])));
        line = br.readLine();
      }
      br.close();
    } catch (IOException e) {
      System.out.println("Error reading file");
    }
  }

  private void analyze() {
    // sort list
    this.list.sort(new AscendingKVPCustom());
    // print top N
    for(int i = 0; i < this.N; i++) {
      KeyValuePair<String, Integer> kvp = this.list.remove(0);
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
      CommonWordsFinderArrayList finder = new CommonWordsFinderArrayList(numWords);
      finder.readFile(args[i]);
      long one = System.nanoTime();
      finder.analyze();
      long two = System.nanoTime() - one;
      double time = two / 1000000000.0;
      System.out.println("Time for " + args[i] + ": " + time + "ms");
    }
  }
}

// rewriting the custom AscendingKVP class because that sorts list in descending order
class AscendingKVPCustom implements Comparator<KeyValuePair<String, Integer>> {
  @Override
  public int compare(KeyValuePair<String, Integer> kvp1, KeyValuePair<String, Integer> kvp2) {
    return -1*(kvp1.getValue()-kvp2.getValue());
  }
}