package aikamsoft.data.models.input;


import aikamsoft.data.types.Types;

public class Expenses extends Criteria {

  transient Types type = Types.cost;

  private int minExpenses;
  private int maxExpenses;

  public int getMaxExpenses() {
    return this.maxExpenses;
  }

  public int getMinExpenses() { return this.minExpenses; }
}
