/*
Name: Parth Parth
Date: 11/10/2021
File: WordCounter2.java
Section: A
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WordCounter2 {
  MapSet<String, Integer> map;

  public WordCounter2(String data_structure) {
    if(data_structure.equals("bst")) {
      this.map = new BSTMap<String, Integer>(new AscendingString());
    }
    else {
      this.map = new HashMap<String, Integer>(new AscendingString());
    }
  }

  public ArrayList<String> readWords(String filename) {
    ArrayList<String> words = new ArrayList<String>();

    try {
      FileReader fileReader = new FileReader(filename);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      // get first line
      String line = bufferedReader.readLine();
      // while lines remain
      while (line != null) {
        // split line into words
        String[] tokens = line.split("[^a-zA-Z0-9']");
        // for each each word, make it to lowercase and add to arraylist
        for (String word : tokens) {
          if(word.length() > 0) {
            words.add(word.toLowerCase());
          }
        }
        // read next line
        line = bufferedReader.readLine();
      }
      bufferedReader.close();
    } catch (FileNotFoundException ex) {
      System.out.println("Unable to open file '" + filename + "'");
    } catch (IOException ex) {
      System.out.println("Error reading file '" + filename + "'");
    }
    return words;
  }

  public double buildMap(ArrayList<String> words) {
    this.map.clear();
    long startTime = System.nanoTime();
    for (String word : words) {
      int count = this.map.get(word) != null ? this.map.get(word) + 1 : 1;
      this.map.put(word, count);
    }
    long endTime = System.nanoTime();
    return (endTime - startTime) / 1000000.0;
  }

  public void clearMap() {
    this.map.clear();
  }

  public int totalWordCount() {
    int size = 0;
    ArrayList<KeyValuePair<String, Integer>> list = this.map.entrySet();
    for(KeyValuePair<String, Integer> pair : list) {
      size += pair.getValue();
    }
    return size;
  }

  public int uniqueWordCount() {
    return this.map.size();
  }

  public int getCount(String word) {
    return this.map.get(word) != null ? this.map.get(word) : 0;
  }

  public double getFrequency(String word) {
    if(getCount(word) == 0) {
      return 0;
    }
    return (double)this.getCount(word)/this.totalWordCount();
  }

  public boolean writeWordCount(String filename) {
    try {
      FileWriter fileWriter = new FileWriter(filename);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      ArrayList<KeyValuePair<String, Integer>> list = this.map.entrySet();
      bufferedWriter.write("totalWordCount: " + this.totalWordCount() + "\n");
      for(KeyValuePair<String, Integer> pair : list) {
        bufferedWriter.write(pair.getKey() + " " + pair.getValue() + "\n");
      }
      bufferedWriter.close();
      return true;
    } catch (IOException ex) {
      System.out.println("Error writing to file '" + filename + "'");
      return false;
    }
  }

  public boolean readWordCount(String filename) {
    this.map.clear();
    try {
      FileReader fileReader = new FileReader(filename);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line = bufferedReader.readLine();
      while (line != null) {
        String[] tokens = line.split("[^a-zA-Z]+");
        for (String token : tokens) {
          int count = this.map.get(token) != null ? this.map.get(token) + 1 : 1;
          map.put(token, count);
        }
        line = bufferedReader.readLine();
      }
      bufferedReader.close();
      return true;
    } catch (FileNotFoundException ex) {
      System.out.println("Unable to open file '" + filename + "'");
      return false;
    } catch (IOException ex) {
      System.out.println("Error reading file '" + filename + "'");
      return false;
    }
  }

  public static void main(String[] args) {
    WordCounter2 wc = new WordCounter2("hash");
    for(String filename: args) {
      ArrayList<String> words = wc.readWords(filename);
      for(int i = 0; i < 5; i++) {
        System.out.println(filename + " took: " + wc.buildMap(words) + "ms");
      }
      System.out.println("Total word count: " + wc.totalWordCount());
      System.out.println("Unique word count: " + wc.uniqueWordCount());
      System.out.println("Collisions: " + ((HashMap) wc.map).getCollisions());
      wc.writeWordCount(filename.substring(0, filename.length() - 4) + "_analyzed.txt");  
    }
  }
}
