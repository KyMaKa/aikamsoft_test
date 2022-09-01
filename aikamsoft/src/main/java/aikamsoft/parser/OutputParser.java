package aikamsoft.parser;

import aikamsoft.objects.entitys.Results;
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

}
