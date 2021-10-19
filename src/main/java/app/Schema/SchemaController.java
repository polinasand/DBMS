package app.Schema;

import app.Columns.Column;
import app.Database.DatabaseManager;
import app.Table.Table;
import app.util.Deserializer;
import app.util.Path;
import app.util.Serializer;
import spark.Request;
import spark.Response;
import spark.Route;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;

public class SchemaController {
    private static DatabaseManager dbManager = DatabaseManager.getInstance();

    public static Route listColumns = (Request request, Response response) -> {
        Schema schema = dbManager.get(request.params(Path.DATABASE_ID)).get(request.params(Path.TABLE_ID)).getSchema();
        return Serializer.toJson(schema);
    };

    public static Route getСolumn = (Request request, Response response) -> {
        Column column = dbManager.get(request.params(Path.DATABASE_ID)).get(request.params(Path.TABLE_ID)).getColumn(request.params(Path.COLUMN_ID));
        return Serializer.toJson(column);
    };

    public static Route addColumn = (Request request, Response response) -> {
        Column column = Deserializer.getJson().fromJson(request.body(), Column.class);
        Table table = dbManager.get(request.params(Path.DATABASE_ID)).get(request.params(Path.TABLE_ID));
        Boolean result = table.addColumn(column);
        if (result)
            response.status(HTTP_OK);
        else
            response.status(HTTP_BAD_REQUEST);
        return Serializer.toJson(result);
    };

    public static Route deleteColumn = (Request request, Response response) -> {
        Table table = dbManager.get(request.params(Path.DATABASE_ID)).get(request.params(Path.TABLE_ID));
        String name = request.params(Path.COLUMN_ID);
        Boolean result = table.deleteColumn(name);
        if (result)
            response.status(HTTP_OK);
        else
            response.status(HTTP_BAD_REQUEST);
        return Serializer.toJson(result);
    };
}
