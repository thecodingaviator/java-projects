/*
Name: Parth Parth
Date: 11/01/2021
File: AscendingString.java
Section: A
*/

import java.util.Comparator;

public class AscendingString implements Comparator<String> {
  @Override
  public int compare(String s1, String s2) {
    //return s1.compareTo(s2);
    return Integer.parseInt(s1)-Integer.parseInt(s2);
  }
}