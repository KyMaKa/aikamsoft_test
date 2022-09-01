package aikamsoft.objects.criterias;

import aikamsoft.objects.types.Types;

public class PassiveCustomers extends Criteria {

  transient Types type = Types.passiveCustomers;

  int badCustomers;

  public int getCount() {
    return this.badCustomers;
  }
}
