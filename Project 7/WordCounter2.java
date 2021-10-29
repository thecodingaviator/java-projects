import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
      map.put(word, 1);
    }
    long endTime = System.nanoTime();
    return (endTime - startTime) / 1000000.0;
  }

  public void clearMap() {
    map.clear();
  }

  public int totalWordCount() {
    return map.size();
  }

  public int uniqueWordCount() {
    return map.uniqueSize();
  }

  
}
