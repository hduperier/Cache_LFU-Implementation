package assignment4_f20;

public class Playground {
  public static void main(String[] args) {
 
  }

  public static void RunMyTests() {
   Cache_LFU lfc = new Cache_LFU(3);
    lfc.refer("AA8C");
    lfc.refer("AA8C");
    lfc.refer("1234");
    lfc.refer("234A");
    lfc.refer("AA8C");
    lfc.refer("234A");
    lfc.refer("ABCD");
    lfc.refer("234A");
    lfc.refer("ABCD");
    lfc.refer("1101");
    lfc.refer("2202"); lfc.refer("2202");
    lfc.refer("2202"); lfc.refer("2202");

    System.out.println(lfc.size());
    System.out.println(lfc.numElts());
    printHeap(lfc.getHeap().getHeap(), lfc.getHeap().size());
    //i hste this

  }

  public static void printHeap(CacheFrame[] e,int len) { 
    // this method skips over unused 0th index....
    System.out.println("Printing Heap");
    for(int i=1; i< len+1; i++) {
      System.out.print("(p."+e[i].value+",f"+e[i].priority+",s"+e[i].getSlot()+")\t");
    }
    System.out.print("\n");
  }

}