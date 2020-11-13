package assignment4_f20;

public class CacheFrame implements Frame {
  public String value;  // a hex string for an address block
  public int priority;  // a fequency counter, used as priority
  private int slot; // the subscript where this element is in the heap array

  public CacheFrame(String av, int ap) {
    this.value = av;
    this.priority = ap;
    this.slot = 0; // this will change as the array is manipulated
  }

  public String getValue() { return this.value; }
  // no setValue... value will not change once set in constructor
  
  public int getPriority() { return this.priority; }
  public void setPriority(int p) { this.priority = p; }
  
  public int getSlot() { return this.slot; }
  public void setSlot(int s) { this.slot = s; }
   
  @Override
  public String toString() {
    return "(val: " + value + ", pri:"  + priority + ", slot: " + slot + ")";
  }
}