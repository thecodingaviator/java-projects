import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class HashMap<K, V> implements MapSet<K, V> {
  private Object[] bucket;
  private Comparator<K> comparator;
  private int collisions;

  public HashMap(Comparator incomp) {
    this.bucket = new LinkedList[16];
    this.comparator = incomp;
    this.collisions = 0;
  }

  public HashMap(Comparator incomp, int capacity) {
    this.bucket = new LinkedList[capacity];
    this.comparator = incomp;
    this.collisions = 0;
  }

  public int getIndex(K key) {
    return Math.abs(key.hashCode()) % bucket.length;
  }

  @Override
  public V put(K new_key, V new_value) {
    int index = getIndex(new_key);

    // if the bucket at index is empty
    if (this.bucket[index] == null) {
      // create a new linked list and add value to it
      this.bucket[index] = new LinkedList<KeyValuePair<K, V>>();
      ((LinkedList) this.bucket[index]).add(new KeyValuePair<K, V>(new_key, new_value));
      return null;
    }
    // else
    else {
      // look for value in linkedlist at bucket
      for (KeyValuePair<K, V> pair : (LinkedList<KeyValuePair<K, V>>) this.bucket[index]) {
        // if key is found, update and return old value
        if (this.comparator.compare(pair.key, new_key) == 0) {
          V old_value = pair.value;
          pair.setValue(new_value);
          return old_value;
        }
      }

      // if value not found, add it to linkedlist
      ((LinkedList) this.bucket[index]).add(new KeyValuePair<K, V>(new_key, new_value));
      return null;
    }
  }

  @Override
  public boolean containsKey(K key) {
    int index = getIndex(key);
    // if bucket at index is empty
    if (this.bucket[index] == null) {
      return false;
    }
    // else
    else {
      // look for value in linkedlist at bucket
      for (KeyValuePair<K, V> pair : (LinkedList<KeyValuePair<K, V>>) this.bucket[index]) {
        // if key is found, return true
        if (this.comparator.compare(pair.key, key) == 0) {
          return true;
        }
      }
      // if value not found, return false
      return false;
    }
  }

  @Override
  public V get(K key) {
    int index = getIndex(key);

    // if bucket at index is empty
    if (this.bucket[index] == null) {
      return null;
    }
    // else
    else {
      // look for value in linkedlist at bucket
      for (KeyValuePair<K, V> pair : (LinkedList<KeyValuePair<K, V>>) this.bucket[index]) {
        // if key is found, return value
        if (this.comparator.compare(pair.key, key) == 0) {
          return pair.value;
        }
      }
      // if value not found, return null
      return null;
    }
  }

  @Override
  public ArrayList<K> keySet() {
    ArrayList<K> keys = new ArrayList<K>();
    // get arraylist of keyvaluepairs
    ArrayList<KeyValuePair<K, V>> pairs = this.entrySet();

    // for each keyvaluepair, add key to arraylist for keys
    for (KeyValuePair<K, V> pair : pairs) {
      keys.add(pair.key);
    }

    return keys;
  }

  @Override
  public ArrayList<V> values() {
    ArrayList<V> values = new ArrayList<V>();
    // get arraylist of keyvaluepairs
    ArrayList<KeyValuePair<K, V>> pairs = this.entrySet();

    // for each keyvaluepair, add value to arraylist for values
    for (KeyValuePair<K, V> pair : pairs) {
      values.add(pair.value);
    }

    return values;
  }

  @Override
  public ArrayList<KeyValuePair<K, V>> entrySet() {
    ArrayList<KeyValuePair<K, V>> entrySet = new ArrayList<KeyValuePair<K, V>>();
    
    // iterate over bucket
    for (int i = 0; i < this.bucket.length; i++) {
      // if a linkedlist is present at index
      if (this.bucket[i] != null) {
        // iterate over it and add all pairs present to arraylist
        for (KeyValuePair<K, V> pair : (LinkedList<KeyValuePair<K, V>>) this.bucket[i]) {
          entrySet.add(pair);
        }
      }
    }
    return entrySet;
  }

  @Override
  public int size() {
    return this.entrySet().size();
  }

  @Override
  public void clear() {
    this.bucket = new LinkedList[16];
    this.collisions = 0;
  }

  public String toString() {
    String result = "";

    // iterate over bucket
    for (int i = 0; i < this.bucket.length; i++) {
      // if a linkedlist is present at index
      if (this.bucket[i] != null) {
        result += "Bucket " + i + ": ";
        // iterate over it and add all pairs present to arraylist
        for (KeyValuePair<K, V> pair : (LinkedList<KeyValuePair<K, V>>) this.bucket[i]) {
          result += pair.toString();
        }
        result+= "\n";
      }
    }

    return result;
  }
}

// TODO: implement colllisions, implement expansion, implement rehashing