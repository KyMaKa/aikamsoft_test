package aikamsoft.main;

import aikamsoft.controller.SearchController;
import aikamsoft.controller.StatisticController;
import aikamsoft.data.models.input.Criteria;
import aikamsoft.data.models.output.Results;
import aikamsoft.data.models.output.Statistic;
import aikamsoft.service.parser.InputParser;
import aikamsoft.service.parser.OutputParser;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {

  /**
   * Main class, entry point for app.*
   * @param args command-line arguments.
   * @throws IOException
   * @throws SQLException
   */
  public static void main(String[] args) throws IOException, SQLException {

    String operation;
    String input_file;
    String output_file;

    InputParser inputParser = new InputParser();
    OutputParser outputParser = new OutputParser();

    if (args.length == 3) {
      operation = args[0];
      input_file = args[1];
      output_file = args[2];
    } else {
      String errorMessage = "Error! Please enter 3 arguments. Ex: search input.json output.json";
      System.out.println(errorMessage);
      outputParser.writeError(errorMessage);
      return;
    }
    // Initializing controllers for mapping data from DB to entity.
    StatisticController statController = new StatisticController();
    SearchController searchController = new SearchController();

    if (operation.equals("search")) {
      List<Criteria> criterias;
      try {
        criterias = inputParser.parseSearch(input_file);
      } catch (CustomException e) {
        System.out.println(e.getMessage());
        outputParser.writeError(e.getMessage());
        return;
      }
      Results results = searchController.searchOperation(criterias);
      outputParser.write(output_file, results);
    } else if (operation.equals("stat")) {
      Criteria crit;
      try {
        crit = inputParser.parseStat(input_file);
      } catch (CustomException e) {
        System.out.println(e.getMessage());
        outputParser.writeError(e.getMessage());
        return;
      }
      Statistic stat = statController.statOperation(crit);
      outputParser.write(output_file, stat);
    } else {
      String errorMessage = "Error! Please write correct operation type: search or stat";
      System.out.println(errorMessage);
      outputParser.writeError(errorMessage);
    }
  }

}
