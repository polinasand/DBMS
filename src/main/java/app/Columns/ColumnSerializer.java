package app.Columns;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ColumnSerializer implements JsonSerializer<Column> {

    @Override
    public JsonElement serialize(Column column, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("type", column.getType().toString());
        result.addProperty("name", column.name);
        return result;
    }
}

