package app.util;

import app.Database.Database;
import app.Database.DatabaseManager;
import app.Table.Table;
import spark.Request;

public class MainController {
    private static DatabaseManager dbManager = DatabaseManager.getInstance();

    public static Database getDatabase(Request request) {

            return dbManager.get(request.params(Path.DATABASE_ID));

    }

    public static Table getTable(Request request) {
        Database database = getDatabase(request);
        return database.get(request.params(Path.TABLE_ID));
    }
}
