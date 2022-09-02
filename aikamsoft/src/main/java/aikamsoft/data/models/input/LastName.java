package aikamsoft.data.models.input;

import aikamsoft.data.types.Types;

public class LastName extends Criteria {

  transient Types type = Types.lastName;
  private String lastName;

  public String getLastName() {
    return this.lastName;
  }

}
