package aikamsoft.data.models.output;

import java.util.LinkedList;
import java.util.List;

public class PersonStatistic {

  private String name;

  private List<Item> purchases = new LinkedList<>();

  private long totalExpenses = 0;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Item> getPurchases() {
    return purchases;
  }

  public void setPurchases(List<Item> purchases) {
    this.purchases = purchases;
  }

  public long getTotalExpenses() {
    return totalExpenses;
  }

  public void setTotalExpenses(long totalExpenses) {
    this.totalExpenses = totalExpenses;
  }
}
