package app;

import app.Database.DatabaseManager;
import app.Database.DatabasesController;
import app.Forms.MainForm;
import app.Row.RowsController;
import app.Schema.SchemaController;
import app.Storage.StorageController;
import app.Table.TableController;
import app.Table.TableOperatorController;
import app.util.Path;

import static spark.Spark.*;

public class App {
    private static void form() {
        MainForm form = new MainForm();
        form.frame.setVisible(true);
    }

    public static void main(String[] args) {
        port(4567);
        before((request, response) -> {
            response.type("application/json");
            response.header("Access-Control-Allow-Origin", "*");

        });

        DatabaseManager dbm = new DatabaseManager();



        get(Path.STORAGE, StorageController.listDatabases);
        get(Path.LOAD_DATABASE, StorageController.loadDatabase);
        post(Path.SAVE_DATABASE, StorageController.saveDatabase);


            get(Path.DATABASES, DatabasesController.listDatabases);
            post(Path.DATABASES, DatabasesController.addDatabase);
            get(Path.DATABASE, DatabasesController.getDatabase);
            delete(Path.DATABASE, DatabasesController.deleteDatabase);

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

                    get(Path.DIFF, TableOperatorController.operation);


                });
            });
    }


}
