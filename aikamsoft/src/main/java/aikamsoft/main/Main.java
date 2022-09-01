package aikamsoft.main;

import aikamsoft.finder.Finder;
import aikamsoft.objects.criterias.Criteria;
import aikamsoft.objects.criterias.Expenses;
import aikamsoft.objects.criterias.LastName;
import aikamsoft.objects.criterias.PassiveCustomers;
import aikamsoft.objects.criterias.ProductName;
import aikamsoft.objects.entitys.CriteriaResults;
import aikamsoft.objects.entitys.Person;
import aikamsoft.objects.entitys.Results;
import aikamsoft.objects.types.Types;
import aikamsoft.parser.InputParser;
import aikamsoft.parser.OutputParser;
import java.io.IOException;
import java.util.Date;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException, SQLException {

    String operation = null;
    if (args.length == 3) {
      operation = args[0];
      String input_file = args[1];
      String output_file = args[2];
    }

    Results results = new Results();

    if (operation != null && operation.equals("search")) {
      results.setType(operation);
    }
    if (operation != null && operation.equals("stat")) {
      System.out.println(operation);
    }

    InputParser inputParser = new InputParser();
    OutputParser outputParser = new OutputParser();

    List<Criteria> criterias = inputParser.parse("aikamsoft/src/main/resources/input.json");
    Finder finder = new Finder();
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
    outputParser.write("output.json", results);
    finder.getStatistic(LocalDate.of(1900, 1,1), LocalDate.of(2222,2,2));
  }
}
