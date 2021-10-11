package app.Columns;

import app.Types.Type;
import com.google.gson.*;


public class ColumnDeserializer implements JsonDeserializer<Column> {


    @Override
    public Column deserialize(JsonElement jsonElement, java.lang.reflect.Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String name = jsonObject.getAsJsonPrimitive("name").getAsString();
        String colType = jsonObject.getAsJsonPrimitive("type").getAsString();
        return Column.getColumn(name, Type.valueOf(colType));
    }
}
