package aikamsoft.service.parser;

import aikamsoft.main.CustomException;
import aikamsoft.service.deserializer.LocalDateDeserializer;
import aikamsoft.data.models.input.Criteria;
import aikamsoft.data.models.input.Dates;
import aikamsoft.data.models.input.Expenses;
import aikamsoft.data.models.input.LastName;
import aikamsoft.data.models.input.PassiveCustomers;
import aikamsoft.data.models.input.ProductName;
import aikamsoft.data.types.Types;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Parses input file. *
 */
public class InputParser {

  /**
   * Parses input file to corresponding objects. *
   * @param filename file to read and parse.
   * @return list of criterias.
   * @throws CustomException if file can't be read of contains incorrect data.
   */
  public List<Criteria> parseSearch(String filename) throws CustomException {

    FileReader reader;
    try {
      reader = new FileReader(filename);
    } catch (FileNotFoundException e) {
      String errorMessage = "File not found!";
      System.out.println(errorMessage);
      throw new CustomException(errorMessage);
    }
    Gson gson = new Gson();
    try {
      JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
      JsonArray jsonArray = jsonElement.getAsJsonObject().get("criterias").getAsJsonArray();

      List<Criteria> criterias = new LinkedList<>();

      for (JsonElement j : jsonArray) {
        String type = j.getAsJsonObject().get("type").getAsString();

        if (type.equals(Types.lastName.name())) {
          criterias.add(gson.fromJson(j, LastName.class));
        } else if (type.equals(Types.product.name())) {
          criterias.add(gson.fromJson(j, ProductName.class));
        } else if (type.equals(Types.cost.name())) {
          criterias.add(gson.fromJson(j, Expenses.class));
        } else if (type.equals(Types.passiveCustomers.name())) {
          criterias.add(gson.fromJson(j, PassiveCustomers.class));
        }
      }
      return criterias;
    } catch (Exception e) {
      String message = "Incorrect input file data. Criterias is missing.";
      throw new CustomException(message);
    }
  }

  /**
   * Parses input file to corresponding objects. *
   * @param filename file to read and parse.
   * @return single criteria with dates.
   * @throws CustomException if file can't be read of contains incorrect data.
   */
  public Criteria parseStat(String filename) throws CustomException {

    FileReader reader;
    try {
      reader = new FileReader(filename);
    } catch (FileNotFoundException e) {
      throw new CustomException(e.getMessage());
    }
    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
        .create();
    try {
      return gson.fromJson(reader, Dates.class);
    } catch (Exception e) {
      String message = "Wrong date format.";
      throw new CustomException(message);
    }
  }

}
