package aikamsoft.parser;

import aikamsoft.objects.criterias.Expenses;
import aikamsoft.objects.criterias.LastName;
import aikamsoft.objects.criterias.PassiveCustomers;
import aikamsoft.objects.criterias.ProductName;
import aikamsoft.objects.types.Types;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class InputParser {

  public void parser(String filename) throws FileNotFoundException {
    FileReader reader = new FileReader(filename);
    Gson gson = new Gson();
    JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
    JsonArray jsonArray = jsonElement.getAsJsonObject().get("criterias").getAsJsonArray();

    LastName lastName = null;
    Expenses expenses = null;
    PassiveCustomers passiveCustomers = null;
    ProductName productName = null;
    for (JsonElement j : jsonArray) {
      String type = j.getAsJsonObject().get("type").getAsString();
      if (type.equals(Types.lastName.name()))
        lastName = gson.fromJson(j, LastName.class);
      else if (type.equals(Types.product.name()))
        productName = gson.fromJson(j, ProductName.class);
      else if (type.equals(Types.cost.name()))
        expenses = gson.fromJson(j, Expenses.class);
      else if (type.equals(Types.passiveCustomers.name()))
        passiveCustomers = gson.fromJson(j, PassiveCustomers.class);
    }
    System.out.println(lastName != null ? lastName.getName() : null);

    System.out.println(productName != null ? productName.getName() : null);

    System.out.println(expenses != null ? expenses.getMaxExpenses() : null);

    System.out.println(passiveCustomers != null ? passiveCustomers.getValue() : null);
  }

}
