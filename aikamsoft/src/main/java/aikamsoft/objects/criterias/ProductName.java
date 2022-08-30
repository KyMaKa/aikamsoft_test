package aikamsoft.objects.criterias;

import aikamsoft.objects.types.Types;

public class ProductName {

  Types type = Types.product;

  String productName;
  int minTimes;

  public String getName() {
    return this.productName;
  }

  public int getValue() {
    return 0;
  }
}
