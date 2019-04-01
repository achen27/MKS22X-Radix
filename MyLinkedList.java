class MyLinkedList<E>{

  class Node{
    private E data;
    private Node next,prev;

    public Node(Node p, E d, Node n){//instantiates instances
      prev = p;
      data = d;
      next = n;
    }

    public Node prev(){//returns the previous node
      return prev;
    }
    public E getData(){//returns the value at the current node
      return data;
    }
    public Node next(){//returns the next node
      return next;
    }

    public void setPrev(Node p){//changes the previous node reference
      prev = p;
    }
    public void setData(E i){//changes the value at the current node
      data = i;
    }
    public void setNext(Node n){//changes the next node reference
      next = n;
    }

    public String toString(){//returns the value at the current node
    return "[" + data + "]";
    }
  }

  private int size;
  private Node start,end;

  public MyLinkedList(){//creates an empty list
    size = 0;
  }

  public int size(){//returns the size of teh list
    return size;
  }

  public void clear(){//reset the list to an empty state
    size = 0;
  }

  public boolean add(E value){//adds a value at the end of the list
    if (size == 0){//adding when there are no values in the list
      Node newNode = new Node(null, value, null);//this node is both the starting and ending node
      start = newNode;
      end = newNode;
    } else {
      Node newEnd = new Node(end, value, null);
      end.setNext(newEnd);//old end willnow reference the newEnd as its next
      end = newEnd;//end will become the new end
    }
    size++;//size increases every time something is added
    return true;
  }

  public String toString(){
    if (size == 0){//will return [] when list is empty
      return "[]";
    } else{
      String output = "[";
      Node current = start;
      while(current.next() != null){
        output += current.getData() + ", ";
        current = current.next();
      }
      output += end.getData() + "]";
      return output;
    }
  }

  private Node getNthNode(int i){
    //System.out.println(this);
    int index = 0;
    Node current = start;
    while(index < i){//loops through linked list until it gets to the requested index
      //System.out.println(index);
      current = current.next();
      index++;
    }
    return current;//returns the requested node
  }

  public E get(int index){//returns the value at the index requested
    if (index < 0 || index >= size()){
      throw new IndexOutOfBoundsException("Index Out of Bounds: "+index);
    }
    Node current = getNthNode(index);
    return current.getData();
  }

  public E set(int index, E value){//changes the value at the index requested
    if (index < 0 || index >= size()){
      throw new IndexOutOfBoundsException("Index Out of Bounds: "+index);
    }
    Node current = getNthNode(index);
    E old = current.getData();
    current.setData(value);
    return old;
  }

  public boolean contains(Integer value){//tells if list has value entered
    Node current = start;
    while(current.next() != null){
      if(current.getData() == value){
        return true;
      }
      current = current.next();
    }
    return current.getData() == value;
  }

  public int indexOf(Integer value){//gives the index of teh first occurance of value entered
    Node current = start;
    int index = 0;//returns -1 if value is not found in list
    while(current.next() != null){
      if(current.getData() == value){
        //System.out.println(index);
        return index;
      }
      current = current.next();
      index++;
    }
    return -1;
  }

  public void add(int index, E value){//adds element with entered value at entered index
    if (index < 0 || index > size()){
      //System.out.println(index);
      throw new IndexOutOfBoundsException("Index Out of Bounds: "+index);
    }
    //System.out.println(size);
    if(index == 0){//adding at the beginning
      Node newNode = new Node(null, value, start);
      start.setPrev(newNode);
      start = newNode;//changes starting reference
      size++;
    }else if(index == size){//adding at end is the same as add(value)
      add(value);
    }else{//adding in the middle
      Node prev = getNthNode(index-1);
      System.out.println(prev);
      Node next = prev.next();
      System.out.println(next);
      Node newNode = new Node(prev, value, next);
      prev.setNext(newNode);
      next.setPrev(newNode);
      size++;
    }
  }

  public E remove(int index){//removes element at entered index
    if (index < 0 || index >= size()){
      throw new IndexOutOfBoundsException("Index Out of Bounds: "+index);
    }
    //System.out.println(index == size - 1);
    if(index == 0){//removing from the beginning
      E old = start.getData();
      Node next = start.next();
      start = next;//changes starting reference
      start.setPrev(null);//sets prev reference of starting reference to null
      size--;
      return old;
    }else if(index == size - 1){//removing from the end
      E old = end.getData();
      Node prev = end.prev();
      end = prev;//changes ending reference
      end.setNext(null);//sets next reference of ending reference to null
      //System.out.println(size);
      size--;
      //System.out.println(size);
      return old;
    }else{//removing from the middle
      Node removing = getNthNode(index);
      E old = removing.getData();
      Node prev = removing.prev();
      Node next = removing.next();
      prev.setNext(next);
      next.setPrev(prev);
      size--;
      return old;
    }
  }

  public boolean remove(Integer value){//removes first instance of entered value
    int index = 0;
    Node current = start;
    while(current.next() != null && current.getData() != value){//finds index of first instance of entered value
      //System.out.println("here");
      index++;
      current = current.next();
    }
    //System.out.println(index);
    if (index < size-1 || current.getData().equals(value)){//checks to see if value is in the list
      //System.out.println(index);
      remove(index);//removes the element at the index
      return true;
    }
    return false;
  }

  public E removeFront(){//remove the 1st element of the list, and return that value
    E old = start.getData();
    Node next = start.next();
    start = next;//changes starting reference
    start.setPrev(null);//sets prev reference of starting reference to null
    size--;
    return old;
  }

  public void extend(MyLinkedList other){//combines two lists together
    if (size == 0){//this list is empty
      start = other.start;//replaces start and end
      end = other.end;
      size = other.size;
      other.size = 0;//other list becomes empty
      other.start = null;
      other.end = null;
    } else if (other.size != 0){
      end.setNext(other.start);
      other.start.setPrev(end);
      end = other.end;
      size += other.size;
      other.size = 0;//other list becomes empty
      other.start = null;
      other.end = null;
    }
  }

}
