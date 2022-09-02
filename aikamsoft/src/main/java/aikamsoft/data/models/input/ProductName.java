package aikamsoft.data.models.input;

import aikamsoft.data.types.Types;

public class ProductName extends Criteria {

  transient Types type = Types.product;

  String productName;
  int minTimes;

  public String getName() {
    return this.productName;
  }

  public int getMinTimes() {
    return minTimes;
  }
}
