package app.Storage;
import app.Database.Database;
import app.Database.DatabaseManager;
import app.util.Link;
import app.util.MainController;
import app.util.Path;
import app.util.Serializer;
import spark.Request;
import spark.Response;
import spark.Route;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;

public class StorageController {

    private static DatabaseManager dbManager = DatabaseManager.getInstance();

    private static Storage getStorage(Request request) {
        String name = request.params(Path.STORAGE_ID);
        if (name.equals("mysql")) {
            return new MySQLClient();
        }
        if (name.equals("mongodb"))
            return new MongoDBClient();
        return null;
    }

    public static Route listDatabases = (Request request, Response response) -> {
        Storage client = getStorage(request);

        System.out.println(client+request.params(Path.STORAGE_ID));
        if (client == null) {
            return null;
        }
        return Serializer.toJson(client.getList());
    };

    public static Route saveDatabase = (Request request, Response response) -> {
        Storage client = getStorage(request);
        Boolean result;
        if (client == null) {
            result = false;
        }
        else {
            client.saveDatabase(MainController.getDatabase(request));
            result = true;
        }

        if (result)
            response.status(HTTP_OK);
        else
            response.status(HTTP_BAD_REQUEST);
        return Serializer.toJson(result);
    };

    public static Route loadDatabase = (Request request, Response response) -> {
        Storage client = getStorage(request);
        String name = request.params(Path.DATABASE_ID);

        Database database = client.loadDatabase(name);

        String data = Serializer.toJson(database);
        if (database == null)
            return data;
        String current = request.url();

        Link[] links = new Link[1];
        links[0] = new Link("GET", "self", current);

        return Link.addProperty(data, links);
    };
}
