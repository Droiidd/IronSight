package droidco.west3.ironsight.items.looting;

public class Quantity {
  private final int low;
  private final int high;

  public Quantity(int low, int high) {
    this.low = low;
    this.high = high;
  }

  public int getNum() {
    return low + (int) (Math.random() * (high - low + 1));
  }
}
