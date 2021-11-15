/*
Name: Parth Parth
Date: 11/15/2021
File: AscendingKVP.java
Section: A
*/

import java.util.Comparator;

public class AscendingKVP implements Comparator<KeyValuePair<String, Integer>> {
  @Override
  public int compare(KeyValuePair<String, Integer> kvp1, KeyValuePair<String, Integer> kvp2) {
    return kvp1.getValue()-kvp2.getValue();
  }
}