/*
Name: Parth Parth
Date: 11/01/2021
File: KeyValuePair.java
Section: A
*/

public class KeyValuePair<Key, Value> {
  Key key;
  Value value;

  public KeyValuePair(Key k, Value v) {
    this.key=k;
    this.value=v;
  }

  public Key getKey() {
    return this.key;
  }

  public Value getValue() {
    return this.value;
  }

  public void setKey(Key k) {
    this.key=k;
  }

  public void setValue(Value v) {
    this.value=v;
  }

  public String toString() {
    return "Key: " + this.getKey() + " Value: " + this.getValue();
  }

  public static void main(String[] args) {
    KeyValuePair<String, Integer> kvp=new KeyValuePair<String, Integer>("String", 7);
    System.out.println(kvp);
    kvp.setValue(11);
    System.out.println(kvp);
  }
}