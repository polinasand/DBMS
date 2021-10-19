package app.Table;

import app.Database.Database;
import app.Database.DatabaseManager;
import app.util.Deserializer;
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
        return Serializer.toJson(tables);
    };

    public static Route getTable = (Request request, Response response) -> {
        Database db = dbManager.get(request.params(Path.DATABASE_ID));
        Table table = db.get(request.params(Path.TABLE_ID));
        return Serializer.toJson(table);
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
