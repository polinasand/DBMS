package app.Database;

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

public class DatabasesController {
    private static DatabaseManager dbManager = DatabaseManager.getInstance();

    public static Route listDatabases = (Request request, Response response) -> {
        String storage = request.params(Path.STORAGE_ID);

        Collection<Database> databases = dbManager.getList();
        String data = Serializer.toJson(databases);
        String current = request.url();
        String target = current+Path.DATABASE_ID;

        Link[] links = new Link[4];
        links[0] = new Link("GET", "self", current);
        links[1] = new Link("GET", "get_database", target);
        links[2] = new Link("POST", "add_database", current);
        links[3] = new Link("DELETE", "delete_database", target);

        return Link.addProperty(data, links);
    };

    public static Route getDatabase = (Request request, Response response) -> {
        String name = request.params(Path.DATABASE_ID);
        Database database = dbManager.get(name);
        String data = Serializer.toJson(database);
        String current = request.url();
        String target = current+Path.TABLES;

        Link[] links = new Link[2];
        links[0] = new Link("GET", "self", current);
        links[1] = new Link("GET", "list_tables", target);

        return Link.addProperty(data, links);

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



}
