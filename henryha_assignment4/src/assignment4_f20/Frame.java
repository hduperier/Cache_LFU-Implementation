package assignment4_f20;

public interface Frame {
  String getValue();  // will be a hexadecimal "address" of a memory block
  // there is no setValue as the address will not change once the frame is made
  
  int getPriority();  // will be a frequency counter, starts at 1
  void setPriority(int p);
  
  int getSlot();
  void setSlot(int s);
}