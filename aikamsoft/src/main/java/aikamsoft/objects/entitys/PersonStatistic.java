package aikamsoft.objects.entitys;

import java.util.List;

public class PersonStatistic {

  private String name;

  private List<Purchases> purchases;

  private long totalExpenses;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Purchases> getPurchases() {
    return purchases;
  }

  public void setPurchases(List<Purchases> purchases) {
    this.purchases = purchases;
  }

  public long getTotalExpenses() {
    return totalExpenses;
  }

  public void setTotalExpenses(long totalExpenses) {
    this.totalExpenses = totalExpenses;
  }
}
