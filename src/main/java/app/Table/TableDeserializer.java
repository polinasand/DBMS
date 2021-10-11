package app.Table;

import app.Cell.Cell;
import app.Columns.Column;
import app.Row;
import app.Schema;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TableDeserializer implements JsonDeserializer<Table> {

    @Override
    public Table deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();

        ArrayList<Column> columns = new ArrayList<>();
        jsonObject.get("schema")
                .getAsJsonArray()
                .forEach(columnJsonElement -> columns.add(
                        jsonDeserializationContext.deserialize(columnJsonElement, Column.class)
                ));

        ArrayList<Row> rows = new ArrayList<>();

        jsonObject.get("rows").getAsJsonArray().forEach(rowJsonElement -> {
            ArrayList<Cell> cells = new ArrayList<>();
            rowJsonElement.getAsJsonObject().get("cells")
                    .getAsJsonArray()
                    .forEach(cellJsonElement -> cells.add(
                            jsonDeserializationContext.deserialize(cellJsonElement, Cell.class)
                    ));

            rows.add(new Row(cells));

        });
        System.out.println(name + " celssss "+rows.size());
        return new Table(name, new Schema(columns), rows);
    }
}
