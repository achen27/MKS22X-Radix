import java.util.*;

public class Radix{
  public static void radixsort(int[]data){

    int longest = data[0];
    for (int i = 1; i < data.length; i++){
      if (Math.abs(data[i])>longest){
        longest = Math.abs(data[i]);
      }
    }
    System.out.println("Largest #: "+longest);

    int place = 1;
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    for (int i = 0; i < buckets.length; i++){
      buckets[i] = new MyLinkedList();
    }
    MyLinkedList<Integer> sorting = new MyLinkedList();

    while (longest % place != longest){
      System.out.println("Start Loop");
      if (place == 1){
        System.out.println("First Pass");
        for (int i = 0; i < data.length; i++){
          System.out.println("Int: "+data[i]);
          if (data[i] >= 0){
            System.out.println("Positive");
            System.out.println(data[i]%10+10);
            buckets[data[i]%10+10].add(data[i]);
            System.out.println(buckets[data[i]%10+10].toString());
          } else {
            System.out.println("Negative");
            buckets[9+data[i]%10].add(data[i]);
          }
        }
        for (int i = 0; i < buckets.length; i++){
          sorting.extend(buckets[i]);
        }
        System.out.println("First Pass: "+sorting.toString());
        System.out.println("--------------------------------------");
      } else {
        System.out.println("sorting: " +sorting.toString());
        for (int i = 0; i < buckets.length; i++){
          buckets[i].clear();
        }
        System.out.println("start size: " +sorting.size());
        while (sorting.size() != 0){
          System.out.println("start other loop");
          int current = sorting.removeFront();
          System.out.println(current);
          System.out.println(current/place%10);
          if (current >= 0){
            buckets[current/place%10].add(current);
            System.out.println(buckets[current/place%10].toString());
          } else {
            buckets[9+current/place%10].add(current);
          }
        }
        for (int i = 0; i < buckets.length; i++){
          sorting.extend(buckets[i]);
          System.out.println("Merging: "+sorting.toString());
          System.out.println("size: " +sorting.size());
        }
        System.out.println("Other Pass: "+sorting.toString());
        System.out.println("--------------------------------------");
      }
      System.out.println("end size: " +sorting.size());
      place *=10;
    }
    for (int i = 0; i < data.length; i++){
      data[i] = sorting.removeFront();
    }
    System.out.println("sorted array: " +Arrays.toString(data));

  }

  public static void main(String[]args){
  /*System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
  int[]MAX_LIST = {1000000000,500,10};
  for(int MAX : MAX_LIST){
    for(int size = 31250; size < 2000001; size*=2){
      long qtime=0;
      long btime=0;
      //average of 5 sorts.
      for(int trial = 0 ; trial <=5; trial++){
        int []data1 = new int[size];
        int []data2 = new int[size];
        for(int i = 0; i < data1.length; i++){
          data1[i] = (int)(Math.random()*MAX);
          data2[i] = data1[i];
        }
        long t1,t2;
        t1 = System.currentTimeMillis();
        Radix.radixsort(data2);
        t2 = System.currentTimeMillis();
        qtime += t2 - t1;
        t1 = System.currentTimeMillis();
        Arrays.sort(data1);
        t2 = System.currentTimeMillis();
        btime+= t2 - t1;
        if(!Arrays.equals(data1,data2)){
          System.out.println("FAIL TO SORT!");
          System.exit(0);
        }
      }
      System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
    }
    System.out.println();
  }*/
  int[] data = {103,45,241,-861,359,-175,920,4};
  radixsort(data);
}

}
