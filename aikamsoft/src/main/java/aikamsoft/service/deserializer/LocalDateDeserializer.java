package aikamsoft.service.deserializer;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Custom Type adapted to deserialize Date to LocalDate.*
 */
public class LocalDateDeserializer extends TypeAdapter<LocalDate> {
@Override
public void write( final JsonWriter jsonWriter, final LocalDate localDate ) throws IOException {
    jsonWriter.value(localDate.toString());
    }

@Override
public LocalDate read( final JsonReader jsonReader ) throws IOException {
    return LocalDate.parse(jsonReader.nextString());
    }
}
