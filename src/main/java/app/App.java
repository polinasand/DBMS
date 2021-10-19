package app;

import app.Database.Database;
import app.Database.DatabaseManager;
import app.Database.DatabasesController;
import app.Forms.MainForm;
import app.Row.RowsController;
import app.Schema.SchemaController;
import app.Table.TableController;
import app.util.Path;

import static spark.Spark.*;

public class App {
    private static void form() {
        MainForm form = new MainForm();
        form.frame.setVisible(true);
    }

    public static void main(String[] args) {
        //staticFileLocation("/static");
        port(8080);
        before((request, response) -> {
            response.type("application/json");
            response.header("Access-Control-Allow-Origin", "*");
        });

        DatabaseManager dbm = new DatabaseManager();

        Database db = dbm.load("db1");
        dbm.add(db);

        get(Path.DATABASES, DatabasesController.listDatabases);
        post(Path.DATABASES, DatabasesController.addDatabase);
        get(Path.DATABASE, DatabasesController.getDatabase);
        delete(Path.DATABASE, DatabasesController.deleteDatabase);

        get(Path.LOAD_DATABASE, DatabasesController.loadDatabase);
        post(Path.SAVE_DATABASE, DatabasesController.saveDatabase);

        path(Path.DATABASE, () -> {
            get(Path.TABLES, TableController.listTables);
            post(Path.TABLES, TableController.addTable);
            get(Path.TABLE, TableController.getTable);
            delete(Path.TABLE, TableController.deleteTable);

            path(Path.TABLE, () -> {
                get(Path.SCHEMA, SchemaController.listColumns);
                post(Path.SCHEMA, SchemaController.addColumn);
                delete(Path.COLUMN, SchemaController.deleteColumn);
                get(Path.COLUMN, SchemaController.getСolumn);

                get(Path.ROWS, RowsController.listRows);
                post(Path.ROWS, RowsController.addRow);
                delete(Path.ROW, RowsController.deleteRow);
                get(Path.ROW, RowsController.getRow);
            });
        });

    }


}
