import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ArrayList<T> {
  private Object[] list;
  private int index;

  public ArrayList() {
    this.list=new Object[10];
    this.index=0;
  }

  public void add(T item) {
    if(this.index<this.list.length-1) {
      this.list[this.index++]=item;
    }
    else {
      Object[] temp=new Object[this.list.length*2];
      for(int i=0; i<this.list.length; i++) {
        temp[i]=this.list[i];
      }
      this.list=temp;
      this.list[this.index++]=item;
    }
  }

  public Object get(int index) {
    return index<this.list.length?this.list[index]:null;
  }

  public Object remove(int index) {
    Object item=this.list[index];
    for(int i=index; i<this.list.length-1; i++) {
      this.list[i]=this.list[i+1];
    }
    return item;
  }

  public String toString() {
    String result="";
    for(int i=0; i<this.list.length; i++) {
      if(this.list[i]==null) {
        break;
      }
      result+=this.list[i] + ", ";
    }
    return result;
  }

  public void shuffle() {
    List<Object> tempList=Arrays.asList(this.list);
    Collections.shuffle(tempList);
    this.list=tempList.toArray(this.list);
  }

  public void clear() {
    this.list=new Object[this.list.length];
    this.index=0;
  }

  public static void main(String[] args) {
    ArrayList<Integer> list=new ArrayList<>();
    for(int i=0; i<15; i++) {
      list.add(i);
    }
    System.out.println(list);
    System.out.println("Removing at index 3: " + list.remove(3));;
    System.out.println(list);
    list.clear();
    for(int i=0; i<15; i++) {
      list.add(i);
    }
    System.out.println(list);
    list.shuffle();
    System.out.println(list);
  }

}