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
    this.comp=comp;
    this.root=null;
    this.nodes=new ArrayList<KeyValuePair<K, V>>();
  }

  public V put(K key, V value) {
    // if root is null, create new node
    if(root==null) {
      root=new TNode(key, value);
      nodes.add(new KeyValuePair<K, V>(key, value));
      return null;
    }
    // else put in the node
    else {
      return this.root.put(key, value, this.comp);
    }
  }

  public int size(TNode root) {
    if(root==null) {
      return 0;
    }
    else {
      return 1+size(root.left)+size(root.right);
    }
  }

  public V get(K key) {
    if(root==null) {
      return null;
    }
    else {
      return this.root.get(key, this.comp);
    }
  }

  private class TNode {
    TNode left, right;
    KeyValuePair<K, V> data;

    public TNode(K k, V v) {
      this.data=new KeyValuePair<K, V>(k, v);
      this.left=this.right=null;
    }

    public V put(K key, V value, Comparator<K> comp) {
      // if nothing is found, insert new node
      if(this.get(key, comp)==null) {
        this.insert(key, value, comp);
        return null;
      }
      else {
        // else replace the value and return old value
        TNode temp=this.find(key, comp);
        V old=temp.data.getValue();
        temp.data.setValue(value);
        return old;
      }
    }

    private TNode find(K key, Comparator<K> comp) {
      // if found, return node
      if(comp.compare(key, this.data.getKey())==0) {
        return this;
      }
      // else if key is less than current node, go left
      else if(comp.compare(key, this.data.getKey())<0) {
        if(this.left==null) {
          return null;
        }
        else {
          return this.left.find(key, comp);
        }
      }
      // else, go right
      else {
        if(this.right==null) {
          return null;
        }
        else {
          return this.right.find(key, comp);
        }
      }
    }

    private TNode insert(K key, V value, Comparator<K> comp) {
      // if found, return
      if(this.data.getKey()==key) {
        this.data.setValue(value);
        return this;
      }
      // if key is less than current, go left
      else if(comp.compare(key, this.data.getKey())<0) {
        if(this.left==null) {
          this.left=new TNode(key, value);
        }
        else {
          this.left.insert(key, value, comp);
        }
      }
      // else go right
      else {
        if(this.right==null) {
          this.right=new TNode(key, value);
        }
        else {
          this.right.insert(key, value, comp);
        }
      }
      // return the current node
      return this;
    }

    public V get(K key, Comparator<K> comp) {
      // if found, return value
      if(comp.compare(this.data.getKey(), key)==0) {
        return this.data.getValue();
      }
      // else if key is less than current node, go left
      else if(comp.compare(this.data.getKey(), key)>0) {
        if(this.left==null) {
          return null;
        }
        else {
          return this.left.get(key, comp);
        }
      }
      // else go right
      else {
        if(this.right==null) {
          return null;
        }
        else {
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
    ArrayList<K> keys=new ArrayList<K>();
    for (KeyValuePair<K, V> kvp : this.nodes) {
      keys.add(kvp.getKey());
    }
    return keys;
  }

  @Override
  public ArrayList<V> values() {
    this.entrySet();
    ArrayList<V> values=new ArrayList<V>();
    for (KeyValuePair<K, V> kvp : this.nodes) {
      values.add(kvp.getValue());
    }
    return values;
  }

  @Override
  public ArrayList<KeyValuePair<K, V>> entrySet() {
    this.nodes.clear();
    traverse(this.root);
    return this.nodes;
  }

  private void traverse(TNode node) {
    if(node==null) {
      return;
    }
    nodes.add(node.data);
    traverse(node.left);
    traverse(node.right);
  }

  @Override
  public int size() {
    this.entrySet();
    return this.nodes.size();
  }

  @Override
  public void clear() {
    this.root=null;
  }

  public String inorder_driver() {
    return this.inorder(this.root);
  }

  public String inorder(TNode root) {
    if(root != null) {
      return inorder(root.left) + " " + root.data.key + " " + inorder(root.right);
    }
    else {
      return "";
    }
  }

  public String preorder_driver() {
    return this.preorder(this.root);
  }

  private String preorder(TNode root) {
    if(root != null) {
      return root.data.getKey() + " Frequency: " + root.data.getValue() + "\n" + preorder(root.left) + preorder(root.right);
    }
    else {
      return "";
    }
  }

  public static void main(String[] argv) {
    BSTMap<String, Integer> bst=new BSTMap<String, Integer>(new AscendingString());

    bst.put("20", 20);
    bst.put("10", 10);
    bst.put("11", 11);
    System.out.println(bst.get("11"));
    bst.put("5", 5);
    bst.put("6", 6);
    bst.put("4", 4);
    System.out.println(bst.get("4"));
    System.out.println(bst.get("6"));
    bst.put("6", 7);
    bst.put("9", 9);

    System.out.println(bst.get("15"));
    System.out.println("Size: " + bst.size(bst.root));
  }

  public int depth() {
    return this.depth(this.root);
  }

  private int depth(TNode root) {
    if(root==null) {
      return 0;
    }
    else {
      return 1 + Math.max(depth(root.left), depth(root.right));
    }
  }
}