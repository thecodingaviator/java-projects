/*
Name: Parth Parth
Date: 11/02/2021
File: WCTester.java
Section: A
*/

public class WCTester {
  public static void main(String[] args) {
    WordCounter wc = new WordCounter();
    wc.analyze("counttest.txt");
    System.out.println(wc.preorder());
    wc.writeWordCountFile("counts_ct.txt");
    wc.readWordCountFile("counts_ct.txt");
    wc.writeWordCountFile("counts_ct_v2.txt");
  }
}