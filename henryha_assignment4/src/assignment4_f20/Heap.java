package assignment4_f20;

public interface Heap {
/*
  Interface: The Heap will provide this collection of operations:

    insert
      in: a CacheFrame object, containing String key (address), 
          the priority (frequency counter) and the array subscript (slot).
          We may have duplicate priorities being inserted 
      return: void
      effect: the heap size goes up one
    delMin
      in: nothing
      return: nothing
      effect: the heap size goes down one, the element at the root is removed
    getMin
      in: nothing
      return: a CacheFrame object (the one in slot 1 on the array)
      effect: no change in heap state
    size
      in: nothing
      return: integer 0 or greater, the count of the number of elements in the heap
      effect: no change in heap state
    incELt
      in: a CacheFrame object
      return: nothing
      effect: frequency counter (priority) in the CacheFrame object is incremented by 1
              heap state is altered by perhaps the indicated element being bubbled down
              also, slot numbers in moved heap elements change to reflect new locations
    decElt
      in: a CacheFrame object
      return: nothing
      effect: frequency counter (priority) in the CacheFrame object is decreased by 1
              heap state is altered by perhaps the indicated element being bubbled up
              also, slot numbers in moved heap elements change to reflect new locations
              error: do no frequency change is the CacheFrame object already has priority 1
              (all priorities must be 1 or greater).
  */

  // ADT operations
  void insert(CacheFrame elt);
  void delMin();
  CacheFrame getMin();  
  int size();
  void incElt( CacheFrame elt );
  void decElt( CacheFrame elt );
  
  // not really an abstract op of a heap but
  // we need it for grading this assignment
  CacheFrame[] getHeap();
}
