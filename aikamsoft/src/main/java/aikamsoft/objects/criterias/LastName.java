package aikamsoft.objects.criterias;

import aikamsoft.objects.types.Types;

public class LastName{

  Types type = Types.lastName;
  String lastName;

  public String getName() {
    return this.lastName;
  }

  public int getValue() {
    return 0;
  }
}
