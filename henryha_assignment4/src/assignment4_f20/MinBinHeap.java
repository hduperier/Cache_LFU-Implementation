package assignment4_f20;

public class MinBinHeap implements Heap {
	private CacheFrame[] array; // load this array
	private int size;      // how many items currently in the heap
	private int arraySize; // Everything in the array will initially
	// be null. This is ok! Just build out
	// from array[1]

public MinBinHeap(int nelts) {
	this.array = new CacheFrame[nelts+1];  // remember we dont use slot 0
	this.arraySize = nelts+1;
	this.size = 0;
	this.array[0] = new CacheFrame(null, 0); // 0 not used, so this is arbitrary
}

// Please do not remove or modify this method! Used to test your entire Heap.
@Override
public CacheFrame[] getHeap() { return this.array; }

// insert new CacheFrame to the array, based on if heap is full or not
@Override
public void insert(CacheFrame elt) {
	//if elt is not already in the heap and there's room
	if((size() + 1) < arraySize) {
		array[size() + 1] = elt;
		size++;
		int Size = size();
		while (Size > 0){
			if(Size%2 == 0) {
				if(array[Size/2] != null && array[Size] != null) {
					if(array[Size/2].getPriority() > array[Size].getPriority()) {
						CacheFrame temp = array[Size];
						array[Size] = array[Size/2];
						array[Size/2] = temp;
					}
				} Size = Size /2;
			} else {
				if((Size - 1)/2 > 0  && array[(Size-1)/2] != null && array[Size] != null) {
					if(array[(Size- 1) /2].getPriority() > array[Size].getPriority()) {
						CacheFrame temp = array[Size];
						array[Size] = array[(Size- 1) /2];
						array[(Size- 1) /2] = temp;
					}
				} Size = (Size - 1)/2;
			}
		}
		Size = 1;
		while(Size <= size()) {
			array[Size].setSlot(Size);
			Size++;
		}
		return;		
		
	} else {
		delMin();
		insert(elt);
		return;
	}

}

@Override
public void delMin() {
	if(size() == 1) {
		array[1] = null;
		size--;
		return;
	}
	if(size() == 0) {
		return;
	}
	
	array[1] = array[size()];
	array[size] = null;
	size--;

	bubbleDown();

	
	int i = 1;
	while(i <= size()) {
		array[i].setSlot(i);
		i++;
	}
}

@Override
public CacheFrame getMin() {
	if(size() == 0) {
		return null;
	} else {
		return array[1];
	}	
}

// size of the heap
@Override
public int size() {
	return size;
}


@Override
public void incElt(CacheFrame elt) {
	int track = 0;
	for(int i=1; i<size()+1;i++) {
		if (array[i].equals(elt)) {
			track = i;
		}
	}
	if(track == 0) return;
	array[track].setPriority(array[track].getPriority()+1);
	CacheFrame mid = array[track];
	int leftone = track*2;
	int rightone = track*2+1;
	if(leftone>arraySize-1 || rightone>arraySize-1) return;
	CacheFrame left = array[leftone];
	CacheFrame right = array[rightone];
	if(left==null || right == null) return;
	
	while(left.getPriority() < mid.getPriority() || right.getPriority() < mid.getPriority()) {
		if(left.getPriority() > right.getPriority()) {
			CacheFrame replacer = array[right.getSlot()];
			int holderSlot = array[mid.getSlot()].getSlot();
			int checkSlot = array[right.getSlot()].getSlot();
			
			array[checkSlot] = array[holderSlot];
			array[holderSlot] = replacer;
			
			int highReplace = array[holderSlot].getSlot();
			int lowReplace = array[checkSlot].getSlot();
			
			array[holderSlot].setSlot(lowReplace);
			array[checkSlot].setSlot(highReplace);
			
			mid = array[checkSlot];
			int doubleit = checkSlot*2;
			int doubleitandone = checkSlot*2+1;
			if(doubleit > size()) {
				return;
			} else {
				left = array[doubleit];
				right = array[doubleitandone];
			}
			if(left == null && right == null) break;
		} else {
			CacheFrame replacer = array[left.getSlot()];
			int holderSlot = array[mid.getSlot()].getSlot();
			int checkSlot = array[left.getSlot()].getSlot();
			
			array[checkSlot] = array[holderSlot];
			array[holderSlot] = replacer;
			
			int highReplace = array[holderSlot].getSlot();
			int lowReplace = array[checkSlot].getSlot();
			
			array[holderSlot].setSlot(lowReplace);
			array[checkSlot].setSlot(highReplace);
			
			mid = array[checkSlot];
			int doubleit = checkSlot*2;
			int doubleitandone = checkSlot*2+1;
			if(doubleit > size()) {
				return;
			} else if(doubleitandone > size()) {
				return;
			}else {
				left = array[doubleit];
				right = array[doubleitandone];
			}
			if(left == null && right == null) break;
		}
	}
}



@Override
public void decElt(CacheFrame elt) {
	int tracker = 0;
	for(int i=1; i<size()+1; i++) {
		if(array[i].equals(elt)) {
			tracker = i;
		}
	}
	if (tracker == 0) return;
	if(array[tracker].getPriority() > 1) array[tracker].setPriority(array[tracker].getPriority()-1);
	
	CacheFrame holder = array[tracker/2];
	CacheFrame check = array[tracker];
	
	while(check.getPriority() < holder.getPriority()) {
		CacheFrame replacer = array[check.getSlot()];
		int holderSlot = array[holder.getSlot()].getSlot();
		int checkSlot = array[check.getSlot()].getSlot();
		
		array[checkSlot] = array[holderSlot];
		array[holderSlot] = replacer;
		
		int highReplace = array[holderSlot].getSlot();
		int lowReplace = array[checkSlot].getSlot();
		
		array[holderSlot].setSlot(lowReplace);
		array[checkSlot].setSlot(highReplace);
		check = array[holderSlot];
		int halver = holderSlot/2;
		holder = array[halver];
	}
}

public void bubbleDown() {
	int i = 1;
	while(i < size()) {
		//two children
		if(i*2 + 1 <= size()) {
			if(array[i*2].getPriority() < array[i*2 + 1].getPriority()){
				if(array[i].getPriority() > array[i*2].getPriority()) {
					CacheFrame temp = array[i];
					array[i] = array[i*2];
					array[i * 2] = temp;
					array[i].setSlot(i);
					array[i * 2].setSlot(i * 2);
				}
			} else {
				if(array[i].getPriority() > array[i*2 + 1].getPriority()) {
					CacheFrame temp = array[i];
					array[i] = array[i*2 + 1];
					array[i * 2 + 1] = temp;
					array[i].setSlot(i);
					array[i * 2 + 1].setSlot(i * 2 + 1);
				}
			}
			//one child
		} else if(i*2 <= size() && array[i*2] !=  null) {
			if(array[i].getPriority() > array[i*2].getPriority()) {
				CacheFrame temp = array[i];
				array[i] = array[i*2];
				array[i * 2] = temp;
				array[i].setSlot(i);
				array[i * 2].setSlot(i * 2);
			}	
		} i = i + 1;
	}
}
}