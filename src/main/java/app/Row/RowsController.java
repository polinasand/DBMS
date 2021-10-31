package app.Row;

import app.Database.DatabaseManager;
import app.Table.Table;
import app.util.Deserializer;
import app.util.Path;
import app.util.Serializer;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Collection;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;

public class RowsController {
    private static DatabaseManager dbManager = DatabaseManager.getInstance();

    public static Route listRows = (Request request, Response response) -> {
        Table table = dbManager.get(request.params(Path.DATABASE_ID)).get(request.params(Path.TABLE_ID));
        Collection<Row> rows = table.getRows();
        return Serializer.toJson(rows);
    };

    public static Route getRow = (Request request, Response response) -> {
        Row row = dbManager.get(request.params(Path.DATABASE_ID)).get(request.params(Path.TABLE_ID)).getRow(Integer.parseInt(request.params(Path.ROW_ID)));
        return Serializer.toJson(row);
    };

    public static Route addRow = (Request request, Response response) -> {
        Row row = Deserializer.getJson().fromJson(request.body(), Row.class);

        Table table = dbManager.get(request.params(Path.DATABASE_ID)).get(request.params(Path.TABLE_ID));
        Boolean res = table.addRow(row);
        if (res)
            response.status(HTTP_OK);
        else {
            response.status(HTTP_BAD_REQUEST);
            System.out.println("Bad rows");
        }

        return Serializer.toJson(response.status());
    };

    public static Route deleteRow = (Request request, Response response) -> {
        Table table = dbManager.get(request.params(Path.DATABASE_ID)).get(request.params(Path.TABLE_ID));
        String name = request.params(Path.ROW_ID);
        Boolean result = table.deleteRow(Integer.parseInt(name));
        if (result)
            response.status(HTTP_OK);
        else
            response.status(HTTP_BAD_REQUEST);
        return Serializer.toJson(result);
    };
}
