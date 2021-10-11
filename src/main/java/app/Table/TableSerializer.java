package app.Table;

import app.Columns.Column;
import app.Columns.ColumnSerializer;
import com.google.gson.*;

import java.lang.reflect.Type;

public class TableSerializer implements JsonSerializer<Table> {
    @Override
    public JsonElement serialize(Table table, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("name", table.getName());
        JsonArray jsonArray = new JsonArray();
        ColumnSerializer columnSerializer = new ColumnSerializer();
        for (String colName: table.getSchema().getKeys()) {
            jsonArray.add(columnSerializer.serialize(table.getColumn(colName), Column.class, jsonSerializationContext));
        }
        result.add("schema", jsonArray);
        result.add("rows", jsonSerializationContext.serialize(table.getRows()));
        return result;
    }
}
