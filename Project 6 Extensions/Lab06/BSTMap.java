/*
Parth Parth

Template for the BSTMap classes
Fall 2020
CS 231 Project 6
*/

import java.util.ArrayList;

public class BSTMap<K, V> implements MapSet<K, V> {

  // fields here

  // constructor: takes in a Comparator object
  public BSTMap(Comparator<K> comp) {
    // initialize fields heres
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

    // stub code
    return null;
  }

  // gets the value at the specified key or null
  public V get(K key) {
    // check for and handle the special case
    // call the root node's get method

    // stub code
    return null;
  }

  // Write stubs (functions with no code) for the remaining
  // functions in the MapSet interface

  // entrySet notes: For the sake of the word-counting project, the
  // pairs should be added to the list by a pre-order traversal.

  // You can make this a nested class
  private class TNode {
    // need fields for the left and right children
    // need a KeyValuePair to hold the data at this node

    // constructor, given a key and a value
    public TNode(K k, V v) {
      // initialize all of the TNode fields
    }

    // Takes in a key, a value, and a comparator and inserts
    // the TNode in the subtree rooted at this node

    // Returns the value associated with the key in the subtree
    // rooted at this node or null if the key does not already exist
    public V put(K key, V value, Comparator<K> comp) {
      // implement the binary search tree put
      // insert only if the key doesn't already exist

      // stub code
      return null;
    }

    // Takes in a key and a comparator
    // Returns the value associated with the key or null
    public V get(K key, Comparator<K> comp) {

      // stub code
      return null;
    }

    // Any additional methods you want to add below, for
    // example, for building ArrayLists

  }// end of TNode class

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
  }

}