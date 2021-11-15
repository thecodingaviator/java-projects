/*
Name: Parth Parth
Date: 11/10/2021
File: HashMap.java
Section: A
*/

import java.util.ArrayList;
import java.util.Comparator;

public class HashMap<K, V> implements MapSet<K, V> {
  private Comparator<K> comp;
  private int size;
  private Object buckets[];
  private int collisions;
  private int maxSize;

  public HashMap(Comparator<K> incomp) {
    this.comp = incomp;
    this.size=this.collisions=0;
    this.buckets = new Object[16];
    this.maxSize = 16;
  }

  public HashMap(Comparator<K> incomp, int capacity) {
    this.comp = incomp;
    this.size=this.collisions=0;
    this.buckets = new Object[capacity];
    this.maxSize = capacity;
  }

  public V put(K key, V value) {
    V returnValue=null;
    // get index of key
    int index = getIndex(key);
    // if index is empty, create new binary tree, put and return null
    if (this.buckets[index] == null) {
      this.buckets[index] = new BSTMap<K, V>(this.comp);
      ((BSTMap) this.buckets[index]).put(key, value);
    }
    // search for key. if already exists, update and return old value
    else if (((BSTMap) this.buckets[index]).containsKey(key)) {
      V oldValue = (V) ((BSTMap) this.buckets[index]).get(key);
      ((BSTMap) this.buckets[index]).put(key, value);
      returnValue = oldValue;
    }
    else {
      // if not, add to tree and return null
      ((BSTMap) this.buckets[index]).put(key, value);
    }
    this.size++;
    // rehash if necessary
    if(this.size==this.maxSize-1){
      this.rehash();
    }
    return returnValue;
  }

  public V get(K key) {
    // get index of key
    int index = getIndex(key);
    // if index is empty, return null
    if (this.buckets[index] == null) {
      return null;
    }
    // search for key. if found, return value
    else if (((BSTMap) this.buckets[index]).containsKey(key)) {
      return (V) ((BSTMap) this.buckets[index]).get(key);
    }
    else {
      return null;
    }
  }

  private int getIndex(K key) {
    return Math.abs(key.hashCode()) % this.buckets.length;
  }

  @Override
  public boolean containsKey(K key) {
    return this.get(key) != null;
  }

  @Override
  public ArrayList<K> keySet() {
    ArrayList<K> keys = new ArrayList<K>();
    for (KeyValuePair<K, V> kvp : this.entrySet()) {
      keys.add(kvp.key);
    }
    return keys;
  }

  @Override
  public ArrayList<V> values() {
    ArrayList<V> values = new ArrayList<V>();
    for (KeyValuePair<K, V> kvp : this.entrySet()) {
      values.add(kvp.value);
    }
    return values;
  }

  @Override
  public ArrayList<KeyValuePair<K, V>> entrySet() {
    ArrayList<KeyValuePair<K, V>> entries = new ArrayList<KeyValuePair<K, V>>();
    for(int i=0; i<this.buckets.length; i++){
      if(this.buckets[i]!=null){
        entries.addAll(((BSTMap) this.buckets[i]).entrySet());
      }
    }
    return entries;
  }

  @Override
  public int size() {
    return this.size;
  }

  public int uniqueWordCount() {
    int count = 0;
    for(int i=0; i<this.buckets.length; i++){
      if(this.buckets[i]!=null){
        count += ((BSTMap) this.buckets[i]).size();
      }
    }
    return count;
  }

  public void calculateSize() {
    this.size = 0;
    for(int i=0; i<this.buckets.length; i++){
      if(this.buckets[i]!=null){
        this.size += ((BSTMap) this.buckets[i]).size();
      }
    }
  }

  @Override
  public void clear() {
    this.size=this.collisions=0;
    this.maxSize = 16;
    this.buckets = new Object[16];
  }

  public int getCollisions() {
    for(int i=0; i<this.buckets.length; i++){
      if(this.buckets[i]!=null && ((BSTMap) this.buckets[i]).size()>1){
        this.collisions++;
      }
    }
    return this.collisions;
  }
  
  private void rehash() {
    // create a copy of the old buckets
    Object[] oldBuckets = this.buckets;
    // create new buckets with double capacity
    this.buckets = new Object[oldBuckets.length*2];
    this.size=this.collisions=0;
    this.maxSize*=2;
    // rehash all entries
    for(int i=0; i<oldBuckets.length; i++){
      if(oldBuckets[i]!=null){
        BSTMap current = (BSTMap) oldBuckets[i];
        ArrayList<KeyValuePair<K, V>> entries = current.entrySet();
        for(KeyValuePair<K, V> kvp : entries){
          this.put(kvp.key, kvp.value);
        }
      }
    }
  }

  @Override
  public String toString() {
    String s = "";
    s += "HashMap: " + this.size + " entries, " + this.collisions + " collisions\n";
    for(int i=0; i<this.buckets.length; i++){
      if(this.buckets[i]!=null){
        BSTMap current = (BSTMap) this.buckets[i];
        ArrayList<KeyValuePair<K, V>> entries = current.entrySet();
        for(KeyValuePair<K, V> kvp : entries){
          s += kvp.key + ": " + kvp.value + "\n";
        }
      }
    }
    return s;
  }

  public static void main(String[] args) {
    HashMap<String, Integer> map=new HashMap<String, Integer>(new AscendingString());
    System.out.println(map.put("1", 1));
    System.out.println(map.put("1", 2));
    System.out.println(map.get("1"));
    System.out.println(map.containsKey("1"));

    map.clear();
    map.put("1", 1);
    map.put("2", 2);
    map.put("3", 3);
    map.put("4", 4);
    map.put("5", 5);
    map.put("6", 6);
    System.out.println(map.getCollisions());
    map.put("7", 7);
    map.put("8", 8);
    map.put("9", 9);
    map.put("10", 10);
    map.put("11", 11);
    map.put("12", 12);
    map.put("13", 13);
    map.put("14", 14);
    map.put("15", 15);
    map.put("16", 16);
    map.put("17", 17);

    System.out.println(map.getCollisions());
    System.out.println(map.size());
    System.out.println("\n" + map);
  }
}
