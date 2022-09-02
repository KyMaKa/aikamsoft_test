package aikamsoft.data.models.input;

import aikamsoft.data.types.Types;
import com.google.gson.annotations.Expose;

/**
 * Main class for all criterias.
 * Used to simplify work with criterias.
 */
public class Criteria {
  @Expose(serialize = true, deserialize = false)
  private Types type;

  public Types getType() {
    return this.type;
  }

}
