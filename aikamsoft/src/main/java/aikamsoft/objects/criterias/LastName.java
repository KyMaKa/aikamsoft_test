package aikamsoft.objects.criterias;

import aikamsoft.objects.types.Types;

public class LastName extends Criteria {

  transient Types type = Types.lastName;
  private String lastName;

  public String getLastName() {
    return this.lastName;
  }

}
