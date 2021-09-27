/*
Name: Parth Parth
Date: 09/27/2021
File: Board.java
Section: A
*/

import java.io.*;

public class Board {
  public boolean read(String filename) {
    try {
      // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
      // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
      FileReader fr=new FileReader(filename);
      BufferedReader br=new BufferedReader(fr);

      // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
      String line=br.readLine();

      // start a while loop that loops while line isn't null
      while(line!=null) {
        // assign to an array of type String the result of calling split on the line with the argument "[ ]+"
        // print the String (line)
        // print the size of the String array (you can use .length)
        // assign to line the result of calling the readLine method of your BufferedReader object.
        String[] split=line.split("[ ]+");
        System.out.println("Line: " + line);
        System.out.println("Size of array: " + split.length);
        line=br.readLine();
      }

      // call the close method of the BufferedReader
      // return true
      br.close();
      return true;
    }
    catch(FileNotFoundException ex) {
      System.out.println("Board.read():: unable to open file " + filename );
    }
    catch(IOException ex) {
      System.out.println("Board.read():: error reading file " + filename);
    }

    return false;
  }

  public static void main(String[] args) {
    Board reader=new Board();
    reader.read("test2.txt");
  }
}
