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

  // memory freed when WordCounter object is destroyed and garbage collected
  BSTMap<String, Integer> map;
  int wordCount;

  public WordCounter() {
    this.map = new BSTMap<String, Integer>(new AscendingString());
    this.wordCount = 0;
  }

  public void analyze(String filename) {
    try {
      // cleap map
      this.map.clear();
      this.wordCount = 0;
      BufferedReader br = new BufferedReader(new FileReader(filename)); // memory freed when br is destroyed and garbage collected
      // read first line
      String line = br.readLine();
      // while lines remain
      while(line != null) {
        // split word
        String[] words = line.split("[^a-zA-Z0-9']"); // memory freed when the while block ends
        // for each word
        for (String word : words) {
          // if word is not empty
          if(word.length()>0) {
            word = word.toLowerCase();
            // if word is present, update count by 1
            if(this.map.containsKey(word)) {
              this.map.put(word, this.map.get(word) + 1);
            }
            // else put it on the map
            else {
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
    }
    else {
      return 0;
    }
  }

  public double getFrequency(String word) {
    if (this.map.containsKey(word)) {
      return (double) this.map.get(word) / this.wordCount;
    }
    else {
      return 0;
    }
  }

  public String preorder() {
    return this.map.preorder_driver();
  }

  public void writeWordCountFile(String filename) {
    // get words in preorder
    ArrayList<KeyValuePair<String, Integer>> list=this.map.entrySet(); // memory freed when method ends
    try {
      FileWriter fw=new FileWriter(filename); // memory freed when fw is destroyed and garbage collected (when method ends)
      fw.write("totalWordCount: "+this.wordCount+"\n");
      // write word, frequency to file
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
      File file = new File(filename); // memory freed when file is destroyed and garbage collected
      BufferedReader br = new BufferedReader(new FileReader(file)); // memory freed when br is destroyed and garbage collected
      // get first line
      String line = br.readLine();
      // while lines remain
      while(line != null) {
        // split word
        String[] words = line.split(" "); // memory freed when the while block ends
        // if first line is totalWordCount, use it to update total word count
        if(words[0].equals("totalWordCount:")) {
          this.wordCount = Integer.parseInt(words[1]);
        }
        // read word and frequency, adding it to map
        else {
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
    for(String filename: ar) {
      long time1=System.currentTimeMillis();
      wc.analyze(filename);
      long time2=System.currentTimeMillis();
      System.out.println(filename + " took: " + (time2-time1) + "ms");
      System.out.println("Total word count for this file is: " + wc.getTotalWordCount());
      System.out.println("Unique word count for this file is: " + wc.getUniqueWordCount());
      System.out.println("Depth of tree for this file is: " + wc.map.depth());
      wc.writeWordCountFile(filename.substring(0, filename.length()-4)+"_analyzed.txt");
    }
  }
}