package aikamsoft.data.models.output;

import java.util.List;

public class Statistic {

  private String type = "stat";
  private long totalDays;

  private List<PersonStatistic> customers;

  private long totalExpenses;
  private double avgExpenses;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public long getTotalDays() {
    return totalDays;
  }

  public void setTotalDays(long totalDays) {
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

  public double getAvgExpenses() {
    return avgExpenses;
  }

  public void setAvgExpenses(double avgExpenses) {
    this.avgExpenses = avgExpenses;
  }
}
