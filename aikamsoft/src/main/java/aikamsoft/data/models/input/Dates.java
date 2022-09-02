package aikamsoft.data.models.input;

import aikamsoft.data.types.Types;
import java.time.LocalDate;

/**
 * Class to map stat request.*
 */
public class Dates extends Criteria{

  public transient Types type = Types.stat;

  private LocalDate startDate;
  private LocalDate endDate;

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }
}
