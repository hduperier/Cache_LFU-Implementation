package assignment4_f20;

import java.util.HashMap;

public interface Cache {
  /*
    Interface: The Cache will provide this collection of operations:
   
    size:
      in: nothing
      return: an integer, the maximum number of frames the cache will hold 
      effect: no cache state change
    numElts:
      in: nothing
      return: an integer, the number of frames currently stored in the cache 
      effect: no cache state change
    isFull:
      in: nothing
      return: a boolean, telling is the number of items in the cache is the same as the max limit
      effect: no cache state change
    refer:
      in: String, a hexadecimal value as a string
          assume this op will always be sent a string the looks like hex
          no need to check or verify
      return: boolean, telling true (we found the hex address in the cache)
                            or false (we did not find the address in cache)
      effect: cache state changes by possibly having a frame removed (if it was full)
              and by having a new frame added if the address is not in the cache.
              if the address IS in the cache, then all that changes in the frequency counter
              for that frame, which goes up one.
              Also the number of frames in the cache goes up one if we add a frame
  */

  // ADT operations
  public int size();
  public int numElts();
  public boolean isFull();
  public boolean refer( String address );
  
  // not really ops of an abstract cache but
  // we need this for grading this assignment
  public MinBinHeap getHeap();
  public HashMap getHashMap();
}
