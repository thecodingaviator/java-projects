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
      map = new BSTMap<String, Integer>(new AscendingString());
    }
    else {
      map = new HashMap<String, Integer>(new AscendingString());
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
        String[] tokens = line.split("[^a-zA-Z]+");
        // for each each word, make it to lowercase and add to arraylist
        for (String token : tokens) {
          words.add(token.toLowerCase());
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
    long startTime = System.nanoTime();
    for (String word : words) {
      int count=map.get(word)!=null?map.get(word)+1:1;
      map.put(word, count);
    }
    long endTime = System.nanoTime();
    return (endTime - startTime) / 1000000.0;
  }

  public void clearMap() {
    map.clear();
  }

  public int totalWordCount() {
    int size=0;
    ArrayList<KeyValuePair<String, Integer>> list = map.entrySet();
    for(KeyValuePair<String, Integer> pair : list) {
      size += pair.getValue();
    }
    return size;
  }

  public int uniqueWordCount() {
    return map.size();
  }

  public int getCount(String word) {
    return map.get(word)!=null?map.get(word):0;
  }

  public double getFrequency(String word) {
    if(getCount(word)==0) {
      return 0;
    }
    return (double)getCount(word)/totalWordCount();
  }

  public boolean writeWordCount(String filename) {
    try {
      FileWriter fileWriter = new FileWriter(filename);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      ArrayList<KeyValuePair<String, Integer>> list = map.entrySet();
      bufferedWriter.write("totalWordCount: " + this.totalWordCount() + "\n");
      for(KeyValuePair<String, Integer> pair : list) {
        bufferedWriter.write(pair+"\n");
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
          int count=map.get(token)!=null?map.get(token)+1:1;
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
    WordCounter2 wc=new WordCounter2("hash");
    ArrayList<String> words=wc.readWords("counttest.txt");
    System.out.println("Time to build map: " + wc.buildMap(words) + " ms"); 
    System.out.println("Total word count: " + wc.totalWordCount());
    System.out.println("Unique word count: " + wc.uniqueWordCount());
    wc.writeWordCount("written.txt");  
  }
}
