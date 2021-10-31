package app.Schema;

import app.Columns.Column;
import app.Database.DatabaseManager;
import app.Table.Table;
import app.util.Deserializer;
import app.util.Link;
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
        String data = Serializer.toJson(schema);
        String current = request.url();
        String target = current+Path.COLUMN;

        Link[] links = new Link[4];
        links[0] = new Link("GET", "self", current);
        links[1] = new Link("GET", "get_table", target);
        links[2] = new Link("POST", "add_table", current);
        links[3] = new Link("DELETE", "delete_table", target);

        return Link.addProperty(data, links);
    };

    public static Route getСolumn = (Request request, Response response) -> {
        Column column = dbManager.get(request.params(Path.DATABASE_ID)).get(request.params(Path.TABLE_ID)).getColumn(request.params(Path.COLUMN_ID));

        String data = Serializer.toJson(column);
        String current = request.url();

        Link[] links = new Link[1];
        links[0] = new Link("GET", "self", current);

        return Link.addProperty(data, links);
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
