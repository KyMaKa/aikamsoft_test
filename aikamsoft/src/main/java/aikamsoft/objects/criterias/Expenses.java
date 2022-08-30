package aikamsoft.objects.criterias;


import aikamsoft.objects.types.Types;

public class Expenses {

  Types type = Types.cost;

  int minExpenses;
  int maxExpenses;

  public int getMaxExpenses() {
    return this.maxExpenses;
  }

  public int getMinExpenses() { return this.minExpenses; }
}
