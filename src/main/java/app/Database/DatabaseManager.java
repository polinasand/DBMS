package app.Database;

import app.Columns.Column;
import app.Columns.ColumnDeserializer;
import app.Columns.ColumnSerializer;
import app.Table.Table;
import app.Table.TableDeserializer;
import app.Table.TableSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class DatabaseManager {
    private HashMap<String, Database> databases;
    public DatabaseManager() {
        this.databases = new HashMap<>();
    }


    public Database get(String name) {
        return this.databases.getOrDefault(name, null);
    }

    public Collection<Database> getList() {
        return databases.values();
    }

    public Boolean add(Database db) {
        if (databases.containsKey(db.getName())) {
            System.out.println("Duplicate value for database name");
            return false;
        }
        databases.put(db.getName(), db);
        return true;
    }

    public Boolean delete(String name) {
        if (!databases.containsKey(name)) {
            System.out.println("Table name does not exists");
            return false;
        }
        databases.remove(name);
        return true;
    }

    public Database load(String name) {
        String fileName = name + ".txt";
        try {
            Scanner scanner = new Scanner(new File(fileName));
            if (scanner.hasNext()) {
                String data = scanner.next();
                System.out.println(data);
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Table.class, new TableDeserializer())
                        .registerTypeAdapter(Column.class, new ColumnDeserializer())
                        .create();
                return gson.fromJson(data, Database.class);

            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);

            return null;
        }
        return new Database();
    }

    public Boolean save(String db) {


        if (!this.databases.containsKey(db))
            return false;

        String fileName = db + ".txt";
        try {
            FileWriter writer = new FileWriter(fileName);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Table.class, new TableSerializer())
                    .registerTypeAdapter(Column.class, new ColumnSerializer())
                    .create();
            writer.write(gson.toJson(this.databases.get(db)));
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
        return true;
    }



}
