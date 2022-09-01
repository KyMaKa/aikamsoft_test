package aikamsoft.objects.entitys;

import java.util.List;

public class Statistic {

  private String type;
  private int totalDays;

  private List<PersonStatistic> customers;

  private long totalExpenses;
  private long avgExpenses;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getTotalDays() {
    return totalDays;
  }

  public void setTotalDays(int totalDays) {
    this.totalDays = totalDays;
  }

  public List<PersonStatistic> getCustomers() {
    return customers;
  }

  public void setCustomers(List<PersonStatistic> customers) {
    this.customers = customers;
  }

  public long getTotalExpenses() {
    return totalExpenses;
  }

  public void setTotalExpenses(long totalExpenses) {
    this.totalExpenses = totalExpenses;
  }

  public long getAvgExpenses() {
    return avgExpenses;
  }

  public void setAvgExpenses(long avgExpenses) {
    this.avgExpenses = avgExpenses;
  }
}
