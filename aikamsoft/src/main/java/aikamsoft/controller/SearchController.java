package aikamsoft.controller;

import aikamsoft.data.models.input.Criteria;
import aikamsoft.data.models.input.Expenses;
import aikamsoft.data.models.input.LastName;
import aikamsoft.data.models.input.PassiveCustomers;
import aikamsoft.data.models.input.ProductName;
import aikamsoft.data.models.output.CriteriaResults;
import aikamsoft.data.models.output.Person;
import aikamsoft.data.models.output.Results;
import aikamsoft.data.types.Types;
import aikamsoft.service.finder.Finder;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SearchController {

  private final Finder finder;

  /**
   * Controller for 'search' operation.
   */
  public SearchController() {
    this.finder = new Finder();
  }

  /**
   * Maps each criteria and result for it to corresponding output entity.
   * @param criterias list of criterias to perform operations.
   * @return Result class that contains list of criterias and results.
   * @throws SQLException if something wrong with DB query.
   */
  public Results searchOperation(List<Criteria> criterias) throws SQLException {

    Results results = new Results();
    results.setType("search");

    List<CriteriaResults> resultsList = new LinkedList<>();
    for (Criteria c : criterias) {
      if (c.getType().equals(Types.lastName)) {
        LastName lastName = (LastName) c;
        List<Person> people = finder.getByLastName(lastName.getLastName());
        CriteriaResults criteriaResults = new CriteriaResults();
        criteriaResults.setCriteria(lastName);
        criteriaResults.setResults(people);
        resultsList.add(criteriaResults);
      } else if (c.getType().equals(Types.product)) {
        ProductName product = (ProductName) c;
        List<Person> people = finder.getByProduct(product.getName(), product.getMinTimes());
        CriteriaResults criteriaResults = new CriteriaResults();
        criteriaResults.setCriteria(product);
        criteriaResults.setResults(people);
        resultsList.add(criteriaResults);
      } else if (c.getType().equals(Types.cost)) {
        Expenses expenses = (Expenses) c;
        List<Person> people = finder.getByPrice(expenses.getMinExpenses(),
            expenses.getMaxExpenses());
        CriteriaResults criteriaResults = new CriteriaResults();
        criteriaResults.setCriteria(expenses);
        criteriaResults.setResults(people);
        resultsList.add(criteriaResults);
      } else if (c.getType().equals(Types.passiveCustomers)) {
        PassiveCustomers passiveCustomers = (PassiveCustomers) c;
        List<Person> people = finder.getByLeastPurchases(passiveCustomers.getCount());
        CriteriaResults criteriaResults = new CriteriaResults();
        criteriaResults.setCriteria(passiveCustomers);
        criteriaResults.setResults(people);
        resultsList.add(criteriaResults);
      }
    }
    results.setResults(resultsList);

    return results;
  }

}
