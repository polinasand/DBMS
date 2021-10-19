package app.Database;

import app.util.Deserializer;
import app.util.Path;
import app.util.Serializer;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Collection;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;

public class DatabasesController {
    private static DatabaseManager dbManager = DatabaseManager.getInstance();

    public static Route listDatabases = (Request request, Response response) -> {
        Collection<Database> databases = dbManager.getList();
        return Serializer.toJson(databases);
    };

    public static Route getDatabase = (Request request, Response response) -> {
        String name = request.params(Path.DATABASE_ID);
        Database database = dbManager.get(name);
        return Serializer.toJson(database);
    };

    public static Route addDatabase = (Request request, Response response) -> {
        Database database = Deserializer.getJson().fromJson(request.body(), Database.class);
        Boolean result = dbManager.add(database);
        if (result)
            response.status(HTTP_OK);
        else
            response.status(HTTP_BAD_REQUEST);
        return Serializer.toJson(result);
    };

    public static Route deleteDatabase = (Request request, Response response) -> {
        String name = request.params(Path.DATABASE_ID);
        Boolean result = dbManager.delete(name);
        if (result)
            response.status(HTTP_OK);
        else
            response.status(HTTP_BAD_REQUEST);
        return Serializer.toJson(result);
    };

    public static Route saveDatabase = (Request request, Response response) -> {
        String name = request.params(Path.DATABASE_ID);
        Boolean result = dbManager.save(name);
        if (result)
            response.status(HTTP_OK);
        else
            response.status(HTTP_BAD_REQUEST);
        return Serializer.toJson(result);
    };

    public static Route loadDatabase = (Request request, Response response) -> {
        String name = request.params(Path.DATABASE_ID);
        Database database = dbManager.load(name);
        return Serializer.toJson(database);
    };

}
