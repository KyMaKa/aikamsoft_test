package aikamsoft.controller;

import aikamsoft.data.models.input.Criteria;
import aikamsoft.data.models.input.Dates;
import aikamsoft.data.models.output.PersonStatistic;
import aikamsoft.data.models.output.Statistic;
import aikamsoft.service.finder.Finder;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * Controller for 'stat' operation.
 */
public class StatisticController {

  private final Finder finder;

  public StatisticController() {
    this.finder = new Finder();
  }

  /**
   * Maps result and some additional data to corresponding output entity.
   * @param criteria entity that contains date.
   * @return Statistic entity with result.
   * @throws SQLException if something wrong with connection to DB query.
   */
  public Statistic statOperation(Criteria criteria) throws SQLException {

    Statistic stats = new Statistic();
    Dates dates = (Dates) criteria;
    LocalDate startDate = dates.getStartDate();
    LocalDate endDate = dates.getEndDate();
    List<PersonStatistic> personStats = finder.getStatistic(startDate, endDate);

    long total = 0;
    long count = 0;
    for (PersonStatistic ps : personStats) {
      total += ps.getTotalExpenses();
      count++;
    }
    stats.setCustomers(personStats);
    stats.setTotalExpenses(total);
    stats.setAvgExpenses((double) total / count);
    stats.setTotalDays(businessDays(startDate, endDate));

    return stats;
  }

  /**
   * Helper class to count only business days.
   * @param startDate date from request.
   * @param endDate date from request.
   * @return number of business days between startDate and endDate.
   */
  private long businessDays(LocalDate startDate, LocalDate endDate) {
    long numberOfDays = 0;
    while (startDate.isBefore(endDate)) {
      if ((DayOfWeek.SATURDAY != startDate.getDayOfWeek())
          && (DayOfWeek.SUNDAY != startDate.getDayOfWeek())) {
        numberOfDays++;
      }
      startDate = startDate.plusDays(1);
    }
    return ++numberOfDays;
  }

}
