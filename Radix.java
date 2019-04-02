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
    MyLinkedList<Integer> sorting = new MyLinkedList();

    while (largest % place != 0){
      if (place == 1){
        for (int i = 0; i < data.length; i++){
          if (data[i] >= 0){
            buckets[data[i]%10+10].add(data[i]);
          } else {
            buckets[9+data[i]%10].add(data[i]);
          }
        }
        sorting = buckets[0];
        for (int i = 1; i < buckets.length; i++){
          sorting.extend(buckets[i]);
        }
      } else {

      }
      place *=10;
    }

  }
}
