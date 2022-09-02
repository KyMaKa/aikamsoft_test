package aikamsoft.service.parser;

import aikamsoft.data.models.output.Error;
import aikamsoft.data.models.output.Results;
import aikamsoft.data.models.output.Statistic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class OutputParser {

  public void write(String filename, Results results) throws IOException {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    FileWriter writer = new FileWriter(filename);
    gson.toJson(results, writer);
    writer.close();
  }

  public void write(String filename, Statistic results) throws IOException {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    FileWriter writer = new FileWriter(filename);
    gson.toJson(results, writer);
    writer.close();
  }

  public void writeError(String message) {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Error error = new Error(message);

    FileWriter writer;
    try {
      writer = new FileWriter("output.json");
      gson.toJson(error, writer);
      writer.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

}
