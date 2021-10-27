
/*
Parth Parth
Template for the BSTMap classes
Fall 2020
CS 231 Project 6
*/
import java.util.ArrayList;
import java.util.Comparator;

public class BSTMap<K, V> /*implements MapSet<K, V>*/ {

  // fields here
  Comparator<K> comp;
  TNode root;

  // constructor: takes in a Comparator object
  public BSTMap(Comparator<K> comp) {
    this.comp = comp;
    this.root=null;
  }

  // adds or updates a key-value pair
  // If there is already a pair with new_key in the map, then update
  // the pair's value to new_value.
  // If there is not already a pair with new_key, then
  // add pair with new_key and new_value.
  // returns the old value or null if no old value existed
  public V put(K key, V value) {
    // check for and handle the special case
    // call the root node's put method
    if(this.root==null) {
      this.root=new TNode(key, value);
      return null;
    }
    else {
      return this.root.put(key, value, comp);
    }
  }

  // gets the value at the specified key or null
  public V get(K key) {
    // check for and handle the special case
    // call the root node's get method

    TNode found=search(this.root, key);
    if(found!=null) {
      return found.nodeKVP.getValue();
    }

    // stub code
    return null;
  }

  private TNode search(TNode root, K key) {
    if(root==null || comp.compare(root.nodeKVP.getKey(), key)==0) {
      return root;
    }

    if(comp.compare(root.nodeKVP.getKey(), key)<0) {
      return search(root.left, key);
    }

    return search(root.right, key);
  }

  // Write stubs (functions with no code) for the remaining
  // functions in the MapSet interface

  // entrySet notes: For the sake of the word-counting project, the
  // pairs should be added to the list by a pre-order traversal.

  // You can make this a nested class
  private class TNode {
    // need fields for the left and right children
    // need a KeyValuePair to hold the data at this node
    TNode left, right;
    KeyValuePair<K, V> nodeKVP;

    // constructor, given a key and a value
    public TNode(K k, V v) {
      // initialize all of the TNode fields
      this.left=this.right=null;
      this.nodeKVP=new KeyValuePair<K, V>(k, v);
    }

    // Takes in a key, a value, and a comparator and inserts
    // the TNode in the subtree rooted at this node

    // Returns the value associated with the key in the subtree
    // rooted at this node or null if the key does not already exist
    public V put(K key, V value, Comparator<K> comp) {
      // implement the binary search tree put
      // insert only if the key doesn't already exist

      // stub code
      return insert(key, value, comp).nodeKVP.getValue();
    }

    private TNode insert(K key, V value, Comparator<K> comp) {
      if(this.nodeKVP.getKey()==null) {
        this.nodeKVP=new KeyValuePair<K, V>(key, value);
        return this;
      }
      if(comp.compare(this.nodeKVP.getKey(), key)<0) {
        if(this.right==null) {
          this.right=new TNode(null, null);
        }
        
        this.right.insert(key, value, comp);
      }
      else if(comp.compare(this.nodeKVP.getKey(), key)>0) {
        if(this.left==null) {
          this.left=new TNode(null, null);
        }
        
        this.left.insert(key, value, comp);
      }
      return this;
    }

    // Takes in a key and a comparator
    // Returns the value associated with the key or null
    public V get(K key, Comparator<K> comp) {
      if(comp.compare(this.nodeKVP.getKey(), key)==0) {
        return this.nodeKVP.getValue();
      }
      if(comp.compare(this.nodeKVP.getKey(), key)<0) {
        return this.right.get(key, comp);
      }
      else {
        return this.left.get(key, comp);
      }
    }

    // Any additional methods you want to add below, for
    // example, for building ArrayLists

    public String toString() {
      return this.nodeKVP+"";
    }

  }// end of TNode class










  public String toString() {
    return inorder(this.root);
  }

  public String inorder(TNode root) {
    if(root!=null) {
      return inorder(root.left) + " " + root.nodeKVP.key + " " + inorder(root.right);
    }
    else { 
      return "";
    }
  }

  public String postorder(TNode root) {
    if(root!=null) {
      return postorder(root.left) + postorder(root.right) + " " + root.nodeKVP.key;
    }
    else { 
      return " ";
    }
  }


  public V min() {
    TNode n=this.root;

    while(n.left!=null) {
      n=n.left;
    }

    return n.nodeKVP.getValue();
  }


  // test function
  public static void main(String[] argv) {
    /*
     * 
     * // create a BSTMap BSTMap<String, Integer> bst = new BSTMap<String, Integer>(
     * new StringAscending() );
     * 
     * bst.put( "twenty", 20 ); bst.put( "ten", 10 ); bst.put( "eleven", 11 );
     * bst.put( "five", 5 ); bst.put( "six", 6 );
     * 
     * System.out.println( bst.get( "eleven" ) );
     * 
     * // put more test code here
     * 
     */
    BSTMap map=new BSTMap<String, Integer>(new AscendingString());

    map.put("15", 15);
    map.put("8", 8);
    map.put("3", 3);
    map.put("17", 17);
    map.put("16", 16);
    map.put("26", 26);
    
    System.out.println(map.postorder(map.root));
  }
}