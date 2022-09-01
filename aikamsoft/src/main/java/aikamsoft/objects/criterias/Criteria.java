package aikamsoft.objects.criterias;

import aikamsoft.objects.types.Types;
import com.google.gson.annotations.Expose;

public class Criteria {
  @Expose(serialize = true, deserialize = false)
  private Types type;

  public Types getType() {
    return this.type;
  }

}
