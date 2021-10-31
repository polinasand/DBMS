package app.Table;

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

public class TableOperatorController {
    private static DatabaseManager dbManager = DatabaseManager.getInstance();

    public static Route operation = (Request request, Response response) -> {
        Database db = dbManager.get(request.params(Path.DATABASE_ID));

        Table t1 = db.get(request.params(Path.TABLE_ID));
        Table t2 = db.get(request.params(Path.TABLE_ID_2));
        System.out.println(t1.getName()+t2.getName()+t1+t2);
        Table result = TableOperator.operation(t1, t2);
        if (result != null) {
            response.status(HTTP_OK);
            db.add(result);
        }
        else
            response.status(HTTP_BAD_REQUEST);
        String data = Serializer.toJson(result);
        String current = request.url();

        Link[] links = new Link[1];
        links[0] = new Link("GET", "self", current);

        return Link.addProperty(data, links);
    };
}
