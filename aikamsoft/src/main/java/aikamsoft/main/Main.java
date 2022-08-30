package aikamsoft.main;

import aikamsoft.parser.InputParser;
import java.io.FileNotFoundException;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {

    String operation = null;
    if (args.length == 3) {
      operation = args[0];
      String input_file = args[1];
      String output_file = args[2];
    }
/*    if (operation.equals("search")) {
      System.out.println(operation);
    }
    if (operation.equals("stat")) {
      System.out.println(operation);
    }*/

    InputParser inputParser = new InputParser();
    inputParser.parser("aikamsoft/src/main/resources/input.json");
  }
}
