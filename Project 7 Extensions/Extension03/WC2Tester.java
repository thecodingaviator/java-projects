import java.util.ArrayList;

public class WC2Tester {
  public static void main(String[] args) {
    WordCounter2 wc = new WordCounter2("hash");
    String filename = "counttest.txt";
    ArrayList<String> words = wc.readWords(filename);
    double times[] = new double[5];
    double robust = 0.0;

    for (int i = 0; i < 5; i++) {
      times[i] = wc.buildMap(words);
    }

    // sort times
    for (int i = 0; i < 4; i++) {
      for (int j = i + 1; j < 5; j++) {
        if (times[i] > times[j]) {
          double temp = times[i];
          times[i] = times[j];
          times[j] = temp;
        }
      }
    }

    // calculate robust average
    for (int i = 1; i < 4; i++) {
      robust += times[i];
    }
    robust /= 3.0;

    System.out.println(filename + " took " + robust + " ms to build");
    System.out.println("Total word count: " + wc.totalWordCount());
    System.out.println("Unique word count: " + wc.uniqueWordCount());
    System.out.println("Collisions: " + ((HashMap) wc.map).getCollisions());
    wc.writeWordCount(filename.substring(0, filename.length() - 4) + "_analyzed.txt");
    wc.readWordCount(filename.substring(0, filename.length() - 4) + "_analyzed.txt");
    wc.writeWordCount(filename.substring(0, filename.length() - 4) + "_2_analyzed.txt");
  }
}
