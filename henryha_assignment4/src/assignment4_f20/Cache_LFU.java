package assignment4_f20;

import java.util.HashMap;

public class Cache_LFU implements Cache {
  
  HashMap<String, CacheFrame> map; 
    // allocate from java collections lib
    // do it this way so we all start with default size and 
    // default lambda and default hash function for string keys
  MinBinHeap heap; // your own heap code above
  int limit;       // max num elts the cache can hold
  int size;        // current number elts in the cache
  
  public Cache_LFU (int maxElts) {
    this.map = new HashMap<String, CacheFrame>();
    this.heap = new MinBinHeap(maxElts);
    this.limit = maxElts;
    this.size = 0;
  }
  
  // dont change this we need it for grading
  public MinBinHeap getHeap() { return this.heap; }
  public HashMap getHashMap() { return this.map; }

@Override
public int size() {
	return limit;
}

@Override
public int numElts() {
	return size;
}

@Override
public boolean isFull() {
	if(numElts() == size()) {
		return true;
	} else {
		return false;
	}
}

@SuppressWarnings("unlikely-arg-type")
@Override
public boolean refer(String address) {
	if(map.containsKey(address)) {
		CacheFrame tempFrame = map.get(address);
		heap.incElt(tempFrame);
		return true;
	}
	else {
		if(numElts() == size()) {
			CacheFrame tempFrame2 = null;
			int low = 15;
			for (CacheFrame frame : map.values()) {
				if(frame.getPriority() < low) {
					low = frame.getPriority();
					tempFrame2 = frame;
				}
			}
			String temp = null;
			for(String key:map.keySet()) {
				if(tempFrame2.equals(map.get(key))) {
					temp = key;
				}
			}
			map.remove(temp);
			heap.delMin();
			CacheFrame tempFrame3 = new CacheFrame(address, 1);
			map.put(address, tempFrame3);
			heap.insert(tempFrame3);
			return false;
		} else {
			CacheFrame newone = new CacheFrame(address, 1);
			map.put(address, newone);
			heap.insert(newone);
			return false;
		}
	}
}

}