package app.Storage;
import app.Database.Database;
import app.Database.DatabaseManager;
import app.util.Link;
import app.util.Path;
import app.util.Serializer;
import spark.Request;
import spark.Response;
import spark.Route;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;

public class StorageController {

    private static DatabaseManager dbManager = DatabaseManager.getInstance();

    public static Route storage = (Request request, Response response) -> {
        String storage = request.params(Path.STORAGE_ID);

        Boolean result = dbManager.setStorage(storage);
        System.out.println(result);
        if (result)
            response.status(HTTP_OK);
        else
            response.status(HTTP_BAD_REQUEST);
        return Serializer.toJson(result);
    };

    public static Route saveDatabase = (Request request, Response response) -> {
        String storage = request.params(Path.STORAGE_ID);
        String name = request.params(Path.DATABASE_ID);
        System.out.println(storage+ "   " +name);

        Boolean result;
        if (storage == "local")
             result = dbManager.save(name);
        else
            result = dbManager.saveToDB(name);

        if (result)
            response.status(HTTP_OK);
        else
            response.status(HTTP_BAD_REQUEST);
        return Serializer.toJson(result);
    };

    public static Route loadDatabase = (Request request, Response response) -> {
        String storage = request.params(Path.STORAGE_ID);
        String name = request.params(Path.DATABASE_ID);

        Database database;
        if (storage == "local")
            database = dbManager.load(name);
        else
            database = dbManager.loadFromDB(name);

        String data = Serializer.toJson(database);
        if (database == null)
            return data;
        String current = request.url();

        Link[] links = new Link[1];
        links[0] = new Link("GET", "self", current);

        return Link.addProperty(data, links);
    };
}
