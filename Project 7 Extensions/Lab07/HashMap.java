import java.util.ArrayList;
import java.util.Comparator;

public class HashMap<K, V> implements MapSet<K, V> {
  private Comparator<K> comp;
  private int size;
  private ArrayList<KeyValuePair<K, V>>[] buckets;
  private int collisions;

  public HashMap(Comparator<K> comp) {
    this.comp = comp;
    this.size=this.collisions=0;
    this.buckets = new ArrayList[10];
  }

  public HashMap(Comparator<K> comp, int capacity) {
    this.comp = comp;
    this.size=this.collisions=0;
    this.buckets = new ArrayList[capacity];
  }

  public V put(K key, V value) {
    int index = getIndex(key);
    if (buckets[index] == null) {
      buckets[index] = new ArrayList<KeyValuePair<K, V>>();
    }
    for (KeyValuePair<K, V> pair : buckets[index]) {
      if (comp.compare(pair.getKey(), key) == 0) {
        V oldValue = pair.getValue();
        pair.setValue(value);
        return oldValue;
      }
    }
    buckets[index].add(new KeyValuePair<K, V>(key, value));
    size++;
    return null;
  }

  public V get(K key) {
    int index = getIndex(key);
    if (buckets[index] == null) {
      return null;
    }
    for (KeyValuePair<K, V> kvp : buckets[index]) {
      if (comp.compare(kvp.key, key) == 0) {
        return kvp.value;
      }
    }
    return null;
  }

  public int getIndex(K key) {
    return Math.abs(key.hashCode()) % buckets.length;
  }

  public static void main(String[] args) {
    HashMap map=new HashMap<String, Integer>(new AscendingString());
    map.put("1", 1);
    map.put("1", 2);
    System.out.println(map.get(1));
  }

  @Override
  public boolean containsKey(K key) {
    return get(key) != null;
  }

  @Override
  public ArrayList<K> keySet() {
    ArrayList<K> keys = new ArrayList<K>();
    for (ArrayList<KeyValuePair<K, V>> bucket : buckets) {
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
    for (ArrayList<KeyValuePair<K, V>> bucket : buckets) {
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
    for (ArrayList<KeyValuePair<K, V>> bucket : buckets) {
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
}
