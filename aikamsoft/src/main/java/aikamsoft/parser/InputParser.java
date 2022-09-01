package aikamsoft.parser;

import aikamsoft.objects.criterias.Criteria;
import aikamsoft.objects.criterias.Expenses;
import aikamsoft.objects.criterias.LastName;
import aikamsoft.objects.criterias.PassiveCustomers;
import aikamsoft.objects.criterias.ProductName;
import aikamsoft.objects.types.Types;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class InputParser {

  public List<Criteria> parse(String filename) throws FileNotFoundException {

    FileReader reader = new FileReader(filename);
    Gson gson = new Gson();
    JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
    JsonArray jsonArray = jsonElement.getAsJsonObject().get("criterias").getAsJsonArray();

    List<Criteria> criterias = new LinkedList<>();

    for (JsonElement j : jsonArray) {
      String type = j.getAsJsonObject().get("type").getAsString();

      if (type.equals(Types.lastName.name()))
        criterias.add(gson.fromJson(j, LastName.class));
      else if (type.equals(Types.product.name()))
        criterias.add(gson.fromJson(j, ProductName.class));
      else if (type.equals(Types.cost.name()))
        criterias.add(gson.fromJson(j, Expenses.class));
      else if (type.equals(Types.passiveCustomers.name()))
        criterias.add(gson.fromJson(j, PassiveCustomers.class));
    }
    return criterias;
  }

}
