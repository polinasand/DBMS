package app.util;

import app.Columns.Column;
import app.Columns.ColumnSerializer;
import app.Table.Table;
import app.Table.TableSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Serializer{
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Table.class, new TableSerializer())
            .registerTypeAdapter(Column.class, new ColumnSerializer()).create();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }
}
