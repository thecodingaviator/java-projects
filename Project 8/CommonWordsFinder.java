import java.util.ArrayList;

public class CommonWordsFinder {
  public static void main(String[] args) {
    WordCounter2 wc = new WordCounter2("hash");
    ArrayList<String> words = wc.readWords("counttext.txt");
    PQHeap<String> pq = new PQHeap<String>(new AscendingString());
    for(String word : words) {
      pq.add(word);
    }
  }
}