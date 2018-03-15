public class StudentHashSet {
  private int size; //the number of items in the set
  private int capacity; //the amount of space in the set
  private double loadFactor; //the size dividedby the capacity
  private Student[] hashSet = new Student[17]; // the set itself represented by an array

  //Constructor
  public StudentHashSet(){
    this.size = 0;
    this.capacity = hashSet.length;
    this.loadFactor = ((double) size) / capacity;
  }
//------------------------------------------------------------------------------
  //Adds the student to the set iff the set does not already contain the student
  public void add(Student s) {
    if (loadFactor > .5) //resize the set if the loadFactor goes above a certain value
      resize();         // ensures that the hashSet continues running at close to O(1) speed
    if (!this.contains(s)){
      try{
      hashSet[linearProbe(s)] = s;
      size++;
      loadFactor = ((double) size) / capacity;
      }catch (Exception exc){}
    }
  }
//------------------------------------------------------------------------------
  //Finds the next index at which Student s should be placed within hashSet
  private int linearProbe(Student s){
    boolean notFound = false;
    int h = s.hashCode();
    int index = h % capacity;
    for (int i = index; i < capacity; i++){
      if (hashSet[i] == null)
        return i;
    }
    for (int i = 0; i < index; i++){
      if (hashSet[i] == null)
        return i;
    }
      return -1;
  }
//------------------------------------------------------------------------------
  //copies all Students into a new Hashset that is larger by probing them to their new proper spots.
  //used when the loadFactor becomes too large
  private void resize(){
    Student[] temp = new Student[capacity * 2 + 1];
    for (int i = 0; i < capacity; i++){
      if(hashSet[i] != null)
        temp[linearProbe(hashSet[i])] = hashSet[i];
    }
    capacity = temp.length;
    this.loadFactor = ((double) size) / capacity;
    this.hashSet = temp;
  }
//------------------------------------------------------------------------------
  //Returns false if the item does not exist to be removed
  //otherwise, removes the item and returns true
  public boolean remove(Student s) {
    if (!this.contains(s))
      return false;
    int h = s.hashCode();
    int index = h % capacity;
    for (int i = index; i < capacity; i++){
      if (hashSet[i] != null && hashSet[i].hashCode() == h){
        hashSet[i] = null;
        size--;
        loadFactor = size / capacity;
        return true;
      }
    }
    for (int i = 0; i < index; i++){
      if (hashSet[i] != null && hashSet[i].hashCode() == h){
        hashSet[i] = null;
        return true;
      }
    }
    return false;
  }
//------------------------------------------------------------------------------
  //Returns true if the Student is in the Hashset and false otherwise
  public boolean contains(Student s) {
    int h = s.hashCode();
    int index = h % capacity;
    for (int i = index; i < capacity; i++){
      if (hashSet[i] != null && hashSet[i].hashCode() == h)
        return true;
    }
    for(int i = 0; i < index; i++){
      if (hashSet[i] != null && hashSet[i].hashCode() == h)
        return true;
    }
    return false;
  }
//------------------------------------------------------------------------------
  //Returns the number of Students in the Set.
  public int size() {
    return size;
  }
}
