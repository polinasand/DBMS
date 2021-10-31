package app.Table;

import app.Database.Database;
import app.Database.DatabaseManager;
import app.util.Deserializer;
import app.util.Link;
import app.util.Path;
import app.util.Serializer;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Collection;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;

public class TableController {
    private static DatabaseManager dbManager = DatabaseManager.getInstance();

    public static Route listTables = (Request request, Response response) -> {
        Database db = dbManager.get(request.params(Path.DATABASE_ID));
        Collection<Table> tables = db.getList();
        String data = Serializer.toJson(tables);
        String current = request.url();
        String target = current+Path.TABLE_ID;

        Link[] links = new Link[4];
        links[0] = new Link("GET", "self", current);
        links[1] = new Link("GET", "get_table", target);
        links[2] = new Link("POST", "add_table", current);
        links[3] = new Link("DELETE", "delete_table", target);

        return Link.addProperty(data, links);

    };

    public static Route getTable = (Request request, Response response) -> {
        Database db = dbManager.get(request.params(Path.DATABASE_ID));
        Table table = db.get(request.params(Path.TABLE_ID));
        String data = Serializer.toJson(table);
        String current = request.url();
        String rows = current+Path.ROWS;
        String column = current + Path.COLUMN;
        String schema = current + Path.SCHEMA;
        String diff = current + Path.DIFF;

        Link[] links = new Link[5];
        links[0] = new Link("GET", "self", current);
        links[1] = new Link("GET", "list_rows", rows);
        links[2] = new Link("GET", "get_column", column);
        links[3] = new Link("GET", "get_schema", schema);
        links[4] = new Link("GET", "get_diff", diff);

        return Link.addProperty(data, links);
    };

    public static Route addTable = (Request request, Response response) -> {
        Table table = Deserializer.getJson().fromJson(request.body(), Table.class);
        Database db = dbManager.get(request.params(Path.DATABASE_ID));
        Boolean result = db.add(table);
        if (result)
            response.status(HTTP_OK);
        else
            response.status(HTTP_BAD_REQUEST);
        return Serializer.toJson(result);
    };

    public static Route deleteTable = (Request request, Response response) -> {
        Database db = dbManager.get(request.params(Path.DATABASE_ID));
        String name = request.params(Path.TABLE_ID);
        Boolean result = db.delete(name);
        if (result)
            response.status(HTTP_OK);
        else
            response.status(HTTP_BAD_REQUEST);
        return Serializer.toJson(result);
    };

}
