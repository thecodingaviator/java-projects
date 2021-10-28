/*
Name: Parth Parth
Date: 11/02/2021
File: WordCounter.java
Section: A
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WordCounter {
  BSTMap<String, Integer> map;
  int wordCount;

  public WordCounter() {
    this.map = new BSTMap<String, Integer>(new AscendingString());
    this.wordCount = 0;
  }

  public void analyze(String filename) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      String line = br.readLine();
      while(line != null) {
        String[] words = line.split("[^a-zA-Z0-9']");
        for (String word : words) {
          if(word.length()>0) {
            word = word.toLowerCase();
            if(this.map.containsKey(word)) {
              this.map.put(word, this.map.get(word) + 1);
            } else {
              this.map.put(word, 1);
            }
            this.wordCount++;
          }
        }
        line = br.readLine();
      }
      br.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    } catch (IOException e) {
      System.out.println("IOException");
    }
  }

  public int getTotalWordCount() {
    return this.wordCount;
  }

  public int getUniqueWordCount() {
    return this.map.size();
  }

  public int getCount(String word) {
    if (this.map.containsKey(word)) {
      return this.map.get(word);
    } else {
      return 0;
    }
  }

  public double getFrequency(String word) {
    if (this.map.containsKey(word)) {
      return (double) this.map.get(word) / this.wordCount;
    } else {
      return 0;
    }
  }

  public String inorder() {
    return this.map.preorder_driver();
  }

  public void writeWordCountFile(String filename) {
    ArrayList<KeyValuePair<String, Integer>> list=this.map.entrySet();
    try {
      FileWriter fw=new FileWriter(filename);
      fw.write("totalWordCount: "+this.wordCount+"\n");
      for(KeyValuePair<String, Integer> pair:list) {
        fw.write(pair.getKey()+" "+pair.getValue()+"\n");
      }
      fw.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
  }

  public void readWordCountFile(String filename) {
    this.map.clear();
    this.wordCount = 0;

    try {
      File file = new File(filename);
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line = br.readLine();
      while(line != null) {
        String[] words = line.split(" ");
        if(words[0].equals("totalWordCount:")) {
          this.wordCount = Integer.parseInt(words[1]);
        } else {
          this.map.put(words[0], Integer.parseInt(words[1]));
        }
        line = br.readLine();
      }
      br.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    } catch (IOException e) {
      System.out.println("IOException");
    }
  }

  public static void main(String ar[]) {
    WordCounter wc = new WordCounter();
    wc.analyze("counttest.txt");
    System.out.println(wc.inorder());
    wc.writeWordCountFile("counts_ct.txt");
    wc.readWordCountFile("counts_ct.txt");
    wc.writeWordCountFile("counts_ct_v2.txt");
  }
}