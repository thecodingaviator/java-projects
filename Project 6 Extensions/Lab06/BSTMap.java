/*
Parth Parth

Template for the BSTMap classes
Fall 2021
CS 231 Project 6
*/

import java.util.ArrayList;
import java.util.Comparator;

public class BSTMap<K, V> implements MapSet<K, V> {

  private TNode root;
  private Comparator<K> comp;
  private ArrayList<KeyValuePair<K, V>> nodes;

  public BSTMap(Comparator<K> comp) {
    this.comp = comp;
    this.root = null;
    this.nodes = new ArrayList<KeyValuePair<K, V>>();
  }

  public V put(K key, V value) {
    if (root == null) {
      root = new TNode(key, value);
      nodes.add(new KeyValuePair<K, V>(key, value));
      return null;
    } else {
      return this.root.put(key, value, this.comp);
    }
  }

  public V get(K key) {
    return this.root.get(key, comp);
  }

  private class TNode {
    TNode left, right;
    KeyValuePair<K, V> data;

    public TNode(K k, V v) {
      this.data = new KeyValuePair<K, V>(k, v);
      this.left = this.right = null;
    }

    public V put(K key, V value, Comparator<K> comp) {
      if (this.get(key, comp) == null) {
        this.insert(key, value, comp);
        return null;
      } else {
        TNode temp = this.find(key, comp);
        V old = temp.data.getValue();
        temp.data.setValue(value);
        return old;
      }
    }

    private TNode find(K key, Comparator<K> comp) {
      if (comp.compare(key, this.data.getKey()) == 0) {
        return this;
      } else if (comp.compare(key, this.data.getKey()) < 0) {
        if (this.left == null) {
          return null;
        } else {
          return this.left.find(key, comp);
        }
      } else {
        if (this.right == null) {
          return null;
        } else {
          return this.right.find(key, comp);
        }
      }
    }

    private TNode insert(K key, V value, Comparator<K> comp) {
      if (this.data.getKey() == key) {
        this.data.setValue(value);
        return this;
      } else if (comp.compare(key, this.data.getKey()) < 0) {
        if (this.left == null) {
          this.left = new TNode(key, value);
        } else {
          this.left.insert(key, value, comp);
        }
      } else {
        if (this.right == null) {
          this.right = new TNode(key, value);
        } else {
          this.right.insert(key, value, comp);
        }
      }
      return this;
    }

    public V get(K key, Comparator<K> comp) {
      if (comp.compare(this.data.getKey(), key) == 0) {
        return this.data.getValue();
      } else if (comp.compare(this.data.getKey(), key) > 0) {
        if (this.left == null) {
          return null;
        } else {
          return this.left.get(key, comp);
        }
      } else {
        if (this.right == null) {
          return null;
        } else {
          return this.right.get(key, comp);
        }
      }
    }

    public String toString() {
      return this.data.toString();
    }
  }

  @Override
  public boolean containsKey(K key) {
    return this.get(key) != null;
  }

  @Override
  public ArrayList<K> keySet() {
    this.entrySet();
    ArrayList<K> keys = new ArrayList<K>();
    for (KeyValuePair<K, V> kvp : this.nodes) {
      keys.add(kvp.getKey());
    }
    return keys;
  }

  @Override
  public ArrayList<V> values() {
    this.entrySet();
    ArrayList<V> values = new ArrayList<V>();
    for (KeyValuePair<K, V> kvp : this.nodes) {
      values.add(kvp.getValue());
    }
    return values;
  }

  @Override
  public ArrayList<KeyValuePair<K, V>> entrySet() {
    traverse(this.root);
    return this.nodes;
  }

  private void traverse(TNode node) {
    if (node == null) {
      return;
    }
    traverse(node.left);
    nodes.add(node.data);
    traverse(node.right);
  }

  @Override
  public int size() {
    this.entrySet();
    return this.nodes.size();
  }

  @Override
  public void clear() {
    this.root = null;
  }

  public static void main(String[] argv) {
    BSTMap<String, Integer> bst = new BSTMap<String, Integer>(new AscendingString());

    bst.put("20", 20);
    bst.put("10", 10);
    bst.put("11", 11);
    bst.put("5", 5);
    bst.put("6", 6);
    bst.put("6", 7);
    bst.put("9", 9);

    System.out.println(bst.get("15"));
  }
}