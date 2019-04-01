public class Radix{
  public static void radixsort(int[]data){
    int largest = data[0];
    for (int i = 1; i < data.length; i++){
      if (data[i]>largest){
        largest = data[i];
      }
    }
    int place = 1;
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];

  }
}
