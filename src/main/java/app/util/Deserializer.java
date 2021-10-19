package app.util;

import app.Columns.Column;
import app.Columns.ColumnDeserializer;
import app.Table.Table;
import app.Table.TableDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Deserializer {
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Table.class, new TableDeserializer())
            .registerTypeAdapter(Column.class, new ColumnDeserializer())
            .create();
    public static Gson getJson() {
        return gson;
    }

}
