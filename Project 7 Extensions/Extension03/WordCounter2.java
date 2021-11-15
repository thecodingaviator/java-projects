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
      BufferedReader br = new BufferedReader(fileReader);
      // get first line
      String line = br.readLine();
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
        line = br.readLine();
      }
      br.close();
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
    if(this.map instanceof HashMap) {
      return ((HashMap) this.map).uniqueWordCount();
    }
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
      BufferedReader br = new BufferedReader(fileReader);
      
      String line = br.readLine();
      String[] words = line.split(" ");
      while (line != null) {
        words = line.split(" ");
        this.map.put(words[0], Integer.parseInt(words[1]));
        line = br.readLine();
      }
      br.close();
      if(this.map.getClass().getName().equals("HashMap")) {
        ((HashMap)this.map).calculateSize();
      }
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
      double times[] = new double[5];
      double robust=0.0;

      for(int i = 0; i < 5; i++) {
        times[i] = wc.buildMap(words);
      }

      // sort times
      for(int i = 0; i < 4; i++) {
        for(int j = i + 1; j < 5; j++) {
          if(times[i] > times[j]) {
            double temp = times[i];
            times[i] = times[j];
            times[j] = temp;
          }
        }
      }

      // calculate robust average
      for(int i = 1; i < 4; i++) {
        robust += times[i];
      }
      robust /= 3.0;

      System.out.println(filename + " took " + robust + " ms to build");
      System.out.println("Total word count: " + wc.totalWordCount());
      System.out.println("Unique word count: " + wc.uniqueWordCount());
      System.out.println("Collisions: " + ((HashMap) wc.map).getCollisions());
      wc.writeWordCount(filename.substring(0, filename.length() - 4) + "_analyzed.txt");
    }
  }
}
