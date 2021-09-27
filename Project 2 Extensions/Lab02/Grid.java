public class Grid {
  public static void main(String[] args) {
    for(int i=0; i<args.length; i++)
      System.out.print(args[i]+' ');
    System.out.println();
    
    int yogi=Integer.parseInt(args[0]);
    int booboo=Integer.parseInt(args[1]);

    String[][] ranger=new String[yogi][booboo];

    for(int i=0; i<yogi; i++)
      for(int j=0; j<booboo; j++)
        ranger[i][j]=new String(String.valueOf(Math.random()));

    for(int i=0; i<ranger.length; i++) {
      for(int j=0; j<ranger[i].length; j++)
        System.out.print(ranger[i][j]+' ');
      System.out.println();
    }
  }
}
