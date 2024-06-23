package droidco.west3.ironsight.items.looting;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Quantity {
  private int low;
  private int high;

  public int getNum() {
    return low + (int) (Math.random() * (high - low + 1));
  }
}
