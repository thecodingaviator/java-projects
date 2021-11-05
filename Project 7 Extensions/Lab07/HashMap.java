import java.util.ArrayList;
import java.util.Comparator;

public class HashMap<K, V> implements MapSet<K, V> {
  private Comparator<K> comp;
  private int size;
  private ArrayList<KeyValuePair<K, V>>[] buckets;
  private int collisions;
  private int maxSize;

  public HashMap(Comparator<K> incomp) {
    this.comp = incomp;
    this.size=this.collisions=0;
    this.buckets = new ArrayList[16];
    this.maxSize=16;
  }

  public HashMap(Comparator<K> incomp, int capacity) {
    this.comp = incomp;
    this.size=this.collisions=0;
    this.buckets = new ArrayList[capacity];
    this.maxSize=capacity;
  }

  public V put(K key, V value) {
    int index = getIndex(key);
    if (this.buckets[index] == null) {
      this.buckets[index] = new ArrayList<KeyValuePair<K, V>>();
    }
    for (KeyValuePair<K, V> pair : this.buckets[index]) {
      if (this.comp.compare(pair.getKey(), key) == 0) {
        V oldValue = pair.getValue();
        pair.setValue(value);
        return oldValue;
      }
    }
    this.buckets[index].add(new KeyValuePair<K, V>(key, value));
    this.size++;
    if(this.size>=this.maxSize*0.75){
      this.rehash();
    }
    else {
      this.getCollisions();
    }
    return null;
  }

  public V get(K key) {
    int index = getIndex(key);
    if (this.buckets[index] == null) {
      return null;
    }
    for (KeyValuePair<K, V> kvp : this.buckets[index]) {
      if (this.comp.compare(kvp.key, key) == 0) {
        return kvp.value;
      }
    }
    return null;
  }

  private int getIndex(K key) {
    return Math.abs(key.hashCode()) % this.buckets.length;
  }

  @Override
  public boolean containsKey(K key) {
    return get(key) != null;
  }

  @Override
  public ArrayList<K> keySet() {
    ArrayList<K> keys = new ArrayList<K>();
    for (ArrayList<KeyValuePair<K, V>> bucket : this.buckets) {
      if (bucket != null) {
        for (KeyValuePair<K, V> kvp : bucket) {
          keys.add(kvp.key);
        }
      }
    }
    return keys;
  }

  @Override
  public ArrayList<V> values() {
    ArrayList<V> values = new ArrayList<V>();
    for (ArrayList<KeyValuePair<K, V>> bucket : this.buckets) {
      if (bucket != null) {
        for (KeyValuePair<K, V> kvp : bucket) {
          values.add(kvp.value);
        }
      }
    }
    return values;
  }

  @Override
  public ArrayList<KeyValuePair<K, V>> entrySet() {
    ArrayList<KeyValuePair<K, V>> entries = new ArrayList<KeyValuePair<K, V>>();
    for (ArrayList<KeyValuePair<K, V>> bucket : this.buckets) {
      if (bucket != null) {
        for (KeyValuePair<K, V> kvp : bucket) {
          entries.add(kvp);
        }
      }
    }
    return entries;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public void clear() {
    this.size=this.collisions=0;
    this.buckets = new ArrayList[10];
  }

  public int getCollisions() {
    for(ArrayList<KeyValuePair<K, V>> bucket : this.buckets) {
      if(bucket.size()>1) {
        this.collisions++;
      }
    }
    return this.collisions;
  }
  
  public void rehash() {
    ArrayList<KeyValuePair<K, V>>[] oldBuckets = this.buckets;
    this.buckets = new ArrayList[oldBuckets.length*2];
    this.size=this.collisions=0;
    for(ArrayList<KeyValuePair<K, V>> bucket : oldBuckets) {
      if(bucket != null) {
        for(KeyValuePair<K, V> kvp : bucket) {
          this.put(kvp.key, kvp.value);
        }
      }
    }
    this.maxSize*=2;
    this.getCollisions();
  }

  public static void main(String[] args) {
    HashMap map=new HashMap<String, Integer>(new AscendingString());
    System.out.println(map.put("1", 1));
    System.out.println(map.put("1", 2));
    System.out.println(map.get("1"));
    System.out.println(map.containsKey("1"));
  }
}
